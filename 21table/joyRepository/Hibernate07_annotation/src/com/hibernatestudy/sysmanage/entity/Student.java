package com.hibernatestudy.sysmanage.entity;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * 类描述：学生信息的持久化对象		 测试多对多单向关联
 * 项目名：Hibernate04_relative
 * 类名： com.hibernatestudy.sysmanage.entity.Student
 * 作者： Joy
 * 时间：2017年3月10日 下午5:17:54
 */
@Entity
@Table(name="STUDENT")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ST_ID")
	private Integer stId;
	@Basic
	@Column(name="ST_NAME")
	private String stName;
	
	//*多对多的单向关联 ;用Student维护
	//多对多双向关联的注解，必须要求有一方放弃维护权
	//否则，会生成两个中间表cascade=CascadeType.ALL)
	@ManyToMany(targetEntity=Course.class,cascade=CascadeType.ALL)
	private Set<Course> courseSet;
	
	public Set<Course> getCourseSet() {
		return courseSet;
	}
	public void setCourseSet(Set<Course> courseSet) {
		this.courseSet = courseSet;
	}

	
	public Integer getStId() {
		return stId;
	}

	public void setStId(Integer stId) {
		this.stId = stId;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}


	
	
}
