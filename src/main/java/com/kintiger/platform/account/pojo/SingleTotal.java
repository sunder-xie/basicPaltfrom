package com.kintiger.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * �����ܵ�
 * @author
 *
 */
public class SingleTotal extends SearchInfo{

	private static final long serialVersionUID = 8439042050053296566L;
	
	private Long plan_id;			//�ƻ�Id
	private String transaction_id;	//����Id
	
	private Long org_id;			//�ύ�˲���Id
	private String org_name;		//�ύ�˲���
	
	private String user_id;	//�տ���/������
	private String user_name;// �տλ
	
	private Long cost_org_id;//���óе�����
	private String cost_org_name;

	private String pay_type;	//֧����ʽ
	
	private BigDecimal total_money;	//�����ܽ��
	private BigDecimal audit_money; //����ܽ��
	
	private String cost_center;		//�ɱ�����
	private String cost_center_name;//�ɱ���������
	private String cost_center_type;//�ɱ���������
	private String has_play_money;	//�Ƿ���		

	private String memo;			//��ע
	private String plan_flag;		//ɾ�����
	
	private Date create_date;
	private Date startDate;
	private Date endDate;
	
	private Long creator_id;
	private Date modify_date;
	private Long operator_id;
	
	/** ������չ����  */
	private String eventId;			//����ID
	private String title;			//����
	private String status;			//״̬
	
	private String pay_ee;// �տ���
	private String pay_account;// �տ����˺�
	private String financial_doc_num;// ���ƾ֤��
	private String auditor;
	private String flag;

	private List<SingleDetail> detailList;

	public Long getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(Long plan_id) {
		this.plan_id = plan_id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Long getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Long getCost_org_id() {
		return cost_org_id;
	}

	public void setCost_org_id(Long cost_org_id) {
		this.cost_org_id = cost_org_id;
	}

	public String getCost_org_name() {
		return cost_org_name;
	}

	public void setCost_org_name(String cost_org_name) {
		this.cost_org_name = cost_org_name;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public BigDecimal getTotal_money() {
		return total_money;
	}

	public void setTotal_money(BigDecimal total_money) {
		this.total_money = total_money;
	}

	public BigDecimal getAudit_money() {
		return audit_money;
	}

	public void setAudit_money(BigDecimal audit_money) {
		this.audit_money = audit_money;
	}

	public String getCost_center() {
		return cost_center;
	}

	public void setCost_center(String cost_center) {
		this.cost_center = cost_center;
	}

	public String getCost_center_type() {
		return cost_center_type;
	}

	public void setCost_center_type(String cost_center_type) {
		this.cost_center_type = cost_center_type;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPlan_flag() {
		return plan_flag;
	}

	public void setPlan_flag(String plan_flag) {
		this.plan_flag = plan_flag;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Long getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Long creator_id) {
		this.creator_id = creator_id;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Long getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(Long operator_id) {
		this.operator_id = operator_id;
	}

	public List<SingleDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<SingleDetail> detailList) {
		this.detailList = detailList;
	}

	public String getCost_center_name() {
		return cost_center_name;
	}

	public void setCost_center_name(String cost_center_name) {
		this.cost_center_name = cost_center_name;
	}

	public String getHas_play_money() {
		return has_play_money;
	}

	public void setHas_play_money(String has_play_money) {
		this.has_play_money = has_play_money;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	public String getPay_ee() {
		return pay_ee;
	}

	public void setPay_ee(String pay_ee) {
		this.pay_ee = pay_ee;
	}

	public String getPay_account() {
		return pay_account;
	}

	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}

	public String getFinancial_doc_num() {
		return financial_doc_num;
	}

	public void setFinancial_doc_num(String financial_doc_num) {
		this.financial_doc_num = financial_doc_num;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
