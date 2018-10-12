package com.ball.m;

import java.util.List;

import com.ball.m.dao.Dao;
import com.ball.m.entity.Ssq;
import com.ball.m.server.Server;
import com.ball.m.station.BallStation;

public class Main {
	public static void main(String[] args) {
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
		System.out.println("启动服务。。。");
		Server server = Server.start();
	}
}
