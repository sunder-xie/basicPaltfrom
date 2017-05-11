package com.kintiger.platform.activitiWebEdit.dao;

import java.util.List;

import com.kintiger.platform.activitiWebEdit.pojo.WorkFlowRole;

public interface IRoleDao {

	/**
	 * 查询所有流程角色（企业架构）
	 * 
	 * @param AllUsers
	 * @return
	 */
	public List<WorkFlowRole> searchAllRoles(WorkFlowRole wfr);
	/**
	 * 查询所有流程角色Count（企业架构）
	 * 
	 * @param AllUsers
	 * @return
	 */
	public int searchAllRolesCount(WorkFlowRole wfr);
	
}
