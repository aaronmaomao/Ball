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
			start = ssqs.size()-1;
		if (start < 0)
			start = 0;
		if (end > ssqs.size())
			end = ssqs.size()-1;
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

	@BallFuntion(params = { "id" })
	public int getRecord(int id) {
		return 55;
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
