package com.kintiger.platform.sap.service;

import java.util.List;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISAPService {

	String RESULT_ERROR = "RFC操作失败！";

	/**
	 * 根据当前用户的sap角色，更新sap临时帐号权限.
	 * 
	 * @param passport
	 * @param roles
	 * @param loginId
	 * @param ip
	 * @return
	 * @throws SystemException
	 */
	String updatePermission(String passport, List<String> roles, String loginId, String ip) throws Exception;

	/**
	 * 清空sap临时帐权限.
	 * 
	 * @param passport
	 * @return
	 */
	boolean removePermission(String passport);

}
