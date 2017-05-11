package com.kintiger.platform.account.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.account.pojo.BugetChange;
import com.kintiger.platform.account.pojo.PayeeInfo;
import com.kintiger.platform.account.pojo.SingleDetail;
import com.kintiger.platform.account.pojo.SingleTotal;
import com.kintiger.platform.account.pojo.WorkPlanTotal;
import com.kintiger.platform.account.service.IAccountService;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.JavaBeanXMLUtil;
import com.kintiger.platform.framework.util.XMLInfo;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.webservice.pojo.UserUtil;
import com.kintiger.platform.webservice.resps.JsonUtil;
import com.kintiger.platform.webservice.service.IWebService;
import com.kintiger.platform.wfe.service.IEventService;

public class AccountAction extends BaseAction {

	private static final long serialVersionUID = 6050882117631405933L;
	private static final Logger logger = Logger.getLogger(AccountAction.class);

	private IAccountService accountService;
	private IEventService eventService;
	private IOrgService orgService;
	private IWebService webService;

	private String userId;
	@Decode
	private String userName;
	private String orgId;
	private String orgName;
	private String key; // 流程模板
	@Decode
	private String title; // 事务标题
	private String eventId; // 事务ID
	private String status; // 事务状态
	private Date startDate;
	private Date endDate;

	private String costCenterText;
	private String costCenter;
	private String payType;
	private String playMoneyFlag;

	private Date cost_date;
	private String cost_purpose;

	private String toDoDetail;
	private String curStaId;

	/**
	 * 附件信息
	 */
	private File[] upload;
	private String[] uploadFileName;

	private SingleTotal singleTotal;// 报销总单
	private String detailJsonStr; // 明细Json字符串

	private String modelKey; // 流程扭转参数
	private String modelValues; // 流程扭转参数值

	private UserUtil userUtil; // 下个处理人列表
	private String nextUserId; // 下一处理人

	private String appUrl;
	private String xmlFilePath; // Xml文件路径
	private String subFolders;

	private int total = 0;
	private List<SingleTotal> singleTotalList;
	private List<SingleDetail> singleDetailList;
	private List<WorkPlanTotal> planList;
	private List<CmsTbDict> costTypeList;
	private List<PayeeInfo> payeeInfoList;
	private List<ProcessEventTotal> proEventTotalList;

	private String modifyFlag;
	private String planId;
	private String transactionId;
	private String projectId;
	private PayeeInfo payeeInfo;
	@Decode
	private String payee;
	private String payAccount;
	private String payArea;
	private String payBank;
	private String payAreaCode;
	private String payBankAlias;
	private String payBankAliCode;
	private String payBankCode;
	private String accountAlias;
	private String email;
	private String id;
	private String ids;
	private String operateFlag;
	private String searchStr;
	private String transaction_id;
	private String financial_doc_num;
	private File uploadFile;
	private String searchStrFlag;
	private String type;
	private String pro_manager_id;

	@Decode
	private String project;
	@Decode
	private String costTypeContent;
	private String download;	// 是否下载完成标识
	
	private String flag;
	
	private BugetChange bugetChange;
	
	private String companyCode;
	
	private String orgFlag;

	/**
	 * 跳转支出申办单
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createSinglePrepare() {
		// 提交者
		userId = getUser().getUserId();
		userName = getUser().getUserName();
		Borg org = orgService.getOrgByOrgId(getUser().getOrgId());
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		return CREATE_PREPARE + "_Single";
	}

	/**
	 * 跳转差旅费报销
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createTraReimburPrepare() {
		// 提交者
		userId = getUser().getUserId();
		userName = getUser().getUserName();
		Borg org = orgService.getOrgByOrgId(getUser().getOrgId());
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		total = accountService.getPayeeInfoCountByName(userName);
		if (total > 0) {
			payeeInfo = accountService.getDefaultPayee(userName);
		}
		return CREATE_PREPARE + "_Tra";
	}

	/**
	 * 打印报销单
	 * 
	 * @return
	 */
	@PermissionSearch
	public String printTraReimbur() {
		singleTotal = accountService.searchSingelTotalByPlanId(
				Long.parseLong(planId.trim()), transaction_id);
		return "printTraReimbur";
	}

	@PermissionSearch
	public String searchTraReimburPrepare() {
		// 判断当前用户是否为出纳角色
		if (accountService.getUserRoles(this.getUser(), "cashier")) {
			modifyFlag = "Y";
		} else {
			modifyFlag = "N";
		}
		return "searchTraReimburPrepare";
	}

