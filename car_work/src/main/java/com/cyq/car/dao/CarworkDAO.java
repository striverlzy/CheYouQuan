package com.cyq.car.dao;

import com.cyq.car.pojo.Carwork;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/17 22:36
 * @Description:
 */
public interface CarworkDAO extends JpaRepository<Carwork, String>, JpaSpecificationExecutor<Carwork> {

    @Query(value = "select * from tb_car_work where id_card=?1 and card_number=?2 and card_type=?3 and card_engine=?4",nativeQuery=true)
    Carwork findByCardParam(String idCard,String cardNumber,String cardType,String cardEngine);

}
