package com.ball.m.server.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.ball.m.server.Request;
import com.ball.m.server.Response;

public class TestHandler extends AbstactHandler {

	@Override
	public void handle(Request request, Response response) throws Exception {
		File f = new File(System.getProperty("user.dir")+"/resource/ui/ssq.html");
		String str="";
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((str=br.readLine())!=null) {
			response.sendText(str+"\r\n");
		}
		br.close();
	}

}
