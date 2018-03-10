package com.cp.dao;

import java.sql.PreparedStatement;
import java.util.List;

import com.cp.entity.Dlt;

public class DltDao extends Dao {
	public void updateDltsx(List<Dlt> dlts) {
		String sql = "delete from dltsx";
		try {
			System.out.println("���ڸ������ݿ⡣����");
			PreparedStatement p = conn.prepareStatement(sql);
			p.executeUpdate();
			for (Dlt d : dlts) {
				sql = "insert into dltsx values (?,?,?,?,?,?,?,?,?)";
				p = conn.prepareStatement(sql);
				p.setInt(1, d.getId());
				p.setString(2, d.getDate());
				p.setInt(3, d.getN1());
				p.setInt(4, d.getN2());
				p.setInt(5, d.getN3());
				p.setInt(6, d.getN4());
				p.setInt(7, d.getN5());
				p.setInt(8, d.getB1());
				p.setInt(9, d.getB2());
				p.executeUpdate();
			}
			System.out.println("�������ݿ���ϣ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
