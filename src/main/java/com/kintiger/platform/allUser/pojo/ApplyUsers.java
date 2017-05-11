package com.kintiger.platform.allUser.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * SMSUSER.APPLYUSERS
 * 
 * @author xujiakun
 * 
 */
public class ApplyUsers extends SearchInfo {

	private static final long serialVersionUID = -6103728926926948913L;

	private Long id;

	/**
	 * 用户id
	 */
	private String userCode;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 用户姓名
	 */
	private String userName;

	/**
	 * 显示名
	 */
	private String userShowName;

	/**
	 * 显示名
	 */
	private String workPhone;

	/**
	 * 办公传真
	 */
	private String workFax;

	/**
	 * 手机
	 */
	private String mobilePhone;

	/**
	 * 宅电
	 */
	private String homePhone;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 邮箱地址
	 */
	private String mailAddress;

	/**
	 * 性别（M男F女）
	 */
	private String sex;

	/**
	 * 是否开通邮箱（1开通0不开通）
	 */
	private String haveMail;

	/**
	 * 组织id
	 */
	private Long organiseId;

	/**
	 * 申请者id
	 */
	private Long squserId;

	/**
	 * 申请者姓名
	 */
	private String squserShowName;

	/**
	 * 人事审核(F未审核T已审核D退回)
	 */
	private String hrsh;

	/**
	 * 信息部审核(F未审核T已审核D退回)
	 */
	private String infosh;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 *  是职位ID
	 */
	private String stationId;

	/**
	 * 申请时间
	 */
	private Date createTime;

	/**
	 * 作废原因
	 */
	private String jjReason;

	/**
	 * 修改时间
	 */
	private Date lastModify;

	/**
	 * 身份证号码
	 */
	private String empIdCard;

	/**
	 * 开始工作日期
	 */
	private String empStartDate;

	private String beginDate;

	private String endDate;

	private String[] orgIds;

	private  String orgStr;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserShowName() {
		return userShowName;
	}

	public void setUserShowName(String userShowName) {
		this.userShowName = userShowName;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getWorkFax() {
		return workFax;
	}

	public void setWorkFax(String workFax) {
		this.workFax = workFax;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHaveMail() {
		return haveMail;
	}

	public void setHaveMail(String haveMail) {
		this.haveMail = haveMail;
	}

	public Long getOrganiseId() {
		return organiseId;
	}

	public void setOrganiseId(Long organiseId) {
		this.organiseId = organiseId;
	}

	public Long getSquserId() {
		return squserId;
	}

	public void setSquserId(Long squserId) {
		this.squserId = squserId;
	}

	public String getSquserShowName() {
		return squserShowName;
	}

	public void setSquserShowName(String squserShowName) {
		this.squserShowName = squserShowName;
	}

	public String getHrsh() {
		return hrsh;
	}

	public void setHrsh(String hrsh) {
		this.hrsh = hrsh;
	}

	public String getInfosh() {
		return infosh;
	}

	public void setInfosh(String infosh) {
		this.infosh = infosh;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getJjReason() {
		return jjReason;
	}

	public void setJjReason(String jjReason) {
		this.jjReason = jjReason;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getEmpIdCard() {
		return empIdCard;
	}

	public void setEmpIdCard(String empIdCard) {
		this.empIdCard = empIdCard;
	}

	public String getEmpStartDate() {
		return empStartDate;
	}

	public void setEmpStartDate(String empStartDate) {
		this.empStartDate = empStartDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String[] getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String[] orgIds) {
		this.orgIds = orgIds;
	}

	public String getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String orgStr) {
		this.orgStr = orgStr;
	}

}
