package com.cp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String NAME = "root";
	private static final String PASSWD = "admin";

	protected Connection conn = null;
	protected PreparedStatement state = null;

	public Dao() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", NAME, PASSWD);
			System.out.println("数据库连接成功。。。");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (state != null)
				state.close();
			if (conn != null)
				conn.close();
			System.out.println("数据库已断开！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Dao baseDao = new Dao();
		baseDao.close();
	}
	
}
