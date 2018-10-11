package com.ball.m.station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ball.m.entity.Ssq;

public class ParseData {

	public static List<Ssq> parseSsq(String data) {
		List<Ssq> ssqs = new ArrayList<Ssq>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String dltStrs[] = data.split("<\\/tr>");
			for (String dltStr : dltStrs) {
				String dlss[] = dltStr.split("<\\/td>");
				if (dlss.length <= 1)
					break;
				for (int i = 0; i < dlss.length; i++) {
					dlss[i] = dlss[i].substring(dlss[i].lastIndexOf(">") + 1, dlss[i].length());
				}

				int id = Integer.parseInt(dlss[0]);
				Date date = sdf.parse(dlss[1]);

				int r1 = Integer.parseInt(dlss[2]);
				int r2 = Integer.parseInt(dlss[3]);
				int r3 = Integer.parseInt(dlss[4]);
				int r4 = Integer.parseInt(dlss[5]);
				int r5 = Integer.parseInt(dlss[6]);
				int r6 = Integer.parseInt(dlss[7]);
				int b = Integer.parseInt(dlss[14]);
				Ssq ssq = new Ssq(id, date, r1, r2, r3, r4, r5, r6, b);
				ssqs.add(ssq);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ssqs;
	}
}
