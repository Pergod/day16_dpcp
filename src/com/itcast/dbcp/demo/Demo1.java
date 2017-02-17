package com.itcast.dbcp.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itcast.dbcp.utils.DbcpUtils;

public class Demo1 {
	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			connection=DbcpUtils.getConnection();
			System.out.println(connection.getClass().getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbcpUtils.release(resultSet, preparedStatement, connection);
		}
		
	}
}
