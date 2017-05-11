package com.kintiger.platform.dict.action;

import java.util.ArrayList;
import java.util.List;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;



public class DictAction extends BaseAction {

	private static final long serialVersionUID = 5042752280539471298L;

	private List<CmsTbDict> cmsTbDictList = new ArrayList<CmsTbDict>();
 
	private List<CmsTbDictType> cmsTbDictTypeList = new ArrayList<CmsTbDictType>();

	private IDictService dictService;
	private StringResult stringResult = new StringResult();
	private int total;

	@Decode
	private String dictTypeName;
	@Decode
	private String remark;
	@Decode
	private String dictTypeValue;

	private long dictTypeId;
	private long itemId;

	private CmsTbDict cmsTbDict;

	private CmsTbDictType cmsTbDictType;
	
	private String ids;

	/**
	 * 查询CmsTbDict字典
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchCmsTbDict() {
		this.dictTypeId=dictTypeId;
		return "searchCmsTbDict";
	}

	/**
	 * CmsTbDict字典配置
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cmsTbDictList", include = { "itemId", "itemName",
			"itemValue", "parentItemId", "itemDescription", "remark",
			"lastModify", "dictTypeId" }, total = "total")
	public String getCmsTbDictJsonList() {
		CmsTbDict m = new CmsTbDict();
		//m = getSearchInfo(m);
		m.setStart(getStart());
		m.setEnd(getEnd());
		m.setDictTypeId(dictTypeId);
		total = dictService.getCmsTbDictCount(m);
		if (total != 0) {
			cmsTbDictList = dictService.getCmsTbDictList(m);
		}

		return JSON;
	}

	/**
	 * CmsTbDict字典配置
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cmsTbDictList", include = { "itemId", "itemValue",
			"itemState", "remark" }, total = "total")
	public String getDictJsonList() {
		CmsTbDict m = new CmsTbDict();
		//m = getSearchInfo(m);
		m.setStart(getStart());
		m.setEnd(getEnd());
		if (!"".equals(dictTypeValue) && dictTypeValue != null) {
			m.setDictTypeValue(dictTypeValue);
		}
		total = dictService.getDictCount(m);
		if (total != 0) {
			cmsTbDictList = dictService.getDictList(m);
		}

		return JSON;
	}

	/**
	 * 查询CmsTbDictType字典
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchCmsTbDictType() {
		return "searchCmsTbDictType";
	}

	/**
	 * CmsTbDictType字典配置
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cmsTbDictTypeList", include = { "dictTypeId",
			"dictTypeName", "dictTypeValue", "remark", "lastModify" }, total = "total")
	public String getCmsTbDictTypeJsonList() {
		CmsTbDictType m = new CmsTbDictType();
//		m = getSearchInfo(m);
		m.setStart(getStart());
		m.setEnd(getEnd());
		m.setDictTypeName(dictTypeName);
		m.setDictTypeValue(dictTypeValue);
		m.setRemark(remark);
		total = dictService.getCmsTbDictTypeCount(m);
		if (total != 0) {
			cmsTbDictTypeList = dictService.getCmsTbDictTypeList(m);
		}

		return JSON;
	}

	@PermissionSearch
	public String toCreateDictType() {
		return "toCreateDictType";
	}

	@PermissionSearch
	public String toCreateDict() {
		this.dictTypeId=dictTypeId;
		return "toCreateDict";
	}

	public String CreateDictType() {
		this.setSuccessMessage("创建成功.");

		BooleanResult booleanResult = dictService.createDictType(cmsTbDictType);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String CreateDict() {
		this.setSuccessMessage("创建成功.");
		BooleanResult booleanResult = dictService.createDict(cmsTbDict);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}
	@PermissionSearch
	public String toUpdateDictType() {
		CmsTbDictType searhCmsTbDictType = new CmsTbDictType();
		searhCmsTbDictType.setDictTypeId(dictTypeId);
		cmsTbDictType = dictService.getCmsTbDictType(searhCmsTbDictType);
		return "toUpdateDictType";
	}
	@PermissionSearch
	public String toUpdateDict() {
		CmsTbDict searchCmsTbDict = new CmsTbDict();
		searchCmsTbDict.setItemId(itemId);
		cmsTbDict = dictService.getCmsTbDict(searchCmsTbDict);
		return "toUpdateDict";
	}

	public String UpdateDict() {
		this.setSuccessMessage("更新成功.");
		BooleanResult booleanResult = dictService.updateDict(cmsTbDict);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	public String UpdateDictType() {
		this.setSuccessMessage("更新成功.");
		BooleanResult booleanResult = dictService.updateDictType(cmsTbDictType);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	public String DeleteDict() {
		this.setSuccessMessage("操作成功！");
		String[] l = ids.split(",");
		CmsTbDict deleteCmsTbDict = new CmsTbDict();
		deleteCmsTbDict.setCodes(l);
		deleteCmsTbDict.setItemState("D");
		BooleanResult booleanResult = dictService.updateDict(deleteCmsTbDict);
		if (!booleanResult.getResult()) {
			//stringResult.setResult("操作失败");
			//stringResult.setCode(booleanResult.getCode());
			this.setFailMessage("操作失败!");

		}
		return RESULT_MESSAGE;
	}

	public String DeleteDictType() {
		this.setSuccessMessage("操作成功！");
		CmsTbDictType deleteCmsTbDictType = new CmsTbDictType();
		deleteCmsTbDictType.setDictTypeId(dictTypeId);
		deleteCmsTbDictType.setDictTypeState("D");
		CmsTbDict dict=new CmsTbDict();
		dict.setDictTypeId(dictTypeId);
		int r=dictService.getCmsTbDictCount(dict);
		if(r>0){
			this.setFailMessage("类型下有条目，请先删除条目!");
		}else{
		BooleanResult booleanResult = dictService
				.updateDictType(deleteCmsTbDictType);
		if (!booleanResult.getResult()) {
			/*stringResult.setResult("操作失败！");
			stringResult.setCode(booleanResult.getCode());*/
			this.setFailMessage("操作失败!");

		}}
		return RESULT_MESSAGE;
	}

	public List<CmsTbDict> getCmsTbDictList() {
		return cmsTbDictList;
	}

	public void setCmsTbDictList(List<CmsTbDict> cmsTbDictList) {
		this.cmsTbDictList = cmsTbDictList;
	}

	public List<CmsTbDictType> getCmsTbDictTypeList() {
		return cmsTbDictTypeList;
	}

	public void setCmsTbDictTypeList(List<CmsTbDictType> cmsTbDictTypeList) {
		this.cmsTbDictTypeList = cmsTbDictTypeList;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDictTypeValue() {
		return dictTypeValue;
	}

	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}

	public long getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(long dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public CmsTbDict getCmsTbDict() {
		return cmsTbDict;
	}

	public void setCmsTbDict(CmsTbDict cmsTbDict) {
		this.cmsTbDict = cmsTbDict;
	}

	public CmsTbDictType getCmsTbDictType() {
		return cmsTbDictType;
	}

	public void setCmsTbDictType(CmsTbDictType cmsTbDictType) {
		this.cmsTbDictType = cmsTbDictType;
	}

	public StringResult getStringResult() {
		return stringResult;
	}

	public void setStringResult(StringResult stringResult) {
		this.stringResult = stringResult;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
