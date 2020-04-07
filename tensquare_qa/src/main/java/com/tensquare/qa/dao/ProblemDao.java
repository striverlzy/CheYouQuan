//package com.tensquare.qa.dao;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//
//import com.tensquare.qa.pojo.Problem;
//import org.springframework.data.jpa.repository.Query;
//
///**
// * 数据访问接口
// *
// * @author Administrator
// */
//public interface QuestionDao extends JpaRepository<Question, String>, JpaSpecificationExecutor<Question> {
//
//    /**
//     * 根据标签ID查询最新问题列表
//     *
//     * @param categoryId
//     * @param pageable
//     * @return
//     */
//    @Query(value = "select p from tb_question p where id in( select questionid from tb_category where categoryid=? 1) order by replytime desc",nativeQuery = true)
//    public Page<Question> findNewListByCategoryId(String categoryId, Pageable pageable);
//
//
//    /**
//     * 根据标签ID查询热门问题列表
//     *
//     * @param categoryId
//     * @param pageable
//     * @return
//     */
//    @Query(value = "select p from tb_question p where id in( select questionid from tb_category where categoryid=? 1) order by reply desc",nativeQuery = true)
//    public Page<Question> findHotListByLabelId(String categoryId, Pageable pageable);
//
//
//    /**
//     * 根据标签ID查询热门问题列表
//     *
//     * @param categoryId
//     * @param pageable
//     * @return
//     */
//    @Query(value = "select p from tb_question p where id in( select questionid from tb_category where categoryid=? 1) and reply=0 order by reply desc",nativeQuery = true)
//    public Page<Question> findWaitListByLabelId(String categoryId, Pageable pageable);
//}
//
//
