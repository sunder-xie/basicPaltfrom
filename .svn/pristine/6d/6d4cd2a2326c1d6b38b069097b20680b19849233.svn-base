package com.kintiger.platform.dict.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.dict.dao.IDictDao;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.util.LogUtil;



public class DictServiceImpl implements IDictService {

	private static final Log logger = LogFactory.getLog(DictServiceImpl.class);

	private IDictDao dictDao;

	public int getCmsTbDictCount(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getCmsTbDictCount(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return 0;
	}

	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getCmsTbDictList(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return null;
	}

	public int getDictCount(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getDictCount(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return 0;
	}

	public List<CmsTbDict> getDictList(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getDictList(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return null;
	}

	public int getCmsTbDictTypeCount(CmsTbDictType cmsTbDictType) {
		try {
			return dictDao.getCmsTbDictTypeCount(cmsTbDictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDictType), e);
		}

		return 0;
	}

	public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType) {
		try {
			return dictDao.getCmsTbDictTypeList(cmsTbDictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDictType), e);
		}

		return null;
	}

	public BooleanResult createDict(CmsTbDict cmsTbDict) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long itemId = dictDao.CreateDict(cmsTbDict);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(itemId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("创建失败");
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return booleanResult;
	}

	public BooleanResult createDictType(CmsTbDictType cmsTbDictType) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long dictTypeId = dictDao.CreateDictType(cmsTbDictType);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(dictTypeId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("创建失败");
			logger.error(LogUtil.parserBean(cmsTbDictType), e);
		}

		return booleanResult;
	}

	public BooleanResult updateDict(CmsTbDict cmsTbDict) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			int n = dictDao.updateDict(cmsTbDict);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(n));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("更新失败");
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return booleanResult;
	}

	public BooleanResult updateDictType(CmsTbDictType cmsTbDictType) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			int n = dictDao.updateDictType(cmsTbDictType);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(n));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("更新失败");
			logger.error(LogUtil.parserBean(cmsTbDictType), e);
		}

		return booleanResult;
	}

	public CmsTbDict getCmsTbDict(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getCmsTbDict(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return null;
	}

	public CmsTbDictType getCmsTbDictType(CmsTbDictType cmsTbDictType) {
		try {
			return dictDao.getCmsTbDictType(cmsTbDictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDictType), e);
		}

		return null;
	}

	public List<CmsTbDict> getCmsTbDictByType(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getCmsTbDictByType(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return null;
	}
	public List<CmsTbDict> getByCmsTbDictList(CmsTbDict cmsTbDict) {
		try {
			return dictDao.getByCmsTbDictList(cmsTbDict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cmsTbDict), e);
		}

		return null;
	}
	public IDictDao getDictDao() {
		return dictDao;
	}

	public void setDictDao(IDictDao dictDao) {
		this.dictDao = dictDao;
	}

	

}
