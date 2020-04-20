package com.cyq.article.dao;

import com.cyq.article.pojo.CollectionRecord;
import com.cyq.article.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/24 12:57
 * @Description:
 */
public interface CollectionRecordDao extends JpaRepository<CollectionRecord,String>, JpaSpecificationExecutor<CollectionRecord> {


    @Query(value = "select count(*) from collection_record", nativeQuery = true)
    public int countCollection();


    @Modifying
    @Query(value = "delete from collection_record where article_id = ?1", nativeQuery = true)
    @Transactional
    public void deleteByArticleId(String articled);
}
