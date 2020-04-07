package com.cyq.question.controller;

import com.cyq.question.dto.QuestionDTO;
import com.cyq.question.dto.ReplyDto;
import com.cyq.question.pojo.Question;
import com.cyq.question.pojo.Reply;
import com.cyq.question.service.QuestionService;
import com.cyq.question.service.ReplyService;
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

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/3 09:19
 * @Description:
 */
@Api(tags = "ReplyController", description = "回答操作")
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;


    @Autowired
    private QuestionService questionService;


    /**
     * 回答问题
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "回答问题", notes = "回答问题")
    @PostMapping(value = "/reply/question")
    public Result reply(@RequestBody ReplyDto param) {
        replyService.reply(param);
        questionService.updateState(param.getQuestionId());
        return new Result(true, StatusCode.OK, "回答成功");
    }


    /**
     * 回答分页+多条件查询
     *
     * @param param 查询条件封装
     * @return 分页结果
     */
    @ApiOperation(value = "回答分页+多条件查询", notes = "回答分页+多条件查询")
    @PostMapping(value = "/reply/search")
    public Result findSearch(@RequestBody ReplyDto param) {
        if (param.getPage() == null) {
            param.setPage(new Integer(1));
        }
        if (param.getSize() == null) {
            param.setSize(new Integer(10));
        }
        Page<Reply> pageList = replyService.findSearch(param);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Reply>(pageList.getTotalElements(), pageList.getContent()));
    }


    /**
     * 删除
     *
     * @param replyId
     * @return
     */
    @ApiOperation(value = "删除", notes = "删除回答")
    @GetMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "replyId", value = "回答Id", paramType = "query")
    })
    public Result delete(@RequestParam String replyId) {
        replyService.deleteById(replyId);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    /**
     * 点赞
     *
     * @param replyId
     * @return
     */
    @ApiOperation(value = "点赞", notes = "点赞问题")
    @GetMapping(value = "/thumbup")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "replyId", value = "问题Id", paramType = "query")
    })
    public Result updateThumbup(@RequestParam String replyId) {
        return new Result(true, StatusCode.OK, "点赞成功", replyService.updateThumbup(replyId));
    }

    /**
     * 取消点赞
     *
     * @param replyId
     * @return
     */
    @ApiOperation(value = "取消点赞", notes = "取消点赞回答")
    @GetMapping(value = "/unthumbup")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "replyId", value = "回答Id", paramType = "query")
    })
    public Result updateNotThumbup(@RequestParam String replyId) {
        return new Result(true, StatusCode.OK, "取消点赞成功", replyService.updateNotThumbup(replyId));
    }
}
