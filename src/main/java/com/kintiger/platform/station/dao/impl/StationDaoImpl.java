package com.kintiger.platform.station.dao.impl;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.station.dao.IStationDao;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.station.pojo.StationOrg;
import com.kintiger.platform.station.pojo.StationRole;

@SuppressWarnings("rawtypes")
public class StationDaoImpl extends BaseDaoImpl implements IStationDao {



	@SuppressWarnings("unchecked")
	public List<Station> getStationJsonList(Station s) {
		return getSqlMapClientTemplate().queryForList(
				"station.getStationJsonList", s);
	}

	public int getStationJsonListCount(Station s) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				("station.getStationJsonListCount"), s);
	}
	public int getStaffStationCount(Station s) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				("station.getStaffStationcount"), s);
	}
	public int getStaffStationAmount(Station s) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				("station.getStaffStationAmount"), s);
	}
	@SuppressWarnings("unchecked")
	public List<Station> getStationJsonListForSelect(Station station) {
		return getSqlMapClientTemplate().queryForList(
				"station.getStationJsonListForSelect", station);
	}

	public int getStationJsonListCountForSelect(Station station) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				("station.getStationJsonListCountForSelect"), station);
	}
	
	public Station getStationEntity(String stationId) {
		return (Station) getSqlMapClientTemplate().queryForObject(
				("station.getStationEntity"), stationId);
	}
	@SuppressWarnings("unchecked")
	public List<Borg> getCompanyJsonList() {
		return getSqlMapClientTemplate().queryForList(
				"station.getCompanyJsonList");
	}
	@SuppressWarnings("unchecked")
	public List<CmsTbDict> getCustTypeList() {
		return getSqlMapClientTemplate().queryForList(
				"station.getCustTypeList");
	}

	public int updateStationEntity(Station s) {
		return getSqlMapClientTemplate().update(
				("station.updateStationEntity"), s);
	}

	@SuppressWarnings("unchecked")
	public List<Station> getStationUser(String stationId) {
		return getSqlMapClientTemplate().queryForList(
				("station.getStationUser"), stationId);
	}

	@SuppressWarnings("unchecked")
	public List<StationRole> getStationRole(String stationId) {
		return getSqlMapClientTemplate().queryForList(
				("station.getStationRole"), stationId);
	}

	public int deleteStationEntity(String stationId) {
		return getSqlMapClientTemplate().update(
				("station.deleteStationEntity"), stationId);
	}

	public String createStationEntity(Station s) {
		return (String) getSqlMapClientTemplate().insert(
				"station.createStationEntity", s);
	}

	public int searchStationUserCount(Station s) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"station.searchStationUserCount", s);
	}

	@SuppressWarnings("unchecked")
	public List<Station> searchStationUser(Station s) {
		return getSqlMapClientTemplate().queryForList(
				"station.searchStationUser", s);
	}

	public int deleteStationUser(Station s) {
		return getSqlMapClientTemplate().delete(
				"station.deleteStationUser", s);
	}



	@SuppressWarnings("unchecked")
	public List<Station> searchUser(Station s) {
		return getSqlMapClientTemplate().queryForList(
				"station.searchUser", s);
	}

	public int searchUserCount(Station s) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"station.searchUserCount", s);
	}

	public Long insertStationUser(Station s) {
		return (Long) getSqlMapClientTemplate().insert(
				"station.insertStationUser", s);
	}

	public int getStationUserCount(String stationId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"station.getStationUserCount", stationId);
	}

	public int getStationRoleCount(String stationId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"station.getStationRoleCount", stationId);
	}

	@SuppressWarnings("unchecked")
	public List<Station> getStationByEmpId(Long empId) {
		return getSqlMapClientTemplate().queryForList(
				"station.getStationByEmpId", empId);
	}
	
	
	public int updateStationUser(AllUsers allUsers) {
		return   getSqlMapClientTemplate().update("station.updateStationUser",allUsers);
	}

	public int getStationCountByEmpId(AllUsers allUsers) {
		return  (Integer) getSqlMapClientTemplate().queryForObject(
				"station.getStationCountByEmpId", allUsers);
	}

	public int searchStationUserMoreCount(Station station) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"station.searchStationUserMoreCount", station);
	}

	@SuppressWarnings("unchecked")
	public List<Station> searchStationUserMore(Station station) {
		return getSqlMapClientTemplate().queryForList(
				"station.searchStationUserMore", station);
	}

	public int deleteStationUserById(Station station) {
		return getSqlMapClientTemplate().update("station.deleteStationUserById",station);
	}

	/***
	 * 创建岗位和组织关系表
	 * @param s
	 * @return
	 */
	public Long createStationOrg(StationOrg s){
		return (Long) getSqlMapClientTemplate().insert("station.createStationOrg",s);
	}
	/***
	 * 查询岗位和组织关系列表
	 * @param s
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StationOrg> getStationOrgList(StationOrg s){
		return getSqlMapClientTemplate().queryForList(
				"station.getStationOrgList", s);
	}
	/***
	 * 修改岗位和组织关系表
	 * @param s
	 * @return
	 */
	public int updateStationOrg(StationOrg s){
		return getSqlMapClientTemplate().update("station.updateStationOrg",s);

	}
	/***
	 * 查询获取岗位是否已经配置
	 * @param s
	 * @return
	 */
	public int getStationOrgCount(StationOrg s){
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"station.getStationOrgCount", s);
	}

	@SuppressWarnings("unchecked")
	public List<String> getOrgListByUserId(String userId) {
		return getSqlMapClientTemplate().queryForList(
				"station.getOrgListByUserId", userId);
	}
	
	public int getPermissionByUserId(String userId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"station.getPermissionByUserId", userId);
	}

}
