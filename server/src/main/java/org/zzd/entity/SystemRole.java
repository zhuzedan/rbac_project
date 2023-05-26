package org.zzd.entity;

import java.util.Date;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @apiNote 角色(SystemRole)实体类
 * @author zzd
 * @date 2023-05-26 14:22:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_system_role")
public class SystemRole implements Serializable {
    @TableId
    @ApiModelProperty(value = "角色id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;
    
    @ApiModelProperty(value = "角色编码")
    private String roleCode;
    
    @ApiModelProperty(value = "描述")
    private String description;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
    @ApiModelProperty(value = "删除标记（0:可用 1:已删除）")
    private Integer isDeleted;
    
}
