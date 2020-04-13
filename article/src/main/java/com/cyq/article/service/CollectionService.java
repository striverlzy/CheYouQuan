package com.cyq.article.service;

import com.cyq.article.dao.ArticleDao;
import com.cyq.article.dao.CollectionRecordDao;
import com.cyq.article.dto.CollectionRecordDTO;
import com.cyq.article.pojo.CollectionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/3/28 17:05
 * @Description:
 */
@Service
public class CollectionService {

    @Autowired
    private CollectionRecordDao collectionRecordDao;
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;


    /**
     * 收藏记录
     *
     * @param param
     * @return
     */
    public void addCollectionRecord(CollectionRecordDTO param) {
        CollectionRecord collectionRecord = new CollectionRecord();
        collectionRecord.setCollectionRecordId(idWorker.nextId() + "");
        collectionRecord.setArticleId(param.getArticleId());
        collectionRecord.setArticleTitle(param.getArticleTitle());
        collectionRecord.setUserId(param.getUserId());
        collectionRecord.setUserImage(param.getUserImage());
        collectionRecord.setUserName(param.getUserName());
        collectionRecord.setCreateDate(LocalDate.now());
        collectionRecordDao.save(collectionRecord);
    }


    /**
     * 收藏更新
     *
     * @param articleId
     * @return
     */
    public int collection(String articleId) {
        return articleDao.collection(articleId);
    }


    /**
     * 取消收藏
     *
     * @param articleId
     * @return
     */
    public int unCollection(String articleId) {
        return articleDao.unCollection(articleId);
    }

    /**
     * 删除记录
     *
     * @param articleId
     * @return
     */
    public void deleteCollection(String articleId) {
        collectionRecordDao.deleteByArticleId(articleId);
    }


    /**
     * 根据用户Id查询报名活动记录
     *
     * @param param
     * @return
     */
    public Page<CollectionRecord> findRecordByUserId(CollectionRecordDTO param) {
        Specification<CollectionRecord> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(param.getPage() - 1, param.getSize());
        return collectionRecordDao.findAll(specification, pageRequest);
    }


    /**
     * 动态条件构建
     *
     * @param signUpRecord
     * @return
     */
    private Specification<CollectionRecord> createSpecification(CollectionRecordDTO signUpRecord) {

        return new Specification<CollectionRecord>() {

            @Override
            public Predicate toPredicate(Root<CollectionRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(signUpRecord.getUserId())) {
                    predicateList.add(cb.equal(root.get("userId").as(String.class), signUpRecord.getUserId()));
                }
                if (!StringUtils.isEmpty(signUpRecord.getArticleId())) {
                    predicateList.add(cb.equal(root.get("articleId").as(String.class), signUpRecord.getArticleId()));
                }
                List<Order> orders = new ArrayList<>();
                orders.add(cb.desc(root.get("createDate").as(LocalDate.class)));
                query.orderBy(orders);

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
