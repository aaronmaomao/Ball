package com.ball.web.biz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.web.entity.Result;

/**
 * Servlet implementation class SsqServlet
 */
public class SuperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Method> mList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuperServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		mList = new ArrayList<>();
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(BallFuntion.class) != null) {
				mList.add(method);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Result result = handle(request);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result.toString().replaceAll("&quot;", "\\\\\""));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private Result handle(HttpServletRequest request) {
		Result result = new Result(false, "");
		String funName = request.getParameter("fun");
		for (Method m : mList) {
			if (m.getName().equals(funName)) {
				BallFuntion anno = m.getAnnotation(BallFuntion.class);
				String params[] = anno.params();
				String values[] = new String[params.length];
				for (int i = 0; i < params.length; i++) {
					params[i] = params[i] + " ";
					String value = request.getParameter(params[i].split("=")[0].trim());
					if (value == null && params[i].split("=").length != 2) {
						return new Result(false, funName + "中" + params[i].trim() + "不能为空");
					} else if (value == null) {
						value = params[i].split("=")[1];
					}
					values[i] = value.trim();
				}

				Object[] args = new Object[m.getParameterTypes().length];
				for (int i = 0; i < m.getParameterTypes().length && i < params.length; i++) {
					if (m.getParameterTypes()[i].getName().equals("int")) {
						if (values[i].isEmpty()) {
							args[i] = 0;
						} else {
							try {
								args[i] = Integer.parseInt(values[i]);
							} catch (Exception e) {
								result.setSuccess(false);
								result.addData(params[i] + "参数类型不合法");
								return result;
							}
						}
					} else {
						args[i] = values[i];
					}
				}
				// if(m.getReturnType()!=null) {
				try {
					Object data = m.invoke(this, args);
					result.setSuccess(true);
					result.addData(data);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
					result.setSuccess(false);
					result.addData(e.getMessage());
				}
				break;
			}

		}

		return result;

	}

	public static void main(String[] args) {
	}
}
