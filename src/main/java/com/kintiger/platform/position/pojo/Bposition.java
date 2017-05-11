package com.kintiger.platform.position.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 职位（人）
 * 
 * @author xujiakun
 * 
 */
public class Bposition extends SearchInfo {

	private static final long serialVersionUID = 5706027748507890893L;

	/**
	 * 职位ID
	 */
	private Long posId;

	/**
	 * 组织ID
	 */
	private Long orgId;

	/**
	 * 职位名
	 */
	private String posName;

	/**
	 * 字母简称
	 */
	private String posCode;

	/**
	 * 上级职位ID
	 */
	private Long parentPosId;

	/**
	 * 职位类型
	 */
	private Long posType;

	/**
	 * 职位享受级别（报销）
	 */
	private Long posLevel;

	/**
	 * 职位享受级别（报销）名称
	 */
	private String posLevelName;

	/**
	 * 状态
	 */
	private String posState;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 最后修改时间
	 */
	private Date lastModify;

	/**
	 * 父职位id
	 */
	private Long pposId;

	/**
	 * 最后修改时间（时间戳）
	 */
	private Date modifyDate;

	/**
	 * 协议类别
	 */
	private Long visitSort;

	/**
	 * 使用标记（空 未使用 Apos _id使用 B img_pos_id使用）
	 */
	private String useState;

	/**
	 * 人员ID
	 */
	private Long empId;

	/**
	 * P:职位映射(有编制) S:权限映射(无编制)
	 */
	private String posFlag;

	/**
	 * 人员name
	 */
	private String empName;

	/**
	 * 职位类型name
	 */
	private String posTypeName;

	/**
	 * 职位享受级别（报销）
	 */
	private String posLevelValue;

	/**
	 * 职位关系维护用
	 */
	private String posInfo;

	/**
	 * 附加参数
	 */
	private String[] posIds;

	/**
	 * 组织名称
	 * 
	 * @return
	 */
	private String orgName;

	/**
	 * 修改时显示用 6个值，原来表中没有
	 */
	// 职位享受级别类型
	private String posLevelType;
	// 协访次数等
	private String tot;
	private String xf;
	private String ct;
	private String ka;
	private Long stationId;

	
	public Long getPosId() {
		return posId;
	}

	public void setPosId(Long posId) {
		this.posId = posId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public Long getParentPosId() {
		return parentPosId;
	}

	public void setParentPosId(Long parentPosId) {
		this.parentPosId = parentPosId;
	}

	public Long getPosType() {
		return posType;
	}

	public void setPosType(Long posType) {
		this.posType = posType;
	}

	public Long getPosLevel() {
		return posLevel;
	}

	public void setPosLevel(Long posLevel) {
		this.posLevel = posLevel;
	}

	public String getPosState() {
		return posState;
	}

	public void setPosState(String posState) {
		this.posState = posState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Long getPposId() {
		return pposId;
	}

	public void setPposId(Long pposId) {
		this.pposId = pposId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getVisitSort() {
		return visitSort;
	}

	public void setVisitSort(Long visitSort) {
		this.visitSort = visitSort;
	}

	public String getUseState() {
		return useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getPosFlag() {
		return posFlag;
	}

	public void setPosFlag(String posFlag) {
		this.posFlag = posFlag;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPosTypeName() {
		return posTypeName;
	}

	public void setPosTypeName(String posTypeName) {
		this.posTypeName = posTypeName;
	}

	public String getPosLevelValue() {
		return posLevelValue;
	}

	public void setPosLevelValue(String posLevelValue) {
		this.posLevelValue = posLevelValue;
	}

	public String getPosInfo() {
		return posInfo;
	}

	public void setPosInfo(String posInfo) {
		this.posInfo = posInfo;
	}

	public String[] getPosIds() {
		return posIds;
	}

	public void setPosIds(String[] posIds) {
		this.posIds = posIds;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPosLevelType() {
		return posLevelType;
	}

	public void setPosLevelType(String posLevelType) {
		this.posLevelType = posLevelType;
	}

	public String getTot() {
		return tot;
	}

	public void setTot(String tot) {
		this.tot = tot;
	}

	public String getXf() {
		return xf;
	}

	public void setXf(String xf) {
		this.xf = xf;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getKa() {
		return ka;
	}

	public void setKa(String ka) {
		this.ka = ka;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public String getPosLevelName() {
		return posLevelName;
	}

	public void setPosLevelName(String posLevelName) {
		this.posLevelName = posLevelName;
	}

	
}
