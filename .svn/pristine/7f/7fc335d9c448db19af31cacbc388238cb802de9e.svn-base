package com.kintiger.platform.position.service.impl;

import java.util.List;

import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.position.dao.IPositionDao;
import com.kintiger.platform.position.pojo.Bposition;
import com.kintiger.platform.position.service.IPositionService;

public class PositionServiceImpl implements IPositionService {


	private IPositionDao positionDao;

	public int getPositionCount(Bposition position) {
		try {
			return positionDao.getPositionCount(position);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Bposition> getPositionList(Bposition position) {
		try {
			return positionDao.getPositionList(position);
		} catch (Exception e) {
		}

		return null;
	}

	public List<Bposition> getPosLevelTypeList(Bposition position) {
		try {
			return positionDao.getPositionList(position);
		} catch (Exception e) {
			
		}

		return null;
	}

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType) {
		try {
			return positionDao.getCmsTbDictTypeList(cmsTbDictType);
		} catch (Exception e) {
			
		}
		return null;
	}

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getCmsTbDictJoinTypeList(CmsTbDict cmsTbDict) {
		try {
			return positionDao.getCmsTbDictJoinTypeList(cmsTbDict);
		} catch (Exception e) {
		}
		return null;
	}

	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
		try {
			return positionDao.getCmsTbDictList(cmsTbDict);
		} catch (Exception e) {
		}
		return null;
	}

	public List<Bposition> getStaffamountPositionList(Long orgId) {
		try {
			return positionDao.getStaffamountPositionList(orgId);
		} catch (Exception e) {
		}
		return null;
	}

	public List<Bposition> getAllStaffamountPosition(Long orgId) {
		try {
			return positionDao.getAllStaffamountPosition(orgId);
		} catch (Exception e) {
		}
		return null;
	}

	

	public Boolean updatePosition(Bposition bposition) {
		try {
			int result = 0;
			result = positionDao.updatePosition(bposition);
			if (result > 0)
				return true;
		} catch (Exception e) {
		
		}
		return false;
	}

	

	/**
	 * 获取职位对象
	 * 
	 * @param position
	 * @return
	 */
	public Bposition getPositionObj(Bposition position) {
		try {
			return positionDao.getPositionObj(position);
		} catch (Exception e) {
			
		}
		return null;
	}

	

	public List<Bposition> getStaffamountPositionPosList(Bposition bposition) {
		try {
			return positionDao.getStaffamountPositionPosList(bposition);
		} catch (Exception e) {
		
		}
		return null;
	}

	public List<Bposition> getManagementList(Long orgId) {
		try {
			return positionDao.getManagementList(orgId);
		} catch (Exception e) {
		}
		return null;
	}

	public List<Bposition> getAssignedPosList(Long posId) {
		try {
			return positionDao.getAssignedPosList(posId);
		} catch (Exception e) {
		}
		return null;
	}

	public List<Bposition> getUnassignedPosList(Long orgId) {
		try {
			return positionDao.getUnassignedPosList(orgId);
		} catch (Exception e) {
		}
		return null;
	}

	public List<Bposition> getPositionsByEmpId(Long empId) {
		try {
			return positionDao.getPositionsByEmpId(empId);
		} catch (Exception e) {
		}
		return null;
	}

	public int getDeviceNum(Long posId) {
		try {
			return positionDao.getDeviceNum(posId);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Bposition> getPosition(Bposition bposition) {
		try {
			return positionDao.getPosition(bposition);
		} catch (Exception e) {
		
		}
		return null;
	}

	public Boolean updatePosition4User(Bposition bposition) {
		try {
			int result = 0;
			result = positionDao.updatePosition4User(bposition);
			if (result > 0)
				return true;
		} catch (Exception e) {
		
		}
		return false;
	}

	public Boolean updatePosition4Position(Bposition bposition) {
		try {
			int result = 0;
			result = positionDao.updatePosition4Position(bposition);
			if (result > 0)
				return true;
		} catch (Exception e) {
		
		}
		return false;
	}

	public Bposition getPositionByUseState(Bposition bposition1) {
		try {
			return positionDao.getPositionByUseState(bposition1);
		} catch (Exception e) {
			
		}

		return null;
	}

	public List<Bposition> getPositionListByUseState(Bposition bposition1) {
		try {
			return positionDao.getPositionListByUseState(bposition1);
		} catch (Exception e) {
			
		}
		return null;
	}

	public IPositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(IPositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public String createPosition(Bposition bposition) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deletePosition(Long posId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String releasePosition(Bposition position) {
		// TODO Auto-generated method stub
		return null;
	}

}
