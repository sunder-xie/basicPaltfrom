package com.kintiger.platform.position.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.position.dao.IPositionDao;
import com.kintiger.platform.position.pojo.Bposition;

public class PositionDaoImpl extends BaseDaoImpl implements IPositionDao {

	public int getPositionCount(Bposition position) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"position.getPositionCount", position);
	}

	@SuppressWarnings("unchecked")
	public List<Bposition> getPositionList(Bposition position) {
		return (List<Bposition>) getSqlMapClientTemplate().queryForList(
				"position.getPositionList", position);
	}

	@SuppressWarnings("unchecked")
	public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType) {
		return (List<CmsTbDictType>) getSqlMapClientTemplate().queryForList(
				"position.getCmsTbDictType", cmsTbDictType);
	}

	@SuppressWarnings("unchecked")
	public List<CmsTbDict> getCmsTbDictJoinTypeList(CmsTbDict cmsTbDict) {
		return (List<CmsTbDict>) getSqlMapClientTemplate().queryForList(
				"position.getCmsTbDictJoinType", cmsTbDict);
	}

	@SuppressWarnings("unchecked")
	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
		return (List<CmsTbDict>) getSqlMapClientTemplate().queryForList(
				"position.getCmsTbDict", cmsTbDict);
	}

	@SuppressWarnings("unchecked")
	public List<Bposition> getStaffamountPositionList(Long orgId) {
		return (List<Bposition>) getSqlMapClientTemplate().queryForList(
				"position.getStaffamountPosition", orgId);
	}

	@SuppressWarnings("unchecked")
	public List<Bposition> getAllStaffamountPosition(Long orgId) {
		return (List<Bposition>) getSqlMapClientTemplate().queryForList(
				"position.getAllStaffamountPosition", orgId);
	}

	public Long createPosition(Bposition bposition) {
		return (Long) getSqlMapClientTemplate().insert(
				"position.createPosition", bposition);
	}

	public Bposition getPositionObj(Bposition position) {
		return (Bposition) getSqlMapClientTemplate().queryForObject(
				"position.getPositionObj", position);
	}

	public int updatePosition(Bposition bposition) {
		return getSqlMapClientTemplate().update("position.updatePosition",
				bposition);
	}

	public int getPostCustNum(Long posId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"position.getPostCustNum", posId);
	}

	public int getDeviceNum(Long posId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"position.getDeviceNum", posId);
	}

	public int updateCustPos(Long posId) {
		return (Integer) getSqlMapClientTemplate().update(
				"position.updateCustPos", posId);
	}

	@SuppressWarnings("unchecked")
	public List<Bposition> getManagementList(Long orgId) {
		return (List<Bposition>) getSqlMapClientTemplate().queryForList(
				"position.getManagementList", orgId);
	}

	@SuppressWarnings("unchecked")
	public List<Bposition> getAssignedPosList(Long posId) {
		return (List<Bposition>) getSqlMapClientTemplate().queryForList(
				"position.getAssignedPosList", posId);
	}

	@SuppressWarnings("unchecked")
	public List<Bposition> getUnassignedPosList(Long orgId) {
		return (List<Bposition>) getSqlMapClientTemplate().queryForList(
				"position.getUnassignedPosList", orgId);
	}

	@SuppressWarnings("unchecked")
	public List<Bposition> getStaffamountPositionPosList(Bposition bposition) {
		return (List<Bposition>) getSqlMapClientTemplate().queryForList(
				"position.getStaffamountPositionBystaIdAndOId", bposition);
	}

	@SuppressWarnings("unchecked")
	public List<Bposition> getPositionsByEmpId(Long empId) {
		return (List<Bposition>) getSqlMapClientTemplate().queryForList(
				"position.getPositionsByEmpId", empId);
	}

	@SuppressWarnings("unchecked")
	public List<Bposition> getPosition(Bposition bposition) {
		return (List<Bposition>) getSqlMapClientTemplate().queryForList(
				"position.getPosition", bposition);
	}



	
	public Bposition getPositionByUseState(Bposition bposition1) {
		return (Bposition) getSqlMapClientTemplate().queryForObject("position.getPositionByUseState",
				bposition1);
	}

	@SuppressWarnings("unchecked")
	public List<Bposition> getPositionListByUseState(Bposition bposition1) {
		return getSqlMapClientTemplate().queryForList("position.getPositionListByUseState",
				bposition1);
	}

	public int updatePosition4User(Bposition bposition) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updatePosition4Position(Bposition bposition) {
		// TODO Auto-generated method stub
		return 0;
	}


}
