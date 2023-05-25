package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzd.entity.SystemMenu;
import org.zzd.mapper.SystemMenuMapper;
import org.zzd.result.ResponseResult;
import org.zzd.service.SystemMenuService;
import org.zzd.utils.MenuHelper;
import org.zzd.utils.SecurityUtils;

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

    @Override
    public ResponseResult findNodes() {
        List<SystemMenu> menuList = this.list();
        if (CollectionUtils.isEmpty(menuList)) {
            return ResponseResult.error("菜单为空");
        }
        //构建树形数据
        List<SystemMenu> result = MenuHelper.buildTree(menuList);
        return ResponseResult.success(result);
    }

    /**
     * @apiNote 前端侧边栏菜单列表
     */
    @Override
    public ResponseResult queryAsideMenu() {
        List<SystemMenu> systemUserMenuList = systemMenuMapper.getAsideMenuList(SecurityUtils.getUserId());
        //构建树形数据
        List<SystemMenu> result = MenuHelper.buildTree(systemUserMenuList);
        return ResponseResult.success(result);
    }
}
