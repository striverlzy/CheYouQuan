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
@ApiModel(value = "CollectionRecordDTO", description = "收藏参数对象")
public class CollectionRecordDTO {

    @ApiModelProperty(notes = "文章id")
    private String articleId;

    @ApiModelProperty(notes = "用户id")
    private String userId;

    @ApiModelProperty(notes = "用户头像")
    private String userImage;

    @ApiModelProperty(notes = "用户名称")
    private String userName;

    @ApiModelProperty(notes = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "条数")
    private Integer size = 10;
}
