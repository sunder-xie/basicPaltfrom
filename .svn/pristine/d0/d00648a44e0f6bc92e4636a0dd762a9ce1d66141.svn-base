package com.kintiger.platform.activitiWebEdit.dao.impl;

import java.util.List;

import com.kintiger.platform.activitiWebEdit.dao.IRoleDao;
import com.kintiger.platform.activitiWebEdit.pojo.WorkFlowRole;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;

@SuppressWarnings("rawtypes")
public class IRoleDaoImpl  extends BaseDaoImpl  implements IRoleDao{

	@SuppressWarnings("unchecked")
	public List<WorkFlowRole> searchAllRoles(WorkFlowRole wfr) {
		
		return getSqlMapClientTemplate().queryForList("workflow.searchRole",wfr);
	}

	public int searchAllRolesCount(WorkFlowRole wfr) {
		return (Integer) getSqlMapClientTemplate().queryForObject("workflow.searchRoleCount",wfr);
	}

}
