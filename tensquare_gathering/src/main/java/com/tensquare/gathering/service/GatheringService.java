package com.tensquare.gathering.service;

import com.tensquare.gathering.dao.GatheringDao;
import com.tensquare.gathering.dao.SignUpRecordDao;
import com.tensquare.gathering.dto.GatheringDTO;
import com.tensquare.gathering.dto.SignUpRecordDTO;
import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.pojo.SignUpRecord;
import entity.PageResult;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class GatheringService {

    @Autowired
    private GatheringDao gatheringDao;

    @Autowired
    private SignUpRecordDao signUpRecordDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Gathering> findAll() {
        return gatheringDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param params
     * @return
     */
    public Page<Gathering> findSearch(GatheringDTO params) {
        Specification<Gathering> specification = createSpecification(params);
        PageRequest pageRequest = PageRequest.of(params.getPage() - 1, params.getSize());
        return gatheringDao.findAll(specification, pageRequest);
    }

    /**
     * 动态条件构建
     *
     * @param params
     * @return
     */
    private Specification<Gathering> createSpecification(GatheringDTO params) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new Specification<Gathering>() {

            @Override
            public Predicate toPredicate(Root<Gathering> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 活动标题
                if (params.getTitle() != null && !"".equals(params.getTitle())) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) params.getTitle() + "%"));
                }
                // 大会简介
                if (params.getIntroduction() != null && !"".equals(params.getIntroduction())) {
                    predicateList.add(cb.like(root.get("introduction").as(String.class), "%" + (String) params.getIntroduction() + "%"));
                }
                // 详细说明
                if (params.getDetail() != null && !"".equals(params.getDetail())) {
                    predicateList.add(cb.like(root.get("detail").as(String.class), "%" + (String) params.getDetail() + "%"));
                }
                // 主办方
                if (params.getSponsor() != null && !"".equals(params.getSponsor())) {
                    predicateList.add(cb.like(root.get("sponsor").as(String.class), "%" + (String) params.getSponsor() + "%"));
                }
                // 举办地点
                if (params.getAddress() != null && !"".equals(params.getAddress())) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%" + (String) params.getAddress() + "%"));
                }
                // 开始时间
                if (params.getStartDate() != null && !"".equals(params.getStartDate())) {
                    predicateList.add(cb.greaterThanOrEqualTo(root.get("startDate").as(LocalDate.class), LocalDate.parse(params.getStartDate(), dateTimeFormatter)));
                }
                // 结束时间
                if (params.getEndDate() != null && !"".equals(params.getEndDate())) {
                    predicateList.add(cb.lessThanOrEqualTo(root.get("endDate").as(LocalDate.class), LocalDate.parse(params.getEndDate(), dateTimeFormatter)));
                }
                if (params.getState().equals("1")) {
                    predicateList.add(cb.equal(root.get("state").as(String.class), params.getState()));
                }
                List<Order> orders = new ArrayList<>();
                if (params.getIsHost().equals("0")) {
                    orders.add(cb.desc(root.get("createDate").as(LocalDateTime.class)));
                }
                if (params.getIsHost().equals("1")) {
                    orders.add(cb.desc(root.get("signNum").as(int.class)));
                }
                query.orderBy(orders);

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 根据ID查询实体
     *
     * @param gatheringId
     * @return
     */
    public List<Gathering> findByGatheringId(String gatheringId) {

        return gatheringDao.findByGatheringId(gatheringId);
    }

    /**
     * 活动结束更新
     * @param gatheringId
     */
    public void endGathering(String gatheringId){
        gatheringDao.endGathering(gatheringId);
    }

    /**
     * 新增报名活动记录
     *
     * @param param
     * @return
     */
    public void addGatheringRecord(SignUpRecordDTO param) {
        SignUpRecord signUpRecord = new SignUpRecord();
        signUpRecord.setSignRecordId(idWorker.nextId() + "");
        signUpRecord.setGatheringId(param.getGatheringId());
        signUpRecord.setGatheringTitle(param.getGatheringTitle());
        signUpRecord.setUserId(param.getUserId());
        signUpRecord.setCreateDate(LocalDate.now());
        signUpRecordDao.save(signUpRecord);
    }


    /**
     * 根据用户Id查询报名活动记录
     *
     * @param param
     * @return
     */
    public Page<SignUpRecord> findRecordByUserId(SignUpRecordDTO param) {
        Specification<SignUpRecord> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(param.getPage() - 1, param.getSize());
        return signUpRecordDao.findAll(specification, pageRequest);
    }


    /**
     * 动态条件构建
     *
     * @param signUpRecord
     * @return
     */
    private Specification<SignUpRecord> createSpecification(SignUpRecordDTO signUpRecord) {

        return new Specification<SignUpRecord>() {

            @Override
            public Predicate toPredicate(Root<SignUpRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(signUpRecord.getUserId())) {
                    predicateList.add(cb.equal(root.get("userId").as(String.class), signUpRecord.getUserId()));
                }
                if (!StringUtils.isEmpty(signUpRecord.getGatheringId())) {
                    predicateList.add(cb.equal(root.get("gatheringId").as(String.class), signUpRecord.getGatheringId()));
                }
                List<Order> orders = new ArrayList<>();
                orders.add(cb.desc(root.get("createDate").as(LocalDateTime.class)));
                query.orderBy(orders);

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 删除
     *
     * @param gatheringId
     * @return
     */
    public void delete(String gatheringId) {
        gatheringDao.deleteById(gatheringId);
    }


    /**
     * 报名更新
     *
     * @param gatheringId
     * @return
     */
    public int signUp(String gatheringId) {
        return gatheringDao.signUp(gatheringId);
    }

    /**
     * 更新报名人数
     */
    public void updateSignIds(String userId, String gatheringId) {
        SignUpRecordDTO param = new SignUpRecordDTO();
        param.setGatheringId(gatheringId);
        param.setPage(1);
        param.setSize(1);
        String signId = null;
        Page<SignUpRecord> pageList = this.findRecordByUserId(param);
        if(pageList.getTotalElements() == 0){
            signId = userId;
            gatheringDao.updateSignIds(signId,gatheringId);
        }else {
            signId = "," + userId;
            gatheringDao.updateConcatSignIds(signId, gatheringId);
        }
    }

    /**
     * 发布活动
     *
     * @param param
     */
    public void add(GatheringDTO param) {
        Gathering gathering = new Gathering();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(param.getStartDate(), dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(param.getEndDate(), dateTimeFormatter);
        LocalDate signEnd = LocalDate.parse(param.getSignEnd(), dateTimeFormatter);
        gathering.setGatheringId(idWorker.nextId() + " ");
        gathering.setTitle(param.getTitle());
        gathering.setAddress(param.getAddress());
        gathering.setDetail(param.getDetail());
        gathering.setInternetUrl(param.getInternetUrl());
        gathering.setStartDate(startDate);
        gathering.setEndDate(endDate);
        gathering.setIntroduction(param.getIntroduction());
        gathering.setGatheringImage(param.getGatheringImage());
        gathering.setSignIds(param.getSignIds());
        gathering.setSignEnd(signEnd);
        gathering.setState("0");
        gathering.setSponsor(param.getSponsor());
        gathering.setSignNum(0);
        gathering.setCreateDate(LocalDateTime.now());
        gatheringDao.save(gathering);
    }


    /**
     * 修改活动
     *
     * @param param
     */
    public void update(GatheringDTO param) {
        Gathering gathering = new Gathering();
        gathering.setGatheringId(param.getGatheringId());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(param.getStartDate(), dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(param.getEndDate(), dateTimeFormatter);
        LocalDate signEnd = LocalDate.parse(param.getSignEnd(), dateTimeFormatter);
        gathering.setTitle(param.getTitle());
        gathering.setAddress(param.getAddress());
        gathering.setDetail(param.getDetail());
        gathering.setInternetUrl(param.getInternetUrl());
        gathering.setStartDate(startDate);
        gathering.setEndDate(endDate);
        gathering.setIntroduction(param.getIntroduction());
        gathering.setGatheringImage(param.getGatheringImage());
        gathering.setSignIds(param.getSignIds());
        gathering.setSignEnd(signEnd);
        gathering.setSponsor(param.getSponsor());
        if (param.getSignNum() == null) {
            gathering.setSignNum(0);
        } else {
            gathering.setSignNum(param.getSignNum());
        }
        gathering.setCreateDate(LocalDateTime.now());
        gatheringDao.save(gathering);
    }
}
