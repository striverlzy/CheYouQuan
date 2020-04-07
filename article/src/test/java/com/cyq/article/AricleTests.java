package com.cyq.article;

import com.cyq.article.dao.ArticleDao;
import com.cyq.article.dao.CategoryDao;
import com.cyq.article.pojo.Article;
import com.cyq.article.pojo.Category;
import com.cyq.article.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.HtmlUtils;
import util.IdWorker;

import java.util.*;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/23 19:41
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AricleTests {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ArticleService articleService;


    @Test
    public void getCategory() {
        List<Category> list = null;
        list = categoryDao.findCategory();
//        if (list != null) {
//
//        }
//
        System.out.println("List list" + list);
    }


    @Test
    public void test() {

    }
}
