package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public ResponseResult findNodes() {
        List<SystemMenu> sysMenuList = this.list();
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return ResponseResult.error("菜单为空");
        }
        //构建树形数据
        List<SystemMenu> result = MenuHelper.buildTree(sysMenuList);
        return ResponseResult.success(result);
    }
}

