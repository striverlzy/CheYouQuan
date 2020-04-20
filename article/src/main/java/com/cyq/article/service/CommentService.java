package com.cyq.article.service;

import com.cyq.article.dao.ArticleDao;
import com.cyq.article.dao.CommentDao;
import com.cyq.article.dto.CommentDTO;
import com.cyq.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/24 13:02
 * @Description:
 */
@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ArticleDao articleDao;

    public void findAll() {
        commentDao.findAll();
    }

    /**
     * 点赞
     *
     * @param articleId
     * @return
     */
    public int updateThumbup(String articleId) {
        return commentDao.updateThumbup(articleId);
    }



    /**
     * 取消点赞
     *
     * @param articleId
     * @return
     */
    public int updateNotThumbup(String articleId) {
        return commentDao.updateNotThumbup(articleId);
    }




    /**
     * 根据文章Id查看评论
     *
     * @param articleId
     * @return
     */
    @Transactional
    public List<Comment> findByArticleId(String articleId) {
        return commentDao.findByArticleId(articleId);
    }


    /**
     * 根据文章Id添加评论
     *
     * @param param
     * @return
     */
    public void addByArticleId(CommentDTO param) {
        Comment comment = new Comment();
        comment.setCommentId(idWorker.nextId() + "");
        comment.setUserImage(param.getUserImage());
        comment.setUserName(param.getUserName());
        comment.setCommentDate(LocalDateTime.now());
        comment.setUserId(param.getUserId());
        comment.setArticleId(param.getArticleId());
        comment.setContent(param.getContent());
        articleDao.commentTotal(param.getArticleId());
        commentDao.save(comment);
    }

    /**
     * 获取评论数
     * @return
     */
    public int countComment(){
        return commentDao.countComment();
    }
    /**
     * 删除
     *
     * @param commentId
     */
    public void deleteById(String commentId) {
        commentDao.deleteById(commentId);
    }


    /**
     * 根据用户Id查询报名活动记录
     *
     * @param param
     * @return
     */
    public Page<Comment> findCommentByArticleId(CommentDTO param) {
        Specification<Comment> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(param.getPage() - 1, param.getSize());
        return commentDao.findAll(specification, pageRequest);
    }


    /**
     * 动态条件构建
     *
     * @param param
     * @return
     */
    private Specification<Comment> createSpecification(CommentDTO param) {

        return new Specification<Comment>() {

            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(param.getArticleId())) {
                    predicateList.add(cb.equal(root.get("articleId").as(String.class), param.getArticleId()));
                }
                List<Order> orders = new ArrayList<>();
                orders.add(cb.desc(root.get("commentDate").as(LocalDate.class)));
                query.orderBy(orders);

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
