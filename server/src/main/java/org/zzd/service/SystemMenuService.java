package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.dto.menu.DoAssignMenuDto;
import org.zzd.entity.SystemMenu;
import org.zzd.vo.menu.AssignMenuVo;

import java.util.List;

/**
 * 菜单表(SystemMenu)表服务接口
 *
 * @author zzd
 * @since 2023-03-19 21:56:53
 */
public interface SystemMenuService extends IService<SystemMenu> {

    List<AssignMenuVo> findSystemMenuByRoleId(Long roleId);

    void doAssign(DoAssignMenuDto assignMenuDto);
}

