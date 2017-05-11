package com.kintiger.platform.boform.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.boform.dao.IBoformDao;
import com.kintiger.platform.boform.pojo.QueryParameter;
import com.kintiger.platform.boform.pojo.ReportParameter;
import com.kintiger.platform.boform.service.IBoformService;
import com.kintiger.platform.framework.util.LogUtil;



public class BoformServiceImpl implements IBoformService {

	private static final Log logger = LogFactory.getLog(BoformServiceImpl.class);
	
	private IBoformDao boformDao;

	public int getReportParameterCount(ReportParameter reportParameter) {
		try {
			return boformDao.getReportParameterCount(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return 0;
	}

	public List<ReportParameter> getReportParameterList(
			ReportParameter reportParameter) {
		try {
			return boformDao.getReportParameterList(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return null;
	}

	public StringResult createBatchReportParameter(
			List<ReportParameter> reportParameterList) {
		StringResult result = new StringResult();

		try {
			String ids = boformDao
					.createBatchReportParameter(reportParameterList);
			result.setResult(ids);
			result.setCode(IBoformService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IBoformService.ERROR);
			result.setResult(IBoformService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(reportParameterList), e);
		}

		return result;
	}

	public ReportParameter getReportParameterByPid(Long pid) {
		try {
			return boformDao.getReportParameterByPid(pid);
		} catch (Exception e) {
			logger.error(pid, e);
		}

		return null;
	}

	public StringResult updateReportParameter(ReportParameter reportParameter) {
		StringResult result = new StringResult();
		result.setCode(IBoformService.ERROR);
		result.setResult(IBoformService.ERROR_MESSAGE);

		try {
			int c = boformDao.updateReportParameter(reportParameter);
			if (c == 1) {
				result.setCode(IBoformService.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return result;
	}

	public StringResult deleteReportParameter(ReportParameter reportParameter) {
		StringResult result = new StringResult();
		result.setCode(IBoformService.ERROR);
		result.setResult(IBoformService.ERROR_MESSAGE);

		try {
			int c = boformDao.deleteReportParameter(reportParameter);
			result.setResult(String.valueOf(c));
			result.setCode(IBoformService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return result;
	}

	public List<ReportParameter> getReportParametersByBid(Long bid) {
		try {
			return boformDao.getReportParametersByBid(bid);
		} catch (Exception e) {
			logger.error(bid, e);
		}

		return null;
	}

	public int getQueryOrgCount(ReportParameter reportParameter) {
		try {
			return boformDao.getQueryOrgCount(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return 0;
	}

	public List<QueryParameter> getQueryOrgList(ReportParameter reportParameter) {
		try {
			return boformDao.getQueryOrgList(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return null;
	}

	public int getQueryAllChildOrgCount(ReportParameter reportParameter) {
		try {
			return boformDao.getQueryAllChildOrgCount(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return 0;
	}

	public List<QueryParameter> getQueryAllChildOrgList(
			ReportParameter reportParameter) {
		try {
			return boformDao.getQueryAllChildOrgList(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return null;
	}

	public int getQueryParameterCount(ReportParameter reportParameter) {
		try {
			return boformDao.getQueryParameterCount(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return 0;
	}

	public List<QueryParameter> getQueryParameterList(
			ReportParameter reportParameter) {
		try {
			return boformDao.getQueryParameterList(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return null;
	}

	public IBoformDao getBoformDao() {
		return boformDao;
	}

	public void setBoformDao(IBoformDao boformDao) {
		this.boformDao = boformDao;
	}

	public List<QueryParameter> getProByEid(String userId) {
		// TODO Auto-generated method stub
		try {
			return boformDao.getProByEid(userId);
		
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(userId, e);
		}
		return null;
	}
	
	public int createOrderReportLog(Map<String,Object> map){
		try {
			return boformDao.createOrderReportLog(map);
		
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(map, e);
		}
		return 0;
	}
	
	public List<String> getKunnrIdByHeadOrAgent(String userId){
		try {
			return boformDao.getKunnrIdByHeadOrAgent(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}
		return null;
	}
	
	public List<String> getKunnrIdByCompetent(String stationId){
		try {
			return boformDao.getKunnrIdByCompetent(stationId);
		} catch (Exception e) {
			logger.error(stationId, e);
		}
		return null;
	}

	@Override
	public List<String> getKunnrIdByUserId(String orgId) {
		try {
			return boformDao.getKunnrIdByUserId(orgId);
		} catch (Exception e) {
			logger.error(orgId, e);
		}
		return null;
	}

}
