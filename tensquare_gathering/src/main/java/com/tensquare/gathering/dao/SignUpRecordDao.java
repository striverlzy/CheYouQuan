package com.tensquare.gathering.dao;

import com.tensquare.gathering.pojo.SignUpRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface SignUpRecordDao extends JpaRepository<SignUpRecord,String>,JpaSpecificationExecutor<SignUpRecord>{

    /**
     * 根据Id查询
     * @param userId
     * @return
     */
    @Query(value = "select gathering_id,sign_record_id,user_id,create_date,gathering_title from sign_record where user_id = ?1", nativeQuery = true)
    public List<SignUpRecord> findRecordByUserId(String userId);



}
