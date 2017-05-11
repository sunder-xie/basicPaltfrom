package com.kintiger.platform.account.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;
import com.kintiger.platform.framework.annotations.Decode;

public class WorkPlanTotal extends SearchInfo{
	private static final long serialVersionUID = -453226097756722L;
	private String totalId;
	private String detailId;
	private String memo;
	private Long recordId;
	private Long moduleId;// 模块号
	private String planTypeName;//模块名称
	@Decode
	private String description;// 描述
	private String analysis;// 技术分析
	private Date finishTime;// 完成时间
	private Date beginTime;// 开始时间
	private String finish_time;// 完成时间-导出excel时存放
	private String project;
	private String pro_title;//项目标题
	private String title;
	private String pro_manager;//项目经理
	private Long pro_manager_id;
	private String pro_major;//项目总监
	private Long pro_major_id;
	private Long creatorId;
	private String userOrgName;//提交部门
	private String createorName;//创建者
	private Date create_date;//申请日期
	private String createDate;//申请日期-导出excel时存放
	private Date startTime;
	private Date endTime;
	private Long transactionId;
	private String teploginId;//登录用户Id
	private double workhours;//工时
	private String itemName;//项目类型
	@Decode
	private String status;//需求单状态：0是未分配，1是已分配，2是已反馈
	private String responsiblePerson;//参与人员
	private Integer lastMonth;//持续月份
	private Integer year;
	private Integer month;
	private Long recordUserId;//考评人id
	private String recordUserName;//考评人
	private String goal;//需求单目标：0是完成上线，1是完成测试
	private Long orgId;//组织ID
	private Long userId;//用户ID
	private String managerOrgName;//负责部门
	private String numFlag;//判断是否26前号提交，Y-26号前，N-26号后
	private String manFlag;//Y-胡总提的需求单，N-普通需求单
	private String delayFlag;//延迟标识：Y是已延迟，N是默认未延迟
	private String spanFlag;//判断是否有跨月： Y-是，N-否
	private String  userCode;
	private String  userName;
	private String custId;
	private String custName;
	
	private String[] statusList;
	
	public String getTotalId() {
		return totalId;
	}

	public void setTotalId(String totalId) {
		this.totalId = totalId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getPlanTypeName() {
		return planTypeName;
	}

	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}



	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
	public String getCreateorName() {
		return createorName;
	}

	public void setCreateorName(String createorName) {
		this.createorName = createorName;
	}

	public String getUserOrgName() {
		return userOrgName;
	}

	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}

	public String getPro_title() {
		return pro_title;
	}

	public void setPro_title(String proTitle) {
		pro_title = proTitle;
	}

	public String getPro_manager() {
		return pro_manager;
	}

	public void setPro_manager(String proManager) {
		pro_manager = proManager;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date createDate) {
		create_date = createDate;
	}

	public double getWorkhours() {
		return workhours;
	}

	public void setWorkhours(double workhours) {
		this.workhours = workhours;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public Integer getLastMonth() {
		return lastMonth;
	}



	public void setLastMonth(Integer lastMonth) {
		this.lastMonth = lastMonth;
	}



	public Integer getYear() {
		return year;
	}




	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getRecordUserId() {
		return recordUserId;
	}

	public void setRecordUserId(Long recordUserId) {
		this.recordUserId = recordUserId;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getRecordUserName() {
		return recordUserName;
	}

	public void setRecordUserName(String recordUserName) {
		this.recordUserName = recordUserName;
	}

	public Long getPro_manager_id() {
		return pro_manager_id;
	}

	public void setPro_manager_id(Long proManagerId) {
		pro_manager_id = proManagerId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getManagerOrgName() {
		return managerOrgName;
	}

	public void setManagerOrgName(String managerOrgName) {
		this.managerOrgName = managerOrgName;
	}

	public String getTeploginId() {
		return teploginId;
	}

	public void setTeploginId(String teploginId) {
		this.teploginId = teploginId;
	}

	public String getNumFlag() {
		return numFlag;
	}

	public void setNumFlag(String numFlag) {
		this.numFlag = numFlag;
	}

	public String getManFlag() {
		return manFlag;
	}

	public void setManFlag(String manFlag) {
		this.manFlag = manFlag;
	}

	public String getDelayFlag() {
		return delayFlag;
	}

	public void setDelayFlag(String delayFlag) {
		this.delayFlag = delayFlag;
	}

	public String[] getStatusList() {
		return statusList;
	}

	public void setStatusList(String[] statusList) {
		this.statusList = statusList;
	}

	public String getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(String finishTime) {
		finish_time = finishTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSpanFlag() {
		return spanFlag;
	}

	public void setSpanFlag(String spanFlag) {
		this.spanFlag = spanFlag;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPro_major() {
		return pro_major;
	}

	public void setPro_major(String pro_major) {
		this.pro_major = pro_major;
	}

	public Long getPro_major_id() {
		return pro_major_id;
	}

	public void setPro_major_id(Long pro_major_id) {
		this.pro_major_id = pro_major_id;
	}
	

}
