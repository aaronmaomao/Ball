package com.ball.m.biz.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class HttpRequestHandler implements Runnable {
	private HttpExchange arg0;
	private Headers responseHeaders;
	private Headers requestHeaders;
	private OutputStream out;
	private Map<String, String> queryStringInfo ;
	private String requestURL;

	public HttpRequestHandler(HttpExchange arg0) {
		this.arg0 = arg0;
		responseHeaders = arg0.getResponseHeaders();
		requestHeaders = arg0.getRequestHeaders();
		out = arg0.getResponseBody();
		queryStringInfo = formData2Dic(arg0.getRequestURI().getQuery());
		requestURL = arg0.getRequestURI().toString();
	}

	@Override
	public void run() {
		try {
			arg0.sendResponseHeaders(200, 0);
			arg0.getResponseBody().write("<h1>aaa</h1>".getBytes());
			arg0.getResponseBody().flush();
			arg0.getResponseBody().close();
			arg0.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Map<String, String> formData2Dic(String formData) {
		Map<String, String> result = new HashMap<>();
		if (formData == null || formData.trim().length() == 0) {
			return result;
		}
		final String[] items = formData.split("&");
		Arrays.stream(items).forEach(item -> {
			final String[] keyAndVal = item.split("=");
			if (keyAndVal.length == 2) {
				try {
					final String key = URLDecoder.decode(keyAndVal[0], "utf8");
					final String val = URLDecoder.decode(keyAndVal[1], "utf8");
					result.put(key, val);
				} catch (UnsupportedEncodingException e) {
				}
			}
		});
		return result;
	}

}
