package com.ball.m.server;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public abstract class Response {

	private OutputStream out;
	protected HttpExchange exchange;

	protected Response(HttpExchange exchange) {
		this.exchange = exchange;
		out = exchange.getResponseBody();
	}

	public void sendText(String text) throws IOException {
		out.write(text.getBytes());
	}

	public OutputStream getOut() {
		return out;
	}

	protected abstract void finsh() throws Exception;
}
