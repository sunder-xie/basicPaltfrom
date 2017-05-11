package com.kintiger.platform.framework.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * Properties 工具类
 * 
 */
public class PropertiesUtil {

	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入. 文件路径使用Spring Resource格式,
	 * 文件编码使用UTF-8.
	 * 
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 */
	public static Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();

		for (String location : resourcesPaths) {
			InputStream is = null;

			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is,
						DEFAULT_ENCODING));
			} catch (IOException e) {
				logger.error("Could not load properties from classpath:"
						+ location + ": " + e.getMessage(), e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						logger.error(e);
					}
				}
			}
		}

		return props;
	}

	// read
	public static String readValue(String filePath, String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			props.load(in);
			return props.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// write
	public static void writeProperties(String filePath, String parameterName,
			String parameterValue) {
		Properties props = new Properties();
		try {
			InputStream fis = new FileInputStream(filePath);
			props.load(fis);
			OutputStream fos = new FileOutputStream(filePath);
			props.setProperty(parameterName, parameterValue);
			props.store(fos, "Update '" + parameterName + "' value");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}