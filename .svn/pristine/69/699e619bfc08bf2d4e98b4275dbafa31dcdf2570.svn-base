package com.kintiger.platform.post.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.post.pojo.EmpPost;
import com.kintiger.platform.post.service.IPostService;

public class PostAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Decode
	private String empPostName;
	
	@Decode
	private String empOrgName;

	private String empOrgId;
	
	private EmpPost empPost;
	
	private long empPostId; //主键ID
	
	private String empId;//分配的人员ID
	
	private Date createDate;//创建日期
	
	private String state;//状态  N 正常   D 删除
	
	private List<EmpPost> empPostList;
	
	private int total;
	@Decode
	private String bhxjFlag;
	
	private String orgId;
	
	private String orgName;
	
	private String empPostIds;
	
	private IOrgService iOrgService;
	
	private IPostService postService;
	@PermissionSearch
	public String toEmpPost(){
		 AllUsers allUsers = this.getUser();
		 orgId= allUsers.getOrgId();
		 orgName = iOrgService.getorgname(Long.valueOf(orgId));
		return "toEmpPost";
	}
	@PermissionSearch
	public String toCreateEmpPost(){
		return "toCreateEmpPost";
	}
	
	/**
	 * 查询组织下职务
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "empPostList", include = { "empPostId","empPostName","orgId","orgName","empId","createDate" },
			total = "total")
	public String getEmpPostList(){
		EmpPost empPost = new EmpPost();
		empPost.setStart(getStart());
		empPost.setEnd(getEnd());
		
		if(StringUtils.isNotEmpty(empPostName)){
			empPost.setEmpPostName(empPostName);
		}
		if(StringUtils.isNotEmpty(orgId)){
			if ("C".equals(bhxjFlag)) {
				String orgids = iOrgService.getFnAllChildStrOrg(orgId);
				if (StringUtils.isNotEmpty(orgids)) {
					empPost.setOrgIds(orgids.split(","));
				}
			} else {
				empPost.setOrgId(orgId);
			}
		}else{
			empPost.setOrgId(this.getUser().getOrgId());
		}
		total = postService.getEmpPostCount(empPost);
		if(total != 0){
			empPostList = postService.getEmpPostList(empPost);
		}else {
			empPostList = null;
		}
		return JSON;
	}
	
	/**
	 * 创建职务
	 * @return
	 */
	public String createEmpPost(){
		this.setFailMessage("");
		this.setSuccessMessage("");
		empPost.setOrgId(empOrgId);
		try {
			empOrgName = new String(this.getEmpOrgName().getBytes("ISO8859-1"), "utf-8");
			empPost.setOrgName(empOrgName);
			Boolean flag = checkEmpPostName(empPost);
			if(flag){
				BooleanResult result = postService.createEmpPost(empPost);
				if(result.getResult()){
					this.setSuccessMessage("职务创建成功,是否关闭当前页面？");
				}else{
					this.setFailMessage(result.getCode());
				}
			}else {
				this.setFailMessage("该组织下已经存在该职务名,请重新输入职务名");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 验证该组织下 该职务名是否被用过
	 * @param empPost
	 * @return
	 */
	public Boolean checkEmpPostName(EmpPost empPost){
		Boolean flag = true;
		int n = postService.getPostNameCount(empPost);
		if(n>=1){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 删除职务 JS里已经判断是否被使用
	 * @return
	 */
	public String deleteEmpPostById(){
		this.setFailMessage("");
		this.setSuccessMessage("");
		EmpPost empPost = new EmpPost();
		String[] ids = empPostIds.split(",");
		empPost.setEmpPostIds(ids);
		empPost.setState("D");
		BooleanResult result = postService.deleteEmpPostById(empPost);
		if(result.getResult()){
			this.setSuccessMessage("已经成功删除"+result.getCode()+"条职务");
		}else{
			this.setFailMessage(result.getCode());
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * 查询向上组织结构
	 * 
	 * @param orgId
	 */
	private String initOrgStr(Long orgId) {
		StringBuffer o = new StringBuffer();
		String orgStr = "";
		List<Borg> orgs = iOrgService.getAllParentOrgs(orgId);
		if (orgs != null && orgs.size() > 0) {
			for (Borg org : orgs) {
				if (o.length() != 0) {
					o.append("\\");
				}
				o.append(org.getOrgName());
			}

			orgStr = o.toString();
		}
		return orgStr;
	}
	
	public String getEmpPostName() {
		return empPostName;
	}

	public void setEmpPostName(String empPostName) {
		this.empPostName = empPostName;
	}

	public String getEmpOrgName() {
		return empOrgName;
	}
	public void setEmpOrgName(String empOrgName) {
		this.empOrgName = empOrgName;
	}
	public String getEmpOrgId() {
		return empOrgId;
	}

	public void setEmpOrgId(String empOrgId) {
		this.empOrgId = empOrgId;
	}

	public IPostService getPostService() {
		return postService;
	}

	public void setPostService(IPostService postService) {
		this.postService = postService;
	}

	public EmpPost getEmpPost() {
		return empPost;
	}

	public void setEmpPost(EmpPost empPost) {
		this.empPost = empPost;
	}
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public long getEmpPostId() {
		return empPostId;
	}
	public void setEmpPostId(long empPostId) {
		this.empPostId = empPostId;
	}
	public void setEmpPostList(List<EmpPost> empPostList) {
		this.empPostList = empPostList;
	}
	public IOrgService getiOrgService() {
		return iOrgService;
	}
	public void setiOrgService(IOrgService iOrgService) {
		this.iOrgService = iOrgService;
	}
	public String getBhxjFlag() {
		return bhxjFlag;
	}
	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getEmpPostIds() {
		return empPostIds;
	}
	public void setEmpPostIds(String empPostIds) {
		this.empPostIds = empPostIds;
	}
	
}
