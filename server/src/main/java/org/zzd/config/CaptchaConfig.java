package org.zzd.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(value = "classpath:data.yml", factory = YamlPropertySourceFactory.class)
public class CaptchaConfig {
    @Value("${captcha.code-type}")
    private String codeType;

}
