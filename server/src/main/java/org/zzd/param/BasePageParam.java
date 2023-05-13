package org.zzd.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @apiNote 分页请求参数
 * @author zzd
 * @date 2023/4/28 17:00
 */
@Data
@ApiModel("分页对象")
public class BasePageParam {
    @NotNull
    @ApiModelProperty(value = "当前页", required = true)
    private Integer pageNum;

    @NotNull
    @ApiModelProperty(value = "页面大小", required = true)
    private Integer pageSize;
}
