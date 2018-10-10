package com.ball.m.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ball.m.server.handler.TestHandler;
import com.sun.net.httpserver.HttpExchange;

public class HttpRequestHandlerCenter implements Runnable {
	private HttpExchange exchange;

	private static Map<String, IHandler> _handlerMap = new HashMap<>();

	public HttpRequestHandlerCenter(HttpExchange exchange) {
		this.exchange = exchange;
	}

	@Override
	public void run() {
		try {

			Request request = makeRequest(exchange);
			Response response = makeRespose(exchange);
			this.exchange.sendResponseHeaders(200, 0);
			distribute(request.getUrl(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			exchange.close();
		}
	}

	public void distribute(String url, Request request, Response response) throws Exception {
		IHandler handler = _handlerMap.get(url.toLowerCase());
		if (handler != null) {
			switch (request.getMethod().toLowerCase()) {
			case "post":
				handler.doPost(request, response);
				break;
			case "get":
			default:
				handler.doGet(request, response);
				break;
			}
		}

		response.finsh();
	}

	private Request makeRequest(HttpExchange exchange) {
		Request request = new Request(exchange) {
		};
		return request;
	}

	private Response makeRespose(HttpExchange exchange) {
		Response response = new Response(exchange) {

			@Override
			protected void finsh() throws IOException {
				this.getOut().flush();
				this.getOut().close();
			}
		};
		return response;
	}

	static {
		_handlerMap.put("/ball/data/ssq.json", new TestHandler());
	}

}
