package com.WebChat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class BaseDao {
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost/test";
	private final static String SID = "root";
	private final static String PWD = "********"; //whatever your pwd is.

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("加载驱动失败：" + DRIVER);
		}
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, SID, PWD);
		} catch (SQLException e) {
			System.err.println("建立连接失败：" + e.getMessage());
		}
		return connection;
	}

	public int update(String sql, String[] args) {
		int i = -1;
		Connection connection = null;
		PreparedStatement ps = null;
		connection = getConnection();
		if (connection == null) {
			return i;
		}
		try {
			ps = connection.prepareStatement(sql);
			if (args == null || args.length == 0) {

			} else {
				for (int j = 0; j < args.length; j++) {
					ps.setString(j + 1, args[j]); // 将args中的字符串嵌入sql语句中的问号位置
				}
			}
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("执行Update 失败：" + sql);
			System.err.println("执行Update 失败：" + e.getMessage());
		} finally { // finally不论在什么情况下都会执行，会在return语句之前执行，所以try、catch中有多个return语句的话，就不用在每个return语句之前写.close()语句了
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	public Result query(String sql, String[] args) {
		Result result = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		try {
			connection = getConnection();
			if (connection == null) {
				return result;
			}
			ps = connection.prepareStatement(sql);
			if (args == null || args.length == 0) {
			} else {
				for (int j = 0; j < args.length; j++) {
					ps.setString(j + 1, args[j]); // 将args中的字符串嵌入sql语句中的问号位置
				}
			}
			rs = ps.executeQuery();
			result = ResultSupport.toResult(rs);
		} catch (SQLException e) {
			System.err.println("执行Query失败：" + sql);
			System.err.println("执行Query失败：" + e.getMessage());
		} finally { // finally不论在什么情况下都会执行，会在return语句之前执行，所以try、catch中有多个return语句的话，就不用在每个return语句之前写.close()语句了
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				System.err.println("数据库关闭失败："+e.getMessage());
			}
		}
		return result;
	}
}
