package com.kintiger.platform.wfe.service;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.pojo.ProEventLookUp;

public interface IAuthorizeEventService {
	public static final String ERROR = "error";
	
	public static final String SUCCESS = "success";
	
	public static final String ERROR_MESSAGE = "操作失败！";
	
	public static final String SUCCESS_MESSAGE = "操作成功！";
	
	/**
	 * 根据组织ID查找人员信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getEmpListByOrgId(String orgId);
	
	/**
	 * 创建授权事务查看
	 * @param proEventLookUp
	 * @return
	 */
	public StringResult createAuthorization(ProEventLookUp proEventLookUp);
	
	/**
	 * 删除授权事务查看
	 * @return
	 */
	public StringResult deleteAuthorization(String[] lookUpIds);
	
	/**
	 * @param proEventLookUp
	 * @return
	 */
	public int getEventReaderListCount(ProEventLookUp proEventLookUp);
	
	/**
	 * @param proEventLookUp
	 * @return
	 */
	public List<ProEventLookUp> getEventReaderList(ProEventLookUp proEventLookUp);
	
	/**
	 * 根据用户Id查找授权事务Id
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getEventIdList(String userId);
	
	public List<ProcessEventTotal> getAuthorizeEventJsonList(ProcessEventTotal processEventTotal);
	
	public int getAuthorizeEventJsonListCount(ProcessEventTotal processEventTotal);

}
