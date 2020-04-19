package com.cyq.car.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/17 22:24
 * @Description:
 */
@Entity
@Table(name="tb_car_work")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Carwork implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String wordId;//ID

    @ApiModelProperty(notes = "身份证号")
    private String idCard;

    @ApiModelProperty(notes = "车牌")
    private String cardNumber;

    @ApiModelProperty(notes = "车型")
    private String cardType;

    @ApiModelProperty(notes = "发动机")
    private String cardEngine;

    @ApiModelProperty(notes = "违章时间")
    @JsonFormat(pattern="yyyy年MM月dd日",timezone="GMT+8")
    private LocalDateTime rulesDate;

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



}
