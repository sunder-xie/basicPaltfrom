package com.kintiger.platform.sap.service;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISSOService {

	String RESULT_ERROR = "Portal���ʧ�ܣ�";

	/**
	 * �����ʺ��������sap.
	 * 
	 * @param user
	 * @param password
	 * @return
	 * @throws SystemException
	 */
	String getSSOUrl(String user, String password) throws Exception;

	/**
	 * �����ʺ��������sap(MYSAPSSO2).
	 * 
	 * @param user
	 * @param password
	 * @return
	 * @throws SystemException
	 */
	String getMySAPSSO2Ticket(String user, String password) throws Exception;

}
