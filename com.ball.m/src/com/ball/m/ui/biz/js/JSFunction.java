package com.ball.m.ui.biz.js;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class JSFunction extends BrowserFunction {

	private JSFunHandler handler;

	public JSFunction(Browser browser, String name, JSFunHandler handler) {
		super(browser, name);
		this.handler = handler;
	}
	
	@Override
	public Object function(Object[] arguments) {
		Method methods[] = handler.getClass().getMethods();
		for (Method m : methods) {
			if (m.getName().equals(this.getName())) {
				try {
					return m.invoke(handler, arguments);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return null;	
	}
}
