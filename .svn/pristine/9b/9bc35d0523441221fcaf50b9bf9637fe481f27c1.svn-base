package com.kintiger.platform.account.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.account.dao.IAccountDao;
import com.kintiger.platform.account.pojo.PayeeInfo;
import com.kintiger.platform.account.pojo.SingleDetail;
import com.kintiger.platform.account.pojo.SingleTotal;
import com.kintiger.platform.account.pojo.WorkPlanTotal;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.pojo.ProEventDetail;

@SuppressWarnings("rawtypes")
public class AccountDaoImpl extends BaseDaoImpl implements IAccountDao{

	public Long saveSingleTotal(SingleTotal singleTotal) {
		return (Long) getSqlMapClientTemplate().insert("account.saveEntity_singleTotal", singleTotal);
	}
	
	public void saveSingleDetail(final List<SingleDetail> detailList){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)throws SQLException {
                executor.startBatch();
                for(SingleDetail detail: detailList) {
                	executor.insert("account.saveEntity_singleDetail", detail);
                }
                executor.executeBatch();
                return null;
            }
        });
	}
	
	/**
	 * 判断user是否有某一角色
	 */
	public int getUserRoles(AllUsers user){
		return (Integer)getSqlMapClientTemplate().queryForObject("account.getUserRoles", user);
	}
	
	public SingleTotal searchSingleTotalByPlanId(Long planId){
		return (SingleTotal) getSqlMapClientTemplate().queryForObject("account.searchSingleTotalByPlanId", planId);
	}
	
	@SuppressWarnings("unchecked")
	public List<SingleDetail> searchSingleDetailByPlanId(Long planId){
		return getSqlMapClientTemplate().queryForList("account.searchSingleDetailByPlanId", planId);
	}
	
	/**
	 * 付款
	 */
	public void playMoney(String ids){
		this.getSqlMapClientTemplate().update("account.playMoney", ids);
	}
	
	/**
	 * 导出到Excel的报销单列表
	 */
	@SuppressWarnings("unchecked")
	public List<SingleTotal>  searchTraReimburListToExcel(SingleTotal singleTotal){
		return getSqlMapClientTemplate().queryForList("account.searchTraReimburListToExcel", singleTotal);
	}
	
	public int searchTraReimburCount(SingleTotal singleTotal){
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.searchTraReimburCount", singleTotal);
	}
	
	@SuppressWarnings("unchecked")
	public List<SingleTotal> searchTraReimburList(SingleTotal singleTotal){
		return getSqlMapClientTemplate().queryForList("account.searchTraReimburList", singleTotal);
	}
	
	public int searchSingleDetailCount(SingleDetail singleDetail){
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.searchSingleDetailCount", singleDetail);
	}
	
	@SuppressWarnings("unchecked")
	public List<SingleDetail> searchSingleDetailList(SingleDetail singleDetail){
		return getSqlMapClientTemplate().queryForList("account.searchSingleDetailList", singleDetail);
	}
	
	public int searchWorkPlanCount(WorkPlanTotal workPlan){
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.searchWorkPlanCount", workPlan);
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkPlanTotal> searchWorkPlan(WorkPlanTotal workPlan){
		return getSqlMapClientTemplate().queryForList("account.searchWorkPlan", workPlan);
	}
	
	public int searchCostTypeCount(CmsTbDict dict){
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.searchCostTypeCount", dict);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmsTbDict> searchCostTypeList(CmsTbDict dict){
		return getSqlMapClientTemplate().queryForList("account.searchCostTypeList", dict);
	}

	public String getStationIdByDetailId(Long eventDetailId) {
		return (String) getSqlMapClientTemplate().queryForObject("account.getStationIdByDetailId", eventDetailId);
	}

	public void updateSingleTotal(SingleTotal singleTotal) {
		getSqlMapClientTemplate().update("account.updateSingleTotal", singleTotal);
	}
	
	public void updateSingleDetail(final List<SingleDetail> detailList){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)throws SQLException {
                executor.startBatch();
                for(SingleDetail singleDetail: detailList) {
                	executor.update("account.updateSingleDetail", singleDetail);
                }
                executor.executeBatch();
                return null;
            }
        });
	}

	public void updateCostCenter(SingleTotal singleTotal) {
		getSqlMapClientTemplate().update("account.updateCostCenter", singleTotal);
		
	}

	public int getPayeeInfoCount(PayeeInfo payeeInfo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("account.getPayeeInfoCount", payeeInfo);
	}

	@SuppressWarnings("unchecked")
	public List<PayeeInfo> getPayeeInfoList(PayeeInfo payeeInfo) {
		return getSqlMapClientTemplate().queryForList("account.getPayeeInfoList", payeeInfo);
	}

	public void addPayeeInfo(PayeeInfo payeeInfo) {
		getSqlMapClientTemplate().insert("account.addPayeeInfo", payeeInfo);
	}
	
	public void removePayeeInfo(final String ids, final String modifier) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)throws SQLException {
                executor.startBatch();
                String[] id = ids.split(",");
                for (int i=0; i<id.length; i++) {
                	HashMap<String, Object> map = new HashMap<String, Object>();
                	map.put("id", Long.parseLong(id[i]));
                	map.put("modifier", modifier);
                	executor.update("account.removePayeeInfo", map);
                }
                executor.executeBatch();
                return null;
            }
        });
	}
	
	public PayeeInfo getPayeeInfoById(Long id) {
		return (PayeeInfo) getSqlMapClientTemplate().queryForObject("account.getPayeeInfoById", id);
	}
	
	public void modifyPayeeInfo(PayeeInfo payeeInfo) {
		getSqlMapClientTemplate().update("account.modifyPayeeInfo", payeeInfo);
	}
	
	public int getPayeeCount(PayeeInfo payeeInfo) {
		return (Integer) getSqlMapClientTemplate().queryForObject("account.getPayeeCount", payeeInfo);
	}
	
	@SuppressWarnings("unchecked")
	public List<PayeeInfo> getPayeeList(PayeeInfo payeeInfo) {
		return getSqlMapClientTemplate().queryForList("account.getPayeeList", payeeInfo);
	}
	
	public int getPayeeInfoCountByName(String userName) {
		return (Integer) getSqlMapClientTemplate().queryForObject("account.getPayeeInfoCountByName", userName);
	}
	
	public PayeeInfo getDefaultPayee(String userName) {
		return (PayeeInfo)getSqlMapClientTemplate().queryForObject("account.getDefaultPayee", userName);
	}
	
	public int getPayAccountCount(PayeeInfo payeeInfo) {
		return (Integer) getSqlMapClientTemplate().queryForObject("account.getPayAccountCount", payeeInfo);
	}
	
	@SuppressWarnings("unchecked")
	public List<PayeeInfo> getPayAccountList(PayeeInfo payeeInfo) {
		return getSqlMapClientTemplate().queryForList("account.getPayAccountList", payeeInfo);
	}
	
	public int getHisEventCount(ProcessEventTotal eventTotal) {
		return (Integer)getSqlMapClientTemplate().queryForObject("account.getHisEventCount", eventTotal);
	};
	
	@SuppressWarnings("unchecked")
	public List<ProcessEventTotal> getHisEventList(ProcessEventTotal eventTotal) {
		return getSqlMapClientTemplate().queryForList("account.getHisEventList", eventTotal);
	}

	@SuppressWarnings("unchecked")
	public List<SingleDetail> getSingleDetailList(String transactionId) {
		return getSqlMapClientTemplate().queryForList("account.getSingleDetailList", transactionId);
	}

	public void updateFinancialDocNum(String transaction_id,
			String financial_doc_num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("transaction_id", transaction_id);
		map.put("financial_doc_num", financial_doc_num);
		getSqlMapClientTemplate().update("account.updateFinancialDocNum", map);
	};
	
	public int countSingleTotal(String transaction_id) {
		return (Integer) getSqlMapClientTemplate().queryForObject("account.countSingleTotal", transaction_id);
	};
	
	public void batchUpdateFinancialDocNum(final List<SingleTotal> singleTotalList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)throws SQLException {
                executor.startBatch();
                for(SingleTotal singleTotal: singleTotalList) {
                	executor.update("account.batchUpdateFinancialDocNum", singleTotal);
                }
                executor.executeBatch();
                return null;
            }
        });
	}; 
	
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getAuditorListByEventId(Long transaction_id) {
		return getSqlMapClientTemplate().queryForList("account.getAuditorListByEventId", transaction_id);
	}

	public int getReimburDetailCount(SingleDetail singleDetail) {
		return (Integer) getSqlMapClientTemplate().queryForObject("account.getReimburDetailCount", singleDetail);
	}

	@SuppressWarnings("unchecked")
	public List<SingleDetail> getReimburDetailList(SingleDetail singleDetail) {
		return getSqlMapClientTemplate().queryForList("account.getReimburDetailList", singleDetail);
	};
	
	@SuppressWarnings("unchecked")
	public List<SingleDetail> getReimburDetailListNoPage(SingleDetail singleDetail) {
		return getSqlMapClientTemplate().queryForList("account.getReimburDetailListNoPage", singleDetail);
	};
	
	public String getDefaultCompanyCode(Long userId) {
		return (String) getSqlMapClientTemplate().queryForObject("account.getDefaultCompanyCode", userId);
	}
	
	public int getSalesOrgCount(String orgId) {
		return (Integer)getSqlMapClientTemplate().queryForObject("account.getSalesOrgCount", orgId);
	}
}
