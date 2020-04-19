package com.cyq.car.service;

import com.cyq.car.dao.CarrecommendDAO;
import com.cyq.car.dto.CarrecommendDTO;
import com.cyq.car.pojo.Carrecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/17 22:43
 * @Description:
 */
@Service
public class CarrecommendService {

    @Autowired
    private CarrecommendDAO carrecommendDAO;


    public Carrecommend findByCardParam(CarrecommendDTO params){
        return carrecommendDAO.findByCardParam(params.getPrice(),params.getCarType(),params.getCategoryName());
    }


}
