package com.kintiger.platform.menu.service;

import java.util.List;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.menu.pojo.Menu;

/**
 * 菜單接口<br>
 * 包括菜單維護 角色菜單維護
 * 
 * @author xujiakun
 * 
 */
public interface IMenuService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "操作失败,请稍后再试!";

	public static final String ERROR_INPUT_MESSAGE = "操作失败,输入有误!";

	public static final String ERROR_NULL_MESSAGE = "操作失败,单据已不存在!";

	public static final String MENU_REDIRECT_URL = "/menuAction!redirectMenu.jspa?node=";

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
	 * 創建菜單
	 * 
	 * @param menu
	 * @return
	 */
	public StringResult createMenu(Menu menu);

	/**
	 * 修改菜單
	 * 
	 * @param menu
	 * @return
	 */
	public StringResult updateMenu(Menu menu);

	/**
	 * 根据id查询菜单信息
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
	 * 刪除菜單
	 * 
	 * @param menu
	 * @return
	 */
	public StringResult deleteMenu(Menu menu);

	/**
	 * 查询角色关联菜单
	 * 
	 * @param menu
	 * @return
	 */
	public int getSelectedMenu4RoleCount(Menu menu);

	/**
	 * 查询角色关联菜单
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getSelectedMenu4RoleList(Menu menu);

	/**
	 * 选择角色菜单
	 * 
	 * @param menu
	 * @return
	 */
	public StringResult selectMenu4Role(Menu menu);

	/**
	 * 删除角色关联菜单
	 * 
	 * @param menu
	 * @return
	 */
	public StringResult deleteSelectedMenu4Role(Menu menu);

	/**
	 * 模糊搜索menu
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> blurSearchMenu(Menu menu);

	/**
	 * 维护单点登录配置
	 * 
	 * @param menu
	 */
	public void createOrUpdateSso(Menu menu);

	/**
	 * 菜单点击日志
	 * 
	 * @param menu
	 */
	public void menuClickLog(Menu menu);

}
