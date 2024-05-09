package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zzd.constant.SecurityConstants;
import org.zzd.dto.user.*;
import org.zzd.entity.SystemMenu;
import org.zzd.entity.SystemUser;
import org.zzd.entity.SystemUserRole;
import org.zzd.exception.ResponseException;
import org.zzd.mapper.SystemMenuMapper;
import org.zzd.mapper.SystemUserMapper;
import org.zzd.mapper.SystemUserRoleMapper;
import org.zzd.pojo.SecuritySystemUser;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.SystemUserService;
import org.zzd.utils.*;
import org.zzd.vo.user.QueryUserPageVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @apiNote 用户表(SystemUser)表服务实现类
 * @author zzd
 * @since 2023-03-02 13:53:39
 */
@Transactional
@Service("systemUserService")
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private SystemMenuMapper systemMenuMapper;
    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private CacheUtils<String, String> cacheUtils;

    private final Logger logger = LoggerFactory.getLogger(SystemUserServiceImpl.class);

    @Override
    public LoginRespDto login(LoginDto loginDto) throws ResponseException {
        SystemUser login = doLogin(loginDto.getUsername(), loginDto.getPassword());
        SecuritySystemUser user = new SecuritySystemUser(login);
        String token = jwtTokenUtil.generateToken(user);
        return new LoginRespDto(SecurityConstants.TOKEN_PREFIX, token, jwtTokenUtil.getExpiredDateFromToken(token).getTime());
    }

    public SystemUser doLogin(String username, String password) {

        SystemUser systemUser;
        UserDetails userDetails;
        ThreadLocalUtil.setUsername(username);
        try {
            userDetails = loadUserByUsername(username);
            systemUser = ((SecuritySystemUser) userDetails).getSystemUser();
        } catch (Exception e) {
            throw new ResponseException(e.getMessage());
        }
        if (!passwordEncoder.matches(password, systemUser.getPassword())) {
            throw new ResponseException(ResultCodeEnum.PASSWORD_ERROR);
        }
        if (!userDetails.isEnabled()) {
            throw new ResponseException(ResultCodeEnum.ACCOUNT_STOP);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return systemUser;
    }

    @Override
    public ResponseResult<?> loginCaptcha(LoginCaptchaDto loginCaptchaDto, HttpServletRequest request) {
        SystemUser login = doCaptchaLogin(loginCaptchaDto, request);
        SecuritySystemUser user = new SecuritySystemUser(login);
        String token = jwtTokenUtil.generateToken(user);
        LoginRespDto loginRespDto = new LoginRespDto(SecurityConstants.TOKEN_PREFIX, token, jwtTokenUtil.getExpiredDateFromToken(token).getTime());
        return ResponseResult.success("登录成功", loginRespDto);
    }

    public SystemUser doCaptchaLogin(LoginCaptchaDto loginCaptchaDto, HttpServletRequest request) {
        SystemUser systemUser;
        UserDetails userDetails;
        Object captcha = cacheUtils.get("captcha");
        logger.info("调用验证码接口后存在缓存中的：" + captcha);
        logger.info("前端实际输入的：" + loginCaptchaDto.getCaptcha());
        if (null == captcha || !captcha.equals(loginCaptchaDto.getCaptcha())) {
            throw new ResponseException("验证码不正确");
        }
        try {
            userDetails = loadUserByUsername(loginCaptchaDto.getUsername());
            systemUser = ((SecuritySystemUser) userDetails).getSystemUser();
        } catch (Exception e) {
            throw new ResponseException(ResultCodeEnum.LOGIN_ERROR);
        }
        if (!passwordEncoder.matches(loginCaptchaDto.getPassword(), systemUser.getPassword())) {
            throw new ResponseException("密码错误");
        }
        if (!userDetails.isEnabled()) {
            throw new ResponseException("账号已停用");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return systemUser;
    }

    /**
     * @apiNote 获取用户信息
     * @return org.zzd.result.ResponseResult
     */
    @Override
    public ResponseResult<?> getInfo() {
        SecuritySystemUser systemSecurityUser = SecurityUtils.getCurrentSecuritySystemUser();
        SystemUser systemUser = systemSecurityUser.getSystemUser();
        List<SystemMenu> menus = systemSecurityUser.getMenus();
        //获取角色权限编码字段
        Object[] perms = menus.stream().filter(Objects::nonNull).map(SystemMenu::getPerms).filter(StringUtils::isNotBlank).toArray();
        UserInfoDto userInfoDto = new UserInfoDto(systemUser.getId(), systemUser.getRealName(), systemUser.getAvatar(), systemUser.getDescription(), perms);
        return ResponseResult.success(userInfoDto);
    }

    @Override
    public PageHelper<QueryUserPageVo> queryPage(UserInfoPageParam params) {
        QueryWrapper<SystemUser> wrapper = new QueryWrapper<>();
        // 用户名模糊查询
        if (!StringUtils.isBlank(params.getUsername())) {
            wrapper.like("username", params.getUsername());
        }
        //用户状态
        if (!StringUtils.isBlank(params.getStatus())) {
            wrapper.eq("status", params.getStatus());
        }
        //是否移动端用户
        if (!StringUtils.isBlank(params.getUserType())) {
            wrapper.eq("user_type", params.getUserType());
        }
        //角色
        if (!StringUtils.isBlank(params.getRoleId())) {
            List<String> userIds = systemUserRoleMapper.getUserIds(params.getRoleId());
            wrapper.in("u.id", userIds);
        }
        Page<SystemUser> page = new Page<>(params.getPageNum(), params.getPageSize());
        IPage<QueryUserPageVo> iPage = systemUserMapper.selectUserPage(page, wrapper);
        return PageHelper.restPage(iPage);
    }

    /**
     * @apiNote 新建用户
     * @param createUserDto : 新建用户的对象
     */
    @Override
    public void insertSystemUser(CreateUserDto createUserDto) {
        Long count = systemUserMapper.selectCount(new QueryWrapper<SystemUser>().eq("username", createUserDto.getUsername()));
        if (count > 0) {
            throw new ResponseException("用户已存在");
        }
        SystemUser systemUser = BeanCopyUtils.copyBean(createUserDto, SystemUser.class);
        if (!StringUtils.isBlank(systemUser.getPassword())) {
            systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        }
        systemUser.setCreateBy(SecurityUtils.getCurrentSystemUser().getUsername());
        //创建人
        int flag = systemUserMapper.insert(systemUser);
        if (flag == 0) {
            throw new ResponseException(ResultCodeEnum.CREATE_FAIL);
        }
        //把用户角色添加到数据库中
        systemUserRoleMapper.insertUserRole(systemUser.getId(), createUserDto.getRoleIds());
    }

    @Override
    public void updateSystemUser(UpdateUserDto updateUserDto) {
        SystemUser systemUser = BeanCopyUtils.copyBean(updateUserDto, SystemUser.class);
        //更新人
        systemUser.setUpdateBy(SecurityUtils.getCurrentSystemUser().getUsername());
        int flag = systemUserMapper.updateById(systemUser);
        if (flag == 0) {
            throw new ResponseException("更新失败");
        }
        // 删除用户角色
        systemUserRoleMapper.delete(new LambdaQueryWrapper<SystemUserRole>().eq(SystemUserRole::getUserId, updateUserDto.getId()));
        // 重新添加用户角色
        systemUserRoleMapper.insertUserRole(systemUser.getId(), updateUserDto.getRoleIds());
    }

    @Override
    public void deleteSystemUser(Long id) {
        SystemUser systemUser = systemUserMapper.selectById(id);
        if (systemUser == null) {
            throw new ResponseException(ResultCodeEnum.DELETE_FAIL);
        }
        int flag = systemUserMapper.deleteById(id);
        if (flag == 0) {
            throw new ResponseException(ResultCodeEnum.DELETE_FAIL);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        SystemUser systemUser = systemUserMapper.selectOne(new QueryWrapper<SystemUser>().eq("username", username));
        //获取菜单列表
        List<SystemMenu> menuList = systemMenuMapper.getSystemUserMenuList(systemUser.getId());
        //获取权限集合
        String[] authoritiesArray = menuList.stream()
                .filter(Objects::nonNull)
                .map(SystemMenu::getPerms)
                .filter(StringUtils::isNotBlank).toArray(String[]::new);
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authoritiesArray);
        return new SecuritySystemUser(systemUser, menuList, authorities);

    }
}