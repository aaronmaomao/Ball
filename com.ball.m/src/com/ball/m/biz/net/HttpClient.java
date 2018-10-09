package com.ball.m.biz.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class HttpClient {
	public static String doGet(String httpurl) {
		String result = "";
		try {
			URL url = new URL(httpurl);
			// 创建代理服务器
			// InetSocketAddress addr = new InetSocketAddress("10.237.116.4", 9515);
			// Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
			URLConnection conn = url.openConnection();

			InputStream in = new GZIPInputStream(conn.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String data = "";
			while ((data = br.readLine()) != null) {
				if (data.length() > 2000)
					result = data;
			}
			in.close();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		String result = HttpClient.doGet("http://datachart.500.com/ssq/history/newinc/outball.php?start=18111&end=99999");
		ParseData.parseSsq(result);
	}
}