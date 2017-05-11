package com.kintiger.platform.monitor.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.monitor.dao.IMonitorDao;
import com.kintiger.platform.monitor.pojo.HessianDetail;
import com.kintiger.platform.monitor.service.IMonitorService;

public class MonitorServiceImpl implements IMonitorService {

	private IMonitorDao monitorDao;
	private static final Log logger = LogFactory
			.getLog(MonitorServiceImpl.class);

	public List<HessianDetail> searchHessian() {
		try {
			return monitorDao.searchHessian();
		} catch (Exception e) {
			logger.error(null, e);
			return null;
		}

	}

	public IMonitorDao getMonitorDao() {
		return monitorDao;
	}

	public void setMonitorDao(IMonitorDao monitorDao) {
		this.monitorDao = monitorDao;
	}

}
