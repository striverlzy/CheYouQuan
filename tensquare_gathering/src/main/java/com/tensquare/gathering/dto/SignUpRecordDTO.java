package com.tensquare.gathering.dto;

/**
 * @Author：liuzhongyu
 * @Date: 2020/3/28 15:53
 * @Description:
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SignUpRecordDTO", description = "报名参数对象")
public class SignUpRecordDTO {

    @Id
    private String signRecordId;

    @ApiModelProperty(notes = "活动id")
    private String gatheringId;

    @ApiModelProperty(notes = "用户id")
    private String userId;

    @ApiModelProperty(notes = "活动名称")
    private String gatheringTitle;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "条数")
    private Integer size = 10;

}
