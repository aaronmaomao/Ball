package com.ball.web.biz;

import java.sql.SQLException;
import java.util.List;

import com.ball.web.dao.Dao;
import com.ball.web.entity.Ssq;
import com.ball.web.station.BallStation;

public class Server {
	public static List<Ssq> updateAllSsq() throws SQLException {
		Dao dao = Dao.getDao();
		System.out.println("获取数据库记录...");
		List<Ssq> allSsqs = dao.getAllSsq();
		System.out.println("获取最新数据。。。");
		Ssq lastSsq = allSsqs.get(allSsqs.size() - 1);
		List<Ssq> stationSsqs = BallStation.getStation().getSsq(lastSsq.getId() + 1, BallStation.MAX);
		System.out.println("更新数据库。。。");
		if (stationSsqs.size() > 0) {
			dao.addSsq(stationSsqs);
		}
		for (Ssq ssq : stationSsqs) {
			allSsqs.add(ssq);
		}
		dao.close();
		return allSsqs;
	}
}
