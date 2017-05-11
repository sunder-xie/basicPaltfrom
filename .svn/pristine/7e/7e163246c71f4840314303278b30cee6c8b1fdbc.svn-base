package com.kintiger.platform.framework.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ognl.OgnlOps;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;

import com.kintiger.platform.wfe.pojo.OaXmlBean;
import com.opensymphony.xwork2.util.OgnlUtil;

public class XmlUtil {

	private static final Logger logger = Logger.getLogger(XmlUtil.class);

	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 通过DOCUMENT得到一个ELement集合
	 * 
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getElementsFromDocument(Document document,
			String xpath) throws DocumentException {

		return document.selectNodes(xpath);
	}

	/**
	 * 从一个字符串得到一个DOCUMENT
	 * 
	 * @param inputStr
	 * @return
	 * @throws DocumentException
	 */
	public static Document getDocumentByStr(String inputStr)
			throws DocumentException {
		return DocumentHelper.parseText(inputStr);
	}

	public static Document getDocumentByFile(String filename)
			throws DocumentException, FileNotFoundException,
			UnsupportedEncodingException {
		SAXReader reader = new SAXReader();
		FileInputStream inputStream = new FileInputStream(new File(filename));
		InputStreamReader in = new InputStreamReader(inputStream, "gbk");
		Document document = reader.read(in);
		return document;
	}

	/**
	 * 通过DOCUMENT得到单个ELEMENT
	 * 
	 * @param document
	 * @param xpath
	 * @return
	 * @throws DocumentException
	 */
	public static Element getElementFromDocument(Document document, String xpath)
			throws DocumentException {
		if (getElementsFromDocument(document, xpath) == null)
			return null;
		return getElementsFromDocument(document, xpath).size() > 0 ? getElementsFromDocument(
				document, xpath).get(0)
				: null;
	}

	/**
	 * 根据ELEMENT得到一个属性
	 * 
	 * @param atrName
	 *            属性名
	 * @param element
	 *            传入的ELEMENT
	 * @return
	 */
	public static Attribute getAttributeByElement(String atrName,
			Element element) {
		if (element == null)
			return null;
		return element.attribute(atrName);
	}

	/**
	 * 根据ELEMENT得到属性它的值
	 * 
	 * @param atrName
	 * @param element
	 * @return
	 */
	public static String getAttributeTextByElement(String atrName,
			Element element) {
		if (element == null)
			return null;
		return element.attribute(atrName).getText();
	}

