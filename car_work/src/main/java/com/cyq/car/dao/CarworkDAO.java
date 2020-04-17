package com.cyq.car.dao;

import com.cyq.car.pojo.Carwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/17 22:36
 * @Description:
 */
public interface CarworkDAO extends JpaRepository<Carwork, String>, JpaSpecificationExecutor<Carwork> {



}
