package com.kintiger.platform.activitiWebEdit.service;

import java.util.List;

import com.kintiger.platform.activitiWebEdit.pojo.WorkFlowRole;

public interface RoleService {

	/**
	 * 查询角色list（企业架构）
	 * 
	 * @param 
	 * @return
	 */
	public List<WorkFlowRole> searchAllRoles(WorkFlowRole wfr);

	/**
	 * 查询角色count（企业架构）
	 * 
	 * @param 
	 * @return
	 */
	public int searchAllRolesCount(WorkFlowRole wfr);
	
	/**
	 * 发布自定义流程
	 * @param xmlString  xml文件内容
	 * @return
	 */
	public boolean deployProcessDefinition(String xmlString,String processname);
}
