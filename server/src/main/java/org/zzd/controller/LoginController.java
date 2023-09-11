package org.zzd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.zzd.annotation.LoginLog;
import org.zzd.dto.user.LoginCaptchaDto;
import org.zzd.dto.user.LoginDto;
import org.zzd.result.ResponseResult;
import org.zzd.service.EasyCaptchaService;
import org.zzd.service.SystemUserService;
import org.zzd.service.WeChatService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @apiNote 登录相关接口
 * @author zzd
 * @date 2023-03-02 13:41
 */
@RestController
@Api(tags = "登录相关接口")
@RequestMapping("/api/auth")
public class LoginController {
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private WeChatService weChatService;
    @Resource
    private EasyCaptchaService easyCaptchaService;

    @LoginLog
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseResult<?> login(@RequestBody LoginDto loginDto) {
        return systemUserService.login(loginDto);
    }

    @LoginLog
    @ApiOperation("用户登录（captcha）")
    @PostMapping("/loginCaptcha")
    public ResponseResult<?> loginAndCaptcha(@RequestBody LoginCaptchaDto loginCaptchaDto, HttpServletRequest request) {
        return systemUserService.loginCaptcha(loginCaptchaDto, request);
    }

    @ApiOperation(value = "获取验证码")
    @GetMapping(value = "/captcha")
    public ResponseResult<?> getCode() {
        return ResponseResult.success(easyCaptchaService.getCaptchaValueAndBase64(null));
    }

    @GetMapping("/wxLogin")
    public ResponseResult<?> wxLogin(String code) {
        return weChatService.wxLogin(code);
    }

}
