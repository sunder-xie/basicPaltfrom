package com.kintiger.platform.wfe.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.dao.IAuthorizeEventDao;
import com.kintiger.platform.wfe.pojo.ProEventLookUp;
import com.kintiger.platform.wfe.service.IAuthorizeEventService;

public class AuthorizeEventServiceImpl implements IAuthorizeEventService {
	private static final Log logger = LogFactory.getLog(AuthorizeEventServiceImpl.class);
	private IAuthorizeEventDao authorizeEventDao;

	public List<AllUsers> getEmpListByOrgId(String orgId) {
		try {
			return authorizeEventDao.getEmpListByOrgId(orgId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public StringResult createAuthorization(ProEventLookUp proEventLookUp) {
		StringResult result = new StringResult();
		result.setCode(IAuthorizeEventService.SUCCESS);
		for(String userId : proEventLookUp.getUserIds()){
			proEventLookUp.setUserId(userId);
			try {
				if(authorizeEventDao.getAuthorizationCount(proEventLookUp) == 0){
					authorizeEventDao.createAuthorization(proEventLookUp);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				result.setCode(IAuthorizeEventService.ERROR);
			}
		}
		return result;
	}
	
	public StringResult deleteAuthorization(String[] lookUpIds) {
		StringResult result = new StringResult();
		result.setCode(IAuthorizeEventService.SUCCESS);
		for(String lookUpId : lookUpIds){
			try {
				authorizeEventDao.deleteAuthorization(Long.valueOf(lookUpId));
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				result.setCode(IAuthorizeEventService.ERROR);
			}
		}
		return result;
	}

	public int getEventReaderListCount(ProEventLookUp proEventLookUp) {
		try {
			return authorizeEventDao.getEventReaderListCount(proEventLookUp);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	public List<ProEventLookUp> getEventReaderList(
			ProEventLookUp proEventLookUp) {
		try {
			return authorizeEventDao.getEventReaderList(proEventLookUp);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}
	
	public List<String> getEventIdList(String userId) {
		try {
			return authorizeEventDao.getEventIdList(userId);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}
	
	public int getAuthorizeEventJsonListCount(ProcessEventTotal processEventTotal) {
		try {
			return authorizeEventDao.getAuthorizeEventJsonListCount(processEventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	public List<ProcessEventTotal> getAuthorizeEventJsonList(
			ProcessEventTotal processEventTotal) {
		try {
			return authorizeEventDao.getAuthorizeEventJsonList(processEventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		
		return null;
	}
	
	public IAuthorizeEventDao getAuthorizeEventDao() {
		return authorizeEventDao;
	}

	public void setAuthorizeEventDao(IAuthorizeEventDao authorizeEventDao) {
		this.authorizeEventDao = authorizeEventDao;
	}
}
