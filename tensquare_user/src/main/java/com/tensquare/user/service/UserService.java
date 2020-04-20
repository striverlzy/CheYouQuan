package com.tensquare.user.service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;


import com.tensquare.user.dto.RegisterDTO;
import com.tensquare.user.dto.SearchListDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import util.IdWorker;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;


    /**
     * 黑名单
     *
     * @param userId
     * @return
     */
    @Transactional
    public int lockState(String userId) {
        return userDao.lockState(userId);
    }

    /**
     * 解封
     *
     * @param userId
     * @return
     */
    @Transactional
    public int unlockState(String userId) {
        return userDao.unlockState(userId);
    }


    /**
     * 获取问答数
     * @return
     */
    public int countUser(){
        return userDao.countUser();
    }

    /**
     * 根据手机号和密码查询用户（用户登录）
     *
     * @param text
     * @param password
     * @return
     */
    public User findByUsernameOrMobileAndPassword(String text, String password) {
        User user = userDao.findByMobile(text);
        if(user == null){
            user = userDao.findByUsername(text);
        }
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * 用户注册
     *
     * @param param
     */
    public void add(RegisterDTO param) {
        User user = userDao.findByMobile(param.getMobile());
        if(user == null){
            user = new User();
            //判断验证码是否正确
            String syscode = (String) redisTemplate.opsForValue().get("smscode_" + param.getMobile());
            System.out.println("syscode:"+param.getCode());
            //提取系统正确的验证码
            if (syscode == null) {
                throw new RuntimeException("请点击获取短信验证码");
            }
            if (!param.getCode().equals(syscode)) {
                throw new RuntimeException("验证码输入不正确");
            }
            user.setUserId(idWorker.nextId() + "");
            user.setRegisterDate(new Date());//注册日期
            user.setUsername(param.getUsername());
            user.setMobile(param.getMobile());
            user.setSex(param.getSex());
            user.setAge(param.getAge());
            //密码加密
            String newpassword = encoder.encode(param.getPassword());//加密后的密码
            user.setPassword(newpassword);
            user.setState("1");
            user.setPersonImage("1");
            userDao.save(user);
        } else {
            throw new RuntimeException("该手机号已被注册");
        }

    }


    public void sendSms(String mobile) {
        //1.生成6位短信验证码
        Random random = new Random();
        int max = 999999;//最大数
        int min = 100000;//最小数
        int code = random.nextInt(max);//随机生成
        if (code < min) {
            code = code + min;
        }
        System.out.println(mobile + "收到验证码是：" + code);
        //2.将验证码放入redis
        redisTemplate.opsForValue().set("smscode_" + mobile, code + "", 500, TimeUnit.MINUTES);//五分钟过期
        //3.将验证码和手机号发动到rabbitMQ中
        Map<String, String> map = new HashMap();
        map.put("mobile", mobile);
        map.put("code", code + "");
        rabbitTemplate.convertAndSend("sms", map);
    }

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param param
     * @return
     */
    public Page<User> findSearch(SearchListDTO param) {
        Specification<User> specification = createSpecification(param);
        PageRequest pageRequest = PageRequest.of(param.getPage() - 1, param.getSize());
        return userDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
//    public List<User> findSearch(Map whereMap) {
//        Specification<User> specification = createSpecification(whereMap);
//        return userDao.findAll(specification);
//    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param user
     */
//    public void add(param) {
//        User user = new User();
//        user.setUserId(idWorker.nextId() + "");
//        userDao.save(user);
//    }

//    /**
//     * 修改
//     *
//     * @param user
//     */
//    public void update(User user) {
//        userDao.save(user);
//    }
//
//    /**
//     * 删除
//     *
//     * @param id
//     */
//    public void deleteById(String id) {
//        userDao.deleteById(id);
//    }

    /**
     * 动态条件构建
     *
     * @param param
     * @return
     */
    private Specification<User> createSpecification(SearchListDTO param) {

        return new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (param.getUserId() != null && !"".equals(param.getUserId())) {
                    predicateList.add(cb.like(root.get("userId").as(String.class), "%" + param.getUserId() + "%"));
                }
                // 手机号码
                if (param.getMobile() != null && !"".equals(param.getMobile())) {
                    predicateList.add(cb.like(root.get("mobile").as(String.class), "%" + param.getMobile() + "%"));
                }
                // 昵称
                if (param.getUsername() != null && !"".equals(param.getUsername())) {
                    predicateList.add(cb.like(root.get("username").as(String.class), "%" + param.getUsername() + "%"));
                }
                // 性别
                if (param.getSex() != null && !"".equals(param.getSex())) {
                    predicateList.add(cb.like(root.get("sex").as(String.class), "%" + param.getSex() + "%"));
                }
                if (param.getState() != null && !"".equals(param.getState())) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) param.getState() + "%"));
                }
                List<Order> orders = new ArrayList<>();
                orders.add(cb.desc(root.get("registerDate")));
                query.orderBy(orders);
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
