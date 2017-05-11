package com.kintiger.platform.data.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.data.dao.IDataManageDao;
import com.kintiger.platform.data.pojo.TableAuthorization;
import com.kintiger.platform.data.pojo.TableColumn;
import com.kintiger.platform.data.pojo.TableInfo;

@SuppressWarnings("rawtypes")
public class DataManageDaoImpl extends BaseDaoImpl implements IDataManageDao {

	public int getCountByUserName(String userName) {
		return (Integer) getSqlMapClientTemplate().queryForObject("data.getCountByUserName", userName);
	}

	public int getCountByTabelName(String tableName) {
		return (Integer) getSqlMapClientTemplate().queryForObject("data.getCountByTabelName", tableName);
	}

	public void createTable(TableInfo tableInfo) {
		this.getSqlMapClientTemplate().update("data.createTable", tableInfo);
	}

	public void createComment(final List<TableColumn> tableColumnList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				for (TableColumn tableColumn : tableColumnList) {
					executor.update("data.createComment", tableColumn);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getAllUserCount(AllUsers allUser) {
		return (Integer) getSqlMapClientTemplate().queryForObject("data.getAllUserCount", allUser);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> getAllUserList(AllUsers allUser) {
		return getSqlMapClientTemplate().queryForList("data.getAllUserList", allUser);
	};

	public int getTableNameCount(TableInfo tableInfo) {
		return (Integer) getSqlMapClientTemplate().queryForObject("data.getTableNameCount", tableInfo);
	}

	@SuppressWarnings("unchecked")
	public List<TableInfo> getTableNameList(TableInfo tableInfo) {
		return getSqlMapClientTemplate().queryForList("data.getTableNameList", tableInfo);
	}

	public void addAuthorization(final List<TableAuthorization> tableAuthorizationList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				for (TableAuthorization tableAuthorization : tableAuthorizationList) {
					int count =
						(Integer) getSqlMapClientTemplate().queryForObject("data.getAuthorizationCount",
							tableAuthorization);
					if (count == 0) {
						executor.insert("data.addAuthorization", tableAuthorization);
					}

				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getTableAuthorizationCount(TableAuthorization tableAuthorization) {
		return (Integer) getSqlMapClientTemplate()
			.queryForObject("data.getTableAuthorizationCount", tableAuthorization);
	}

	@SuppressWarnings("unchecked")
	public List<TableAuthorization> getTableAuthorizationList(TableAuthorization tableAuthorization) {
		return getSqlMapClientTemplate().queryForList("data.getTableAuthorizationList", tableAuthorization);
	}

	public void cancelAuthorization(String ids) {
		getSqlMapClientTemplate().delete("data.cancelAuthorization", ids);
	}

	@SuppressWarnings("unchecked")
	public List<TableColumn> getTableColumns(String tableName) {
		return getSqlMapClientTemplate().queryForList("data.getTableColumns", tableName);
	}

	public int getCountByPrimaryKey(String tableName, String primaryKeys) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("primaryKeys", primaryKeys);
		return (Integer) getSqlMapClientTemplate().queryForObject("data.getCountByPrimaryKey", map);
	}

	public void createDataInfo(List<TableInfo> tableInfoList) {
//		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
//			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
//				executor.startBatch();// 游标数溢出
//				for (TableInfo tableInfo : tableInfoList) {
//					executor.insert("data.createDataInfo", tableInfo);
//				}
//				executor.executeBatch();
//				return null;
//			}
//		});
		for (TableInfo tableInfo : tableInfoList) {
			if(tableInfo.getTableName().equals("BASIS.FACTORY_PERSON_NUM")){
				int num = (Integer)getSqlMapClientTemplate().queryForObject("data.getFACTORY_PERSON_NUM", tableInfo);
				if(num>0){
				    getSqlMapClientTemplate().update("data.updateFACTORY_PERSON_NUM", tableInfo);
				}else{
					getSqlMapClientTemplate().insert("data.createDataInfo", tableInfo);
				}
			}else{
				getSqlMapClientTemplate().insert("data.createDataInfo", tableInfo);
			}
		}
	}

	public int getDataCount(String tableName) {
		return (Integer) getSqlMapClientTemplate().queryForObject("data.getDataCount", tableName);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDataList(String tableName, int start, int end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("start", start);
		map.put("end", end);
		return getSqlMapClientTemplate().queryForList("data.getDataList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDataListNoPage(String tableName) {
		return getSqlMapClientTemplate().queryForList("data.getDataListNoPage", tableName);
	}

	public void createTableLog(String userId, String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("tableName", tableName);
		getSqlMapClientTemplate().insert("data.createTableLog", map);
	}

	public void createUploadLog(String userId, String tableName, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("tableName", tableName);
		map.put("count", count);
		getSqlMapClientTemplate().insert("data.createUploadLog", map);
	}
	
	public void deleteData(String tableName) {
		getSqlMapClientTemplate().delete("data.deleteData", tableName);
	}
	
	public void createDeleteLog(String tableName, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("tableName", tableName);
		getSqlMapClientTemplate().insert("data.createDeleteLog", map);
	}

}
