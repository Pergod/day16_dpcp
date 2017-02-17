package com.itcast.dbcp.demo;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itcast.dbcp.utils.DbcpUtils;

public class Demo3 {
	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			connection=DbcpUtils.getConnection();
			String sql="insert into study (name,password) values (?,?)";
			preparedStatement=connection.prepareStatement(sql);
			ParameterMetaData metaData=preparedStatement.getParameterMetaData();
			System.out.println(metaData.getParameterCount());
			System.out.println(metaData.getParameterType(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbcpUtils.release(resultSet, preparedStatement, connection);
		}
		
	}
}
