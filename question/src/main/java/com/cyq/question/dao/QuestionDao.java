package com.cyq.question.dao;

import com.cyq.question.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/2 18:08
 * @Description:
 */
public interface QuestionDao extends JpaRepository<Question, String>, JpaSpecificationExecutor<Question> {


    @Query(value = "select question_id,user_id,user_name,user_image,category_id,category_name,title,content,reply_total,thumbup,create_date,state from tb_question where question_id = ?1", nativeQuery = true)
    public Question findByQuestionId(String questionId);

    // 点赞
    @Modifying
    @Query(value = "update tb_question a set thumbup=thumbup+1 where question_id=?1", nativeQuery = true)
    public int updateThumbup(String questionId);


    // 取消点赞
    @Modifying
    @Query(value = "update tb_question a set thumbup=thumbup-1 where question_id=?1", nativeQuery = true)
    public int updateNotThumbup(String questionId);


    // 已回答
    @Modifying
    @Query(value = "update tb_question a set state='1' where question_id=?1", nativeQuery = true)
    public default int updateState(String questionId) {
        return 0;
    }

    // 已回答
    @Modifying
    @Query(value = "update tb_question a set reply_total=reply_total+1 where question_id=?1", nativeQuery = true)
    public int updateReplyTotal(String questionId);
}
