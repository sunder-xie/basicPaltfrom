package com.kintiger.platform.account.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * �տ�����Ϣʵ����
 *
 */
public class PayeeInfo extends SearchInfo{
	private static final long serialVersionUID = -8942870418117357616L;
	
	private Long id;
	
	private String payee;// �տλ
	
	private String payAccount;// �տ��˺�
	
	private String payArea;// �տ����
	
	private String payBank;// �տ�����
	
	private String payAreaCode;// �տ��˵�������
	
	private String payBankAlias;// �տ��б�����
	
	private String payBankAliCode;// �տ��б����
	
	private String payBankCode;// �տ����д���
	
	private String accountAlias;// �˻�����
	
	private String email;// ��������
	
	private String flag;// ɾ�����
	
	private String searchStr;
	
	private String creator;// ������
	
	private Date createDate;// ����ʱ��
	
	private String modifier;// �޸���
	
	private String modifyDate;// �޸�ʱ��

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

}
