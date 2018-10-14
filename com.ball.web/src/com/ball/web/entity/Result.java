package com.ball.web.entity;

public class Result {

	private JSONObject result;

	public Result(boolean isSuccess, Object data) {
		super();
		result = new JSONObject();
		result.add("isSuccess", isSuccess);
		result.add("data", data);
	}

	public void setSuccess(boolean b) {
		result.add("isSuccess", b);
	}

	public void addData(Object data) {
		result.add("data", data);
	}

	@Override
	public String toString() {
		return result.toString();
	}
}
