package com.cyq.article.dao;

import com.cyq.article.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/23 09:04
 * @Description:
 */
public interface CategoryDao extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {


    @Query(value = "select category_name,category_id,category_state from tb_category where category_state = '1'", nativeQuery = true)
    public List<Category> findCategory();

    @Query(value = "select category_name,category_id,category_state from tb_category where category_id = ?1", nativeQuery = true)
    public List<Category> findByCategoryId(String CategoryId);



    // 禁用
    @Modifying
    @Query(value = "update tb_category set category_state='0' where category_id=?1", nativeQuery = true)
    public int lockState(String categoryId);

    // 解封
    @Modifying
    @Query(value = "update tb_category set category_state='1' where category_id=?1", nativeQuery = true)
    public int unlockState(String categoryId);
}
