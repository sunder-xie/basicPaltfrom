package com.kintiger.platform.data.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.data.pojo.DataGridInfo;
import com.kintiger.platform.data.pojo.TableAuthorization;
import com.kintiger.platform.data.pojo.TableColumn;
import com.kintiger.platform.data.pojo.TableInfo;
import com.kintiger.platform.data.service.IDataManageService;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;

public class DataManageAction extends BaseAction {

	private static final long serialVersionUID = 8046744582339014255L;

	private static final Logger logger = Logger.getLogger(DataManageAction.class);

	private int validateInfo;

	private String tableName;

	private IDataManageService dataManageService;

	private List<TableColumn> tableColumnList;

	private List<AllUsers> allUserList;

	private String searchStr;

	private int total;

	private String empId;

	private List<TableInfo> tableNameList;

	private String tableNames;

	private List<TableAuthorization> tableAuthorizationList;

	private String ids;

	private String download;

	private File upload;

	private String uploadFileName;

	private String datagridHead;

	private String datagridContent;

	private String gridColumns;

	private String gridData;

	private DataGridInfo dataGridInfo;

	private String type;

	/**
	 * 数据表新建页面跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toTableCreate() {
		return "toTableCreate";
	}

	/**
	 * 表名合法性验证
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "validateInfo")
	public String tableNameValidate() {
		validateInfo = dataManageService.getTableNameValidateInfo(tableName);
		return JSON;
	}

	/**
	 * 新建表
	 * 
	 * @return
	 */
	public String createTable() {
		StringResult result = dataManageService.createTable(getUser().getUserId(), tableName, tableColumnList);
		if (IDataManageService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("创建成功!");
		} else {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 数据表导入权限管理页面跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toAuthorizationManage() {
		return "toAuthorizationManage";
	}

	/**
	 * 查询人员信息列表
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "allUserList", include = { "userId", "loginId", "userName" }, total = "total")
	public String getAllUserJsonList() {
		AllUsers allUser = new AllUsers();
		if (StringUtils.isNotEmpty(searchStr) && StringUtils.isNotEmpty(searchStr.trim())) {
			try {
				searchStr = new String(getServletRequest().getParameter("searchStr").getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			allUser.setSearch(searchStr);
		}
		allUser.setStart(getStart());
		allUser.setEnd(getEnd());
		total = dataManageService.getAllUserCount(allUser);
		if (total != 0) {
			allUserList = dataManageService.getAllUserList(allUser);
		}
		return JSON;
	}

	/**
	 * 查询表名列表
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tableNameList", include = { "tableName" }, total = "total")
	public String getTableNameJsonList() {
		TableInfo tableInfo = new TableInfo();
		if (StringUtils.isNotEmpty(searchStr) && StringUtils.isNotEmpty(searchStr.trim())) {
			try {
				searchStr = new String(getServletRequest().getParameter("searchStr").getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			tableInfo.setTableName(searchStr.toUpperCase());
		}
		tableInfo.setStart(getStart());
		tableInfo.setEnd(getEnd());
		total = dataManageService.getTableNameCount(tableInfo);
		if (total != 0) {
			tableNameList = dataManageService.getTableNameList(tableInfo);
		}
		return JSON;
	}

	/**
	 * 查询数据表授权信息列表
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tableAuthorizationList", include = { "id", "empName", "tableName", "authorizerName",
		"authorizeDate" }, total = "total")
	public String getTableAuthorizationJsonList() {
		TableAuthorization tableAuthorization = new TableAuthorization();
		if (StringUtils.isNotEmpty(empId) && StringUtils.isNotEmpty(empId.trim())) {
			tableAuthorization.setEmpId(Long.parseLong(empId));
		}
		if (StringUtils.isNotEmpty(searchStr) && StringUtils.isNotEmpty(searchStr.trim())) {
			try {
				searchStr = new String(getServletRequest().getParameter("searchStr").getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			tableAuthorization.setTableName(searchStr.toUpperCase());
		}
		tableAuthorization.setStart(getStart());
		tableAuthorization.setEnd(getEnd());
		total = dataManageService.getTableAuthorizationCount(tableAuthorization);
		if (total != 0) {
			tableAuthorizationList = dataManageService.getTableAuthorizationList(tableAuthorization);
		}
		return JSON;
	}

	/**
	 * 添加授权
	 * 
	 * @return
	 */
	public String addAuthorization() {
		StringResult result = dataManageService.addAuthorization(empId, tableNames, getUser().getUserId());
		if (IDataManageService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("授权成功!");
		} else {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 取消授权
	 * 
	 * @return
	 */
	public String cancelAuthorization() {
		StringResult result = dataManageService.cancelAuthorization(ids);
		if (IDataManageService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("操作成功!");
		} else {
			this.setFailMessage("操作失败!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 数据操作（模板下载、数据上载）页面跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toDataOperate() {
		empId = getUser().getUserId();
		return "toDataOperate";
	}

	/**
	 * 验证表是否存在
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "validateInfo")
	public String validTableName() {
		validateInfo = dataManageService.validTableName(tableName);
		return JSON;
	}

	/**
	 * 数据模板下载
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportDataTemplate() {
		ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Ing");
		try {
			File source = dataManageService.exportDataTemplate(tableName, type);
			if (source != null) {
				display(source, tableName + "模板.xls", ServletActionContext.getResponse());
				source.delete();
				ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Over");
			} else {
				this.setFailMessage("Excel模板导出出错!");
			}
		} catch (Exception e) {
			this.setFailMessage("Excel模板导出出错!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 文件下载
	 * 
	 * @param file
	 * @param fileName
	 * @param response
	 * @return6
	 */
	@SuppressWarnings("finally")
	@PermissionSearch
	private boolean display(File file, String fileName, HttpServletResponse response) {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName);
			in = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			response.flushBuffer();
		} catch (Exception ex) {
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final Exception e) {
				}
			}
			return true;
		}
	}

	/**
	 * 数据导入页面初始化
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toDataImport() {
		return "toDataImport";
	}

	/**
	 * Excel数据导入
	 * 
	 * @return
	 */
	public String importData() {
		StringResult result = dataManageService.importData(tableName, upload, getUser().getUserId());
		if (IDataManageService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(result.getResult());
		} else {
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 校验数据是否下载完成
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "download")
	public String checkDownLoadOver() {
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("DownLoad");
		if (obj == null || "Ing".equals(obj)) {
			download = "No";
		} else {
			download = "Yes";
		}
		return JSON;
	}

	/**
	 * 获取任意数据表格的列头
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "gridColumns")
	public String getDataColumns() {
		List<TableColumn> tableColumns = dataManageService.getTableColumns(tableName);
		StringBuilder sb = new StringBuilder();
		sb.append("[[");
		for (int i = 0; i < tableColumns.size(); i++) {
			String comments =
				(StringUtils.isNotEmpty(tableColumns.get(i).getComments()) && StringUtils.isNotEmpty(tableColumns
					.get(i).getComments().trim())) ? "(" + tableColumns.get(i).getComments().replace("'", "") + ")"
					: "";
			sb.append("{field : '").append(tableColumns.get(i).getColumnName()).append("fix").append("', title : '")
				.append(tableColumns.get(i).getColumnName()).append(comments)
				.append("', align : 'center', width : 120},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]]");
		setGridColumns(sb.toString());
		return JSON;
	}

	/**
	 * 查看任意表数据
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "gridData")
	public String getData() {
		List<TableColumn> tableColumns = dataManageService.getTableColumns(tableName);
		int dataCount = dataManageService.getDataCount(tableName);
		List<Map<String, Object>> dataList = dataManageService.getDataList(tableName, getStart(), getEnd());
		StringBuilder sb = new StringBuilder();

		sb.append("{'total' : ").append(dataCount).append(", 'rows' : [");
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				sb.append("{");
				for (int j = 0; j < tableColumns.size(); j++) {
					String value =
						dataList.get(i).get(tableColumns.get(j).getColumnName()) == null ? "''" : "'"
							+ dataList.get(i).get(tableColumns.get(j).getColumnName()) + "'";
					sb.append("'").append(tableColumns.get(j).getColumnName()).append("fix").append("' : ")
						.append(value).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("},");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]}");
		setGridData(sb.toString());
		return JSON;
	}

	/**
	 * 加载表头和数据
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "dataGridInfo", include = { "gridColumn", "gridData" })
	public String getDataGridInfo() {
		dataGridInfo = new DataGridInfo();
		List<TableColumn> tableColumns = dataManageService.getTableColumns(tableName);
		int dataCount = dataManageService.getDataCount(tableName);
		List<Map<String, Object>> dataList = dataManageService.getDataList(tableName, getStart(), getEnd());
		StringBuilder data = new StringBuilder();
		StringBuilder column = new StringBuilder();
		column.append("[[");
		if (tableColumns != null) {
			for (int i = 0; i < tableColumns.size(); i++) {
				String comments =
					(StringUtils.isNotEmpty(tableColumns.get(i).getComments()) && StringUtils.isNotEmpty(tableColumns
						.get(i).getComments().trim())) ? "(" + tableColumns.get(i).getComments().replace("'", "") + ")"
						: "";// 去除字段注释中的'(单引号)
				column.append("{field : '").append(tableColumns.get(i).getColumnName()).append("fix")
					.append("', title : '").append(tableColumns.get(i).getColumnName()).append(comments)
					.append("', align : 'center', width : 120},");
			}
			if (tableColumns.size() > 0) {
				column.deleteCharAt(column.length() - 1);
			}
		}
		column.append("]]");
		dataGridInfo.setGridColumn(column.toString());
		data.append("{'total' : ").append(dataCount).append(", 'rows' : [");
		if (dataList != null) {
			for (int i = 0; i < dataList.size(); i++) {
				data.append("{");
				for (int j = 0; j < tableColumns.size(); j++) {
					String value =
						dataList.get(i).get(tableColumns.get(j).getColumnName()) == null ? "''" : "'"
							+ dataList.get(i).get(tableColumns.get(j).getColumnName()) + "'";
					data.append("'").append(tableColumns.get(j).getColumnName()).append("fix").append("' : ")
						.append(value).append(",");
				}
				data.deleteCharAt(data.length() - 1);
				data.append("},");
			}
			if (dataList.size() > 0) {
				data.deleteCharAt(data.length() - 1);
			}
		}
		data.append("]}");
		dataGridInfo.setGridData(data.toString());
		return JSON;
	}

	/**
	 * 清空表数据
	 * 
	 * @return
	 */
	public String deleteData() {
		StringResult result = dataManageService.deleteData(tableName, getUser().getUserId());
		if (IDataManageService.SUCCESS.equals(result.getCode()))
			setSuccessMessage("操作成功！");
		else {
			setFailMessage("操作失败！");
		}
		return RESULT_MESSAGE;
	}

	public int getValidateInfo() {
		return validateInfo;
	}

	public void setValidateInfo(int validateInfo) {
		this.validateInfo = validateInfo;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public IDataManageService getDataManageService() {
		return dataManageService;
	}

	public void setDataManageService(IDataManageService dataManageService) {
		this.dataManageService = dataManageService;
	}

	public List<TableColumn> getTableColumnList() {
		return tableColumnList;
	}

	public void setTableColumnList(List<TableColumn> tableColumnList) {
		this.tableColumnList = tableColumnList;
	}

	public List<AllUsers> getAllUserList() {
		return allUserList;
	}

	public void setAllUserList(List<AllUsers> allUserList) {
		this.allUserList = allUserList;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public List<TableInfo> getTableNameList() {
		return tableNameList;
	}

	public void setTableNameList(List<TableInfo> tableNameList) {
		this.tableNameList = tableNameList;
	}

	public String getTableNames() {
		return tableNames;
	}

	public void setTableNames(String tableNames) {
		this.tableNames = tableNames;
	}

	public List<TableAuthorization> getTableAuthorizationList() {
		return tableAuthorizationList;
	}

	public void setTableAuthorizationList(List<TableAuthorization> tableAuthorizationList) {
		this.tableAuthorizationList = tableAuthorizationList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDatagridHead() {
		return datagridHead;
	}

	public void setDatagridHead(String datagridHead) {
		this.datagridHead = datagridHead;
	}

	public String getDatagridContent() {
		return datagridContent;
	}

	public void setDatagridContent(String datagridContent) {
		this.datagridContent = datagridContent;
	}

	public String getGridColumns() {
		return gridColumns;
	}

	public void setGridColumns(String gridColumns) {
		this.gridColumns = gridColumns;
	}

	public String getGridData() {
		return gridData;
	}

	public void setGridData(String gridData) {
		this.gridData = gridData;
	}

	public void setDataGridInfo(DataGridInfo dataGridInfo) {
		this.dataGridInfo = dataGridInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
