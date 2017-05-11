package com.kintiger.platform.data.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.data.dao.IDataManageDao;
import com.kintiger.platform.data.pojo.TableAuthorization;
import com.kintiger.platform.data.pojo.TableColumn;
import com.kintiger.platform.data.pojo.TableInfo;
import com.kintiger.platform.data.service.IDataManageService;

public class DataManageServiceImpl implements IDataManageService {

	private static final Logger logger = Logger.getLogger(DataManageServiceImpl.class);

	private IDataManageDao dataManageDao;

	private String excelTemplateFilePath;

	public int getTableNameValidateInfo(String tableName) {
		int count;
		try {
			count = dataManageDao.getCountByUserName(tableName.split("\\.")[0]);
			if (count == 0) {// 当前用户在数据库里不存在
				return 1;
			} else {
				count = dataManageDao.getCountByTabelName(tableName);
				if (count == 1) {// 当前表名数据库里已经存在
					return 2;
				}
			}
			return 0;
		} catch (Exception e) {
			logger.error(e);
		}
		return 3;// 验证失败
	}

	@SuppressWarnings("rawtypes")
	public StringResult createTable(String userId, String tableName, List<TableColumn> tableColumnList) {
		StringResult result = new StringResult();
		try {
			TableInfo tableInfo = new TableInfo();
			tableInfo.setTableName(tableName);
			StringBuilder pk = new StringBuilder();
			List<TableColumn> commentInfoList = new ArrayList<TableColumn>();
			for (int i = 0; i < tableColumnList.size(); i++) {
				Class[] c = { String.class };
				String fieldDetail = "";
				Method method = BeanUtils.findMethod(tableInfo.getClass(), "setFieldDetail" + (i + 1), c);
				if (tableColumnList.get(i).getDataLength() == null) {
					fieldDetail =
						"not null".equals(tableColumnList.get(i).getDataConstraint()) ? tableColumnList.get(i)
							.getColumnName() + " " + tableColumnList.get(i).getDataType() + " not null"
							: tableColumnList.get(i).getColumnName() + " " + tableColumnList.get(i).getDataType();
				} else {
					fieldDetail =
						"not null".equals(tableColumnList.get(i).getDataConstraint()) ? tableColumnList.get(i)
							.getColumnName()
							+ " "
							+ tableColumnList.get(i).getDataType()
							+ "("
							+ tableColumnList.get(i).getDataLength() + ")" + " not null" : tableColumnList.get(i)
							.getColumnName()
							+ " "
							+ tableColumnList.get(i).getDataType()
							+ "("
							+ tableColumnList.get(i).getDataLength() + ")";
				}
				method.invoke(tableInfo, new Object[] { fieldDetail });

				if ("primary key".equals(tableColumnList.get(i).getDataConstraint())) {
					if (pk.length() > 0) {
						pk.append(",");
					}
					pk.append(tableColumnList.get(i).getColumnName());
				}
				if (pk.length() > 0) {
					tableInfo.setPrimaryKey(pk.toString());
				}

				TableColumn tableColumn = new TableColumn();
				tableColumn.setColumnName(tableName + "." + tableColumnList.get(i).getColumnName());
				tableColumn.setComments(tableColumnList.get(i).getComments() == null ? "''" : "'"
					+ tableColumnList.get(i).getComments() + "'");
				commentInfoList.add(tableColumn);
			}
			dataManageDao.createTable(tableInfo);
			dataManageDao.createComment(commentInfoList);
			dataManageDao.createTableLog(userId, tableName);
			result.setCode(IDataManageService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IDataManageService.ERROR);
		}
		return result;
	}

