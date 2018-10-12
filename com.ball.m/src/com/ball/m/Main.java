package com.ball.m;

import java.util.List;

import com.ball.m.dao.Dao;
import com.ball.m.entity.Ssq;
import com.ball.m.station.BallStation;

public class Main {
	public static void main(String[] args) {
		Dao dao = Dao.getDao();
		List<Ssq> allSsqs = dao.getAllSsq();
		Ssq lastSsq = allSsqs.get(allSsqs.size() - 1);
		List<Ssq> stationSsqs = BallStation.getStation().getSsq(lastSsq.getId() + 1, BallStation.MAX);
		if (stationSsqs.size() > 0) {
			dao.addSsq(stationSsqs);
		}
		for(Ssq ssq:stationSsqs) {
			allSsqs.add(ssq);
		}
		
		
		dao.close();
	}
}
