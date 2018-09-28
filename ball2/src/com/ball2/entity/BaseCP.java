package com.ball2.entity;

public abstract class BaseCP {
	protected String id;
	protected String date;

	public BaseCP() {
		// TODO 自动生成的构造函数存根
	}
	
	public BaseCP(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public abstract String[] getOrderAsc();
	public abstract String[] getOrderDesc();
}
