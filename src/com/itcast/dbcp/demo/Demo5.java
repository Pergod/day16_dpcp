package com.itcast.dbcp.demo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.itcast.dbcp.utils.DbcpUtilsEx;
import com.itcast.domain.User;

public class Demo5 {
	
	@Test
	public void test1() throws ParseException, SQLException {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date date=format.parse("1309-08-03");
		java.sql.Date birthday=new java.sql.Date(date.getTime());
		User user=new User("1","÷Ó∏¡¡","123","zhugeliang@163.com",birthday,"ÿ©œ‡");
		add(user);
	}
	
	@Test
	public void test2() throws ParseException, SQLException {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date date=format.parse("1309-08-03");
		java.sql.Date birthday=new java.sql.Date(date.getTime());
		User user=new User("1","÷Ó∏¡¡","123","zhugeliang@163.com",birthday,"ÿ©œ‡");
		delete(user.getId());
	}

	@Test
	public void test3() throws ParseException, SQLException {
		User user=find("1");
		System.out.println(user.getNickname());
	}
	
	@Test
	public void test4() throws ParseException, SQLException {
		List<User> users=getAll();
		for (User user : users) {
			System.out.println(user.getId());
		}
	}
	
	public void add(User user) throws SQLException{
		String sql="insert into users (id,username,password,email,birthday,nickname) values(?,?,?,?,?,?)";
		Object[] params={user.getId(),user.getUsername(),user.getPassword(),user.getEmail(),user.getBirthday(),user.getNickname()};
		DbcpUtilsEx.update(sql, params);
	}
	
	public void delete(String id) throws SQLException{
		String sql="delete from users where id=?";
		Object[] params={id};
		DbcpUtilsEx.update(sql, params);
	}
	public User find(String id) throws SQLException{
		String sql="select *from users where id=?";
		Object[] params={id};
		return (User)DbcpUtilsEx.Query(sql, params, new BeanHandler(User.class));
	}
	
	public List getAll() throws SQLException{
		String sql="select *from users";
		Object[] params={};
		return (List) DbcpUtilsEx.Query(sql, params, new BeanListHandler(User.class));
	}
}
