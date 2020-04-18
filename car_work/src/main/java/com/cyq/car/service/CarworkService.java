package com.cyq.car.service;

import com.cyq.car.dao.CarworkDAO;
import com.cyq.car.dto.CarrecommendDTO;
import com.cyq.car.dto.CarworkDTO;
import com.cyq.car.pojo.Carrecommend;
import com.cyq.car.pojo.Carwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/17 22:43
 * @Description:
 */
@Service
public class CarworkService {

    @Autowired
    private CarworkDAO carworkDAO;

    @Autowired
    private IdWorker idWorker;

    public Carwork findByCardParam(CarworkDTO params){
        return carworkDAO.findByCardParam(params.getIdCard(),params.getCardNumber(),params.getCardType(),params.getCardVin(),params.getCardEngine());
    }

}
