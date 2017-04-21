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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 类描述：
 * 项目名：Hibernate04_relative
 * 类名： com.hibernatestudy.sysmanage.entity.Dept
 * 作者： Joy
 * 时间：2017年3月10日 下午4:20:39
 */
@Entity
@Table(name="DEPT")
public class Dept {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="DEPT_ID")
	private Integer deptId;
	@Basic
	@Column(name="DEPT_NAME")
	private String deptName;
	
	//站在一对多的角度(Dept作为一对多的一方时);
	@OneToMany(targetEntity=Dept.class,cascade=CascadeType.ALL)
	@JoinColumn(name="PARENT_DEPT_ID")
	private Set<Dept> childDeptSet;
		
	//站在多对一的角度(Dept作为多对一的多方时)：
	@ManyToOne(targetEntity=Dept.class,cascade=CascadeType.ALL)
	@JoinColumn(name="PARENT_DEPT_ID")
	private Dept parentDept;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<Dept> getChildDeptSet() {
		return childDeptSet;
	}

	public void setChildDeptSet(Set<Dept> childDeptSet) {
		this.childDeptSet = childDeptSet;
	}

	public Dept getParentDept() {
		return parentDept;
	}

	public void setParentDept(Dept parentDept) {
		this.parentDept = parentDept;
	}
	
	
	
	
	
	
}
