package com.ball.web.biz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.web.commom.Constant;
import com.ball.web.commom.Util;
import com.ball.web.entity.Result;
import com.ball.web.entity.SsqTable;
import com.mysql.jdbc.StringUtils;

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
		getAllSsq();
		mList = new ArrayList<>();
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(BallFuntion.class) != null) {
				mList.add(method);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = handle(request);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result.toString().replaceAll("&quot;", "\\\\\""));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private Result handle(HttpServletRequest request) {
		Result result = new Result(false, "");
		String funName = request.getParameter("fun");
		if (StringUtils.isNullOrEmpty(funName)) {
			result.addData(Constant.FUN_INVALID);
			return result;
		}
		for (Method m : mList) {
			if (m.getName().equals(funName)) {
				BallFuntion anno = m.getAnnotation(BallFuntion.class);
				String params[] = anno.params();
				String values[] = new String[params.length];
				for (int i = 0; i < params.length; i++) {
					params[i] = params[i] + " ";
					String value = request.getParameter(params[i].split("=")[0].trim());
					if (value == null && params[i].split("=").length != 2) {
						return new Result(false, Constant.ARGS_NULL + params[i]);
					} else if (value == null) {
						value = params[i].split("=")[1];
					}
					values[i] = value.trim();
				}

				Object[] args = new Object[m.getParameterTypes().length];
				for (int i = 0; i < m.getParameterTypes().length && i < params.length; i++) {
					try {
						if (m.getParameterTypes()[i].getName().equals("int")) {
							args[i] = Integer.parseInt(values[i]);
						} else if (m.getParameterTypes()[i].getName().equals("java.util.Date")) {
							args[i] = Util.df2.parse(values[i]);
						} else {
							args[i] = values[i];
						}
					} catch (Exception e) {
						result.setSuccess(false);
						result.addData(Constant.ARGS_NULL + params[i] + " = " + values[i]);
						return result;
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

	protected SsqTable getAllSsq() {
		SsqTable table = (SsqTable) this.getServletContext().getAttribute(Constant.SSQ_TABLE);
		if (table == null) {
			try {
				table = new SsqTable(Server.updateAllSsq());
				this.getServletContext().setAttribute(Constant.SSQ_TABLE, table);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return table;
	}

}
