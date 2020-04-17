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
 * @Date: 2020/4/17 22:30
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CarrecommendDTO", description = "推荐参数对象")
public class CarrecommendDTO {

    @ApiModelProperty(notes = "价位")
    private String price;

    @ApiModelProperty(notes = "类型")
    private String carType;

    @ApiModelProperty(notes = "系列")
    private String cagetoryName;

    @ApiModelProperty(notes = "推荐车")
    private String cars;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "条数")
    private Integer size = 10;
}
