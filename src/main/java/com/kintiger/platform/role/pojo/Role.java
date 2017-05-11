package com.kintiger.platform.role.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 角色
 * 
 * @author zhyiyue
 * 
 */
public class Role extends SearchInfo {

	private static final long serialVersionUID = -5100230087589323253L;

	private String roleId;
	private String roleName;
	private String descn;
	private String state;
	private Date lastModify;
	private String ownFlag;
	private String roleType;

	/**
	 * 权限岗位
	 */
	private String stationId;

	private Long stationRoleId;

	/**
	 * 权限点id
	 */
	private Long conpointId;

	/**
	 * 职位岗位
	 */
	private String positionTypeId;

	private Long pyRoleId;

	/**
	 * 通用id 储存 stationId/positionTypeId
	 */
	private String id;

	/**
	 * 菜单id
	 */
	private Long menuId;
	/**
	 * 报表参数ID
	 */
	private String pid;
	
	/**
	 * 新增
	 */
	private String memo;
	
	private String value;
	
	private String  bid;
	
	private String userId;
	
	private String sign;
	
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getOwnFlag() {
		return ownFlag;
	}

	public void setOwnFlag(String ownFlag) {
		this.ownFlag = ownFlag;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public Long getStationRoleId() {
		return stationRoleId;
	}

	public void setStationRoleId(Long stationRoleId) {
		this.stationRoleId = stationRoleId;
	}

	public Long getConpointId() {
		return conpointId;
	}

	public void setConpointId(Long conpointId) {
		this.conpointId = conpointId;
	}

	public String getPositionTypeId() {
		return positionTypeId;
	}

	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
	}

	public Long getPyRoleId() {
		return pyRoleId;
	}

	public void setPyRoleId(Long pyRoleId) {
		this.pyRoleId = pyRoleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
