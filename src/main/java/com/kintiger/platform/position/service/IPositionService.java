package com.kintiger.platform.position.service;

import java.util.List;

import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.position.pojo.Bposition;

/**
 * 接口<br>
 * 包括職位維護 编制职位类型 一些不該出現的字典接口方法
 * 
 */
public interface IPositionService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "操作失败！";

	/**
	 * 
	 * @param bposition
	 * @return
	 */
	public int getPositionCount(Bposition position);

	/**
	 * 
	 * @param bposition
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
	 * taob写的，根据orgId得到该组织余下的编制职位类型
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Bposition> getStaffamountPositionList(Long orgId);

	/**
	 * 根据orgId得到该组织下的编制职位类型
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Bposition> getAllStaffamountPosition(Long orgId);

	/**
	 * 创建职位
	 * 
	 * @param bposition
	 * @return
	 */
	public String createPosition(Bposition bposition);

	/**
	 * 修改职位
	 * 
	 * @param bposition
	 * @return
	 */
	public Boolean updatePosition(Bposition bposition);

	/**
	 * 删除职位(做标记)
	 * 
	 * @param bposition
	 * @return
	 */
	public String deletePosition(Long posId);

	/**
	 * 获取职位对象
	 * 
	 * @param position
	 * @return
	 */
	public Bposition getPositionObj(Bposition position);

	/**
	 * 释放职位类型
	 * 
	 * @param bposition
	 * @return
	 */
	public String releasePosition(Bposition position);

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
	 * 判断职位是否有设备
	 * 
	 * @param posId
	 * @return
	 */
	public int getDeviceNum(Long posId);

	/**
	 * 根据参数获取职位信息 查询映射职位
	 * 
	 * @param bposition
	 * @return
	 */
	public List<Bposition> getPosition(Bposition bposition);

	public Boolean updatePosition4User(Bposition bposition);

	public Boolean updatePosition4Position(Bposition bposition);

	/**
	 * 根据Use_State查找职位对象
	 * 
	 * @param bposition1
	 * @return
	 */
	public Bposition getPositionByUseState(Bposition bposition1);

	public List<Bposition> getPositionListByUseState(Bposition bposition1);

}
