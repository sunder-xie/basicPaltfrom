package com.kintiger.platform.station.service;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.station.pojo.StationOrg;
import com.kintiger.platform.station.pojo.StationRole;

public interface IStationService {


	public int getStationJsonListCount(Station s);
	public int getStaffStationCount(Station s);
	public int getStaffStationAmount(Station s);
	public List<Station> getStationJsonList(Station s);

	public Station getStationEntity(String stationId);

	public int updateStationEntity(Station s);

	public List<Station> getStationUser(String stationId);
	public List<Borg> getCompanyJsonList();
	/**
	 * 根据stationId查询station_user用户数
	 * 
	 * @param stationId
	 * @return
	 */
	public int getStationUserCount(String stationId);

	public List<StationRole> getStationRole(String stationId);
	public List<CmsTbDict> getCustTypeList();
	/**
	 * 根据stationId查询cms_tb_station_role角色数
	 * 
	 * @param stationId
	 * @return
	 */
	public int getStationRoleCount(String stationId);

	public int deleteStationEntity(String stationId);

	public String createStationEntity(Station s);

	public int searchStationUserCount(Station s);

	public List<Station> searchStationUser(Station s);

	public int deleteStationUser(Station s);


	public List<Station> searchUser(Station s);

	public int searchUserCount(Station s);

	public Long insertStationUser(Station s);
	
	/**
	 * 根据UserId查询岗位
	 * @param empId
	 * @return
	 */
	public List<Station> getStationByEmpId(Long empId);
	/**
	 * 根据UserId查询主次岗位的组织
	 * @param empId
	 * @return
	 */
	public List<String> getOrgListByUserId(String userId);

	public int getStationJsonListCountForSelect(Station station);

	public List<Station> getStationJsonListForSelect(Station station);
	/**
	 * 根据组织，岗位ID去更新station_user里的人员ID，，把人员和岗位对应起来
	 * @param allUsers
	 * @return
	 */
	public BooleanResult updateStationUser(AllUsers allUsers);
	/**
	 * 根据人员ID，查找改人员是否分配了岗位
	 * @param allUsers
	 * @return
	 */
	public String getStationCountByEmpId(AllUsers allUsers);
	public int searchStationUserMoreCount(Station station);
	public List<Station> searchStationUserMore(Station station);
	/**
	 * 释放人员和岗位之间的关系
	 * @param station
	 * @return
	 */
	public int deleteStationUserById(Station station);
	
	/***
	 * 创建岗位和组织关系表
	 * @param s
	 * @return
	 */
	public Long createStationOrg(StationOrg s);
	/***
	 * 查询岗位和组织关系列表
	 * @param s
	 * @return
	 */
	public List<StationOrg> getStationOrgList(StationOrg s);
	/***
	 * 修改岗位和组织关系表
	 * @param s
	 * @return
	 */
	public int updateStationOrg(StationOrg s);
	/***
	 * 检测是否查看所有组织权限
	 * 角色名：全部组织查看
	 * 角色id：org_search_all
	 * @param s
	 * @return
	 */
	public int getPermissionByUserId(String userId);


}
