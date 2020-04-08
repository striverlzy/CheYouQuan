package com.cyq.question.controller;

import com.cyq.question.dto.QuestionDTO;
import com.cyq.question.pojo.Question;
import com.cyq.question.service.QuestionService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/3 09:19
 * @Description:
 */
@Api(tags = "QuestionController", description = "问题操作")
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 问题分页+多条件查询
     *
     * @param param 查询条件封装
     * @return 分页结果
     */
    @ApiOperation(value = "问题分页+多条件查询", notes = "问题分页+多条件查询")
    @PostMapping(value = "/question/search")
    public Result findSearch(@RequestBody QuestionDTO param) {
        if (param.getPage() == null) {
            param.setPage(new Integer(1));
        }
        if (param.getSize() == null) {
            param.setSize(new Integer(10));
        }
        Page<Question> pageList = questionService.findSearch(param);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Question>(pageList.getTotalElements(), pageList.getContent()));
    }



    /**
     * 删除成功
     *
     * @param questionId
     * @return
     */
    @ApiOperation(value = "删除", notes = "删除问题")
    @GetMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionId", value = "问题Id", paramType = "query")
    })
    public Result delete(@RequestParam String questionId) {
        questionService.deleteById(questionId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 发布问答
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "发布问答", notes = "发布问答")
    @PostMapping("/publish")
    public Result addArticle(@RequestBody QuestionDTO param) {
        questionService.publishQusertion(param);
        return new Result(true, StatusCode.OK, "发布成功");
    }


    /**
     * 点赞
     *
     * @param questionId
     * @return
     */
    @ApiOperation(value = "点赞", notes = "点赞问题")
    @GetMapping(value = "/thumbup")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionId", value = "问题Id", paramType = "query")
    })
    public Result updateThumbup(@RequestParam String questionId) {
        return new Result(true, StatusCode.OK, "点赞成功", questionService.updateThumbup(questionId));
    }
    /**
     * 取消点赞
     *
     * @param questionId
     * @return
     */
    @ApiOperation(value = "取消点赞", notes = "取消点赞问题")
    @GetMapping(value = "/unthumbup")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionId", value = "问题Id", paramType = "query")
    })
    public Result updateNotThumbup(@RequestParam String questionId) {
        return new Result(true, StatusCode.OK, "取消点赞成功", questionService.updateNotThumbup(questionId));
    }


}
