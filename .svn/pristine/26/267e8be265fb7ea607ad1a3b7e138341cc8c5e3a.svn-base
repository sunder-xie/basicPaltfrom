package com.kintiger.platform.role.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.role.dao.IRoleDao;
import com.kintiger.platform.role.pojo.Role;
import com.kintiger.platform.role.service.IRoleService;
import com.kintiger.platform.station.pojo.Station;


public class RoleServiceImpl implements IRoleService {
	
	private static final Log logger = LogFactory.getLog(RoleServiceImpl.class);

	
	public int getRoleCount(Role role) {
		try {
			return roleDao.getRoleCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}
	
	public int getBORoleCount(Role role) {
		try {
			return roleDao.getBORoleCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}
	
	public int getBORoleDetailCount(Role role) {
		try {
			return roleDao.getBORoleDetailCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}
	
	
	public int getRoleCount1(Role role) {
		try {
			return roleDao.getRoleCount1(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}
	
	public int getConRole(Role role){
		try {
			return roleDao.getConRole(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}
		return 0;
	}
	
	public int getRole1Count(Role role) {
		try {
			return roleDao.getRole1Count(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getRoleList(Role role) {
		try {
			return roleDao.getRoleList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}
	
	public List<Role> getBORoleList(Role role) {
		try {
			return roleDao.getBORoleList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}
	public List<Role> getBODetailRoleList(Role role) {
		try {
			return roleDao.getBODetailRoleList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}
	public List<Role> getConstraintList(Role role) {
		try {
			return roleDao.getConstraintList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}
	
	
	public int getPositionType4RoleCount(Role role) {
		try {
			return roleDao.getPositionType4RoleCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}
		return 0;
	}
	public List<Station> getPositionType4RoleList(Role role) {
		try {
			return roleDao.getPositionType4RoleList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}
		return null;
	}
	public List<Role> getRole1List(Role role) {
		try {
			return roleDao.getRole1List(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}
	public StringResult createRole(Role role) {
		StringResult result = new StringResult();

		try {
			String id = roleDao.createRole(role);
			result.setResult(id);
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IRoleService.ERROR);
			result.setResult(IRoleService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}
	public StringResult createRoledt(Role role) {
		StringResult result = new StringResult();

		try {
			String id = roleDao.createRoledt(role);
			result.setResult(id);
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IRoleService.ERROR);
			result.setResult(IRoleService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public StringResult updateRole(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int c = roleDao.updateRole(role);
			if (c == 1) {
				result.setCode(IRoleService.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}
	

	public StringResult deleteRole(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int c = roleDao.deleteRole(role);
			result.setResult(String.valueOf(c));
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}
	
	public StringResult deleteRoledt(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int c = roleDao.deleteRoledt(role);
			result.setResult(String.valueOf(c));
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public int getRole4ConpointCount(Role role) {
		try {
			return roleDao.getRole4ConpointCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getRole4ConpointList(Role role) {
		try {
			return roleDao.getRole4ConpointList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public int getRole4MenuCount(Role role) {
		try {
			return roleDao.getRole4MenuCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getRole4MenuList(Role role) {
		try {
			return roleDao.getRole4MenuList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public Role getRoleByRoleId(String roleId) {
		try {
			return roleDao.getRoleByRoleId(roleId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(roleId), e);
		}

		return null;
	}

	public int getSelectedRole4StationCount(Role role) {
		try {
			return roleDao.getSelectedRole4StationCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getSelectedRole4StationList(Role role) {
		try {
			return roleDao.getSelectedRole4StationList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public StringResult selectRole4Station(Role role) {
		StringResult result = new StringResult();

		try {
			// 获取codes中已存在的role
			List<Role> list = roleDao.getSelectedRole4Station(role);
			Map<String, Boolean> map = new HashMap<String, Boolean>();
			for (Role r : list) {
				map.put(r.getRoleId(), true);
			}

			int i = 0;
			String[] temp = new String[role.getCodes().length];

			for (String code : role.getCodes()) {
				if (map.get(code) == null) {
					temp[i++] = code;
				}
			}

			role.setCodes(temp);

			String ids = roleDao.selectRole4Station(role);
			result.setResult(ids);
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IRoleService.ERROR);
			result.setResult(IRoleService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public StringResult deleteSelectedRole4Station(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int c = roleDao.deleteSelectedRole4Station(role);
			result.setResult(String.valueOf(c));
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public int getSelectedRole4PositionTypeCount(Role role) {
		try {
			return roleDao.getSelectedRole4PositionTypeCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getSelectedRole4PositionTypeList(Role role) {
		try {
			return roleDao.getSelectedRole4PositionTypeList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public StringResult selectRole4PositionType(Role role) {
		StringResult result = new StringResult();

		try {
			// 获取codes中已存在的role
			List<Role> list = roleDao.getSelectedRole4PositionType(role);

			Map<String, Boolean> map = new HashMap<String, Boolean>();

			for (Role r : list) {
				map.put(r.getRoleId(), true);
			}

			int i = 0;
			String[] temp = new String[role.getCodes().length];

			for (String code : role.getCodes()) {
				if (map.get(code) == null) {
					temp[i++] = code;
				}
			}

			role.setCodes(temp);

			String ids = roleDao.selectRole4PositionType(role);
			result.setResult(ids);
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IRoleService.ERROR);
			result.setResult(IRoleService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public StringResult deleteSelectedRole4PositionType(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int c = roleDao.deleteSelectedRole4PositionType(role);
			result.setResult(String.valueOf(c));
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	private IRoleDao roleDao;

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

}

