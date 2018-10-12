package com.ball.m.biz;

import java.util.Comparator;

import com.ball.m.entity.Ball;

public class BallComparator<T extends Ball> implements Comparator<T> {
	private String by;
	private boolean isAsc;

	public static final String ID = "id";

	public BallComparator(String by, boolean isAsc) {
		this.by = by;
		this.isAsc = isAsc;
	}

	@Override
	public int compare(T o1, T o2) {
		if (!isAsc) {
			T t = o1;
			o1 = o2;
			o2 = t;
		}
		switch (by) {

		default:
			return byID(o1, o2);
		}
	}

	private int byID(T o1, T o2) {
		if (o1.getId() > o2.getId()) {
			return 1;
		} else {
			return 0;
		}
	}

}
