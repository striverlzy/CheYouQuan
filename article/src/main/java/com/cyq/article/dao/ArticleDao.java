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

    @Query(value = "select article_id,is_collection,user_id,user_name,user_image,category_id,category_name,title,content,filter_content,article_image,comment_total,collection_total,thumbup,create_date,article_state from tb_article where article_state = '1' and category_id = ?1", nativeQuery = true)
    public List<Article> findAllByCategoryId(String categoryId);

    @Query(value = "select article_id,is_collection,user_id,user_name,user_image,category_id,category_name,title,content,filter_content,article_image,comment_total,collection_total,thumbup,create_date,article_state from tb_article where article_state = '1' and user_id = ?1", nativeQuery = true)
    public List<Article> findAllByUserId(String userId);

    // 点赞
    @Modifying
    @Query(value = "update tb_article a set thumbup=thumbup+1 where article_id=?1", nativeQuery = true)
    public int updateThumbup(String articleId);


    // 取消点赞
    @Modifying
    @Query(value = "update tb_article a set thumbup=thumbup-1 where article_id=?1", nativeQuery = true)
    public int updateNotThumbup(String articleId);

    // 收藏
    @Transactional
    @Modifying
    @Query(value = "update tb_article a set collection_total=collection_total+1 where article_id=?1", nativeQuery = true)
    public int collection(String articleId);

    // 取消收藏
    @Transactional
    @Modifying
    @Query(value = "update tb_article a set collection_total=collection_total-1 where article_id=?1", nativeQuery = true)
    public int unCollection(String articleId);


    // 审核
    @Modifying
    @Query(value = "update tb_article set article_state='1' where article_id=?1", nativeQuery = true)
    public int checkState(String articleId);

    // 审核
    @Modifying
    @Query(value = "update tb_article set article_state='2' where article_id=?1", nativeQuery = true)
    public int unCheckState(String articleId);

}
