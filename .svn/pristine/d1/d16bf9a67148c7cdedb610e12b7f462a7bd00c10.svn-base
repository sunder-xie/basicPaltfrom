package com.kintiger.platform.conpoint.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.conpoint.dao.IConpointDao;
import com.kintiger.platform.conpoint.pojo.Conpoint;


public class ConpointDaoImpl extends BaseDaoImpl implements IConpointDao {

	public int getConpointCount(Conpoint p) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"conpoint.getConpointCount", p);
	}

	@SuppressWarnings("unchecked")
	public List<Conpoint> getConpointList(Conpoint p) {
		return (List<Conpoint>) getSqlMapClientTemplate().queryForList(
				"conpoint.getConpointList", p);
	}

	public int deleteConpoint(BigDecimal conpointId) {
		return getSqlMapClientTemplate().update("conpoint.deleteConpoint",
				conpointId);
	}

	public Conpoint getConpointMenuPojo(BigDecimal conpointId) {
		return (Conpoint) getSqlMapClientTemplate().queryForObject(
				"conpoint.getConpointMenuPojo", conpointId);
	}

	public int modifyConpoint(Conpoint p) {
		return getSqlMapClientTemplate().update("conpoint.modifyConpoint", p);
	}

	public Object createConpoint(Conpoint c) {
		return getSqlMapClientTemplate().insert("conpoint.createConpoint", c);
	}

	public Conpoint isAut(Conpoint conpoint) {
		return (Conpoint) getSqlMapClientTemplate().queryForObject(
				"conpoint.isAut", conpoint);
	}

	@SuppressWarnings("unchecked")
	public List<Conpoint> getPermissions(Conpoint conpoint) {
		return (List<Conpoint>) getSqlMapClientTemplate().queryForList(
				"conpoint.getPermissions", conpoint);
	}

	public int getRoleConpointCount(Conpoint conpoint) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"conpoint.getRoleConpointCount", conpoint);
	}

	@SuppressWarnings("unchecked")
	public List<Conpoint> getRoleConpointList(Conpoint conpoint) {
		return (List<Conpoint>) getSqlMapClientTemplate().queryForList(
				"conpoint.getRoleConpointList", conpoint);
	}

	public Long createRoleConpoint(Conpoint conpoint) {
		return (Long) getSqlMapClientTemplate().insert(
				"conpoint.createRoleConpoint", conpoint);
	}

	public int updateRoleConpoint(Conpoint conpoint) {
		return getSqlMapClientTemplate().update("conpoint.updateRoleConpoint",
				conpoint);
	}

	public Conpoint getRoleConpointById(Long roleConpointId) {
		return (Conpoint) getSqlMapClientTemplate().queryForObject(
				"conpoint.getRoleConpointById", roleConpointId);
	}

	public int deleteConpoints(Conpoint p) {
		return getSqlMapClientTemplate().delete("conpoint.deleteConpoints", p);
	}

	@SuppressWarnings("unchecked")
	public List<Conpoint> getConpointListJson(Conpoint p) {
		return (List<Conpoint>) getSqlMapClientTemplate().queryForList(
				"conpoint.getConpointListJson", p);
	}

	public int getRolesByConpointId(Conpoint c) {
		return (Integer)getSqlMapClientTemplate().queryForObject("conpoint.getRolesByConpointId",c);
	}

	@SuppressWarnings("unchecked")
	public List<Conpoint> getConpointListIsExit(Conpoint p) {
		return (List<Conpoint>) getSqlMapClientTemplate().queryForList(
				"conpoint.getConpointListIsExit", p);
	}

	
}
