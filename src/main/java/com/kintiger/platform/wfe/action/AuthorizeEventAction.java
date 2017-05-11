package com.kintiger.platform.wfe.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.pojo.ProEventLookUp;
import com.kintiger.platform.wfe.service.IAuthorizeEventService;

/**
 * 授权事务查看
 *
 */
public class AuthorizeEventAction extends BaseAction{
	
	private static final long serialVersionUID = -7060431423089202176L;
	private String eventId;
	@Decode
	private String eventTitle;
	@Decode
	private String initator;
	@Decode
	private String modelName;
	private String orgId;
	private List<AllUsers> empList;
	private IAuthorizeEventService authorizeEventService;
	private String userIds;
	private List<ProEventLookUp> proEventLookUpList;
	@Decode
	private String userName;
	private int total = 0;
	private String ids;
	private List<ProcessEventTotal> processEventTotalList;
	
	private String from;

	/**
	 * 页面跳转（事务查阅人）
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchEventReader() {
		return "toSearchEventReader";
	}
	
	/**
	 * 页面跳转（授权）
	 * @return
	 */
	@PermissionSearch
	public String toAuthorizeAddMain(){
		return "toAuthorizeAddMain";
	}
	
	/**
	 * 根据组织ID查找人员信息
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "empList", include = { "userId", "posName", 
	"userName", "orgId"} )
	public String getEmpListByOrgId(){
		if(orgId!=null && !"".equals(orgId)){
			empList = authorizeEventService.getEmpListByOrgId(orgId);
		}
		
		return JSON;
		
	}
	
	/**
	 * 创建授权事务查看
	 * 
	 * @return
	 */
	public String createAuthorization(){
		if(StringUtils.isEmpty(userIds) || StringUtils.isEmpty(eventId)){
			this.setFailMessage(IAuthorizeEventService.ERROR_MESSAGE);
			return RESULT_MESSAGE;
		}
		ProEventLookUp proEventLookUp = new ProEventLookUp();
		proEventLookUp.setEventId(Long.valueOf(eventId));
		proEventLookUp.setCreator(getUser().getUserId());
		proEventLookUp.setUserIds(userIds.split(","));
		
		StringResult result = authorizeEventService.createAuthorization(proEventLookUp);
		if(IAuthorizeEventService.ERROR.equals(result.getCode())){
			this.setFailMessage(IAuthorizeEventService.ERROR_MESSAGE);
		}else{
			this.setSuccessMessage(IAuthorizeEventService.SUCCESS_MESSAGE);
		}
		
		return RESULT_MESSAGE;
	}
	
	/**
	 * 删除授权事务查看
	 * @return
	 */
	public String deleteAuthorization(){
		String[] lookUpIds = ids.split(",");
		StringResult result = authorizeEventService.deleteAuthorization(lookUpIds);
		if(IAuthorizeEventService.ERROR.equals(result.getCode())){
			this.setFailMessage(IAuthorizeEventService.ERROR_MESSAGE);
		}else{
			this.setSuccessMessage(IAuthorizeEventService.SUCCESS_MESSAGE);
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 查看授权信息
	 * 
	 * @return
	 */
	@JsonResult(field = "proEventLookUpList", include = { "eventLookUpId",
			"orgName", "userName", "creator", "createDate" }, total = "total")
	@PermissionSearch		
	public String getEventReaderList(){
		ProEventLookUp proEventLookUp = new ProEventLookUp();
		proEventLookUp.setStart(getStart());
		proEventLookUp.setEnd(getEnd());
		proEventLookUp.setCreator(getUser().getUserId());
		proEventLookUp.setEventId(Long.valueOf(eventId));
		proEventLookUp.setUserName(userName);

		total = authorizeEventService.getEventReaderListCount(proEventLookUp);
		if(total != 0){
			proEventLookUpList = authorizeEventService.getEventReaderList(proEventLookUp);
		}
		return JSON;
	}
	
	/**
	 * 授权事务查看
	 * 
	 * @return
	 */
	@JsonResult(field = "processEventTotalList", include = { "eventId", "currentDetailid", 
			"eventTitle", "initator", "status", "modelId", "creatdate", "empName", "modelName" }, total = "total")
	@PermissionSearch
	public String getAuthorizeEventJsonList(){
		ProcessEventTotal processEventTotal = new ProcessEventTotal();
		processEventTotal.setUserId(getUser().getUserId());
		if(StringUtils.isNotEmpty(eventId)){
			processEventTotal.setEventId(Long.parseLong(eventId));
		}
		processEventTotal.setEventTitle(eventTitle);
		processEventTotal.setEmpName(initator);
		processEventTotal.setModelName(modelName);
		processEventTotal.setStart(getStart());
		processEventTotal.setEnd(getEnd());
		processEventTotal.setSort("a.creatdate");
		processEventTotal.setDir("desc");
		total = authorizeEventService.getAuthorizeEventJsonListCount(processEventTotal);
		if(total != 0) {
			processEventTotalList = authorizeEventService.getAuthorizeEventJsonList(processEventTotal);
		} else{
			processEventTotalList = null;
		}
		
		return JSON;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<AllUsers> getEmpList() {
		return empList;
	}

	public void setEmpList(List<AllUsers> empList) {
		this.empList = empList;
	}

	public IAuthorizeEventService getAuthorizeEventService() {
		return authorizeEventService;
	}

	public void setAuthorizeEventService(
			IAuthorizeEventService authorizeEventService) {
		this.authorizeEventService = authorizeEventService;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public List<ProEventLookUp> getProEventLookUpList() {
		return proEventLookUpList;
	}

	public void setProEventLookUpList(List<ProEventLookUp> proEventLookUpList) {
		this.proEventLookUpList = proEventLookUpList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<ProcessEventTotal> getProcessEventTotalList() {
		return processEventTotalList;
	}

	public void setProcessEventTotalList(
			List<ProcessEventTotal> processEventTotalList) {
		this.processEventTotalList = processEventTotalList;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getInitator() {
		return initator;
	}

	public void setInitator(String initator) {
		this.initator = initator;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
