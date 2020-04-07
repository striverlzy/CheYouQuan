package com.tensquare.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.convert.Convert;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/22 14:48
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LoginDTO", description = "登录参数对象")
public class LoginDTO extends Convert {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名/手机号")
    private String text;

    @ApiModelProperty(value = "密码")
    private String password;
}
