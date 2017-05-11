package com.kintiger.platform.ireport.service;

import java.util.List;

import com.kintiger.platform.ireport.pojo.IreportType;

public interface IreportService {
	/**
	 * 查询报表模板
	 */
	public List<IreportType> serachReportModle(IreportType  type);
	
	/**
	 * 保存报表模板
	 */
	public Long saveReportModle(IreportType  type);
	/**删除报表模板
	 */
	public int deleteReportModle(IreportType  type);
	/**修改报表模板
	 */
	public int modifyReportModle(IreportType  type);
}
