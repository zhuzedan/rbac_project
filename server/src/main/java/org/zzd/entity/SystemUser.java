package org.zzd.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author :zzd
 * @apiNote :用户表(SystemUser)实体类
 * @date :2023-03-31 10:23:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_system_user")
public class SystemUser implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "性别;1=男,2=女,3=未知")
    private String gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除标记（0:可用 1:已删除）")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "用户类型后台前台")
    private Integer userType;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "状态1启用0禁用")
    private Integer status;
}
