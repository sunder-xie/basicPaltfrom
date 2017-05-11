package com.kintiger.platform.wfe.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.wfe.dao.IPlanAttributeDao;
import com.kintiger.platform.wfe.pojo.ActProcdef;
import com.kintiger.platform.wfe.pojo.PlanAttribute;
import com.kintiger.platform.wfe.pojo.PlanAttributeDetail;

@SuppressWarnings("rawtypes")
public class PlanAttributeDaoImpl extends BaseDaoImpl implements IPlanAttributeDao{

	public int getPlanAttributeCount(PlanAttribute planAttribute) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"planAttribute.getPlanAttributeCount", planAttribute);
	}

	@SuppressWarnings("unchecked")
	public List<PlanAttribute> getPlanAttributeList(PlanAttribute planAttribute) {
		return getSqlMapClientTemplate().queryForList(
				"planAttribute.getPlanAttributeList", planAttribute);
	}
	
	@SuppressWarnings("unchecked")
	public List<PlanAttributeDetail> getPlanAttContentByPlanAttId(Long planAttId) {
		return getSqlMapClientTemplate().queryForList(
				"planAttribute.getPlanAttContentByPlanAttId", planAttId);
	}

	@SuppressWarnings("unchecked")
	public List<PlanAttribute> getPlanAttributeByModelId(String modelId) {
		return getSqlMapClientTemplate().queryForList(
				"planAttribute.getPlanAttributeByModelId", modelId);
	}
	

	@SuppressWarnings("unchecked")
	public List<ActProcdef> blurSearchModel(ActProcdef act) {
		return getSqlMapClientTemplate().queryForList(
				"planAttribute.getModelByEntity", act);
	}
	
	public void createPlanAttTotal(PlanAttribute planAtt){
		getSqlMapClientTemplate().insert("planAttribute.insertPlanAttTotal", planAtt);
	}

	public void updatePlanAttTotal(PlanAttribute planAttribute) {
		getSqlMapClientTemplate().update("planAttribute.updatePlanAttTotal", planAttribute);
		
	}

	public void deleteModelAtt(String ids){
		this.getSqlMapClientTemplate().update("planAttribute.deletPlanAtt", ids);
	}
	
	public PlanAttribute getPlanAttributeByPlanAttId(Long planAttId) {
		return (PlanAttribute) getSqlMapClientTemplate().queryForObject("planAttribute.getPlanAttributeByPlanAttId", planAttId);
	}
	
	public int getModelAttributeDetailCount(PlanAttributeDetail planAttributeDetail){
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"planAttribute.getModelAttributeDetailCount", planAttributeDetail);
	}

	@SuppressWarnings("unchecked")
	public List<PlanAttributeDetail> getModelAttributeDetailList(PlanAttributeDetail planAttributeDetail){
		return getSqlMapClientTemplate().queryForList(
				"planAttribute.getModelAttributeDetailList", planAttributeDetail);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmsTbDict> searchDataType(CmsTbDict dict){
		return getSqlMapClientTemplate().queryForList("planAttribute.searchDataType", dict);
	}
	
	public void createPlanAttDetail(PlanAttributeDetail attributeDetail){
		getSqlMapClientTemplate().insert("planAttribute.createPlanAttDetail", attributeDetail);
	}
	
	public PlanAttributeDetail getAttDetailByDetail(Long detailId){
		return (PlanAttributeDetail) getSqlMapClientTemplate().queryForObject("planAttribute.getAttDetailByDetail", detailId);
	}
	
	public void updatePlanAttDetail(PlanAttributeDetail attributeDetail){
		getSqlMapClientTemplate().update("planAttribute.updatePlanAttDetail", attributeDetail);
	}
	
	public void deleteAttDetail(String ids){
		this.getSqlMapClientTemplate().delete("planAttribute.deleteAttDetail", ids);
	}

}
