package com.kintiger.platform.ireport.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;

public class ReportDataSource implements JRDataSource {
	JasperReport jasperReport;
	private List datas;	
	private Iterator iter ;
	Map map = new HashMap();
	
	public ReportDataSource(JasperReport jasperReport) {
		this.jasperReport =  jasperReport ;
		datas = DataSourceBaseFactory.createBeanCollection(jasperReport);//(String id);
		iter= datas.iterator();	
	}
	
	
	public boolean next() throws JRException {
		if(iter.hasNext()){
			map = (Map) iter.next();
			return true;
			}
		return false;
		}
	
	public Object getFieldValue(JRField arg0) throws JRException {
	return map.get(arg0.getName());
	}

}
