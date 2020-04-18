package com.cyq.car.dao;

import com.cyq.car.pojo.Carrecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/17 22:36
 * @Description:
 */
public interface CarrecommendDAO extends JpaRepository<Carrecommend, String>, JpaSpecificationExecutor<Carrecommend> {
    @Query(value = "select * from tb_car_recommend where price=?1 and car_type=?2 and cagetory_name=?3",nativeQuery=true)
    Carrecommend findByCardParam(String price,String carType,String catetory_name);

}
