package com.cyq.question.pojo;

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
 * @Date: 2020/4/2 17:55
 * @Description:
 */
@Entity
@Table(name="tb_reply")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String replyId;

    @ApiModelProperty(notes = "用户Id")
    private String userId;

    @ApiModelProperty(notes = "文章Id")
    private String questionId;

    @ApiModelProperty(notes = "点赞数")
    private int thumbup;

    @ApiModelProperty(notes = "用户名")
    private String userName;

    @ApiModelProperty(notes = "用户头像")
    private String userImage;

    @ApiModelProperty(notes = "评论内容")
    private String content;

    @ApiModelProperty(notes = "评论时间")
    private LocalDateTime replyDate;

}
