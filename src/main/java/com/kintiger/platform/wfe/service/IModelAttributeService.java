package com.kintiger.platform.wfe.service;

import java.util.List;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.wfe.pojo.ActProcdef;
import com.kintiger.platform.wfe.pojo.PlanAttribute;
import com.kintiger.platform.wfe.pojo.PlanAttributeDetail;


/**
 * ģ����������
 * 
 * 
 */
public interface IModelAttributeService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String SUCCESS_MESSAGE = "�����ɹ���";

	public static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	public static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	public static final String ERROR_NULL_MESSAGE = "����ʧ�ܣ������Ѳ����ڣ�";

	public List<PlanAttributeDetail> getPlanAttContentByPlanAttId(Long planAttId);
	
	/**
	 * 
	 * @param planAttribute
	 * @return
	 */
	public int getModelAttributeCount(PlanAttribute planAttribute);

	/**
	 * 
	 * @param planAttribute
	 * @return
	 */
	public List<PlanAttribute> getModelAttributeList(PlanAttribute planAttribute);
	
	/**
	 * ����ģ��id��ѯģ������
	 * 
	 * @param modelId
	 * @return
	 */
	public List<PlanAttribute> getModelAttributeByModelId(String modelId);
	
	/**
	 * ��ѯ����ģ��
	 * @param act
	 * @return
	 */
	public List<ActProcdef> blurSearchModel(ActProcdef act);
	
	public StringResult createModelAtt(PlanAttribute planAtt);
	
	public StringResult updateModelAtt(PlanAttribute planAtt);
	
	public StringResult deleteModelAtt(String ids);
	
	public PlanAttribute getModelAttributeByPlanAttId(Long planAttId);
	
	public int getModelAttributeDetailCount(PlanAttributeDetail planAttributeDetail);

	public List<PlanAttributeDetail> getModelAttributeDetailList(PlanAttributeDetail planAttributeDetail);
	
	public List<CmsTbDict> searchDataType(CmsTbDict dict);
	
	public StringResult createAttDetail(PlanAttributeDetail attributeDetail);
	
	public PlanAttributeDetail getAttDetailByDetail(Long detailId);
	
	public StringResult updateAttDetail(PlanAttributeDetail attributeDetail);
	
	public StringResult deleteAttDetail(String ids);
}
