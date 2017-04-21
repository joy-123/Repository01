package com.joy.util.alltable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joy.util.JdbcTemplate;
import com.joy.util.bean.Dept;
import com.joy.util.inteface.RowMapper;

/**封装增删改  &连接ConnDataScoure——##AllTable实体类 ——&模板类JdbcTemplate——DeptDaoTemplate
 * 封装查询方法（高级——）    &接口 RowMapper<T>——&模板类JdbcTemplate——DeptDaoTemplate（参数，内部类 重写方法）
 * 带&的重复利用，不用单独再写，直接调用即可
 * 本例子中带##的 也可以重复用(一个表一个实体类，不建议这样所有表的实体类放在一起)
 * java.util.Date(父类)；java.sql.Date（子类）、java.sql.Timestamp（子类）
 	直接把实体类中时间 设为java.sql.Date 或者java.sql.Timestamp ；
	 调用设置参数时就都不用转化了    
	 * java.sql.Timestamp(long time) 
          使用毫秒时间值构造 Timestamp 对象。
 *java.sql.Date(long date) 
		使用给定毫秒时间值构造一个 Date 对象。  
 *java.util.Date() 
          分配 Date 对象并初始化此对象，以表示分配它的时间（精确到毫秒）。   
  Date(long date) 
          分配 Date 对象并初始化此对象，以表示自从标准基准时间（称为“历元（epoch）”，即 1970 年 1 月 1 日 00:00:00 GMT）以来的指定毫秒数。 
//System.currentTimeMillis(),返回以毫秒为单位的当前时间
 * 异常要try catch
 * DeptDaoTemplate
 1、增改方法： String sql，Object[] params ————————调用AllTable对象，调用该成员方法返回的是int
 2、删 方法  ：参数，String sql，Object[] params ————————调用该成员方法返回的是boolean
 3、查 方法：参数，String sql，Object[] params，重写方法（本行记录存入AllTable对象中）——————调用该成员方法返回的是List<AllTable>
 */
public class TableDaoTemplate extends JdbcTemplate{//继承模板类JdbcTemplate

	
	// 1.2方法基本一样
	//1 增    增加一条M_DEPT表记录   一条
	public int addTable(AllTable tables) {
		/*//添加数据
		String insertsql="INSERT INTO M_DEPT(DEPT_ID,DEPT_NAME,PARENT_DEPT_ID,STATUS)VALUES(?,?,?,?)";
		//参数
		Object[] params=new Object[]{tables.getDept_id() ,tables.getDept_name(),
				tables.getParent_dept_id(),tables.getStatus()};  */ 
		
		String insertsql="INSERT INTO M_USER(USER_ID,USER_NAME,DEPT_ID,ROLE_ID)VALUES(?,?,?,?)";
		Object[] params=new Object[]{tables.getUser_id() ,tables.getUser_name(),
				tables.getDept_id(),tables.getRole_id()}; 
		return this.updateTemplate(insertsql, params);	//调用父类的方法，返回int rows
	}

	//2改 修改一条M_DEPT表记录
	public int updateTable(AllTable tables) {
			//修改数据
			String updatesql="UPDATE M_DEPT SET DEPT_NAME = ? ,STATUS = ? WHERE DEPT_ID=?";
			Object[] params=new Object[]{tables.getDept_name(),tables.getStatus(),tables.getDept_id()};
			return this.updateTemplate(updatesql, params);
	}

	//*3删  删除M_DEPT表一条记录
	public boolean delTable(int dept_id){//一般一个参数 主键即可//dept_id
		boolean flag=false;
		int rows=0;
		
		String deletesql="DELETE FROM M_DEPT WHERE dept_id=?";//dept_id
		Object[] params=new Object[]{dept_id};//dept_id
		
		rows=this.updateTemplate(deletesql, params);
		if(rows>0){
			flag=true;
		}
		return flag;
	}
	//* 4封装查询方法（高级——）
	public List<AllTable> selecttable(long role_id){//(int parent_deptno_id)
		List<AllTable> listtable=new ArrayList<AllTable>();
		//多表联查
		String sql="SELECT US.USER_ID,US.USER_NAME,DE.DEPT_NAME FROM M_DEPT DE,M_USER US WHERE DE.DEPT_ID=US.DEPT_ID AND US.ROLE_ID=?";
		Object[] params=new Object[]{role_id};
		listtable= this.queryForList(new RowMapper<AllTable>(){
			public AllTable mappingRow(ResultSet result, int rownum) throws SQLException{
				AllTable tables=new AllTable();
				tables.setUser_id(result.getLong("USER_ID"));//大小写均可，数据库不区分大小写(除了字符类型的)
				tables.setUser_name(result.getString("USER_NAME"));
				tables.setDept_name(result.getString("DEPT_NAME"));
				return tables;
			}
		}
		 , sql, params);
		return listtable;
	}
	
	
	
	public static void main(String[] args){
		//1测试addTable()
//		int rows=0;
//		TableDaoTemplate tabledao=new TableDaoTemplate();
//		AllTable table=new AllTable();
//		table.setUser_id(200004L);
//		table.setUser_name("RR");
//		table.setDept_id(21L);
//		table.setRole_id(302L);
//		rows=tabledao.addTable(table);
//		System.out.println("rows="+rows);
		//tables.getUser_id(200001L) ,tables.getUser_name("QQ"),tables.getDept_id(2),tables.getRole_id(301)

		//2测试updateTable()
//		int rows=0;
//		TableDaoTemplate tabledao=new TableDaoTemplate();
//		AllTable table=new AllTable();
//		table.setDept_id(2L);
//		table.setDept_name("制造总部");
//		table.setStatus(0);
//		rows=tabledao.updateTable(table);
//		System.out.println("rows="+rows);
		
		//3测试delTable()
//		TableDaoTemplate tabledao=new TableDaoTemplate();
//		System.out.println(tabledao.delTable(1));
		
		//4测试selecttable()
		TableDaoTemplate tabledao=new TableDaoTemplate();
		List<AllTable> listtable=new ArrayList<AllTable>();
		listtable=tabledao.selecttable(302);
		if(listtable!=null && listtable.size()>0){//先判断，严谨
			for (AllTable table : listtable) {//循环集合
				System.out.println(table);
				System.out.println(table.getUser_id()+"==="+table.getUser_name()+"==="+table.getDept_name());
			}
		}
		
		
	}

}
