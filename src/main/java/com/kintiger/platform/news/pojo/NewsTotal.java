package com.kintiger.platform.news.pojo;

import java.util.Date;
import java.util.List;

import com.kintiger.platform.base.pojo.SearchInfo;

public class NewsTotal extends SearchInfo {

	private static final long serialVersionUID = 1L;

	/**
	 * 栏目id
	 */
	private Long total_id;

	/**
	 * 父ID
	 */
	private Long total_parent_id;

	/**
	 * 栏目名称
	 */
	private String total_name;

	/**
	 * 栏目说明
	 */
	private String total_title;

	/**
	 * 创建时间
	 */
	private Date total_date;

	/**
	 * 栏目排序码
	 */
	private String total_code;

	/**
	 * 是否在首页显示（Y：是，N：否）
	 */
	private String total_show;

	/**
	 * 是否为跑马灯状态（Y：是，N：否）
	 */
	private String total_sign;

	/**
	 * 删除状态（Y：正常，N：删除）
	 */
	private String total_flag;

	/**
	 * 修改时间
	 */
	private Date last_modify;

	/**
	 * 文件上传标识字段（Y：是，N：否）
	 */
	private String total_upload_sign;

	private List<NewsDetail> newsdet_list;
	
	//判断排序码是否占用
	private String newsCode;
	/**
	 * 批量刪除ID
	 * @return
	 */
    private String[] total_ids;//编制ID组
    
	public String[] getTotal_ids() {
		return total_ids;
	}

	public void setTotal_ids(String[] total_ids) {
		this.total_ids = total_ids;
	}

	public Long getTotal_id() {
		return total_id;
	}

	public void setTotal_id(Long total_id) {
		this.total_id = total_id;
	}

	public Long getTotal_parent_id() {
		return total_parent_id;
	}

	public void setTotal_parent_id(Long total_parent_id) {
		this.total_parent_id = total_parent_id;
	}

	public String getTotal_name() {
		return total_name;
	}

	public void setTotal_name(String total_name) {
		this.total_name = total_name;
	}

	public String getTotal_title() {
		return total_title;
	}

	public void setTotal_title(String total_title) {
		this.total_title = total_title;
	}

	public Date getTotal_date() {
		return total_date;
	}

	public void setTotal_date(Date total_date) {
		this.total_date = total_date;
	}

	public String getTotal_code() {
		return total_code;
	}

	public void setTotal_code(String total_code) {
		this.total_code = total_code;
	}

	public String getTotal_show() {
		return total_show;
	}

	public void setTotal_show(String total_show) {
		this.total_show = total_show;
	}

	public String getTotal_sign() {
		return total_sign;
	}

	public void setTotal_sign(String total_sign) {
		this.total_sign = total_sign;
	}

	public String getTotal_flag() {
		return total_flag;
	}

	public void setTotal_flag(String total_flag) {
		this.total_flag = total_flag;
	}

	public Date getLast_modify() {
		return last_modify;
	}

	public void setLast_modify(Date last_modify) {
		this.last_modify = last_modify;
	}

	public String getTotal_upload_sign() {
		return total_upload_sign;
	}

	public void setTotal_upload_sign(String total_upload_sign) {
		this.total_upload_sign = total_upload_sign;
	}

	public List<NewsDetail> getNewsdet_list() {
		return newsdet_list;
	}

	public void setNewsdet_list(List<NewsDetail> newsdet_list) {
		this.newsdet_list = newsdet_list;
	}

	public String getNewsCode() {
		return newsCode;
	}

	public void setNewsCode(String newsCode) {
		this.newsCode = newsCode;
	}
	
}