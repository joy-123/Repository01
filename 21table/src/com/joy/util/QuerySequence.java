package com.joy.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class QuerySequence {

	/*
	 *、获取序列（必须先保证数据库中的序列无误，才能在java中调用）
	 * 在数据库中查看序列Sequence-- 查询当前序列号：SELECT SYS_SEQUENCE.NEXTVAL FROM DUAL;
	 * 声明返回值-->java程序连接数据库（数据库连接con、预编译语句对象pre、sql、结果集result、关闭对象）--》返回数据
	 */
	
	public static long getSequence(){
		long seq=0;//返回值seq
		Connection con=null;
		PreparedStatement pre=null;//创建预编译语句对象
		ResultSet rseq=null;// 创建一个结果集对象
		con=ConnDataScoure.getConnec();
		String sql="SELECT SYS_SEQUENCE.NEXTVAL FROM DUAL";
		try {
			pre=con.prepareStatement(sql);
			rseq=pre.executeQuery();
			while(rseq.next()){
				seq=rseq.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return seq;
	}
		
	public static void main(String[] args) {
		System.out.println(QuerySequence.getSequence());

	}

}
