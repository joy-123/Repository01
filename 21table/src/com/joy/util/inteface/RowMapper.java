package com.joy.util.inteface;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 行映射的接口,通过传人的ResultSet对象将每一条记录映射成对应的对象
 */
public interface RowMapper<T> {
	/**
	 * 每一行数据要映射的对象
	 *  result          结果集对象
	 * rownum   当前结果集映射的行号
	 * @throws SQLException 
	 */
	public T mappingRow(ResultSet result, int rownum) throws SQLException ;
}

/*
 * //RowMapper<T> mapper=new RowMapperImp<T>();//接口不能被实例化  多态  
 * 这里泛型<T>写什么类型，成员方法mappingRow就返回什么类型，即T为什么类型：<T>=<String> ;public String mappingRow(ResultSet rs, int rownum);
*/
