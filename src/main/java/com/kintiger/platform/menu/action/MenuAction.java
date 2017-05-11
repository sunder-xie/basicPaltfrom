package com.kintiger.platform.menu.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.EncryptUtil;
import com.kintiger.platform.menu.pojo.Menu;
import com.kintiger.platform.menu.service.IMenuService;

/**
 * Menu
 * 
 * 
 */
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = 7380054609278309516L;

	private static final Log logger = LogFactory.getLog(MenuAction.class);

	private IMenuService menuService;
	private List<Menu> menuList;
	private int total = 0;
	@Decode
	private String id;
	private String pid;
	private Menu menu;

	/**
	 * �Ƿ����ڿ�project��ת��ַ
	 */
	private String isRedirect;

	/**
	 * �ϼ��˵�����
	 */
	@Decode
	private String pname;

	/**
	 * �˵�����
	 */
	@Decode
	private String name;
	/**
	 * ��ɫid
	 */
	@Decode
	private String roleId;
	@Decode
	private String menuIds;
	/**
	 * �˵�id
	 */
	private String node;
	/**
	 * �˵���ת��ַ
	 */
	private String redirectUrl;

	/**
	 * ʱ���
	 */
	private String timestamp;

	private String ids;

	private static final String DATEFULLTIME_FORMAT = "yyyyMMdd";

	// private ICAService caService;
	private String params;

	/**
	 * ��ѯ�˵�
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchMenu() {
		return "searchMenu";
	}

	/**
	 * �˵�����
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "menuList", include = { "id", "pid", "name", "pname", "url", "target", "redirectUrl" }, total = "total")
	public String getMenuJsonList() {
		menuList = new ArrayList<Menu>();
		Menu m = new Menu();
		try {
			m.setStart(getStart());
			m.setEnd(getEnd());
			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(id.trim())) {
				m.setId(Long.parseLong(id.trim()));
			}
			if (StringUtils.isNotEmpty(pid) && StringUtils.isNotEmpty(pid.trim())) {
				m.setPid(Long.parseLong(pid.trim()));
			}
			if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(name.trim())) {
				m.setName(name.trim());
			}
			if (StringUtils.isNotEmpty(pname) && StringUtils.isNotEmpty(pname.trim())) {
				m.setPname(pname.trim());
			}

		} catch (Exception e) {
			logger.error("id:" + id + "pid:" + pid + "name:" + this.getName() + "pname:" + pname, e);
		}
		total = menuService.getMenuCount(m);
		if (total != 0) {
			menuList = menuService.getMenuList(m);
		} else {
			menuList = null;
		}
		return JSON;
	}

	/**
	 * �˵�����
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "menuList", include = { "id", "pid", "name", "pname", "url", "target", "redirectUrl" }, total = "total")
	public String getMenuJsonListforRole() {
		menuList = new ArrayList<Menu>();
		Menu m = new Menu();
		try {
			m.setStart(getStart());
			m.setEnd(getEnd());
			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(id.trim())) {
				id = new String(this.getId().getBytes("ISO8859-1"), "utf-8");
				boolean isNum = id.matches("[0-9]+");
				if (isNum) {
					m.setId(Long.parseLong(id.trim()));
					// m.setName(id.trim());
				} else {
					m.setName(id.trim());
				}
			}

		} catch (Exception e) {
			logger.error("id:" + id + "pid:" + pid + "name:" + this.getName() + "pname:" + pname, e);
		}
		total = menuService.getMenuCount(m);
		if (total != 0) {
			menuList = menuService.getMenuList(m);
		} else {
			menuList = null;
		}
		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "menuList", include = { "pid", "pname" })
	public String blurSearchMenu() {
		menuList = new ArrayList<Menu>();
		Menu m = new Menu();
		if (StringUtils.isNotEmpty(pname) && StringUtils.isNotEmpty(pname.trim())) {
			try {
				pname = new String(getServletRequest().getParameter("pname").getBytes("ISO8859-1"), "UTF-8");
				m.setName(pname.trim());
				menuList = menuService.blurSearchMenu(m);
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
		}
		return JSON;
	}

	/**
	 * validate
	 * 
	 * @param menu
	 * @return
	 */
	@PermissionSearch
	private boolean validate(Menu menu) {

		if (menu == null) {
			return false;
		}
		if (menu.getPid() == null || StringUtils.isEmpty(menu.getName()) || StringUtils.isEmpty(menu.getName().trim())
			|| StringUtils.isEmpty(menu.getTarget()) || StringUtils.isEmpty(menu.getTarget().trim())) {
			return false;
		}

		return true;
	}

	@PermissionSearch
	public String createMenuPrepare() {
		return CREATE_PREPARE;
	}

	/**
	 * �����˵�
	 * 
	 * @return
	 */
	public String createMenu() {
		if (!validate(menu)) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}
		if ("y".equals(isRedirect)) {
			menu.setRedirectUrl(menu.getUrl());
			menu.setUrl(IMenuService.MENU_REDIRECT_URL);
		}
		if ("NA".equals(menu.getTarget())) {
			menu.setRedirectUrl("");
			menu.setUrl("");
		}

		StringResult result = menuService.createMenu(menu);
		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("�ѳɹ������˵�!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * �޸�/��ѯ�˵���Ϣ
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateMenuPrepare() {

		if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(id.trim())) {
			try {
				menu = menuService.getMenuById(Long.parseLong(id));
				return UPDATE_PREPARE;
			} catch (Exception e) {
				logger.error(id, e);
			}
		}

		menu = new Menu();
		return UPDATE_PREPARE;
	}

	public String updateMenu() {

		if (!validate(menu)) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		if ("y".equals(isRedirect)) {
			menu.setRedirectUrl(menu.getUrl());
			menu.setUrl(IMenuService.MENU_REDIRECT_URL + menu.getId());
		}

		StringResult result = menuService.updateMenu(menu);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("�ѳɹ��޸Ĳ˵�!");
		}

		return RESULT_MESSAGE;
	}

	public String deleteMenu() {
		String[] l = ids.split(",");
		menu = new Menu();
		menu.setCodes(l);
		StringResult result = menuService.deleteMenu(menu);
		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("�ѳɹ�ɾ��" + result.getResult() + "���˵�!δɾ���˵��¹��н�ɫ���Ӳ˵�");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ��ɫ�˵�
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchSelectedMenu4Role() {

		if (StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(roleId.trim())) {

			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}

		return "searchSelectedMenu4Role";
	}

	/**
	 * ��ӽ�ɫ�˵�����ѡ��˵�
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchSelectedMenu4Add() {
		return "searchSelectedMenu4Add";
	}

	/**
	 * ��ɫ�˵�����
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "menuList", include = { "roleMenuId", "id", "pid", "roleId", "name", "url", "target",
		"redirectUrl" }, total = "total")
	public String getSelectedMenu4RoleJsonList() {
		Menu m = new Menu();
		try {
			m.setStart(getStart());
			m.setEnd(getEnd());
			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(id.trim())) {
				m.setId(Long.parseLong(id.trim()));
			}
			if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(name.trim())) {
				m.setName(this.getName().trim());
			}
			m.setRoleId(roleId.trim());

		} catch (Exception e) {
			logger.error("id:" + id + "name:" + this.getName() + "roleId" + roleId, e);
		}
		total = menuService.getSelectedMenu4RoleCount(m);
		if (total != 0) {
			menuList = menuService.getSelectedMenu4RoleList(m);
		} else {
			menuList = null;
		}

		return JSON;
	}

	/**
	 * ������ɫ�˵�
	 * 
	 * @return
	 */
	public String selectMenu4Role() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		Menu m = new Menu();
		if (StringUtils.isNotEmpty(menuIds) && StringUtils.isNotEmpty(menuIds.trim())) {
			m.setCodes(menuIds.split(","));
		}

		if (StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(roleId.trim())) {
			m.setRoleId(roleId.trim());
		}

		StringResult result = menuService.selectMenu4Role(m);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		if (IMenuService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("�ɹ�ά����ɫ�˵���ţ�" + result.getResult());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * ɾ����ɫ�˵�
	 * 
	 * @return
	 */
	public String deleteSelectedMenu4Role() {
		String[] l = ids.split(",");
		menu = new Menu();
		menu.setCodes(l);
		menu.setRoleId(roleId);
		StringResult result = menuService.deleteSelectedMenu4Role(menu);
		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("�ѳɹ�ɾ��" + result.getResult() + "����ɫ�˵�!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ϵͳ�˵���ת
	 * 
	 * @return
	 */
	@PermissionSearch
	public String redirectMenu() {
		if (StringUtils.isNotEmpty(node) && StringUtils.isNotEmpty(node.trim())) {
			try {
				Menu m = new Menu();
				m.setUserId(getUser().getUserId());
				m.setId(Long.parseLong(node));
				Menu menu = menuService.getMenu(m);
				redirectUrl = menu.getRedirectUrl();
				if (redirectUrl.indexOf("sso=y") > 0) {
					// ƴ���˺�
					// ( ��ƮƮ263�����˺�ȡ���ݿ�)
					if (redirectUrl.indexOf("type=xpp263&") > 0) {
						if (StringUtils.isNotEmpty(this.getUser().getEmail())) {
							redirectUrl = redirectUrl + "&uid=" + this.getUser().getEmail().split("@")[0];
						}
					} else if (StringUtils.isNotEmpty(menu.getSsoUser())) {
						redirectUrl = redirectUrl + "&" + menu.getSsoUser();
					}
					// ƴ������
					if (StringUtils.isNotEmpty(menu.getSsoPwd())) {
						redirectUrl = redirectUrl + "&" + menu.getSsoPwd();
					}

					// post��֤�ύ
					if ("post".equals(menu.getValidateType())) {
						String kv[] = redirectUrl.split("\\?");
						redirectUrl = kv[0];
						params = kv[1];
						return "menuRedirect2post";
					} else {
						// ��ƮƮ263������֤��MD5���� ��ʱӲ����
						if (redirectUrl.indexOf("type=xpp263&") > 0) {
							String kv[] = redirectUrl.split("type=xpp263&");
							String sign = "sign=" + EncryptUtil.md5Encry(kv[1] + "&key=2Tu6Iq7Acs4");
							redirectUrl = redirectUrl + "&" + sign;
							redirectUrl = redirectUrl.replace("sso=y&type=xpp263&", "");
						}
					}
				}
				// sso();
			} catch (Exception e) {
				logger.error(node, e);
			}
		}

		if (StringUtils.isEmpty(redirectUrl)) {
			redirectUrl = "/";
		}
		return "menuRedirect";
	}

	/**
	 * �����¼����ת
	 * 
	 * @return
	 */
	@PermissionSearch
	public String ssoPre() {
		return "ssoPre";
	}

	/**
	 * �����¼��ѯ
	 * 
	 * @return
	 */
	@JsonResult(field = "menuList", include = { "id", "name", "redirectUrl", "validateType", "ssoUser", "ssoPwd" })
	public String ssoSearch() {
		Menu menu = new Menu();
		menu.setPagination("false");
		menu.setPname("�ҵ�ϵͳ");
		menu.setUserId(getUser().getUserId());
		menuList = menuService.getMenuList(menu);
		return JSON;
	}

	/**
	 * �����¼������ת
	 * 
	 * @return
	 */
	@PermissionSearch
	public String ssoCreatePre() {
		Menu m = new Menu();
		m.setUserId(getUser().getUserId());
		m.setId(Long.parseLong(id));
		menu = menuService.getMenu(m);
		return "ssoCreatePre";
	}

	/**
	 * �����¼����
	 * 
	 * @return
	 */
	@PermissionSearch
	public String ssoCreate() {
		menu.setUserId(getUser().getUserId());
		menuService.createOrUpdateSso(menu);
		this.setSuccessMessage("ok");
		return RESULT_MESSAGE;

	}

	/**
	 * ���token
	 * 
	 * @param redirectUrl
	 */
	/*
	 * private void sso() { AllUsers alluser = this.getUser();
	 * 
	 * try { // ��תʱ��� timestamp = EncryptUtil.md5Encry(DateUtil
	 * .getNowDatetimeStr(DATEFULLTIME_FORMAT));
	 * 
	 * token = caService.generateToken(alluser); } catch (Exception e) {
	 * logger.error(e); } }
	 */

	public void menuClickLog() {
		Menu m = new Menu();
		m.setSsoUser(getUser().getLoginId());
		m.setName(name);
		m.setRedirectUrl(redirectUrl);
		menuService.menuClickLog(m);
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getIsRedirect() {
		return isRedirect;
	}

	public void setIsRedirect(String isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
