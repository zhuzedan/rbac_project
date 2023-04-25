package org.zzd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.zzd.annotation.Log;
import org.zzd.dto.user.CreateUserDto;
import org.zzd.entity.SystemUser;
import org.zzd.enums.BusinessType;
import org.zzd.enums.OperatorType;
import org.zzd.exception.ResponseException;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.SystemUserService;
import org.zzd.utils.PageHelper;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 用户表(SystemUser)表控制层
 *
 * @author zzd
 * @since 2023-03-02 13:53:39
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/systemUser")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @Log(title = "分页查询用户", businessType = BusinessType.SELECT, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "分页查询")
    @PostMapping("/queryPage")
    @PreAuthorize("hasAuthority('bnt.sysUser.list')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
            @ApiImplicitParam(name = "startDate", value = "起始日期", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endDate", value = "结束日期", paramType = "query", dataType = "date")
    })
    public ResponseResult<PageHelper<SystemUser>> queryPage(@ApiIgnore @RequestParam HashMap params) {
        return systemUserService.queryPage(params);
    }

    @Log(title = "用户详情", businessType = BusinessType.READ, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Integer id) {
        SystemUser systemUser = systemUserService.getById(id);
        if (!Objects.isNull(systemUser)) {
            return ResponseResult.success(systemUser);
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_VALID.getMessage());
        }
    }

    @Log(title = "新增用户", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "新增数据")
    @PostMapping("/insertSystemUser")
    public ResponseResult insert(@RequestBody CreateUserDto createUserDto) {
        return systemUserService.insertSystemUser(createUserDto);
    }

    @Log(title = "修改用户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody SystemUser systemUser) {
        systemUserService.updateById(systemUser);
        return ResponseResult.success();
    }

    @Log(title = "删除用户", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "删除数据")
    @DeleteMapping("delete")
    public ResponseResult delete(Long id) {
        boolean flag = systemUserService.removeById(id);
        if (flag) {
            return ResponseResult.success();
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_VALID.getMessage());
        }
    }

    @Log(title = "批量删除用户", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/batchRemove")
    public ResponseResult batchRemove(@RequestBody List<Long> idList) {
        systemUserService.removeByIds(idList);
        return ResponseResult.success();
    }
}

