package com.kintiger.platform.wfe.dao;

import java.util.List;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.wfe.pojo.ActProcdef;
import com.kintiger.platform.wfe.pojo.PlanAttribute;
import com.kintiger.platform.wfe.pojo.PlanAttributeDetail;

public interface IPlanAttributeDao {

	/**
	 * 
	 * @param planAttribute
	 * @return
	 */
	public int getPlanAttributeCount(PlanAttribute planAttribute);

	/**
	 * 
	 * @param planAttribute
	 * @return
	 */
	public List<PlanAttribute> getPlanAttributeList(PlanAttribute planAttribute);
	
	public List<PlanAttributeDetail> getPlanAttContentByPlanAttId(Long planAttId);

	/**
	 * 根据模板id查询模板属性
	 * 
	 * @param planAttribute
	 * @return
	 */
	public List<PlanAttribute> getPlanAttributeByModelId(String modelId);
	
	/**
	 * 查询流程模板
	 * @param act
	 * @return
	 */
	public List<ActProcdef> blurSearchModel(ActProcdef act);
	
	/**
	 * 保存模板属性总表
	 * @param planAtt
	 * @return
	 */
	public void createPlanAttTotal(PlanAttribute planAtt);
	
	/**
	 * @param planAttribute
	 */
	public void updatePlanAttTotal(PlanAttribute planAttribute);
	
	public void deleteModelAtt(String ids);
	
	public PlanAttribute getPlanAttributeByPlanAttId(Long planAttId);
	
	/**
	 * 查询模板属性明细Count
	 * @param planAttributeDetail
	 * @return
	 */
	public int getModelAttributeDetailCount(PlanAttributeDetail planAttributeDetail);

	/**
	 * 查询模板属性明细List 
	 * @param planAttributeDetail
	 * @return
	 */
	public List<PlanAttributeDetail> getModelAttributeDetailList(PlanAttributeDetail planAttributeDetail);
	
	public List<CmsTbDict> searchDataType(CmsTbDict dict);
	
	/**
	 * 保存模板属性明细
	 * @param planAtt
	 * @return
	 */
	public void createPlanAttDetail(PlanAttributeDetail attributeDetail);
	
	public PlanAttributeDetail getAttDetailByDetail(Long detailId);
	
	/**
	 * @param detailList
	 */
	public void updatePlanAttDetail(PlanAttributeDetail attributeDetail);
	
	public void deleteAttDetail(String ids);
	
}
