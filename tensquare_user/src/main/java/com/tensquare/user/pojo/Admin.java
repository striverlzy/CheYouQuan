package com.tensquare.user.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类
 * @author liuzhongyu
 *
 */
@Entity
@Table(name="tb_admin")
public class Admin implements Serializable{

	@Id
	private String adminId;//ID
	private String adminname;//登陆名称
	private String password;//密码

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
