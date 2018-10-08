package com.ball.m.ui.commom;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

public class MyBrowser extends Browser {

	public MyBrowser(Composite parent, int style, String url) {
		super(parent, style);
		init();
		this.setUrl(url);
	}
	
	public void init() {
		this.setJavascriptEnabled(true);
	}
	
	@Override
	protected void checkSubclass() {
		// TODO Auto-generated method stub
	}

}
