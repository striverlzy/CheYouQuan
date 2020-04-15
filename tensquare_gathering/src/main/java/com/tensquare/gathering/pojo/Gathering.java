package com.tensquare.gathering.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_gathering")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Gathering implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
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
	private LocalDate startDate;

	@ApiModelProperty(notes = "结束时间")
	private LocalDate endDate;

	@ApiModelProperty(notes = "举办地点")
	private String address;

	@ApiModelProperty(notes = "主办方")
	private String sponsor;

	@ApiModelProperty(notes = "报名截止日")
	private LocalDate signEnd;

	@ApiModelProperty(notes = "报名人数")
	private int signNum;

	@ApiModelProperty(notes = "报名人")
	private String signIds;

	@ApiModelProperty(notes = "活动状态：0未结束活动，1已结束活动")
	private String state;

	@ApiModelProperty(notes = "创建时间")
	private LocalDateTime createDate;
	
}
