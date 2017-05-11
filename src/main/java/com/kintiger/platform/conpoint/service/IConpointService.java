package com.kintiger.platform.conpoint.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.conpoint.pojo.Conpoint;

/**
 * 
 * 权限点接口
 * 
 */
public interface IConpointService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "操作失败！";

	public static final String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	public int getConpointCount(Conpoint p);

	public List<Conpoint> getConpointList(Conpoint p);
	public List<Conpoint> getConpointListIsExit(Conpoint p);

	public List<Conpoint> getConpointListJson(Conpoint p);

	public int deleteConpoint(BigDecimal conpointId);
	public StringResult deleteConpoints(Conpoint p) ;

	public Conpoint getConpointMenuPojo(BigDecimal conpointId);

	public int modifyConpoint(Conpoint p);

	public Object createConpoint(Conpoint c);
	public int getRolesByConpointId(Conpoint c);

	/**
	 * 权限校验
	 * 
	 * @param conpoint
	 * @return
	 */
	@Deprecated
	public String isAut(Conpoint conpoint);

	/**
	 * 获取权限点 返回 map key = conpointId value = "Y"
	 * 
	 * @param userId
	 * @param conpointId
	 * @return
	 */
	public Map<BigDecimal, String> getPermissions(String userId,
			String[] conpointId);

	/**
	 * 角色权限点
	 * 
	 * @param conpoint
	 * @return
	 */
	public int getRoleConpointCount(Conpoint conpoint);

	/**
	 * 角色权限点
	 * 
	 * @param conpoint
	 * @return
	 */
	public List<Conpoint> getRoleConpointList(Conpoint conpoint);

	/**
	 * 创建角色权限点
	 * 
	 * @param conpoint
	 * @return
	 */
	public StringResult createRoleConpoint(Conpoint conpoint);

	/**
	 * 修改角色权限点
	 * 
	 * @param conpoint
	 * @return
	 */
	public StringResult updateRoleConpoint(Conpoint conpoint);

	/**
	 * 根据id查询角色权限点信息
	 * 
	 * @param roleConpointId
	 * @return
	 */
	public Conpoint getRoleConpointById(Long roleConpointId);

	/**
	 * 删除角色权限点
	 * 
	 * @param conpoint
	 * @return
	 */
	public StringResult deleteRoleConpoint(Conpoint conpoint);

}
