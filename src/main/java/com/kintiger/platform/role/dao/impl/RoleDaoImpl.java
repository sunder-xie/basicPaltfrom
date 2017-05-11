package com.kintiger.platform.role.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.role.dao.IRoleDao;
import com.kintiger.platform.role.pojo.Role;
import com.kintiger.platform.station.pojo.Station;

public class RoleDaoImpl extends BaseDaoImpl implements IRoleDao {
	
	public int getRoleCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getRoleCount", role);
	}
	
	public int getBORoleCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getBORoleCount", role);
	}
	
	public int getBORoleDetailCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getBORoleDetailCount", role);
	}
	
	
	public int getRoleCount1(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getRoleCount1", role);
	}
	
	public int getConRole(Role role){
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getConRole", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleList(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getRoleList", role);
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getBORoleList(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getBORoleList", role);
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getBODetailRoleList(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getBODetailRoleList", role);
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getConstraintList(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getConstraintList", role);
	}

	public int getRole1Count(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getRole1Count", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRole1List(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getRole1List", role);
	}
	public String createRole(Role role) {
		return (String) getSqlMapClientTemplate().insert("role.createRole",
				role);
	}
	
	public String createRoledt(Role role) {
		return (String) getSqlMapClientTemplate().insert("role.createRoledt",
				role);
	}

	public int updateRole(Role role) {
		return getSqlMapClientTemplate().update("role.updateRole", role);
	}

	public int deleteRole(Role role) {
		return getSqlMapClientTemplate().delete("role.deleteRole", role);
	}
	public int deleteRoledt(Role role) {
		return getSqlMapClientTemplate().delete("role.deleteRoledt", role);
	}

	public int getRole4ConpointCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getRole4ConpointCount", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRole4ConpointList(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getRole4ConpointList", role);
	}

	public int getRole4MenuCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getRole4MenuCount", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRole4MenuList(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getRole4MenuList", role);
	}

	public Role getRoleByRoleId(String roleId) {
		return (Role) getSqlMapClientTemplate().queryForObject(
				"role.getRoleByRoleId", roleId);
	}

	public int getSelectedRole4StationCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getSelectedRole4StationCount", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getSelectedRole4StationList(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getSelectedRole4StationList", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getSelectedRole4Station(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getSelectedRole4Station", role);
	}

	public String selectRole4Station(final Role role) {
		return (String) getSqlMapClientTemplate().execute(
				new SqlMapClientCallback() {
					StringBuilder sb = new StringBuilder();

					public Object doInSqlMapClient(SqlMapExecutor executor)
							throws SQLException {
						executor.startBatch();
						Role r = new Role();
						for (String s : role.getCodes()) {
							if (StringUtils.isNotEmpty(s)) {
								r.setRoleId(s);
								r.setStationId(role.getStationId());
								if (sb.length() != 0) {
									sb.append(",");
								}
								sb.append(executor.insert(
										"role.selectRole4Station", r));
							}
						}
						executor.executeBatch();
						return sb.toString();
					}
				});
	}

	public int deleteSelectedRole4Station(Role role) {
		return getSqlMapClientTemplate().delete(
				"role.deleteSelectedRole4Station", role);
	}

	public int getSelectedRole4PositionTypeCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getSelectedRole4PositionTypeCount", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getSelectedRole4PositionTypeList(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getSelectedRole4PositionTypeList", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getSelectedRole4PositionType(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getSelectedRole4PositionType", role);
	}

	public String selectRole4PositionType(final Role role) {
		return (String) getSqlMapClientTemplate().execute(
				new SqlMapClientCallback() {
					StringBuilder sb = new StringBuilder();

					public Object doInSqlMapClient(SqlMapExecutor executor)
							throws SQLException {
						executor.startBatch();
						Role r = new Role();
						for (String s : role.getCodes()) {
							if (StringUtils.isNotEmpty(s)) {
								r.setRoleId(s);
								r.setPositionTypeId(role.getPositionTypeId());
								if (sb.length() != 0) {
									sb.append(",");
								}
								sb.append(executor.insert(
										"role.selectRole4PositionType", r));
							}
						}
						executor.executeBatch();
						return sb.toString();
					}
				});
	}

	public int deleteSelectedRole4PositionType(Role role) {
		return getSqlMapClientTemplate().delete(
				"role.deleteSelectedRole4PositionType", role);
	}

	public List<Station> getPositionType4RoleList(Role role) {
		return getSqlMapClientTemplate().queryForList(
				"role.getPositionType4RoleList", role);
	}

	public int getPositionType4RoleCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"role.getPositionType4RoleCount", role);
	}




}
