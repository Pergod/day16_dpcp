package com.itcast.dbcp.demo;

import java.sql.ResultSet;

public interface ResultSetHandler {
	public Object Handle(ResultSet resultSet);
}
