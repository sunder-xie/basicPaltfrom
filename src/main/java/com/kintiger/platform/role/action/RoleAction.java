package com.kintiger.platform.role.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.menu.action.MenuTreeAjaxAction;
import com.kintiger.platform.role.pojo.Role;
import com.kintiger.platform.role.service.IRoleService;
import com.kintiger.platform.station.pojo.Station;

public class RoleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645806566226562765L;

	private static final Log logger = LogFactory
			.getLog(MenuTreeAjaxAction.class);
	private String orgId;
	private String bhxjFlag;
	private String loginId;
	private String userName;
	private String phone;
	private String mobile;
	private int total;
	private IRoleService roleService;
	@Decode
	private String roleId;
	@Decode
	private String roleName;
	@Decode
	private String stationId;
	@Decode
	private String stationName;
	@Decode
	private String positionTypeId;
	private List<Station> stationList = new ArrayList<Station>();
	private List<CmsTbDict> dictlist = new ArrayList<CmsTbDict>();
	private Role role;
	/**
	 * 权限点
	 */
	private String conpointId;
	private String roleIds;
	private String sids;
	private IDictService dictService;

	/**
	 * 菜单id
	 */
	private String menuId;

	private String ids;
	
	private String  ids1;

	private List<Role> roleList = new ArrayList<Role>();
	private List<Role> roleLists = new ArrayList<Role>();

	/**
	 * 查询菜单
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchRole() {
		return "searchRole";
	}

	/**
	 * 供权限点使用/供菜单使用
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchRole4Config() {

		return "searchRole4Config";
	}

	/**
	 * 取问题处理状态
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "dictlist", include = { "itemName", "itemValue" })
	public String getCustTypeList() {
		CmsTbDict cm = new CmsTbDict();
		cm.setItemName("角色类别");
		dictlist = dictService.getByCmsTbDictList(cm);
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
	@PermissionSearch
	@JsonResult(field = "stationList", include = { "stationId", "stationName",
			"orgId", "orgName" }, total = "total")
	public String getPositionType4RoleJsonList() {
		Role r = new Role();
		r.setStart(getStart());
		r.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim());
			if (StringUtils.isNotEmpty(stationName)
					&& StringUtils.isNotEmpty(stationName.trim())) {
				r.setRoleName(stationName.trim());
			}
			if (StringUtils.isNotEmpty(stationId)
					&& StringUtils.isNotEmpty(stationId.trim())) {
				r.setDescn(stationId.trim());
			}
			total = roleService.getPositionType4RoleCount(r);
			if (total != 0) {
				stationList = roleService.getPositionType4RoleList(r);
			} else {
				stationList = null;
			}
		} else {
			stationList = null;
		}
		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "roleList", include = { "roleId", "roleName", "descn",
			"roleType" }, total = "total")
	public String getRoleJsonList() {
		Role r = new Role();
		r.setStart(getStart());
		r.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim());
		}

		if (StringUtils.isNotEmpty(roleName)
				&& StringUtils.isNotEmpty(roleName.trim())) {
			r.setRoleName(roleName.trim());
		}
		total = roleService.getRoleCount(r);
		if (total != 0) {
			roleList = roleService.getRoleList(r);
		} else {
			roleList = null;
		}
		return JSON;
	}

	/**
	 * 修改/查询角色信息
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateRolePrepare() {
		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {

			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"),
						"UTF-8");
				role = roleService.getRoleByRoleId(roleId);
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}
		role = role == null ? new Role() : role;
		return UPDATE_PREPARE;
	}

	public String updateRole() {
		if (!validate(role)) {
			this.setFailMessage(IRoleService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}
		StringResult result = roleService.updateRole(role);
		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}else{
			this.setSuccessMessage("角色修改成功");
		}
		return RESULT_MESSAGE;
	}

	private boolean validate(Role role) {

		if (role == null) {
			return false;
		}
		if (StringUtils.isEmpty(role.getRoleId())
				|| StringUtils.isEmpty(role.getRoleId().trim())
				|| StringUtils.isEmpty(role.getRoleName())
				|| StringUtils.isEmpty(role.getRoleName().trim())) {
			return false;
		}

		return true;
	}

	@PermissionSearch
	public String createRolePrepare() {
		return CREATE_PREPARE;
	}

	/**
	 * 创建角色
	 * 
	 * @return
	 */
	public String createRole() {
		if (!validate(role)) {
			this.setFailMessage(IRoleService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}
		if(roleService.getRoleCount1(role)!=0l){
			this.setFailMessage("该角色已被占用");
			return RESULT_MESSAGE;
		}
		StringResult result = roleService.createRole(role);
		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}
		this.setSuccessMessage("创建成功");
		return RESULT_MESSAGE;
	}

	public String deleteRole() {
		String[] l = ids.split(",");
		Role role = new Role();
		Role role1 = new Role();
		role.setCodes(l);
		for(int i = 0 ;i<l.length;i++){
		    role1.setRoleId(l[i]);
		    if(roleService.getConRole(role1)>0){
		    	this.setFailMessage("角色ID"+l[i]+"下还有未删除权限点 ");
		    	return RESULT_MESSAGE;
		    }
		}
		StringResult result = roleService.deleteRole(role);
		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("已成功删除" + result.getResult() + "个角色!");
		}
		return RESULT_MESSAGE;
	}

	
	public String deleteRoledt() {
		String[] l = ids1.split(",");
		Role role = new Role();
		role.setCodes(l);
		StringResult result = roleService.deleteRoledt(role);
		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("已成功删除" + result.getResult() + "明細!");
		}
		return RESULT_MESSAGE;
	}
	/**
	 * 供权限点使用/供菜单使用
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "roleList", include = { "roleId", "roleName", "descn" }, total = "total")
	public String getRole4ConfigJsonList() {
		Role r = new Role();
		// r = getSearchInfo(r);
		r.setStart(getStart());
		r.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim());
		}

		if (StringUtils.isNotEmpty(roleName)
				&& StringUtils.isNotEmpty(roleName.trim())) {
			r.setRoleName(roleName.trim());
		}

		if (StringUtils.isNotEmpty(conpointId)
				&& StringUtils.isNotEmpty(conpointId.trim())) {
			try {
				r.setConpointId(Long.parseLong(conpointId.trim()));
			} catch (Exception e) {
				logger.error("conpointId:" + conpointId, e);
				return JSON;
			}

			total = roleService.getRole4ConpointCount(r);
			if (total != 0) {
				roleList = roleService.getRole4ConpointList(r);
			} else {
				roleList = null;
			}

		} else if (StringUtils.isNotEmpty(menuId)
				&& StringUtils.isNotEmpty(menuId.trim())) {
			try {
				r.setMenuId(Long.parseLong(menuId.trim()));
			} catch (Exception e) {
				logger.error("menuId:" + menuId, e);
				return JSON;
			}

			total = roleService.getRole4MenuCount(r);
			if (total != 0) {
				roleList = roleService.getRole4MenuList(r);
			} else {
				roleList = null;
			}
		}
		return JSON;
	}

	/**
	 * 选择角色 用于权限/职位岗位
	 * 
	 * @return
	 */
	public String selectRole() {
		Role r = new Role();
		StringResult result = null;

		if (StringUtils.isNotEmpty(roleIds)
				&& StringUtils.isNotEmpty(roleIds.trim())) {

			String[] temp = roleIds.split(",");
			String[] ids = new String[temp.length];
			int i = 0;

			for (String t : temp) {
				ids[i++] = t.trim();
			}
			r.setCodes(ids);
		}

		if (StringUtils.isNotEmpty(stationId)
				&& StringUtils.isNotEmpty(stationId.trim())) {
			r.setStationId(stationId.trim());

			result = roleService.selectRole4Station(r);
		}

		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		if (IRoleService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("成功维护角色编号：" + result.getResult());
		}

		return RESULT_MESSAGE;
	}

	public String searchAddressBookPre() {
		return SUCCESS;
	}

	@PermissionSearch
	@JsonResult(field = "roleList", include = { "id", "roleId", "roleName",
			"descn" }, total = "total")
	public String getSelectedRoleJsonList() {
		Role r = new Role();
		r = getSearchInfo(r);
		r.setStart(getStart());
		r.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId) &&
				  StringUtils.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim()
					);
		 }

		if (StringUtils.isNotEmpty(roleName)
				&& StringUtils.isNotEmpty(roleName.trim())) {
			r.setRoleName(roleName.trim());
		}

		if (StringUtils.isNotEmpty(stationId)
				&& StringUtils.isNotEmpty(stationId.trim())) {
			r.setStationId(stationId.trim());

			total = roleService.getSelectedRole4StationCount(r);
			if (total != 0) {
				roleList = roleService.getSelectedRole4StationList(r);
			} else {
				roleList = null;
			}
		}
		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "roleLists", include = { "id", "roleId", "roleName",
			"descn" }, total = "total")
	public String getSelectedRoleJsonListJosn() {
		Role r = new Role();
		r = getSearchInfo(r);
		r.setStart(getStart());
		r.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {
			try {
				roleId = new String(this.getRoleId().getBytes("ISO8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			r.setRoleId(roleId.trim());
			r.setRoleName(roleId.trim());
		}
		total = roleService.getRole1Count(r);
		if (total != 0) {
			roleLists = roleService.getRole1List(r);
		} else {
			roleLists = null;
		}

		return JSON;
	}

	/**
	 * 选择角色 用于权限/职位岗位
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchSelectedRole() {
		stationId = this.getStationId();
		roleId = "";
		return "searchSelectedRole";
	}

	/**
	 * 选择角色 用于权限/职位岗位
	 * 
	 * @return
	 */
	public String deleteSelectedRole() {
		int i = 0;
		Role role = new Role();
		String type = null;
		String[] ls = sids.split(",");
		String[] l = new String[ls.length];
		for (i = 0; i < ls.length; i++) {
			if (ls.length >= 0) {
				l[i] = ls[i];
				type = "station";
			}
		}
		/*
		 * for (Role r : roleList) { if (r.getStationRoleId() != null) { l[i++]
		 * = r.getStationRoleId().toString(); type = "station"; } }
		 */
		// 无有效的角色id
		if (i == 0) {
			this.setFailMessage(IRoleService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		role.setCodes(l);
		StringResult result = null;
		if ("station".equals(type)) {
			result = roleService.deleteSelectedRole4Station(role);
		} /*
		 * else if ("positionType".equals(type)) { result =
		 * roleService.deleteSelectedRole4PositionType(role); }
		 */
		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("已成功删除" + l.length + "个角色！");
		}

		return RESULT_MESSAGE;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getBhxjFlag() {
		return bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getConpointId() {
		return conpointId;
	}

	public void setConpointId(String conpointId) {
		this.conpointId = conpointId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getPositionTypeId() {
		return positionTypeId;
	}

	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
	}

	public List<Role> getRoleLists() {
		return roleLists;
	}

	public void setRoleLists(List<Role> roleLists) {
		this.roleLists = roleLists;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getSids() {
		return sids;
	}

	public void setSids(String sids) {
		this.sids = sids;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public List<CmsTbDict> getDictlist() {
		return dictlist;
	}

	public void setDictlist(List<CmsTbDict> dictlist) {
		this.dictlist = dictlist;
	}

	public String getIds1() {
		return ids1;
	}

	public void setIds1(String ids1) {
		this.ids1 = ids1;
	}

}
