package com.cp.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DataServer {

	public static String getOnlineListBy(DataURL url1, String start, String end) {
		String data = "";
		// TODO Auto-generated method stub
		try {
			URL url = new URL(url1.getUrl().replace("#start", start).replace("#end", end));
			// 创建代理服务器
			InetSocketAddress addr = new InetSocketAddress("10.237.116.4", 9515);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
			URLConnection conn = url.openConnection(proxy);

			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("utf-8")));
			while ((data = br.readLine()) != null) {
				if (data.length() > 2000)
					break;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		data = data.substring(data.indexOf("<tr"), data.lastIndexOf("</tr>") + "</tr>".length());
		return data;
	}
}
