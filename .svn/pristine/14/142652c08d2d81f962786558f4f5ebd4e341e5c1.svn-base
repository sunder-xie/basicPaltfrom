package com.kintiger.platform.framework.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class JavaBeanXMLUtil {

	private static final Logger logger = Logger.getLogger(JavaBeanXMLUtil.class);

	private static final String CREATOR_ID = "CI";

	private static final String CREATOR_NAME = "CN";

	private static final String MODIFY_DATE = "M";

	private static final String LINE_NUM = "L";

	/**
	 * JavaBean2XML
	 * 
	 * @param xmlFilePath
	 * @param object
	 * @param creatorId
	 * @param creatorName
	 * @return
	 */
	public static boolean JavaBean2XML(String xmlFilePath, Object object, String creatorId, String creatorName,
		String xmlfile) {
		return JavaBean2XML(xmlFilePath, object, null, creatorId, creatorName, DateUtil.getNowDatetimeStr(), null,
			xmlfile);
	}

	/**
	 * JavaBean2XML
	 * 
	 * @param xmlFilePath
	 * @param object
	 * @param histories
	 * @param creatorId
	 * @param creatorName
	 * @param modifyDate
	 * @param lineNum
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean JavaBean2XML(String xmlFilePath, Object object, List<String> histories, String creatorId,
		String creatorName, String modifyDate, String lineNum, String xmlfile) {
		Document document = null;
		Element root = null;
		//
		if (xmlfile == null || "null".equals(xmlfile) || "".equals(xmlfile)) {
			String subFolders = DateUtil.datetime("yyyyMM");
			StringBuffer sb = new StringBuffer(xmlFilePath);
			sb.insert(xmlFilePath.lastIndexOf('/'), "/" + subFolders);
			xmlFilePath = sb.toString();
			StringBuffer ss = new StringBuffer(xmlFilePath);
			ss.delete(xmlFilePath.lastIndexOf('/'), xmlFilePath.lastIndexOf("l") + 1);
			File savedir = new File(ss.toString());
			// 如果目录不存在，则新建
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
		} else {
			StringBuffer sb = new StringBuffer(xmlFilePath);
			sb.insert(xmlFilePath.lastIndexOf('/'), "/" + xmlfile);
			xmlFilePath = sb.toString();

		}
		//

		document = DocumentHelper.createDocument();
		root = document.addElement("root");
		String clazz = object.getClass().toString();
		Element element = root.addElement(clazz.substring(clazz.lastIndexOf(".") + 1));
		element.setAttributeValue(CREATOR_ID, creatorId);
		element.setAttributeValue(CREATOR_NAME, creatorName);
		element.setAttributeValue(MODIFY_DATE, modifyDate);
		element.setAttributeValue(LINE_NUM, lineNum);

		if (JavaBean2XML(object, element)) {
			if (histories != null) {
				Element historiesElement = root.addElement("histories");
				for (String str : histories) {
					historiesElement.addElement("id").setText(str);
				}
			}

			return writeDocument(document, xmlFilePath);
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * 
	 * @param object
	 * @param element
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static boolean JavaBean2XML(Object object, Element element) {

		for (int i = 0; i < object.getClass().getMethods().length; i++) {
			String methodName = object.getClass().getMethods()[i].getName();
			if (methodName.indexOf("get") == 0) {
				try {
					Object obj = object.getClass().getMethods()[i].invoke(object);
					methodName = methodName.substring(methodName.indexOf("get") + 3);

					if (obj == null) {
					} else if (obj instanceof String || (obj instanceof StringBuffer)) {

						element.addElement(methodName).setText(obj.toString());

					} else if (obj instanceof Object[]) {

					} else if ((obj instanceof Number) || (obj instanceof Boolean)) {

						element.addElement(methodName).setText(obj.toString());

					} else if (obj instanceof Collection) {

						Collection col = (Collection) obj;
						Iterator iter = col.iterator();

						Element initialNode = element.addElement(methodName);

						while (iter.hasNext()) {
							Element node = initialNode.addElement(methodName.substring(0, methodName.length() - 3 - 1));
							Object obj0 = iter.next();
							JavaBean2XML(obj0, node);
						}

					} else if (obj instanceof Map) {

					} else if (obj instanceof Date) {
						element.addElement(methodName).setText(
							DateUtil.getDateTime((Date) obj, DateUtil.DEFAULT_DATETIME_FORMAT));
					} else if (obj instanceof Exception) {

					} else if (obj instanceof Calendar) {

					} else {

					}

				} catch (IllegalArgumentException e) {
					logger.error(LogUtil.parserBean(object), e);
					return Boolean.FALSE;
				} catch (SecurityException e) {
					logger.error(LogUtil.parserBean(object), e);
					return Boolean.FALSE;
				} catch (IllegalAccessException e) {
					logger.error(LogUtil.parserBean(object), e);
					return Boolean.FALSE;
				} catch (InvocationTargetException e) {
					logger.error(LogUtil.parserBean(object), e);
					return Boolean.FALSE;
				}
			}
		}
		return Boolean.TRUE;
	}

	/**
	 * 
	 * @param object
	 *            JavaBean
	 * @param xmlFilePath
	 *            XML名字
	 * @return error错误情况
	 * @return XMLInfo正确情况
	 */
	@SuppressWarnings("unchecked")
	public static XMLInfo XML2JavaBean(String xmlFilePath, Object object) {
		XMLInfo XMLinfo = new XMLInfo();

		String clazz = object.getClass().toString();
		clazz = clazz.substring(clazz.lastIndexOf(".") + 1);

		// 读XML节点
		Document document = null;
		// String pathFile =
		// ServletActionContext.getServletContext().getRealPath(xmlFilePath);
		try {
			document = XmlUtil.getDocumentByFile(xmlFilePath);
		} catch (FileNotFoundException e) {
			logger.error(LogUtil.parserBean(xmlFilePath), e);
			return null;
		} catch (DocumentException e) {
			logger.error(LogUtil.parserBean(object), e);
			return null;
		} catch (UnsupportedEncodingException e) {
			logger.error(LogUtil.parserBean(object), e);
			return null;
		}
		List<Element> clazzList = document.selectNodes("/root/" + clazz);
		Element element = null;
		try {
			if (clazzList.size() > 0) {
				element = clazzList.get(0);
				if (element.attribute(LINE_NUM) != null) {
					XMLinfo.setLineNumber(element.attribute(LINE_NUM).getText());
				}
				if (element.attribute(MODIFY_DATE) != null) {
					XMLinfo.setTime(element.attribute(MODIFY_DATE).getText());
				}
				if (element.attribute(CREATOR_ID) != null) {
					XMLinfo.setCreatorId(element.attribute(CREATOR_ID).getText());
				}
				if (element.attribute(CREATOR_NAME) != null) {
					XMLinfo.setCreatorName(element.attribute(CREATOR_NAME).getText());
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

		if (Boolean.TRUE.equals(XML2JavaBean(object, element))) {
			XMLinfo.setObject(object);
		} else {
			return null;
		}

		List<Element> historyList = document.selectNodes("/root/histories/id");
		List<String> historysStrList = new ArrayList<String>();
		for (Element ele : historyList) {
			historysStrList.add(ele.getText());
		}
		XMLinfo.setModifyRecordList(historysStrList);
		return XMLinfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean XML2JavaBean(Object object, Element element) {
		List<Element> clazzList = element.elements();

		// object的所有属性
		Field[] fields = object.getClass().getDeclaredFields();

		for (Field field : fields) {
			String fieldName = field.getName();
			String xmlFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

			if ("SerialVersionUID".equals(xmlFieldName)) {
				continue;
			}

			String methodName = "set" + xmlFieldName;
			// type = field.getType().toString();
			if (List.class.equals(field.getType())) {
				// 赋值List
				List objList = new ArrayList();
				List<Element> listTemp = element.selectNodes(xmlFieldName);

				if (listTemp == null || listTemp.size() == 0) {
					continue;
				}

				List<Element> detailList = listTemp.get(0).elements();

				for (int j = 0; j < detailList.size(); j++) {
					try {
						Type typ = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
						// 加载类到内存中！获取一个Class对象
						Class cls = Class.forName(typ.toString().substring(6));
						Object obj = cls.newInstance();
						Boolean flag = XML2JavaBean(obj, detailList.get(j));
						if (flag.equals(Boolean.TRUE)) {
							objList.add(obj);
						} else {
							return Boolean.FALSE;
						}
					} catch (ClassNotFoundException e) {
						logger.error(e);
					} catch (InstantiationException e) {
						logger.error(e);
					} catch (IllegalAccessException e) {
						logger.error(e);
					} catch (SecurityException e) {
						logger.error(e);
					} catch (IllegalArgumentException e) {
						logger.error(e);
					}
				}

				try {
					Method method = object.getClass().getDeclaredMethod(methodName, field.getType());
					method.invoke(object, new Object[] { objList });
				} catch (SecurityException e) {
					logger.error(e);
				} catch (NoSuchMethodException e) {
					logger.error(e);
				} catch (IllegalArgumentException e) {
					logger.error(e);
				} catch (IllegalAccessException e) {
					logger.error(e);
				} catch (InvocationTargetException e) {
					logger.error(e);
				}
			} else {
				try {
					Method method = object.getClass().getDeclaredMethod(methodName, field.getType());
					String str = null;
					for (int j = 0; j < clazzList.size(); j++) {
						if (clazzList.get(j).getName().equals(xmlFieldName)) {
							str = clazzList.get(j).getText();
							if ((String.class).equals(field.getType())) {
								method.invoke(object, new Object[] { str });
							} else if ((Integer.class).equals(field.getType())) {
								method.invoke(object, new Object[] { Integer.valueOf(str) });
							} else if ((Float.class).equals(field.getType())) {
								method.invoke(object, new Object[] { Float.valueOf(str) });
							} else if ((Long.class).equals(field.getType())) {
								method.invoke(object, new Object[] { Long.valueOf(str) });
							} else if ((Double.class).equals(field.getType())) {
								method.invoke(object, new Object[] { Double.valueOf(str) });
							} else if ((double.class).equals(field.getType())) {
								method.invoke(object, new Object[] { Double.valueOf(str) });
							} else if ((BigDecimal.class).equals(field.getType())) {
								method.invoke(object, new Object[] { BigDecimal.valueOf(Double.valueOf(str)) });
							} else if ((Date.class).equals(field.getType())) {
								method.invoke(object, new Object[] { DateUtil.getDateFromStr(str) });
							}
							break;
						}
					}
				} catch (SecurityException e) {
					logger.error(e);
				} catch (NoSuchMethodException e) {
					logger.error(e);
				} catch (IllegalArgumentException e) {
					logger.error(e);
				} catch (IllegalAccessException e) {
					logger.error(e);
				} catch (InvocationTargetException e) {
					logger.error(e);
				}
			}
		}

		return Boolean.TRUE;
	}

	/**
	 * 写入xml文件地址
	 * 
	 * @param document
	 *            所属要写入的内容
	 * @param xmlFilePath
	 *            文件存放的地址
	 */
	private static boolean writeDocument(Document document, String xmlFilePath) {
		// 读取文件
		// FileWriter fileWriter = null;

		FileOutputStream fos = null;

		// 设置文件编码
		OutputFormat xmlFormat = null;

		// 创建写文件方法
		XMLWriter xmlWriter = null;
		try {
			// String pathFile =
			// ServletActionContext.getServletContext().getRealPath(xmlFilePath);
			// fileWriter = new FileWriter(xmlFilePath);
			fos = new FileOutputStream(xmlFilePath);
			xmlFormat = new OutputFormat();
			xmlFormat.setEncoding("gb2312");
			xmlWriter = new XMLWriter(fos, xmlFormat);

			// 写入文件
			xmlWriter.write(document);
			// 关闭
			xmlWriter.close();
		} catch (IOException e) {
			logger.error(xmlFilePath, e);
			return Boolean.FALSE;
		} finally {
			xmlWriter = null;
			xmlFormat = null;
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error(e);
				}
				fos = null;
			}

		}
		return Boolean.TRUE;
	}

}