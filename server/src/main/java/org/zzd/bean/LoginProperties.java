package org.zzd.bean;

import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Objects;

/**
 * @apiNote 配置文件读取
 * @author zzd
 * @date 2023/3/31 14:29
 */
@Data
@Component
public class LoginProperties {
    private LoginCode loginCode;

    /**
     * @apiNote 获取默认验证码
     * @return {@link Captcha }
     */
    public Captcha getCaptcha() {
        if (Objects.isNull(loginCode)) {
            loginCode = new LoginCode();
            if (Objects.isNull(loginCode.getCodeType())) {
                loginCode.setCodeType(LoginCodeEnum.ARITHMETIC);
            }
        }
        return switchCaptcha(loginCode);
    }

    /**
     * @apiNote 获取默认验证码
     * @return {@link Captcha }
     */
    public Captcha getCaptcha(String type) {
        if (Objects.isNull(loginCode)) {
            loginCode = new LoginCode();
            if (Objects.isNull(loginCode.getCodeType())) {
                loginCode.setCodeType(LoginCodeEnum.ARITHMETIC);
            }
        }
        return switchCaptcha(loginCode);
    }

    /**
     * @param loginCode: 验证码配置
     * @return com.wf.captcha.base.Captcha
     * @apiNote 依据配置信息生产验证码
     */
    private Captcha switchCaptcha(LoginCode loginCode) {
        Captcha captcha = null;
        synchronized (this) {
            switch (loginCode.getCodeType()) {
                case ARITHMETIC:
                    captcha = new FixedArithmeticCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case CHINESE:
                    captcha = new ChineseCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case CHINESE_GIF:
                    captcha = new ChineseGifCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case GIF:
                    captcha = new GifCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case SPEC:
                    captcha = new SpecCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                default:
                    System.out.println("验证码配置信息错误！正确配置查看 LoginCodeEnum ");
            }
        }
        if (StringUtils.isNotBlank(loginCode.getFontName())) {
            captcha.setFont(new Font(loginCode.getFontName(), Font.PLAIN, loginCode.getFontSize()));
        }
        return captcha;
    }

    static class FixedArithmeticCaptcha extends ArithmeticCaptcha {
        public FixedArithmeticCaptcha(int width, int height) {
            super(width, height);
        }

        @Override
        protected char[] alphas() {
            // 生成随机数字和运算符
            int n1 = num(1, 10), n2 = num(1, 10);
            int opt = num(3);

            // 计算结果
            int res = new int[]{n1 + n2, n1 - n2, n1 * n2}[opt];
            // 转换为字符运算符
            char optChar = "+-x".charAt(opt);

            this.setArithmeticString(String.format("%s%c%s=?", n1, optChar, n2));
            this.chars = String.valueOf(res);

            return chars.toCharArray();
        }
    }
}
