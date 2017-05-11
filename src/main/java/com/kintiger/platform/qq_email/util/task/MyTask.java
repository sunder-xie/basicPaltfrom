package com.kintiger.platform.qq_email.util.task;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

public class MyTask extends HttpServlet implements ServletContextListener{

	private static final long serialVersionUID = 1983542442806939247L;
	
	private java.util.Timer timer = null;
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000; 

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/*** 定制每日2:00执行方法 ***/
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 2);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		Date date=calendar.getTime(); //第一次执行定时任务的时间(次日2:00)
		  
		timer = new java.util.Timer(true);
		timer.schedule(new TaskToSendEmailForEvtstatistics(sce), date, PERIOD_DAY);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();
	}

}
