package org.zzd.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zzd.param.BasePageParam;

/**
 * @apiNote 查询用户入参
 * @author zzd
 * @date 2023/4/28 17:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户分页对象")
public class UserInfoPageParam extends BasePageParam {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "是否是移动端用户")
    private String userType;

}
