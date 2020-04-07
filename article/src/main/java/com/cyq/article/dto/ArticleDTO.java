package com.cyq.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/24 14:25
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ArticleDTO", description = "文章参数对象")
public class ArticleDTO {

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "分类Id")
    private String categoryId;

    @ApiModelProperty(value = "分类名")
    private String categoryName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "过滤内容")
    private String filterContent;

    @ApiModelProperty(value = "文章状态")
    private String articleState;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "条数")
    private Integer size = 10;
}
