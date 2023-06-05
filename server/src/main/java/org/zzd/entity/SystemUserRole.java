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
 * @apiNote 用户角色(SystemUserRole)实体类
 * @author zzd
 * @date 2023-06-05 20:12:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_system_user_role")
public class SystemUserRole implements Serializable {
    @TableId
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "角色id")
    private Long roleId;
    
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
    @ApiModelProperty(value = "删除标记（0:可用 1:已删除）")
    private Integer isDeleted;
    
}
