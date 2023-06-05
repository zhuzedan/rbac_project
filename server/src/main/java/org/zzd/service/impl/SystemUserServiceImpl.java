package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zzd.constant.SecurityConstants;
import org.zzd.dto.user.*;
import org.zzd.entity.SystemMenu;
import org.zzd.entity.SystemUser;
import org.zzd.exception.ResponseException;
import org.zzd.mapper.SystemMenuMapper;
import org.zzd.mapper.SystemUserMapper;
import org.zzd.pojo.SecuritySystemUser;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.SystemUserService;
import org.zzd.utils.JwtTokenUtil;
import org.zzd.utils.PageHelper;
import org.zzd.utils.SecurityUtils;
import org.zzd.utils.ThreadLocalUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @apiNote 用户表(SystemUser)表服务实现类
 * @author zzd
 * @since 2023-03-02 13:53:39
 */
@Service("systemUserService")
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private SystemMenuMapper systemMenuMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    private final Logger logger = LoggerFactory.getLogger(SystemUserServiceImpl.class);

    @Override
    public ResponseResult login(LoginDto loginDto) throws ResponseException {
        SystemUser login = doLogin(loginDto.getUsername(), loginDto.getPassword());
        SecuritySystemUser user = new SecuritySystemUser(login);
        String token = jwtTokenUtil.generateToken(user);
        LoginRespDto loginRespDto = new LoginRespDto(SecurityConstants.TOKEN_PREFIX, token, jwtTokenUtil.getExpiredDateFromToken(token).getTime());
        return ResponseResult.success("登录成功", loginRespDto);
    }

    public SystemUser doLogin(String username, String password) {

        SystemUser systemUser;
        UserDetails userDetails;
        ThreadLocalUtil.setUsername(username);
        try {
            userDetails = loadUserByUsername(username);
            systemUser = ((SecuritySystemUser) userDetails).getSystemUser();
        } catch (Exception e) {
            throw new ResponseException(ResultCodeEnum.LOGIN_ERROR);
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
    public ResponseResult loginCaptcha(LoginCaptchaDto loginCaptchaDto, HttpServletRequest request) {
        SystemUser login = doCaptchaLogin(loginCaptchaDto, request);
        SecuritySystemUser user = new SecuritySystemUser(login);
        String token = jwtTokenUtil.generateToken(user);
        LoginRespDto loginRespDto = new LoginRespDto(SecurityConstants.TOKEN_PREFIX, token, jwtTokenUtil.getExpiredDateFromToken(token).getTime());
        return ResponseResult.success("登录成功", loginRespDto);
    }

    public SystemUser doCaptchaLogin(LoginCaptchaDto loginCaptchaDto, HttpServletRequest request) {
        SystemUser systemUser;
        UserDetails userDetails;
        String captcha = (String) request.getSession().getAttribute("captcha");
        logger.info("调用验证码接口后存在session中的：" + captcha);
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
    public ResponseResult getInfo() {
        SecuritySystemUser systemSecurityUser = SecurityUtils.getCurrentSecuritySystemUser();
        SystemUser systemUser = systemSecurityUser.getSystemUser();
        List<SystemMenu> menus = systemSecurityUser.getMenus();
        //获取角色权限编码字段
        Object[] perms = menus.stream().filter(Objects::nonNull).map(SystemMenu::getPerms).filter(StringUtils::isNotBlank).toArray();
        UserInfoDto userInfoDto = new UserInfoDto(systemUser.getId(), systemUser.getNickname(), systemUser.getAvatar(), systemUser.getDescription(), perms);
        return ResponseResult.success(userInfoDto);
    }

    @Override
    public ResponseResult<PageHelper<SystemUser>> queryPage(UserInfoPageParam params) {
        LambdaQueryWrapper<SystemUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 用户名模糊查询
        if (!StringUtils.isBlank(params.getUsername())) {
            lambdaQueryWrapper.like(SystemUser::getUsername, params.getUsername());
        }
        //用户状态
        if (!StringUtils.isBlank(params.getStatus())) {
            lambdaQueryWrapper.eq(SystemUser::getStatus, params.getStatus());
        }
        //是否移动端用户
        if (!StringUtils.isBlank(params.getUserType())) {
            lambdaQueryWrapper.eq(SystemUser::getUserType, params.getUserType());
        }
        Page<SystemUser> page = new Page<>(params.getPageNum(), params.getPageSize());
        IPage<SystemUser> iPage = systemUserMapper.selectPage(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
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
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(createUserDto,systemUser);
        if (!StringUtils.isBlank(systemUser.getPassword())) {
            systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        }
        //创建人
        systemUser.setCreateBy(SecurityUtils.getCurrentSystemUser().getUsername());
        int flag = systemUserMapper.insert(systemUser);
        if (flag == 0) {
            throw new ResponseException(ResultCodeEnum.CREATE_FAIL);
        }
    }

    @Override
    public void updateSystemUser(UpdateUserDto updateUserDto) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(updateUserDto,systemUser);
        int flag = systemUserMapper.updateById(systemUser);
        if (flag == 0) {
            throw new ResponseException("更新失败");
        }
    }

    @Override
    public void deleteSystemUser(Long id) {
        SystemUser systemUser = systemUserMapper.selectById(id);
        if (systemUser == null) {
            throw new ResponseException(ResultCodeEnum.DELETE_FAIL);
        }
        int flag = systemMenuMapper.deleteById(id);
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