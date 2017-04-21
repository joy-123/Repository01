package com.joy.util.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * Dept实体类 :定义一个java类接收数据库返回的表中的数据
 */
public class Dept implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer dept_id;//?Long
	private String dept_name;
	private Integer parent_dept_id;//?Long
	private String dept_desc;
	private String dept_phone;
	private String dept_address;
	private Long dept_manager_id;//?Long
	private Integer status;
	private Date tv_update;
	
	
	@Override
	public String toString() {
		return "Dept [dept_id=" + dept_id + ", dept_name=" + dept_name
				+ ", parent_dept_id=" + parent_dept_id + ", dept_desc="
				+ dept_desc + ", dept_phone=" + dept_phone + ", dept_address="
				+ dept_address + ", dept_manager_id=" + dept_manager_id
				+ ", status=" + status + ", tv_update=" + tv_update + "]";
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public Integer getParent_dept_id() {
		return parent_dept_id;
	}
	public void setParent_dept_id(Integer parent_dept_id) {
		this.parent_dept_id = parent_dept_id;
	}
	public String getDept_desc() {
		return dept_desc;
	}
	public void setDept_desc(String dept_desc) {
		this.dept_desc = dept_desc;
	}
	public String getDept_phone() {
		return dept_phone;
	}
	public void setDept_phone(String dept_phone) {
		this.dept_phone = dept_phone;
	}
	public String getDept_address() {
		return dept_address;
	}
	public void setDept_address(String dept_address) {
		this.dept_address = dept_address;
	}
	public Long getDept_manager_id() {
		return dept_manager_id;
	}
	public void setDept_manager_id(Long dept_manager_id) {
		this.dept_manager_id = dept_manager_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getTv_update() {
		return tv_update;
	}
	public void setTv_update(Date tv_update) {
		this.tv_update = tv_update;
	}
 
}
