package com.ball.web.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ball.web.commom.Util;

public class SsqTable {
	private List<Ssq> ssqs;
	private int[] idAry;
	private Date[] dateAry;

	public SsqTable(List<Ssq> ssqs) {
		this.ssqs = ssqs;
	}

	public synchronized int[] getIdAry() {
		if (idAry == null) {
			idAry = new int[ssqs.size()];
			for (int i = 0; i < ssqs.size(); i++) {
				idAry[i] = ssqs.get(i).getId();
			}
		}
		return idAry;
	}

	public synchronized Date[] getDateAry() {
		if (dateAry == null) {
			dateAry = new Date[ssqs.size()];
			for (int i = 0; i < ssqs.size(); i++) {
				dateAry[i] = ssqs.get(i).getDate();
			}
		}
		return dateAry;
	}

	public int size() {
		return ssqs.size();
	}

	public List<Ssq> getList() {
		return ssqs;
	}

	public Ssq[] getList(int id1, int id2) {
		int index1 = Util.halfSearch(this.getIdAry(), id1, true);
		int index2 = Util.halfSearch(this.getIdAry(), id2, true);
		Ssq[] ary = new Ssq[index2 - index1 + 1];
		for (int i = index1; i <= index2; i++) {
			ary[i - index1] = ssqs.get(i);
		}
		return ary;
	}

	public Ssq[] getList(Date date1, Date date2) {
		int index1 = Util.halfSearch(this.getDateAry(), date1, true);
		int index2 = Util.halfSearch(this.getDateAry(), date2, true);
		Ssq[] ary = new Ssq[index2 - index1 + 1];
		for (int i = index1; i <= index2; i++) {
			ary[i - index1] = ssqs.get(i);
		}
		return ary;
	}
}
