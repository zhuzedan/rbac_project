package org.zzd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.zzd.entity.SystemRole;
import org.zzd.exception.ResponseException;
import org.zzd.param.BasePageParam;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.SystemRoleService;
import org.zzd.utils.PageHelper;

import java.util.List;
import java.util.Objects;

/**
 * @apiNote 角色(SystemRole)控制器
 * @author zzd
 * @date 2023-05-26 14:22:55
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/systemRole")
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;

    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPageSystemRole")
    @PreAuthorize("@ex.hasAuthority('btn.sysRole.list')")
    public ResponseResult<PageHelper<SystemRole>> queryPage(BasePageParam params) {
        return systemRoleService.queryPage(params);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/readSystemRole")
    public ResponseResult selectOne(Integer id) {
        SystemRole systemRole = systemRoleService.getById(id);
        if (!Objects.isNull(systemRole)) {
            return ResponseResult.success(systemRole);
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID);
        }
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/insertSystemRole")
    @PreAuthorize("@ex.hasAuthority('btn.sysRole.insert')")
    public ResponseResult insert(@RequestBody SystemRole systemRole) {
        boolean flag = systemRoleService.save(systemRole);
        if (flag) {
            return ResponseResult.success();
        }else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID);
        }
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/updateSystemRole")
    public ResponseResult update(@RequestBody SystemRole systemRole) {
        systemRoleService.updateById(systemRole);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("deleteSystemRole")
    public ResponseResult delete(Long id) {
        boolean flag = systemRoleService.removeById(id);
        if (flag) {
            return ResponseResult.success();
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID);
        }
    }
    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/batchRemoveSystemRole")
    public ResponseResult batchRemove(@RequestBody List<Long> idList) {
        systemRoleService.removeByIds(idList);
        return ResponseResult.success();
    }
}

