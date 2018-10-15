package com.ball.web.biz;

import java.sql.SQLException;
import java.util.List;

import com.ball.web.commom.Constant;
import com.ball.web.entity.JSONArray;
import com.ball.web.entity.JSONObject;
import com.ball.web.entity.Ssq;

public class SsqServlet extends SuperServlet {

	private static final long serialVersionUID = -3078436182490412275L;

	@BallFuntion(params = { "start=0", "end" })
	public JSONObject getRecords(int start, int end) {
		List<Ssq> ssqs = getAllSsq();
		JSONArray array = new JSONArray();
		if (start > ssqs.size())
			start = ssqs.size() - 1;
		if (start < 0)
			start = 0;
		if (end > ssqs.size())
			end = ssqs.size() - 1;
		if (end < 0)
			end = 0;
		if (end < start) {
			int temp;
			temp = end;
			end = start;
			start = temp;
		}
		for (int i = start; i <= end; i++) {
			array.add(ssqs.get(i).getJSON());
		}
		JSONObject object = new JSONObject();
		object.add("total", ssqs.size());
		object.add("rows", array);
		return object;
	}

	@BallFuntion(params = {})
	public JSONObject sum() {
		List<Ssq> ssqs = getAllSsq();
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
		JSONObject avg = new JSONObject("common", "jz");
		avg.add("r1", r1 / ssqs.size());
		avg.add("r2", r2 / ssqs.size());
		avg.add("r3", r3 / ssqs.size());
		avg.add("r4", r4 / ssqs.size());
		avg.add("r5", r5 / ssqs.size());
		avg.add("r6", r6 / ssqs.size());
		avg.add("b", b / ssqs.size());
		JSONArray array = new JSONArray();
		array.add(sum);
		array.add(avg);
		JSONObject result = new JSONObject();
		result.add("total", 2);
		result.add("rows", array);
		return result;
	}

	@BallFuntion(params = { "id=0" })
	public void test(int id) {
	}

	private List<Ssq> getAllSsq() {
		@SuppressWarnings("unchecked")
		List<Ssq> ssqs = (List<Ssq>) this.getServletContext().getAttribute(Constant.ALLSSQ);
		if (ssqs == null) {
			try {
				ssqs = Server.updateAllSsq();
				this.getServletContext().setAttribute(Constant.ALLSSQ, ssqs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ssqs;
	}
}
