package com.kintiger.platform.menu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.menu.dao.IMenuDao;
import com.kintiger.platform.menu.pojo.Menu;

public class MenuDaoImpl extends BaseDaoImpl implements IMenuDao {

	@SuppressWarnings("unchecked")
	public List<Menu> getMenuTreeList(Menu menu) {
		if ("A".equals(menu.getIsKuunrMenu())) {
			return (List<Menu>) getSqlMapClientTemplate().queryForList(
					"menu.getMenuKunnrTreeList", menu);
		} else {
			return (List<Menu>) getSqlMapClientTemplate().queryForList(
					"menu.getMenuTreeList", menu);
		}

	}

	public int getMenuCount(Menu menu) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"menu.getMenuCount", menu);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getMenuList(Menu menu) {
		return (List<Menu>) getSqlMapClientTemplate().queryForList(
				"menu.getMenuList", menu);
	}

	public Long createMenu(Menu menu) {
		return (Long) getSqlMapClientTemplate().insert("menu.createMenu", menu);
	}

	public int updateMenu(Menu menu) {
		return getSqlMapClientTemplate().update("menu.updateMenu", menu);
	}

	public Menu getMenuById(Long id) {
		return (Menu) getSqlMapClientTemplate().queryForObject(
				"menu.getMenuById", id);
	}

	public Menu getMenu(Menu menu) {
		return (Menu) getSqlMapClientTemplate().queryForObject("menu.getMenu",
				menu);
	}

	public int deleteMenu(Menu menu) {
		return getSqlMapClientTemplate().delete("menu.deleteMenu", menu);
	}

	public int getSelectedMenu4RoleCount(Menu menu) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"menu.getSelectedMenu4RoleCount", menu);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getSelectedMenu4RoleList(Menu menu) {
		return (List<Menu>) getSqlMapClientTemplate().queryForList(
				"menu.getSelectedMenu4RoleList", menu);
	}

	public int deleteSelectedMenu4Role(Menu menu) {
		return getSqlMapClientTemplate().delete("menu.deleteSelectedMenu4Role",
				menu);
	}

	public boolean checkSelectedMenu4Role(String roleId, Long menuId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("roleId", roleId);
		m.put("menuId", menuId);

		int c = (Integer) getSqlMapClientTemplate().queryForObject(
				"menu.checkSelectedMenu4Role", m);

		return c == 0 ? false : true;
	}

	public Long selectMenu4Role(String roleId, Long menuId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("roleId", roleId);
		m.put("menuId", menuId);

		return (Long) getSqlMapClientTemplate().insert("menu.selectMenu4Role",
				m);
	}

	public Long getParentMenuId4Role(String roleId, Long menuId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("roleId", roleId);
		m.put("menuId", menuId);

		return (Long) getSqlMapClientTemplate().queryForObject(
				"menu.getParentMenuId4Role", m);
	}

	@SuppressWarnings("unchecked")
	public List<Long> getChildMenuId4Role(String roleId, Long menuId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("roleId", roleId);
		m.put("menuId", menuId);

		return (List<Long>) getSqlMapClientTemplate().queryForList(
				"menu.getChildMenuId4Role", m);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> blurSearchMenu(Menu menu) {
		return (List<Menu>) getSqlMapClientTemplate().queryForList(
				"menu.blurSearchMenu", menu);
	}

	public int searchSsoCount(Menu menu) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"menu.searchSsoCount", menu);
	}

	public void createSso(Menu menu) {
		getSqlMapClientTemplate().insert("menu.createSso", menu);
	}

	public void updateSso(Menu menu) {
		getSqlMapClientTemplate().update("menu.updateSso", menu);
	}

	public void menuClickLog(Menu menu) {
		getSqlMapClientTemplate().insert("menu.menuClickLog", menu);
	}

}
