package com.cyq.car.controller;

import com.cyq.car.dto.CarrecommendDTO;
import com.cyq.car.dto.CarworkDTO;
import com.cyq.car.pojo.Carrecommend;
import com.cyq.car.pojo.Carwork;
import com.cyq.car.service.CarrecommendService;
import com.cyq.car.service.CarworkService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/17 23:14
 * @Description:
 */
@Api(tags = "CarworkController", description = "车务操作")
@RestController
@RequestMapping("/carwork")
public class CarworkController {

    @Autowired
    private CarrecommendService carrecommendService;

    @Autowired
    private CarworkService carworkService;

    /**
     * 推荐
     *
     * @param param 查询条件封装
     * @return 分页结果
     */
    @ApiOperation(value = "推荐分页+多条件查询", notes = "推荐分页+多条件查询")
    @PostMapping(value = "/carrecommend/search")
    public Result findCarRecommendSearch(@RequestBody CarrecommendDTO param) {

        return new Result(true, StatusCode.OK, "查询成功",carrecommendService.findByCardParam(param));
    }


    /**
     * 推荐
     *
     * @param param 查询条件封装
     * @return 结果
     */
    @ApiOperation(value = "推荐分页+多条件查询", notes = "推荐分页+多条件查询")
    @PostMapping(value = "/carwork/search")
    public Result findCarWorkSearch(@RequestBody CarworkDTO param) {
        return new Result(true, StatusCode.OK, "查询成功", carworkService.findByCardParam(param));
    }

}
