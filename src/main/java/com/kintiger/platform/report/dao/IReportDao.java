package com.kintiger.platform.report.dao;

import java.util.List;
import java.util.Map;

import com.kintiger.platform.report.pojo.ReportStructure;

public interface IReportDao {
	List<ReportStructure> getContentList(long id);
	/**
	 * 根据ID查询信息
	 * @param structureID
	 * @return
	 */
	ReportStructure getContent(long structureID);
	/**
	 * 根据动态SQL查询结果集
	 * @param sql
	 * @param comm
	 * @return
	 */
	List<Map<String,Object>> getDataBySql(String sql,Map<String,String> comm);
	/**
	 * 根据存储过程查询结果集
	 * @param proc
	 * @param comm
	 * @return
	 */
	List<Map<String,String>> getDataByProc(String proc,Map<String,String> comm);
	
}
