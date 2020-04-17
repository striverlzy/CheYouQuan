package com.cyq.question.service;

import com.cyq.question.dao.QuestionDao;
import com.cyq.question.dto.QuestionDTO;
import com.cyq.question.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/3 09:18
 * @Description:
 */
@Service
public class QuestionService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private QuestionDao questionDao;


    /**
     * 条件查询+分页
     *
     * @param param
     * @return
     */
    public Page<Question> findSearch(QuestionDTO param) {
        Specification<Question> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(param.getPage() - 1, param.getSize());
        return questionDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param param * @return
     */
    private Specification<Question> createSpecification(QuestionDTO param) {
        return new Specification<Question>() {
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // userId
                if (param.getUserId() != null && !"".equals(param.getUserId())) {
                    predicateList.add(cb.equal(root.get("userId").as(String.class), param.getUserId()));
                }
                // userName
                if (param.getUserName() != null && !"".equals(param.getUserName())) {
                    predicateList.add(cb.like(root.get("userName").as(String.class), "%" + (String) param.getUserName() + "%"));
                }
                // title
                if (param.getTitle() != null && !"".equals(param.getTitle())) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) param.getTitle() + "%"));
                }
                // content
                if (param.getContent() != null && !"".equals(param.getContent())) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) param.getContent() + "%"));
                }
                List<Order> orders = new ArrayList<>();
                if ("1".equals(param.getSearchState())) {  // 最新问答
                    orders.add(cb.desc(root.get("createDate").as(LocalDateTime.class)));
                }
                if ("2".equals(param.getSearchState())) { // 最热问答
                    orders.add(cb.desc(root.get("replyTotal")));
                }
                if ("3".equals(param.getSearchState())) { // 等待回答
                    predicateList.add(cb.equal(root.get("replyTotal"), 0));
                    orders.add(cb.desc(root.get("createDate").as(LocalDateTime.class)));
                }
                query.orderBy(orders);
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }


    /**
     * 点赞
     *
     * @param questionId
     * @return
     */
    @Transactional
    public int updateThumbup(String questionId) {
        return questionDao.updateThumbup(questionId);
    }


    /**
     * 取消点赞
     *
     * @param questionId
     * @return
     */
    @Transactional
    public int updateNotThumbup(String questionId) {
        return questionDao.updateThumbup(questionId);
    }


    /**
     * 删除
     *
     * @param questionId
     */
    public void deleteById(String questionId) {
        questionDao.deleteById(questionId);
    }

    /**
     * 回答问题
     *
     * @param questionId
     * @return
     */
    @Transactional
    public int updateState(String questionId) {
       return   questionDao.updateState(questionId);
    }


    /**
     * 发布问答
     *
     * @param param
     * @return
     */
    public void publishQusertion(QuestionDTO param) {
        Question question = new Question();
        question.setQuestionId(idWorker.nextId() + "");
        question.setUserId(param.getUserId());
        question.setUserName(param.getUserName());
        question.setUserImage(param.getUserImage());
        question.setCategoryId(param.getCategoryId());
        question.setCategoryName(param.getCategoryName());
        question.setContent(param.getContent());
        question.setReplyTotal(0);
        question.setThumbup(0);
        question.setState("0");
        question.setTitle(param.getTitle());
        question.setCreateDate(LocalDateTime.now());
        questionDao.save(question);
    }


}
