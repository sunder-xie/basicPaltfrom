package com.kintiger.platform.monitor.dao;

import java.util.List;

import com.kintiger.platform.monitor.pojo.HessianDetail;

public interface IMonitorDao {
	/**
	 * �鿴hessian�ӿ�״̬
	 * 
	 * @return
	 */
	public List<HessianDetail> searchHessian();
	
}
