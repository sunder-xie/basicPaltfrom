package com.kintiger.platform.ireport.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


public class JDBCConnection {
	public static Connection getConnection(){
		try {
	          String drivername = IreportUtil.getProperty ("jdbc.driverClassName");
	          String url = IreportUtil.getProperty ("jdbc.url");
	          String username=IreportUtil.getProperty("jdbc.username");
	          String password=IreportUtil.getProperty("jdbc.password");
	          Class.forName(drivername);
	          Connection con = DriverManager.getConnection(url, username, password);
	          return con;
		   }
		  catch(Exception e){
		    e. printStackTrace();
		  }
		  return null;
	}
	public static void closeConntion(Connection conn){
		try {
			if(conn!=null&&!conn.isClosed()){
				conn.close();
			}
		} catch (Exception e) {
			
		}
		
	}
}
