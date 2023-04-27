package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzd.entity.SystemMenu;
import org.zzd.mapper.SystemMenuMapper;
import org.zzd.result.ResponseResult;
import org.zzd.service.SystemMenuService;
import org.zzd.utils.MenuHelper;

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
     * @return org.zzd.result.ResponseResult
     * @apiNote 查管理系统左侧栏菜单
     */
    @Override
    public ResponseResult queryAsideMenu() {
        //先查出一级菜单
        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        List<SystemMenu> parentMenuList = systemMenuMapper.selectList(queryWrapper);
        //构建树形数据
        List<SystemMenu> result = MenuHelper.buildTree(parentMenuList);
        return ResponseResult.success(result);
    }
}

