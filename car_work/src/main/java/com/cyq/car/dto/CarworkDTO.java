package com.cyq.car.dto;

import io.swagger.annotations.ApiModel;
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
 * @Date: 2020/4/17 22:24
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CarworkDTO", description = "车务参数对象")
public class CarworkDTO {

    @ApiModelProperty(notes = "身份证号")
    private String idCard;

    @ApiModelProperty(notes = "车牌")
    private String cardNumber;

    @ApiModelProperty(notes = "车型")
    private String cardType;

    @ApiModelProperty(notes = "车架号")
    private String cardVin;

    @ApiModelProperty(notes = "发动机")
    private String cardEngine;

    @ApiModelProperty(notes = "违章时间")
    private String rulesDate;

    @ApiModelProperty(notes = "违章地点")
    private String rulesAddress;

    @ApiModelProperty(notes = "违章行为")
    private String rulesDetail;

    @ApiModelProperty(notes = "罚款")
    private String fine;

    @ApiModelProperty(notes = "计分")
    private String points;

    @ApiModelProperty(notes = "0:“未处理”，1：“已处理”")
    private String state;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "条数")
    private Integer size = 10;



}
