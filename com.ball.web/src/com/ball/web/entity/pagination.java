package com.ball.web.entity;

public class pagination {
	private int page;
	private int rows;
	private int total;
	private JSONArray data;

	public pagination(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
		data = new JSONArray();
	};

	public int getStart() {
		return (page - 1) * rows;
	}

	public int getEnd() {
		return page * rows - 1;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void addRow(JSONObject row) {
		data.add(row);
	}

	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		object.add("total", total);
		object.add("rows", data);
		return object;
	}

}
