package com.cyq.article.dao;

import com.cyq.article.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/23 09:04
 * @Description:
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {


    @Query(value = "select article_id,is_collection,is_thumbup,user_id,user_name,user_image,category_id,category_name,title,content,filter_content,article_image,comment_total,collection_total,thumbup,create_date,article_state from tb_article where article_state = '1' and category_id = ?1", nativeQuery = true)
    public List<Article> findAllByCategoryId(String categoryId);

    @Query(value = "select count(*) from tb_article", nativeQuery = true)
    public int countArticle();

    @Query(value = "select count(*) from tb_article where category_id=1?", nativeQuery = true)
    public int countByCategory(String CategoryId);



    @Query(value = "select sum(thumbup) from tb_article", nativeQuery = true)
    public int countThumbup();

    @Query(value = "select article_id,is_collection,is_thumbup,user_id,user_name,user_image,category_id,category_name,title,content,filter_content,article_image,comment_total,collection_total,thumbup,create_date,article_state from tb_article where article_state = '1' and user_id = ?1", nativeQuery = true)
    public List<Article> findAllByUserId(String userId);

    // 点赞数加一
    @Modifying
    @Query(value = "update tb_article a set thumbup=thumbup+1 where article_id=?1", nativeQuery = true)
    public int updateThumbup(String articleId);

    // 已点赞
    @Modifying
    @Query(value = "update tb_article a set is_thumbup='1' where article_id=?1", nativeQuery = true)
    @Transactional
    public int isThumbup(String articleId);


    // 点赞数减一
    @Modifying
    @Query(value = "update tb_article a set thumbup=thumbup-1 where article_id=?1", nativeQuery = true)
    public int updateNotThumbup(String articleId);

    // 取消点赞
    @Modifying
    @Query(value = "update tb_article a set is_thumbup='0' where article_id=?1", nativeQuery = true)
    @Transactional
    public int notThumbup(String articleId);

    // 评论数加一
    @Transactional
    @Modifying
    @Query(value = "update tb_article a set comment_total=comment_total+1 where article_id=?1", nativeQuery = true)
    public int commentTotal(String articleId);

    // 收藏数加一
    @Transactional
    @Modifying
    @Query(value = "update tb_article a set collection_total=collection_total+1 where article_id=?1", nativeQuery = true)
    public int collection(String articleId);

    // 已收藏
    @Transactional
    @Modifying
    @Query(value = "update tb_article a set is_collection='1' where article_id=?1", nativeQuery = true)
    public int updateCollection(String articleId);

    // 收藏数减一
    @Transactional
    @Modifying
    @Query(value = "update tb_article a set collection_total=collection_total-1 where article_id=?1", nativeQuery = true)
    public int unCollection(String articleId);

    // 取消收藏
    @Transactional
    @Modifying
    @Query(value = "update tb_article a set is_collection='0' where article_id=?1", nativeQuery = true)
    public int updateNotCollection(String articleId);


    // 审核
    @Modifying
    @Query(value = "update tb_article set article_state='1' where article_id=?1", nativeQuery = true)
    public int checkState(String articleId);

    // 审核
    @Modifying
    @Query(value = "update tb_article set article_state='2' where article_id=?1", nativeQuery = true)
    public int unCheckState(String articleId);

}
