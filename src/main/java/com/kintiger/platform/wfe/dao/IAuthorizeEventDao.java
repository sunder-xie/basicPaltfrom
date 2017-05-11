package com.kintiger.platform.wfe.dao;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.pojo.ProEventLookUp;

public interface IAuthorizeEventDao {
	public List<AllUsers> getEmpListByOrgId(String orgId);
	
	public int getAuthorizationCount(ProEventLookUp proEventLookUp);
	
	public void createAuthorization(ProEventLookUp proEventLookUp);
	
	public void deleteAuthorization(Long lookUpId);
	
	public int getEventReaderListCount(ProEventLookUp proEventLookUp);
	
	public List<ProEventLookUp> getEventReaderList(ProEventLookUp proEventLookUp);
	
	public List<String> getEventIdList(String userId);
	
	public ProcessEventTotal getAuthorizeEventById(String eventId);
	
	public int getAuthorizeEventJsonListCount(ProcessEventTotal processEventTotal);
	
	public List<ProcessEventTotal> getAuthorizeEventJsonList(ProcessEventTotal processEventTotal);

}
