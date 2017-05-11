package com.kintiger.platform.activitiWebEdit.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.kintiger.platform.activitiWebEdit.pojo.WorkFlowRole;
import com.kintiger.platform.activitiWebEdit.service.RoleService;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;

public class DeployProcessDefinition extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private RoleService roleservice;



	private List<WorkFlowRole> roleList;
	
	private int total;
	@Decode
	private String processDefinitionName;
	
	private String roleName;
	/**
	 * 发布的流程XML string
	 */
	private String xmlString;
	/**
	 * 发布的流程名称
	 */
	private String processname;
	
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getXmlString() {
		return xmlString;
	}
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 初始化
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createEventPrepare() {

		return CREATE;
	}
	/**
	 * 新建流程
	 */
	@PermissionSearch
	public String createProcessDefinition(){
		return CREATE_PREPARE;
	}
	@PermissionSearch
	public String toSerachRoles(){
		return "toSerachRoles";
	}
	
	/**
	 * 角色查询
	 * @throws UnsupportedEncodingException 
	 */
	@PermissionSearch
	@JsonResult(field = "roleList", include = { "rolename","roleid","lastmodify" })
	public String serachRoles() {
		try {
			 roleName= new String (getServletRequest().getParameter("rolename").getBytes("ISO8859-1"), "UTF-8");
			 System.err.println(roleName);
			 WorkFlowRole wfr=new WorkFlowRole();
			 wfr.setRolename(roleName);
				roleList=roleservice.searchAllRoles(wfr);
		} catch (Exception e) {
		
		}

		return JSON;
	}
	/**
	 * User Task 属性 
	 * @return
	 */
	@PermissionSearch
	public String getTaskProperties(){

		return "taskproperties";
	}
	/**
	 * transition 属性
	 * @return
	 */
	@PermissionSearch
	public String getFlowProperties(){
		
		return "flowproperties";
	}
	/**
	 * 流程 属性
	 * @return
	 */
	@PermissionSearch
	public String getProcessProperties(){
		
		return "processproperties";
	}
	/**
	 * 分支属性
	 * @return
	 */
	@PermissionSearch
	public String getExclusiveGatewayProperties(){
		return "exclusiveGatewayProperties";
	}
	
	/**
	 * 发布流程
	 * @return
	 */
	public  String deployProcessDefinition(){
		this.setFailMessage("");
		this.setSuccessMessage("");
		if(roleservice.deployProcessDefinition(xmlString,processname)){	
			this.setSuccessMessage("流程发布成功!");
		}else{
			this.setFailMessage("流程发布出错!");
			
		}
		return RESULT_MESSAGE;
	}
	
	public RoleService getRoleservice() {
		return roleservice;
	}
	public void setRoleservice(RoleService roleservice) {
		this.roleservice = roleservice;
	}
	public List<WorkFlowRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<WorkFlowRole> roleList) {
		this.roleList = roleList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}
}
