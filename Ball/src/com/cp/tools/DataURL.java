package com.cp.tools;

public enum DataURL {

	DltURL("http://datachart.500.com/dlt/history/newinc/outball.php?start=#start&end=#end"), 
	SsqURL("http://datachart.500.com/ssq/history/newinc/outball.php?start=#start&end=#end");
	private String url;

	private DataURL(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
