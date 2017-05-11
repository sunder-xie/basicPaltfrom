package com.kintiger.platform.report.service.impl;

import java.util.List;
import java.util.Map;

import com.kintiger.platform.report.dao.IReportDao;
import com.kintiger.platform.report.pojo.ReportStructure;
import com.kintiger.platform.report.service.IReportService;

public class ReportServiceImpl implements IReportService{
	private IReportDao reportDao;

	public IReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}

	public ReportStructure searchContent(long structureID) {
		
		return reportDao.getContent(structureID);
	}

	public List<Map<String, Object>> transformersReportData(
			ReportStructure content,Map comm) {
		// TODO Auto-generated method stub
		//--->SQL
		//.....
		StringBuffer sb = new StringBuffer();
		
		if(content.getREPORT_HEADER_ITEM() != null && !"".equals(content.getREPORT_HEADER_ITEM())){
			String header[] = content.getREPORT_HEADER_ITEM().split("#");
			for(String h : header){
				String hh = h.split("&")[1];
				if(comm.get(hh) != null && !"".equals(comm.get(hh))){
					sb.append(" and "+ h.replaceAll("&", "").replaceAll(hh, "'"+comm.get(hh)+"'"));
				}
			}
		}
		content.setREPORT_CONTENT_SOURCE(content.getREPORT_CONTENT_SOURCE().replaceAll("#", sb.toString()));
		List<Map<String, Object>>  resultData = reportDao.getDataBySql(content.getREPORT_CONTENT_SOURCE(), comm);
		//--->prc
		//.....
		
		return resultData;
	}
}
