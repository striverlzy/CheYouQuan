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


    @ApiModelProperty(notes = "发动机")
    private String cardEngine;


}
