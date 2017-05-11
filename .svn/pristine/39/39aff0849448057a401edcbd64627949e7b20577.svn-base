package com.kintiger.platform.position.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.position.dao.IPositionTypeDao;
import com.kintiger.platform.position.pojo.BpositionType;

public class PositionTypeDaoImpl extends BaseDaoImpl implements
		IPositionTypeDao {

	public int getPositionTypesCount(BpositionType positionType) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"positionType.getpositionTypeCount", positionType);
	}

	@SuppressWarnings("unchecked")
	public List<BpositionType> getPositionTypesList(BpositionType positionType) {
		return (List<BpositionType>) getSqlMapClientTemplate().queryForList(
				"positionType.getpositionTypeList", positionType);
	}

	@SuppressWarnings("unchecked")
	public List<BpositionType> exportPositionTypesList(
			BpositionType positionType) {
		return (List<BpositionType>) getSqlMapClientTemplate().queryForList(
				"positionType.exportpositionTypeList", positionType);
	}

	public BpositionType getPositionTypes(BpositionType positionType) {
		return (BpositionType) getSqlMapClientTemplate().queryForObject(
				"positionType.getPositionTypes", positionType);
	}

	public int updatePositionTypes(BpositionType positionType) {
		return (Integer) getSqlMapClientTemplate().update(
				"positionType.updatePositionTypes", positionType);
	}

	public Long createPositionTypes(BpositionType positionType) {
		return (Long) getSqlMapClientTemplate().insert(
				"positionType.createPositionTypes", positionType);
	}

	public int getPositionType4RoleCount(BpositionType positionType) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"positionType.getPositionType4RoleCount", positionType);
	}

	public boolean insertPos(BpositionType positionType) {
		Long l = (Long) getSqlMapClientTemplate().insert(
				"positionType.createPositionTypes", positionType);
		if (l != null && l != 0l) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BpositionType> getPositionType4RoleList(
			BpositionType positionType) {
		return (List<BpositionType>) getSqlMapClientTemplate().queryForList(
				"positionType.getPositionType4RoleList", positionType);
	}

}
