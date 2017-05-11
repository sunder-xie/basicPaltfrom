package com.kintiger.platform.role.service;

import java.util.List;

import com.kintiger.platform.role.pojo.Role;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.base.pojo.StringResult;

public interface IRoleService {
	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	public static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	public static final String ERROR_NULL_MESSAGE = "����ʧ�ܣ������Ѳ����ڣ�";

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
	 * ���ݽ�ɫ��ȡ��λ��Ϣ
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
	 * Ȩ�޵�ѡ���ɫҳ��
	 * 
	 * @param role
	 * @return
	 */
	public int getRole4ConpointCount(Role role);

	/**
	 * Ȩ�޵�ѡ���ɫҳ��
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getRole4ConpointList(Role role);

	/**
	 * �˵�ѡ���ɫҳ��
	 * 
	 * @param role
	 * @return
	 */
	public int getRole4MenuCount(Role role);

	/**
	 * �˵�ѡ���ɫҳ��
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
	 * ��ȡȨ�޸�λ��ѡ��Ľ�ɫ
	 * 
	 * @param role
	 * @return
	 */
	public int getSelectedRole4StationCount(Role role);

	/**
	 * ��ȡȨ�޸�λ��ѡ��Ľ�ɫ
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getSelectedRole4StationList(Role role);

	/**
	 * ����Ȩ�޸�λѡ���ɫ
	 * 
	 * @param role
	 * @return
	 */
	public StringResult selectRole4Station(Role role);

	/**
	 * ɾ��Ȩ�޸�λ��ѡ���ɫ
	 * 
	 * @param role
	 * @return
	 */
	public StringResult deleteSelectedRole4Station(Role role);

	/**
	 * ��ȡְλ��λ��ѡ��Ľ�ɫ
	 * 
	 * @param role
	 * @return
	 */
	public int getSelectedRole4PositionTypeCount(Role role);

	/**
	 * ��ȡְλ��λ��ѡ��Ľ�ɫ
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getSelectedRole4PositionTypeList(Role role);

	/**
	 * ����ְλ��λѡ���ɫ
	 * 
	 * @param role
	 * @return
	 */
	public StringResult selectRole4PositionType(Role role);

	/**
	 * ɾ��ְλ��λ��ѡ���ɫ
	 * 
	 * @param role
	 * @return
	 */
	public StringResult deleteSelectedRole4PositionType(Role role);


}
