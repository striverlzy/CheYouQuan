package com.cyq.article.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Author：liuzhongyu
 * @Date: 2020/3/28 16:45
 * @Description:
 */
@Entity
@Table(name="collection_record")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CollectionRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String collectionRecordId;

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

    @ApiModelProperty(notes = "收藏日期")
    private LocalDate createDate;

}
