package com.kintiger.platform.conpoint.dao;

import java.math.BigDecimal;
import java.util.List;

import com.kintiger.platform.conpoint.pojo.Conpoint;


public interface IConpointDao {

	public int getConpointCount(Conpoint p);

	public List<Conpoint> getConpointList(Conpoint p);
	public List<Conpoint> getConpointListIsExit(Conpoint p);
	public List<Conpoint> getConpointListJson(Conpoint p);

	public int deleteConpoint(BigDecimal conpointId);
	public int deleteConpoints(Conpoint p);

	public Conpoint getConpointMenuPojo(BigDecimal conpointId);

	public int modifyConpoint(Conpoint p);

	public Object createConpoint(Conpoint c);

	@Deprecated
	public Conpoint isAut(Conpoint conpoint);
	public int getRolesByConpointId(Conpoint c);

	/**
	 * 
	 * @param conpoint
	 * @return
	 */
	public List<Conpoint> getPermissions(Conpoint conpoint);

	/**
	 * 
	 * @param conpoint
	 * @return
	 */
	public int getRoleConpointCount(Conpoint conpoint);

	/**
	 * 
	 * @param conpoint
	 * @return
	 */
	public List<Conpoint> getRoleConpointList(Conpoint conpoint);

	/**
	 * 
	 * @param conpoint
	 * @return
	 */
	public Long createRoleConpoint(Conpoint conpoint);

	/**
	 * 
	 * @param conpoint
	 * @return
	 */
	public int updateRoleConpoint(Conpoint conpoint);

	/**
	 * 
	 * @param roleConpointId
	 * @return
	 */
	public Conpoint getRoleConpointById(Long roleConpointId);

}
