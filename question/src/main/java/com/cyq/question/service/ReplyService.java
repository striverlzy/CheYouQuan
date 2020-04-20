package com.cyq.question.service;

import com.cyq.question.dao.ReplyDao;
import com.cyq.question.dto.QuestionDTO;
import com.cyq.question.dto.ReplyDto;
import com.cyq.question.pojo.Question;
import com.cyq.question.pojo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/3 09:19
 * @Description:
 */
@Service
public class ReplyService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ReplyDao replyDao;

    /**
     * 条件查询+分页
     *
     * @param param
     * @return
     */
    public Page<Reply> findSearch(ReplyDto param) {
        Specification<Reply> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(param.getPage() - 1, param.getSize());
        return replyDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param param * @return
     */
    private Specification<Reply> createSpecification(ReplyDto param) {
        return new Specification<Reply>() {
            public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // userId
                if (param.getUserId() != null && !"".equals(param.getUserId())) {
                    predicateList.add(cb.equal(root.get("userId").as(String.class), param.getUserId()));
                }

                // userId
                if (param.getQuestionId() != null && !"".equals(param.getQuestionId())) {
                    predicateList.add(cb.equal(root.get("questionId").as(String.class), param.getQuestionId()));
                }
                // userName
                if (param.getUserName() != null && !"".equals(param.getUserName())) {
                    predicateList.add(cb.like(root.get("userName").as(String.class), "%" + (String) param.getUserName() + "%"));
                }

                // content
                if (param.getContent() != null && !"".equals(param.getContent())) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) param.getContent() + "%"));
                }

                List<Order> orders = new ArrayList<>();
                orders.add(cb.desc(root.get("replyDate")));
                query.orderBy(orders);
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }


    /**
     * 点赞
     *
     * @param replyId
     * @return
     */
    @Transactional
    public int updateThumbup(String replyId) {
        return replyDao.updateThumbup(replyId);
    }


    /**
     * 取消点赞
     *
     * @param replyId
     * @return
     */
    @Transactional
    public int updateNotThumbup(String replyId) {
        return replyDao.updateNotThumbup(replyId);
    }


    /**
     * 删除
     *
     * @param replyId
     */
    public void deleteById(String replyId) {
        replyDao.deleteById(replyId);
    }

    /**
     * 回答
     *
     * @param param
     * @return
     */
    public void reply(ReplyDto param) {
        Reply reply = new Reply();
        reply.setReplyId(idWorker.nextId() + "");
        reply.setUserId(param.getUserId());
        reply.setQuestionId(param.getQuestionId());
        reply.setUserName(param.getUserName());
        reply.setUserImage(param.getUserImage());
        reply.setContent(param.getContent());
        reply.setThumbup(0);
        reply.setReplyDate(LocalDateTime.now());
        replyDao.save(reply);
    }


}
