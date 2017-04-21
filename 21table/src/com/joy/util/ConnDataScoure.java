package com.joy.util;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnDataScoure {
	private static ComboPooledDataSource datasource;
	static{
		
		try {
			Properties pp=new Properties();//创建属性对象   集合
			ClassLoader c=ConnDataScoure.class.getClassLoader();
			InputStream is=c.getResourceAsStream("jdbc.properties");
		
			pp.load(is);// 从输入流中读取属性列表
			is.close();//关闭流？
	
			datasource=new ComboPooledDataSource();
			datasource.setDriverClass(pp.getProperty("OracleDriver"));
			datasource.setJdbcUrl(pp.getProperty("url"));
			datasource.setUser(pp.getProperty("user"));
			datasource.setPassword(pp.getProperty("password"));
			//System.out.println("开始尝试连接Oracle数据库！");
			
			//设置连接池对象的属性(有缺省（即默认值）不设置时就默认)
			// 设置初始化连接数量（第一次访问的时候连接数量为5）
			datasource.setInitialPoolSize(5);
			// 设置连接池每次增加连接的数量（连接数量不够时，自动增加3个连接）
			datasource.setAcquireIncrement(3);
			// 设置连接池最大连接数量（连接数量最多为）
			datasource.setMaxPoolSize(50);
			// 设置连接池最小连接数量（连接有闲置时，会自动关闭一些，最少保留3个）
			datasource.setMinPoolSize(3);
			// 设置闲置连接最大存活时间（有闲置连接时，200内没有访问，则会关闭一个连接。。。。）
			datasource.setMaxIdleTime(200);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static Connection getConnec(){
		Connection con=null;
		try {
			con=datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return con;
	}
	
	
	public static void closeConnnection(Connection con, Statement st,ResultSet result){
		try {
			if(result!=null){
				result.close();
			}
			if(st!=null){
				st.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	

}
