package com.kintiger.platform.role.dao;

import java.util.List;

import com.kintiger.platform.role.pojo.Role;
import com.kintiger.platform.station.pojo.Station;

public interface IRoleDao {
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public int getRoleCount(Role role);
	
	public int getBORoleCount(Role role);
	
	public int getBORoleDetailCount(Role role);
	
	public int getRoleCount1(Role role);
	
	public int getConRole(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getRoleList(Role role);
	
	public List<Role> getBORoleList(Role role);
	
	public List<Role> getBODetailRoleList(Role role);
	
	public List<Role> getConstraintList(Role role);
	
	
	public List<Station> getPositionType4RoleList(Role role);
	
	/**
	 * 
	 * 
	 * @param role
	 * @return
	 */
	public int getPositionType4RoleCount(Role role);
	/**
	 * 
	 * @param role
	 * @return
	 */
	public String createRole(Role role);
	
	public String createRoledt(Role role);
	public int getRole1Count(Role role);
	public List<Role> getRole1List(Role role);
	/**
	 * 
	 * @param role
	 * @return
	 */
	public int updateRole(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public int deleteRole(Role role);
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public int deleteRoledt(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public int getRole4ConpointCount(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getRole4ConpointList(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public int getRole4MenuCount(Role role);

	/**
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
	 * 
	 * @param role
	 * @return
	 */
	public int getSelectedRole4StationCount(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getSelectedRole4StationList(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getSelectedRole4Station(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public String selectRole4Station(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public int deleteSelectedRole4Station(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public int getSelectedRole4PositionTypeCount(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getSelectedRole4PositionTypeList(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getSelectedRole4PositionType(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public String selectRole4PositionType(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public int deleteSelectedRole4PositionType(Role role);


}
