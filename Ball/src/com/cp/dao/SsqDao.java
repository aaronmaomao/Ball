package com.cp.dao;

import java.sql.PreparedStatement;
import java.util.List;

import com.cp.entity.Ssq;

public class SsqDao extends Dao {
	public void updateSsqsx(List<Ssq> ssqs) {
		String sql = "delete from ball";
		try {
			System.out.println("正在更新数据库。。。");
			PreparedStatement p = conn.prepareStatement(sql);
			p.executeUpdate();
			int i = 0;
			conn.setAutoCommit(false);
			sql = "insert into ball values (?,?,?,?,?,?,?,?,?)";
			p = conn.prepareStatement(sql);
			for (Ssq d : ssqs) {
				System.out.println(d);
				p.setInt(1, d.getId());
				p.setString(2, d.getDate());
				p.setInt(3, d.getR1());
				p.setInt(4, d.getR2());
				p.setInt(5, d.getR3());
				p.setInt(6, d.getR4());
				p.setInt(7, d.getR5());
				p.setInt(8, d.getR6());
				p.setInt(9, d.getB());
				i++;
				p.addBatch();
				if (i == 1000) {
					i = 0;
					p.executeBatch();
				}
			}
			if (i != 0) {
				p.executeBatch();
			}
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("插入数据库完毕！");
			p.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
