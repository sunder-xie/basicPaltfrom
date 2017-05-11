package com.kintiger.platform.base.pojo;

import java.io.Serializable;

/**
 * SearchInfo
 * 
 * 
 */
public class SearchInfo implements Serializable {

	private static final long serialVersionUID = 235499309845516885L;

	public static final String DIR_DESC = "DESC";

	public static final String DIR_ASC = "ASC";

	/**
	 * 编号数组
	 */
	private String[] codes;

	/**
	 * 搜索使用的搜索值，不分字段
	 */
	private String search;

	/**
	 * 排序字段
	 */
	private String sort;

	/**
	 * 排序类型
	 */
	private String dir;

	/**
	 * 当前页
	 */
	private int page;

	/**
	 * 每页显示记录数
	 */
	private int rows;

	/**
	 * 开始行
	 */
	private int start;

	/**
	 * 结束行
	 */
	private int end;

	/**
	 * 是否使用分页
	 */
	private String pagination;

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
