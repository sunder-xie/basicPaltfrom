package com.kintiger.platform.position.dao;

import java.util.List;

import com.kintiger.platform.position.pojo.BpositionType;

public interface IPositionTypeDao {

	/**
	 * 
	 * @param bpositionType
	 * @return
	 */
	public int getPositionTypesCount(BpositionType positionType);

	/**
	 * 
	 * @param positionType
	 * @return
	 */
	public List<BpositionType> getPositionTypesList(BpositionType positionType);

	/**
	 * 
	 * @param positionType
	 * @return
	 */
	public List<BpositionType> exportPositionTypesList(
			BpositionType positionType);

	/**
	 * 
	 * @param positionType
	 * @return
	 */
	public BpositionType getPositionTypes(BpositionType positionType);

	/**
	 * 
	 * @param positionType
	 * @return
	 */
	public int updatePositionTypes(BpositionType positionType);

	/**
	 * 
	 * @param positionType
	 * @return
	 */
	public Long createPositionTypes(BpositionType positionType);

	/**
	 * 
	 * @param positionType
	 * @return
	 */
	public int getPositionType4RoleCount(BpositionType positionType);

	/**
	 * 
	 * @param positionType
	 * @return
	 */
	public List<BpositionType> getPositionType4RoleList(
			BpositionType positionType);

	public boolean insertPos(BpositionType positionType);

}
