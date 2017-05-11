package com.kintiger.platform.org.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.conpoint.service.IConpointService;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.login.service.ILDAPService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.SapTOrgUnit;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.position.action.PositionTypeAction;
import com.kintiger.platform.qq_email.util.OperateDept;
import com.kintiger.platform.staffAmount.pojo.StaffAmount;
import com.kintiger.platform.staffAmount.service.IStaffService;

/**
 * org
 * 
 * @author jhzhou
 */
public class OrgAction extends BaseAction {

	private Logger logger = Logger.getLogger(PositionTypeAction.class);

	private static final long serialVersionUID = 1L;

	private IOrgService orgService;
	private IStaffService staffAmountService;
	protected String actionName;
	private String orgName;
	private String orgId;
	private Borg borg;
	private Long orgParentId;
	private int total = 0;
	private String orgParentName;
	private Long orgLevel;
	private List<Borg> companyList;
	private List<SapTOrgUnit> slist = new ArrayList<SapTOrgUnit>();
	private String orgUnit;
	private String orgIdIn;
	private String txtmd;
	private IConpointService conpointService;
	private String groupConpoinit;
	private String creatOrgConpoinit;
	private String updateorgConpoinit;
	private TransactionTemplate transactionTemplate;
	private ILDAPService LDAPService;
	private boolean validate;
	private String flag;
	private String orgBId;

	@PermissionSearch
	public String toOrgMangerMain() {

		return SUCCESS;
	}

	@PermissionSearch
	@JsonResult(field = "borg", include = { "orgId", "orgCode", "orgName","sapOrgId",
			"orgCity", "orgLevel", "orgRange", "orgParentId", "orgParentName",
			"shortName", "jianPing", "costCenter" })
	public String showOrgDetail() {
		// 获取权限点
		/*
		 * AllUsers users = this.getUser(); Conpoint conpoint = new Conpoint();
		 * conpoint.setUserId(users.getUserId());
		 * conpoint.setConpointId(BigDecimal.valueOf(750));
		 * conpoint.setCloseFlag("Y"); groupConpoinit =
		 * conpointService.isAut(conpoint);
		 * 
		 * Conpoint conpointCreate = new Conpoint();
		 * conpointCreate.setUserId(users.getUserId());
		 * conpointCreate.setConpointId(BigDecimal.valueOf(770));
		 * conpointCreate.setCloseFlag("Y"); creatOrgConpoinit =
		 * conpointService.isAut(conpointCreate);
		 * 
		 * Conpoint conpointUpdate = new Conpoint();
		 * conpointUpdate.setUserId(users.getUserId());
		 * conpointUpdate.setConpointId(BigDecimal.valueOf(771));
		 * conpointUpdate.setCloseFlag("Y"); updateorgConpoinit =
		 * conpointService.isAut(conpointUpdate);
		 */

		if (orgId != null) {
			borg = orgService.getOrgByOrgId(orgId);
		}
		return JSON;
	}

	@PermissionSearch
	public String toCreateOrg() {
		try {
			this.orgParentName = new String(
					orgParentName.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(orgParentName, e);
		}
		orgLevel = orgLevel + 1;
		return "toCreateOrg";
	}

	@PermissionSearch
	@JsonResult(field = "companyList", include = { "orgId", "orgName" }, total = "total")
	public String getCompanyList() {
		Borg borg = new Borg();
		borg = getSearchInfo(borg);
		if (StringUtils.isNotEmpty(orgId)
				&& StringUtils.isNotEmpty(orgId.trim())) {
			borg.setOrgId(Long.valueOf(orgId));
		}
		if (StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())) {
			borg.setOrgName(orgName);
		}

		total = orgService.getCompanyListCount(borg);
		if (total != 0) {
			companyList = orgService.getCompanyList(borg);
		}
		return JSON;
	}

	@PermissionSearch
	public String showSapOrg() {
		return "showSapOrg";
	}

