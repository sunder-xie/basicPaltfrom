package com.kintiger.platform.wfe.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.wfe.pojo.ActProcdef;
import com.kintiger.platform.wfe.pojo.PlanAttribute;
import com.kintiger.platform.wfe.pojo.PlanAttributeDetail;
import com.kintiger.platform.wfe.service.IModelAttributeService;

public class ModelAttributeAction extends BaseAction{

	private static final long serialVersionUID = -6796763060092829932L;
	private static final Logger logger = Logger.getLogger(ModelAttributeAction.class);
	
	private IModelAttributeService modelAttributeService;
	
	private PlanAttribute planAttribute;
	private String rid;
	private String modelId;
	@Decode
	private String modelName;
	@Decode
	private String planTypeName;
	private String planAttFlag;
	private String attMemo;
	
	private List<PlanAttribute> planAttributeList = new ArrayList<PlanAttribute>();
	private List<ActProcdef> modelList;
	
	private PlanAttributeDetail attributeDetail;
	@Decode
	private String planAttContent;
	private String planAttDataType;
	private String planAttIsNull;
	private String planAttKey;
	private List<PlanAttributeDetail> attributeDetailList;
	private List<CmsTbDict> dataTypeList;
	
	private int total = 0;
	private String type;
	private String ids;
	
	/**
	 * 查询模板属性
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchModelAttribute() {
		return "searchModelAttribute";
	}
	
	/**
	 * 查询流程模板事务类别
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "planAttributeList", include = { "planAttId",
			"modelName", "planTypeName", "planAttMemo", "planAttFlag", "modifyDate" }, 
			total = "total")
	public String getModelAttributeJsonList() {
		PlanAttribute p = new PlanAttribute();
		p.setStart(getStart());
		p.setEnd(getEnd());
		try {
			if (StringUtils.isNotEmpty(modelName)
					&& StringUtils.isNotEmpty(modelName.trim())) {
				p.setModelName(modelName.trim());
			}
			if (StringUtils.isNotEmpty(planTypeName)
					&& StringUtils.isNotEmpty(planTypeName.trim())) {
				p.setPlanTypeName(planTypeName.trim());
			}
			if (StringUtils.isNotEmpty(planAttFlag)
					&& StringUtils.isNotEmpty(planAttFlag.trim())) {
				p.setPlanAttFlag(planAttFlag.trim());
			}
		} catch (Exception e) {
			logger.error("modelName:" + modelName + "planAttFlag:" + planAttFlag, e);
		}
		total = modelAttributeService.getModelAttributeCount(p);
		if (total != 0) {
			planAttributeList = modelAttributeService.getModelAttributeList(p);
		}
		return JSON;
	}
	
	/**
	 * 新增属性模板(跳转)
	 * @return
	 */
	@PermissionSearch
	public String createModelAttPrepare(){
		return CREATE_PREPARE;
	}
	
	/**
	 * 跳转修改
	 * @return
	 */
	@PermissionSearch
	public String toUpdateModelAtt(){
		type = "modify";
		planAttribute = modelAttributeService.getModelAttributeByPlanAttId(Long.parseLong(rid));
		return CREATE_PREPARE;
	}

