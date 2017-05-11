package com.kintiger.platform.conpoint.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.conpoint.pojo.Conpoint;
import com.kintiger.platform.conpoint.service.IConpointService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.menu.pojo.Menu;
import com.kintiger.platform.menu.service.IMenuService;

public class ConPointAction extends BaseAction {

	private static final long serialVersionUID = -8879856863207897141L;

	private static final Log logger = LogFactory.getLog(ConPointAction.class);

	private int total = 0;
	private BigDecimal conpointId;

	@Decode
	private String conpointName;
	private String conpointNum;
	private List<Conpoint> conpointList;
	private IConpointService conpointService;
	private Conpoint point;
	private Long menuId;
	private String ids;
	private String menuName;
	private List<Menu> menuList;

	/**
	 * 角色id
	 */
	@Decode
	private String roleId;

	/**
	 * 角色权限点id
	 */
	private String roleConpointId;

	@PermissionSearch
	public String forOpenConpoint() {
		return "searchConpoint";
	}

	@PermissionSearch
	@JsonResult(field = "conpointList", include = { "conpointId",
			"conpointName", "conpointNum", "menuId", "menuName" }, total = "total")
	public String searchConpoint() {
		Conpoint p = new Conpoint();
		p.setStart(getStart());
		p.setEnd(getEnd());
		p.setConpointId(conpointId);
		p.setConpointName(conpointName);
		p.setConpointNum(conpointNum);

		total = conpointService.getConpointCount(p);
		if (total != 0) {
			conpointList = conpointService.getConpointList(p);
		} else {
			conpointList = null;
		}
		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "conpointList", include = { "conpointId",
			"conpointName", "conpointNum", "menuId", "menuName" })
	public String searchConpointForJson() {
		Conpoint con = new Conpoint();
		if (StringUtils.isNotEmpty(conpointName)
				&& StringUtils.isNotEmpty(conpointName.trim())) {

			try {
				conpointName = new String(conpointName.trim().getBytes(
						"ISO8859-1"), "UTF-8");
				con.setConpointName(conpointName);
			} catch (UnsupportedEncodingException e) {
				logger.error(conpointName, e);
			}
		}
		conpointList = conpointService.getConpointListJson(con);
		return JSON;
	}

	@SuppressWarnings("unused")
	public String deleteConpoint() {
		String[] l = ids.split(",");
		point = new Conpoint();
		String str = "";
		String pstr = "";
		for (int i = 0; i < l.length; i++) {
			Conpoint p = new Conpoint();
			p.setConpointId(BigDecimal.valueOf(Long.valueOf(l[i])));
			int r = conpointService.getRolesByConpointId(p);
			if (r > 0) {
				str = l[i] + "," + str;
			} else {
				pstr = l[i] + "," + pstr;
			}
		}
		String l1 = "";
		String[] l2 = null;
		if (null != pstr) {
			l2 = pstr.split(",");
			point.setCodes(l2);

		}
		StringResult result = conpointService.deleteConpoints(point);
		if (IConpointService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			if (null != str) {
				l1 = str.substring(0, str.length() - 1);
				this.setSuccessMessage("已成功删除" + result.getResult()
						+ "个权限点,权限点" + l1 + "正被某个角色使用，请先解除角色权限点的关系！");
			} else {
				this.setSuccessMessage("已成功删除" + result.getResult() + "个权限点!");
			}
		}
		return RESULT_MESSAGE;

	}

	@PermissionSearch
	public String forModifyConpoint() {
		point = conpointService.getConpointMenuPojo(conpointId);
		return "formodify";
	}

