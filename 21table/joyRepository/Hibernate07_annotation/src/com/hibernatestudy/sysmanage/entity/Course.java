package com.hibernatestudy.sysmanage.entity;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * 类描述：     测试多对多单向关联
 * 项目名：Hibernate04_relative
 * 类名： com.hibernatestudy.sysmanage.entity.Course
 * 作者： Joy
 * 时间：2017年3月10日 下午5:18:06
 */

@Entity
@Table(name="COURSE")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="COURSE_ID")
	private Integer courseId;
	@Basic
	@Column(name="COURSE_NAME")
	private String courseName;	
	
	//*多对多的单向关联 ;用Course维护
	//多对多双向关联的注解，必须要求有一方放弃维护权
	//否则，会生成两个中间表cascade=CascadeType.ALL)  
	//mappedBy="courseSet"写在Course类中表示Course放弃维护权，把courseSet(另一表中对应的属性名)交给另一方维护
	@ManyToMany(targetEntity=Student.class,cascade=CascadeType.ALL,mappedBy="courseSet")
	private Set<Student> studentSet;
	
	public Set<Student> getStudentSet() {
		return studentSet;
	}

	public void setStudentSet(Set<Student> studentSet) {
		this.studentSet = studentSet;
	}
	
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	
	
}
