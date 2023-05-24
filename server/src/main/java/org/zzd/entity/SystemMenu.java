package org.zzd.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author :zzd
 * @apiNote :菜单表(SystemMenu)实体类
 * @date :2023-05-05 08:48:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_system_menu")
public class SystemMenu implements Serializable {
    @TableId
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "所属上级")
    private Long parentId;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "类型(0:目录,1:菜单,2:按钮)")
    private Integer type;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "授权路径")
    private String url;

    @ApiModelProperty(value = "排序")
    private Integer sortValue;

    @ApiModelProperty(value = "状态(0:禁止,1:正常)")
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除标记（0:可用 1:已删除）")
    private Integer isDeleted;

    // 下级列表
    @TableField(exist = false)
    private List<SystemMenu> children;
    //是否选中
    @TableField(exist = false)
    private boolean isSelect;

}

