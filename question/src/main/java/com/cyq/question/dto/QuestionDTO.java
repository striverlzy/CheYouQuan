package com.cyq.question.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/2 17:54
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "QuestionDTO", description = "文章参数对象")
public class QuestionDTO {


    @ApiModelProperty(notes = "用户Id")
    private String userId;

    @ApiModelProperty(notes = "用户名")
    private String userName;

    @ApiModelProperty(notes = "用户头像")
    private String userImage;

    @ApiModelProperty(notes = "分类Id")
    private String categoryId;

    @ApiModelProperty(notes = "分类名")
    private String categoryName;

    @ApiModelProperty(notes = "标题")
    private String title;

    @ApiModelProperty(notes = "内容详情")
    private String content;

    @ApiModelProperty(notes = "查询状态：1:'最新问答'，2:'最热问答'，3:'等待回答'")
    private String searchState;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "条数")
    private Integer size = 10;
}
