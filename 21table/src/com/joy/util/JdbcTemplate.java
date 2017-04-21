package com.joy.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joy.util.inteface.RowMapper;

/**模板类
 * （2）封装增删改

 * （4）封装查询方法（高级——Spring）
 *封装增删改：  &连接ConnDataScoure——Dept实体类 ——&模板类JdbcTemplate——DeptDaoTemplate
 * 封装查询方法：    &接口 RowMapper<T>——&模板类JdbcTemplate——DeptDaoTemplate（参数，内部类 重写方法）
 * 带&的重复利用，不用单独再写，直接调用即可
 */ 
 
public class JdbcTemplate {
	/**封装增删改
	 */
	public int updateTemplate(String sql,Object[] params){
		//返回值
		int rows=0;
		//建立连接
		Connection con=null;
		PreparedStatement pre=null;
		//ResultSet result=null;//不用
		
		//连接数据库
		con=ConnDataScoure.getConnec();
		try {
			//预编译对象
			pre=con.prepareStatement(sql);
			//设置参数
			setPams(params, pre);
			//执行
	 		rows=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			ConnDataScoure.closeConnnection(con, pre, null);//无result
		}
		//返回值
		return rows;
	}

	//循环设置参数   把代码抽取为setPams方法
	public void setPams(Object[] params, PreparedStatement pre)
			throws SQLException {
		if(params!=null){
			for (int i = 0; i < params.length; i++) {
				pre.setObject(i+1,params[i]);//赋值从1开始，数组从0开始
				//不确定类型pre.setObject(i+1,params[i]);result.getObject(i+1);
			}	
		}
	}
	
	/*
	 * 封装查询方法（高级——Spring）
	 * <T> List<T>:可以理解为一个类型的声明,List<T>是我新指定的一个类型   相当于int a;
	 * */
	public <T> List<T> queryForList(RowMapper<T> mapper,String sql,Object[] params){
		List<T> listtable=new ArrayList<T>();
		
		Connection con=null;
		PreparedStatement pre=null;
		ResultSet result=null;
		con=ConnDataScoure.getConnec();
		
		try {
			pre=con.prepareStatement(sql);
			setPams(params, pre);
			result=pre.executeQuery();
			int rownum=0;
			while(result.next()){
				rownum++;
				listtable.add(mapper.mappingRow(result, rownum));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDataScoure.closeConnnection(con, pre, result);
		}
		return listtable;
		
	}	
	
	
}
