package com.kintiger.platform.boform.dao;

import java.util.List;
import java.util.Map;

import com.kintiger.platform.boform.pojo.QueryParameter;
import com.kintiger.platform.boform.pojo.ReportParameter;


public interface IBoformDao {

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getReportParameterCount(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public List<ReportParameter> getReportParameterList(
			ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameterList
	 * @return
	 */
	public String createBatchReportParameter(
			List<ReportParameter> reportParameterList);

	/**
	 * 
	 * @param pid
	 * @return
	 */
	public ReportParameter getReportParameterByPid(Long pid);

	/**
	 * 
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int updateReportParameter(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int deleteReportParameter(ReportParameter reportParameter);

	/**
	 * 
	 * @param bid
	 * @return
	 */
	public List<ReportParameter> getReportParametersByBid(Long bid);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getQueryOrgCount(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public List<QueryParameter> getQueryOrgList(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getQueryAllChildOrgCount(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public List<QueryParameter> getQueryAllChildOrgList(
			ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getQueryParameterCount(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public List<QueryParameter> getQueryParameterList(
			ReportParameter reportParameter);

	public List<QueryParameter> getProByEid(String userId);
	
	public int createOrderReportLog(Map<String,Object> map);
	
	/**
	 * 
	 * @param 业务人员关联经销商
	 * @return
	 */
	public List<String> getKunnrIdByHeadOrAgent(String userId);
	
	public List<String> getKunnrIdByCompetent(String stationId);

	public List<String> getKunnrIdByUserId(String orgId);

}
