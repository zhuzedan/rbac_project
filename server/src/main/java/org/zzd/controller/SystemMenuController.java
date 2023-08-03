package org.zzd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zzd.dto.menu.DoAssignMenuDto;
import org.zzd.result.ResponseResult;
import org.zzd.service.SystemMenuService;
import org.zzd.vo.menu.AssignMenuVo;

import java.util.List;

/**
 * @apiNote 菜单表(SystemMenu)表控制层
 * @author zzd
 * @date 2023-05-24 10:21:08
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/api/systemMenu")
public class SystemMenuController {

    @Autowired
    private SystemMenuService systemMenuService;

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("/toAssign")
    public ResponseResult<List<AssignMenuVo>> toAssign(Long roleId) {
        return ResponseResult.success(systemMenuService.findSystemMenuByRoleId(roleId));
    }

    @ApiOperation(value = "分配角色的菜单权限")
    @PostMapping("/doAssign")
    public ResponseResult<?> doAssign(@RequestBody DoAssignMenuDto assignMenuDto) {
        systemMenuService.doAssign(assignMenuDto);
        return ResponseResult.success();
    }
}

