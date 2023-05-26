package org.zzd.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @apiNote 登录接口出参
 * @author zzd
 * @date 2023/5/26 8:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRespDto {
    @ApiModelProperty(value = "token头")
    private String tokenHead;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "过期时间")
    private Long expireTime;
}
