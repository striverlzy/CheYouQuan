package com.cyq.article.dao;

import com.cyq.article.pojo.CollectionRecord;
import com.cyq.article.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/24 12:57
 * @Description:
 */
public interface CollectionRecordDao extends JpaRepository<CollectionRecord,String>, JpaSpecificationExecutor<CollectionRecord> {

}
