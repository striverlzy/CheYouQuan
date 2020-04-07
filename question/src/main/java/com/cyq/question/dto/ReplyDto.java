package com.cyq.question.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/3 09:41
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ReplyDto", description = "回答参数对象")
public class ReplyDto {

    @ApiModelProperty(notes = "用户Id")
    private String userId;

    @ApiModelProperty(notes = "文章Id")
    private String questionId;

    @ApiModelProperty(notes = "用户名")
    private String userName;

    @ApiModelProperty(notes = "用户头像")
    private String userImage;

    @ApiModelProperty(notes = "评论内容")
    private String content;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "条数")
    private Integer size = 10;
}