	/**
	 * 读取xml
	 * 
	 * @param path
	 * @param oaXmlBean
	 * @param type
	 *            xml中root节点某一项属性
	 * @return
	 */
	public static List<OaXmlBean> readXml(String path, OaXmlBean oaXmlBean,
			String type) {
		List<OaXmlBean> list = new ArrayList<OaXmlBean>();
		OaXmlBean oab;
		try {
			// xml文件
			File f = new File(path);
			// 构造dom
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 解析文件
			org.w3c.dom.Document doc = builder.parse(f);
			if (StringUtils.isNotEmpty(type)) {
				org.w3c.dom.Element element = doc.getDocumentElement();
				String str = element.getAttribute(type);
				oab = new OaXmlBean();
				oab.setParameter(str);
				list.add(oab);
			} else {
				// 直接读取里面节点为VALUE的
				NodeList nl = doc
						.getElementsByTagName(oaXmlBean.getParameter());
				for (int i = 0; i < nl.getLength(); i++) {
					oab = new OaXmlBean();
					String value = null;
					if ((value = getFirstChildValue(doc,
							oaXmlBean.getParameter1(), i)) != null) {
						oab.setParameter1(value);
					}
					if ((value = getFirstChildValue(doc,
							oaXmlBean.getParameter2(), i)) != null) {
						oab.setParameter2(value);
					}
					if ((value = getFirstChildValue(doc,
							oaXmlBean.getParameter3(), i)) != null) {
						oab.setParameter3(value);
					}
					list.add(oab);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	private static String getFirstChildValue(org.w3c.dom.Document doc,
			String tagName, int index) {
		try {
			return doc.getElementsByTagName(tagName).item(index)
					.getFirstChild().getNodeValue();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将xml字符串转成javabean
	 * 
	 * @param xmlValue
	 *            要转换的xml字符串
	 * @param toClass
	 *            要转化到的类型
	 * @param childMap
	 *            该类型中集合字段的字段名和集合元素类型map
	 * @return Object
	 */
	public static Object xml2Bean(Class toClass, String xmlValue,
			Map<String, Class> childMap) {

		Map<String, Object> map = toMap(xmlValue);
		try {
			Object obj = toClass.newInstance();
			PropertyDescriptor[] props = OgnlUtil.getPropertyDescriptors(obj);
			for (PropertyDescriptor desc : props) {
				if (map.containsKey(desc.getName())
						|| childMap.containsKey(desc.getName())) {

					Object value = null;
					if (java.util.Date.class.isAssignableFrom(desc
							.getPropertyType())) {
						try {
							String date = (String) map.get(desc.getName());
							if (date.contains(":")) {
								value = new SimpleDateFormat(DATE_PATTERN)
										.parse(date);
							} else {
								value = new SimpleDateFormat("yyyy-MM-dd")
										.parse(date);
							}
						} catch (ParseException e) {
							logger.error(e);
						}
					} else if (java.sql.Date.class.isAssignableFrom(desc
							.getPropertyType())) {
						try {
							String date = (String) map.get(desc.getName());
							if (date.contains(":")) {
								value = new java.sql.Date(new SimpleDateFormat(
										DATE_PATTERN).parse(date).getTime());
							} else {
								value = new java.sql.Date(new SimpleDateFormat(
										"yyyy-MM-dd").parse(date).getTime());
							}
						} catch (ParseException e) {
							logger.error(e);
						}
					} else if (Collection.class.isAssignableFrom(desc
							.getPropertyType())) {
						Class clz = childMap.get(desc.getName());
						if (clz.isPrimitive()
								|| CharSequence.class.isAssignableFrom(clz)
								|| Number.class.isAssignableFrom(clz)) {
							value = convertFromStringListSimple(
									map.get("items"), clz);
						} else {
							value = convertFromStringListComplex(
									map.get("items"), clz);
						}
					} else {
						if (desc.getPropertyType().isPrimitive()
								|| CharSequence.class.isAssignableFrom(desc
										.getPropertyType())
								|| Number.class.isAssignableFrom(desc
										.getPropertyType())) {
							value = OgnlOps.convertValue(
									map.get(desc.getName()),
									desc.getPropertyType());
						} else {
							value = xml2Bean(desc.getPropertyType(),
									map.get(desc.getName()).toString(), null);
						}
					}

					desc.getWriteMethod().invoke(obj, new Object[] { value });
				}
			}
			return obj;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("xml xml2Bean()方法错误.", e);
			return null;
		}
	}

	private static Object convertFromStringListSimple(Object list, Class toClass) {
		if (toClass == null) {
			return null;
		}
		ArrayList<Map> param = (ArrayList<Map>) list;
		ArrayList<Object> result = new ArrayList<Object>();
		try {
			int i;
			Object value = null;
			for (i = 0; i < param.size(); i++) {
				Map property = param.get(i);
				if (java.util.Date.class.isAssignableFrom(toClass)) {
					try {
						value = new SimpleDateFormat(DATE_PATTERN)
								.parse((String) property.get("item"));
					} catch (ParseException e) {
						logger.error(e);
					}
				} else if (java.sql.Date.class.isAssignableFrom(toClass)) {
					try {
						value = new java.sql.Date(new SimpleDateFormat(
								DATE_PATTERN).parse(
								(String) property.get("item")).getTime());
					} catch (ParseException e) {
						logger.error(e);
					}
				} else {
					value = OgnlOps.convertValue(property.get("item"), toClass);
					result.add(value);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error("xml 简单对象数据转换错误", e);
		}
		return null;
	}

	private static Object convertFromStringListComplex(Object list,
			Class toClass) {
		if (toClass == null) {
			return null;
		}
		ArrayList<Map> param = (ArrayList<Map>) list;
		ArrayList<Object> result = new ArrayList<Object>();
		try {
			for (int i = 0; i < param.size(); i++) {
				Object obj = toClass.newInstance();
				Map map = param.get(i);
				PropertyDescriptor[] props = OgnlUtil
						.getPropertyDescriptors(obj);
				for (PropertyDescriptor desc : props) {
					if (map.containsKey(desc.getName())) {
						Object value = null;
						// 处理日期型
						// "yyyy-MM-dd'T'HH:mm:ss'Z'"此格式是由json2.js转换后的Date格式
						if (java.util.Date.class.isAssignableFrom(desc
								.getPropertyType())) {
							try {
								value = new SimpleDateFormat(DATE_PATTERN)
										.parse((String) map.get(desc.getName()));
							} catch (ParseException e) {
								logger.error(e);
							}
						} else if (java.sql.Date.class.isAssignableFrom(desc
								.getPropertyType())) {
							try {
								value = new java.sql.Date(new SimpleDateFormat(
										DATE_PATTERN).parse(
										(String) map.get(desc.getName()))
										.getTime());
							} catch (ParseException e) {
								logger.error(e);
							}
						} else {
							value = OgnlOps.convertValue(
									map.get(desc.getName()),
									desc.getPropertyType());

						}
						desc.getWriteMethod().invoke(obj,
								new Object[] { value });
					}
				}
				result.add(obj);
			}
		} catch (Exception e) {
			logger.error("xml 复杂对象数据转换错误.", e);
		}
		return result;
	}

	public static Map<String, Object> toMap(String xmlValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Document doc = DocumentHelper.parseText(xmlValue);
			Element root = doc.getRootElement();
			Iterator i = root.elementIterator();
			ArrayList<Map> items = new ArrayList<Map>();
			while (i.hasNext()) {
				Element e = (Element) i.next();
				if (e.nodeCount() != 0) {
					if (e.getName().equalsIgnoreCase("items")) {
						Iterator j = e.elementIterator();
						while (j.hasNext()) {
							Map<String, Object> item = new HashMap<String, Object>();
							Element ie = (Element) j.next();							
							Iterator k = ie.elementIterator();
							// 简单类型数组
							if (!k.hasNext()) {
								item.put(ie.getName(), ie.getTextTrim());
							}
							// 复杂对象类型数组
							while (k.hasNext()) {
								Element pro = (Element) k.next();
								if (!pro.getTextTrim().equals("")) {
									item.put(pro.getName(), pro.getTextTrim());
								}
							}
							if (!item.isEmpty()) {
								items.add(item);
							}
						}
						if (items.size() > 0) {
							map.put("items", items);
						}
					} else {
						map.put(e.getName(), e.getTextTrim());
					}
				}
			}
		} catch (DocumentException e) {
			logger.error("xml toMap方法错误.", e);
			return null;
		}
		return map;
	}
	
	
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><expTravel>"
			+ "<property>SDF</property>"
			+ "<user_id>34567</user_id>"
			+ "<items>"
			+ "<将>"
			+ "<A>Hello</A><B>World</B>"
			+ "</将><帅>F</帅><兵>X</兵>"
			+ "</items>"
			+ "</expTravel>";
		Map<String, Object> m = XmlUtil.toMap(xml);
		Iterator it = m.keySet().iterator();
		
		System.out.println(m);
		
		while(it.hasNext()){
			String key = (String) it.next();
			System.out.println(key + " = " + m.get(key));
		}
	}
}

