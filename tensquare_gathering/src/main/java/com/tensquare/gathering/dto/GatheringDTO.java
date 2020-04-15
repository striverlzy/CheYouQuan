package com.tensquare.gathering.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Author：liuzhongyu
 * @Date: 2020/3/25 17:38
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GatheringDTO", description = "活动参数对象")
public class GatheringDTO {

    @ApiModelProperty(notes = "活动id")
    private String gatheringId;

    @ApiModelProperty(notes = "活动标题")
    private String title;

    @ApiModelProperty(notes = "活动介绍")
    private String introduction;

    @ApiModelProperty(notes = "活动详情")
    private String detail;

    @ApiModelProperty(notes = "图片")
    private String gatheringImage;

    @ApiModelProperty(notes = "活动官网")
    private String internetUrl;

    @ApiModelProperty(notes = "开始时间")
    private String startDate;

    @ApiModelProperty(notes = "结束时间")
    private String endDate;

    @ApiModelProperty(notes = "举办地点")
    private String address;

    @ApiModelProperty(notes = "主办方")
    private String sponsor;

    @ApiModelProperty(notes = "报名截止日")
    private String signEnd;

    @ApiModelProperty(notes = "报名人数")
    private Integer signNum;

    @ApiModelProperty(notes = "报名人")
    private String signIds;

    @ApiModelProperty(notes = "活动状态，0：最新活动，1：最热活动")
    private String isHost;

    @ApiModelProperty(notes = "活动状态：0：未结束活动，1：已结束活动")
    private String state;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "条数")
    private Integer size = 10;
}
