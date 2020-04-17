package com.cyq.question.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/2 17:54
 * @Description:
 */
@Entity
@Table(name="tb_question")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Question implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    private String questionId;//ID

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

    @ApiModelProperty(notes = "评论数")
    private int replyTotal;

    @ApiModelProperty(notes = "点赞数")
    private int thumbup;

    @ApiModelProperty(notes = "创建时间")
    @JsonFormat(pattern="yyyy年MM月dd日",timezone="GMT+8")
    private LocalDateTime createDate;

    @ApiModelProperty(notes = "状态：0为等待回答，1为已回答")
    private String state;
}
