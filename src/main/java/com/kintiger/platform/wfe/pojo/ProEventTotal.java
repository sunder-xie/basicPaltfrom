package com.kintiger.platform.wfe.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;


/**
 * 流程事务总表
 * 
 * @author hfwu
 * 
 */
public class ProEventTotal extends SearchInfo {

	private static final long serialVersionUID = 8526025544393653691L;

	/**
	 * 事务ID
	 */
	private Long eventId;
	
	private String detailId;

	/**
	 * 发起人的user_id
	 */
	private String initator;

	/**
	 * 模板ID
	 */
	private String modelId;

	/**
	 * 操作时间
	 */
	private Date createDate;

	/**
	 * 事务状态（0为未处理，1为处理中，2为处理完成，3为已拒绝，4表示取消）
	 */
	private String status;

	/**
	 * 系统标志（0——oa系统，1--冷柜）
	 */
	private String sysTag;

	/**
	 * 有路由时填写的条件
	 */
	private String keys;

	/**
	 * 事务类别号
	 */
	private Long traclassid;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	private String attribute5;

	private String attribute6;

	private String attribute7;

	private String attribute8;

	private Date lastModify;

	/**
	 * Y：xml走oa流程；N：走报销流程；null：走老流程
	 */
	private String uiXmlFlag;

	/**
	 * 路由条件
	 */
	private String condition;

	/**
	 * 对应生成xml的action
	 */
	private String actionId;

	private String memo;

	/**
	 * 事物标题
	 */
	private String eventTitle;

	/**
	 * 响应速度
	 */
	private BigDecimal answerCareer;

	/**
	 * 处理速度
	 */
	private BigDecimal disposalCareer;

	/**
	 * 处理结果
	 */
	private BigDecimal disposalResult;

	/**
	 * 事务紧急程度
	 */
	private BigDecimal urgent;

	/**
	 * 
	 */
	private String userId;

	private String userName;

	private String modelName;

	private Long eventLookUpId;

	/**
	 * 流程count
	 */
	private int count;

	/**
	 * 时间戳
	 */
	private String timestamp;

	/**
	 * 流程
	 */
	private Long nextModelDetailId;

	private Long[] nextModelDetailIds;

	private String pointStatus;

	private String kpiId;

	/**
	 * xml文件路径
	 */
	private String subFolders;

	private String anyFlag;

	private String scoreFlag;

	private String domain;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getInitator() {
		return initator;
	}

	public void setInitator(String initator) {
		this.initator = initator;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSysTag() {
		return sysTag;
	}

	public void setSysTag(String sysTag) {
		this.sysTag = sysTag;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public Long getTraclassid() {
		return traclassid;
	}

	public void setTraclassid(Long traclassid) {
		this.traclassid = traclassid;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getUiXmlFlag() {
		return uiXmlFlag;
	}

	public void setUiXmlFlag(String uiXmlFlag) {
		this.uiXmlFlag = uiXmlFlag;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public BigDecimal getAnswerCareer() {
		return answerCareer;
	}

	public void setAnswerCareer(BigDecimal answerCareer) {
		this.answerCareer = answerCareer;
	}

	public BigDecimal getDisposalCareer() {
		return disposalCareer;
	}

	public void setDisposalCareer(BigDecimal disposalCareer) {
		this.disposalCareer = disposalCareer;
	}

	public BigDecimal getDisposalResult() {
		return disposalResult;
	}

	public void setDisposalResult(BigDecimal disposalResult) {
		this.disposalResult = disposalResult;
	}

	public BigDecimal getUrgent() {
		return urgent;
	}

	public void setUrgent(BigDecimal urgent) {
		this.urgent = urgent;
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

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Long getEventLookUpId() {
		return eventLookUpId;
	}

	public void setEventLookUpId(Long eventLookUpId) {
		this.eventLookUpId = eventLookUpId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Long getNextModelDetailId() {
		return nextModelDetailId;
	}

	public void setNextModelDetailId(Long nextModelDetailId) {
		this.nextModelDetailId = nextModelDetailId;
	}

	public Long[] getNextModelDetailIds() {
		return nextModelDetailIds;
	}

	public void setNextModelDetailIds(Long[] nextModelDetailIds) {
		this.nextModelDetailIds = nextModelDetailIds;
	}

	public String getPointStatus() {
		return pointStatus;
	}

	public void setPointStatus(String pointStatus) {
		this.pointStatus = pointStatus;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}

	public String getAnyFlag() {
		return anyFlag;
	}

	public void setAnyFlag(String anyFlag) {
		this.anyFlag = anyFlag;
	}

	public String getScoreFlag() {
		return scoreFlag;
	}

	public void setScoreFlag(String scoreFlag) {
		this.scoreFlag = scoreFlag;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

}