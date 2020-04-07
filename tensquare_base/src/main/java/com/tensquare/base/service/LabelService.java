package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @Author：liuzhongyu
 * @Date: 2019/12/9 22:23
 * @Description: 标签业务逻辑类
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     *
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据ID查询标签
     *
     * @param id
     * @return
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /**
     * 增加标签
     *
     * @param label
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");//设置ID
        labelDao.save(label);
    }

    /**
     * 修改标签
     *
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 删除标签
     *
     * @param id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }


    /**
     * 构建查询条件
     *
     * @param searchMap
     * @return
     */
//    private Specification<Label> createSpecification(Map searchMap) {
//        return new Specification<Label>() {
//            @Override
//            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?>
//                    criteriaQuery, CriteriaBuilder cb) {
//                List<Predicate> predicateList = new ArrayList<>();
//                if (searchMap.get("labelname") != null && !"".equals(searchMap.get("labelname"))) {
//                    predicateList.add(cb.like(root.get("labelname").as(String.class), "%" + (String) searchMap.get("labelname") + "%"));
//                }
//                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
//                    predicateList.add(cb.equal(root.get("state").as(String.class), (String) searchMap.get("state")));
//                }
//                if (searchMap.get("recommend") != null && !"".equals(searchMap.get("recommend"))) {
//                    predicateList.add(cb.equal(root.get("recommend").as(String.class), (String) searchMap.get("recommend")));
//                }
//                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
//            }
//        };
//    }


    /**
     * 条件查询
     *
     * @param searchMap
     * @return
     */
    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是封装到哪个对象中。 where 类名 = label.getId
             * @param criteriaQuery  封装查询关键字，比如group by order by 等
             * @param criteriaBuilder  用来封装条件对象
             * @return
             */
            @Override
            public javax.persistence.criteria.Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // new一个list集合.来存放所有得条件
                List<javax.persistence.criteria.Predicate> list = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    javax.persistence.criteria.Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"); // where labelname like "%小明%"
                    list.add(predicate);
                }
                if (label.getState() != null && !"".equals(label.getState())) {
                    javax.persistence.criteria.Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState()); // where state = "1"
                    list.add(predicate);
                }
                // new一个数组作为最终返回值得条件
                javax.persistence.criteria.Predicate[] parr = new javax.persistence.criteria.Predicate[list.size()];
                // 把list直接转为数组
                parr = list.toArray(parr);
                return criteriaBuilder.and(parr); // where labelname like "%小明%" and state = "1"
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        // 封装分页对象
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是封装到哪个对象中。 where 类名 = label.getId
             * @param criteriaQuery  封装查询关键字，比如group by order by 等
             * @param criteriaBuilder  用来封装条件对象
             * @return
             */
            @Override
            public javax.persistence.criteria.Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // new一个list集合.来存放所有得条件
                List<javax.persistence.criteria.Predicate> list = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    javax.persistence.criteria.Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"); // where labelname like "%小明%"
                    list.add(predicate);
                }
                if (label.getState() != null && !"".equals(label.getState())) {
                    javax.persistence.criteria.Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState()); // where state = "1"
                    list.add(predicate);
                }
                // new一个数组作为最终返回值得条件
                javax.persistence.criteria.Predicate[] parr = new javax.persistence.criteria.Predicate[list.size()];
                // 把list直接转为数组
                parr = list.toArray(parr);
                return criteriaBuilder.and(parr); // where labelname like "%小明%" and state = "1"
            }
        }, pageable);
    }
}