	/**
	 * 查询报销单
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "singleTotalList", include = { "plan_id",
			"transaction_id", "title", "pay_ee", "cost_org_name",
			"cost_center_name", "total_money", "audit_money", "pay_type",
			"create_date", "has_play_money", "status", "financial_doc_num" }, total = "total")
	public String searchTraReimbur() {
		SingleTotal singleTotal = new SingleTotal();
		singleTotal.setStart(getStart());
		singleTotal.setEnd(getEnd());
		// 判断当前用户是否为财务审批角色、VP,如果不是只能查看自己的报销单，如果是可以查看全部报销单
		if (!(accountService.getUserRoles(this.getUser(), "financial") || accountService
				.getUserRoles(this.getUser(), "f_manager"))) {
			singleTotal.setUser_id(this.getUser().getUserId());
		}
		try {
			if (StringUtils.isNotEmpty(eventId)
					&& StringUtils.isNotEmpty(eventId.trim())) {
				singleTotal.setEventId(eventId.trim());
			}
			if (StringUtils.isNotEmpty(title)
					&& StringUtils.isNotEmpty(title.trim())) {
				singleTotal.setTitle(title.trim());
			}
			if (StringUtils.isNotEmpty(status)
					&& StringUtils.isNotEmpty(status.trim())) {
				singleTotal.setStatus(status.trim());
			}
			if (StringUtils.isNotEmpty(payee)
					&& StringUtils.isNotEmpty(payee.trim())) {
				singleTotal.setPay_ee(payee.trim());
			}
			if (startDate != null) {
				singleTotal.setStartDate(startDate);
			}
			if (endDate != null) {
				singleTotal.setEndDate(endDate);
			}
			if (StringUtils.isNotEmpty(playMoneyFlag)
					&& StringUtils.isNotEmpty(playMoneyFlag.trim())) {
				singleTotal.setHas_play_money(playMoneyFlag.trim());
			}
		} catch (Exception e) {
			logger.error(e);
		}
		total = accountService.searchTraReimburCount(singleTotal);
		if (total != 0) {
			singleTotalList = accountService.searchTraReimburList(singleTotal);
		}
		return JSON;
	}

	public String searchSingleDetailPrepare() {
		return "searchSingleDetailPrepare";
	}

	/**
	 * 查询报销单明细
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "singleDetailList", include = { "detail_id",
			"cost_type_content", "cost_date", "cost_purpose", "invoice_num",
			"invoice_amount", "cost_memo", "financial_doc_num" }, total = "total")
	public String searchSingleDetail() {
		SingleDetail singleDetail = new SingleDetail();
		singleDetail.setPlan_id(Long.parseLong(planId.trim()));
		singleDetail.setStart(getStart());
		singleDetail.setEnd(getEnd());
		try {
			if (cost_date != null) {
				singleDetail.setCost_date(cost_date);
			}
			if (StringUtils.isNotEmpty(cost_purpose)
					&& StringUtils.isNotEmpty(cost_purpose.trim())) {
				cost_purpose = new String(getServletRequest().getParameter(
						"cost_purpose").getBytes("ISO8859-1"), "UTF-8");
				singleDetail.setCost_purpose(cost_purpose.trim());
			}
		} catch (Exception e) {
			logger.error("cost_date:" + cost_date + "cost_purpose:"
					+ cost_purpose, e);
		}
		total = accountService.searchSingleDetailCount(singleDetail);
		if (total != 0) {
			singleDetailList = accountService
					.searchSingleDetailList(singleDetail);
		}
		return JSON;
	}

	/**
	 * 打款
	 * 
	 * @return
	 */
	public String playMoney() {
		StringResult result = accountService.playMoney(planId);
		if (IAccountService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(result.getResult());
		} else {
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 导出到Excel(查询的全部结果,对于财务,需要查看全部报销单)
	 * 
	 * @return
	 */
	public String downLoadExcel() {
		SingleTotal singleTotal = new SingleTotal();
		try {
			if (StringUtils.isNotEmpty(eventId)
					&& StringUtils.isNotEmpty(eventId.trim())) {
				singleTotal.setEventId(eventId.trim());
			}
			if (StringUtils.isNotEmpty(title)
					&& StringUtils.isNotEmpty(title.trim())) {
				singleTotal.setTitle(title.trim());
			}
			if (StringUtils.isNotEmpty(status)
					&& StringUtils.isNotEmpty(status.trim())) {
				singleTotal.setStatus(status.trim());
			}
			if (StringUtils.isNotEmpty(userName)
					&& StringUtils.isNotEmpty(userName.trim())) {
				singleTotal.setUser_name(userName.trim());
			}
			if (startDate != null) {
				singleTotal.setStartDate(startDate);
			}
			if (endDate != null) {
				singleTotal.setEndDate(endDate);
			}
			if (StringUtils.isNotEmpty(playMoneyFlag)
					&& StringUtils.isNotEmpty(playMoneyFlag.trim())) {
				singleTotal.setHas_play_money(playMoneyFlag.trim());
			}
			File source = accountService.exportTraReimbur(singleTotal);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
			if (source != null) {
				display(source, "报销单_" + df.format(new Date()) + ".xls",
						ServletActionContext.getResponse());
				source.delete();
			} else {
				this.setFailMessage("Excel报销单导出出错");
			}
		} catch (Exception e) {
			this.setFailMessage("Excel报销单导出出错");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 启动选择下个处理人
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result" })
	public String selectNexUser() {
		Object[] res = new Object[4];
		res[0] = key;
		res[1] = userId;
		res[2] = "projectId";
		res[3] = projectId;
		userUtil = webService.startWorkflowFix(res);
		return JSON;
	}

	/**
	 * 取消下个处理人
	 * 
	 * @return
	 */
	public String cancelNextUser() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		try {
			webService.cancelEvent(eventId);
		} catch (Exception e) {
			logger.error(e);
		}
		this.setSuccessMessage("ok");
		return RESULT_MESSAGE;
	}

	/**
	 * 获取下个处理人并处理
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String processWorkflowFix() {
		this.setSuccessMessage("");
		this.setFailMessage("");

		Object[] res = new Object[7];
		res[0] = eventId;
		res[1] = userId;
		res[2] = nextUserId;
		res[3] = title;
		res[4] = appUrl + "/account/accountAction!searchExpenseForm.jspa";
		res[5] = key;
		res[6] = "";
		List<SingleDetail> detailList = JsonUtil.getDTOList(detailJsonStr,
				SingleDetail.class);
		singleTotal.setDetailList(detailList);
		singleTotal.setTransaction_id(eventId);
		singleTotal.setUser_id(this.getUser().getUserId());
		singleTotal.setCreator_id(Long.parseLong(this.getUser().getUserId()));
		singleTotal.setOperator_id(Long.parseLong(this.getUser().getUserId()));
		StringResult strResult = accountService.createSingle(singleTotal); // 保存报销单进表

		if (IAccountService.SUCCESS.equals(strResult.getCode())) {
			String result = webService.processWorkflowFix(res);
			if ("success".equals(result)) {
				String detailId = eventService.getProEventDetail(eventId,
						getUser().getUserId());
				// 保存附件
				if (upload != null && upload.length > 0) {
					eventService.processAttachments(upload, uploadFileName,
							Long.valueOf(detailId),
							String.valueOf(new Date().getTime()), key);
				}
				// 写XML文件
				if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + eventId
						+ ".xml", singleTotal, getUser().getUserId(), getUser()
						.getUserName(), null)) {
					this.setFailMessage("写入XML出错");
				}
				this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
			} else {
				this.setFailMessage("启动失败");
			}
		} else {
			this.setFailMessage(strResult.getResult());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 事务审批过程中查看内容
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchExpenseForm() {
		/*
		 * if(toDoDetail!=null){ curStaId =
		 * accountService.getStationIdByDetailId(Long.valueOf(toDoDetail)); }
		 */
		singleTotal = new SingleTotal();
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, singleTotal);
			if (info != null) {
				singleTotal = (SingleTotal) info.getObject();
			}
		}
		return "searchExpenseForm";
	}

