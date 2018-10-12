package com.ball.m.entity;

import java.util.Arrays;
import java.util.Date;

import com.ball.m.commom.Util;

public class Ssq extends Ball {
	
	private int r1;
	private int r2;
	private int r3;
	private int r4;
	private int r5;
	private int r6;
	private int b;

	public Ssq() {
	}

	public Ssq(int r1, int r2, int r3, int r4, int r5, int r6, int b) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.r3 = r3;
		this.r4 = r4;
		this.r5 = r5;
		this.r6 = r6;
		this.b = b;
	}

	public Ssq(int id, Date date, int r1, int r2, int r3, int r4, int r5, int r6, int b) {
		this(r1, r2, r3, r4, r5, r6, b);
		this.id = id;
		this.date = date;
	}

	@Override
	public int[] getAsc() {
		int rAsc[] = Util.orderAsc(new int[] {r1,r2,r3,r4,r5,r6});
		return Util.concat(rAsc, b);
	}
	
	
	
	public int getR1() {
		return r1;
	}

	public void setR1(int r1) {
		this.r1 = r1;
	}

	public int getR2() {
		return r2;
	}

	public void setR2(int r2) {
		this.r2 = r2;
	}

	public int getR3() {
		return r3;
	}

	public void setR3(int r3) {
		this.r3 = r3;
	}

	public int getR4() {
		return r4;
	}

	public void setR4(int r4) {
		this.r4 = r4;
	}

	public int getR5() {
		return r5;
	}

	public void setR5(int r5) {
		this.r5 = r5;
	}

	public int getR6() {
		return r6;
	}

	public void setR6(int r6) {
		this.r6 = r6;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Ssq(1, 5, 44, 22, 34, 15, 8).getAsc()));
	}
}
