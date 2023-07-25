package org.zzd.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @apiNote 查询用户列表vo类
 * @author zzd
 * @date 2023-07-25 9:38  
 */
@Data
public class QueryUserPageVo {
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "性别;1=男,2=女,3=未知")
    private String gender;

    @ApiModelProperty(value = "删除标记（0:可用 1:已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "用户类型后台前台")
    private Integer userType;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "状态1启用0禁用")
    private Integer status;

    @ApiModelProperty(value = "角色列表")
    private List<QueryUserRoleVo> roleList;
}
