package com.cp.main;

import com.cp.dao.SsqDao;
import com.cp.tools.DataServer;
import com.cp.tools.DataURL;
import com.cp.tools.ParseData;

public class Main {

	public static void main(String[] args) {
		SsqDao dao = new SsqDao();
		//dao.updateDltsx(ParseData.parseDlt(DataServer.getOnlineListBy(DataURL.DltURL, 8069, 99999)));
		dao.updateSsqsx(ParseData.parseSsq(DataServer.getOnlineListBy(DataURL.SsqURL, "00000", "99999")));
	}
}
