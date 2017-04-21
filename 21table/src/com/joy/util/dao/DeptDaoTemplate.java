package com.joy.util.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joy.util.JdbcTemplate;
import com.joy.util.bean.Dept;
import com.joy.util.inteface.RowMapper;

/**封装增删改  &连接ConnDataScoure——Dept实体类 ——&模板类JdbcTemplate——DeptDaoTemplate
 * 封装查询方法（高级——）    &接口 RowMapper<T>——&模板类JdbcTemplate——DeptDaoTemplate（参数，内部类 重写方法）
 * 带&的重复利用，不用单独再写，直接调用即可
 */
public class DeptDaoTemplate extends JdbcTemplate{//继承模板类JdbcTemplate

	
	// 1.3方法基本一样
	//1 增    增加一条M_DEPT表记录   一条
	public int addDept(Dept dept) {
		//添加数据
		String insertsql="INSERT INTO M_DEPT(DEPT_ID,DEPT_NAME,PARENT_DEPT_ID,STATUS)VALUES(?,?,?,?)";
		//参数
		Object[] params=new Object[]{dept.getDept_id() ,dept.getDept_name(),
				dept.getParent_dept_id(),dept.getStatus()};                                                               
		return this.updateTemplate(insertsql, params);	//调用父类的方法，返回int rows
	}

	//3 改 修改一条M_DEPT表记录
	public int updateDept(Dept dept) {
			//修改数据
			String updatesql="UPDATE M_DEPT SET DEPT_NAME = ? ,STATUS = ? WHERE DEPT_ID=?";
			Object[] params=new Object[]{dept.getDept_name(),dept.getStatus(),dept.getDept_id()};
			return this.updateTemplate(updatesql, params);
	}

	//*2删  删除M_DEPT表一条记录
	public boolean delDept(int dept_id){//一般一个参数 主键即可
		boolean flag=false;
		int rows=0;
		
		//删除数据
		String deletesql="DELETE FROM M_DEPT WHERE dept_id=?";
		Object[] params=new Object[]{dept_id};
		rows=this.updateTemplate(deletesql, params);
		if(rows>0){
			flag=true;
		}
		return flag;
	}
	//* 封装查询方法（高级——）
	public List<Dept> selectDept(int parent_dept_id){
		List<Dept> listdept=new ArrayList<Dept>();
		String sql="SELECT DEPT_ID,DEPT_NAME,PARENT_DEPT_ID,STATUS FROM M_DEPT WHERE PARENT_DEPT_ID=?";
		Object[] params=new Object[]{parent_dept_id};
		listdept= this.queryForList(new RowMapper<Dept>(){
			public Dept mappingRow(ResultSet result, int rownum) throws SQLException{
				Dept dept=new Dept();
				dept.setDept_id(result.getInt("dept_id"));//大小写均可，数据库不区分大小写(除了字符类型的)
				dept.setDept_name(result.getString("DEPT_NAME"));
				dept.setStatus(result.getInt("STATUS"));
				return dept;
			}
		}
		 , sql, params);
		return listdept;
	}
	
	
	
	public static void main(String[] args){
//		int rows=0;
//		DeptDaoTemplate deptdao=new DeptDaoTemplate();
//		Dept dept=new Dept();
//		dept.setDept_id(22);
//		dept.setDept_name("制造部22");
//		dept.setParent_dept_id(2);
//		dept.setStatus(1);
//		rows=deptdao.addDept(dept);
//		System.out.println("rows="+rows);
		
//		int rows=0;
//		DeptDaoTemplate deptdao=new DeptDaoTemplate();
//		Dept dept=new Dept();
//		dept.setDept_id(2);
//		dept.setDept_name("制造总部");
//		dept.setStatus(1);
//		rows=deptdao.updateDept(dept);
//		System.out.println("rows="+rows);
		
//		DeptDaoTemplate deptdao=new DeptDaoTemplate();
//		System.out.println(deptdao.delDept(1));
		
		DeptDaoTemplate deptdao=new DeptDaoTemplate();
		List<Dept> listdept=new ArrayList<Dept>();
		listdept=deptdao.selectDept(2);
		for (Dept dept : listdept) {
			System.out.println(dept);
			// System.out.println(dept.getDept_id()+"==="+dept.getDept_name()+"==="+dept.getStatus());
		}
		
	}

}
