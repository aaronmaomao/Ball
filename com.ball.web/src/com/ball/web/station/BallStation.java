package com.ball.web.station;

import java.util.List;

import com.ball.web.entity.Ssq;

public class BallStation {
	private static BallStation station;
	private static final String ssqUrl = "http://datachart.500.com/ssq/history/newinc/outball.php?start=00000&end=99999";

	public static final int MAX = 99999;

	private BallStation() {

	}
	
	public static synchronized BallStation getStation() {
		if(station==null) {
			station = new BallStation();
		}
		return station;
	}

	public List<Ssq> getSsq(int start, int end) {
		String url = ssqUrl.replace("00000", String.format("%05d", start)).replace("99999", String.format("%05d", end));
		String data = HttpClient.doGet(url);
		List<Ssq> ssqs = ParseData.parseSsq(data);
		return ssqs;
	}
}
