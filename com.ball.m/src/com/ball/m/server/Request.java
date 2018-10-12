package com.ball.m.server;

import java.util.Map;

import com.ball.m.commom.Util;
import com.sun.net.httpserver.HttpExchange;

public abstract class Request {
	private Map<String, String> queryParams;
	private String url;
	private String method;

	protected Request(HttpExchange exchange) {
		this.url = exchange.getRequestURI().toString();

		if (url.equals(Server.root)) {
			url = "";
		} else {
			int i = url.indexOf("?");
			if (i > 0) {
				url = url.substring(0, i);
			}
			url = url.substring(Server.root.length(), url.length());
		}
		this.queryParams = Util.formData2Dic(exchange.getRequestURI().getQuery());
		this.method = exchange.getRequestMethod();
	}

	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	public String getUrl() {
		return url;
	}

	public String getMethod() {
		return method;
	}

}
