package com.cyq.article.dao;

import com.cyq.article.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/24 12:57
 * @Description:
 */
public interface CommentDao extends JpaRepository<Comment,String>, JpaSpecificationExecutor<Comment> {

    /**
     * 根据文章id查评论
     * @param articleId
     * @return
     */
    @Query(value = "select comment_id,article_id,user_id,like_total,content,comment_date from tb_comment where article_id = ?1",nativeQuery = true)
    public List<Comment> findByArticleId(String articleId);


    // 点赞
    @Transactional
    @Modifying
    @Query(value = "update tb_comment a set thumbup=thumbup+1 where comment_id=?1", nativeQuery = true)
    public int updateThumbup(String commentId);

    // 取消点赞
    @Transactional
    @Modifying
    @Query(value = "update tb_comment a set thumbup=thumbup-1 where comment_id=?1", nativeQuery = true)
    public int updateNotThumbup(String commentId);

}