	public String CreatOrg() {
		if (borg.getOrgCity().equals("C")||borg.getOrgCity().equals("A")) {
			borg.setOrganiseType("X");// 公司需要标记为X
		} else {
			borg.setOrganiseType("Z");
		}
		borg.setCreateTime(new Date());
		borg.setLastModify(new Date());
		borg.setState("Y");

		// 开启事务管理
		Object result = transactionTemplate.execute(new TransactionCallback() {
			String results = "";

			public Object doInTransaction(TransactionStatus ts) {
				boolean flag = true;
				// start获得AdGroupName和sAMAccountName
				String orgType = borg.getOrganiseType();
				String groupSN = borg.getShortName();
				String groupJP = borg.getJianPing();
				if (!"X".equals(orgType)) {
					Long pId = borg.getOrgParentId();
					if (pId != 0) {
						String tempSN = orgService.getOrgByOrgId(
								String.valueOf(pId)).getAdGroupName();
						groupSN = tempSN + "_" + groupSN;
						String tempJP = orgService.getOrgByOrgId(
								String.valueOf(pId)).getsAMAccountName();
						groupJP = tempJP + "_" + groupJP;
					} else {
						// pId为NULL,创建root
						pId = 0L;
						borg.setOrgParentId(pId);
					}
				}
				borg.setAdGroupName(groupSN);
				borg.setsAMAccountName(groupJP);
				// end获得AdGroupName和sAMAccountName
				if (validate) {
					flag = LDAPService.addOrg2Ad(borg);
				}
				if (flag) {
					BooleanResult booleanResult = orgService.createOrg(borg);
					if (!booleanResult.getResult()) {
						ts.setRollbackOnly();
						results = "F";
						return results;
					}
					
					results = "T";
					return results;
				} else {
					results = "FA";
					return results;
				}
			}
		});
		if ("F".equals(result.toString())) {
			this.setFailMessage("组织信息创建失败.");
		} else if ("T".equals(result.toString())) {
			//rtxDeptUtil.addDeptToRtx(borg);
			String path = orgService.getPartyPath(borg);
			System.out.println(path);
			OperateDept.AddDept(path);
			this.setSuccessMessage("组织信息创建成功.");
		} else if ("FA".equals(result.toString())) {
			this.setFailMessage("组织信息创建失败.AD域该组织信息创建异常");
		}
		return RESULT_MESSAGE;
	}

	// IMS组织
	@PermissionSearch
	public String ImsOrgCheck() {
		List<Borg> synOrgList = orgService.getOrgTreeListByPorgId(String
				.valueOf(borg.getOrgId()));
		if (synOrgList != null) {
			for (Borg org : synOrgList) {

				// start获得AdGroupName和sAMAccountName
				String orgType = org.getOrganiseType();
				String groupSN = org.getShortName();
				String groupJP = org.getJianPing();
				if (!"X".equals(orgType)) {
					Long pId = org.getOrgParentId();
					String tempSN = orgService.getOrgByOrgId(
							String.valueOf(pId)).getAdGroupName();
					groupSN = tempSN + "_" + groupSN;
					String tempJP = orgService.getOrgByOrgId(
							String.valueOf(pId)).getsAMAccountName();
					groupJP = tempJP + "_" + groupJP;
				}
				org.setAdGroupName(groupSN);
				org.setsAMAccountName(groupJP);
				// end获得AdGroupName和sAMAccountName
				BooleanResult booleanResult = orgService
						.updateBorgWithADInfo(org);
				if (booleanResult.getResult()) {
					this.setSuccessMessage("检查完毕.");
				} else {
					this.setFailMessage("在Ims[" + borg.getOrgName()
							+ "]下更新子组织sAMAccountName,ADGroupName属性失败！");
				}

			}
		} else {
			this.setFailMessage("在Ims[" + borg.getOrgName() + "]下寻找不到任何子组织！");
		}
		this.setSuccessMessage("检查完毕.");
		return RESULT_MESSAGE;

	}

	public String updateOrg() {
		boolean flag = true;
		BooleanResult imsbooleanResult = new BooleanResult();
		Borg oldBorg = orgService
				.getOrgByOrgId(String.valueOf(borg.getOrgId()));
		if (!borg.getOrgName().equals(oldBorg.getOrgName())) {
			if (validate) {
				flag = LDAPService.updateOrg2Ad(oldBorg, borg);
			}
		}
		if (borg.getOrgCity().equals("C")||borg.getOrgCity().equals("A")) {
			borg.setOrganiseType("X");
		}
		if (flag) {
			imsbooleanResult = orgService.updateBorg(borg);
			if (!imsbooleanResult.getResult()) {
				this.setFailMessage("组织信息修改失败.");
			} else {
				//rtxDeptUtil.updateDeptOnRtx(borg);
				this.setSuccessMessage("组织信息修改成功.请去维护邮箱组织架构");
			}
		} else {
			this.setFailMessage("组织信息修改失败.AD域该组织信息修改异常");
		}
		return RESULT_MESSAGE;
	}

	public String deleteOrg() {
		BooleanResult imsbooleanResult = new BooleanResult();
		StaffAmount staffAmount = new StaffAmount();
		String orgids = orgService.getFnAllChildStrOrg(orgId);
		if (StringUtils.isNotEmpty(orgids)) {
			staffAmount.setOrgIdarrs(orgids.split(","));
		}
		total = staffAmountService.getStaffTotal(staffAmount);
		if (total > 0) {
			this.setSuccessMessage("该组织结构下挂有岗位编制,不允许刪除");
		} else {
			String orgid[] = orgids.split(",");
			for (int i = orgid.length - 1; i >= 0; i--) {
				boolean flag = true;
				borg.setOrgId(Long.parseLong(orgid[i]));
				borg.setSourceId(orgid[i]);
				if (validate) {
					flag = LDAPService.deleteOrg2Ad(borg);
				}
				if (flag) {
					String path = orgService.getPartyPath(borg);
					imsbooleanResult = orgService.deleteBorg(borg);
					if (!imsbooleanResult.getResult()) {
						this.setFailMessage("组织结构删除失败.");
					} else {
						OperateDept.DelDept(path);
						this.setSuccessMessage("组织结构删除成功.");
					}
				} else {
					this.setFailMessage("组织结构删除失败.AD域该组织下含有人员.");
				}
			}
		}
		return RESULT_MESSAGE;
	}

