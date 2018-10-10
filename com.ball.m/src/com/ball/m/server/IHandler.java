package com.ball.m.server;

public interface IHandler {

	public void doPost(Request request, Response response) throws Exception;

	public void doGet(Request request, Response response) throws Exception;
}
