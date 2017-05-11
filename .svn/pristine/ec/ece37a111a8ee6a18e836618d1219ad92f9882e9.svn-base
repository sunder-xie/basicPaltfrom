package com.kintiger.platform.menu.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.menu.pojo.Menu;
import com.kintiger.platform.menu.pojo.Tree4Ajax;
import com.kintiger.platform.menu.service.IMenuService;

/**
 * MenuTreeAjax
 * 
 * 
 */
public class MenuTreeAjaxAction extends BaseAction {

	private static final long serialVersionUID = -785317530988895673L;

	private static final Log logger = LogFactory
			.getLog(MenuTreeAjaxAction.class);
	private IMenuService menuService;
	private String node;
	private List<Tree4Ajax> treeList;
	private String flag;

	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state",
			"target", "attributes", "redirectAttributes", "orderBy", "isFirst" })
	public String getMenuTreeListByAjax() {
		List<Menu> menuList = null;
		treeList = new ArrayList<Tree4Ajax>();
		if (StringUtils.isNotEmpty(node)) {
			Menu menu = new Menu();
			AllUsers alluser = this.getUser();
			try {
				// 硬编码,系统初始化!admin登录即拥有素有菜单
				if (!"admin".equals(alluser.getLoginId())) {
					menu.setUserId(alluser.getUserId());
					if ("A".equals(alluser.getCustType())) {
						menu.setIsKuunrMenu("A");
					}

				}
				menu.setFlag(flag);
				menu.setPid(Long.parseLong(node));
				menuList = menuService.getMenuTreeList(menu);

			} catch (Exception e) {
				logger.error(LogUtil.parserBean(menu), e);
			}
		}
		if (menuList == null || menuList.size() == 0) {
			return JSON;
		}

		for (Menu menu : menuList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(menu.getId()));
			tree.setText(menu.getName());
			tree.setOrderBy(menu.getOrderBy());
			if (StringUtils.isBlank(menu.getUrl())) {
				tree.setAttributes("");
			} else {
				if (menu.getUrl().indexOf("menuAction!redirectMenu") > 0) {
					tree.setRedirectAttributes(menu.getRedirectUrl());
				}
				tree.setAttributes(menu.getUrl());
			}

			// 首页展示
			if ("Y".equals(menu.getIsFirst())) {
				tree.setIsFirst("Y");
			}
			tree.setTarget(menu.getTarget());
			if ("NA".equals(tree.getTarget())) {
				tree.setState("closed");
			}
			if ("_blank".equals(tree.getTarget())) {
				tree.setAttributes(tree.getAttributes() + "#_blank");
			}
			treeList.add(tree);
		}

		return JSON;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
