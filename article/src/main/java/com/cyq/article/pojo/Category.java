package com.cyq.article.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/23 08:29
 * @Description:
 */
@Entity
@Table(name="tb_category")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String categoryId;

    @ApiModelProperty(notes = "分类名")
    private String categoryName;

    @ApiModelProperty(notes = "分类状态，0为禁用，1为显示")
    private String categoryState;

}
