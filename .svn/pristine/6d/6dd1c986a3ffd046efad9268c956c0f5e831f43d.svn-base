package com.kintiger.platform.menu.dao;

import java.util.List;

import com.kintiger.platform.menu.pojo.Menu;

public interface IMenuDao {

	/**
	 * 菜单树
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getMenuTreeList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int getMenuCount(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getMenuList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public Long createMenu(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int updateMenu(Menu menu);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Menu getMenuById(Long id);

	/**
	 * 根据menu查询菜单信息
	 * 
	 * @param menu
	 * @return
	 */
	public Menu getMenu(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int deleteMenu(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int getSelectedMenu4RoleCount(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getSelectedMenu4RoleList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public int deleteSelectedMenu4Role(Menu menu);

	/**
	 * 验证角色+菜单是否已经存在; true: 存在
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public boolean checkSelectedMenu4Role(String roleId, Long menuId);

	/**
	 * 创建角色菜单
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public Long selectMenu4Role(String roleId, Long menuId);

	/**
	 * 根据菜单menuId 获取父级角色菜单menuId
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public Long getParentMenuId4Role(String roleId, Long menuId);

	/**
	 * 根据菜单menuId 获取子级角色菜单menuId
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public List<Long> getChildMenuId4Role(String roleId, Long menuId);

	/**
	 * 模糊搜索menu
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> blurSearchMenu(Menu menu);

	/**
	 * 查詢单点登录配置
	 * 
	 * @param menu
	 */
	public int searchSsoCount(Menu menu);

	/**
	 * 维护单点登录配置
	 * 
	 * @param menu
	 */
	public void createSso(Menu menu);

	/**
	 * 维护单点登录配置
	 * 
	 * @param menu
	 */
	public void updateSso(Menu menu);

	/**
	 * 菜单点击日志
	 * 
	 * @param menu
	 */
	public void menuClickLog(Menu menu);

}
