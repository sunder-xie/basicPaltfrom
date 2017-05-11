package com.kintiger.platform.ireport.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.ireport.dao.IreportDao;
import com.kintiger.platform.ireport.pojo.IreportType;
import com.kintiger.platform.menu.pojo.Menu;

public class ReportDaoImpl  extends BaseDaoImpl implements IreportDao{


	public List<IreportType> serachReportModle(IreportType type) {
		return (List<IreportType>) getSqlMapClientTemplate().queryForList(
				"ireport.getreportModleList", type);
	}


	public Long saveReportModle(IreportType type) {
		return (Long) getSqlMapClientTemplate().insert("ireport.saveModle", type);
	}


	public int deleteReportModle(IreportType type) {
		return getSqlMapClientTemplate().delete("ireport.deleteModle", type);
	
	}


	public int modifyReportModle(IreportType type) {
		return getSqlMapClientTemplate().update("ireport.updateModle", type);
	}

}
