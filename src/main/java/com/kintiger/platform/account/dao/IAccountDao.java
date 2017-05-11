package com.kintiger.platform.account.dao;

import java.util.List;

import com.kintiger.platform.account.pojo.PayeeInfo;
import com.kintiger.platform.account.pojo.SingleDetail;
import com.kintiger.platform.account.pojo.SingleTotal;
import com.kintiger.platform.account.pojo.WorkPlanTotal;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.pojo.ProEventDetail;

public interface IAccountDao {

	public Long saveSingleTotal(SingleTotal singleTotal);
	
	public void saveSingleDetail(List<SingleDetail> detailList);
	
	public int getUserRoles(AllUsers user);
	
	public void playMoney(String ids);
	
	public List<SingleTotal> searchTraReimburListToExcel(SingleTotal singleTotal);
	
	public int searchTraReimburCount(SingleTotal singleTotal);
	
	public List<SingleTotal> searchTraReimburList(SingleTotal singleTotal);
	
	public int searchSingleDetailCount(SingleDetail singleDetail);
	
	public List<SingleDetail> searchSingleDetailList(SingleDetail singleDetail);
	
	public int searchWorkPlanCount(WorkPlanTotal workPlan);
	
	public List<WorkPlanTotal> searchWorkPlan(WorkPlanTotal workPlan);
	
	public int searchCostTypeCount(CmsTbDict dict);
	
	public List<CmsTbDict> searchCostTypeList(CmsTbDict dict);
	
	public String getStationIdByDetailId(Long eventDetailId);
	
	public void updateSingleTotal(SingleTotal singleTotal);
	
	public void updateSingleDetail(List<SingleDetail> detailList);

	public void updateCostCenter(SingleTotal singleTotal);
	
	public SingleTotal searchSingleTotalByPlanId(Long planId);
	
	public List<SingleDetail> searchSingleDetailByPlanId(Long planId);
	
	public int getPayeeInfoCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayeeInfoList(PayeeInfo payeeInfo);
	
	public void addPayeeInfo(PayeeInfo payeeInfo);
	
	public void removePayeeInfo(String ids, String modifier);
	
	public PayeeInfo getPayeeInfoById(Long id);
	
	public void modifyPayeeInfo(PayeeInfo payeeInfo);
	
	public int getPayeeCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayeeList(PayeeInfo payeeInfo);
	
	public int getPayeeInfoCountByName(String userName);
	
	public PayeeInfo getDefaultPayee(String userName);
	
	public int getPayAccountCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayAccountList(PayeeInfo payeeInfo);
	
	public int getHisEventCount(ProcessEventTotal eventTotal);
	
	public List<ProcessEventTotal> getHisEventList(ProcessEventTotal eventTotal);
	
	public List<SingleDetail> getSingleDetailList(String transactionId);
	
	public void updateFinancialDocNum(String transaction_id, String financial_doc_num);
	
	public int countSingleTotal(String transaction_id);
	
	public void batchUpdateFinancialDocNum(List<SingleTotal> singleTotalList);
	
	public List<ProEventDetail> getAuditorListByEventId(Long transaction_id);
	
	public int getReimburDetailCount(SingleDetail singleDetail);
	
	public List<SingleDetail> getReimburDetailList(SingleDetail singleDetail);
	
	public List<SingleDetail> getReimburDetailListNoPage(SingleDetail singleDetail);
	
	String getDefaultCompanyCode(Long userId);
	
	int getSalesOrgCount(String orgId);
	
}
