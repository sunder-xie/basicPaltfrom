package com.kintiger.platform.station.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.station.dao.IStationDao;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.station.pojo.StationOrg;
import com.kintiger.platform.station.pojo.StationRole;
import com.kintiger.platform.station.service.IStationService;

public class StationServiceImpl implements IStationService {

	private static final Logger logger = Logger
			.getLogger(StationServiceImpl.class);

	private IStationDao stationDao;

	public List<Station> getStationJsonList(Station s) {
		try {
			return stationDao.getStationJsonList(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(s), e);
			return null;
		}
	}

	public List<Borg> getCompanyJsonList() {
		try {
			return stationDao.getCompanyJsonList();
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(this.getClass()), e);
			return null;
		}
	}

	public List<CmsTbDict> getCustTypeList() {
		try {
			return stationDao.getCustTypeList();
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(this.getClass()), e);
			return null;
		}
	}
	public int getStationJsonListCount(Station s) {
		try {
			return stationDao.getStationJsonListCount(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(s), e);
			return 0;
		}
	}
	
	public int getStationJsonListCountForSelect(Station station) {
		try {
			return stationDao.getStationJsonListCountForSelect(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
			return 0;
		}
	}

	public List<Station> getStationJsonListForSelect(Station station) {
		try {
			return stationDao.getStationJsonListForSelect(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
			return null;
		}
	}



	public Station getStationEntity(String stationId) {
		try {
			return stationDao.getStationEntity(stationId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(stationId), e);
			return null;
		}
	}

	public int updateStationEntity(Station s) {
		try {
			return stationDao.updateStationEntity(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(s), e);
			return 0;
		}
	}

	public List<Station> getStationUser(String stationId) {
		try {
			return stationDao.getStationUser(stationId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(stationId), e);
			return null;
		}
	}

	public List<StationRole> getStationRole(String stationId) {
		try {
			return stationDao.getStationRole(stationId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(stationId), e);
			return null;
		}
	}

	public int deleteStationEntity(String stationId) {
		try {
			return stationDao.deleteStationEntity(stationId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(stationId), e);
			return 0;
		}
	}

	public String createStationEntity(Station s) {
		try {
			return stationDao.createStationEntity(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(s), e);
			return null;
		}
	}

	public int searchStationUserCount(Station s) {
		try {
			return stationDao.searchStationUserCount(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(s), e);
			return 0;
		}
	}

	public List<Station> searchStationUser(Station s) {
		try {
			return stationDao.searchStationUser(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(s), e);
			return null;
		}
	}

	public int deleteStationUser(Station s) {
		try {
			return stationDao.deleteStationUser(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(s), e);
			return 0;
		}
	}


	public List<Station> searchUser(Station s) {
		try {
			return stationDao.searchUser(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(this.getClass()), e);
			return null;
		}
	}

	public int searchUserCount(Station s) {
		try {
			return stationDao.searchUserCount(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(this.getClass()), e);
			return 0;
		}
	}

	public Long insertStationUser(Station s) {
		try {
			return stationDao.insertStationUser(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(s), e);
			return 0L;
		}
	}

	public int getStationUserCount(String stationId) {
		try {
			return stationDao.getStationUserCount(stationId);
		} catch (Exception e) {
			logger.error(stationId, e);
		}

		return 0;
	}
	public int getStaffStationCount(Station s) {
		try {
			return stationDao.getStaffStationCount(s);
		} catch (Exception e) {
			logger.error(s.getStationId(), e);
		}

		return 0;
	}
	public int getStaffStationAmount(Station s) {
		try {
			return stationDao.getStaffStationAmount(s);
		} catch (Exception e) {
			logger.error(s.getStationId(), e);
		}
		return 0;
	}
	public int getStationRoleCount(String stationId) {
		try {
			return stationDao.getStationRoleCount(stationId);
		} catch (Exception e) {
			logger.error(stationId, e);
		}

		return 0;
	}
	
	/**
	 * 根据UserId查询岗位
	 * @param empId
	 * @return
	 */
	public List<Station> getStationByEmpId(Long empId){
		try {
			return stationDao.getStationByEmpId(empId);
		} catch (Exception e) {
			logger.error(empId, e);
			return null;
		}
	}
	/**
	 * 根据UserId查询主次岗位的组织
	 * @param empId
	 * @return
	 */
	public List<String> getOrgListByUserId(String userId){
		try {
			return stationDao.getOrgListByUserId(userId);
		} catch (Exception e) {
			logger.error(userId, e);
			return null;
		}
	}

	public IStationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(IStationDao stationDao) {
		this.stationDao = stationDao;
	}

	public BooleanResult updateStationUser(AllUsers allUsers) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			int n = stationDao.updateStationUser(allUsers);
			if(n==1){
				booleanResult.setResult(true);
			}
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("人员岗位关系创建失败");
			logger.error(LogUtil.parserBean(allUsers), e);
		}

		return booleanResult;
	}

	public String getStationCountByEmpId(AllUsers allUsers) {
		try {
			int n =  stationDao.getStationCountByEmpId(allUsers);
			if(n==0){
				return "unexist";
			}
			return "exist";
		} catch (Exception e) {
			logger.error(allUsers, e);
			return null;
		}
	}

	public int searchStationUserMoreCount(Station station) {
		try {
			return stationDao.searchStationUserMoreCount(station);
		} catch (Exception e) {
			logger.error(station, e);
		}

		return 0;
	}

	public List<Station> searchStationUserMore(Station station) {
		try {
			return stationDao.searchStationUserMore(station);
		} catch (Exception e) {
			logger.error(station, e);
		}

		return null;
	}

	public int deleteStationUserById(Station station) {
		try {
			return stationDao.deleteStationUserById(station);
		} catch (Exception e) {
			logger.error(station, e);
		}
		return 0;
	}

	/***
	 * 创建岗位和组织关系表
	 * @param s
	 * @return
	 */
	public Long createStationOrg(StationOrg s){
		try{
			 int  result = stationDao.getStationOrgCount(s);
			 if(result > 0){
				 return Long.valueOf(stationDao.updateStationOrg(s));
			 }else{
				return stationDao.createStationOrg(s);
			 }
		}catch (Exception e) {
			logger.error(s, e);
		}
		return 0L;
	}
	/***
	 * 查询岗位和组织关系列表
	 * @param s
	 * @return
	 */
	public List<StationOrg> getStationOrgList(StationOrg s){
		try{
			return stationDao.getStationOrgList(s);
		}catch (Exception e) {
			logger.error(s, e);
		}
		return null;
	}
	/***
	 * 修改岗位和组织关系表
	 * @param s
	 * @return
	 */
	public int updateStationOrg(StationOrg s){
		try{
			return stationDao.updateStationOrg(s);
		}catch (Exception e) {
			logger.error(s, e);
		}
		return 0;
	}
	
	public int getPermissionByUserId(String userId) {
		try{
			return stationDao.getPermissionByUserId(userId);
		}catch (Exception e) {
			logger.error(userId, e);
		}
		return 0;
	}
}
