package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author：liuzhongyu
 * @Date: 2019/12/9 22:34
 * @Description: 标签控制层
 */
@Api(tags = "LabelController",description = "标签操作")
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 查询全部列表
     *
     * @return
     */
    @ApiOperation(value = "查询全部列表",notes = "查询全部列表")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    @ApiOperation(value = "根据Id查询",notes = "根据条件查询")
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    @ApiOperation(value = "增加",notes = "增加")
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @ApiOperation(value = "修改",notes = "修改")
    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String labelId) {
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @ApiOperation(value = "删除",notes = "删除")
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @ApiOperation(value = "根据条件查询",notes = "根据条件查询")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label) {
        List<Label> list = labelService.findSearch(label);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 条件+分页查询
     *
     * @param label
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "条件+分页查询",notes = "条件+分页查询")
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> pageData = labelService.pageQuery(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }
}
