package org.zzd.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * @author :zzd
 * @apiNote :创建用户
 * @date : 2023-03-31 9:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "性别;1=男,2=女,3=未知")
    private String gender;

    @ApiModelProperty(value = "生日(yyyy-MM-dd)")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "是否是移动端用户")
    private Integer userType;

    @ApiModelProperty(value = "角色id")
    @NotEmpty(message = "角色不能为空")
    private List<Long> roleIds;

}
