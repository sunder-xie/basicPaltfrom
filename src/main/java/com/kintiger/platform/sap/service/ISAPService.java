package com.kintiger.platform.sap.service;

import java.util.List;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISAPService {

	String RESULT_ERROR = "RFC����ʧ�ܣ�";

	/**
	 * ���ݵ�ǰ�û���sap��ɫ������sap��ʱ�ʺ�Ȩ��.
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
	 * ���sap��ʱ��Ȩ��.
	 * 
	 * @param passport
	 * @return
	 */
	boolean removePermission(String passport);

}
