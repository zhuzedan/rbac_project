package org.zzd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zzd.result.ResponseResult;
import org.zzd.service.SystemMenuService;

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

    @ApiOperation(value = "获取菜单")
    @GetMapping("findNodes")
    public ResponseResult findNodes() {
        return systemMenuService.findNodes();
    }

    @ApiOperation(value = "获取左侧栏的菜单")
    @GetMapping("/queryAsideMenus")
    public ResponseResult queryAsideMenu() {
        return systemMenuService.queryAsideMenu();
    }
}

