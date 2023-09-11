package org.zzd.service.impl;

import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzd.bean.LoginCodeEnum;
import org.zzd.bean.LoginProperties;
import org.zzd.service.EasyCaptchaService;
import org.zzd.utils.CacheUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @apiNote 验证码Service实现类
 * @author zzd
 * @date 2023-09-11 9:56  
 */
@Service
@Slf4j
public class EasyCaptchaServiceImpl implements EasyCaptchaService {
    @Resource
    private LoginProperties easyCaptchaProducer;

    @Autowired
    private CacheUtils cacheUtils;

    @Override
    public Map<String, String> getCaptchaValueAndBase64(LoginCodeEnum codeType) {
        Captcha captcha = easyCaptchaProducer.getCaptcha();
        //获取到结果值
        String captchaValue = captcha.text();
        //对于数学类型的需要进行处理
        if (codeType == null || codeType == LoginCodeEnum.ARITHMETIC) {
            if (captcha.getCharType() - 1 == LoginCodeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")) {
                captchaValue = captchaValue.split("\\.")[0];
            }
        }
        //获取到Base64编码
        String captchaBase64 = captcha.toBase64();
        Map<String, String> result = new HashMap<>(2);
        result.put("code", captchaValue);
        result.put("base64", captchaBase64);
        log.info("图片验证码结果是" + captchaValue);
        cacheUtils.put("captcha", captchaValue);
        return result;
    }
}