	/**
	 * 查询项目
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "planList", include = { "totalId", "title" }, total = "total")
	public String searchWorkPlan() {
		planList = new ArrayList<WorkPlanTotal>();
		WorkPlanTotal plan = new WorkPlanTotal();
		if ("process".equals(type)) {// 报销处理时根据提报人查项目
			plan.setPro_manager_id(Long.parseLong(pro_manager_id));
		} else {
			plan.setPro_manager_id(Long.parseLong(getUser().getUserId()));
		}
		if (StringUtils.isNotEmpty(title)
				&& StringUtils.isNotEmpty(title.trim())) {
			try {
				title = new String(getServletRequest().getParameter("title")
						.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			plan.setTitle(title);
		}
		plan.setStart(getStart());
		plan.setEnd(getEnd());
		total = accountService.searchWorkPlanCount(plan);
		if (total != 0) {
			planList = accountService.searchWorkPlan(plan);
		}
		return JSON;
	}

	/**
	 * 获取费用类型
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "costTypeList", include = { "itemId", "itemValue",
			"itemName" }, total = "total")
	public String searchCostType() {
		costTypeList = new ArrayList<CmsTbDict>();
		CmsTbDict dict = new CmsTbDict();
		dict.setDictTypeValue("costType");
		if (StringUtils.isNotEmpty(title)
				&& StringUtils.isNotEmpty(title.trim())) {
			try {
				title = new String(getServletRequest().getParameter("title")
						.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			dict.setItemName(title);
		}
		dict.setStart(getStart());
		dict.setEnd(getEnd());
		total = accountService.searchCostTypeCount(dict);
		if (total != 0) {
			costTypeList = accountService.searchCostTypeList(dict);
		}
		return JSON;
	}

	@SuppressWarnings("unchecked")
	public String updateExpenseForm() {
		List<SingleDetail> detailList = JsonUtil.getDTOList(detailJsonStr,
				SingleDetail.class);
		singleTotal.setDetailList(detailList);
		singleTotal.setOperator_id(Long.parseLong(this.getUser().getUserId()));
		String xmlFile = eventService.getEventTotalById(
				Long.valueOf(singleTotal.getTransaction_id())).getSubFolders();
		// 更新XML文件
		if (!JavaBeanXMLUtil.JavaBean2XML(
				xmlFilePath + "/" + singleTotal.getTransaction_id() + ".xml",
				singleTotal, getUser().getUserId(), getUser().getUserName(),
				xmlFile)) {
			this.setFailMessage("更新XML出错!");
			return RESULT_MESSAGE;
		}
		StringResult stringResult = accountService.updateSingle(singleTotal);
		if (IAccountService.SUCCESS.equals(stringResult.getCode())) {
			this.setSuccessMessage("操作成功!");
		} else {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 文件下载
	 * 
	 * @param file
	 * @param fileName
	 * @param response
	 * @return6
	 */
	@SuppressWarnings("finally")
	@PermissionSearch
	private boolean display(File file, String fileName,
			HttpServletResponse response) {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ fileName);
			in = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			response.flushBuffer();
		} catch (Exception ex) {
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e) {
					logger.error(e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final Exception e) {
					logger.error(e);
				}
			}
			return true;
		}
	}

	/**
	 * 收款人信息维护页面跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchPayeeInfo() {
		return "toSearchPayeeInfo";
	}

	/**
	 * 收款人信息列表查询
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "payeeInfoList", include = { "id", "payee",
			"payAccount", "payArea", "payBank", "payAreaCode", "payBankAlias",
			"payBankAliCode", "payBankCode", "accountAlias", "email" }, total = "total")
	public String getPayeeInfoJsonList() {
		payeeInfoList = new ArrayList<PayeeInfo>();
		payeeInfo = new PayeeInfo();
		if (StringUtils.isNotEmpty(payee) && StringUtils.isNotEmpty(payee))
			payeeInfo.setPayee(payee.trim());
		if (StringUtils.isNotEmpty(payAccount)
				&& StringUtils.isNotEmpty(payAccount))
			payeeInfo.setPayAccount(payAccount.trim());
		payeeInfo.setStart(getStart());
		payeeInfo.setEnd(getEnd());
		total = accountService.getPayeeInfoCount(payeeInfo);
		if (total != 0) {
			payeeInfoList = accountService.getPayeeInfoList(payeeInfo);
		}
		return JSON;
	}

	/**
	 * 新增收款人页面跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toAddPayeeInfo() {
		return "payeeInfo";
	}

	/**
	 * 新增收款人
	 * 
	 * @return
	 */
	public String addPayeeInfo() {
		payeeInfo = new PayeeInfo();
		payeeInfo.setPayee(payee.trim());
		payeeInfo.setPayAccount(payAccount.trim());
		payeeInfo.setPayArea(payArea.trim());
		payeeInfo.setPayBank(payBank.trim());
		payeeInfo.setPayAreaCode(payAreaCode.trim());
		payeeInfo.setPayBankAlias(payBankAlias.trim());
		payeeInfo.setPayBankAliCode(payBankAliCode.trim());
		payeeInfo.setPayBankCode(payBankCode.trim());
		payeeInfo.setEmail(email.trim());
		payeeInfo.setCreator(getUser().getUserId());
		StringResult result = accountService.addPayeeInfo(payeeInfo);
		if (IAccountService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("操作成功!");
		} else {
			this.setFailMessage("操作失败!");
		}
		;
		return RESULT_MESSAGE;
	}

	/**
	 * 收款人删除
	 * 
	 * @return
	 */
	public String removePayeeInfo() {
		StringResult result = accountService.removePayeeInfo(ids, getUser()
				.getUserId());
		if (IAccountService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("操作成功!");
		} else {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 收款人信息修改页面跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toModifyPayeeInfo() {
		payeeInfo = accountService.getPayeeInfoById(Long.valueOf(id));
		return "payeeInfo";
	}

	/**
	 * 收款人信息修改
	 * 
	 * @return
	 */
	public String modifyPayeeInfo() {
		payeeInfo = new PayeeInfo();
		payeeInfo.setId(Long.valueOf(id));
		payeeInfo.setPayee(payee.trim());
		payeeInfo.setPayAccount(payAccount.trim());
		payeeInfo.setPayArea(payArea.trim());
		payeeInfo.setPayBank(payBank.trim());
		payeeInfo.setPayAreaCode(payAreaCode.trim());
		payeeInfo.setPayBankAlias(payBankAlias.trim());
		payeeInfo.setPayBankAliCode(payBankAliCode.trim());
		payeeInfo.setPayBankCode(payBankCode.trim());
		payeeInfo.setEmail(email.trim());
		payeeInfo.setModifier(getUser().getUserId());
		StringResult result = accountService.modifyPayeeInfo(payeeInfo);
		if (IAccountService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("操作成功!");
		} else {
			this.setFailMessage("操作失败!");
		}
		;
		return RESULT_MESSAGE;
	}

	/**
	 * 查找收款人信息
	 * 
	 * @return
	 */
	@JsonResult(field = "payeeInfoList", include = { "payee" }, total = "total")
	@PermissionSearch
	public String getPayeeJsonList() {
		payeeInfo = new PayeeInfo();
		if (StringUtils.isNotEmpty(searchStr)
				&& StringUtils.isNotEmpty(searchStr.trim())) {
			try {
				searchStr = new String(getServletRequest().getParameter(
						"searchStr").getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			payeeInfo.setSearchStr(searchStr);
		}
		payeeInfo.setStart(getStart());
		payeeInfo.setEnd(getEnd());
		total = accountService.getPayeeCount(payeeInfo);
		if (total != 0) {
			payeeInfoList = accountService.getPayeeList(payeeInfo);
		}
		return JSON;
	}

	/**
	 * 查找账号信息
	 * 
	 * @return
	 */
	@JsonResult(field = "payeeInfoList", include = { "id", "payAccount" })
	@PermissionSearch
	public String getPayAccountJsonList() {
		payeeInfo = new PayeeInfo();
		if (StringUtils.isNotEmpty(payee)
				&& StringUtils.isNotEmpty(payee.trim())) {
			try {
				payee = new String(getServletRequest().getParameter("payee")
						.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			payeeInfo.setPayee(payee);
		}
		payeeInfoList = accountService.getPayAccountList(payeeInfo);
		return JSON;
	}

	/**
	 * 查询默认银行账号
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "id")
	public String getDefaultPayAccount() {
		try {
			payee = new String(getServletRequest().getParameter("payee")
					.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		id = accountService.getDefaultPayee(payee).getId().toString();
		return JSON;
	}

	/**
	 * 新增收款账号页面跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toAddNewAccount() {
		try {
			payee = new String(getServletRequest().getParameter("payee")
					.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return "toAddNewAccount";
	}

	/**
	 * 历史报销事务查询
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "proEventTotalList", include = { "eventId",
			"eventTitle", "creatdate" }, total = "total")
	public String getHisEventJsonList() {
		ProcessEventTotal eventTotal = new ProcessEventTotal();
		if (StringUtils.isNotEmpty(searchStr)
				&& StringUtils.isNotEmpty(searchStr.trim())) {
			try {
				searchStr = new String(getServletRequest().getParameter(
						"searchStr").getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			eventTotal.setEventTitle(searchStr);
		}
		eventTotal.setStart(getStart());
		eventTotal.setEnd(getEnd());
		eventTotal.setInitator(getUser().getUserId());
		total = accountService.getHisEventCount(eventTotal);
		if (total != 0) {
			proEventTotalList = accountService.getHisEventList(eventTotal);
		}
		return JSON;
	}

	/**
	 * 查询报销单明细
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "singleDetailList", include = { "detail_id",
			"cost_type", "cost_type_content", "cost_date", "cost_purpose",
			"invoice_num", "invoice_amount", "cost_memo" })
	public String getSingleDetailJsonList() {
		singleDetailList = accountService.getSingleDetailList(transactionId);
		return JSON;
	}

	/**
	 * 更新财务凭证号
	 * 
	 * @return
	 */
	public String updateFinancialDocNum() {
		System.out.println(transaction_id);
		System.out.println(financial_doc_num);
		StringResult result = accountService.updateFinancialDocNum(
				transaction_id, financial_doc_num);
		if (IAccountService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("操作成功!");
		} else {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 会计凭证号导入页面跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toImportFinancialDocNum() {
		return "toImportFinancialDocNum";
	}

	/**
	 * 财务凭证号导入
	 * 
	 * @return
	 */
	public String importData() {

		FileInputStream fileIn = null;
		Workbook rwb = null;
		StringBuilder titleResult = new StringBuilder();
		StringBuilder contentResult = new StringBuilder();

		try {
			fileIn = new FileInputStream(uploadFile);
			rwb = Workbook.getWorkbook(fileIn);
			Sheet rs = rwb.getSheet(0);
			int row = rs.getRows();
			int column = rs.getColumns();

			if (row == 0 && column == 0) {
				this.setFailMessage("导入的Excel为空！");
				return RESULT_MESSAGE;
			} else {
				if (!"事务ID".equals(rs.getCell(0, 0).getContents().trim())) {
					titleResult.append("模板中第1行1列字段名错误--事务ID！   ");
				}
				if (!"会计凭证号".equals(rs.getCell(1, 0).getContents().trim())) {
					titleResult.append("模板中第1行2列字段名错误--会计凭证号！   ");
				}
				if (!"".equals(titleResult.toString())) {
					this.setFailMessage(titleResult.toString());
					return RESULT_MESSAGE;
				} else {
					List<SingleTotal> list = new ArrayList<SingleTotal>();
					for (int i = 1; i < row; i++) {
						singleTotal = new SingleTotal();
						if ("".equals(rs.getCell(0, i).getContents().trim())
								&& "".equals(rs.getCell(1, i).getContents()
										.trim())) {// 当前行为空
							continue;
						} else {
							if ("".equals(rs.getCell(0, i).getContents().trim())) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("第" + (i + 1)
										+ "行事务ID不能为空!");
							} else if (accountService.countSingleTotal(rs
									.getCell(0, i).getContents().trim()) == 0) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("第" + (i + 1)
										+ "行事务ID不存在!");
							}
							if (!rs.getCell(1, i).getContents().trim()
									.matches("^\\d{10}$")) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("第" + (i + 1)
										+ "行会计凭证号必须为10位阿拉伯数字!");
							}

							singleTotal.setTransaction_id((rs.getCell(0, i)
									.getContents().trim()));
							singleTotal.setFinancial_doc_num(rs.getCell(1, i)
									.getContents().trim());

							list.add(singleTotal);
						}

					}

					if (contentResult.length() > 0) {
						this.setFailMessage(contentResult.toString());
						return RESULT_MESSAGE;
					}

					StringResult result = accountService
							.batchUpdateFinancialDocNum(list);

					if (IAccountService.SUCCESS.equals(result.getCode())) {
						this.setSuccessMessage("操作成功!");
					} else {
						this.setFailMessage("操作失败!");
					}

				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error(e);
			this.setFailMessage("Excel模板错误！");
			return RESULT_MESSAGE;
		} catch (BiffException e) {
			logger.error(e);
			this.setFailMessage("03以上版本Excel导入暂不支持");
			return RESULT_MESSAGE;
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("操作失败！");
			return RESULT_MESSAGE;
		}
		return RESULT_MESSAGE;

	}

	/**
	 * 报销明细查询页面跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchReimburDetail() {
		return "toSearchReimburDetail";
	}

	/**
	 * 报销单明细列表查询
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "singleDetailList", include = { "transaction_id", "payee", "project", "project_manager", "cost_type_content",
			"cost_date", "cost_purpose", "invoice_num", "invoice_amount", "audit_money", "cost_memo", "financial_operate_date" }, total = "total")
	public String getReimburDetailJsonList() {
		SingleDetail singleDetail = new SingleDetail();
		if (StringUtils.isNotEmpty(transactionId) && StringUtils.isNotEmpty(transactionId.trim()))
			singleDetail.setTransaction_id(Long.parseLong(transactionId));
		if (StringUtils.isNotEmpty(payee) && StringUtils.isNotEmpty(payee.trim()))
			singleDetail.setPayee(payee);
		if (StringUtils.isNotEmpty(project) && StringUtils.isNotEmpty(project.trim()))
			singleDetail.setProject(project);
		if (StringUtils.isNotEmpty(costTypeContent) && StringUtils.isNotEmpty(costTypeContent.trim()))
			singleDetail.setCost_type_content(costTypeContent);
		if (StringUtils.isNotEmpty(status) && StringUtils.isNotEmpty(status.trim()))
			singleDetail.setStatus(status);
		if (startDate != null)
			singleDetail.setStart_date(startDate);
		if (endDate != null)
			singleDetail.setEnd_date(endDate);
		singleDetail.setStart(getStart());
		singleDetail.setEnd(getEnd());
		total = accountService.getReimburDetailCount(singleDetail);
		if (total != 0) {
			singleDetailList = accountService
					.getReimburDetailList(singleDetail);
		}
		return JSON;
	}
	
	/**
	 * excel导出报销明细
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportReimberDetailList(){
		ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Ing");
		SingleDetail singleDetail = new SingleDetail();
		if (StringUtils.isNotEmpty(transactionId) && StringUtils.isNotEmpty(transactionId.trim()))
			singleDetail.setTransaction_id(Long.parseLong(transactionId));
		if (StringUtils.isNotEmpty(payee) && StringUtils.isNotEmpty(payee.trim()))
			singleDetail.setPayee(payee);
		if (StringUtils.isNotEmpty(project) && StringUtils.isNotEmpty(project.trim()))
			singleDetail.setProject(project);
		if (StringUtils.isNotEmpty(costTypeContent) && StringUtils.isNotEmpty(costTypeContent.trim()))
			singleDetail.setCost_type_content(costTypeContent);
		if (StringUtils.isNotEmpty(status) && StringUtils.isNotEmpty(status.trim()))
			singleDetail.setStatus(status);
		if (startDate != null)
			singleDetail.setStart_date(startDate);
		if (endDate != null)
			singleDetail.setEnd_date(endDate);
		try{
			File source = accountService.exportReimberDetailList(singleDetail);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
			if (source != null) {
				display(source, "报销单行项目明细_" + df.format(new Date()) + ".xls",
						ServletActionContext.getResponse());
				source.delete();
				ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Over");
			}else{
				this.setFailMessage("Excel数据导出出错");
			}	
		}catch(Exception e){
			this.setFailMessage("Excel数据导出出错");
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 校验数据是否下载完成
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "download")
	public String checkDownLoadOver(){
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("DownLoad");
		if(obj == null || "Ing".equals(obj)){
			download = "No";
		}else{
			download = "Yes";
		}
		return JSON;
	}
	
	/***
	 * 跳转到预算调整页面
	 * @return
	 */
	public String toBugetChange(){
		companyCode = accountService.getDefaultCompanyCode(Long.parseLong(getUser().getUserId()));// 获取登录人的公司代码
		orgFlag = String.valueOf(accountService.getSalesOrgCount(getUser().getOrgId()));
		return "toBugetChange";
	}
	/**
	 * 启动选择下个处理人
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result" })
	public String selectBugetChangeNexUser() {
		String role = "";
		if (accountService.getUserRoles(this.getUser(), "fiance_manager")) { //财务经理
			role = "fiance_manager";
		}else if(accountService.getUserRoles(this.getUser(), "changzhang")){ //厂长
			role = "changzhang";
		}else if(accountService.getUserRoles(this.getUser(), "provincesManager")){ //省区经理
			role = "provincesManager";
		}else if(accountService.getUserRoles(this.getUser(), "regionalDirector")){ //大区总监
			role = "regionalDirector";
		}else if(accountService.getUserRoles(this.getUser(), "deptLeader")){ //部门经理
			role = "deptLeader";
		}else if(accountService.getUserRoles(this.getUser(), "departmentDirector")){ //部门总监
			role = "departmentDirector";
		}else{
			role = "qita";
		}
		companyCode = accountService.getDefaultCompanyCode(Long.parseLong(getUser().getUserId()));// 获取登录人的公司代码
		Object[] res = new Object[4];
		res[0] = key;
		res[1] = this.getUser().getUserId();
		res[2] = "flag,role,companyCode,monthFlag";
		res[3] = flag+","+role+","+companyCode+",Y";
		userUtil = webService.startWorkflowFix(res);
		return JSON;
	}
	/**
	 * 获取下个处理人并处理
	 * 
	 * @return
	 */
	public String bugetChangeApplay() {
		this.setSuccessMessage("");
		this.setFailMessage("");

		Object[] res = new Object[7];
		res[0] = eventId;
		res[1] = this.getUser().getUserId();
		res[2] = nextUserId;
		res[3] = title;
		res[4] = appUrl + "/account/accountAction!searchBugetChange.jspa";
		res[5] = key;
		res[6] = "";
		String result = webService.processWorkflowFix(res);
		if ("success".equals(result)) {
			// 写XML文件
			if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + eventId
						+ ".xml", bugetChange, getUser().getUserId(), getUser()
							.getUserName(), null)) {
				this.setFailMessage("写入XML出错");
			}
			String detailId = eventService.getProEventDetail(eventId,
					getUser().getUserId());
			// 保存附件
			if (upload != null && upload.length > 0) {
				eventService.processAttachments(upload, uploadFileName,
						Long.valueOf(detailId),
						String.valueOf(new Date().getTime()), key);
			}
		}
		this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
	   return RESULT_MESSAGE;
	}
	
	/***
	 * 流程中查看预算调整
	 * @return
	 */
	public String searchBugetChange(){
		bugetChange = new BugetChange();
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, bugetChange);
			if (info != null) {
				bugetChange = (BugetChange) info.getObject();
			}
		}
		return "searchBugetChange";
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SingleTotal getSingleTotal() {
		return singleTotal;
	}

	public void setSingleTotal(SingleTotal singleTotal) {
		this.singleTotal = singleTotal;
	}

	public String getDetailJsonStr() {
		return detailJsonStr;
	}

	public void setDetailJsonStr(String detailJsonStr) {
		this.detailJsonStr = detailJsonStr;
	}

	public IEventService getEventService() {
		return eventService;
	}

	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

	public IWebService getWebService() {
		return webService;
	}

	public void setWebService(IWebService webService) {
		this.webService = webService;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getModelKey() {
		return modelKey;
	}

	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}

	public String getModelValues() {
		return modelValues;
	}

	public void setModelValues(String modelValues) {
		this.modelValues = modelValues;
	}

	public UserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public String getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public String getSubFolders() {
		return subFolders;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<WorkPlanTotal> getPlanList() {
		return planList;
	}

	public void setPlanList(List<WorkPlanTotal> planList) {
		this.planList = planList;
	}

	public List<CmsTbDict> getCostTypeList() {
		return costTypeList;
	}

	public void setCostTypeList(List<CmsTbDict> costTypeList) {
		this.costTypeList = costTypeList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getCostCenterText() {
		return costCenterText;
	}

	public void setCostCenterText(String costCenterText) {
		this.costCenterText = costCenterText;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getToDoDetail() {
		return toDoDetail;
	}

	public void setToDoDetail(String toDoDetail) {
		this.toDoDetail = toDoDetail;
	}

	public String getCurStaId() {
		return curStaId;
	}

	public void setCurStaId(String curStaId) {
		this.curStaId = curStaId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getPlayMoneyFlag() {
		return playMoneyFlag;
	}

	public void setPlayMoneyFlag(String playMoneyFlag) {
		this.playMoneyFlag = playMoneyFlag;
	}

	public List<SingleTotal> getSingleTotalList() {
		return singleTotalList;
	}

	public void setSingleTotalList(List<SingleTotal> singleTotalList) {
		this.singleTotalList = singleTotalList;
	}

	public List<SingleDetail> getSingleDetailList() {
		return singleDetailList;
	}

	public void setSingleDetailList(List<SingleDetail> singleDetailList) {
		this.singleDetailList = singleDetailList;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Date getCost_date() {
		return cost_date;
	}

	public void setCost_date(Date cost_date) {
		this.cost_date = cost_date;
	}

	public String getCost_purpose() {
		return cost_purpose;
	}

	public void setCost_purpose(String cost_purpose) {
		this.cost_purpose = cost_purpose;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<PayeeInfo> getPayeeInfoList() {
		return payeeInfoList;
	}

	public void setPayeeInfoList(List<PayeeInfo> payeeInfoList) {
		this.payeeInfoList = payeeInfoList;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getPayArea() {
		return payArea;
	}

	public void setPayArea(String payArea) {
		this.payArea = payArea;
	}

	public String getPayBank() {
		return payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}

	public String getPayAreaCode() {
		return payAreaCode;
	}

	public void setPayAreaCode(String payAreaCode) {
		this.payAreaCode = payAreaCode;
	}

	public String getPayBankAlias() {
		return payBankAlias;
	}

	public void setPayBankAlias(String payBankAlias) {
		this.payBankAlias = payBankAlias;
	}

	public String getPayBankAliCode() {
		return payBankAliCode;
	}

	public void setPayBankAliCode(String payBankAliCode) {
		this.payBankAliCode = payBankAliCode;
	}

	public String getPayBankCode() {
		return payBankCode;
	}

	public void setPayBankCode(String payBankCode) {
		this.payBankCode = payBankCode;
	}

	public String getAccountAlias() {
		return accountAlias;
	}

	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOperateFlag() {
		return operateFlag;
	}

	public void setOperateFlag(String operateFlag) {
		this.operateFlag = operateFlag;
	}

	public PayeeInfo getPayeeInfo() {
		return payeeInfo;
	}

	public void setPayeeInfo(PayeeInfo payeeInfo) {
		this.payeeInfo = payeeInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public List<ProcessEventTotal> getProEventTotalList() {
		return proEventTotalList;
	}

	public void setProEventTotalList(List<ProcessEventTotal> proEventTotalList) {
		this.proEventTotalList = proEventTotalList;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getFinancial_doc_num() {
		return financial_doc_num;
	}

	public void setFinancial_doc_num(String financial_doc_num) {
		this.financial_doc_num = financial_doc_num;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getSearchStrFlag() {
		return searchStrFlag;
	}

	public void setSearchStrFlag(String searchStrFlag) {
		this.searchStrFlag = searchStrFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPro_manager_id() {
		return pro_manager_id;
	}

	public void setPro_manager_id(String pro_manager_id) {
		this.pro_manager_id = pro_manager_id;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCostTypeContent() {
		return costTypeContent;
	}

	public void setCostTypeContent(String costTypeContent) {
		this.costTypeContent = costTypeContent;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BugetChange getBugetChange() {
		return bugetChange;
	}

	public void setBugetChange(BugetChange bugetChange) {
		this.bugetChange = bugetChange;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getOrgFlag() {
		return orgFlag;
	}

	public void setOrgFlag(String orgFlag) {
		this.orgFlag = orgFlag;
	}
}
