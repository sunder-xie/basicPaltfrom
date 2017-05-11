package com.kintiger.platform.account.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import com.kintiger.platform.account.dao.IAccountDao;
import com.kintiger.platform.account.pojo.PayeeInfo;
import com.kintiger.platform.account.pojo.SingleDetail;
import com.kintiger.platform.account.pojo.SingleTotal;
import com.kintiger.platform.account.pojo.WorkPlanTotal;
import com.kintiger.platform.account.service.IAccountService;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.service.IEventService;

public class AccountServiceImpl implements IAccountService{

	private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);
	
	private IAccountDao accountDao;
	private String downloadPath;

	/**
	 * 保存报销单
	 */
	public StringResult createSingle(SingleTotal singleTotal) {
		StringResult result = new StringResult();
		try{
			Long businessKey = accountDao.saveSingleTotal(singleTotal);
			for(SingleDetail detail: singleTotal.getDetailList()) {
				detail.setPlan_id(businessKey);
            }
			accountDao.saveSingleDetail(singleTotal.getDetailList());
			result.setCode(IEventService.SUCCESS);
		} catch(Exception e){
			logger.error(e);
			result.setCode(IEventService.ERROR);
			result.setResult(IEventService.ERROR_MESSAGE);
		}
		return result;
	}
	
	/**
	 *查询当前用户角色
	 */
	public boolean getUserRoles(AllUsers user, String roleId){
		user.setRoleIds(roleId);
		boolean flag = false;
		try{
			flag = accountDao.getUserRoles(user) > 0; 
		}catch(Exception e){
			logger.error(LogUtil.parserBean(user), e);
		}
		return flag;
	}
	
	/**
	 * 查询报销单数量(分页)
	 */
	public int searchTraReimburCount(SingleTotal singleTotal){
		try {
			return accountDao.searchTraReimburCount(singleTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(singleTotal), e);
		}
		return 0;
	}
	
	/**
	 * 查询报销单(分页)
	 */
	public List<SingleTotal> searchTraReimburList(SingleTotal singleTotal){
		try{
			return accountDao.searchTraReimburList(singleTotal);
		}catch(Exception e){
			logger.error(LogUtil.parserBean(singleTotal), e);
		}
		return null;
	}
	
	/**
	 * 查询报销单明细数量(分页)
	 */
	public int searchSingleDetailCount(SingleDetail singleDetail){
		try {
			return accountDao.searchSingleDetailCount(singleDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(singleDetail), e);
		}
		return 0;
	}
	
	/**
	 * 查询报销单明细(分页)
	 */
	public List<SingleDetail> searchSingleDetailList(SingleDetail singleDetail){
		try{
			return accountDao.searchSingleDetailList(singleDetail);
		}catch(Exception e){
			logger.error(LogUtil.parserBean(singleDetail), e);
		}
		return null;
	}
	
	/**
	 * 通过主键查询报销单(含明显内容，不分页)
	 */
	public SingleTotal searchSingelTotalByPlanId(Long planId, String transaction_id){
		try{
			SingleTotal sinTotal = accountDao.searchSingleTotalByPlanId(planId);
			List<SingleDetail> detailList = accountDao.searchSingleDetailByPlanId(planId);
			List<ProEventDetail> eventDetailList = accountDao.getAuditorListByEventId(Long.parseLong(transaction_id));
			StringBuilder sb = new StringBuilder();
			for(ProEventDetail eventDetail : eventDetailList){
				if(sb.length() == 0) {
					sb.append(eventDetail.getCurUserName());
				} else {
					sb.append("、" + eventDetail.getCurUserName());
				}
			}
			sinTotal.setDetailList(detailList);
			sinTotal.setAuditor(sb.toString());
			return sinTotal;
		}catch(Exception e){
			logger.error(LogUtil.parserBean(planId), e);
		}
		return null;
	}
	
	/**
	 * 打款
	 */
	public StringResult playMoney(String ids){
		StringResult result = new StringResult();
		try{
			accountDao.playMoney(ids);
			result.setCode(IAccountService.SUCCESS);
			result.setResult(IAccountService.SUCCESS_MESSAGE);
		}catch(Exception e){
			logger.error(e);
			result.setCode(IAccountService.ERROR);
			result.setResult(IAccountService.ERROR_MESSAGE);
		}
		return result;
	}
	
	/**
	 * 将报销单导出到Excel中
	 */
	public File exportTraReimbur(SingleTotal singleTotal){
		try{
			// 建立空模板文件保存路径
			File saveDir = new File(downloadPath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			File rootFile = new File(downloadPath + "/" + "emptyBasis.xls");
			if (!rootFile.exists()) {
				rootFile.createNewFile();
			}
			
			List<SingleTotal> singleTotalList = accountDao.searchTraReimburListToExcel(singleTotal);
			// 写数据到Excel表格中
			WritableWorkbook book = Workbook.createWorkbook(rootFile);
			WritableSheet sheet = book.createSheet("Sheet_1", 0);
			//	设置字体样式
			WritableFont fontHead = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
			WritableCellFormat formatHead = new WritableCellFormat(fontHead);
			formatHead.setAlignment(Alignment.CENTRE);
			formatHead.setVerticalAlignment(VerticalAlignment.CENTRE);
			formatHead.setBackground(jxl.format.Colour.YELLOW); // 设置单元格的背景颜色
			
			WritableCellFormat formatTable = new WritableCellFormat();
			formatTable.setAlignment(Alignment.CENTRE);
			formatTable.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			Label label;
			jxl.write.Number labelNum;
			BigDecimal total = new BigDecimal(0);
			// 设置第一行行高
			sheet.setRowView(0, 400);
			label = new Label(0, 0, "收款人", formatHead);
			sheet.addCell(label);
			// 设置列宽
			sheet.setColumnView(0, 20);
			
			label = new Label(1, 0, "收款账号", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(1, 40);
			
			label = new Label(2, 0, "实际审核后金额", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(2, 20);
			
			label = new Label(3, 0, "事务ID", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(3, 20);
			
			label = new Label(4, 0, "报销单抬头说明", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(4, 50);
			
			label = new Label(5, 0, "会计凭证号", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(5, 20);
			
			for(int i=0;i<singleTotalList.size();i++){
				label = new Label(0, i+1, singleTotalList.get(i).getPay_ee(), formatTable);
				sheet.addCell(label);
				label = new Label(1, i+1, singleTotalList.get(i).getPay_account(), formatTable);
				sheet.addCell(label);
				labelNum = new jxl.write.Number(2, i+1, singleTotalList.get(i).getAudit_money().doubleValue(), formatTable);// 数值格式
				sheet.addCell(labelNum);
				label = new Label(3, i+1, singleTotalList.get(i).getTransaction_id(), formatTable);
				sheet.addCell(label);
				label = new Label(4, i+1, singleTotalList.get(i).getMemo(), formatTable);
				sheet.addCell(label);
				label = new Label(5, i+1, singleTotalList.get(i).getFinancial_doc_num(), formatTable);
				sheet.addCell(label);
				total = total.add(singleTotalList.get(i).getAudit_money());
			}
			label = new Label(2, singleTotalList.size()+1, "SUM:"+total.toString(), formatTable);
			sheet.addCell(label);
			book.write();
			book.close();
			return rootFile;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	public StringResult updateSingle(SingleTotal singleTotal) {
		StringResult result = new StringResult();
		List<SingleDetail> singleDetailListForInsert = new ArrayList<SingleDetail>();
		List<SingleDetail> singleDetailListForUpdate = new ArrayList<SingleDetail>();
		try{
			accountDao.updateSingleTotal(singleTotal);
			for(SingleDetail singleDetail : singleTotal.getDetailList()){
				if(singleDetail.getDetail_id() != null){
					singleDetailListForUpdate.add(singleDetail);
				}else{
					singleDetailListForInsert.add(singleDetail);
				}
			}
			accountDao.updateSingleDetail(singleDetailListForUpdate);
			accountDao.saveSingleDetail(singleDetailListForInsert);
			accountDao.updateCostCenter(singleTotal);
			result.setCode(IAccountService.SUCCESS);
		} catch(Exception e){
			logger.error(e);
			e.printStackTrace();
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}
	
	public int searchWorkPlanCount(WorkPlanTotal workPlan){
		try {
			return accountDao.searchWorkPlanCount(workPlan);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(workPlan), e);
		}
		return 0;
	}
	
	/**
	 * 选择项目(分页)
	 */
	public List<WorkPlanTotal> searchWorkPlan(WorkPlanTotal workPlan){
		try{
			return accountDao.searchWorkPlan(workPlan);
		}catch(Exception e){
			logger.error(LogUtil.parserBean(workPlan), e);
		}
		return null;
	}
	
	public int searchCostTypeCount(CmsTbDict dict){
		try {
			return accountDao.searchCostTypeCount(dict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dict), e);
		}
		return 0;
	}
	
	public List<CmsTbDict> searchCostTypeList(CmsTbDict dict){
		try{
			return accountDao.searchCostTypeList(dict);
		}catch(Exception e){
			logger.error(LogUtil.parserBean(dict), e);
		}
		return null;
	}

	public String getStationIdByDetailId(Long eventDetailId) {
		try {
			return accountDao.getStationIdByDetailId(eventDetailId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public int getPayeeInfoCount(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayeeInfoCount(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		} 
		return 0;
	}

	public List<PayeeInfo> getPayeeInfoList(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayeeInfoList(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public StringResult addPayeeInfo(PayeeInfo payeeInfo) {
		StringResult result = new StringResult();
		try {
			accountDao.addPayeeInfo(payeeInfo);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}
	
	public StringResult removePayeeInfo(String ids, String modifier) {
		StringResult result = new StringResult();
		try {
			accountDao.removePayeeInfo(ids, modifier);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}
	
	public PayeeInfo getPayeeInfoById(Long id) {
		try {
			return accountDao.getPayeeInfoById(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public StringResult modifyPayeeInfo(PayeeInfo payeeInfo) {
		StringResult result = new StringResult();
		try {
			accountDao.modifyPayeeInfo(payeeInfo);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}
	
	public int getPayeeCount(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayeeCount(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	public List<PayeeInfo> getPayeeList(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayeeList(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public int getPayeeInfoCountByName(String userName) {
		try {
			return accountDao.getPayeeInfoCountByName(userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	public PayeeInfo getDefaultPayee(String userName) {
		try {
			return accountDao.getDefaultPayee(userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public int getPayAccountCount(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayAccountCount(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	public List<PayeeInfo> getPayAccountList(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayAccountList(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public int getHisEventCount(ProcessEventTotal eventTotal) {
		try {
			return accountDao.getHisEventCount(eventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	};
	
	public List<ProcessEventTotal> getHisEventList(ProcessEventTotal eventTotal) {
		try {
			return accountDao.getHisEventList(eventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	};
	
	public List<SingleDetail> getSingleDetailList(String transactionId) {
		try {
			return accountDao.getSingleDetailList(transactionId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public StringResult updateFinancialDocNum(String transaction_id,
			String financial_doc_num) {
		StringResult result = new StringResult();
		try {
			accountDao.updateFinancialDocNum(transaction_id, financial_doc_num);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}
	
	public int countSingleTotal(String transaction_id) {
		try {
			return accountDao.countSingleTotal(transaction_id);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	public StringResult batchUpdateFinancialDocNum(
			List<SingleTotal> singleTotalList) {
		StringResult result = new StringResult();
		try {
			accountDao.batchUpdateFinancialDocNum(singleTotalList);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}
	
	public int getReimburDetailCount(SingleDetail singleDetail) {
		try {
			return accountDao.getReimburDetailCount(singleDetail);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	public List<SingleDetail> getReimburDetailList(SingleDetail singleDetail) {
		try {
			return accountDao.getReimburDetailList(singleDetail);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public File exportReimberDetailList(SingleDetail singleDetail) {

		try{
			// 建立空模板文件保存路径
			File saveDir = new File(downloadPath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			File rootFile = new File(downloadPath + "/" + "emptyBasis.xls");
			if (!rootFile.exists()) {
				rootFile.createNewFile();
			}

			List<SingleDetail> singleDetailList = accountDao.getReimburDetailListNoPage(singleDetail);
			// 写数据到Excel表格中
			WritableWorkbook book = Workbook.createWorkbook(rootFile);
			WritableSheet sheet = book.createSheet("Sheet_1", 0);
			//	设置字体样式
			WritableFont fontHead = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
			WritableCellFormat formatHead = new WritableCellFormat(fontHead);
			formatHead.setAlignment(Alignment.CENTRE);
			formatHead.setVerticalAlignment(VerticalAlignment.CENTRE);
			formatHead.setBackground(jxl.format.Colour.YELLOW); // 设置单元格的背景颜色
			
			WritableCellFormat formatTable = new WritableCellFormat();
			formatTable.setAlignment(Alignment.CENTRE);
			formatTable.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			Label label;
			jxl.write.Number labelNum;
			// 设置第一行行高
			sheet.setRowView(0, 400);
			label = new Label(0, 0, "事务ID", formatHead);
			sheet.addCell(label);
			// 设置列宽
			sheet.setColumnView(0, 20);
			
			label = new Label(1, 0, "收款人", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(1, 20);
			
			label = new Label(2, 0, "项目", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(2, 20);
			
			label = new Label(3, 0, "项目经理", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(3, 20);
			
			label = new Label(4, 0, "费用类型", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(4, 20);
			
			label = new Label(5, 0, "费用日期", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(5, 20);
			
			label = new Label(6, 0, "开支用途", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(6, 40);
			
			label = new Label(7, 0, "发票张数", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(7, 20);
			
			label = new Label(8, 0, "发票金额", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(8, 20);
			
			label = new Label(9, 0, "实际金额", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(9, 20);
			
			label = new Label(10, 0, "备注", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(10, 40);
			
			label = new Label(11, 0, "财务处理时间", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(11, 20);
			
			
			String temp1="yyyy-MM-dd hh:mm:ss";
			SimpleDateFormat formatter1 = new SimpleDateFormat (temp1);
			String temp2="yyyy-MM-dd";
			SimpleDateFormat formatter2 = new SimpleDateFormat (temp2);
			for(int i=0; i<singleDetailList.size(); i++){
				labelNum = new jxl.write.Number(0, i+1, singleDetailList.get(i).getTransaction_id().doubleValue(), formatTable);// 数值格式
				sheet.addCell(labelNum);
				label = new Label(1, i+1, singleDetailList.get(i).getPayee(), formatTable);
				sheet.addCell(label);
				label = new Label(2, i+1, singleDetailList.get(i).getProject(), formatTable);
				sheet.addCell(label);
				label = new Label(3, i+1, singleDetailList.get(i).getProject_manager(), formatTable);
				sheet.addCell(label);
				label = new Label(4, i+1, singleDetailList.get(i).getCost_type_content(), formatTable);
				sheet.addCell(label);
				label = new Label(5, i+1, singleDetailList.get(i).getCost_date()==null ? "" : formatter2.format(singleDetailList.get(i).getCost_date()), formatTable);
				sheet.addCell(label);
				label = new Label(6, i+1, singleDetailList.get(i).getCost_purpose(), formatTable);
				sheet.addCell(label);
				labelNum = new jxl.write.Number(7, i+1, singleDetailList.get(i).getInvoice_num().doubleValue(), formatTable);
				sheet.addCell(labelNum);
				labelNum = new jxl.write.Number(8, i+1, singleDetailList.get(i).getInvoice_amount().doubleValue(), formatTable);
				sheet.addCell(labelNum);
				label = new Label(9, i+1, singleDetailList.get(i).getAudit_money()==null ? "" : singleDetailList.get(i).getAudit_money().toString(), formatTable);
				sheet.addCell(label);
				label = new Label(10, i+1, singleDetailList.get(i).getCost_memo(), formatTable);
				sheet.addCell(label);
				label = new Label(11, i+1, singleDetailList.get(i).getFinancial_operate_date()==null ? "" : formatter1.format(singleDetailList.get(i).getFinancial_operate_date()), formatTable);
				sheet.addCell(label);
			}
			book.write();
			book.close();
			return rootFile;
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	
	}
	
	public String getDefaultCompanyCode(Long userId) {
		try {
			return accountDao.getDefaultCompanyCode(userId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public int getSalesOrgCount(String orgId) {
		try {
			return accountDao.getSalesOrgCount(orgId);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	public IAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

}
