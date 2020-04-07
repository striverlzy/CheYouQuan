package com.cyq.article.controller;

import com.cyq.article.dto.CategoryDTO;
import com.cyq.article.pojo.Category;
import com.cyq.article.service.CategoryService;
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
 * @Date: 2020/3/24 10:18
 * @Description:
 */
@Api(tags = "CategoryController", description = "标签操作")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 标签分页+多条件查询
     *
     * @param param 查询条件封装
     * @return 分页结果
     */
    @ApiOperation(value = "标签分页+多条件查询", notes = "标签分页+多条件查询")
    @PostMapping(value = "/category/search")
    public Result findCategorySearch(@RequestBody CategoryDTO param) {
        if(param.getPage() == null){
            param.setPage(new Integer(1));
        }
        if(param.getSize() == null){
            param.setSize(new Integer(10));
        }
        Page<Category> pageList = categoryService.findSearch(param);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Category>(pageList.getTotalElements(), pageList.getContent()));
    }


    /**
     * 获取分类
     *
     * @return
     */
    @ApiOperation(value = "分类", notes = "获取分类名称")
    @GetMapping(value = "/getcategory")
    public Result getCategory() {
        return new Result(true, StatusCode.OK, "获取分类名成功", categoryService.findCategory());
    }



    /**
     * 根据标签id查询
     */
    @ApiOperation(value = "根据标签id查询", notes = "根据标签id查询")
    @GetMapping(value = "/findByCategoryId")
    public Result findCategoryIdByCategoryName(@RequestParam String categoryId) {
        return new Result(true, StatusCode.OK, "查询成功", categoryService.findByCategoryId(categoryId));
    }


    /**
     * 禁用
     *
     * @param categoryId
     * @return
     */
    @ApiOperation(value = "禁用", notes = "禁用")
    @GetMapping(value = "/lock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "标签Id", paramType = "query")
    })
    public Result lockState(@RequestParam String categoryId) {
        return new Result(true, StatusCode.OK, "禁用成功", categoryService.lockState(categoryId));
    }


    /**
     * 解封
     *
     * @param categoryId
     * @return
     */
    @ApiOperation(value = "解封", notes = "解封")
    @GetMapping(value = "/unlock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "标签Id", paramType = "query")
    })
    public Result unlockState(@RequestParam String categoryId) {
        return new Result(true, StatusCode.OK, "解封成功", categoryService.unlockState(categoryId));
    }



    /**
     * 添加标签
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "添加标签", notes = "添加标签")
    @PostMapping("/add/category")
    public Result addCategory(@RequestBody CategoryDTO param) {
        categoryService.addCategory(param);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改标签
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "修改标签", notes = "修改标签")
    @PostMapping("/update/category")
    public Result updateCategory(@RequestBody CategoryDTO param) {
        categoryService.update(param);
        return new Result(true, StatusCode.OK, "更新成功");
    }


    /**
     * 删除标签
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "删除标签", notes = "删除标签")
    @GetMapping("/delete/category")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "标签Id", paramType = "query")
    })
    public Result deleteCategory(@RequestParam String categoryId) {
        categoryService.deleteById(categoryId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
