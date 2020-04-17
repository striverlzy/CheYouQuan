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

    /**
     * 条件查询+分页
     *
     * @param param
     * @return
     */
    public Page<Carrecommend> findSearch(CarrecommendDTO param) {
        Specification<Carrecommend> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(param.getPage() - 1, param.getSize());
        return carrecommendDAO.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param param * @return
     */
    private Specification<Carrecommend> createSpecification(CarrecommendDTO param) {
        return new Specification<Carrecommend>() {
            public Predicate toPredicate(Root<Carrecommend> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (param.getPrice() != null && !"".equals(param.getPrice())) {
                    predicateList.add(cb.equal(root.get("price").as(String.class), param.getPrice()));
                }

                if (param.getCarType() != null && !"".equals(param.getCarType())) {
                    predicateList.add(cb.equal(root.get("carType").as(String.class), param.getCarType()));
                }

                if (param.getCagetoryName() != null && !"".equals(param.getCagetoryName())) {
                    predicateList.add(cb.equal(root.get("cagetoryName").as(String.class), param.getCagetoryName()));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

}
