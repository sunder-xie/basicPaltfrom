package com.kintiger.platform.monitor.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.monitor.dao.IMonitorDao;
import com.kintiger.platform.monitor.pojo.HessianDetail;

public class MonitorDaoImpl extends BaseDaoImpl implements IMonitorDao {

	@SuppressWarnings("unchecked")
	public List<HessianDetail> searchHessian() {
		return (List<HessianDetail>) this.getSqlMapClientTemplate()
				.queryForList("monitor.searchHessian");
	}

}
