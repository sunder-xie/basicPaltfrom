package com.kintiger.platform.account.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 收款人信息实体类
 *
 */
public class PayeeInfo extends SearchInfo{
	private static final long serialVersionUID = -8942870418117357616L;
	
	private Long id;
	
	private String payee;// 收款单位
	
	private String payAccount;// 收款账号
	
	private String payArea;// 收款地区
	
	private String payBank;// 收款银行
	
	private String payAreaCode;// 收款人地区代码
	
	private String payBankAlias;// 收款行别名称
	
	private String payBankAliCode;// 收款行别代码
	
	private String payBankCode;// 收款银行代码
	
	private String accountAlias;// 账户别名
	
	private String email;// 电子邮箱
	
	private String flag;// 删除标记
	
	private String searchStr;
	
	private String creator;// 创建人
	
	private Date createDate;// 创建时间
	
	private String modifier;// 修改人
	
	private String modifyDate;// 修改时间

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
