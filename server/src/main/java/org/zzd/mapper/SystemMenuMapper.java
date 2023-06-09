package org.zzd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.zzd.entity.SystemMenu;

import java.util.List;

/**
 * 菜单表(SystemMenu)表数据库访问层
 *
 * @author zzd
 * @since 2023-03-13 14:26:10
 */
@Repository
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {
    List<SystemMenu> getSystemUserMenuList(Long userId);
    List<SystemMenu> getAsideMenuList(Long userId);
}