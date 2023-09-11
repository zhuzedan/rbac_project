package org.zzd.service;

import org.zzd.bean.LoginCodeEnum;

import java.util.Map;

/**
 * @apiNote 验证码服务
 * @author zzd
 * @date 2023-08-15 14:51  
 */
public interface EasyCaptchaService {

    /**
     * @apiNote 获取指定类型的验证码结果以及Base64编码值
     * @param codeType 代码类型
     * @return {@link Map }<{@link String }, {@link String }>
     */
    Map<String, String> getCaptchaValueAndBase64(LoginCodeEnum codeType);

}
