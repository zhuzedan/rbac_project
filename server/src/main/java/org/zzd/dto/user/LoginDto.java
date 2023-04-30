package org.zzd.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @apiNote :登录dto
 * @author :zzd
 * @date : 2023-03-02 11:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "登录对象")
public class LoginDto implements Serializable {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