	public String dropOrg() {
		BooleanResult imsbooleanResult = new BooleanResult();
		imsbooleanResult = orgService.dropBorg(borg);
		if (!imsbooleanResult.getResult()) {
			this.setFailMessage("组织结构调动失败.");
		} else {
			this.setSuccessMessage("组织结构调动成功.");
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String orgTreePage() {
		if("Y".equals(flag)){
		orgIdIn=this.getUser().getOrgId();
		}else{
		}
		return "orgtree";
	}

	@PermissionSearch
	public String toOrgTreeBySearch() {
		
		return "toOrgTreeBySearch";
	}
	
	 @PermissionSearch
	public String orgTreePage2() {
		return "orgtree2";
	}

	 @PermissionSearch
	  public String orgTreePage1()
	  {
	    int i = this.orgService.checkOrgCity(Long.valueOf(Long.parseLong(getUser().getUserId())));
	    
	    if (i != 0) {
	      return "orgTreeAll";
	    }
	    this.orgIdIn = getUser().getUserId();
	    return "orgtree1";
	  }
	
	@PermissionSearch
	public String orgSAPTreePage() {
		orgIdIn=this.getUser().getUserId();
		return "orgSAPTree";
	}

	public String orgTreePage4Post() {
		return "orgTreePage4Post";
	}

	/**
	 * 进入组织同步AD域页面
	 * 
	 * @return
	 */
	public String orgSynchronizPre() {
		return "orgSynchroniz";
	}

	/**
	 * 组织同步AD域
	 * 
	 * @return
	 */
	public String orgSynchroniz() {
		// 查询得到所有组织
		if (validate) {
			List<Borg> allOrgList = orgService.getOrgTreeListByOrgId("");
			if (allOrgList != null && allOrgList.size() > 0) {
				for (Borg org : allOrgList) {
					LDAPService.addOrg2Ad(org);
				}
			}
			this.setSuccessMessage("org#组织同步AD域完成!");
		}else{
			this.setFailMessage("未设置AD环境变量，无法同步!");
		}
		return RESULT_MESSAGE;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public Borg getBorg() {
		return borg;
	}

	public void setBorg(Borg borg) {
		this.borg = borg;
	}

	public Long getOrgParentId() {
		return orgParentId;
	}

	public void setOrgParentId(Long orgParentId) {
		this.orgParentId = orgParentId;
	}

	public Long getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Long orgLevel) {
		this.orgLevel = orgLevel;
	}

	public void setCompanyList(List<Borg> companyList) {
		this.companyList = companyList;
	}

	public List<SapTOrgUnit> getSlist() {
		return slist;
	}

	public void setSlist(List<SapTOrgUnit> slist) {
		this.slist = slist;
	}

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	public String getTxtmd() {
		return txtmd;
	}

	public void setTxtmd(String txtmd) {
		this.txtmd = txtmd;
	}

	public String getOrgParentName() {
		return orgParentName;
	}

	public void setOrgParentName(String orgParentName) {
		this.orgParentName = orgParentName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public IConpointService getConpointService() {
		return conpointService;
	}

	public void setConpointService(IConpointService conpointService) {
		this.conpointService = conpointService;
	}

	public String getGroupConpoinit() {
		return groupConpoinit;
	}

	public void setGroupConpoinit(String groupConpoinit) {
		this.groupConpoinit = groupConpoinit;
	}

	public String getCreatOrgConpoinit() {
		return creatOrgConpoinit;
	}

	public void setCreatOrgConpoinit(String creatOrgConpoinit) {
		this.creatOrgConpoinit = creatOrgConpoinit;
	}

	public String getUpdateorgConpoinit() {
		return updateorgConpoinit;
	}

	public void setUpdateorgConpoinit(String updateorgConpoinit) {
		this.updateorgConpoinit = updateorgConpoinit;
	}

	public IStaffService getStaffAmountService() {
		return staffAmountService;
	}

	public void setStaffAmountService(IStaffService staffAmountService) {
		this.staffAmountService = staffAmountService;
	}

	public ILDAPService getLDAPService() {
		return LDAPService;
	}

	public void setLDAPService(ILDAPService lDAPService) {
		LDAPService = lDAPService;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getOrgIdIn() {
		return orgIdIn;
	}

	public void setOrgIdIn(String orgIdIn) {
		this.orgIdIn = orgIdIn;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrgBId() {
		return orgBId;
	}

	public void setOrgBId(String orgBId) {
		this.orgBId = orgBId;
	}
	

}
