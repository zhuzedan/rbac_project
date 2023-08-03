package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzd.dto.menu.DoAssignMenuDto;
import org.zzd.entity.SystemMenu;
import org.zzd.entity.SystemRoleMenu;
import org.zzd.mapper.SystemMenuMapper;
import org.zzd.mapper.SystemRoleMenuMapper;
import org.zzd.service.SystemMenuService;
import org.zzd.utils.BeanCopyUtils;
import org.zzd.utils.TreeParser;
import org.zzd.vo.menu.AssignMenuVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单表(SystemMenu)表服务实现类
 *
 * @author zzd
 * @since 2023-03-19 21:56:53
 */
@Service("systemMenuService")
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements SystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private SystemRoleMenuMapper systemRoleMenuMapper;

    @Override
    public List<AssignMenuVo> findSystemMenuByRoleId(Long roleId) {
        //获取所有status为1的权限列表
        List<SystemMenu> menuList = systemMenuMapper.selectList(new QueryWrapper<SystemMenu>().eq("status", 1));
        List<AssignMenuVo> assignMenuVos = BeanCopyUtils.copyBeanList(menuList, AssignMenuVo.class);
        //根据角色id获取角色权限
        List<SystemRoleMenu> roleMenus = systemRoleMenuMapper.selectList(new QueryWrapper<SystemRoleMenu>().eq("role_id", roleId));
        //获取该角色已分配的所有权限id
        List<Long> roleMenuIds = new ArrayList<>();
        for (SystemRoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getMenuId());
        }
        //遍历所有权限列表
        for (AssignMenuVo assignMenuVo : assignMenuVos) {
            //设置该权限已被分配
            assignMenuVo.setSelect(roleMenuIds.contains(assignMenuVo.getId()));
        }
        //将权限列表转换为权限树
        return TreeParser.getTreeList(0L, assignMenuVos);
    }

    @Override
    public void doAssign(DoAssignMenuDto assignMenuDto) {
        //删除已分配的权限
        systemRoleMenuMapper.delete(new LambdaQueryWrapper<SystemRoleMenu>().eq(SystemRoleMenu::getRoleId, assignMenuDto.getRoleId()));
        //遍历所有已选择的权限id
        for (Long menuId : assignMenuDto.getMenuIdList()) {
            if (menuId != null) {
                //创建SysRoleMenu对象
                SystemRoleMenu sysRoleMenu = new SystemRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(assignMenuDto.getRoleId());
                //添加新权限
                systemRoleMenuMapper.insert(sysRoleMenu);
            }
        }
    }
}
