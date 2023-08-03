package org.zzd.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @apiNote 分配菜单
 * @author zzd
 * @date 2023-08-03 16:02  
 */
@ApiModel(description = "分配菜单")
@Data
public class DoAssignMenuDto {
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "菜单id列表")
    private List<Long> menuIdList;
}
