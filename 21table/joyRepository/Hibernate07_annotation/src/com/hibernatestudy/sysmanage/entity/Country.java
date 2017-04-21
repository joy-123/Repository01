package com.hibernatestudy.sysmanage.entity;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 类描述：
 * 项目名：Hibernate07_annotation
 * 类名： com.hibernatestudy.sysmanage.entity.Country
 * 作者： Joy
 * 时间：2017年3月14日 下午4:32:41
 */
@Entity
@Table(name="COUNTRY")
public class Country {
	@Id
	@GeneratedValue(generator="idStr")
	@GenericGenerator(name="idStr",strategy="native")//如果要用native等主键生成策略
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	@Basic
	@Column(name="COUNTRY_NAME")
	private String countryName;
	
	//*1、 一对多的单向关联：Set<Area>   一个Country对象中可有很多 area对象
	//注解配置了mapped属性，代表这方放弃维护权，同时将joinColumn注解去掉,把country(另一表中对应的属性名)交给另一方维护
	//country放弃了维护权后，session.save(country)时会报错
	@OneToMany(targetEntity=Area.class,cascade=CascadeType.ALL)//,mappedBy="country"
	@JoinColumn(name="A_COUNTRY_ID")
	private Set<Area> areaSet;
	
	public Set<Area> getAreaSet() {
		return areaSet;
	}
	public void setAreaSet(Set<Area> areaSet) {
		this.areaSet = areaSet;
	}
	

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
	
	
	
	
	
}
