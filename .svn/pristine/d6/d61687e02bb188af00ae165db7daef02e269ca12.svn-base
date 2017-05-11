package com.kintiger.platform.wfe.pojo;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 流程事务明细表
 * 
 * @author hfwu
 * 
 */
public class ProEventDetail extends ProEventTotal {

	private static final long serialVersionUID = 5150545730645207461L;

	/**
	 * 事务流程ID
	 */
	private Long eventDtlId;

	/**
	 * 当前处理人的user_id
	 */
	private String curUserId;
	
	private String curUserName;

	/**
	 * 后一个处理人user_id
	 */
	private String nextUserId;

	/**
	 * 标题
	 */
	private String subTitle;

	/**
	 * 上传附件ID
	 */
	private String link;

	/**
	 * 操作（0为拒绝，1为同意,9:未处理）
	 */
	private String operation;

	/**
	 * 操作时间
	 */
	private Date time;

	/**
	 * 当前处理人的station_id
	 */
	private String curStaId;

	/**
	 * 当前模板明细ID
	 */
	private Long curModelDtlId;

	/**
	 * 下一个事务明细ID
	 */
	private String nextEventdtlId;

	/**
	 * 前一个处理人的USER_ID
	 */
	private String preUserId;

	/**
	 * 上一个处理人的station_id
	 */
	private String preStaId;

	/**
	 * 下一个处理人的station_id
	 */
	private String nextStaId;

	/**
	 * N是正常；D表示取消；R表示回退；B表示取回；S表示授权
	 */
	private String eventDtlFlag;

	/**
	 * 下一岗位ID
	 */
	private Long nextOrgId;

	/**
	 * 催办时间
	 */
	private String limitTime;

	/**
	 * 被授权人ID
	 */
	private String priUserId;

	private Long curOrgId;

	/**
	 * 所有等待的next_model_detail_id
	 */
	private String waitNmd;

	/**
	 * 上传附件名称
	 */
	private String[] uploadFileNames;

	/**
	 * 返回多个下一处理人角色
	 */
	private String[] nextStaIds;

	/**
	 * 返回多个下一处理人组织
	 */
	private Long[] nextOrgIds;

	/**
	 * 返回多个下一处理人user_id
	 */
	private String[] nextUserIds;

	/**
	 * 返回多个催办天数
	 */
	private Long[] limitDays;

	private Long limitDay;

	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 附件名称
	 */
	private String filename;
	
	private Long fileId;

	/**
	 * 附件在系统上代号
	 */
	private String filenameNew;

	/**
	 * 附件
	 */
	private File[] uploadFiles;

	private String orgName;
	
	private String repalyCount;
	/**
	 * 抄送明细集合，每个节点明细中都可能有多个抄送明细
	 */
	private List<Cc> ccs;
	

	public Long getEventDtlId() {
		return eventDtlId;
	}

	public void setEventDtlId(Long eventDtlId) {
		this.eventDtlId = eventDtlId;
	}

	public String getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(String curUserId) {
		this.curUserId = curUserId;
	}

	public String getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCurStaId() {
		return curStaId;
	}

	public void setCurStaId(String curStaId) {
		this.curStaId = curStaId;
	}

	public Long getCurModelDtlId() {
		return curModelDtlId;
	}

	public void setCurModelDtlId(Long curModelDtlId) {
		this.curModelDtlId = curModelDtlId;
	}

	public String getNextEventdtlId() {
		return nextEventdtlId;
	}

	public void setNextEventdtlId(String nextEventdtlId) {
		this.nextEventdtlId = nextEventdtlId;
	}

	public String getPreUserId() {
		return preUserId;
	}

	public void setPreUserId(String preUserId) {
		this.preUserId = preUserId;
	}

	public String getPreStaId() {
		return preStaId;
	}

	public void setPreStaId(String preStaId) {
		this.preStaId = preStaId;
	}

	public String getNextStaId() {
		return nextStaId;
	}

	public void setNextStaId(String nextStaId) {
		this.nextStaId = nextStaId;
	}

	public String getEventDtlFlag() {
		return eventDtlFlag;
	}

	public void setEventDtlFlag(String eventDtlFlag) {
		this.eventDtlFlag = eventDtlFlag;
	}

	public Long getNextOrgId() {
		return nextOrgId;
	}

	public void setNextOrgId(Long nextOrgId) {
		this.nextOrgId = nextOrgId;
	}

	public String getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}

	public String getPriUserId() {
		return priUserId;
	}

	public void setPriUserId(String priUserId) {
		this.priUserId = priUserId;
	}

	public Long getCurOrgId() {
		return curOrgId;
	}

	public void setCurOrgId(Long curOrgId) {
		this.curOrgId = curOrgId;
	}

	public String getWaitNmd() {
		return waitNmd;
	}

	public void setWaitNmd(String waitNmd) {
		this.waitNmd = waitNmd;
	}

	public String[] getUploadFileNames() {
		return uploadFileNames;
	}

	public void setUploadFileNames(String[] uploadFileNames) {
		this.uploadFileNames = uploadFileNames;
	}

	public String[] getNextStaIds() {
		return nextStaIds;
	}

	public void setNextStaIds(String[] nextStaIds) {
		this.nextStaIds = nextStaIds;
	}

	public Long[] getNextOrgIds() {
		return nextOrgIds;
	}

	public void setNextOrgIds(Long[] nextOrgIds) {
		this.nextOrgIds = nextOrgIds;
	}

	public String[] getNextUserIds() {
		return nextUserIds;
	}

	public void setNextUserIds(String[] nextUserIds) {
		this.nextUserIds = nextUserIds;
	}

	public Long[] getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(Long[] limitDays) {
		this.limitDays = limitDays;
	}

	public Long getLimitDay() {
		return limitDay;
	}

	public void setLimitDay(Long limitDay) {
		this.limitDay = limitDay;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilenameNew() {
		return filenameNew;
	}

	public void setFilenameNew(String filenameNew) {
		this.filenameNew = filenameNew;
	}

	public File[] getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(File[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getCurUserName() {
		return curUserName;
	}

	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}

	public String getRepalyCount() {
		return repalyCount;
	}

	public void setRepalyCount(String repalyCount) {
		this.repalyCount = repalyCount;
	}

	public List<Cc> getCcs() {
		return ccs;
	}

	public void setCcs(List<Cc> ccs) {
		this.ccs = ccs;
	}
}