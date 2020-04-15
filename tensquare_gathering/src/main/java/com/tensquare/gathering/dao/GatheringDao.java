package com.tensquare.gathering.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.gathering.pojo.Gathering;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface GatheringDao extends JpaRepository<Gathering,String>,JpaSpecificationExecutor<Gathering>{

    /**
     * 根据Id查询
     * @param gatheringId
     * @return
     */
    @Query(value = "select gathering_id,title,introduction,detail,gathering_image,internet_url,start_date,end_date,address,sponsor,sign_end,sign_num,sign_ids,create_date,state from tb_gathering where gathering_id = ?1", nativeQuery = true)
    public List<Gathering> findByGatheringId(String gatheringId);


    /**
     * 报名
     * @param gatheringId
     * @return
     */

    @Transactional
    @Modifying
    @Query(value = "update tb_gathering a set sign_num=sign_num+1 where gathering_id=?1",nativeQuery = true)
    public int signUp(String gatheringId);


    /**
     * 更新报名人数
     * @param gatheringId
     * @return
     */

    @Transactional
    @Modifying
    @Query(value = "update tb_gathering a set sign_ids=concat(sign_ids,?1) where gathering_id=?2",nativeQuery = true)
    public void updateConcatSignIds(String sign_ids,String gatheringId);

    @Transactional
    @Modifying
    @Query(value = "update tb_gathering a set sign_ids=?1 where gathering_id=?2",nativeQuery = true)
    public void updateSignIds(String sign_ids,String gatheringId);


    /**
     * 活动结束更新状态
     * @param gatheringId
     */
    @Transactional
    @Modifying
    @Query(value = "update tb_gathering a set state='1' where gathering_id=?1",nativeQuery = true)
    public void endGathering(String gatheringId);

}