	public int getAllUserCount(AllUsers allUser) {
		try {
			return dataManageDao.getAllUserCount(allUser);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	public List<AllUsers> getAllUserList(AllUsers allUser) {
		try {
			return dataManageDao.getAllUserList(allUser);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public int getTableNameCount(TableInfo tableInfo) {
		try {
			return dataManageDao.getTableNameCount(tableInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	public List<TableInfo> getTableNameList(TableInfo tableInfo) {
		try {
			return dataManageDao.getTableNameList(tableInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public StringResult addAuthorization(String empId, String tableNames, String authorizer) {
		StringResult result = new StringResult();
		try {
			List<TableAuthorization> tableAuthorizationList = new ArrayList<TableAuthorization>();
			String[] tableNameArr = tableNames.split(",");
			for (String tableName : tableNameArr) {
				TableAuthorization tableAuthorization = new TableAuthorization();
				tableAuthorization.setEmpId(Long.parseLong(empId));
				tableAuthorization.setTableName(tableName.trim());
				tableAuthorization.setAuthorizer(Long.parseLong(authorizer));
				tableAuthorizationList.add(tableAuthorization);
			}
			dataManageDao.addAuthorization(tableAuthorizationList);
			result.setCode(IDataManageService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IDataManageService.ERROR);
		}
		return result;
	}

	public int getTableAuthorizationCount(TableAuthorization tableAuthorization) {
		try {
			return dataManageDao.getTableAuthorizationCount(tableAuthorization);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	public List<TableAuthorization> getTableAuthorizationList(TableAuthorization tableAuthorization) {
		try {
			return dataManageDao.getTableAuthorizationList(tableAuthorization);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public StringResult cancelAuthorization(String ids) {
		StringResult result = new StringResult();
		try {
			dataManageDao.cancelAuthorization(ids);
			result.setCode(IDataManageService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IDataManageService.ERROR);
		}
		return result;
	}

	public int validTableName(String tableName) {
		int count;
		try {
			count = dataManageDao.getCountByTabelName(tableName);
			if (count == 0) {// 当前表数据库里不存在
				return 1;
			}
			return 0;
		} catch (Exception e) {
			logger.error(e);
		}
		return 2;// 验证失败
	}

	public File exportDataTemplate(String tableName, String type) {
		try {
			File saveDir = new File(excelTemplateFilePath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			File rootFile = new File(excelTemplateFilePath + "/" + "template.xls");
			if (!rootFile.exists()) {
				rootFile.createNewFile();
			}

			List<TableColumn> tableColumnList = dataManageDao.getTableColumns(tableName);
			List<Map<String, Object>> dataList = dataManageDao.getDataListNoPage(tableName);
			WritableWorkbook book = Workbook.createWorkbook(rootFile);
			WritableSheet sheet = book.createSheet("Sheet_1", 0);
			WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
			WritableCellFormat headFormat = new WritableCellFormat(headFont);
			headFormat.setAlignment(Alignment.CENTRE);
			headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			headFormat.setBackground(jxl.format.Colour.YELLOW);
			
			WritableCellFormat formatTable = new WritableCellFormat();
			formatTable.setAlignment(Alignment.CENTRE);
			formatTable.setVerticalAlignment(VerticalAlignment.CENTRE);

			sheet.setRowView(0, 400);// 设置第一行行高
			Label label;
			for (int i = 0; i < tableColumnList.size(); i++) {
				String comments =
					(StringUtils.isNotEmpty(tableColumnList.get(i).getComments()) && StringUtils
						.isNotEmpty(tableColumnList.get(i).getComments().trim())) ? "("
						+ tableColumnList.get(i).getComments() + ")" : "";
				label = new Label(i, 0, tableColumnList.get(i).getColumnName() + comments, headFormat);
				sheet.addCell(label);
				sheet.setColumnView(i, 30);
			}
			if ("Y".equals(type)) {
				for (int i = 0; i < dataList.size(); i++) {
					for (int j = 0; j < tableColumnList.size(); j++) {
						String value =
							dataList.get(i).get(tableColumnList.get(j).getColumnName()) == null ? "" : dataList.get(i)
								.get(tableColumnList.get(j).getColumnName()).toString();
						if ("DATE".equals(tableColumnList.get(j).getDataType())
							&& dataList.get(i).get(tableColumnList.get(j).getColumnName()) != null) {
							DateTime dt =
								new DateTime(j, i + 1, (Date) dataList.get(i).get(
									tableColumnList.get(j).getColumnName()), new WritableCellFormat(
									new jxl.write.DateFormat("yyyy/MM/dd HH:mm:ss")));
							sheet.addCell(dt);
						} else {
							label = new Label(j, i + 1, value, formatTable);
							sheet.addCell(label);
						}
					}
				}
			}
			book.write();
			book.close();
			return rootFile;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public StringResult importData(String tableName, File file, String userId) {
		StringResult result = new StringResult();
		try {
			StringBuilder headResult = new StringBuilder();
			StringBuilder contentResult = new StringBuilder();
			InputStream inputStream = new FileInputStream(file);
			Workbook workbook = Workbook.getWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(0);
			int columns = sheet.getColumns();
			int rows = sheet.getRows();
			if (rows <= 1) {
				result.setCode(IDataManageService.ERROR);
				result.setResult("数据不能为空");
				return result;
			}
			List<TableColumn> tableColumnList = dataManageDao.getTableColumns(tableName);
			if (tableColumnList.size() != columns) {
				result.setCode(IDataManageService.ERROR);
				result.setResult("导入文件列数与模板不一致!");
				return result;
			}
			String[] header = new String[tableColumnList.size()];
			for (int i = 0; i < tableColumnList.size(); i++) {
				String comments =
					(StringUtils.isNotEmpty(tableColumnList.get(i).getComments()) && StringUtils
						.isNotEmpty(tableColumnList.get(i).getComments().trim())) ? "("
						+ tableColumnList.get(i).getComments() + ")" : "";
				header[i] = tableColumnList.get(i).getColumnName() + comments;
			}
			for (int i = 0; i < tableColumnList.size(); i++) {
				if (!header[i].equals(sheet.getCell(i, 0).getContents())) {
					if (headResult.length() > 0) {
						headResult.append("</br>");
					}
					headResult.append("第1行第").append(i + 1).append("列与模板中").append(header[i]).append("不一致");
				}
			}
			if (headResult.length() > 0) {
				result.setCode(IDataManageService.ERROR);
				result.setResult(headResult.toString());
				return result;
			}
			List<TableInfo> tableInfoList = new ArrayList<TableInfo>();
			boolean[] nullArr = new boolean[rows];
			for (int i = 1; i < rows; i++) {
				TableInfo tableInfo = new TableInfo();
				StringBuilder pk = new StringBuilder();
				StringBuilder rowIndex = new StringBuilder();
				tableInfo.setTableName(tableName);
				for (int j = 0; j < columns; j++) {
					Class[] c = { String.class };
					TableColumn tableColumn = tableColumnList.get(j);
					Method method1 = BeanUtils.findMethod(tableInfo.getClass(), "setParameter" + (j + 1), c);
					method1.invoke(tableInfo, new Object[] { tableColumn.getColumnName() });
					String value;
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
					if (sheet.getCell(j, i).getType() == CellType.DATE) {
						DateCell dc = (DateCell) sheet.getCell(j, i);
						value = df.format(convertDate4JXL(dc.getDate()));
					} else {
						value = sheet.getCell(j, i).getContents();
					}
					Method method2 = BeanUtils.findMethod(tableInfo.getClass(), "setValue" + (j + 1), c);
					if ("DATE".equals(tableColumn.getDataType())) {
						method2.invoke(tableInfo, new Object[] { (StringUtils.isNotEmpty(value) && StringUtils
							.isNotEmpty(value.trim())) ? "to_date('" + value + "', 'yyyy-MM-dd HH24:mi:ss')" : "''" });
					} else if ("NUMBER".equals(tableColumn.getDataType()) || "LONG".equals(tableColumn.getDataType())) {
						method2.invoke(tableInfo, new Object[] { (StringUtils.isNotEmpty(value) && StringUtils
							.isNotEmpty(value.trim())) ? value : "''" });
					} else {
						method2.invoke(tableInfo, new Object[] { (StringUtils.isNotEmpty(value) && StringUtils
							.isNotEmpty(value.trim())) ? "'" + value + "'" : "''" });
					}

					if (StringUtils.isEmpty(value) || StringUtils.isEmpty(value.trim())) {
						if ("N".equals(tableColumn.getNullable())) {
							if (contentResult.length() > 0) {
								contentResult.append("</br>");
							}
							contentResult.append("第").append(i).append("行").append("第").append(j + 1).append("列")
								.append("不能为空");
						}
						if ("P".equals(tableColumn.getPrimaryKey())) {
							nullArr[i] = true;// 当前行主键有为空的情况，不再去进行主键的唯一性校验
						}
					} else {
						if ("DATE".equals(tableColumn.getDataType())) {
							if (sheet.getCell(j, i).getType() != CellType.DATE) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("第").append(i).append("行").append("第").append(j + 1).append("列")
									.append("日期格式错误");
							}
						} else if ("NUMBER".equals(tableColumn.getDataType())) {
							if (!value.matches("^\\d+(.\\d+)?$")) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("第").append(i).append("行").append("第").append(j + 1).append("列")
									.append("必须是数字格式");
							}
							int length = tableColumn.getDataLength();
							if (value.length() > length) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("第").append(i).append("行").append("第").append(j + 1).append("列")
									.append("字段长度超过").append(length);
							}
						} else if ("VARCHAR2".equals(tableColumn.getDataType())) {
							int length = tableColumn.getDataLength();
							if (value.length() * 2 > length) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("第").append(i).append("行").append("第").append(j + 1).append("列")
									.append("字段长度超过").append(length);
							}
						}
					}

					if ("P".equals(tableColumn.getPrimaryKey()) && contentResult.length() == 0) {
						if (rowIndex.length() > 0) {
							rowIndex.append("、");
						}
						rowIndex.append(j + 1);
						if (pk.length() > 0) {
							pk.append(" and ");
						}
						if ("DATE".equals(tableColumn.getDataType())) {
							pk.append(tableColumn.getColumnName()).append(" = ").append("to_date('").append(value)
								.append("', 'yyyy-MM-dd HH24:mi:ss')");

						} else if ("NUMBER".equals(tableColumn.getDataType())
							|| "LONG".equals(tableColumn.getDataType())) {
							pk.append(tableColumn.getColumnName()).append(" = ").append(value);
						} else {
							pk.append(tableColumn.getColumnName()).append(" = '").append(value).append("'");
						}
					}
				}

				if (!nullArr[i] && pk.length() > 0) {
					int count = dataManageDao.getCountByPrimaryKey(tableName, pk.toString());
					if (count > 0) {
						if (contentResult.length() > 0) {
							contentResult.append("</br>");
						}
						contentResult.append("第").append(i).append("行").append("第").append(rowIndex).append("列")
							.append("主键冲突");
					}
				}
				tableInfoList.add(tableInfo);
			}
			if (contentResult.length() > 0) {
				result.setCode(IDataManageService.ERROR);
				result.setResult(contentResult.toString());
				return result;
			}
			dataManageDao.createDataInfo(tableInfoList);
			dataManageDao.createUploadLog(userId, tableName, tableInfoList.size());
			result.setCode(IDataManageService.SUCCESS);
			result.setResult("导入成功!");
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IDataManageService.ERROR);
			result.setResult("导入失败!");
		}
		return result;
	}

	public List<TableColumn> getTableColumns(String tableName) {
		try {
			return dataManageDao.getTableColumns(tableName);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public int getDataCount(String tableName) {
		try {
			return dataManageDao.getDataCount(tableName);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	public List<Map<String, Object>> getDataList(String tableName, int start, int end) {
		try {
			return dataManageDao.getDataList(tableName, start, end);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public Date convertDate4JXL(Date jxlDate) throws ParseException {
		if (jxlDate == null)
			return null;
		TimeZone gmt = TimeZone.getTimeZone("GMT");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		dateFormat.setTimeZone(gmt);
		String str = dateFormat.format(jxlDate);

		TimeZone local = TimeZone.getDefault();
		dateFormat.setTimeZone(local);
		return dateFormat.parse(str);
	}
	
	public StringResult deleteData(String tableName, String userId) {
		StringResult result = new StringResult();
		try {
			dataManageDao.deleteData(tableName);
			dataManageDao.createDeleteLog(tableName, userId);
			result.setCode(IDataManageService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IDataManageService.ERROR);
		}
		return result;
	}

	public IDataManageDao getDataManageDao() {
		return dataManageDao;
	}

	public void setDataManageDao(IDataManageDao dataManageDao) {
		this.dataManageDao = dataManageDao;
	}

	public String getExcelTemplateFilePath() {
		return excelTemplateFilePath;
	}

	public void setExcelTemplateFilePath(String excelTemplateFilePath) {
		this.excelTemplateFilePath = excelTemplateFilePath;
	}

}
