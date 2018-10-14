package com.ball.web.biz;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ball.web.commom.Constant;
import com.ball.web.entity.Ssq;

/**
 * Application Lifecycle Listener implementation class WebStartListener
 *
 */
public class WebStartListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public WebStartListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			List<Ssq> ssqs = Server.updateAllSsq();
			arg0.getServletContext().setAttribute(Constant.ALLSSQ, ssqs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
