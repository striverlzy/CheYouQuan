package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：liuzhongyu
 * @Date: 2019/12/14 09:46
 * @Description:
 */
@Api(tags = "ArticleSearchController",description = "搜索模块")
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    @ApiOperation(value = "保存",notes = "保存")
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article) {
        articleSearchService.save(article);
        return new Result(true, StatusCode.OK, "操作成功");
    }

    @ApiOperation(value = "查询",notes = "查询")
    @RequestMapping(value = "/search/{keywords}/{page}/{size}", method = RequestMethod.GET)
    public Result findByTitleLike(@PathVariable String keywords, @PathVariable int page, @PathVariable int size) {
        Page<Article> articlePage = articleSearchService.findByTitleLike(keywords, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Article>(articlePage.getTotalElements(), articlePage.getContent()));
    }
}
