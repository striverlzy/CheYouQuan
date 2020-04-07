package com.tensquare.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.convert.Convert;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/22 15:08
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SearchListDTO", description = "列表参数对象")
public class SearchListDTO extends Convert {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Id")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "页")
    private Integer page;

    @ApiModelProperty(value = "条")
    private Integer size;
}
