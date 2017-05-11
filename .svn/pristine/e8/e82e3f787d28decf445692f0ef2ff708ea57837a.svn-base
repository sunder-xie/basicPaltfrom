package com.kintiger.platform.data.dao;

import java.util.List;
import java.util.Map;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.data.pojo.TableAuthorization;
import com.kintiger.platform.data.pojo.TableColumn;
import com.kintiger.platform.data.pojo.TableInfo;

public interface IDataManageDao {

	/**
	 * 查看数据库中当前用户名是否存在
	 * 
	 * @param userName
	 * @return
	 */
	int getCountByUserName(String userName);

	/**
	 * 查看数据库中当前表名是否存在
	 * 
	 * @param tableName
	 * @return
	 */
	int getCountByTabelName(String tableName);

	/**
	 * 新建表
	 * 
	 * @param tableInfo
	 */
	void createTable(TableInfo tableInfo);

	/**
	 * 添加注释
	 * 
	 * @param tableColumnList
	 */
	void createComment(List<TableColumn> tableColumnList);

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
	 * @param tableAuthorizationList
	 */
	void addAuthorization(List<TableAuthorization> tableAuthorizationList);

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
	 */
	void cancelAuthorization(String ids);

	/**
	 * 获取表字段信息
	 * 
	 * @param tableName
	 * @return
	 */
	List<TableColumn> getTableColumns(String tableName);

	/**
	 * 查看当前主键值数据库中是否存在
	 * 
	 * @param tableName
	 * @param primaryKeys
	 * @return
	 */
	int getCountByPrimaryKey(String tableName, String primaryKeys);

	/**
	 * 插入表数据
	 * 
	 * @param tableInfoList
	 */
	void createDataInfo(List<TableInfo> tableInfoList);

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
	 * @param tableName
	 * @return
	 */
	List<Map<String, Object>> getDataListNoPage(String tableName);

	/**
	 * 添加建表日志
	 * 
	 * @param userId
	 * @param tableName
	 */
	void createTableLog(String userId, String tableName);

	/**
	 * 添加数据上载日志
	 * 
	 * @param userId
	 * @param tableName
	 * @param count
	 */
	void createUploadLog(String userId, String tableName, int count);
	
	/**
	 * 清空表中数据
	 * 
	 * @param tableName
	 */
	void deleteData(String tableName);
	
	/**
	 * 添加数据清空日志
	 * 
	 * @param tableName
	 * @param userId
	 */
	void createDeleteLog(String tableName, String userId);

}
