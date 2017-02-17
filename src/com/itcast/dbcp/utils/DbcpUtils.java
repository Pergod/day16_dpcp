package com.itcast.dbcp.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DbcpUtils {
	
	private static Properties properties=new Properties();
	private static BasicDataSource dataSource=null;
	static{
		BasicDataSourceFactory factory=new BasicDataSourceFactory();
		try {
			properties.load(DbcpUtils.class.getClassLoader().getResourceAsStream("dbcpcofing.properties"));
			dataSource=factory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	public static void release(ResultSet results,Statement statement,Connection connection) {
		if (results!=null) {
			try {
				results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
