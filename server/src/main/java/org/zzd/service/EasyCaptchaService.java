package org.zzd.service;

import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzd.bean.LoginCodeEnum;
import org.zzd.bean.LoginProperties;
import org.zzd.config.CaptchaConfig;
import org.zzd.utils.CacheUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @apiNote 验证码服务
 * @author zzd
 * @date 2023-08-15 14:51  
 */
@Service
@Slf4j
public class EasyCaptchaService {

    @Autowired
    private LoginProperties easyCaptchaProducer;

    @Autowired
    private CacheUtils cacheUtils;

    @Autowired
    private CaptchaConfig captchaConfig;

    /**
     * @apiNote 获取指定类型的验证码结果以及Base64编码值
     * @param codeType 代码类型
     * @return {@link Map }<{@link String }, {@link String }>
     */
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
