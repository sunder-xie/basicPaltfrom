package com.kintiger.platform.account.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

public class BugetChange extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String comment;  //��ע
	private String isChangeMoney; //�Ƿ�����ܶ�
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getIsChangeMoney() {
		return isChangeMoney;
	}
	public void setIsChangeMoney(String isChangeMoney) {
		this.isChangeMoney = isChangeMoney;
	}
}
