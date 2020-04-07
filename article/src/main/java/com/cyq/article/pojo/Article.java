package com.cyq.article.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/23 08:28
 * @Description:
 */
@Entity
@Table(name="tb_article")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String articleId;//ID

    @ApiModelProperty(notes = "用户Id")
    private String userId;

    @ApiModelProperty(notes = "分类Id")
    private String categoryId;
    
    @ApiModelProperty(notes = "分类名")
    private String categoryName;

    @ApiModelProperty(notes = "文章标题")
    private String title;

    @ApiModelProperty(notes = "内容详情")
    private String content;

    @ApiModelProperty(notes = "过滤内容详情")
    private String filterContent;

    @ApiModelProperty(notes = "图片")
    private String articleImage;

    @ApiModelProperty(notes = "评论数")
    private int commentTotal;

    @ApiModelProperty(notes = "收藏数")
    private int collectionTotal;

    @ApiModelProperty(notes = "点赞数")
    private int thumbup;

    @ApiModelProperty(notes = "创建时间")
    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    private LocalDateTime createDate;

    @ApiModelProperty(notes = "文章状态，是否显示（0：否，1：是）")
    private String articleState;

}
