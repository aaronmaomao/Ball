package com.ball.m.server.handler;

import com.ball.m.server.IHandler;
import com.ball.m.server.Request;
import com.ball.m.server.Response;

public abstract class AbstactHandler implements IHandler {

	@Override
	public void doGet(Request request, Response response) throws Exception {
		handle(request, response);
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		handle(request, response);
	}

	public void dispach(String location, Request request, Response response) {

	}
	
	public abstract void handle(Request request, Response response) throws Exception;

}
