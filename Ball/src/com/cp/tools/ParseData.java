package com.cp.tools;

import java.util.ArrayList;
import java.util.List;

import com.cp.entity.Dlt;
import com.cp.entity.Ssq;

public class ParseData {
	public static List<Dlt> parseDlt(String data) {
		List<Dlt> dlts = new ArrayList<Dlt>();
		String dltStr = data.substring(0, data.indexOf("</tr>") + 5);
		data = data.substring(data.indexOf("</tr>") + 5, data.length());
		while (true) {

			dltStr = dltStr.replace("<tr class=\"t_tr1\">", "").replace("</tr>", "");
			Dlt d = new Dlt();

			d.setId(Integer.parseInt(dltStr.substring(dltStr.indexOf(">") + 1, dltStr.indexOf("</td>"))));
			dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());

			d.setDate(dltStr.substring(dltStr.indexOf(">") + 1, dltStr.indexOf("</td>")));
			dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
			if (!dltStr.contains("&nbsp;")) {
				d.setN1(Integer.parseInt(dltStr.substring(dltStr.indexOf(">") + 1, dltStr.indexOf("</td>"))));
				dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
				d.setN2(Integer.parseInt(dltStr.substring(dltStr.indexOf(">") + 1, dltStr.indexOf("</td>"))));
				dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
				d.setN3(Integer.parseInt(dltStr.substring(dltStr.indexOf(">") + 1, dltStr.indexOf("</td>"))));
				dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
				d.setN4(Integer.parseInt(dltStr.substring(dltStr.indexOf(">") + 1, dltStr.indexOf("</td>"))));
				dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
				d.setN5(Integer.parseInt(dltStr.substring(dltStr.indexOf(">") + 1, dltStr.indexOf("</td>"))));
				dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
			}

			dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
			dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
			dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
			dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
			dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());

			d.setB1(Integer.parseInt(dltStr.substring(dltStr.indexOf(">") + 1, dltStr.indexOf("</td>"))));
			dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());
			d.setB2(Integer.parseInt(dltStr.substring(dltStr.indexOf(">") + 1, dltStr.indexOf("</td>"))));
			dltStr = dltStr.substring(dltStr.indexOf("</td>") + 5, dltStr.length());

			dlts.add(d);
			System.out.println(d);
			if (!data.equals("")) {
				if (data.length() == dltStr.length()) {
					dltStr = data;
					data = "";
				} else {
					dltStr = data.substring(0, data.indexOf("</tr>") + 5);
					data = data.substring(data.indexOf("</tr>") + 5, data.length());
				}
			} else {
				break;
			}
		}
		return dlts;
	}

	public static List<Ssq> parseSsq(String data) {
		List<Ssq> ssqs = new ArrayList<Ssq>();
		String dltStrs[] = data.split("</tr>");
		for (String dltStr : dltStrs) {
			String dlss[] = dltStr.split("</td>");
			for (int i = 0; i < dlss.length; i++) {
				dlss[i] = dlss[i].substring(dlss[i].lastIndexOf(">") + 1, dlss[i].length());
			}
			Ssq ssq = new Ssq();
			ssq.setId(Integer.parseInt(dlss[0]));
			ssq.setDate(dlss[1]);
			ssq.setR1(Integer.parseInt(dlss[2]));
			ssq.setR2(Integer.parseInt(dlss[3]));
			ssq.setR3(Integer.parseInt(dlss[4]));
			ssq.setR4(Integer.parseInt(dlss[5]));
			ssq.setR5(Integer.parseInt(dlss[6]));
			ssq.setR6(Integer.parseInt(dlss[7]));
			ssq.setB(Integer.parseInt(dlss[14]));
			ssqs.add(ssq);
		}
		return ssqs;
	}
}
