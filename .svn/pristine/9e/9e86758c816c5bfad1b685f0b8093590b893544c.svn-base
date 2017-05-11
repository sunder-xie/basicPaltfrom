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

	String SUCCESS_MESSAGE = "操作成功!";

	String ERROR_MESSAGE = "操作失败!";

	/**
	 * 表名合法性验证
	 * 
	 * @param tableName
	 * @return
	 */
	int getTableNameValidateInfo(String tableName);

	/**
	 * 新建表
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
	 * 添加授权
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
	 * 取消授权
	 * 
	 * @param ids
	 * @return
	 */
	StringResult cancelAuthorization(String ids);
	
	/**
	 * 验证表是否存在
	 * 
	 * @param tableName
	 * @return
	 */
	int validTableName(String tableName);

	/**
	 * 数据模板下载
	 * 
	 * @param tableName
	 * @return
	 */
	File exportDataTemplate(String tableName, String type);

	/**
	 * Excel数据导入
	 * 
	 * @param tableName
	 * @param file
	 * @param userId
	 * @return
	 */
	StringResult importData(String tableName, File file, String userId);

	/**
	 * 获取表字段信息
	 * 
	 * @param tableName
	 * @return
	 */
	List<TableColumn> getTableColumns(String tableName);

	/**
	 * 查询表中数据总数
	 * 
	 * @param tableName
	 * @return
	 */
	int getDataCount(String tableName);

	/**
	 * 查询任意表数据
	 * 
	 * @param tableName
	 * @param start
	 * @param end
	 * @return
	 */
	List<Map<String, Object>> getDataList(String tableName, int start, int end);
	
	/**
	 * 清空表中数据
	 * 
	 * @param tableName
	 * @param userId
	 * @return
	 */
	StringResult deleteData(String tableName, String userId);
	

}
