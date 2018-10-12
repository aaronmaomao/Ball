package com.ball.m.server.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.ball.m.server.Request;
import com.ball.m.server.Response;

public class FileRequestHandler extends AbstactHandler{

	@Override
	public void handle(Request request, Response response) throws Exception {
		String url = request.getUrl();
		sendFile(new File(System.getProperty("user.dir")+url), response);
		
	}
	
	public void sendFile(File file, Response response) throws Exception {
		InputStream in = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len=0;
		while((len=in.read(buf))>0) {
			response.getOut().write(buf, 0, len);
		}
		in.close();
	}

}
