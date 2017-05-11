package com.kintiger.platform.org.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.service.IAllUserService;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringArrayResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.menu.pojo.Tree4Ajax;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.SapTOrgUnit;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.position.action.PositionTypeAction;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.station.service.IStationService;

/**
 * OrgTreeAjax
 * 
 * @author xujiakun
 * 
 */
public class OrgTreeAjaxAction extends BaseAction {

	private Logger logger = Logger.getLogger(PositionTypeAction.class);

	private static final long serialVersionUID = 9124918976690173831L;

	private String node;

	private String orgId;

	private @Decode String orgName;

	private String sapOrgId;

	private String actionName;

	private List<Tree4Ajax> treeList;

	private IOrgService orgService;

	private StringArrayResult companyInfo = new StringArrayResult();

	private String userId;

	private IAllUserService allUserService;
	
	private String flag;

	private List<Borg> companyList;
	
	private String orgBId;
	
	private IDictService dictService;
	
	private IStationService stationService;
	
	@PermissionSearch
	public String getOrgTreeMain() {
		return SUCCESS;
	}

	/**
	 * 组织树 点击组织 查看该组织详细信息
	 * 
	 * @return
	 */
	@PermissionSearch
	public String getOrgTreeInfo() {
		// AllUsers users = this.getUser();
		this.actionName = "orgAction!showOrgDetail.jspa";
		this.node = "1";
		return "orgTreeAjaxInfo";
	}

	@PermissionSearch
	public String getOrgTreeInfo4User() {
		// AllUsers users = this.getUser();
		this.actionName = env.getProperty("appUrl")
				+ "/alluser/allUserAction!showUserByOrg.jspa";
		this.node = "1";
		return "orgTreeAjaxInfo";
	}

	@PermissionSearch
	public String toGetOrgTreeInfo4Position() {
		return "orgTreePosition";
	}

	@PermissionSearch
	public String getOrgTreeInfo4Position() {
		this.actionName = env.getProperty("appUrl")
				+ "/alluser/allUserAction!toGetPositionList.jspa";
		this.node = "1";
		return "orgTreeAjaxInfo";
	}

	/**
	 * 组织树 根据总部标记 判断是否显示整个组织树
	 * 
	 * @return
	 */
	@PermissionSearch
	public String getOrgTree() {
		AllUsers users = this.getUser();
		if ("1".equals(users.getHqSign())) {
			return "orgTreeAjax";
		} else {
			this.node = "-1";
			return "orgTreeAjax";
		}
	}

	/**
	 * 组织树 显示整个组织树
	 * 
	 * @return
	 */
	@PermissionSearch
	public String getOrgAllTree() {
		return "orgTreeAjax";
	}

