package com.kintiger.platform.ireport.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;
/**
* Map中的键值要与模板文件的file值对应。
**/
public class DataSourceBaseFactory {

public static List createBeanCollection(JasperReport jasperReport) {
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		List datas = new ArrayList();
				
		try {
		con = JDBCConnection .getConnection();
		st = con.createStatement();
		System.err.println(jasperReport.getQuery().getText());
		rs = st. executeQuery (jasperReport.getQuery().getText());
		getMapValue(rs,datas);
		


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
            if(rs != null) rs.close();
            if(st != null) st.close();
            if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return datas;
	}

public static List getMapValue(ResultSet rs,List datas) throws Exception {
	Map map = null;
	if(rs==null)
	return datas;
	// 用于获取列数、或者列类型  
	 map = new HashMap();
	 ResultSetMetaData meta = rs.getMetaData();  
	 while (rs.next()) {  
		  for (int i = 1; i <= meta.getColumnCount(); i++) {  
			  // 当前列名  
               String colName = meta.getColumnName(i); 
               
              // 获取当前位置的值，返回Object类型  
               Object value = rs.getObject(colName);  
               if(value!=null&&value.getClass().isInstance(java.math.BigDecimal.class))
            	   value = value.toString();
               else if(value!=null&& (value instanceof java.sql.Date)){
                   DateFormat dateFormat;
                   dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);//设定格式
                   dateFormat.setLenient(false);
                   java.util.Date timeDate = dateFormat.parse(value.toString());//util类型
                   java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//  	   
                   map.put(colName, dateTime);
               }else{
            	   map.put(colName, value);
            	   
               }   
		  } 
		  datas.add(map);
	 }
	return datas;
	}
} 
   
