package com.ball.m;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.eclipse.swt.widgets.Composite;

import com.ball.m.biz.service.HttpRequestHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {

	public Main() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void init() throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
		server.createContext("/test", new HttpHandler() {

			@Override
			public void handle(HttpExchange arg0) throws IOException {
				new Thread(new HttpRequestHandler(arg0)).start();

			}
		});
		
		server.start();
	}

	public static void startWithRCP(Composite parent, String url) {
	}

	public static void startWithWeb(String url) {

	}

}
