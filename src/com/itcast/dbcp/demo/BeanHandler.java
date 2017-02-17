package com.itcast.dbcp.demo;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class BeanHandler implements ResultSetHandler{
	
	private Class clazz;
	public BeanHandler(Class clazz) {
		this.clazz=clazz;
	}
	
	@Override
	public Object Handle(ResultSet resultSet) {
		try {
			if (!resultSet.next()) {
				return null;
			}
			Object bean=clazz.newInstance();
			ResultSetMetaData metaData=resultSet.getMetaData();
			int count=metaData.getColumnCount();
			for(int i=0;i<count;i++){
				//获取列名
				String columnName=metaData.getColumnName(i+1);
				//获取列名对应的值
				Object value=resultSet.getObject(columnName);
				
				//反射bean上与列名相对应的属性
				Field field=bean.getClass().getDeclaredField(columnName);
				field.setAccessible(true);
				field.set(bean, value);
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
