package com.cyq.car.pojo;

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
@Entity
@Table(name="tb_car_recommend")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Carrecommend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String recommendId;//ID

    @ApiModelProperty(notes = "价位")
    private String price;

    @ApiModelProperty(notes = "类型")
    private String carType;

    @ApiModelProperty(notes = "系列")
    private String cagetoryName;

    @ApiModelProperty(notes = "推荐车")
    private String cars;
}
