package com.kintiger.platform.wfe.action;

import java.util.ArrayList;
import java.util.List;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.station.service.IStationService;
import com.kintiger.platform.wfe.pojo.ActProcdef;
import com.kintiger.platform.wfe.pojo.PlanAttribute;
import com.kintiger.platform.wfe.service.IModelAttributeService;
import com.kintiger.platform.wfe.service.IModelService;

/**
 * 流程选择Action
 */
public class ProcessChooseAction extends BaseAction{

	private static final long serialVersionUID = -7089143288279441542L;

	private IStationService stationService;
	private IModelService modelService;
	private IModelAttributeService modelAttributeService;
	
	private List<Station> stationList;
	private List<ActProcdef> actProcdefList;
	private List<PlanAttribute> planAttributeList;
	private String from;
	private String key;
	private IDictService dictService;	
	/**
	 * 获取岗位
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "stationList", include = { "stationId", "stationName" })
	public String getStations(){
		stationList = stationService.getStationByEmpId(Long
				.parseLong(getUser().getUserId()));
		return JSON;
	}
	
	/**
	 * 获取模板
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "actProcdefList", include = { "key", "name" })
	public String getModels(){
		CmsTbDict cmsTbDict = new CmsTbDict();
		ActProcdef actProcdef = new ActProcdef();
		 
		List<CmsTbDict> cmsTbDicts = new ArrayList<CmsTbDict>();
		cmsTbDict.setItemName("事务申报页面流程列表");
		
		cmsTbDicts = dictService.getByCmsTbDictList(cmsTbDict); //获取事务申报页面可以选择到的流程
		if(cmsTbDicts != null && cmsTbDicts.size() >0){
			String[]  models = new String[cmsTbDicts.size() + 1]; 
			int i = 0;
			for (CmsTbDict c : cmsTbDicts) {
				models[i] = c.getItemValue();
				i++;
			}
			actProcdef.setModels(models);
		}
		actProcdefList = modelService.getModelByRole(actProcdef);
		return JSON;
	}
	
	/**
	 * 获取模板属性
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "planAttributeList", include = { "planAttId", "planTypeName" })
	public String getPlanAttribute(){
		planAttributeList = modelAttributeService.getModelAttributeByModelId(key);
		return JSON;
	}

	public IStationService getStationService() {
		return stationService;
	}

	public void setStationService(IStationService stationService) {
		this.stationService = stationService;
	}

	public IModelService getModelService() {
		return modelService;
	}

	public void setModelService(IModelService modelService) {
		this.modelService = modelService;
	}

	public IModelAttributeService getModelAttributeService() {
		return modelAttributeService;
	}

	public void setModelAttributeService(
			IModelAttributeService modelAttributeService) {
		this.modelAttributeService = modelAttributeService;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public List<ActProcdef> getActProcdefList() {
		return actProcdefList;
	}

	public void setActProcdefList(List<ActProcdef> actProcdefList) {
		this.actProcdefList = actProcdefList;
	}
	
	public List<PlanAttribute> getPlanAttributeList() {
		return planAttributeList;
	}

	public void setPlanAttributeList(List<PlanAttribute> planAttributeList) {
		this.planAttributeList = planAttributeList;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}
}
