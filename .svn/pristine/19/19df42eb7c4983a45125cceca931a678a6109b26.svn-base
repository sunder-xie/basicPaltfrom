package com.kintiger.platform.position.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.position.dao.IPositionTypeDao;
import com.kintiger.platform.position.pojo.BpositionType;
import com.kintiger.platform.position.service.IPositionTypeService;

public class PositionTypeServiceImpl implements IPositionTypeService {

	private Logger logger = Logger
			.getLogger(PositionTypeServiceImpl.class);

	private IPositionTypeDao positionTypeDao;
	
	
	private TransactionTemplate transactionTemplate;

	public int getPositionTypesCount(BpositionType positionType) {
		try {
			return positionTypeDao.getPositionTypesCount(positionType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(positionType), e);
		}

		return 0;
	}

	public List<BpositionType> getPositionTypesList(BpositionType positionType) {
		try {
			return positionTypeDao.getPositionTypesList(positionType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(positionType), e);
		}

		return null;
	}
	public List<BpositionType> exportPositionTypesList(BpositionType positionType) {
		try {
			return positionTypeDao.exportPositionTypesList(positionType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(positionType), e);
		}

		return null;
	}
	public BpositionType getPositionTypes(BpositionType positionType) {
		try {
			return positionTypeDao.getPositionTypes(positionType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(positionType), e);
		}

		return null;
	}

//	public Borg getCompanyName(Borg borg) {
//		try {
//			return orgDao.getCompanyList(borg);
//		} catch (Exception e) {
//			logger.error(LogUtil.parserBean(borg), e);
//		}
//
//		return null;
//	}
	public int updatePositionTypes(BpositionType positionType) {
		try {
			return positionTypeDao.updatePositionTypes(positionType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(positionType), e);
		}

		return 0;
	}

	public Long createPositionTypes(BpositionType positionType) {
		try {
			return positionTypeDao.createPositionTypes(positionType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(positionType), e);
		}

		return 0l;
	}

	public int getPositionType4RoleCount(BpositionType positionType) {
		try {
			return positionTypeDao.getPositionType4RoleCount(positionType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(positionType), e);
		}

		return 0;
	}
	public StringResult insertPos(final List<BpositionType> BList){
		StringResult result = new StringResult();
		result = (StringResult) transactionTemplate
				.execute(new TransactionCallback() {
					public StringResult doInTransaction(TransactionStatus ts) {
						StringResult result = new StringResult();
						result.setCode(IPositionTypeService.SUCCESS);
						try {
							for (BpositionType positionType : BList) {
							positionTypeDao.insertPos(positionType);
							}
						} catch (Exception e) {
							result.setCode(IPositionTypeService.ERROR);
							ts.setRollbackOnly();
							logger.error("±£´æ³ö´í",e);
						}
						return result;
					}
				});
		return result;
	}
	
	

	public List<BpositionType> getPositionType4RoleList(
			BpositionType positionType) {
		try {
			return positionTypeDao.getPositionType4RoleList(positionType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(positionType), e);
		}

		return null;
	}

	public IPositionTypeDao getPositionTypeDao() {
		return positionTypeDao;
	}

	public void setPositionTypeDao(IPositionTypeDao positionTypeDao) {
		this.positionTypeDao = positionTypeDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	


}