	@PermissionSearch
	public String getOrgTree4UserChange() {
		return "orgTreeAjax4UserChange";
	}
	/**
	 * 获取树形结构
	 * 组织树形
	 * 经销商树形
	 * TODO：貌似这个后台的方法是共用的，只需要添加相应的页面和JS代码跳转这里即可
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getOrgTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
	//	List<Borg> orgTreeList = null;
		companyList = new  ArrayList<Borg>();
		try {
		if("Y".equals(flag)){
				Borg borg = new Borg();
				borg = orgService.getOrgByUserId(node);
				if(StringUtils.isNotEmpty(orgBId) && StringUtils.isNotEmpty(orgBId.trim())){
					CmsTbDict cmsTbDict = new CmsTbDict();
					cmsTbDict.setEnd(99999);
					cmsTbDict.setDictTypeValue("orgBId");
					List<CmsTbDict> list = dictService.getDictList(cmsTbDict);
					List<Station> stationList = stationService.getStationByEmpId(Long.valueOf(this.getUser().getUserId()));
					boolean b=false;//b 判断是否是在字典内
					for(int i=0;i<list.size();i++){
						if(orgBId.equals(list.get(i).getItemValue())){
							if(stationList != null && stationList.size()>1){
								b=true;//在字典中并且有次岗位
								Long id = orgService.getporgid(borg.getOrgId());
								borg = orgService.getOrgByOrgId(String.valueOf(id));
								companyList.add(borg);
								break;
							}
						}
					}
					if(!b){//如果不在组织追溯的字典中或者没有次岗位，则循环主次岗位的组织，添加到list中
						for(Station s:stationList){
							borg = orgService.getOrgByOrgId(String.valueOf(s.getOrgId()));
							companyList.add(borg);
						}
					}
				}
				
//				companyList.add(borg);
//我把添加移入到for循环内判断				
		}else{
			   companyList = orgService.getOrgTreeListByPorgId(node);
		}
		} catch (Exception e) {
			logger.error(node, e);
		}
		if (companyList == null || companyList.size() == 0) {
			return JSON;
		}
		for (Borg borg : companyList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(borg.getOrgId()));
			tree.setText(borg.getOrgName());
			tree.setState("closed");
			treeList.add(tree);
		}

		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "companyList", include = { "orgId", "orgName", "adGroupName" })
	public String searchOrgTreeList() {
		companyList = new  ArrayList<Borg>();
		Borg borg=new Borg();
		boolean search=false;
		if (StringUtils.isNotEmpty(orgId)
				&& StringUtils.isNotEmpty(orgId.trim())) {
			borg.setOrgId(Long.valueOf(orgId));
			search=true;
		}
		if (StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())) {
			borg.setOrgName(orgName);
			search=true;
		}
		if(!search){
			borg.setOrgId(Long.valueOf("50919"));
		}
		try {
		
			   companyList = orgService.searchOrgTreeList(borg);
		
		} catch (Exception e) {
			logger.error(node, e);
		}
		
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "orgUnit" })
	public String getSapOrgTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();

		List<SapTOrgUnit> sapOrgTreeList = null;
		try {
			if (StringUtils.isNotEmpty(node)) {
				sapOrgTreeList = orgService.getSapTOrgUnitListByPId(node);
			}
		} catch (Exception e) {
			logger.error(node, e);
		}

		if (sapOrgTreeList == null || sapOrgTreeList.size() == 0) {
			return JSON;
		}

		for (SapTOrgUnit sapTOrgUnit : sapOrgTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(sapTOrgUnit.getNodeid()));
			tree.setText(sapTOrgUnit.getTxtsh() + ":"
					+ sapTOrgUnit.getOrgUnit());
			treeList.add(tree);
		}

		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "companyInfo", include = { "result", "code" })
	public String getCompany() {
		companyInfo.setCode("T");
		String[] result = new String[2];
		if (!StringUtils.isNotEmpty(orgId)) {
			companyInfo.setCode("F");
			result[0] = "未传入参数orgId";
		}
		Borg b = orgService.getOrgByOrgId(orgId);
		if (b.getCompanyName() != null) {
			result[0] = String.valueOf(b.getCompanyId());
			result[1] = String.valueOf(b.getCompanyName());
		} else {
			result[0] = String.valueOf("未查组织ID为" + orgId + "的公司");
		}
		companyInfo.setResult(result);
		return JSON;
	}

	@PermissionSearch
	public String getOrgNameByOrgid() {

		return "orgInfo";
	}

	/**
	 * 组织人员树 通讯录RTX
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state",
			"iconCls" })
	public String getOrgUserTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		List<Borg> orgTreeList = null;
		List<AllUsers> userTreeList = null;
		try {
			userTreeList = allUserService.getEmpListByOrgId(node);
			orgTreeList = orgService.getOrgTreeListByPorgId(node);
		} catch (Exception e) {
			logger.error(node, e);
		}
		for (AllUsers user : userTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(user.getUserId());
			tree.setText(user.getUserName());
			// tree.setState("closed");
			if ("M".equals(user.getSex()))
				tree.setIconCls("icon-male");
			else
				tree.setIconCls("icon-female");
			treeList.add(tree);
		}
		for (Borg borg : orgTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(borg.getOrgId()));
			tree.setText(borg.getOrgName());
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSON;
	}
	
	
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getSapOrgTreeListForBo() {
		treeList = new ArrayList<Tree4Ajax>();
	//	List<Borg> orgTreeList = null;
		companyList = new  ArrayList<Borg>();
		try {
		if("Y".equals(flag)){
				Borg borg = new Borg();
				borg = orgService.getOrgByUserId(node);
				companyList.add(borg);
		}else{
			   companyList = orgService.getOrgTreeListByPorgId(node);
		}
		} catch (Exception e) {
			logger.error(node, e);
		}
		if (companyList == null || companyList.size() == 0) {
			return JSON;
		}
		for (Borg borg : companyList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(borg.getOrgId()+":"+borg.getSapOrgId());
			tree.setText(borg.getOrgName()+":"+borg.getSapOrgId());
			tree.setState("closed");
			treeList.add(tree);
		}

		return JSON;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
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

	public String getSapOrgId() {
		return sapOrgId;
	}

	public void setSapOrgId(String sapOrgId) {
		this.sapOrgId = sapOrgId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public StringArrayResult getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(StringArrayResult companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<Borg> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Borg> companyList) {
		this.companyList = companyList;
	}

	public String getOrgBId() {
		return orgBId;
	}

	public void setOrgBId(String orgBId) {
		this.orgBId = orgBId;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public IStationService getStationService() {
		return stationService;
	}

	public void setStationService(IStationService stationService) {
		this.stationService = stationService;
	}

}
