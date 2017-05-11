package com.kintiger.platform.wfe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.wfe.dao.IPlanAttributeDao;
import com.kintiger.platform.wfe.pojo.ActProcdef;
import com.kintiger.platform.wfe.pojo.PlanAttribute;
import com.kintiger.platform.wfe.pojo.PlanAttributeDetail;
import com.kintiger.platform.wfe.service.IModelAttributeService;

public class ModelAttributeServiceImpl implements IModelAttributeService{
	
	private static final Logger logger = Logger.getLogger(ModelAttributeServiceImpl.class);
	
	private IPlanAttributeDao planAttributeDao;

	public int getModelAttributeCount(PlanAttribute planAttribute) {
		try {
			return planAttributeDao.getPlanAttributeCount(planAttribute);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(planAttribute), e);
		}
		return 0;
	}

	public List<PlanAttribute> getModelAttributeList(PlanAttribute planAttribute) {
		try {
			return planAttributeDao.getPlanAttributeList(planAttribute);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(planAttribute), e);
		}
		return null;
	}
	
	public List<PlanAttributeDetail> getPlanAttContentByPlanAttId(Long planAttId) {
		try {
			return planAttributeDao.getPlanAttContentByPlanAttId(planAttId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(planAttId), e);
		}
		return null;
	}
	
	public PlanAttribute getModelAttributeByPlanAttId(Long planAttId) {
		try {
			return planAttributeDao.getPlanAttributeByPlanAttId(planAttId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<PlanAttribute> getModelAttributeByModelId(String modelId) {
		try {
			return planAttributeDao.getPlanAttributeByModelId(modelId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public List<ActProcdef> blurSearchModel(ActProcdef act) {
		try {
			return planAttributeDao.blurSearchModel(act);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public StringResult createModelAtt(PlanAttribute planAtt){
		StringResult result = new StringResult();
		try{
			planAttributeDao.createPlanAttTotal(planAtt);		
//			if(!"".equals(attContent)){
//				String[] attArray = attContent.split(";");
//				String[] keyArray = new String[attArray.length];
//				System.arraycopy(keyContent.split(";"), 0, keyArray, 0, keyContent.split(";").length);
//				if(keyContent.endsWith(";")){
//					keyArray[keyArray.length-1] = "";
//				}
//				for(int i=0;i<attArray.length;i++){
//					PlanAttributeDetail detail = new PlanAttributeDetail();
//					detail.setPlanAttId(planAttId);
//					detail.setPlanAttContent(attArray[i]);
//					detail.setModelKey(keyArray[i]);
//					detailList.add(detail);
//				}
//				planAttributeDao.createPlanAttDetail(detailList);
//			}
			result.setCode(IModelAttributeService.SUCCESS);
			result.setResult(IModelAttributeService.SUCCESS_MESSAGE);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			result.setCode(IModelAttributeService.ERROR);
			result.setResult(IModelAttributeService.ERROR_MESSAGE);
		}
		return result;
	}
	
	public StringResult updateModelAtt(PlanAttribute planAtt){
		StringResult result = new StringResult();
		try{
			planAttributeDao.updatePlanAttTotal(planAtt);
			
//			if(!"".equals(planAtt.getPlanAttContent())){
//				String[] attArray = planAtt.getPlanAttContent().split(";");
//				String[] keyArray = new String[attArray.length];
//				System.arraycopy(planAtt.getPlanKeyContent().split(";"), 0, keyArray, 0, 
//						planAtt.getPlanKeyContent().split(";").length);
//				if(planAtt.getPlanKeyContent().endsWith(";")){
//					keyArray[keyArray.length-1] = "";
//				}
				
				//数量可能会出现变动,先删除再整体添加
//				planAttributeDao.deletePlanAttContent(planAtt.getPlanAttId());
//				List<PlanAttributeDetail> detailList = new ArrayList<PlanAttributeDetail>();
//				for(int i=0;i<attArray.length;i++){
//					PlanAttributeDetail detail = new PlanAttributeDetail();
//					detail.setPlanAttId(planAtt.getPlanAttId());
//					detail.setPlanAttContent(attArray[i]);
//					detail.setModelKey(keyArray[i]);
//					detailList.add(detail);
//				}
//				planAttributeDao.createPlanAttDetail(detailList);
//			}
			result.setCode(IModelAttributeService.SUCCESS);
		}catch(Exception e){
			logger.error(e);
			result.setCode(IModelAttributeService.ERROR);
		}
		return result;
	}
	
	public StringResult deleteModelAtt(String ids){
		StringResult result = new StringResult();
		try{
			planAttributeDao.deleteModelAtt(ids);
			result.setCode(IModelAttributeService.SUCCESS);
			result.setResult(IModelAttributeService.SUCCESS_MESSAGE);
		}catch(Exception e){
			logger.error(e);
			result.setCode(IModelAttributeService.ERROR);
			result.setResult(IModelAttributeService.ERROR_MESSAGE);
		}
		return result;
	}
	
	/**
	 * 查询模板属性明细
	 */
	public int getModelAttributeDetailCount(PlanAttributeDetail planAttributeDetail){
		try {
			return planAttributeDao.getModelAttributeDetailCount(planAttributeDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(planAttributeDetail), e);
		}
		return 0;
	}

	/**
	 * 查询模板属性明细
	 */
	public List<PlanAttributeDetail> getModelAttributeDetailList(PlanAttributeDetail planAttributeDetail){
		try {
			return planAttributeDao.getModelAttributeDetailList(planAttributeDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(planAttributeDetail), e);
		}
		return null;
	}
	
	/**
	 * 查询字典数据类型
	 */
	public List<CmsTbDict> searchDataType(CmsTbDict dict){
		try{
			return planAttributeDao.searchDataType(dict);
		}catch(Exception e){
			logger.error(LogUtil.parserBean(dict), e);
		}
		return null;
	}
	
	public StringResult createAttDetail(PlanAttributeDetail attributeDetail){
		StringResult result = new StringResult();
		try{
			planAttributeDao.createPlanAttDetail(attributeDetail);
			result.setCode(IModelAttributeService.SUCCESS);
			result.setResult(IModelAttributeService.SUCCESS_MESSAGE);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			result.setCode(IModelAttributeService.ERROR);
			result.setResult(IModelAttributeService.ERROR_MESSAGE);
		}
		return result;
	}
	
	public PlanAttributeDetail getAttDetailByDetail(Long detailId){
		try {
			return planAttributeDao.getAttDetailByDetail(detailId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public StringResult updateAttDetail(PlanAttributeDetail attributeDetail){
		StringResult result = new StringResult();
		try{
			planAttributeDao.updatePlanAttDetail(attributeDetail);
			result.setCode(IModelAttributeService.SUCCESS);
			result.setResult(IModelAttributeService.SUCCESS_MESSAGE);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			result.setCode(IModelAttributeService.ERROR);
			result.setResult(IModelAttributeService.ERROR_MESSAGE);
		}
		return result;
	}
	
	public StringResult deleteAttDetail(String ids){
		StringResult result = new StringResult();
		try{
			planAttributeDao.deleteAttDetail(ids);
			result.setCode(IModelAttributeService.SUCCESS);
			result.setResult(IModelAttributeService.SUCCESS_MESSAGE);
		}catch(Exception e){
			logger.error(e);
			result.setCode(IModelAttributeService.ERROR);
			result.setResult(IModelAttributeService.ERROR_MESSAGE);
		}
		return result;
	}

	public IPlanAttributeDao getPlanAttributeDao() {
		return planAttributeDao;
	}

	public void setPlanAttributeDao(IPlanAttributeDao planAttributeDao) {
		this.planAttributeDao = planAttributeDao;
	}


}
