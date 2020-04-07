package com.cyq.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/24 17:05
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CommentDTO", description = "文章评论参数对象")
public class CommentDTO {

    @ApiModelProperty(value = "文章Id")
    private String articleId;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(notes = "用户头像")
    private String userImage;

    @ApiModelProperty(notes = "用户名称")
    private String userName;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "条数")
    private Integer size = 10;

}
