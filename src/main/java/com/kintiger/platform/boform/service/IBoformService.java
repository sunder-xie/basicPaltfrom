package com.kintiger.platform.boform.service;

import java.util.List;
import java.util.Map;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.boform.pojo.QueryParameter;
import com.kintiger.platform.boform.pojo.ReportParameter;


/**
 * bo報表中心接口<br>
 * 包括報表參數配置 報表中心免登
 * 
 * @author xujiakun
 * 
 */
public interface IBoformService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "操作失败！";

	public static final String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

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
	 * 批量创建
	 * 
	 * @param reportParameterList
	 * @return
	 */
	public StringResult createBatchReportParameter(
			List<ReportParameter> reportParameterList);

	/**
	 * 根据主键查询
	 * 
	 * @param pid
	 * @return
	 */
	public ReportParameter getReportParameterByPid(Long pid);

	/**
	 * 
	 * 修改
	 * 
	 * @param reportParameter
	 * @return
	 */
	public StringResult updateReportParameter(ReportParameter reportParameter);

	/**
	 * 删除
	 * 
	 * @param reportParameter
	 * @return
	 */
	public StringResult deleteReportParameter(ReportParameter reportParameter);

	/**
	 * 根据报表id查询报表参数
	 * 
	 * @param bid
	 * @return
	 */
	public List<ReportParameter> getReportParametersByBid(Long bid);

	/**
	 * 查询smsuser.b_saporg_smsorg
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getQueryOrgCount(ReportParameter reportParameter);

	/**
	 * 查询smsuser.b_saporg_smsorg
	 * 
	 * @param reportParameter
	 * @return
	 */
	public List<QueryParameter> getQueryOrgList(ReportParameter reportParameter);

	/**
	 * 查询smsuser.b_saporg_smsorg 递归查询所有子组织
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getQueryAllChildOrgCount(ReportParameter reportParameter);

	/**
	 * 查询smsuser.b_saporg_smsorg 递归查询所有子组织
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
	
	/**
	 * 创建对账单报表访问日志
	 * @param 
	 * @return
	 */
	public int createOrderReportLog(Map<String,Object> map);
	
	/**
	 * 
	 * @param 业务人员关联经销商
	 * @return
	 */
	public List<String> getKunnrIdByHeadOrAgent(String userId);
	
	public List<String> getKunnrIdByCompetent(String stationId);
	/**
	 * 根据组织id查询经销商
	 * sl.zhu
	 * @param userId 登陆人id
	 * @return 经销商列表
	 */
	public List<String> getKunnrIdByUserId(String orgId);

}
