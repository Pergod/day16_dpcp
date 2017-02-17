package com.itcast.dbcp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import com.itcast.dbcp.demo.ResultSetHandler;

public class DbcpUtilsEx {
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
	
	//ÀýÈç: String sql="insert into users values (name,password) values (?,?)"
	public static void update(String sql,Object[] params) throws SQLException {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			connection=getConnection();
			statement=connection.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			statement.executeUpdate();
		} catch (Exception e) {
			
		}finally {
			release(resultSet, statement, connection);
		}
	}
	
	//²éÕÒ
	public static Object Query(String sql,Object[] params,ResultSetHandler handler) throws SQLException {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		
		try {
			connection=getConnection();
			statement=connection.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			resultSet=statement.executeQuery();
			return handler.Handle(resultSet);
		}finally {
			release(resultSet, statement, connection);
		}
	}
}
