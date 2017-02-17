package com.itcast.dbcp.demo;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import com.itcast.dbcp.utils.DbcpUtils;

public class Demo4 {
	
	@Test
	public void test1() {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			connection=DbcpUtils.getConnection();
			String sql="select *from users";
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			ResultSetMetaData metaData=resultSet.getMetaData();
			System.out.println(metaData.getColumnCount());
			System.out.println(metaData.getColumnName(1));
			System.out.println(metaData.getColumnName(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbcpUtils.release(resultSet, preparedStatement, connection);
		}
	}
}
