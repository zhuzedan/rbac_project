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
/**
 * (WechatUser)表实体类
 *
 * @author zzd
 * @since 2023-03-30 09:12:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_wechat_user")
public class WechatUser implements Serializable {
    @TableId
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "微信昵称")
    private String nickname;

    @ApiModelProperty(value = "微信openid")
    private String openId;

    @ApiModelProperty(value = "微信sessionKey")
    private String sessionKey;

    @ApiModelProperty(value = "生成自定义登录状态")
    private String skey;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除标识")
    private Integer isDeleted;

}

