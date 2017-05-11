package com.kintiger.platform.framework.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

public class ExcelUtil {

	private static Logger logger = Logger.getLogger(ExcelUtil.class);

	/**
	 * createExcelWithTemplate
	 * 
	 * @param inputStream
	 * @param os
	 * @param props
	 * @param dataList
	 */
	public static void createExcelWithTemplate(InputStream inputStream,
			OutputStream os, List<String> props, List dataList) {
		Workbook tBook = null;
		WritableWorkbook wbook = null;
		// 需要导出的属性名getter
		List<String> getterList = new ArrayList<String>();

		try {
			tBook = Workbook.getWorkbook(inputStream);
			wbook = Workbook.createWorkbook(os, tBook);
			WritableSheet wsheet = wbook.getSheet(0);
			if (props != null) {
				for (String prop : props) {
					getterList.add("get" + prop.substring(0, 1).toUpperCase()
							+ prop.substring(1));
				}
			}
			int row = 1;
			int size = getterList.size();
			if (dataList != null) {
				for (Object obj : dataList) {
					int col = 0;
					Class clazz = obj.getClass();
					for (int i = 0; i < size; i++) {
						Method method = BeanUtils.findMethod(clazz,
								getterList.get(i), new Class[0]);
						Object valObj = method.invoke(obj, new Object[0]);
						addCell(wsheet, col++, row, valObj);
					}
					row++;
				}
			}
			// 输出内容
			wbook.write();
		} catch (Exception e) {
			logger.error(
					LogUtil.parserBean(props) + LogUtil.parserBean(dataList), e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
					logger.error(e);
				}
			}
			if (tBook != null) {
				try {
					tBook.close();
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
	}
	
	/**
	 新 导出
	 */
	
	 public static void createExcelWithBookMap(WritableWorkbook wbook,
			List<String> props, List<Map<String, Object>> resultData){
		 Workbook tBook = null;
		 try {
				WritableSheet wsheet = wbook.getSheet(0);
				int row = 1;
				int size = props.size();
				if (resultData != null) {
					for (Map<String, Object> obj : resultData) {
						int col = 0;
						for (int i = 0; i < size; i++) {
							Object valObj = obj.get(props.get(i).toUpperCase());
							addCell(wsheet, col++, row, valObj);
						}
						row++;
					}
				}
				// 输出内容
				wbook.write();
			} catch (Exception e) {
				logger.error(
						LogUtil.parserBean(props) + LogUtil.parserBean(resultData), e);
			} finally {
				if (wbook != null) {
					try {
						wbook.close();
					} catch (Exception e) {
						logger.error(e);
					}
				}
				if (tBook != null) {
					try {
						tBook.close();
					} catch (Exception e) {
						logger.error(e);
					}
				}
			}
	 }

	/**
	 * createExcelWithBook
	 * 
	 * @param wbook
	 * @param props
	 * @param dataList
	 * @author xxping
	 */
	public static void createExcelWithBook(WritableWorkbook wbook,
			List<String> props, List dataList) {
		Workbook tBook = null;
		// 需要导出的属性名getter
		List<String> getterList = new ArrayList<String>();
		try {
			WritableSheet wsheet = wbook.getSheet(0);
			if (props != null) {
				for (String prop : props) {
					getterList.add("get" + prop.substring(0, 1).toUpperCase()
							+ prop.substring(1));
				}
			}
			int row = 1;
			int size = getterList.size();
			if (dataList != null) {
				for (Object obj : dataList) {
					int col = 0;
					Class clazz = obj.getClass();
					for (int i = 0; i < size; i++) {
						Method method = BeanUtils.findMethod(clazz,
								getterList.get(i), new Class[0]);
						Object valObj = method.invoke(obj, new Object[0]);
						addCell(wsheet, col++, row, valObj);
					}
					row++;
				}
			}
			// 输出内容
			wbook.write();
		} catch (Exception e) {
			logger.error(
					LogUtil.parserBean(props) + LogUtil.parserBean(dataList), e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
					logger.error(e);
				}
			}
			if (tBook != null) {
				try {
					tBook.close();
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
	}

	/**
	 * 添加单元格&内容.
	 * 
	 * @param ws
	 * @param cel
	 * @param row
	 * @param valObject
	 */
	private static void addCell(WritableSheet ws, int cel, int row,
			Object valObj) throws RowsExceededException, WriteException {

		if (valObj == null) {
			Label cr = new Label(cel, row, "");
			ws.addCell(cr);
			return;
		}

		Class clazz2 = valObj.getClass();
		if (clazz2 == String.class) {
			Label cr = new Label(cel, row, (String) valObj);
			ws.addCell(cr);
			return;
		} else if (clazz2 == int.class || clazz2 == Integer.class) {
			Number cr = new Number(cel, row,
					Double.parseDouble(((Integer) valObj).toString()));
			ws.addCell(cr);
			return;
		} else if (clazz2 == long.class || clazz2 == Long.class) {
			Number cr = new Number(cel, row, Double.parseDouble(((Long) valObj)
					.toString()));
			ws.addCell(cr);
			return;
		} else if (clazz2 == float.class || clazz2 == Float.class) {
			Number cr = new Number(cel, row,
					Double.parseDouble(((Float) valObj).toString()));
			ws.addCell(cr);
			return;
		} else if (clazz2 == double.class || clazz2 == Double.class) {
			Number cr = new Number(cel, row,
					Double.parseDouble(((Double) valObj).toString()));
			ws.addCell(cr);
			return;
		} else if (clazz2 == Date.class) {
			DateTime cr = new DateTime(cel, row, (Date) valObj);
			ws.addCell(cr);
			return;
		} else if (clazz2 == boolean.class || clazz2 == Boolean.class) {
			jxl.write.Boolean cr = new jxl.write.Boolean(cel, row,
					(Boolean) valObj);
			ws.addCell(cr);
			return;
		} else if (clazz2 == BigDecimal.class) {
			NumberFormat nf = new NumberFormat("0.00");
			WritableCellFormat wcf = new WritableCellFormat(nf);
			wcf.setFont(new WritableFont(WritableFont.createFont("宋体"), 9));
			Number cr = new Number(cel, row,
					((BigDecimal) valObj).doubleValue(), wcf);
			ws.addCell(cr);
			return;
		} else if (clazz2 == Short.class) {
			Number cr = new Number(cel, row,
					Double.parseDouble(((Short) valObj).toString()));
			ws.addCell(cr);
			return;
		}
	}

}