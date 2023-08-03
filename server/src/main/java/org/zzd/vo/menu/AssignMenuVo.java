package org.zzd.vo.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.zzd.utils.TreeEntity;

import java.util.List;

/**
 * @apiNote 根据角色获取菜单
 * @author zzd
 * @date 2023-08-03 14:30  
 */
@Data
public class AssignMenuVo implements TreeEntity<AssignMenuVo> {
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "所属上级")
    private Long parentId;

    @ApiModelProperty(value = "下级列表")
    private List<AssignMenuVo> childList;

    @ApiModelProperty(value = "是否选中")
    private boolean isSelect;

}
