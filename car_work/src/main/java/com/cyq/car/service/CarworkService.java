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


    /**
     * 条件查询+分页
     *
     * @param param
     * @return
     */
    public Page<Carwork> findSearch(CarworkDTO param) {
        Specification<Carwork> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(param.getPage() - 1, param.getSize());
        return carworkDAO.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param param * @return
     */
    private Specification<Carwork> createSpecification(CarworkDTO param) {
        return new Specification<Carwork>() {
            public Predicate toPredicate(Root<Carwork> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (param.getCardEngine() != null && !"".equals(param.getCardEngine())) {
                    predicateList.add(cb.equal(root.get("cardEngine").as(String.class), param.getCardEngine()));
                }

                if (param.getCardNumber() != null && !"".equals(param.getCardNumber())) {
                    predicateList.add(cb.equal(root.get("cardNumber").as(String.class), param.getCardNumber()));
                }

                if (param.getCardType() != null && !"".equals(param.getCardType())) {
                    predicateList.add(cb.equal(root.get("cardType").as(String.class), param.getCardType()));
                }
                if (param.getCardVin() != null && !"".equals(param.getCardVin())) {
                    predicateList.add(cb.equal(root.get("cardVin").as(String.class), param.getCardVin()));
                }
                if (param.getIdCard() != null && !"".equals(param.getIdCard())) {
                    predicateList.add(cb.equal(root.get("idCard").as(String.class), param.getIdCard()));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
