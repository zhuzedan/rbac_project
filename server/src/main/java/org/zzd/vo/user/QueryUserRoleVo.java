package org.zzd.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @apiNote 查询用户角色vo
 * @author zzd
 * @date 2023-07-24 17:19  
 */
@Data
public class QueryUserRoleVo {
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "角色名")
    private String roleName;
}
