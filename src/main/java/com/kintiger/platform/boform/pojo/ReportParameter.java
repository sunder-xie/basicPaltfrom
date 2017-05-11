package com.kintiger.platform.boform.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;


/**
 * 報表參數對象
 * 
 * @author 
 * 
 */
public class ReportParameter extends SearchInfo {

	private static final long serialVersionUID = -4990847836959932772L;

	/**
	 * 自增ID
	 */
	private Long pid;

	/**
	 * 报表ID
	 */
	private Long bid;

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * id字段
	 */
	private String zdid;

	/**
	 * 查询lable
	 */
	private String memo;

	/**
	 * 参数可选数量(0手动填，1单值，2多值，3选日期，4OA组织树，5年，6月，7水站，20多值一页显示n条记录)
	 */
	private int amount;

	/**
	 * 是否有描述(0无，1有)
	 */
	private int txt;

	/**
	 * 是否为必填(0不是，1必填)
	 */
	private int che;

	/**
	 * 描述字段
	 */
	private String zdtxt;

	/**
	 * 查询条件(F工厂id,H人事，Q分销渠道，C公司代码，X销售组织，B销售办公室)
	 */
	private String d;

	/**
	 * 别名
	 */
	private String nickname;

	/**
	 * 校验方式(0手动填时的校验：4数字，1英文,2英文数字，3金额)
	 */
	private Integer checkWay;
	
	/**
	 * 约束条件
	 */
	private String s;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getZdid() {
		return zdid;
	}

	public void setZdid(String zdid) {
		this.zdid = zdid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTxt() {
		return txt;
	}

	public void setTxt(int txt) {
		this.txt = txt;
	}

	public int getChe() {
		return che;
	}

	public void setChe(int che) {
		this.che = che;
	}

	public String getZdtxt() {
		return zdtxt;
	}

	public void setZdtxt(String zdtxt) {
		this.zdtxt = zdtxt;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	

	

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getCheckWay() {
		return checkWay;
	}

	public void setCheckWay(Integer checkWay) {
		this.checkWay = checkWay;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}



	

}
