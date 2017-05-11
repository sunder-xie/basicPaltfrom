package com.kintiger.platform.wfe.dao.impl;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.dao.IAuthorizeEventDao;
import com.kintiger.platform.wfe.pojo.ProEventLookUp;

@SuppressWarnings("rawtypes")
public class AuthorizeEventDaoImpl extends BaseDaoImpl implements IAuthorizeEventDao {

	@SuppressWarnings("unchecked")
	public List<AllUsers> getEmpListByOrgId(String orgId) {
		Long org_id = Long.valueOf(orgId);
		return (List<AllUsers>)getSqlMapClientTemplate().queryForList("wfe.getEmpListByOrgId", org_id);
	}

	public int getAuthorizationCount(ProEventLookUp proEventLookUp) {
		return (Integer)getSqlMapClientTemplate().queryForObject("wfe.getAuthorizationCount", proEventLookUp);
	}

	public void createAuthorization(ProEventLookUp proEventLookUp) {
		getSqlMapClientTemplate().insert("wfe.createAuthorization", proEventLookUp);
	}
	
	public void deleteAuthorization(Long lookUpId) {
		getSqlMapClientTemplate().update("wfe.deleteAuthorization", lookUpId);
	}

	public int getEventReaderListCount(ProEventLookUp proEventLookUp) {
		return (Integer)getSqlMapClientTemplate().queryForObject("wfe.getEventReaderListCount", proEventLookUp);
	}

	@SuppressWarnings("unchecked")
	public List<ProEventLookUp> getEventReaderList(
			ProEventLookUp proEventLookUp) {
		return (List<ProEventLookUp>)getSqlMapClientTemplate().queryForList("wfe.getEventReaderList", proEventLookUp);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getEventIdList(String userId){
		return getSqlMapClientTemplate().queryForList("wfe.getEventIdList", userId);
	}

	public ProcessEventTotal getAuthorizeEventById(String eventId) {
		return (ProcessEventTotal)getSqlMapClientTemplate().queryForObject("wfe.getAuthorizeEventById", eventId);
	}
	
	public int getAuthorizeEventJsonListCount(ProcessEventTotal processEventTotal) {
		return (Integer)getSqlMapClientTemplate().queryForObject("wfe.getAuthorizeEventJsonListCount", processEventTotal);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcessEventTotal> getAuthorizeEventJsonList(ProcessEventTotal processEventTotal){
		return (List<ProcessEventTotal>)getSqlMapClientTemplate().queryForList("wfe.getAuthorizeEventJsonList", processEventTotal);
	}

}
