package com.kintiger.platform.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author xujiakun
 * 
 */
public class LogUtil {

	private static final Logger logger = Logger.getLogger(LogUtil.class);

	private LogUtil() {
	}

	@SuppressWarnings("unchecked")
	public static String parserBean(Object obj) {
		StringBuffer sb = new StringBuffer();
		try {
			if (obj == null)
				return "null";
			else if (obj instanceof String || (obj instanceof StringBuffer)) {
				obj = obj.toString();
				String v = ((String) obj).replaceAll("/\\{0}\"{1}/", "\\\"");
				v = v.replaceAll("/\r/", "\\n");
				sb.append('"').append("params:").append('"').append(obj)
						.append('"');
			} else if (obj instanceof Object[]) {
				Object[] os = (Object[]) obj;
				sb.append("[");
				for (int i = 0; i < os.length; i++) {
					Object obj0 = os[i];
					sb.append(parserBean(obj0)).append(',');
				}
				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("]");
			} else if ((obj instanceof Number) || (obj instanceof Boolean)) {
				sb.append(obj.toString());
			} else if (obj instanceof Collection) {
				Collection col = (Collection) obj;
				Iterator iter = col.iterator();
				sb.append("[");
				while (iter.hasNext()) {
					Object obj0 = iter.next();
					sb.append(parserBean(obj0)).append(',').append("\r\n");
				}
				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("]");
			} else if (obj instanceof Map) {
				sb.append('{');
				Map map = (Map) obj;
				Iterator keys = map.keySet().iterator();
				while (keys.hasNext()) {
					Object key = keys.next();
					Object v = map.get(key);
					sb.append('"').append(key.toString()).append("\":")
							.append(parserBean(v)).append(',');
				}
				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append('}');
			} else if (obj instanceof java.util.Date) {
				sb.append('"')
						.append(DateUtil.datetime((Date) obj,
								DateUtil.DEFAULT_DATETIME_FORMAT)).append('"');
			} else if (obj instanceof Exception) {
				Exception e = (Exception) obj;
				StringBuffer t = new StringBuffer();
				t.append(e.getClass().getName()).append(':')
						.append(e.getMessage());
				sb.append(parserBean(t));
			} else if (obj instanceof Calendar) {
				Calendar c = (Calendar) obj;
				sb.append('"')
						.append(DateUtil.datetime((Date) c.getTime(),
								DateUtil.DEFAULT_DATETIME_FORMAT)).append('"');
			} else {
				sb.append(obj.getClass().getName() + ":");
				sb.append("{");
				Map<String, Object> map = PropertyUtils.describe(obj);
				for (Iterator<String> iter = map.keySet().iterator(); iter
						.hasNext();) {
					String element = iter.next();
					Object value = map.get(element);
					if (value == null) {
						sb.append("\"" + element + "\":").append('"')
								.append("null").append('"').append(',');
					} else if (value instanceof String
							|| (value instanceof StringBuffer)) {
						sb.append("\"" + element + "\":");
						value = value.toString();
						String v = ((String) value).replaceAll("/\\{0}\"{1}/",
								"\\\"");
						v = v.replaceAll("/\r/", "\\n");
						sb.append('"').append(v).append('"').append(',');
					} else if (value instanceof java.util.Date) {
						sb.append("\"" + element + "\":");
						sb.append('"')
								.append(DateUtil.datetime((Date) value,
										DateUtil.DEFAULT_DATETIME_FORMAT))
								.append('"').append(',');

					} else if (value instanceof Calendar) {
						sb.append("\"" + element + "\":");
						Calendar c = (Calendar) value;
						sb.append('"')
								.append(DateUtil.datetime((Date) c.getTime(),
										DateUtil.DEFAULT_DATETIME_FORMAT))
								.append('"').append(',');
					} else if (value instanceof java.lang.Number
							|| (value instanceof Boolean)) {
						sb.append("\"" + element + "\":")
								.append(":\"" + value + "\"").append(',');
					}
				}
				if (sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append('}');
			}
		} catch (IllegalAccessException e) {
			logger.error("", e);
		} catch (InvocationTargetException e) {
			logger.error("", e);
		} catch (NoSuchMethodException e) {
			logger.error("", e);
		} catch (Exception e) {
			logger.error("", e);
		}
		return sb.toString();
	}

}
