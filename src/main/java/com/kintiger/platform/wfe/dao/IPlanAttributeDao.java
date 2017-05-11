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
	 * ����ģ��id��ѯģ������
	 * 
	 * @param planAttribute
	 * @return
	 */
	public List<PlanAttribute> getPlanAttributeByModelId(String modelId);
	
	/**
	 * ��ѯ����ģ��
	 * @param act
	 * @return
	 */
	public List<ActProcdef> blurSearchModel(ActProcdef act);
	
	/**
	 * ����ģ�������ܱ�
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
	 * ��ѯģ��������ϸCount
	 * @param planAttributeDetail
	 * @return
	 */
	public int getModelAttributeDetailCount(PlanAttributeDetail planAttributeDetail);

	/**
	 * ��ѯģ��������ϸList 
	 * @param planAttributeDetail
	 * @return
	 */
	public List<PlanAttributeDetail> getModelAttributeDetailList(PlanAttributeDetail planAttributeDetail);
	
	public List<CmsTbDict> searchDataType(CmsTbDict dict);
	
	/**
	 * ����ģ��������ϸ
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
