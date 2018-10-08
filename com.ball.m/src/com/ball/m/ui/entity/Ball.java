package com.ball.m.ui.entity;

import java.util.Date;

public abstract class Ball {
	
	protected int id = 0;
	protected Date date = null;
	
	public Ball() {
		
	}

	public Ball(int id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public abstract int[] getAsc();
}