	public String modifyConpoint() {
		Conpoint p = new Conpoint();
		p.setConpointName(conpointName);
		List<Conpoint> cl = new ArrayList<Conpoint>();
		cl = conpointService.getConpointListIsExit(p);
		if (cl.size() > 0) {
			if (cl.size() == 1) {
				if (!cl.get(0).getConpointId().equals(conpointId)) {
					this.setFailMessage("权限点名称已经存在!");
				} else {
					p.setConpointId(conpointId);
					int result = conpointService.modifyConpoint(p);
					if (result == 1) {
						this.setSuccessMessage("修改成功!");
					} else {
						this.setFailMessage("修改失败!");
					}
				}
			} else {
				this.setFailMessage("权限点名称已经存在!");
			}
		} else {
			p.setConpointId(conpointId);
			int result = conpointService.modifyConpoint(p);
			if (result == 1) {
				this.setSuccessMessage("修改成功!");
			} else {
				this.setFailMessage("修改失败!");
			}
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String forAddConpoing() {
		return "foraddconpoing";
	}

	public String addConpoint() {
		Conpoint p = new Conpoint();
		p.setConpointName(conpointName.trim());

		if (null==menuId) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		} else {
			p.setMenuId(new BigDecimal(menuId));
		}
		int i = conpointService.getConpointCount(p);
		if (i > 0) {
			this.setFailMessage("权限点名称已经存在!");
		} else {
			Object obj = conpointService.createConpoint(p);
			if (obj != null) {
				this.setSuccessMessage("添加成功!");
			} else {
				this.setFailMessage("添加失败!");
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 角色权限点
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchRoleConpoint() {

		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {

			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}

		return "searchRoleConpoint";
	}

	@PermissionSearch
	@JsonResult(field = "conpointList", include = { "roleConpointId",
			"conpointId", "conpointName", "conpointNum", "menuId", "menuName",
			"closeFlag" }, total = "total")
	public String getRoleConpointJsonList() {
		Conpoint p = new Conpoint();
		p.setStart(getStart());
		p.setEnd(getEnd());

		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {

			p.setRoleId(roleId.trim());

			total = conpointService.getRoleConpointCount(p);
			if (total != 0) {
				conpointList = conpointService.getRoleConpointList(p);
			} else {
				conpointList = null;
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
	private boolean validate(Conpoint point) {

		if (point == null) {
			return false;
		}

		if (StringUtils.isEmpty(point.getRoleId())
				|| StringUtils.isEmpty(point.getRoleId().trim())
				|| point.getConpointId() == null
				|| StringUtils.isEmpty(point.getCloseFlag())
				|| StringUtils.isEmpty(point.getCloseFlag().trim())) {
			return false;
		}

		return true;
	}

	@PermissionSearch
	public String createRoleConpointPrepare() {

		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {

			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}

		return CREATE_PREPARE;
	}

	/**
	 * 创建角色权限点
	 * 
	 * @return
	 */
	public String createRoleConpoint() {
		if (!validate(point)) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}
		StringResult result = conpointService.createRoleConpoint(point);

		if (IConpointService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("操作成功！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 修改/查询菜单信息
	 * 
	 * @return
	 */
	public String updateRoleConpointPrepare() {

		if (StringUtils.isNotEmpty(roleConpointId)
				&& StringUtils.isNotEmpty(roleConpointId.trim())) {
			try {
				point = conpointService.getRoleConpointById(Long
						.parseLong(roleConpointId));
				return UPDATE_PREPARE;
			} catch (Exception e) {
				logger.error(roleConpointId);
			}
		}

		point = new Conpoint();
		return UPDATE_PREPARE;
	}

	public String updateRoleConpoint() {
		Conpoint conpoint=new Conpoint();
		conpoint.setPagination("false");
		conpoint.setConpointName(point.getConpointName());
		List<Conpoint> list1=null;
		list1=conpointService.getConpointListJson(conpoint);
		if(0==list1.size()){
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}else{
			ArrayList<String> str=new ArrayList<String>();
			for (int i = 0; i < list1.size(); i++) {
				str.add(list1.get(i).getConpointName());
			}
			if(!str.contains(point.getConpointName())){
				this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
				return RESULT_MESSAGE;
			}
		}
		StringResult result = conpointService.updateRoleConpoint(point);

		if (IConpointService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("操作成功！");
		}

		return RESULT_MESSAGE;
	}

	public String deleteRoleConpoint() {
		String[] l = ids.split(",");
		int i = 0;
		for (int j = 0; j < l.length; j++) {
			i++;
		}

		// 无有效的菜单id
		if (i == 0) {
			this.setFailMessage(IConpointService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		point = new Conpoint();
		point.setCodes(l);
		StringResult result = conpointService.deleteRoleConpoint(point);
		if (IConpointService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("已成功删除" + result.getResult() + "个角色权限点!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 供权限点使用/供菜单使用
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchRole4Config() {

		return "searchRole4Config";
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public BigDecimal getConpointId() {
		return conpointId;
	}

	public void setConpointId(BigDecimal conpointId) {
		this.conpointId = conpointId;
	}

	public String getConpointName() {
		return conpointName;
	}

	public void setConpointName(String conpointName) {
		this.conpointName = conpointName;
	}

	public String getConpointNum() {
		return conpointNum;
	}

	public void setConpointNum(String conpointNum) {
		this.conpointNum = conpointNum;
	}

	public List<Conpoint> getConpointList() {
		return conpointList;
	}

	public void setConpointList(List<Conpoint> conpointList) {
		this.conpointList = conpointList;
	}

	public IConpointService getConpointService() {
		return conpointService;
	}

	public void setConpointService(IConpointService conpointService) {
		this.conpointService = conpointService;
	}

	public Conpoint getPoint() {
		return point;
	}

	public void setPoint(Conpoint point) {
		this.point = point;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleConpointId() {
		return roleConpointId;
	}

	public void setRoleConpointId(String roleConpointId) {
		this.roleConpointId = roleConpointId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

}
