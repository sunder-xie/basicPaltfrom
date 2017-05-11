package com.kintiger.platform.position.dao;

import java.util.List;

import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.position.pojo.Bposition;

public interface IPositionDao {

	/**
	 * 
	 * @param position
	 * @return
	 */
	public int getPositionCount(Bposition position);

	/**
	 * 
	 * @param position
	 * @return
	 */
	public List<Bposition> getPositionList(Bposition position);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getCmsTbDictJoinTypeList(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict);

	/**
	 * taob写的，逻辑复杂
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Bposition> getStaffamountPositionList(Long orgId);

	/**
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Bposition> getAllStaffamountPosition(Long orgId);

	/**
	 * 
	 * @param bposition
	 * @return
	 */
	public Long createPosition(Bposition bposition);

	/**
	 * 
	 * @param bposition
	 * @return
	 */
	public int updatePosition(Bposition bposition);

	/**
	 * 判断该职位上是否有挂客户
	 * 
	 * @param bposition
	 * @return
	 */
	public int getPostCustNum(Long posId);

	/**
	 * 判断该职位下是否有挂设备
	 * 
	 * @param bposition
	 * @return
	 */
	public int getDeviceNum(Long posId);

	/**
	 * 删除成功后要做更新cust表，逻辑问tub
	 * 
	 * @param posId
	 * @return
	 */
	public int updateCustPos(Long posId);

	/**
	 * 获取职位对象
	 * 
	 * @param position
	 * @return
	 */
	public Bposition getPositionObj(Bposition position);

	/**
	 * 返回办事处管理层职位列表
	 * 
	 * @param posId
	 * @return
	 */
	public List<Bposition> getManagementList(Long orgId);

	/**
	 * 已分配上级的职位
	 * 
	 * @param posId
	 * @return
	 */
	public List<Bposition> getAssignedPosList(Long posId);

	/**
	 * 未分配上级的职位
	 * 
	 * @param posId
	 * @return
	 */
	public List<Bposition> getUnassignedPosList(Long orgId);

	/**
	 * taob写的，逻辑复杂
	 * 
	 * @param bposition
	 * @return
	 */
	public List<Bposition> getStaffamountPositionPosList(Bposition bposition);

	/**
	 * 根据empId获取职位岗位信息（流程）
	 * 
	 * @param empId
	 * @return
	 */
	public List<Bposition> getPositionsByEmpId(Long empId);

	/**
	 * 
	 * @param bposition
	 * @return
	 */
	public List<Bposition> getPosition(Bposition bposition);

	
	public int updatePosition4User(Bposition bposition);
	
	
	
	public int updatePosition4Position(Bposition bposition);

	/**
	 * 根据Use_State查找职位对象
	 * @param bposition1
	 * @return
	 */
	public Bposition getPositionByUseState(Bposition bposition1);

	public List<Bposition> getPositionListByUseState(Bposition bposition1);

}
