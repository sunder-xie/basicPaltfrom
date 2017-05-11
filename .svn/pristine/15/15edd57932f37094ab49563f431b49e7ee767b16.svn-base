package com.kintiger.platform.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class SuperCSV {

	/**
	 * 读取csv文件(不带头部)
	 * 
	 * @param file
	 *            File
	 * @return csv文件组装成list
	 * @throws IOException
	 */
	public static List<String[]> getContentFromFile(File file)
			throws IOException {
		List<String[]> content = new ArrayList<String[]>();
		/*
		 * CsvListReader reader = new CsvListReader(new FileReader(file),
		 * CsvPreference.EXCEL_PREFERENCE);
		 */
		InputStream in = new FileInputStream(file);
		CsvListReader reader = new CsvListReader(new InputStreamReader(in,
				"GBK"), CsvPreference.EXCEL_PREFERENCE);
		reader.getHeader(true);// 去除头部字段声明
		List<String> line = new ArrayList<String>();
		while ((line = reader.read()) != null) {
			content.add(line.toArray(new String[] {}));
		}
		return content;
	}

	/**
	 * 读取csv文件(带头部)
	 * 
	 * @param file
	 *            File
	 * @return csv文件组装成list
	 * @throws IOException
	 */
	public static List<String[]> getDetailFromFile(File file)
			throws IOException {
		List<String[]> content = new ArrayList<String[]>();
		;
		/*
		 * CsvListReader reader = new CsvListReader(new FileReader
		 * 
		 * (file), CsvPreference.EXCEL_PREFERENCE);
		 */
		InputStream in = new FileInputStream(file);
		CsvListReader reader = new CsvListReader(new InputStreamReader(in,
				"GBK"), CsvPreference.EXCEL_PREFERENCE);
		String[] header = reader.getHeader(true);
		content.add(header);
		List<String> line = new ArrayList<String>();
		while ((line = reader.read()) != null) {
			content.add(line.toArray(new String[] {}));
		}
		return content;
	}

	/**
	 * 读取csv文件的头部
	 * 
	 * @param file
	 *            File
	 * @return csv文件的头部
	 * @throws IOException
	 */
	public static String[] getHeaderFromFile(File file) throws IOException {
		/*
		 * CsvListReader reader = new CsvListReader(new FileReader
		 * 
		 * (file), CsvPreference.EXCEL_PREFERENCE);
		 */
		InputStream in = new FileInputStream(file);
		CsvListReader reader = new CsvListReader(new InputStreamReader(in,
				"GBK"), CsvPreference.EXCEL_PREFERENCE);
		return reader.getHeader(true);
	}

	/**
	 * 写入csv文件
	 * 
	 * @param file
	 *            File
	 * @param header
	 *            头部
	 * @param content
	 *            内容
	 * @throws IOException
	 */
	public static void writeToCsv(File file, String[] header,
			List<String[]> content) throws

	IOException {
		/*
		 * CsvListWriter writer = new CsvListWriter(new FileWriter(file),
		 * CsvPreference.EXCEL_PREFERENCE);
		 */
		OutputStream out = new FileOutputStream(file);
		CsvListWriter writer = new CsvListWriter(new OutputStreamWriter(out,
				"GBK"), CsvPreference.EXCEL_PREFERENCE);
		writer.writeHeader(header);
		for (String[] str : content) {
			writer.write(str);
		}
		writer.close();
	}

	/**
	 * 写入csv文件
	 * 
	 * @param file
	 *            File
	 * @param content
	 *            内容
	 * @throws IOException
	 */
	public static void writeContentToCsv(File file, List<String[]> content)
			throws IOException {
		/*
		 * CsvListWriter writer = new CsvListWriter(new FileWriter(file),
		 * 
		 * CsvPreference.EXCEL_PREFERENCE);
		 */

		OutputStream out = new FileOutputStream(file);
		CsvListWriter writer = new CsvListWriter(new OutputStreamWriter(out,
				"GBK"), CsvPreference.EXCEL_PREFERENCE);
		for (String[] str : content) {
			writer.write(str);
		}
		writer.close();
	}

	/**
	 * 写入csv文件(头部)
	 * 
	 * @param file
	 *            File
	 * @param content
	 *            内容
	 * @throws IOException
	 */
	public static void writeHeaderToCsv(File file, String[] header)
			throws IOException {
		/*
		 * CsvListWriter writer = new CsvListWriter(new FileWriter(file),
		 * 
		 * CsvPreference.EXCEL_PREFERENCE);
		 */
		OutputStream out = new FileOutputStream(file);
		CsvListWriter writer = new CsvListWriter(new OutputStreamWriter(out,
				"GBK"), CsvPreference.EXCEL_PREFERENCE);
		writer.writeHeader(header);
		writer.close();
	}
}
