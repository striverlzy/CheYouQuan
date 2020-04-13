package com.cyq.article.service;

import com.cyq.article.dao.ArticleDao;
import com.cyq.article.dto.ArticleDTO;
import com.cyq.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/23 09:14
 * @Description:
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 条件查询+分页
     *
     * @param param
     * @return
     */
    public Page<Article> findSearch(ArticleDTO param) {
        Specification<Article> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(param.getPage() - 1, param.getSize());
        return articleDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param param * @return
     */
    private Specification<Article> createSpecification(ArticleDTO param) {
        return new Specification<Article>() {
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // categoryId
                if (param.getCategoryId() != null && !"".equals(param.getCategoryId())) {
                    predicateList.add(cb.equal(root.get("categoryId").as(String.class), param.getCategoryId()));
                }
                // filterContent
                if (param.getFilterContent() != null && !"".equals(param.getFilterContent())) {
                    predicateList.add(cb.like(root.get("filterContent").as(String.class), "%" + (String) param.getFilterContent() + "%"));
                }
                // title
                if (param.getTitle() != null && !"".equals(param.getTitle())) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) param.getTitle() + "%"));
                }
                // articleState
                if (param.getArticleState() != null && !"".equals(param.getArticleState())) {
                    predicateList.add(cb.equal(root.get("articleState").as(String.class), param.getArticleState()));
                }

                List<Order> orders = new ArrayList<>();
                orders.add(cb.desc(root.get("createDate")));
                query.orderBy(orders);
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    /**
     * 新增
     *
     * @param param
     */
    public void addArticle(ArticleDTO param) {
        Article article = new Article();
        article.setArticleId(idWorker.nextId() + "");
        article.setUserId(param.getUserId());
        article.setUserName(param.getUserName());
        article.setUserImage(param.getUserImage());
        article.setCategoryId(param.getCategoryId());
        article.setCreateDate(LocalDateTime.now());
        article.setTitle(param.getTitle());
        article.setCategoryName(param.getCategoryName());
        article.setContent(param.getContent());
        article.setFilterContent(param.getFilterContent());
        article.setIsCollection("0");
        article.setIsThumbup("0");
        article.setArticleState("1");
        article.setCommentTotal(0);
        article.setThumbup(0);
        article.setCollectionTotal(0);
        articleDao.save(article);
    }

    /**
     * 分类查询
     *
     * @param categoryId
     * @return
     */
    public List<Article> findAllByCategoryId(String categoryId) {
        return articleDao.findAllByCategoryId(categoryId);
    }

    /**
     * 根据用户Id查询
     *
     * @param userId
     * @return
     */
    public List<Article> findAllByUserId(String userId) {
        return articleDao.findAllByUserId(userId);
    }

    /**
     * 点赞
     *
     * @param articleId
     * @return
     */
    public int isThumbup(String articleId) {
        return articleDao.isThumbup(articleId);
    }

    /**
     * 点赞数加一
     *
     * @param articleId
     * @return
     */
    @Transactional
    public int updateThumbup(String articleId) {
       return articleDao.updateThumbup(articleId);
    }

    /**
     * 取消点赞
     *
     * @param articleId
     * @return
     */
    public int notThumbup(String articleId) {
        return articleDao.notThumbup(articleId);
    }

    /**
     * 点赞数减一
     *
     * @param articleId
     * @return
     */
    @Transactional
    public int updateNotThumbup(String articleId) {
        return articleDao.updateNotThumbup(articleId);
    }


    /**
     * 审核
     *
     * @param articleId
     * @return
     */
    @Transactional
    public int checkState(String articleId) {
         return articleDao.checkState(articleId);
    }

    /**
     * 审核
     *
     * @param articleId
     * @return
     */
    @Transactional
    public int unCheckState(String articleId) {
        return articleDao.unCheckState(articleId);
    }


    /**
     * 查询全部
     */
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    /**
     * 根据文章ID查询
     *
     * @param articleId
     * @return
     */
    public Article findByArticleId(String articleId) {
        Article article = articleDao.findById(articleId).get();
        return article;
    }

    /**
     * 删除
     *
     * @param articleId
     */
    public void deleteById(String articleId) {
        articleDao.deleteById(articleId);
    }

    /**
     * 修改
     *
     * @param article
     */
    public void update(Article article) {
        articleDao.save(article);
    }

}
