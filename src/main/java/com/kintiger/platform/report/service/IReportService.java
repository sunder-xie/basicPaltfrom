package com.kintiger.platform.report.service;

import java.util.List;
import java.util.Map;

import com.kintiger.platform.report.pojo.ReportStructure;

public interface IReportService {
	/**
	 * 根据报表ID查询报表组成信息
	 * @param structureID
	 * @return
	 */
	ReportStructure searchContent(long structureID);
	
	/**
	 * 根据报表明细查询展现数据
	 * @param content
	 * @param comm 
	 * @return
	 */
	List<Map<String, Object>> transformersReportData(ReportStructure content, Map comm);
	
}
