package org.zzd.controller;

import cn.hutool.core.lang.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zzd.annotation.Log;
import org.zzd.dto.user.CreateUserDto;
import org.zzd.dto.user.UpdateUserDto;
import org.zzd.dto.user.UserInfoPageParam;
import org.zzd.entity.SystemUser;
import org.zzd.enums.BusinessType;
import org.zzd.enums.OperatorType;
import org.zzd.result.ResponseResult;
import org.zzd.service.SystemUserService;
import org.zzd.utils.PageHelper;
import org.zzd.vo.user.QueryUserPageVo;

import java.util.List;

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

    @Log(title = "获取当前登录用户信息", businessType = BusinessType.SELECT, operatorType = OperatorType.MANAGE)
    @ApiOperation("用户信息")
    @GetMapping("/info")
    public ResponseResult<?> getInfo() {
        return ResponseResult.success(systemUserService.getInfo());
    }

    @Log(title = "用户退出登录", businessType = BusinessType.OTHER)
    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public ResponseResult<?> logout() {
        return ResponseResult.success();
    }

    @Log(title = "分页查询用户", businessType = BusinessType.SELECT, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "分页查询用户")
    @GetMapping("/querySystemUserPage")
    @PreAuthorize("@ex.hasAuthority('btn.sysUser.list')")
    public ResponseResult<PageHelper<QueryUserPageVo>> queryPage(UserInfoPageParam userInfoPageParam) {
        return ResponseResult.success(systemUserService.queryPage(userInfoPageParam));
    }

    @Log(title = "用户详情", businessType = BusinessType.READ, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult<?> queryOneSystemUser(Integer id) {
        SystemUser systemUser = systemUserService.getById(id);
        Assert.notNull(systemUser, "该用户不存在");
        return ResponseResult.success(systemUser);
    }

    @Log(title = "新增用户", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "新增数据")
    @PreAuthorize("@ex.hasAuthority('btn.sysUser.insert')")
    @PostMapping("/insertSystemUser")
    public ResponseResult<?> insert(@Validated @RequestBody CreateUserDto createUserDto) {
        systemUserService.insertSystemUser(createUserDto);
        return ResponseResult.success();
    }

    @Log(title = "修改用户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "修改数据")
    @PostMapping("/updateSystemUser")
    @PreAuthorize("@ex.hasAuthority('btn.sysUser.update')")
    public ResponseResult<?> update(@Validated @RequestBody UpdateUserDto updateUserDto) {
        systemUserService.updateSystemUser(updateUserDto);
        return ResponseResult.success();
    }

    @Log(title = "删除用户", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "删除数据")
    @DeleteMapping("delete")
    @PreAuthorize("@ex.hasAuthority('btn.sysUser.delete')")
    public ResponseResult<?> delete(Long id) {
        systemUserService.deleteSystemUser(id);
        return ResponseResult.success();
    }

    @Log(title = "批量删除用户", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/batchRemove")
    public ResponseResult<?> batchRemove(@RequestBody List<Long> idList) {
        systemUserService.removeByIds(idList);
        return ResponseResult.success();
    }
}

