package com.ball.m.ui.commom;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

import com.ball.m.ui.biz.js.JSFunctionFac;

public class MyBrowser extends Browser {

	public MyBrowser(Composite parent, int style, String url) {
		super(parent, style);
		init();
		this.setUrl(url);
	}
	
	public void init() {
		this.setJavascriptEnabled(true);
		JSFunctionFac fac = new JSFunctionFac();
		fac.initJSFunction(this);
	}
	
	@Override
	protected void checkSubclass() {
	}

}
