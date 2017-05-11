package com.kintiger.platform.account.service;

import java.io.File;
import java.util.List;

import com.kintiger.platform.account.pojo.PayeeInfo;
import com.kintiger.platform.account.pojo.SingleDetail;
import com.kintiger.platform.account.pojo.SingleTotal;
import com.kintiger.platform.account.pojo.WorkPlanTotal;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;

public interface IAccountService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String SUCCESS_MESSAGE = "操作成功！";

	public static final String ERROR_MESSAGE = "操作失败！";

	public static final String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	public static final String ERROR_NULL_MESSAGE = "操作失败，单据已不存在！";;
	
	public boolean getUserRoles(AllUsers user, String roleId);
	
	public StringResult playMoney(String ids);
	
	public StringResult createSingle(SingleTotal singleTotal);
	
	public int searchTraReimburCount(SingleTotal singleTotal);
	
	public List<SingleTotal> searchTraReimburList(SingleTotal singleTotal);
	
	public int searchSingleDetailCount(SingleDetail singleDetail);
	
	public List<SingleDetail> searchSingleDetailList(SingleDetail singleDetail);
	
	public int searchWorkPlanCount(WorkPlanTotal workPlan);
	
	public List<WorkPlanTotal> searchWorkPlan(WorkPlanTotal workPlan);
	
	public int searchCostTypeCount(CmsTbDict dict);
	
	public List<CmsTbDict> searchCostTypeList(CmsTbDict dict);
	
	public String getStationIdByDetailId(Long eventDetailId);
	
	public StringResult updateSingle(SingleTotal singleTotal);
	
	public SingleTotal searchSingelTotalByPlanId(Long planId, String transaction_id);
	
	public File exportTraReimbur(SingleTotal singleTotal);
	
	public int getPayeeInfoCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayeeInfoList(PayeeInfo payeeInfo);
	
	public StringResult addPayeeInfo(PayeeInfo payeeInfo);
	
	public StringResult removePayeeInfo(String ids, String modifier);
	
	public PayeeInfo getPayeeInfoById(Long id);
	
	public StringResult modifyPayeeInfo(PayeeInfo payeeInfo);
	
	public int getPayeeCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayeeList(PayeeInfo payeeInfo);
	
	public int getPayeeInfoCountByName(String userName);
	
	public PayeeInfo getDefaultPayee(String userName);
	
	public int getPayAccountCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayAccountList(PayeeInfo payeeInfo);
	
	public int getHisEventCount(ProcessEventTotal eventTotal);
	
	public List<ProcessEventTotal> getHisEventList(ProcessEventTotal eventTotal);
	
	public List<SingleDetail> getSingleDetailList(String transactionId);
	
	public StringResult updateFinancialDocNum(String transaction_id, String financial_doc_num);
	
	public int countSingleTotal(String transaction_id);
	
	public StringResult batchUpdateFinancialDocNum(List<SingleTotal> singleTotalList);
	
	public int getReimburDetailCount(SingleDetail singleDetail);
	
	public List<SingleDetail> getReimburDetailList(SingleDetail singleDetail);
	
	public File exportReimberDetailList(SingleDetail singleDetail);
	
	String getDefaultCompanyCode(Long userId);
	
	int getSalesOrgCount(String orgId);
}
