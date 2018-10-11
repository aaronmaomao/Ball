package com.ball.m.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ball.m.entity.Ssq;

public class Dao {
	private static Dao dao;
	private Connection conn;

	private static final String db_name = "jdbc:mysql://43.226.43.163:3306/mysql1810114b73_db";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String user_name = "mysql1810114b73";
	private static final String user_passwd = "4Sb730fb76";

	private Dao() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(db_name, user_name, user_passwd);
			System.out.println("数据库连接成功。。。");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
		}
	}

	public static synchronized Dao getDao() {
		try {
			if (dao == null || dao.getConn().isClosed()) {
				dao = new Dao();
			}
			return dao;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Connection getConn() {
		return conn;
	}

	public List<Ssq> getAllSsq() {
		String sql = "select * from ssq order by id asc";
		List<Ssq> ssqs = new ArrayList<>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				Date date = resultSet.getDate(2);
				int r1 = resultSet.getInt(3);
				int r2 = resultSet.getInt(4);
				int r3 = resultSet.getInt(5);
				int r4 = resultSet.getInt(6);
				int r5 = resultSet.getInt(7);
				int r6 = resultSet.getInt(8);
				int b = resultSet.getInt(9);
				Ssq ssq = new Ssq(id, date, r1, r2, r3, r4, r5, r6, b);
				ssqs.add(ssq);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ssqs;

	}

	public void addSsq(List<Ssq> ssqs) {
		for (Ssq ssq : ssqs) {
			String sql = "insert into ssq values (?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, ssq.getId());
				statement.setDate(2, new Date(ssq.getDate().getTime()));
				statement.setInt(3, ssq.getR1());
				statement.setInt(4, ssq.getR2());
				statement.setInt(5, ssq.getR3());
				statement.setInt(6, ssq.getR4());
				statement.setInt(7, ssq.getR5());
				statement.setInt(8, ssq.getR6());
				statement.setInt(9, ssq.getB());
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		try {
			if (!conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