	/**
	 * 查询流程模板
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "modelList", include = { "key", "name" })
	public String blurSearchModel() {
		modelList = new ArrayList<ActProcdef>();
		ActProcdef act = new ActProcdef();
		if (StringUtils.isNotEmpty(modelName)
				&& StringUtils.isNotEmpty(modelName.trim())) {
			try {
				modelName = new String(getServletRequest().getParameter("modelName")
						.getBytes("ISO8859-1"), "UTF-8");
				act.setName(modelName.trim());
				modelList = modelAttributeService.blurSearchModel(act);
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
		}
		return JSON;
	}
	
	/**
	 * 创建保存模板事务类别
	 * @return
	 */
	public String createModelAtt(){
		PlanAttribute planAtt = new PlanAttribute();
		planAtt.setModelId(modelId);
		planAtt.setPlanTypeName(planTypeName);
		planAtt.setPlanAttMemo(attMemo);
		planAtt.setPlanAttFlag(planAttFlag);
		planAtt.setPlanAttUser(this.getUser().getUserId());
		StringResult result = modelAttributeService.createModelAtt(planAtt);
		if(IModelAttributeService.SUCCESS.equals(result.getCode())){
			this.setSuccessMessage(result.getResult());
		}else{
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 修改模板事务类别
	 * 
	 * @return
	 */
	public String updateModelAtt(){
		PlanAttribute planAtt = new PlanAttribute();
		planAtt.setModelId(modelId);
		planAtt.setPlanTypeName(planTypeName);
		planAtt.setPlanAttMemo(attMemo);
		planAtt.setPlanAttFlag(planAttFlag);
		planAtt.setPlanAttUser(this.getUser().getUserId());
		planAtt.setPlanAttId(Long.parseLong(rid));
		StringResult result = modelAttributeService.updateModelAtt(planAtt);
		if(IModelAttributeService.SUCCESS.equals(result.getCode())){
			this.setSuccessMessage(IModelAttributeService.SUCCESS_MESSAGE);
		}else{
			this.setFailMessage(IModelAttributeService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 禁用模板事务类别
	 * @return
	 */
	public String deleteModelAtt(){
		StringResult result = modelAttributeService.deleteModelAtt(ids);
		if(IModelAttributeService.SUCCESS.equals(result.getCode())){
			this.setSuccessMessage(result.getResult());
		}else{
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 查询模板事务列表属性明细
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "attributeDetailList", include = { "planAttDetailId",
			"planAttContent", "planAttDataType", "planAttKey"}, total = "total")
	public String getModelAttributeDetailList(){
		attributeDetail = new PlanAttributeDetail();
		attributeDetail.setPlanAttId(rid==null?-1000:Long.parseLong(rid.trim()));
		attributeDetail.setStart(this.getStart());
		attributeDetail.setEnd(this.getEnd());
		try {
			if (StringUtils.isNotEmpty(planAttContent)
					&& StringUtils.isNotEmpty(planAttContent.trim())) {
				attributeDetail.setPlanAttContent(planAttContent.trim());
			}
		} catch (Exception e) {
			logger.error("planAttContent:" + planAttContent, e);
		}
		total = modelAttributeService.getModelAttributeDetailCount(attributeDetail);
		if(total>0){
			attributeDetailList = modelAttributeService.getModelAttributeDetailList(attributeDetail);
		}
		return JSON;
	}
	
	/**
	 * 新增模板属性明细(跳转)
	 * @return
	 */
	@PermissionSearch
	public String createAttDetailPrepare(){
		return "createAttDetailPrepare";
	}
	
	public String updateAttDetailPrepare(){
		type = "modify";
		attributeDetail = modelAttributeService.getAttDetailByDetail(Long.parseLong(rid));
		return "createAttDetailPrepare";
	}
	
	/**
	 * 查询数据类型
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "dataTypeList", 
			include = {"itemId", "itemName", "itemValue"})
	public String searchMprDatatype(){
		CmsTbDict dict = new CmsTbDict();
		dict.setDictTypeValue("dataType");
		dataTypeList = modelAttributeService.searchDataType(dict);
		return JSON;
	}
	
	/**
	 * 创建模板属性明细
	 * @return
	 */
	public String createAttDetail(){
		attributeDetail = new PlanAttributeDetail();
		attributeDetail.setPlanAttId(Long.parseLong(rid.trim()));
		attributeDetail.setPlanAttContent(planAttContent);
		attributeDetail.setPlanAttKey(planAttKey);
		attributeDetail.setPlanAttDataType(planAttDataType);
		attributeDetail.setPlanAttIsNull(planAttIsNull);
		StringResult result = modelAttributeService.createAttDetail(attributeDetail);
		if(IModelAttributeService.SUCCESS.equals(result.getCode())){
			this.setSuccessMessage(result.getResult());
		}else{
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 修改模板属性明细
	 * @return
	 */
	public String updateAttDetail(){
		attributeDetail = new PlanAttributeDetail();
		attributeDetail.setPlanAttDetailId(Long.parseLong(rid.trim()));
		attributeDetail.setPlanAttContent(planAttContent);
		attributeDetail.setPlanAttKey(planAttKey);
		attributeDetail.setPlanAttDataType(planAttDataType);
		attributeDetail.setPlanAttIsNull(planAttIsNull);
		StringResult result = modelAttributeService.updateAttDetail(attributeDetail);
		if(IModelAttributeService.SUCCESS.equals(result.getCode())){
			this.setSuccessMessage(result.getResult());
		}else{
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}
	
	public String deleteAttDetail(){
		StringResult result = modelAttributeService.deleteAttDetail(ids);
		if(IModelAttributeService.SUCCESS.equals(result.getCode())){
			this.setSuccessMessage(result.getResult());
		}else{
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}
	
	public String createModelAttribute(){
		return RESULT_MESSAGE;
	}
	
	public IModelAttributeService getModelAttributeService() {
		return modelAttributeService;
	}

	public void setModelAttributeService(
			IModelAttributeService modelAttributeService) {
		this.modelAttributeService = modelAttributeService;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getPlanTypeName() {
		return planTypeName;
	}

	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}

	public String getPlanAttFlag() {
		return planAttFlag;
	}

	public void setPlanAttFlag(String planAttFlag) {
		this.planAttFlag = planAttFlag;
	}

	public List<PlanAttribute> getPlanAttributeList() {
		return planAttributeList;
	}

	public void setPlanAttributeList(List<PlanAttribute> planAttributeList) {
		this.planAttributeList = planAttributeList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ActProcdef> getModelList() {
		return modelList;
	}

	public void setModelList(List<ActProcdef> modelList) {
		this.modelList = modelList;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	public String getAttMemo() {
		return attMemo;
	}

	public void setAttMemo(String attMemo) {
		this.attMemo = attMemo;
	}

	public PlanAttribute getPlanAttribute() {
		return planAttribute;
	}

	public void setPlanAttribute(PlanAttribute planAttribute) {
		this.planAttribute = planAttribute;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public PlanAttributeDetail getAttributeDetail() {
		return attributeDetail;
	}

	public void setAttributeDetail(PlanAttributeDetail attributeDetail) {
		this.attributeDetail = attributeDetail;
	}

	public String getPlanAttContent() {
		return planAttContent;
	}

	public void setPlanAttContent(String planAttContent) {
		this.planAttContent = planAttContent;
	}

	public List<PlanAttributeDetail> getAttributeDetailList() {
		return attributeDetailList;
	}

	public void setAttributeDetailList(List<PlanAttributeDetail> attributeDetailList) {
		this.attributeDetailList = attributeDetailList;
	}

	public List<CmsTbDict> getDataTypeList() {
		return dataTypeList;
	}

	public void setDataTypeList(List<CmsTbDict> dataTypeList) {
		this.dataTypeList = dataTypeList;
	}

	public String getPlanAttDataType() {
		return planAttDataType;
	}

	public void setPlanAttDataType(String planAttDataType) {
		this.planAttDataType = planAttDataType;
	}

	public String getPlanAttIsNull() {
		return planAttIsNull;
	}

	public void setPlanAttIsNull(String planAttIsNull) {
		this.planAttIsNull = planAttIsNull;
	}

	public String getPlanAttKey() {
		return planAttKey;
	}

	public void setPlanAttKey(String planAttKey) {
		this.planAttKey = planAttKey;
	}
	
}
