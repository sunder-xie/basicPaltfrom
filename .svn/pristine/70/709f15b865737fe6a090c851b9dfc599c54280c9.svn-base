package com.kintiger.platform.boform.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.boform.dao.IBoformDao;
import com.kintiger.platform.boform.pojo.QueryParameter;
import com.kintiger.platform.boform.pojo.ReportParameter;

public class BoformDaoImpl extends BaseDaoImpl implements IBoformDao {

	public int getReportParameterCount(ReportParameter reportParameter) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"boform.getReportParameterCount", reportParameter);
	}

	@SuppressWarnings("unchecked")
	public List<ReportParameter> getReportParameterList(
			ReportParameter reportParameter) {
		return (List<ReportParameter>) getSqlMapClientTemplate().queryForList(
				"boform.getReportParameterList", reportParameter);
	}

	public String createBatchReportParameter(
			final List<ReportParameter> reportParameterList) {
		return (String) getSqlMapClientTemplate().execute(
				new SqlMapClientCallback() {
					StringBuilder sb = new StringBuilder();

					public Object doInSqlMapClient(SqlMapExecutor executor)
							throws SQLException {
						executor.startBatch();

						for (ReportParameter reportParameter : reportParameterList) {
							if (sb.length() != 0) {
								sb.append(",");
							}
							sb.append(executor.insert(
									"boform.createReportParameter",
									reportParameter));
						}

						executor.executeBatch();
						return sb.toString();
					}
				});
	}

	public ReportParameter getReportParameterByPid(Long pid) {
		return (ReportParameter) getSqlMapClientTemplate().queryForObject(
				"boform.getReportParameterByPid", pid);
	}

	public int updateReportParameter(ReportParameter reportParameter) {
		return getSqlMapClientTemplate().update("boform.updateReportParameter",
				reportParameter);
	}

	public int deleteReportParameter(ReportParameter reportParameter) {
		return getSqlMapClientTemplate().delete("boform.deleteReportParameter",
				reportParameter);
	}

	@SuppressWarnings("unchecked")
	public List<ReportParameter> getReportParametersByBid(Long bid) {
		return (List<ReportParameter>) getSqlMapClientTemplate().queryForList(
				"boform.getReportParametersByBid", bid);
	}

	public int getQueryOrgCount(ReportParameter reportParameter) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"boform.getQueryOrgCount", reportParameter);
	}

	@SuppressWarnings("unchecked")
	public List<QueryParameter> getQueryOrgList(ReportParameter reportParameter) {
		return (List<QueryParameter>) getSqlMapClientTemplate().queryForList(
				"boform.getQueryOrgList", reportParameter);
	}

	public int getQueryAllChildOrgCount(ReportParameter reportParameter) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"boform.getQueryAllChildOrgCount", reportParameter);
	}

	@SuppressWarnings("unchecked")
	public List<QueryParameter> getQueryAllChildOrgList(
			ReportParameter reportParameter) {
		return (List<QueryParameter>) getSqlMapClientTemplate().queryForList(
				"boform.getQueryAllChildOrgList", reportParameter);
	}

	public int getQueryParameterCount(ReportParameter reportParameter) {
		if (reportParameter.getTxt() == 0) {
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"boform.getQueryParameterCount0", reportParameter);
		}

		if (reportParameter.getTxt() == 1) {
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"boform.getQueryParameterCount1", reportParameter);
		}

		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<QueryParameter> getQueryParameterList(
			ReportParameter reportParameter) {
		if (reportParameter.getTxt() == 0) {
			return (List<QueryParameter>) getSqlMapClientTemplate()
					.queryForList("boform.getQueryParameterList0",
							reportParameter);
		}

		if (reportParameter.getTxt() == 1) {
			return (List<QueryParameter>) getSqlMapClientTemplate()
					.queryForList("boform.getQueryParameterList1",
							reportParameter);
		}

		return null;
	}

	public List<QueryParameter> getProByEid(String userId) {
		// TODO Auto-generated method stub
		return (List<QueryParameter>) getSqlMapClientTemplate().queryForList(
				"boform.getProByEid", userId);
	}
	
	public int createOrderReportLog(Map<String,Object> map){
		getSqlMapClientTemplate().insert(
				"boform.createOrderReportLog", map);
		return 1;
	}
	
    @SuppressWarnings("unchecked")
	public List<String> getKunnrIdByHeadOrAgent(String userId){
    	return (List<String>) getSqlMapClientTemplate().queryForList(
				"boform.getKunnrIdByHeadOrAgent", userId);
    }
	
	@SuppressWarnings("unchecked")
	public List<String> getKunnrIdByCompetent(String stationId){
		return (List<String>) getSqlMapClientTemplate().queryForList(
				"boform.getKunnrIdByCompetent", stationId);
	}

	 @SuppressWarnings("unchecked")
	public List<String> getKunnrIdByUserId(String orgId) {
		return (List<String>) getSqlMapClientTemplate().queryForList(
				"boform.getKunnrIdByUserId", orgId);
	}

}
