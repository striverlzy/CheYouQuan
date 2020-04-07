package com.cyq.article.service;

import com.cyq.article.dao.CategoryDao;
import com.cyq.article.dto.CategoryDTO;
import com.cyq.article.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/23 09:14
 * @Description:
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private IdWorker idWorker;


    /**
     * 添加
     *
     * @param param
     * @return
     */
    public void addCategory(CategoryDTO param) {
        Category category = new Category();
        category.setCategoryId(idWorker.nextId() + "");
        category.setCategoryName(param.getCategoryName());
        category.setCategoryState(param.getCategoryState());
        categoryDao.save(category);
    }

    /**
     * 更新标签
     *
     * @param param
     * @return
     */
    public void update(CategoryDTO param) {
        Category category = new Category();
        category.setCategoryId(param.getCategoryId());
        category.setCategoryName(param.getCategoryName());
        category.setCategoryState(param.getCategoryState());
        categoryDao.save(category);
    }

    /**
     * 删除
     *
     * @param categoryId
     * @return
     */
    public void delete(String categoryId) {
        categoryDao.deleteById(categoryId);
    }



    /**
     * 条件查询+分页
     *
     * @param param
     * @return
     */
    public Page<Category> findSearch(CategoryDTO param) {
        Specification<Category> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(param.getPage() - 1, param.getSize());
        return categoryDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param param * @return
     */
    private Specification<Category> createSpecification(CategoryDTO param) {
        return new Specification<Category>() {

            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // categoryId
                if (param.getCategoryId() != null && !"".equals(param.getCategoryId())) {
                    predicateList.add(cb.equal(root.get("categoryId").as(String.class), param.getCategoryId()));
                }
                // categoryName
                if (param.getCategoryName() != null && !"".equals(param.getCategoryName())) {
                    predicateList.add(cb.like(root.get("categoryName").as(String.class), "%" + (String) param.getCategoryName() + "%"));
                }
                // categoryState
                if (param.getCategoryState() != null && !"".equals(param.getCategoryState())) {
                    predicateList.add(cb.equal(root.get("categoryState").as(String.class), param.getCategoryState()));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
    /**
     * 增加
     *
     * @param category
     */
    public void add(Category category) {
        category.setCategoryId(idWorker.nextId() + "");
        categoryDao.save(category);
    }


    /**
     * 查询全部
     */
    public List<Category> findCategory(){
        return categoryDao.findCategory();
    }

    /**
     * 删除
     *
     * @param categoryId
     */
    public void deleteById(String categoryId) {
        categoryDao.deleteById(categoryId);
    }


    /**
     * 修改
     *
     * @param category
     */
    public void update(Category category) {
        categoryDao.save(category);
    }



    /**
     * 禁用
     *
     * @param categoryId
     * @return
     */
    @Transactional
    public int lockState(String categoryId) {
       return categoryDao.lockState(categoryId);
    }

    /**
     * 根据id查询
     *
     * @param categoryId
     * @return
     */
    public List<Category> findByCategoryId(String categoryId) {
        return categoryDao.findByCategoryId(categoryId);
    }

    /**
     * 解封
     *
     * @param categoryId
     * @return
     */
    @Transactional
    public int unlockState(String categoryId) {
        return categoryDao.unlockState(categoryId);
    }

}
