package com.cyq.question.dao;

import com.cyq.question.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/2 18:08
 * @Description:
 */
public interface ReplyDao extends JpaRepository<Reply, String>, JpaSpecificationExecutor<Reply> {

    // 点赞
    @Modifying
    @Query(value = "update tb_reply a set thumbup=thumbup+1 where reply_id=?1", nativeQuery = true)
    public int updateThumbup(String replyId);


    // 取消点赞
    @Modifying
    @Query(value = "update tb_reply a set thumbup=thumbup+1 where reply_id=?1", nativeQuery = true)
    public int updateNotThumbup(String replyId);
}
