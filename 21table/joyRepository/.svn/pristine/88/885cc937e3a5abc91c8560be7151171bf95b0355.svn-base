package com.hibernatestudy.sysmanage.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类描述：
 * 项目名：Hibernate07_annotation
 * 类名： com.hibernatestudy.sysmanage.entity.User
 * 作者： Joy
 * 时间：2017年3月14日 下午4:46:40
 * 
 *  如果要用native等主键生成策略，如下： 
 * @GeneratedValue(generator="hibS")
   @GenericGenerator(name="hibS",strategy="native")
 */
@Entity //声明是持久化对象
@Table(name="T_USER")  //该持久化对象映射的表
public class User {
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE) //主键生成策略
	@Column(name="USER_ID") //属性与表字段的映射
	private Integer userId;
	@Basic//表明当前属性将持久化到数据库中  默认可以不写
	@Column(name="USER_NAME")
	private String userName;
	@Basic
	@Column(name="USER_AGE")
	private Integer userAge;
	@Transient //表明当前属性不写入到数据库中
	private String userSex;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	
	
}
