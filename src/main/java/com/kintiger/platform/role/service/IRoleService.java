package com.kintiger.platform.role.service;

import java.util.List;

import com.kintiger.platform.role.pojo.Role;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.base.pojo.StringResult;

public interface IRoleService {
	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "操作失败！";

	public static final String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	public static final String ERROR_NULL_MESSAGE = "操作失败，单据已不存在！";

	/**
	 * 
	 * @param role
	 * @return
	 */
	public int getRoleCount(Role role);
	
	public int getBORoleCount(Role role);
	
	public int getBORoleDetailCount(Role role);
	
	public int getRole1Count(Role role);
	
	public int getRoleCount1(Role role);
	
	public int getConRole(Role role);
	/**
	 * 根据角色获取岗位信息
	 * @param positionType
	 * @return
	 */
	public int getPositionType4RoleCount(Role role);
	public List<Station> getPositionType4RoleList(Role role);
	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getRoleList(Role role);
	
	public List<Role> getBORoleList(Role role);
	
	public List<Role> getBODetailRoleList(Role role);
	
	public List<Role> getConstraintList(Role role);
	
	public List<Role> getRole1List(Role role);
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public StringResult createRole(Role role);
	
	public StringResult createRoledt(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public StringResult updateRole(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public StringResult deleteRole(Role role);
	
	public StringResult deleteRoledt(Role role);

	/**
	 * 权限点选择角色页面
	 * 
	 * @param role
	 * @return
	 */
	public int getRole4ConpointCount(Role role);

	/**
	 * 权限点选择角色页面
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getRole4ConpointList(Role role);

	/**
	 * 菜单选择角色页面
	 * 
	 * @param role
	 * @return
	 */
	public int getRole4MenuCount(Role role);

	/**
	 * 菜单选择角色页面
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getRole4MenuList(Role role);

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public Role getRoleByRoleId(String roleId);

	/**
	 * 获取权限岗位已选择的角色
	 * 
	 * @param role
	 * @return
	 */
	public int getSelectedRole4StationCount(Role role);

	/**
	 * 获取权限岗位已选择的角色
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getSelectedRole4StationList(Role role);

	/**
	 * 根据权限岗位选择角色
	 * 
	 * @param role
	 * @return
	 */
	public StringResult selectRole4Station(Role role);

	/**
	 * 删除权限岗位已选择角色
	 * 
	 * @param role
	 * @return
	 */
	public StringResult deleteSelectedRole4Station(Role role);

	/**
	 * 获取职位岗位已选择的角色
	 * 
	 * @param role
	 * @return
	 */
	public int getSelectedRole4PositionTypeCount(Role role);

	/**
	 * 获取职位岗位已选择的角色
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getSelectedRole4PositionTypeList(Role role);

	/**
	 * 根据职位岗位选择角色
	 * 
	 * @param role
	 * @return
	 */
	public StringResult selectRole4PositionType(Role role);

	/**
	 * 删除职位岗位已选择角色
	 * 
	 * @param role
	 * @return
	 */
	public StringResult deleteSelectedRole4PositionType(Role role);


}
