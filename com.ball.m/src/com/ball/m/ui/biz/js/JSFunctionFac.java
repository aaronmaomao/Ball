package com.ball.m.ui.biz.js;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.browser.Browser;

public class JSFunctionFac{
	private Set<JSFunHandler> jsfs = new HashSet<>();

	public void initJSFunction(Browser browser) {
		jsfs.add(new CommJSFunction());

		for (JSFunHandler handler : jsfs) {
			Method[] methods = handler.getClass().getDeclaredMethods();
			for (Method m : methods) {
				for (Annotation a : m.getAnnotations()) {
					if (a.getClass().equals("JSFun")) {
						new JSFunction(browser, m.getName(), handler);
					}
				}
			}
		}
	}
}
