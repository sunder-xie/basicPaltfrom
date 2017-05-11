package com.kintiger.platform.position.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;

import org.apache.commons.lang.StringUtils;


import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.position.pojo.BpositionType;
import com.kintiger.platform.position.service.IPositionTypeService;

/**
 * 职位岗位
 * 
 * @author jhzhou
 * 
 */
public class PositionTypeAction extends BaseAction {

	private Logger logger = Logger.getLogger(PositionTypeAction.class);

	private static final long serialVersionUID = 3057481442362112386L;
	private int total = 0;
	private IPositionTypeService positionTypeService;
	private List<BpositionType> positionTypeList;
	@Decode
	private String roleId;
	private String positionTypeId;
	@Decode
	private String positionTypeName;
	
	

	/**
	 * 跳转到职位岗位查询页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchPositionType() {

		return "searchPositionType";
	}

	/**
	 * 获取职位岗位列表
	 * 
	 * @return
	 */
	@JsonResult(field = "positionTypeList", include = { "positionTypeId",
			"positionTypeName", "positionProperty", "companyName" }, total = "total")
	@PermissionSearch
	public String searchStationTypeList() {
		BpositionType bpositionType = new BpositionType();
		/*
		 * if (orgId !=null) { bpositionType.setCompanyId(Long.valueOf(orgId));
		 * } if (StringUtil.isNotEmpty(positionTypeId) &&
		 * StringUtil.isNotEmpty(positionTypeId.trim())) {
		 * bpositionType.setPositionTypeId(Long.valueOf(positionTypeId)); } if
		 * (StringUtil.isNotEmpty(positionTypeName) &&
		 * StringUtil.isNotEmpty(positionTypeName.trim())) {
		 * bpositionType.setPositionTypeName(positionTypeName); }
		 */

		bpositionType.setStart(getStart());
		bpositionType.setEnd(getEnd());
		total = positionTypeService.getPositionTypesCount(bpositionType);
		if (total != 0) {
			positionTypeList = positionTypeService
					.getPositionTypesList(bpositionType);
		}
		return JSON;
	}
	

	/**
	 * 职位岗位查询（角色）
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchPositionType4Role() {

		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {

			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}

		return "searchPositionType4Role";
	}


	/**
	 * 职位岗位查询（角色）
	 * 
	 * @return
	 */
	@JsonResult(field = "positionTypeList", include = { "positionTypeId",
			"positionTypeName", "positionProperty", "companyName" }, total = "total")
	@PermissionSearch
	public String getPositionType4RoleJsonList() {
		BpositionType bpositionType = new BpositionType();
		bpositionType = this.getSearchInfo(bpositionType);
		bpositionType.setStart(getStart());
		bpositionType.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId)) {
			bpositionType.setRoleId(roleId.trim());
		} 
		if(StringUtils.isNotEmpty(positionTypeId)&& StringUtils.isNotEmpty(positionTypeId.trim())){
			bpositionType.setPositionTypeId(Long.parseLong(positionTypeId));
		}if(StringUtils.isNotEmpty(positionTypeName)&& StringUtils.isNotEmpty(positionTypeName.trim())){
			bpositionType.setPositionTypeName(positionTypeName.trim());
		}
		total = positionTypeService.getPositionType4RoleCount(bpositionType);
		if (total != 0) {
			positionTypeList = positionTypeService
					.getPositionType4RoleList(bpositionType);
		}else{
			positionTypeList=null;
		}
		return JSON;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public IPositionTypeService getPositionTypeService() {
		return positionTypeService;
	}

	public void setPositionTypeService(IPositionTypeService positionTypeService) {
		this.positionTypeService = positionTypeService;
	}

	public List<BpositionType> getPositionTypeList() {
		return positionTypeList;
	}

	public void setPositionTypeList(List<BpositionType> positionTypeList) {
		this.positionTypeList = positionTypeList;
	}

	public String getPositionTypeId() {
		return positionTypeId;
	}

	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
	}

	public String getPositionTypeName() {
		return positionTypeName;
	}

	public void setPositionTypeName(String positionTypeName) {
		this.positionTypeName = positionTypeName;
	}
	

}
