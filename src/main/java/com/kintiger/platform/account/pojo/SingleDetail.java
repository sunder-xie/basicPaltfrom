package com.kintiger.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class SingleDetail extends SearchInfo {

	private static final long serialVersionUID = -7217743280414889116L;

	
	private Long transaction_id;
	private Long detail_id;
	private Long plan_id;
	
	private String cost_type;			//费用类型
	private String cost_type_content;	//费用类型内容
	
	private Date cost_date;				//费用日期
	private String cost_purpose;		//开支用途
	private Long invoice_num;			//发票张数
	private BigDecimal invoice_amount;	//发票金额
	private BigDecimal audit_money;// 实际金额
	private String cost_memo;// 备注
	private String payee;// 收款人
	private String project;// 项目
	private String project_manager;// 项目经理
	private Date financial_operate_date;// 财务处理时间
	private String status;// 事务状态
	private Date start_date;
	private Date end_date;
	public Long getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}
	public Long getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(Long plan_id) {
		this.plan_id = plan_id;
	}
	public String getCost_type() {
		return cost_type;
	}
	public void setCost_type(String cost_type) {
		this.cost_type = cost_type;
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
	public Long getInvoice_num() {
		return invoice_num;
	}
	public void setInvoice_num(Long invoice_num) {
		this.invoice_num = invoice_num;
	}
	public BigDecimal getInvoice_amount() {
		return invoice_amount;
	}
	public void setInvoice_amount(BigDecimal invoice_amount) {
		this.invoice_amount = invoice_amount;
	}
	public String getCost_memo() {
		return cost_memo;
	}
	public void setCost_memo(String cost_memo) {
		this.cost_memo = cost_memo;
	}
	public String getCost_type_content() {
		return cost_type_content;
	}
	public void setCost_type_content(String cost_type_content) {
		this.cost_type_content = cost_type_content;
	}
	public BigDecimal getAudit_money() {
		return audit_money;
	}
	public void setAudit_money(BigDecimal audit_money) {
		this.audit_money = audit_money;
	}
	public Long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(Long transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getProject_manager() {
		return project_manager;
	}
	public void setProject_manager(String project_manager) {
		this.project_manager = project_manager;
	}
	public Date getFinancial_operate_date() {
		return financial_operate_date;
	}
	public void setFinancial_operate_date(Date financial_operate_date) {
		this.financial_operate_date = financial_operate_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
}
