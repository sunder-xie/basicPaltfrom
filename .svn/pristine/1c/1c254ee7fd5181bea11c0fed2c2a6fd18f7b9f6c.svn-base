package com.kintiger.platform.framework.timer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;


public class WxTimerTask  extends HttpServlet implements ServletContextListener{
	private java.util.Timer timer = null;
	
	public void contextInitialized(ServletContextEvent  event) {
		
//		try {
//			timer = new java.util.Timer(true);
//			InputStream in = event.getServletContext().getResourceAsStream(
//					"/WEB-INF/env.properties");
//			Properties pro = new Properties();
//			pro.load(in);
//			Long time=Long.parseLong(pro.getProperty("wxsendtime"));
//			timer.schedule(new WxSendMessage(event), 0, time);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	
	public void contextDestroyed(ServletContextEvent event) {
//		timer.cancel();
	}

}
