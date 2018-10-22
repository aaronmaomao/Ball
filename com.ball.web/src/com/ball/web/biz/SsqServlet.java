package com.ball.web.biz;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.ball.web.entity.JSONArray;
import com.ball.web.entity.JSONObject;
import com.ball.web.entity.Ssq;
import com.ball.web.entity.SsqTable;
import com.ball.web.entity.pagination;

public class SsqServlet extends SuperServlet {

	private static final long serialVersionUID = -3078436182490412275L;

	@BallFuntion(params = { "page=1", "rows=99999" })
	public JSONObject getRecords(int page, int rows) {
		pagination pagination = new pagination(page, rows);
		List<Ssq> ssqs = getAllSsq().getList();
		int start = pagination.getStart();
		int end = pagination.getEnd();
		if (start > ssqs.size() - 1)
			start = ssqs.size() - 1;
		if (end > ssqs.size() - 1)
			end = ssqs.size() - 1;
		for (int i = start; i <= end; i++) {
			pagination.addRow(ssqs.get(i).getJSON());
		}
		pagination.setTotal(ssqs.size());
		return pagination.toJsonObject();
	}

	@BallFuntion(params = { "date1=2003-01-01", "date2=2222-01-01", "page=1", "rows=99999" })
	public JSONObject getRecordsByDate(Date date1, Date date2, int page, int rows) throws Exception {
		pagination pagination = new pagination(page, rows);
		Ssq[] ary = getAllSsq().getList(date1, date2);
		pagination.setTotal(ary.length);
		int start = pagination.getStart();
		int end = pagination.getEnd();
		if (start > ary.length - 1)
			start = ary.length - 1;
		if (end > ary.length - 1)
			end = ary.length - 1;
		for (int i = start; i <= end; i++) {
			pagination.addRow(ary[i].getJSON());
		}
		return pagination.toJsonObject();
	}

	@BallFuntion(params = {})
	public JSONObject sum() {
		List<Ssq> ssqs = getAllSsq().getList();
		long r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0, r6 = 0, b = 0;
		for (Ssq ssq : ssqs) {
			r1 = r1 + ssq.getR1();
			r2 = r2 + ssq.getR2();
			r3 = r3 + ssq.getR3();
			r4 = r4 + ssq.getR4();
			r5 = r5 + ssq.getR5();
			r6 = r6 + ssq.getR6();
			b = b + ssq.getB();
		}
		JSONObject sum = new JSONObject("common", "hz");
		sum.add("r1", r1);
		sum.add("r2", r2);
		sum.add("r3", r3);
		sum.add("r4", r4);
		sum.add("r5", r5);
		sum.add("r6", r6);
		sum.add("b", r6);
		JSONObject avg = new JSONObject("common", "jz");
		avg.add("r1", (double) r1 / (double) ssqs.size());
		avg.add("r2", (double) r2 / (double) ssqs.size());
		avg.add("r3", (double) r3 / (double) ssqs.size());
		avg.add("r4", (double) r4 / (double) ssqs.size());
		avg.add("r5", (double) r5 / (double) ssqs.size());
		avg.add("r6", (double) r6 / (double) ssqs.size());
		avg.add("b", (double) b / (double) ssqs.size());
		JSONArray array = new JSONArray();
		array.add(sum);
		array.add(avg);
		JSONObject result = new JSONObject();
		result.add("total", 2);
		result.add("rows", array);
		return result;
	}

	private int[][] tj_count(Ssq[] ssqs) {
		int[][] result = new int[6][33];
		for (Ssq ssq : ssqs) {
			int[] ball = ssq.getBall();
			for (int i = 0; i < 6; i++) {
				result[i][ball[i]-1]++;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		
	}
}
