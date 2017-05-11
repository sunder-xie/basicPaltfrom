package com.kintiger.platform.conpoint.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.conpoint.dao.IConpointDao;
import com.kintiger.platform.conpoint.pojo.Conpoint;
import com.kintiger.platform.conpoint.service.IConpointService;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.menu.service.IMenuService;



public class ConpointServiceImpl implements IConpointService {


	private static final Log logger = LogFactory.getLog(ConpointServiceImpl.class);

	private IConpointDao conpointDao;

	public int getConpointCount(Conpoint p) {
		try {
			return conpointDao.getConpointCount(p);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(p), e);
		}

		return 0;
	}

	public List<Conpoint> getConpointList(Conpoint p) {
		try {
			return conpointDao.getConpointList(p);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(p), e);
		}

		return null;
	}

	public int deleteConpoint(BigDecimal conpointId) {
		try {
			return conpointDao.deleteConpoint(conpointId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(conpointId), e);
		}

		return 0;
	}

	public Conpoint getConpointMenuPojo(BigDecimal conpointId) {
		try {
			return conpointDao.getConpointMenuPojo(conpointId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(conpointId), e);
		}

		return null;
	}

	public int modifyConpoint(Conpoint p) {
		try {
			return conpointDao.modifyConpoint(p);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(p), e);
		}

		return 0;
	}

	public Object createConpoint(Conpoint c) {
		try {
			return conpointDao.createConpoint(c);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(c), e);
		}

		return null;
	}

	public String isAut(Conpoint conpoint) {
		try {
			if (conpointDao.isAut(conpoint) != null) {
				return "true";
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(conpoint), e);
			return null;
		}

		return "false";
	}

	public Map<BigDecimal, String> getPermissions(String userId,
			String[] conpointId) {
		Map<BigDecimal, String> map = new HashMap<BigDecimal, String>();

		try {
			Conpoint conpoint = new Conpoint();
			conpoint.setUserId(userId);
			conpoint.setCodes(conpointId);
			List<Conpoint> list = conpointDao.getPermissions(conpoint);

			if (list != null && list.size() > 0) {
				for (Conpoint c : list) {
					map.put(c.getConpointId(), c.getCloseFlag());
				}
			}
		} catch (Exception e) {
			logger.error("userId:" + userId + "conpointId:" + conpointId, e);
		}

		return map;
	}

	public int getRoleConpointCount(Conpoint conpoint) {
		try {
			return conpointDao.getRoleConpointCount(conpoint);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(conpoint), e);
		}

		return 0;
	}

	public List<Conpoint> getRoleConpointList(Conpoint conpoint) {
		try {
			return conpointDao.getRoleConpointList(conpoint);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(conpoint), e);
		}

		return null;
	}

	public StringResult createRoleConpoint(Conpoint conpoint) {
		StringResult result = new StringResult();

		try {
			Long id = conpointDao.createRoleConpoint(conpoint);
			result.setResult(id.toString());
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IMenuService.ERROR);
			result.setResult(IMenuService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(conpoint), e);
		}

		return result;
	}

	public StringResult updateRoleConpoint(Conpoint conpoint) {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		try {
			int c = conpointDao.updateRoleConpoint(conpoint);
			if (c == 1) {
				result.setCode(IMenuService.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(conpoint), e);
		}

		return result;
	}

	public Conpoint getRoleConpointById(Long roleConpointId) {
		try {
			return conpointDao.getRoleConpointById(roleConpointId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(roleConpointId), e);
		}

		return null;
	}

	public StringResult deleteRoleConpoint(Conpoint conpoint) {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		try {
			int c = conpointDao.updateRoleConpoint(conpoint);
			result.setResult(String.valueOf(c));
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(conpoint), e);
		}

		return result;
	}

	public IConpointDao getConpointDao() {
		return conpointDao;
	}

	public void setConpointDao(IConpointDao conpointDao) {
		this.conpointDao = conpointDao;
	}

	public StringResult deleteConpoints(Conpoint p) {
		StringResult result = new StringResult();
		result.setCode(IConpointService.ERROR);
		result.setResult(IConpointService.ERROR_MESSAGE);

		try {
			int c = conpointDao.deleteConpoints(p);
			result.setResult(String.valueOf(c));
			result.setCode(IConpointService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(p), e);
		}

		return result;
	}

	public List<Conpoint> getConpointListJson(Conpoint p) {
		try {
			return conpointDao.getConpointListJson(p);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(p), e);
		}

		return null;
	}

	public int getRolesByConpointId(Conpoint c) {
		try {
			return conpointDao.getRolesByConpointId(c);
		} catch (Exception e) {
			
			logger.error(LogUtil.parserBean(c), e);
		}
		return 0;
	}

	public List<Conpoint> getConpointListIsExit(Conpoint p) {
		try {
			return conpointDao.getConpointListIsExit(p);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(p), e);
		}

		return null;
	}

	

}
