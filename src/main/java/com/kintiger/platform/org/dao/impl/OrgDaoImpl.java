package com.kintiger.platform.org.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.org.dao.IOrgDao;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.SapTOrgUnit;

public class OrgDaoImpl extends BaseDaoImpl implements IOrgDao {

	/**
	 * 查组织
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Borg> getOrgListByUserId(String userId) {
		String orgIds = (String) getSqlMapClientTemplate().queryForObject(
				"org.fnUserOrgList", userId);
		String[] orgIdarrs = orgIds.split(",");
		Borg borg = new Borg();
		borg.setOrgIdarrs(orgIdarrs);
		if (StringUtils.isNotEmpty(orgIds)) {
			return (List<Borg>) getSqlMapClientTemplate().queryForList(
					"org.getOrgListByOrgIds", borg);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getOrgTreeListByPorgId(String pOrgId) {
		Borg org = new Borg();
		org.setOrgParentId(Long.valueOf(pOrgId));
		return (List<Borg>) getSqlMapClientTemplate().queryForList(
				"org.getOrgTreeListByOrgId", org);
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getOrgTreeListByOrgId(String OrgId) {
		Borg org = new Borg();
		if (StringUtils.isNotEmpty(OrgId))
			org.setOrgId(Long.valueOf(OrgId));
		return (List<Borg>) getSqlMapClientTemplate().queryForList(
				"org.getOrgTreeListByOrgId", org);
	}

	public Borg getOrgByOrgId(String orgId) {
		Borg org = new Borg();
		org.setOrgId(Long.valueOf(orgId));
		return (Borg) getSqlMapClientTemplate().queryForObject(
				"org.getOrgByOrgId", org);
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getCompanyList(Borg borg) {
		return (List<Borg>) getSqlMapClientTemplate().queryForList(
				"org.getCompanyList", borg);
	}

	public Borg getCompanyName(Borg borg) {
		return (Borg) getSqlMapClientTemplate().queryForList(
				"org.getCompanyList", borg);
	}

	@SuppressWarnings("unchecked")
	public List<SapTOrgUnit> getSapTOrgUnitListByPId(String PID) {
		SapTOrgUnit sapTOrgUnit = new SapTOrgUnit();
		sapTOrgUnit.setParentId(Long.valueOf(PID));
		return (List<SapTOrgUnit>) getSqlMapClientTemplate().queryForList(
				"org.getSapTOrgUnitListByPId", sapTOrgUnit);
	}

	public Long createOrg(Borg org) {
		return (Long) getSqlMapClientTemplate().insert("org.createOrg", org);
	}

	public int updateBorg(Borg borg) {
		return getSqlMapClientTemplate().update("org.updateBorg", borg);
	}

	public int dropBorg(Borg borg) {
		return getSqlMapClientTemplate().update("org.dropBorg", borg);
	}

	public int checkOrgCity(Long userId) {
	    return (Integer)getSqlMapClientTemplate().queryForObject(
	      "org.checkOrgCity", userId);
	  }
	
	public int deleteBorg(Borg borg) {
		return getSqlMapClientTemplate().update("org.deleteBorg", borg);
	}

	public int getCompanyListCount(Borg borg) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"org.getCompanyListCount", borg);
	}

	public String getFnAllChildStrOrg(String orgId) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"org.fnAllChildStrOrg", orgId);
	}

	public Borg getOrgByUserId(String userId) {
		return (Borg) getSqlMapClientTemplate().queryForObject(
				"org.getOrgByUserId", Long.valueOf(userId));
	}

	public Borg getOrgByLoginId(String loginId) {
		return (Borg) getSqlMapClientTemplate().queryForObject(
				"org.getOrgByLoginId", loginId);
	}

	public String getFnUserOrg(String userId) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"org.getFnUserOrg", userId);
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getAllParentOrgs(Long orgId) {
		return (List<Borg>) getSqlMapClientTemplate().queryForList(
				"org.getAllParentOrgs", orgId);
	}

	public int getOrgCount(Borg borg) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"org.getOrgCount", borg);
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getOrgList(Borg borg) {
		return (List<Borg>) getSqlMapClientTemplate().queryForList(
				"org.getOrgListByOrgIds", borg);
	}

	/**
	 * 获取组织名称 add by hfwu
	 * 
	 * @param org_id
	 * @return
	 */
	public String getorgname(Long org_id) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"org.getorgname", org_id);
	}

	/**
	 * 获取上级组织ID add by hfwu
	 * 
	 * @param org_id
	 * @return
	 */
	public Long getporgid(Long org_id) {
		return (Long) getSqlMapClientTemplate().queryForObject("org.getporgid",
				org_id);
	}

	public Long getorgid() {
		return (Long) getSqlMapClientTemplate().queryForObject("org.getorgid");
	}
	
	@SuppressWarnings("unchecked")
	public List<Borg> searchOrgTreeList(Borg borg) {
		return (List<Borg>) getSqlMapClientTemplate().queryForList(
				"org.searchOrgTreeList", borg);
	}

	public int getInAnotherOrgCount(String baseOrgId, String orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseOrgId", baseOrgId);
		map.put("orgId", orgId);
		return (Integer) getSqlMapClientTemplate().queryForObject("org.getInAnotherOrgCount", map);
	}

	public int getOrgCountByUserId(Borg borg) {
		return (Integer) getSqlMapClientTemplate().queryForObject("org.getOrgCountByUserId", borg);

	}

	public List<Borg> getOrgListByUserId(Borg borg) {
		return (List<Borg>) getSqlMapClientTemplate().queryForList(
				"org.getOrgListByUserId", borg);
	}

}
