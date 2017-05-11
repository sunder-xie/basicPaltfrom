package com.kintiger.platform.menu.pojo;

import java.util.List;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 菜单实体
 * 
 * @author xujiakun
 * 
 */
public class Menu extends SearchInfo {

	private static final long serialVersionUID = -3155216126617302268L;

	/**
	 * 菜单id
	 */
	private Long id;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 父级菜单id
	 */
	private Long pid;

	/**
	 * 父级菜单名称
	 */
	private String pname;

	private String url;

	private String target;

	private int orderBy;

	private String userId;

	private String isFirst;// 是否首先展示

	/**
	 * 是否为经销商菜单
	 */
	private String isKuunrMenu;

	/**
	 * 是否为办事处菜单
	 */
	private String isOfficeMenu;

	/**
	 * 是否为水站菜单
	 */
	private String isClientMenu;

	/**
	 * 是否为客户菜单
	 */
	private String isCustMenu;

	/**
	 * 跨project跳转地址
	 */
	private String redirectUrl;

	/**
	 * 角色id
	 */
	private String roleId;

	/**
	 * 角色菜单id
	 */
	private Long roleMenuId;

	private List<Long> ids;

	private String ssoUser;// 单点登录账号形参

	private String ssoPwd;// 密码形参

	private String flag;// 菜单显示权限标示

	private String validateType;

	public String getValidateType() {
		return validateType;
	}

	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSsoUser() {
		return ssoUser;
	}

	public void setSsoUser(String ssoUser) {
		this.ssoUser = ssoUser;
	}

	public String getSsoPwd() {
		return ssoPwd;
	}

	public void setSsoPwd(String ssoPwd) {
		this.ssoPwd = ssoPwd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsKuunrMenu() {
		return isKuunrMenu;
	}

	public void setIsKuunrMenu(String isKuunrMenu) {
		this.isKuunrMenu = isKuunrMenu;
	}

	public String getIsOfficeMenu() {
		return isOfficeMenu;
	}

	public void setIsOfficeMenu(String isOfficeMenu) {
		this.isOfficeMenu = isOfficeMenu;
	}

	public String getIsClientMenu() {
		return isClientMenu;
	}

	public void setIsClientMenu(String isClientMenu) {
		this.isClientMenu = isClientMenu;
	}

	public String getIsCustMenu() {
		return isCustMenu;
	}

	public void setIsCustMenu(String isCustMenu) {
		this.isCustMenu = isCustMenu;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Long getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(Long roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

}
