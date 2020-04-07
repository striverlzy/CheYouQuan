package com.cyq.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author：liuzhongyu
 * @Date: 2020/3/23 23:37
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CategoryDTO", description = "标签列表参数对象")
public class CategoryDTO {

    @ApiModelProperty(value = "分类名")
    private String categoryName;

    @ApiModelProperty(value = "分类Id")
    private String categoryId;

    @ApiModelProperty(value = "状态")
    private String categoryState;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "条数")
    private Integer size = 10;
}
