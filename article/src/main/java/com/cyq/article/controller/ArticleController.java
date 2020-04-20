package com.cyq.article.controller;

import com.cyq.article.dto.ArticleDTO;
import com.cyq.article.dto.CollectionRecordDTO;
import com.cyq.article.dto.CommentDTO;
import com.cyq.article.pojo.Article;
import com.cyq.article.pojo.Category;
import com.cyq.article.pojo.CollectionRecord;
import com.cyq.article.service.ArticleService;
import com.cyq.article.service.CategoryService;
import com.cyq.article.service.CollectionService;
import com.cyq.article.service.CommentService;
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

import java.util.*;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/24 14:06
 * @Description:
 */
@Api(tags = "ArticleController", description = "文章操作")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 发布文章
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "发布文章", notes = "发布文章")
    @PostMapping("/add/article")
    public Result addArticle(@RequestBody ArticleDTO param) {
        param.setContent(HtmlUtils.htmlEscape(param.getContent()));
        articleService.addArticle(param);
        return new Result(true, StatusCode.OK, "添加成功");
    }


    /**
     * 点赞
     *
     * @param articleId
     * @return
     */
    @ApiOperation(value = "点赞", notes = "点赞文章")
    @GetMapping(value = "/thumbup/article")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章Id", paramType = "query")
    })
    public Result updateThumbup(@RequestParam String articleId) {
        articleService.updateThumbup(articleId);
        articleService.isThumbup(articleId);
        return new Result(true, StatusCode.OK, "点赞成功");
    }

    /**
     * 取消点赞
     *
     * @param articleId
     * @return
     */
    @ApiOperation(value = "取消点赞", notes = "取消点赞文章")
    @GetMapping(value = "/unthumbup/article")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章Id", paramType = "query")
    })
    public Result updateNotThumbup(@RequestParam String articleId) {
        articleService.updateNotThumbup(articleId);
        articleService.notThumbup(articleId);
        return new Result(true, StatusCode.OK, "取消点赞成功");
    }


    /**
     * 评论点赞
     *
     * @param commentId
     * @return
     */
    @ApiOperation(value = "评论点赞", notes = "点赞评论")
    @GetMapping(value = "/thumbup/comment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId", value = "评论Id", paramType = "query")
    })
    public Result updateCommentThumbup(@RequestParam String commentId) {
        return new Result(true, StatusCode.OK, "点赞成功", commentService.updateThumbup(commentId));
    }

    /**
     * 取消评论点赞
     *
     * @param commentId
     * @return
     */
    @ApiOperation(value = "取消评论点赞", notes = "取消评论点赞")
    @GetMapping(value = "/unthumbup/comment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId", value = "评论Id", paramType = "query")
    })
    public Result updateCommentNotThumbup(@RequestParam String commentId) {
        return new Result(true, StatusCode.OK, "取消点赞成功", commentService.updateNotThumbup(commentId));
    }


    /**
     * 审核
     *
     * @param articleId
     * @return
     */
    @ApiOperation(value = "审核通过", notes = "审核通过")
    @GetMapping(value = "/check")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章Id", paramType = "query")
    })
    public Result checkState(@RequestParam String articleId) {
        return new Result(true, StatusCode.OK, "审核成功", articleService.checkState(articleId));
    }

    /**
     * 审核不通过
     *
     * @param articleId
     * @return
     */
    @ApiOperation(value = "审核不通过", notes = "审核不通过")
    @GetMapping(value = "/unCheck")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章Id", paramType = "query")
    })
    public Result unCheckState(@RequestParam String articleId) {
        return new Result(true, StatusCode.OK, "审核成功", articleService.unCheckState(articleId));
    }


    /**
     * 删除
     *
     * @param articleId
     * @return
     */
    @ApiOperation(value = "删除", notes = "删除文章")
    @GetMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章Id", paramType = "query")
    })
    public Result delete(@RequestParam String articleId) {
        articleService.deleteById(articleId);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    /**
     * 文章分页+多条件查询
     *
     * @param param 查询条件封装
     * @return 分页结果
     */
    @ApiOperation(value = "文章分页+多条件查询", notes = "文章分页+多条件查询")
    @PostMapping(value = "/article/search")
    public Result findSearch(@RequestBody ArticleDTO param) {
        if (param.getPage() == null) {
            param.setPage(new Integer(1));
        }
        if (param.getSize() == null) {
            param.setSize(new Integer(10));
        }
        Page<Article> pageList = articleService.findSearch(param);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据文章id查询
     *
     * @param articleId
     * @return
     */
    @ApiOperation(value = "根据文章id查询", notes = "根据文章id查询")
    @GetMapping(value = "/findByArticleId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章Id", paramType = "query")
    })
    public Result findByArticleId(@RequestParam String articleId) {
        String content = HtmlUtils.htmlUnescape(articleService.findByArticleId(articleId).getContent());
        articleService.findByArticleId(articleId).setContent(content);
        return new Result(true, StatusCode.OK, "根据文章id查询成功", articleService.findByArticleId(articleId));
    }

    /**
     * 根据文章id查询评论
     *
     * @param articleId
     * @return
     */
    @ApiOperation(value = "根据文章id查询评论", notes = "根据文章id查询评论")
    @GetMapping(value = "/findCommentByArticleId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章Id", paramType = "query")
    })
    public Result findCommentByArticleId(@RequestParam String articleId) {
        return new Result(true, StatusCode.OK, "根据文章id查询评论", commentService.findByArticleId(articleId));
    }



    /**
     * 评论
     *
     * @param commentDTO
     * @return
     */
    @ApiOperation(value = "评论", notes = "评论文章")
    @PostMapping(value = "/comment")
    public Result commentArticle(@RequestBody CommentDTO commentDTO) {
        commentService.addByArticleId(commentDTO);
        return new Result(true, StatusCode.OK, "评论成功");
    }

    /**
     * 收藏
     *
     * @param collectionRecordDTO
     * @return
     */
    @ApiOperation(value = "收藏", notes = "收藏")
    @PostMapping(value = "/collection")
    public Result collection(@RequestBody CollectionRecordDTO collectionRecordDTO) {
        collectionService.addCollectionRecord(collectionRecordDTO);
        collectionService.collection(collectionRecordDTO.getArticleId());
        collectionService.updateCollection(collectionRecordDTO.getArticleId());
        return new Result(true, StatusCode.OK, "收藏成功");
    }

    /**
     * 查看收藏
     *
     * @param collectionRecordDTO
     * @return
     */
    @ApiOperation(value = "查看收藏", notes = "查看收藏")
    @PostMapping(value = "/search/collection")
    public Result searchCollection(@RequestBody CollectionRecordDTO collectionRecordDTO) {
        Page<CollectionRecord> pageList = collectionService.findRecordByUserId(collectionRecordDTO);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<CollectionRecord>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 取消收藏
     *
     * @param articleId
     * @return
     */
    @ApiOperation(value = "取消收藏", notes = "取消收藏")
    @GetMapping(value = "/unCollection")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章Id", paramType = "query")
    })
    public Result unCollection(@RequestParam String articleId) {
        collectionService.unCollection(articleId);
        collectionService.deleteCollection(articleId);
        collectionService.updateNotCollection(articleId);
        return new Result(true, StatusCode.OK, "取消收藏成功");
    }


    /**
     * 统计分析
     *
     * @return
     */
    @ApiOperation(value = "统计分析", notes = "统计分析")
    @GetMapping(value = "/count/article")
    public Result countArticle() {
        int countArticle = articleService.countArticle();
        int countThumbup = articleService.countThumbup();
        int countComment = commentService.countComment();
        int countCollection = collectionService.countCollection();
        Map mapArticle = new HashMap();
        Map mapThumbup = new HashMap();
        Map mapComment = new HashMap();
        Map mapCollection = new HashMap();
        mapArticle.put("value",countArticle);
        mapArticle.put("name","文章数");
        mapThumbup.put("value",countThumbup);
        mapThumbup.put("name","点赞数");
        mapComment.put("value",countComment);
        mapComment.put("name","评论数");
        mapCollection.put("value",countCollection);
        mapCollection.put("name","收藏数");
        List countName = new ArrayList();
        List countList = new ArrayList();
        List perList = new ArrayList();
        perList.add(countArticle);
        perList.add(countThumbup);
        perList.add(countComment);
        perList.add(countCollection);
        countName.add("文章数");
        countName.add("评论数");
        countName.add("点赞数");
        countName.add("收藏数");
        countList.add(mapArticle);
        countList.add(mapThumbup);
        countList.add(mapComment);
        countList.add(mapCollection);
        List<Category> categoryList = categoryService.findCategory();
        List categoryCount = new ArrayList();
        List categoryNameList = new ArrayList();
        for (int i=0;i<categoryList.size();i++){
            categoryNameList.add(categoryList.get(i).getCategoryName());
            categoryCount.add(articleService.countByCategory(categoryList.get(i).getCategoryId()));
        }
        Map map = new HashMap();
        map.put("countName",countName);
        map.put("countList",countList);
        map.put("perList",perList);
        map.put("categoryName",categoryNameList);
        map.put("categoryCount",categoryCount);
        return new Result(true, StatusCode.OK, "统计分析查询成功",map);
    }

    /**
     * 分类查询
     */
    @ApiOperation(value = "分类查询", notes = "分类查询")
    @GetMapping(value = "/getArticleByCategoryId")
    public Result getArticleByCategoryId(@RequestParam String categoryId) {
        List<Article> list = articleService.findAllByCategoryId(categoryId);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContent(HtmlUtils.htmlUnescape(list.get(i).getContent()));
        }
        return new Result(true, StatusCode.OK, "分类查询成功", list);
    }
}
