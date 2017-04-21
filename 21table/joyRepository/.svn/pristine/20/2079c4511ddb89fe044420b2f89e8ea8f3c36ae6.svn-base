package com.hibernatestudy.sysmanage.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


/**
 * 类描述：
 * 项目名：Hibernate04_relative
 * 类名： com.hibernatestudy.sysmanage.entity.Area
 * 作者： Joy
 * 时间：2017年3月10日 下午2:36:04
 */
@Entity
@Table(name="AREA")
public class Area {
	@Id
	@GeneratedValue(generator="idStr")
	@GenericGenerator(name="idStr",strategy="native")//如果要用native等主键生成策略
	@Column(name="AREA_ID") 
	private Integer areaId;
	@Basic
	@Column(name="AREA_NAME")
	private String areaName;
		
	//*2、多对一的单向关联 ;Country对象
	//@Transient  //一对多的单向关联时country属性要删除或者，@Transient注解否则报错建列
	@ManyToOne(targetEntity=Country.class,cascade=CascadeType.ALL)
	@JoinColumn(name="A_COUNTRY_ID")
	private Country country;
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
	
	
}
