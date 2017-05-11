package com.kintiger.platform.station.dao;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.station.pojo.StationOrg;
import com.kintiger.platform.station.pojo.StationRole;

public interface IStationDao {


	public int getStationJsonListCount(Station s);

	public List<Station> getStationJsonList(Station s);

	public Station getStationEntity(String stationId);

	public int updateStationEntity(Station s);
	public List<Borg> getCompanyJsonList() ;
	public List<Station> getStationUser(String stationId);

	public List<StationRole> getStationRole(String stationId);

	public int deleteStationEntity(String stationId);

	public String createStationEntity(Station s);

	public int searchStationUserCount(Station s);
	public int getStaffStationCount(Station s);
	public List<Station> searchStationUser(Station s);
	public int getStaffStationAmount(Station s);
	public int deleteStationUser(Station s);
	public List<Station> searchUser(Station s);

	public int searchUserCount(Station s);

	public Long insertStationUser(Station s);

	public int getStationUserCount(String stationId);
	public List<CmsTbDict> getCustTypeList();

	public int getStationRoleCount(String stationId);
	
	public List<Station> getStationByEmpId(Long empId);

	public List<Station> getStationJsonListForSelect(Station station);

	public int getStationJsonListCountForSelect(Station station);

	public int updateStationUser(AllUsers allUsers);

	public int getStationCountByEmpId(AllUsers allUsers);

	public int searchStationUserMoreCount(Station station);

	public List<Station> searchStationUserMore(Station station);

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
	 * 查询获取岗位是否已经配置
	 * @param s
	 * @return
	 */
	public int getStationOrgCount(StationOrg s);

	/**
	 * 根据UserId查询主次岗位的组织
	 * @param empId
	 * @return
	 */
	public List<String> getOrgListByUserId(String userId);

	/***
	 * 检测是否查看所有组织权限
	 * 角色名：全部组织查看
	 * 角色id：org_search_all
	 * @param s
	 * @return
	 */
	public int getPermissionByUserId(String userId);
	
}
