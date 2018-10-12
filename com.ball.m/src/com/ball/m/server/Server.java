package com.ball.m.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.eclipse.swt.widgets.Composite;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
	private static Server server;
	private static final int port=8888;
	public static final String root="/ball";
	

	private Server() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized Server start() {
		if (server == null) {
			server = new Server();
		}
		return server;
	}

	private void init() throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext(root, new HttpHandler() {

			@Override
			public void handle(HttpExchange arg0) throws IOException {
				new Thread(new HttpRequestHandlerCenter(arg0)).start();

			}
		});
		server.start();
	}

	public static void startWithRCP(Composite parent, String url) {
	}

	public static void startWithWeb(String url) {

	}

	public static void main(String[] args) {
		Server server = Server.start();
	}

}
