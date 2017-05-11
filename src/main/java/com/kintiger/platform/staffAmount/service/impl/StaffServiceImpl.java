package com.kintiger.platform.staffAmount.service.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.staffAmount.dao.IStaffDao;
import com.kintiger.platform.staffAmount.pojo.StaffAmount;
import com.kintiger.platform.staffAmount.service.IStaffService;
import com.kintiger.platform.station.pojo.Station;

public class StaffServiceImpl implements IStaffService {

	private static final Log logger = LogFactory.getLog(StaffServiceImpl.class);
	private IStaffDao iStaffDao;
	private TransactionTemplate transactionTemplate;

	public int getStaffTotal(StaffAmount s) {
		try {
			return iStaffDao.getStaffTotal(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(s), e);
		}
		return 0;
	}

	public List<StaffAmount> getStaffList(StaffAmount s) {
		try {
			return iStaffDao.getStaffList(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(s), e);
		}
		return null;
	}

	/**
	 * 修改编制
	 */
	public StringResult updateStaffAmounts(final StaffAmount staffAmount) {
		StringResult result = new StringResult();
		result = (StringResult) transactionTemplate.execute(new TransactionCallback() {
			public StringResult doInTransaction(TransactionStatus ts) {
				StringResult result = new StringResult();
				try {
					int c = iStaffDao.updateStaffAmounts(staffAmount);
					if(c==-1){
						ts.setRollbackOnly();
						result.setResult("有编制审批中，请重新调整数量！");
						result.setCode(IStaffService.ERROR);
						return result;
					}
					result.setResult(String.valueOf(c));
					result.setCode(IStaffService.SUCCESS);
				} catch (Exception e) {
					ts.setRollbackOnly();
					logger.error(LogUtil.parserBean(staffAmount), e);
				}
				return result;
			}
		});
		return result;
	}

	public IStaffDao getiStaffDao() {
		return iStaffDao;
	}

	public void setiStaffDao(IStaffDao iStaffDao) {
		this.iStaffDao = iStaffDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public int getStaffAmountCount(StaffAmount staffAmount) {
		try {
			return iStaffDao.getStaffAmountCount(staffAmount);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(staffAmount), e);
		}
		return 0;
	}

	public StringResult createStaff(final StaffAmount staffAmount) {
		StringResult result = new StringResult();
		result = (StringResult) transactionTemplate.execute(new TransactionCallback() {
			public StringResult doInTransaction(TransactionStatus ts) {
				StringResult result = new StringResult();
				try {
					Long id = iStaffDao.createStaff(staffAmount);
					result.setResult(id.toString());
					result.setCode(IStaffService.SUCCESS);
				} catch (Exception e) {
					result.setCode(IStaffService.ERROR);
					logger.error(LogUtil.parserBean(staffAmount), e);
				}
				return result;
			}
		});
		return result;
	}

	public List<Station> blurSearchStaff(Station station) {
		try {
			return iStaffDao.blurSearchStaff(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
		}
		return null;
	}

	public int getStaffAmountCountU(StaffAmount staffAmount) {
		try {
			return iStaffDao.getStaffAmountCountU(staffAmount);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(staffAmount), e);
		}
		return 0;
	}

	public List<Station> geStaffUser(Station paramStation) {
		try {
			return iStaffDao.geStaffUser(paramStation);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(paramStation), e);
		}
		return null;
	}

}
