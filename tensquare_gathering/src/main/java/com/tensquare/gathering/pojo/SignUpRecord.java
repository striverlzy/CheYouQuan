package com.tensquare.gathering.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @Author：liuzhongyu
 * @Date: 2020/3/28 15:41
 * @Description:
 */
@Entity
@Table(name="sign_record")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SignUpRecord {
    private static final long serialVersionUID = 1L;

    @Id
    private String signRecordId;

    @ApiModelProperty(notes = "活动id")
    private String gatheringId;

    @ApiModelProperty(notes = "用户id")
    private String userId;

    @ApiModelProperty(notes = "活动名称")
    private String gatheringTitle;

    @ApiModelProperty(notes = "报名日期")
    private LocalDate createDate;

}
