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
				//��ȡ����
				String columnName=metaData.getColumnName(i+1);
				//��ȡ������Ӧ��ֵ
				Object value=resultSet.getObject(columnName);
				
				//����bean�����������Ӧ������
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
