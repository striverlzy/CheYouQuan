package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    public User findByMobile(String mobile);

    @Query(value = "select count(*) from tb_user", nativeQuery = true)
    public int countUser();


    @Query(value = "select mobile from tb_user", nativeQuery = true)
    public List getMobileList();

    @Query(value = "select username from tb_user", nativeQuery = true)
    public List getUserNameList();


    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    public User findByUsername(String username);

    // 加入黑名单
    @Modifying
    @Query(value = "update tb_user set state='0' where user_id=?1", nativeQuery = true)
    public int lockState(String userId);

    // 解封
    @Modifying
    @Query(value = "update tb_user set state='1' where user_id=?1", nativeQuery = true)
    public int unlockState(String userId);

}
