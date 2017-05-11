package com.kintiger.platform.data.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.data.pojo.TableAuthorization;
import com.kintiger.platform.data.pojo.TableColumn;
import com.kintiger.platform.data.pojo.TableInfo;

public interface IDataManageService {

	String SUCCESS = "success";

	String ERROR = "error";

	String SUCCESS_MESSAGE = "�����ɹ�!";

	String ERROR_MESSAGE = "����ʧ��!";

	/**
	 * �����Ϸ�����֤
	 * 
	 * @param tableName
	 * @return
	 */
	int getTableNameValidateInfo(String tableName);

	/**
	 * �½���
	 * 
	 * @param userId
	 * @param tableName
	 * @param tableColumnList
	 * @return
	 */
	StringResult createTable(String userId, String tableName, List<TableColumn> tableColumnList);

	/**
	 * @param allUser
	 * @return
	 */
	int getAllUserCount(AllUsers allUser);

	/**
	 * @param allUser
	 * @return
	 */
	List<AllUsers> getAllUserList(AllUsers allUser);

	/**
	 * @param tableInfo
	 * @return
	 */
	int getTableNameCount(TableInfo tableInfo);

	/**
	 * @param tableInfo
	 * @return
	 */
	List<TableInfo> getTableNameList(TableInfo tableInfo);

	/**
	 * �����Ȩ
	 * 
	 * @param empId
	 * @param tableNames
	 * @param userId
	 * @return
	 */
	StringResult addAuthorization(String empId, String tableNames, String authorizer);

	/**
	 * @param tableAuthorization
	 * @return
	 */
	int getTableAuthorizationCount(TableAuthorization tableAuthorization);

	/**
	 * @param tableAuthorization
	 * @return
	 */
	List<TableAuthorization> getTableAuthorizationList(TableAuthorization tableAuthorization);

	/**
	 * ȡ����Ȩ
	 * 
	 * @param ids
	 * @return
	 */
	StringResult cancelAuthorization(String ids);
	
	/**
	 * ��֤���Ƿ����
	 * 
	 * @param tableName
	 * @return
	 */
	int validTableName(String tableName);

	/**
	 * ����ģ������
	 * 
	 * @param tableName
	 * @return
	 */
	File exportDataTemplate(String tableName, String type);

	/**
	 * Excel���ݵ���
	 * 
	 * @param tableName
	 * @param file
	 * @param userId
	 * @return
	 */
	StringResult importData(String tableName, File file, String userId);

	/**
	 * ��ȡ���ֶ���Ϣ
	 * 
	 * @param tableName
	 * @return
	 */
	List<TableColumn> getTableColumns(String tableName);

	/**
	 * ��ѯ������������
	 * 
	 * @param tableName
	 * @return
	 */
	int getDataCount(String tableName);

	/**
	 * ��ѯ���������
	 * 
	 * @param tableName
	 * @param start
	 * @param end
	 * @return
	 */
	List<Map<String, Object>> getDataList(String tableName, int start, int end);
	
	/**
	 * ��ձ�������
	 * 
	 * @param tableName
	 * @param userId
	 * @return
	 */
	StringResult deleteData(String tableName, String userId);
	

}
