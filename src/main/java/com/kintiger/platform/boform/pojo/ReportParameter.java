package com.kintiger.platform.boform.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;


/**
 * ��텢������
 * 
 * @author 
 * 
 */
public class ReportParameter extends SearchInfo {

	private static final long serialVersionUID = -4990847836959932772L;

	/**
	 * ����ID
	 */
	private Long pid;

	/**
	 * ����ID
	 */
	private Long bid;

	/**
	 * ����
	 */
	private String tableName;

	/**
	 * id�ֶ�
	 */
	private String zdid;

	/**
	 * ��ѯlable
	 */
	private String memo;

	/**
	 * ������ѡ����(0�ֶ��1��ֵ��2��ֵ��3ѡ���ڣ�4OA��֯����5�꣬6�£�7ˮվ��20��ֵһҳ��ʾn����¼)
	 */
	private int amount;

	/**
	 * �Ƿ�������(0�ޣ�1��)
	 */
	private int txt;

	/**
	 * �Ƿ�Ϊ����(0���ǣ�1����)
	 */
	private int che;

	/**
	 * �����ֶ�
	 */
	private String zdtxt;

	/**
	 * ��ѯ����(F����id,H���£�Q����������C��˾���룬X������֯��B���۰칫��)
	 */
	private String d;

	/**
	 * ����
	 */
	private String nickname;

	/**
	 * У�鷽ʽ(0�ֶ���ʱ��У�飺4���֣�1Ӣ��,2Ӣ�����֣�3���)
	 */
	private Integer checkWay;
	
	/**
	 * Լ������
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
