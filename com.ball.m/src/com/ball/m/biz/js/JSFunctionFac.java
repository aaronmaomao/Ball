package com.ball.m.biz.js;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.browser.Browser;

public class JSFunctionFac {
	private Set<JSFunHandler> jsfs = new HashSet<>();

	public void initJSFunction(Browser browser) {
		jsfs.add(new CommJSFunction());

		for (JSFunHandler handler : jsfs) {
			Method[] methods = handler.getClass().getDeclaredMethods();
			for (Method m : methods) {
				if (m.getAnnotationsByType(JSFun.class) != null)
					new JSFunction(browser, m.getName(), handler);
			}

		}
	}
}
