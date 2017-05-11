package com.kintiger.platform.dict.dao;

import java.util.List;

import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;



public interface IDictDao {

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public int getCmsTbDictCount(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict);

	/**
	 * 关联 dict type 查询 dict
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public int getDictCount(CmsTbDict cmsTbDict);

	/**
	 * 关联 dict type 查询 dict
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getDictList(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	public int getCmsTbDictTypeCount(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	public Long CreateDictType(CmsTbDictType cmsTbDictType);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public Long CreateDict(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public int updateDict(CmsTbDict cmsTbDict);

	/**
	 * 
	 * @param cmsTbDictType
	 * @return
	 */
	public int updateDictType(CmsTbDictType cmsTbDictType);

	public CmsTbDictType getCmsTbDictType(CmsTbDictType cmsTbDictType);

	public CmsTbDict getCmsTbDict(CmsTbDict cmsTbDict);

	public List<CmsTbDict> getCmsTbDictByType(CmsTbDict cmsTbDict);
	public List<CmsTbDict> getByCmsTbDictList(CmsTbDict cmsTbDict);
	


}
