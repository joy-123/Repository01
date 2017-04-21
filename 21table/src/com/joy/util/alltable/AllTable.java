package com.joy.util.alltable;

import java.io.Serializable;
import java.sql.Date;

public class AllTable implements Serializable {

	/**
	 * 一个表一个实体类，不建议这样所有表的实体类放在一起
	 * 当多表联查时可以扩充
	 * java.util.Date(父类)；java.sql.Date（子类）、java.sql.Timestamp（子类）
	 * void java.sql.PreparedStatement.setDate(int parameterIndex, Date x) 方法中的Date x是java.sql.Date 
	   void java.sql.PreparedStatement.setTimestamp(int parameterIndex, Timestamp x)
	   Date java.sql.ResultSet.getDate(int columnIndex)
	   Timestamp java.sql.ResultSet.getTimestamp(int columnIndex)
	 *直接把实体类中java.util.Date 改为java.sql.Date 或者java.sql.Timestamp ；
	 调用设置参数时就都不用转化了    
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//1.部门表 M_DEPT
		private Long dept_id;
		private String dept_name;	
		private Long parent_dept_id;
		private String dept_desc;	
		private String dept_phone;	  
		private String dept_address;	
		private Long dept_manager_id;	
		private Integer status;	
		private Date tv_update;	//import java.sql.Date;
		
		//2用户表 M_USER
		private Long user_id;	
		private String user_name;	
		private String password	;
		//private Integer status;	
		//private Long dept_id;	
		private Long role_id;	
		private Long mobile	;
		private String otelephone;	
		private String htelephone;	
		private String remark	;
		//private Date tv_update;
		
		//3.角色表 M_ROLE
		//private Long role_id;
		private String role_name;	
		//private Integer status	;
		//private String remark;	
		//private Date tv_update	;
	
		//4.菜单功能表 M_MODULE
		//private Long module_id;	
		private String module_name;	
		private Long parent_id;	
		private String module_url;	
		//private Integer status	;
		//private Integer disp_order;	
		//private Date tv_update;	
		
		//5.角色菜单对应表 M_ROLE_MODULE
		//private Long role_id;	
		private Long module_id;	
		//private Date tv_update;	
		
		//6.字典表 M_DICT
		private Long dict_sn;	
		private Long class_id;	
		private Long dict_id;	
		private String dict_descrb;	
		private Integer disp_order;	
		private Long parent_sn;	
		//private Integer status;	
		
		//7.日志表 SYS_LOG
		private Long log_sn;	
		private Integer log_type;	
		private String oper_desc;	
		private Long oper_user_id;	
		private Date oper_tv;
		
		
		
		
		
		
		@Override
		public String toString() {
			return "AllTable [dept_id=" + dept_id + ", dept_name=" + dept_name
					+ ", parent_dept_id=" + parent_dept_id + ", dept_desc="
					+ dept_desc + ", dept_phone=" + dept_phone
					+ ", dept_address=" + dept_address + ", dept_manager_id="
					+ dept_manager_id + ", status=" + status + ", tv_update="
					+ tv_update + ", user_id=" + user_id + ", user_name="
					+ user_name + ", password=" + password + ", role_id="
					+ role_id + ", mobile=" + mobile + ", otelephone="
					+ otelephone + ", htelephone=" + htelephone + ", remark="
					+ remark + ", role_name=" + role_name + ", module_name="
					+ module_name + ", parent_id=" + parent_id
					+ ", module_url=" + module_url + ", module_id=" + module_id
					+ ", dict_sn=" + dict_sn + ", class_id=" + class_id
					+ ", dict_id=" + dict_id + ", dict_descrb=" + dict_descrb
					+ ", disp_order=" + disp_order + ", parent_sn=" + parent_sn
					+ ", log_sn=" + log_sn + ", log_type=" + log_type
					+ ", oper_desc=" + oper_desc + ", oper_user_id="
					+ oper_user_id + ", oper_tv=" + oper_tv + "]";
		}
		public Long getDept_id() {
			return dept_id;
		}
		public void setDept_id(Long dept_id) {
			this.dept_id = dept_id;
		}
		public String getDept_name() {
			return dept_name;
		}
		public void setDept_name(String dept_name) {
			this.dept_name = dept_name;
		}
		public Long getParent_dept_id() {
			return parent_dept_id;
		}
		public void setParent_dept_id(Long parent_dept_id) {
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
		public Long getUser_id() {
			return user_id;
		}
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Long getRole_id() {
			return role_id;
		}
		public void setRole_id(Long role_id) {
			this.role_id = role_id;
		}
		public Long getMobile() {
			return mobile;
		}
		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}
		public String getOtelephone() {
			return otelephone;
		}
		public void setOtelephone(String otelephone) {
			this.otelephone = otelephone;
		}
		public String getHtelephone() {
			return htelephone;
		}
		public void setHtelephone(String htelephone) {
			this.htelephone = htelephone;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getRole_name() {
			return role_name;
		}
		public void setRole_name(String role_name) {
			this.role_name = role_name;
		}
		public String getModule_name() {
			return module_name;
		}
		public void setModule_name(String module_name) {
			this.module_name = module_name;
		}
		public Long getParent_id() {
			return parent_id;
		}
		public void setParent_id(Long parent_id) {
			this.parent_id = parent_id;
		}
		public String getModule_url() {
			return module_url;
		}
		public void setModule_url(String module_url) {
			this.module_url = module_url;
		}
		public Long getModule_id() {
			return module_id;
		}
		public void setModule_id(Long module_id) {
			this.module_id = module_id;
		}
		public Long getDict_sn() {
			return dict_sn;
		}
		public void setDict_sn(Long dict_sn) {
			this.dict_sn = dict_sn;
		}
		public Long getClass_id() {
			return class_id;
		}
		public void setClass_id(Long class_id) {
			this.class_id = class_id;
		}
		public Long getDict_id() {
			return dict_id;
		}
		public void setDict_id(Long dict_id) {
			this.dict_id = dict_id;
		}
		public String getDict_descrb() {
			return dict_descrb;
		}
		public void setDict_descrb(String dict_descrb) {
			this.dict_descrb = dict_descrb;
		}
		public Integer getDisp_order() {
			return disp_order;
		}
		public void setDisp_order(Integer disp_order) {
			this.disp_order = disp_order;
		}
		public Long getParent_sn() {
			return parent_sn;
		}
		public void setParent_sn(Long parent_sn) {
			this.parent_sn = parent_sn;
		}
		public Long getLog_sn() {
			return log_sn;
		}
		public void setLog_sn(Long log_sn) {
			this.log_sn = log_sn;
		}
		public Integer getLog_type() {
			return log_type;
		}
		public void setLog_type(Integer log_type) {
			this.log_type = log_type;
		}
		public String getOper_desc() {
			return oper_desc;
		}
		public void setOper_desc(String oper_desc) {
			this.oper_desc = oper_desc;
		}
		public Long getOper_user_id() {
			return oper_user_id;
		}
		public void setOper_user_id(Long oper_user_id) {
			this.oper_user_id = oper_user_id;
		}
		public Date getOper_tv() {
			return oper_tv;
		}
		public void setOper_tv(Date oper_tv) {
			this.oper_tv = oper_tv;
		}
		
		
		
}
