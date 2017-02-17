package com.itcast.dbcp.demo;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler implements ResultSetHandler{
	
	private Class clazz;
	public BeanListHandler(Class clazz) {
		this.clazz=clazz;
	}
	
	@Override
	public Object Handle(ResultSet resultSet) {
		List list=new ArrayList<>();
		try {
			while (resultSet.next()) {
				Object bean=clazz.newInstance();
				ResultSetMetaData metaData=resultSet.getMetaData();
				for(int i=0;i<metaData.getColumnCount();i++){
					//��ȡ����
					String columnName=metaData.getColumnName(i+1);
					//��ȡ������Ӧ��ֵ
					Object value=resultSet.getObject(columnName);
					
					//����bean�����������Ӧ������
					Field field=bean.getClass().getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(bean, value);
				}
				list.add(bean);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

}
