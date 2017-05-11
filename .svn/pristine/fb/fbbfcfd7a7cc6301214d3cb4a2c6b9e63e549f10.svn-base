package com.kintiger.platform.allUser.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.pojo.Kunnr;
import com.kintiger.platform.allUser.service.IAllUserService;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.conpoint.service.IConpointService;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.mail.MailService;
import com.kintiger.platform.framework.util.EncryptUtil;
import com.kintiger.platform.framework.util.ParamsEncryptUtil;
import com.kintiger.platform.login.service.ILDAPService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.post.pojo.EmpPost;
import com.kintiger.platform.post.service.IPostService;
import com.kintiger.platform.qq_email.util.OperateUser;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.station.service.IStationService;



public class AllUserAction extends BaseAction {

	/**
	 * 
	 */
	private static final Log logger = LogFactory.getLog(AllUserAction.class);
	private static final long serialVersionUID = -4645806566226562765L;
	@Decode
	private String orgId;
	private String bhxjFlag;
	private String loginId;
	@Decode
	private String userName;
	private String userId;
	private String phone;
	private String mobile;
	private int total;
	private List<AllUsers> userList;
	@Decode
	private IAllUserService allUserService;
	private String ids;
	private List<Station> stationList;
	private String stationId;
	private String stationName;
	private IStationService stationService;
	private String loginId4Check;
	private AllUsers allUsers;
	private String roleIds;
	private String email;
	private String password;
	private String content;
	private String emailaddress;
	private String emailpassword;
	private String smtpServer;
	private String from;
	private String displayName;
	private Properties env;
	
	@Decode
	private String orgName;
	private String flag;// 标记 标记组织是否有重选过
	private String orgId4Update;// 页面有重复 orgId 所以用这个来传。。
	private String loginId4AD;
	private String kunnrId;
	@Decode
	private String name1;
	private List<Kunnr> kunnrList;
	private Kunnr kunnr;
	private String isoffKunnr;// 判断用户权限
	/**
	 * 错误信息
	 */
	private String failMessage;

	/**
	 * 成功信息
	 */
	private String successMessage;

	private String stationNames;

	private IOrgService iOrgService;

	private String repassWd;

	private String orgStr;

	private ILDAPService LDAPService;
	private String logins;
	private boolean validate;

	private List<EmpPost> empPostList;

	private String orgId4Post;

	private IPostService postService;

	private IDictService dictService; // 查看字典信息
	private List<CmsTbDict> cmsTbDictList = new ArrayList<CmsTbDict>();// 字典管理
	
	private boolean positiveRole;//这个发起转正的权限（杭州试用人员）

	/**
	 * 通讯录类似RTX
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchRtxPre() {
		return "rtx";
	}

	/**
	 * 通讯录人员信息
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "allUsers", include = { "userName", "sex", "orgName",
			"stationNames", "busMobilephone", "phone", "address", "email",
			"workFax" })
	public String userInfoInRtx() {
		allUsers = allUserService.getAllUsersByUserId(userId);
		Station station = new Station();
		station.setUserId(userId);
		List<Station> stationList = new ArrayList<Station>();
		stationList = stationService.searchStationUserMore(station);
		stationNames = "";
		if (stationList.size() != 0) {
			for (Station station2 : stationList) {
				stationNames += station2.getStationName() + ",";
			}
			stationNames = stationNames.substring(0, stationNames.length() - 1);
		}
		allUsers.setStationNames(stationNames);
		return JSON;
	}

	@PermissionSearch
	public String searchAddressBookPre() {
		AllUsers allUsers = this.getUser();
		orgId = allUsers.getOrgId();
		orgName = iOrgService.getorgname(Long.valueOf(orgId));
		return "searchAddressBookPre";
	}

	/**
	 * 老的版本 暂不用
	 * 
	 * @return
	 */
	@JsonResult(field = "userList", include = { "userId", "loginId",
			"userName", "userShowName", "userState", "haveMail", "posName",
			"positionTypeName", "orgId", "phone", "mobile", "email", "address" }, total = "total")
	public String searchAddressBook() {
		AllUsers allUser = new AllUsers();
		if (StringUtils.isNotEmpty(orgId)) {
			if ("C".equals(bhxjFlag)) {
				// String orgids = orgService.getFnAllChildStrOrg(orgId);
				// if (StringUtils.isNotEmpty(orgids)) {
				// allUser.setOrgIds(orgids.split(","));
			}
		} else {
			allUser.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(loginId)) {
			allUser.setLoginId(loginId);
		}
		if (StringUtils.isNotEmpty(userName)) {
			allUser.setUserName(userName);
		}
		if (StringUtils.isNotEmpty(phone)) {
			allUser.setPhone(phone);
		}
		if (StringUtils.isNotEmpty(mobile)) {
			allUser.setMobile(mobile);
		}
		total = allUserService.searchAllUsersCount(allUser);
		if (total != 0) {
			userList = allUserService.searchAllUsers(allUser);
		}
		return JSON;
	}

	@PermissionSearch
	public String userManage() {
		AllUsers allUsers = this.getUser();
		orgId = allUsers.getOrgId();
		orgName = iOrgService.getorgname(Long.valueOf(orgId));
		return "userManage";
	}

	/**
	 * 人员管理页面数据 查询
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@JsonResult(field = "userList", include = { "userId", "loginId",
			"userName", "userState", "orgId", "orgName", "mobile", "email",
			"address", "phone", "sex" }, total = "total")
	@PermissionSearch
	public String getUserInfoList() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		userList = null;
		AllUsers allUser = new AllUsers();
		allUser.setStart(getStart());
		allUser.setEnd(getEnd());
		if (StringUtils.isNotEmpty(orgId)) {
			if ("C".equals(bhxjFlag)) {
				String orgids = iOrgService.getFnAllChildStrOrg(orgId);
				if (StringUtils.isNotEmpty(orgids)) {
					allUser.setOrgIds(orgids.split(","));
				}
			} else {
				allUser.setOrgId(orgId);
			}
		} else {
			allUser.setOrgId(this.getUser().getOrgId());
		}
		if (StringUtils.isNotEmpty(loginId)) {
			allUser.setLoginId(loginId);
		}
		if (StringUtils.isNotEmpty(userName)) {
			allUser.setUserName(userName);
		}
		total = allUserService.searchAllUsersCount(allUser);
		if (total != 0) {
			userList = allUserService.searchAllUsers(allUser);
		} else {
			userList = null;
		}
		return JSON;
	}

	/**
	 * 禁用人员账号 根据人员 EMP_ID
	 * 
	 * @return
	 */
	public String forbidden() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		AllUsers allUsers = new AllUsers();
		allUsers.setUserId(userId);
		allUsers.setUserState("N");
		allUsers.setLoginId(loginId4AD);
		if (validate) {
			flag = LDAPService.disableUser2Ad(allUsers);
		}
		if (flag) {
			StringResult result = allUserService.forbidden(allUsers);
			if (SUCCESS.equals(result.getCode())) {
				this.setSuccessMessage("人员账号禁用成功");
			} else {
				this.setFailMessage(result.getResult());
			}
		} else {
			this.setSuccessMessage("人员账号禁用失败.AD域禁用人员账号异常");

		}
		return RESULT_MESSAGE;
	}

	/**
	 * 启用人员账号
	 * 
	 * @return
	 */
	public String startup() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		AllUsers allUsers = new AllUsers();
		allUsers.setUserId(userId);
		allUsers.setUserState("Y");
		allUsers.setLoginId(loginId4AD);
		if (validate) {
			flag = LDAPService.enableUser2Ad(allUsers);
		}
		if (flag) {
			StringResult result = allUserService.forbidden(allUsers);
			if (SUCCESS.equals(result.getCode())) {
				this.setSuccessMessage("人员账号启用成功");
			} else {
				this.setFailMessage(result.getResult());
			}
		} else {
			this.setSuccessMessage("人员账号启用失败.AD域启用人员账号异常");
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String toCreateUser() {
		return "toCreateUser";
	}

	/**
	 * 人员删除，先判断是否分配了岗位
	 * 
	 * @return
	 */
	public String deleteUserByEmpId() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		AllUsers allUsers = new AllUsers();
		String[] id = ids.split(",");
		String[] login = logins.split(",");
		int count = 0;
		int count_rtx=0;
		for (int i = 0; i < id.length; i++) {
			boolean flag = true;
			allUsers.setUserId(id[i]);
			allUsers.setLoginId(login[i]);
			String haveStation = stationService
					.getStationCountByEmpId(allUsers);
			if ("exist".equals(haveStation)) {
				this.setFailMessage("员工    "
						+ allUserService.getEmpNameByUserId(id[i])
						+ "  还处于岗位上，请先解除岗位再做删除");
				return RESULT_MESSAGE;
			}
			if (validate) {
				flag = LDAPService.deleteUser2Ad(allUsers);
			}
			if (flag) {
				StringResult result = allUserService
						.deleteUserByEmpId(allUsers);
				if (IConpointService.ERROR.equals(result.getCode())) {
					this.setFailMessage(result.getResult());
				} else {
					AllUsers allUsers2= allUserService.getAllUsersByUserId(allUsers.getUserId());
					//OperateUser.DelUser(allUsers2);
					//rtxUserUtil.deleteUserOnRtx(allUsers2.getRtx_LoginId());
					count++;
				}
				} 
			}
		this.setSuccessMessage("已成功删除" + count + "个人员!");
		return RESULT_MESSAGE;
	}
	
	/**
	 * 根据人员组织，找到该组织下所有有编制可用的岗位
	 * 
	 * @return
	 */
	@JsonResult(field = "stationList", include = { "id", "stationId",
			"stationName", "orgName", "userName" }, total = "total")
	@PermissionSearch
	public String getSelectedStationsJSON(){
		Station station = new Station();
		station = getSearchInfo(station);
		station.setStart(getStart());
		station.setEnd(getEnd());
		if (StringUtils.isNotEmpty(stationId)
				&& StringUtils.isNotEmpty(stationId.trim())) {
			try {
				stationId = new String(this.getStationId()
						.getBytes("ISO8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			station.setStationId(stationId.trim());
			station.setStationName(stationId.trim());
		}
		if (StringUtils.isNotEmpty(orgId)
				&& StringUtils.isNotEmpty(orgId.trim())) {
			station.setOrgId(Long.valueOf(orgId));
		}
		total = stationService.getStationJsonListCountForSelect(station);
		if (total != 0) {
			stationList = stationService.getStationJsonListForSelect(station);
		} else {
			stationList = null;
		}
		return JSON;
	}

	/**
	 * 判断登录名（loginId）是否存在
	 * 
	 * @return
	 */
	@PermissionSearch
	public String isLoginIdExist() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		String message = "";
		message = allUserService.getAllUserByLoginId(loginId4Check);
		if ("exist".equals(message)) {
			this.setFailMessage(loginId4Check + "已经存在，请在后面添加阿拉伯数字");
		} else if ("unexist".equals(message)) {
			this.setSuccessMessage("该用户名可以使用");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 重置密码(重置密码写配置文件里去）
	 * 
	 * @return
	 */
	public String resetPWd() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		AllUsers users = new AllUsers();
		if (StringUtils.isNotEmpty(userId)) {
			users.setUserId(userId);
			users.setLoginId(allUsers.getLoginId());
		} else {
			this.setFailMessage("用户为空，请重新登录再修改！");
			return RESULT_MESSAGE;
		}
		try {
			users.setPassWd(EncryptUtil.md5Encry("123456"));
			users.setExpressly("123456");
			if (validate) {
				flag = LDAPService.modifyPassword2Ad(users);
			}
			if (flag) {
				StringResult result = allUserService.updatePwd(users);
				if (IConpointService.ERROR.equals(result.getCode())) {
					this.setFailMessage(result.getCode());
				} else {
					AllUsers allUsers2= allUserService.getAllUsersByUserId(users.getUserId());
					this.setSuccessMessage("密码已经重置为123456！！");
				}
			} else {
				this.setFailMessage("密码重置失败.AD域账号密码重置失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 查找人员所分配的岗位
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchStationUser() {
		this.userId = userId;
		this.orgId = orgId;
		if (StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())) {
			try {
				this.orgName = new String(orgName.trim().getBytes("ISO8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "searchStationUser";
	}

	/**
	 * 通讯录的岗位查看页面 只能查看，不能做修改的。
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchStationUser4Book() {
		this.userId = userId;
		this.orgId = orgId;
		if (StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())) {
			try {
				this.orgName = new String(orgName.trim().getBytes("ISO8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "searchStationUser4Book";
	}

	/**
	 * 为人员配置岗位 先判断组织有无变更 然后根据岗位对应的的station_user里的ID 去把人员的emp_id(userId)添加进去
	 * 
	 * @return
	 */
	public String addUserStationById() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		boolean flag1 = true;
		AllUsers allUsers = new AllUsers();
		allUsers.setUserId(userId);
		allUsers.setLoginId(loginId);
		BooleanResult booleanResultForStation=new BooleanResult();
		if (!StringUtils.isEmpty(ids) && !StringUtils.isEmpty(ids.trim())) {
			String[] stationIds = ids.split(",");
			for (int i = 0; i <= stationIds.length - 1; i++) {
				allUsers.setIds(stationIds[i]);
				booleanResultForStation = stationService
						.updateStationUser(allUsers);
				if (!booleanResultForStation.getResult()) {
					this.setFailMessage(booleanResultForStation.getCode());
					return RESULT_MESSAGE;
				}
			}
		}
		
		if (!StringUtils.isEmpty(flag) && !StringUtils.isEmpty(flag.trim())) {
			allUsers.setOrgId(orgId4Update);
			if (validate) {
				Borg oldOrg = iOrgService.getOrgByUserId(userId);
				flag1 = LDAPService.changeGroup(allUsers, oldOrg.getOrgId()
						.toString());
			}
			if (flag1) {
				int n = allUserService.updateAllUserOrg(allUsers);
				if (n != 1) {
					this.setFailMessage("组织变更失败");
				}
			} else {
				this.setFailMessage("组织变更失败.AD域账号组织变更失败");
				return RESULT_MESSAGE;
			}
		}
		
		this.setSuccessMessage("岗位配置成功");
		return RESULT_MESSAGE;
	}

	/**
	 * 删除人员岗位 可批量删除
	 * 
	 * @return
	 */
	public String deleteUserStationById() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		Station station = new Station();
		station.setUserId(userId);
		int count = 0;
		if (!StringUtils.isEmpty(ids) && !StringUtils.isEmpty(ids.trim())) {
			String[] stationIds = ids.split(",");
			for (int i = 0; i <= stationIds.length - 1; i++) {
				station.setId(Long.valueOf(stationIds[i]));
				int n = stationService.deleteStationUserById(station);
				if (n == 1) {
					count++;
				} else {
					this.setFailMessage("删除失败");
				}
			}
		}
		this.setSuccessMessage("已经成功删除" + count + "个岗位");
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String toUpdatePassPre() throws Exception {
		this.email = ParamsEncryptUtil.Encrypt(this.email);
		this.successMessage = null;
		this.failMessage = null;
		return "toUpdatePassPre";
	}

	public String updatePass() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		AllUsers user = new AllUsers();
		user.setStart(0);
		user.setEnd(10);
		user.setEmail(email);
		try {
			user.setPassWd(EncryptUtil.md5Encry(password));
		} catch (Exception e) {
			e.printStackTrace();
			user.setPassWd("");
		}
		List<AllUsers> u = allUserService.searchAllUsers(user);
		if (u != null) {
			if (validate) {
				u.get(0).setExpressly(password);
				flag = LDAPService.modifyPassword2Ad(u.get(0));
			}
			if (flag) {
				BooleanResult result = allUserService.updateAllUser(user);
				if (IConpointService.ERROR.equals(result.getCode())) {
					this.setFailMessage("密码重置失败,请重试！");
				} else {
					this.setSuccessMessage("密码重置成功,请重新登录！");
				}
			} else {
				this.setFailMessage("密码重置失败.AD域账号密码修改失败");
			}
		}
		return RESULT_MESSAGE;

	}

	@PermissionSearch
	public String sendMailPre() {
		if (StringUtils.isNotEmpty(loginId)
				&& StringUtils.isNotEmpty(loginId.trim())) {
			try {
				loginId = new String(loginId.trim().getBytes("ISO8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(loginId, e);
			}
		}
		this.successMessage = null;
		this.failMessage = null;
		this.email = null;
		return "sendMailPre";
	}

	@JsonResult(field = "userList", include = { "userId","loginId", "userName", "email" }, total = "total")
	@PermissionSearch
	public String getEmailJsonList() {
		AllUsers allUser = new AllUsers();
		allUser.setStart(getStart());
		allUser.setEnd(getEnd());
		if (StringUtils.isNotEmpty(loginId)
				&& StringUtils.isNotEmpty(loginId.trim())) {
			allUser.setLoginId(loginId.trim());
		}
		total = allUserService.getEmpCount(allUser);
		if (total != 0) {
			userList = allUserService.getEmpList(allUser);
		} else {
			userList = null;
		}
		return JSON;
	}
	public String creatUser() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		String message = allUserService.getAllUserByLoginId(allUsers
				.getLoginId());
		if ("exist".equals(message)) {
			this.setFailMessage("用户" + allUsers.getLoginId()
					+ "已经存在，请在后面添加阿拉伯数字");
			return RESULT_MESSAGE;
		}
		try {
			allUsers.setUserState("Y");
			allUsers.setExpressly(allUsers.getPassWd());
			allUsers.setPassWd(EncryptUtil.md5Encry(allUsers.getPassWd()));
			if (validate) {
				flag = LDAPService.addUser2Ad(allUsers);
			}
			if (flag) {

				AllUsers aMobile1 = new AllUsers();
				aMobile1.setBusMobilephone(allUsers.getBusMobilephone());
				List<AllUsers> list1 = allUserService
						.getAllUserByMobile(aMobile1);
				if (list1.size() > 1) {
					this.setFailMessage("创建失败：" + aMobile1.getBusMobilephone()
							+ "  手机号码已经被注册");
					return RESULT_MESSAGE;
				}
				allUsers.setRtx_LoginId(allUsers.getLoginId());
				BooleanResult booleanResult = allUserService
						.createUser(allUsers);
				if (!booleanResult.getResult()) {
					this.setFailMessage(booleanResult.getCode());
				} else {
					if (!StringUtils.isEmpty(roleIds)
							&& !StringUtils.isEmpty(roleIds.trim())) {
						String[] ids = roleIds.split(",");
						for (int i = 0; i <= ids.length - 1; i++) {
							allUsers.setUserId(booleanResult.getCode());
							allUsers.setIds(ids[i]);
							BooleanResult booleanResultForStation = stationService
									.updateStationUser(allUsers);
							if (!booleanResultForStation.getResult()) {
								this.setFailMessage(booleanResultForStation
										.getCode());
							}
						}
					}
					//String end_AddUserToRtx = rtxUserUtil.addUserToRtx(allUsers);
					
					String path = iOrgService.getPartyPath(allUsers);
					//OperateUser.AddUser(allUsers,path);
					
					this.setSuccessMessage("人员创建成功.");
				}
			} else {
				this.setSuccessMessage("人员创建失败.AD域人员信息创建异常");
			}
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("人员创建失败");
		}
			//RtxsvrapiObj.UnInit();
			return RESULT_MESSAGE;
	}
	
	/**
	 * 跳转到用户信息修改页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toUpdateUserInfo() {
		allUsers = allUserService.getAllUsersByUserId(ids);
		this.userId = ids;
		Station station = new Station();
		station.setUserId(ids);
		List<Station> stationList = new ArrayList<Station>();
		stationList = stationService.searchStationUserMore(station);
		roleIds = "";
		stationNames = "";
		if (stationList.size() != 0) {
			for (Station station2 : stationList) {
				roleIds += station2.getId() + ",";
				stationNames += station2.getStationName() + ",";
			}
			roleIds = roleIds.substring(0, roleIds.length() - 1);
			stationNames = stationNames.substring(0, stationNames.length() - 1);
		}
		return "updateUserInfo";
	}

	public String updateUserInfo() {
		boolean flag = true;
		this.setSuccessMessage("");
		this.setFailMessage("");
		allUsers.setUserId(userId);
		Borg oldOrg = iOrgService.getOrgByUserId(userId);
		if (!allUsers.getOrgId().equals(oldOrg.getOrgId().toString())) {
			if (validate) {
				flag = LDAPService.updateUser2Ad(allUsers, oldOrg.getOrgId()
						.toString());
			}
		} else {
			if (validate) {
				flag = LDAPService.updateUser2Ad(allUsers, "");
			}
		}
		if (flag) {
			AllUsers aMobile = new AllUsers();
			aMobile.setBusMobilephone(allUsers.getBusMobilephone());
			List<AllUsers> list = allUserService.getAllUserByMobile(aMobile);
			if (list.size() > 1) {
				this.setFailMessage("修改失败：" + aMobile.getBusMobilephone()
						+ "  手机号码已经被注册");
				return RESULT_MESSAGE;
			} else if (list.size() == 1) {
				if (!allUsers.getLoginId().equals(list.get(0).getLoginId())) {
					this.setFailMessage("修改失败：" + aMobile.getBusMobilephone()
							+ "手机号码已经被注册");
					return RESULT_MESSAGE;
				}
			}
			BooleanResult booleanResult = allUserService
					.updateUserInfo(allUsers);
			if(booleanResult.getResult()){
				AllUsers allUsers2= allUserService.getAllUsersByUserId(allUsers.getUserId());
				//rtxUserUtil.updateUserInfoOnRtx(allUsers2);
				String path = iOrgService.getPartyPath(allUsers2);
				//OperateUser.alterUser(allUsers2,path);
			}
			if (!booleanResult.getResult()) {
				this.setFailMessage(booleanResult.getCode());
			}
			this.setSuccessMessage(booleanResult.getCode());
			if (!StringUtils.isEmpty(roleIds)
					&& !StringUtils.isEmpty(roleIds.trim())) {
				String[] ids = roleIds.split(",");
				for (int i = 0; i <= ids.length - 1; i++) {
					allUsers.setUserId(userId);
					allUsers.setIds(ids[i]);
					BooleanResult booleanResultForStation = stationService
							.updateStationUser(allUsers);
					if (!booleanResultForStation.getResult()) {
						this.setFailMessage(booleanResultForStation.getCode());
					}
				}
			}
			
		} else {
			this.setFailMessage("人员信息修改失败.AD域人员信息修改失败");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updatePwd() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		boolean flag = true;
		try {
			AllUsers user = new AllUsers();
			if (StringUtils.isNotEmpty(userId)) {
				user.setUserId(userId);
				user.setLoginId(allUsers.getLoginId());
			} else {
				this.setFailMessage("用户为空，请重新登录再修改！");
				return RESULT_MESSAGE;
			}
			user.setOrgId(allUsers.getOrgId());
			user.setPassWd(EncryptUtil.md5Encry(repassWd));
			user.setExpressly(repassWd);
			if (validate) {
				flag = LDAPService.modifyPassword2Ad(user);
			}
			if (flag) {
				StringResult stringResult = allUserService.updatePwd(user);
				if (IConpointService.ERROR.equals(stringResult.getCode())) {
					this.setFailMessage("密码修改失败,请重试！");
					
				} else {
					//AllUsers allUsers2= allUserService.getAllUsersByUserId(user.getUserId());
					//rtxUserUtil.upadteUserPwd(allUsers2.getRtx_LoginId(), repassWd);
					this.setSuccessMessage("密码修改成功,请重新登录！");
				}
			} else {
				this.setFailMessage("密码修改失败.AD域账号密码修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 修改初始密码
	 * 
	 * @return
	 */
	public String updateInitPwd() {
		boolean flag = true;
		try {
			AllUsers user;
			if (StringUtils.isNotEmpty(loginId)) {
				user = allUserService.getAllUserByPassport(loginId);
				user.setPassWd(EncryptUtil.md5Encry(repassWd));
				user.setExpressly(repassWd);
			} else if (repassWd.length() < 6) {
				this.setFailMessage("密码长度至少6位！");
				return RESULT_MESSAGE;
			} else {
				this.setFailMessage("登录账号丢失，请重新登录再修改！");
				return RESULT_MESSAGE;
			}
			if (validate) {
				flag = LDAPService.modifyPassword2Ad(user);
			}
			if (flag) {
				StringResult stringResult = allUserService.updatePwd(user);
				if (IConpointService.ERROR.equals(stringResult.getCode())) {
					this.setFailMessage("密码修改失败,请重试！");
				} else {
					this.setSuccessMessage("密码修改成功");
					//AllUsers allUsers2= allUserService.getAllUsersByUserId(user.getUserId());
					//rtxUserUtil.upadteUserPwd(allUsers2.getRtx_LoginId(), repassWd);
				}
			} else {
				this.setFailMessage("密码修改失败.AD域账号密码修改失败,请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String sendTenderMail() throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		AllUsers allUser = new AllUsers();
		if (StringUtils.isNotEmpty(loginId)
				&& StringUtils.isNotEmpty(loginId.trim())) {// 邮件收件人不为空loginId
			allUser.setLoginId(loginId.trim());
			userList = allUserService.getEmpList4Code(allUser);
			if (userList.size() != 0) {
				for (AllUsers allUser1 : userList) {
					String userNumber = "<br>&nbsp;&nbsp;您的姓名为："
							+ allUser1.getUserName()
							+ "&nbsp;&nbsp;<br>&nbsp;&nbsp;" + "您的登陆账号为："
							+ allUser1.getLoginId()
							+ "&nbsp;&nbsp;<br>&nbsp;&nbsp;请点击修改:"
							+ "<a href='" + env.getProperty("appUrl")
							+ "/allUserAction1/allUserAction1!toUpdatePassPre."
							+ "jspa?email="
							+ ParamsEncryptUtil.Encrypt(allUser1.getEmail())
							+ "'>点击此链接修改</a>"; // 供应商登陆信息
					String contents = "尊敬的用户" + userNumber + "<br>";
					if (null != allUser1.getEmail()) {
						MailService mail = new MailService(smtpServer, from,
								displayName, emailaddress, emailpassword,
								allUser1.getEmail(), "密码修改确认函", contents);// 邮件做成
						map = mail.send(); // 邮件发送
					} else {
						this.failMessage = "邮件发送失败,没有找到该账号的邮箱地址，请确认账号是否正确！<br>如账号是准确的，请联系管理员，确认邮箱信息是否维护！";
						return "warnMegs";
					}
					if ("success".equals(map.get("state"))) {
						this.successMessage =
						/* map.get("message") */"尊敬的用户"
								+ allUser1.getUserName()
								+ "，您好：<br>邮件已发送至您邮箱<font size=2 color=blue>"
								+ allUser1.getEmail() + "</font>，注意查收！";
					} else {
						this.failMessage = "邮件发送失败,请联系系统管理员！";
					}
				}
			} else {
				this.failMessage = "系统内无该用户,请确认账号是否正确！";
			}
		}
		return "warnMegs";

	}

	/**
	 * 查看人员详细信息
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchAllUserInfo() {
		allUsers = allUserService.getAllUser(userId);

		if (allUsers != null) {

			initOrgStr(Long.parseLong(allUsers.getOrgId()));

			// Bposition bposition = new Bposition();
			// bposition.setEmpId(Long.valueOf(userId));
			// bposition.setUseState("B");
			//
			// List<Bposition> pList = positionService.getPosition(bposition);
			//
			// if (pList != null && pList.size() > 0) {
			// StringBuffer str = new StringBuffer();
			// for (Bposition p : pList) {
			// if (str.length() != 0) {
			// str.append("*");
			// }
			// str.append(p.getPosId()).append(",").append(p.getPosName());
			// }
			// imgPosStr = str.toString();
			// }
		}

		return "searchAllUserInfo";
	}

	/**
	 * 查询向上组织结构
	 * 
	 * @param orgId
	 */
	@PermissionSearch
	private void initOrgStr(Long orgId) {
		StringBuffer o = new StringBuffer();

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
	}

	/**
	 * 根据组织树选择人员(跳转)
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toShowUserByOrgId() {
		return "toShowUserByOrgIdMain";
	}

	/**
	 * 根据组织ID查找人员信息
	 * 
	 * @return
	 */
	@JsonResult(field = "userList", include = { "userId", "posName",
			"userName", "orgId" })
	@PermissionSearch
	public String getEmpListByOrgId() {
		if (orgId != null && !"".equals(orgId)) {
			userList = allUserService.getEmpListByOrgId(orgId);
		}
		return JSON;
	}

	/**
	 * 查看个人信息非管理员权限
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toViewOfUserInfo() {
		AllUsers aUsers = this.getUser();
		userId = aUsers.getUserId();
		int num=iOrgService.getInAnotherOrgCount("51104",aUsers.getOrgId());//判断是否在杭州公司
		if(num==1&&"S".equals(aUsers.getUserState())){//杭州公司的状态为试用的人员
			positiveRole=true;
		}else{
			positiveRole=false;
		}
		allUsers = allUserService.getAllUsersByUserId(userId);
		Station station = new Station();
		station.setUserId(userId);
		List<Station> stationList = new ArrayList<Station>();
		stationList = stationService.searchStationUserMore(station);
		roleIds = "";
		stationNames = "";
		if (stationList.size() != 0) {
			for (Station station2 : stationList) {
				roleIds += station2.getId() + ",";
				stationNames += station2.getStationName() + ",";
			}
			roleIds = roleIds.substring(0, roleIds.length() - 1);
			stationNames = stationNames.substring(0, stationNames.length() - 1);
		}
		return "toViewOfUserInfo";
	}

	/**
	 * 跳转到经销商雇员信息修改页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toUpdateKunnrUserInfo() {
		allUsers = allUserService.getAllKunnrUsersByUserId(ids);
		this.userId = ids;
		if (null == getUser().getIsOffice()) { // 判断十分是总部客户
			kunnrId = "";
			if ("admin".equals(getUser().getLoginId())) {
				isoffKunnr = "A";
			}
		} else {
			kunnrId = getUser().getIsOffice();
		}
		return "updateKunnrUserInfo";
	}

	/**
	 * 跳转到经销商雇员信息修改页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toUpdateKunnrUser() {
		ids = this.getUser().getUserId();
		orgId = getUser().getOrgId();
		if (null == getUser().getIsOffice()) {
			kunnrId = "";
			if ("admin".equals(getUser().getLoginId())) {
				isoffKunnr = "A";
			}
		} else {
			kunnrId = getUser().getIsOffice();
		}
		allUsers = allUserService.getAllKunnrUsersByUserId(ids);
		System.out.println(allUsers.getIsOffice());
		this.userId = ids;
		return "toViewOfKunnrUserInfo";
	}

	public String updateKunnrUserInfo() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		allUsers.setUserId(userId);
		allUsers.setIsOffice(kunnrId);
		allUsers.setMobile(allUsers.getMobile().trim());
		allUsers.setBusMobilephone(allUsers.getBusMobilephone().trim());
		AllUsers aMobile = new AllUsers();
		aMobile.setMobile(allUsers.getMobile());
		List<AllUsers> list = allUserService.getAllUserByMobile(aMobile);
		if (null != list) {
			if (list.size() > 1) {
				this.setFailMessage("修改失败：" + aMobile.getMobile()
						+ " 手机号码已经被注册");
				return RESULT_MESSAGE;
			} else if (list.size() == 1) {
				if (!allUsers.getLoginId().equals(list.get(0).getLoginId())) {
					this.setFailMessage("修改失败：" + aMobile.getMobile()
							+ " 手机号码已经被注册");
					return RESULT_MESSAGE;
				}
			}
		}

		AllUsers aMobile1 = new AllUsers();
		aMobile1.setBusMobilephone(allUsers.getBusMobilephone());
		List<AllUsers> list1 = allUserService.getAllUserByMobile(aMobile1);
		if (null != list1) {
			if (list1.size() > 1) {
				this.setFailMessage("修改失败：" + aMobile1.getBusMobilephone()
						+ " 手机号码已经被注册");
				return RESULT_MESSAGE;
			} else if (list1.size() == 1) {
				if (!allUsers.getLoginId().equals(list1.get(0).getLoginId())) {
					this.setFailMessage("修改失败：" + aMobile1.getBusMobilephone()
							+ " 手机号码已经被注册");
					return RESULT_MESSAGE;
				}
			}
		}
		BooleanResult booleanResult = allUserService
				.updateKunnrUserInfo(allUsers);
		
		//同步经销商手机号码到经销商法人手机
		if(allUsers.getLoginId().equals(allUsers.getIsOffice())){
			allUserService.updateKunnrMobile(allUsers);
		}
		
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		this.setSuccessMessage(booleanResult.getCode());

		return RESULT_MESSAGE;
	}

	/***
	 * 经销商雇员查询
	 * 
	 * @return
	 */
	@PermissionSearch
	public String KunnruserManage() {
		AllUsers allUsers = this.getUser();
		orgId = allUsers.getOrgId();
		return "KunnruserManage";
	}

	/**
	 * 
	 * 經銷商查询结果
	 * 
	 * @return
	 */
	@JsonResult(field = "kunnrList", include = { "kunnr", "name1", "name3",
			"mobNumber", "bukrs", "channelName", "konzs", "street1",
			"telNumber", "businessManager", "businessCompetent", "bank",
			"bankAccount", "status", "orgId", "orgName" }, total = "total")
	public String kunnrSearch() {
		kunnr = new Kunnr();
		kunnr.setStart(getStart());
		kunnr.setEnd(getEnd());
		if (StringUtils.isNotEmpty(kunnrId)) {
			kunnr.setKunnr(kunnrId);
		}
		if (StringUtils.isNotEmpty(name1)) {
			kunnr.setName1(name1);
		}
		if ("admin".equals(getUser().getLoginId())) {

		} else if (!"A".equals(getUser().getCustType())) {
			kunnr.setBhxjFlag("C");
			kunnr.setOrgId(this.getUser().getOrgId());
		} else {
			kunnr.setOrgId(this.getUser().getOrgId());
		}
		// kunnr.setst
		total = allUserService.kunnrSearchCount(kunnr);
		if (total != 0) {
			kunnrList = allUserService.kunnrSearch(kunnr);
		}
		return JSON;
	}

	/**
	 * 
	 * 保存雇员
	 * 
	 * @return
	 */
	public String creatKunnrUser() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		String message = allUserService.getAllUserByLoginId(allUsers
				.getLoginId());
		allUsers.setMobile(allUsers.getMobile().trim());
		allUsers.setBusMobilephone(allUsers.getBusMobilephone().trim());
		if ("exist".equals(message)) {
			this.setFailMessage("用户" + allUsers.getLoginId()
					+ "已经存在，请在后面添加阿拉伯数字");
			return RESULT_MESSAGE;
		}
		try {
			CmsTbDict cmsTbDict = new CmsTbDict();
			List<CmsTbDict> cmsTbDicts = new ArrayList<CmsTbDict>();
			cmsTbDict.setItemName("雇员配置");
			cmsTbDicts = dictService.getByCmsTbDictList(cmsTbDict); // 获取事务申报页面可以选择到的流程
			String jxsrole = "";
			String role = "";
			if (null != cmsTbDicts) {
				for (CmsTbDict c : cmsTbDicts) {
					if ("雇员配置".equals(c.getItemName())) {
						role = c.getItemValue();
					}
					if ("经销商配置".equals(c.getItemName())) {
						jxsrole = c.getItemValue();
					}
				}
				if (!"".equals(role)) {
					allUsers.setPosName(role);// 不愿意加字段 用职位每次代替角色字段
				} else {
					this.setFailMessage("创建失败，请联系管理人员");
					return RESULT_MESSAGE;
				}
			}

			allUsers.setUserState("Y");
			allUsers.setExpressly(allUsers.getPassWd());
			allUsers.setOrgId(orgId);
			allUsers.setIsOffice(kunnrId);
			allUsers.setPassWd(EncryptUtil.md5Encry(allUsers.getPassWd()));
			// 判断用户 是否唯一手机号码
			AllUsers aMobile = new AllUsers();
			aMobile.setMobile(allUsers.getMobile());
			List<AllUsers> list = allUserService.getAllUserByMobile(aMobile);
			if (list.size() >= 1) {
				this.setFailMessage("创建失败：" + aMobile.getMobile()
						+ " 手机号码已经被注册");
				return RESULT_MESSAGE;
			}

			AllUsers aMobile1 = new AllUsers();
			aMobile1.setBusMobilephone(allUsers.getBusMobilephone());
			List<AllUsers> list1 = allUserService.getAllUserByMobile(aMobile1);

			if (list1.size() >= 1) {
				this.setFailMessage("创建失败：" + aMobile1.getBusMobilephone()
						+ "  手机号码已经被注册");
				return RESULT_MESSAGE;
			}
			AllUsers byid = new AllUsers();
			byid.setLoginId(kunnrId);
			byid.setStart(getStart());
			byid.setEnd(10);
			List<AllUsers> list4=allUserService.searchAllKunnrUsers(byid);
			if(list4.size()>0){
				byid = list4.get(0);
			}else{
				byid.setStaffNubmer(0L);
			}
			AllUsers byid1 = new AllUsers();
			byid1.setIsOffice(kunnrId);
			if ("admin".equals(getUser().getLoginId())) {
				if (kunnrId.equals(allUsers.getLoginId())) {
					allUsers.setPosName(jxsrole); // 根据编号和经销商匹配
					// allUsers.setStaffNubmer(staffNubmer)
				} else {
					allUsers.setPosName(role);
				}
			} else {
				int t = allUserService.searchAllKunnrUsersCount(byid1);
				if (t - 1 > byid.getStaffNubmer()) {
					this.setFailMessage("创建失败：编制数为" + byid.getStaffNubmer()
							+ "  请联系管理人员扩充");
					return RESULT_MESSAGE;
				}
			}

			BooleanResult booleanResult = allUserService
					.createKunnrUser(allUsers);
			if (!booleanResult.getResult()) {
				this.setFailMessage(booleanResult.getCode());
			} else {

				this.setSuccessMessage("雇员创建成功.");
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			this.setFailMessage("雇员创建失败");
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 雇员参加
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toCreateKunnrUser() {
		orgId = getUser().getOrgId();
		if (null == getUser().getIsOffice()) {
			kunnrId = "";
			if ("admin".equals(getUser().getLoginId())) {
				isoffKunnr = "A";
			}
		} else {
			kunnrId = getUser().getIsOffice();
		}

		return "toCreateKunnrUser";
	}

	/**
	 * 修改初始密码
	 * 
	 * @return
	 */
	public String updateKunnrInitPwd() {
		boolean flag = true;
		try {
			AllUsers user;
			if (StringUtils.isNotEmpty(loginId)) {
				user = allUserService.getAllUserByPassport(loginId);
				user.setPassWd(EncryptUtil.md5Encry(repassWd));
				user.setExpressly(repassWd);
			} else if (repassWd.length() < 6) {
				this.setFailMessage("密码长度至少6位！");
				return RESULT_MESSAGE;
			} else {
				this.setFailMessage("登录账号丢失，请重新登录再修改！");
				return RESULT_MESSAGE;
			}

			StringResult stringResult = allUserService.updateKunnrPwd(user);
			if (IConpointService.ERROR.equals(stringResult.getCode())) {
				this.setFailMessage("密码修改失败,请重试！");
			} else {
				this.setSuccessMessage("密码修改成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updateKunnrPwd() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		try {
			AllUsers user = new AllUsers();
			if (StringUtils.isNotEmpty(userId)) {
				user.setUserId(userId);
				user.setLoginId(allUsers.getLoginId());
			} else {
				this.setFailMessage("用户为空，请重新登录再修改！");
				return RESULT_MESSAGE;
			}
			user.setOrgId(allUsers.getOrgId());
			user.setPassWd(EncryptUtil.md5Encry(repassWd));
			user.setExpressly(repassWd);
			StringResult stringResult = allUserService.updateKunnrPwd(user);
			if (IConpointService.ERROR.equals(stringResult.getCode())) {
				this.setFailMessage("密码修改失败,请重试！");
			} else {
				this.setSuccessMessage("密码修改成功,请重新登录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 禁用人员账号 根据人员 EMP_ID
	 * 
	 * @return
	 */
	public String forKunnrbidden() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		AllUsers allUsers = new AllUsers();
		allUsers.setUserId(userId);
		allUsers.setUserState("N");
		allUsers.setLoginId(loginId4AD);
		StringResult result = allUserService.forKunnrbidden(allUsers);
		if (SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("人员账号禁用成功");
		} else {
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 启用人员账号
	 * 
	 * @return
	 */
	public String startKunnrup() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		AllUsers allUsers = new AllUsers();
		allUsers.setUserId(userId);
		allUsers.setUserState("Y");
		allUsers.setLoginId(loginId4AD);
		StringResult result = allUserService.forKunnrbidden(allUsers);
		if (SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("人员账号启用成功");
		} else {
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 雇员管理页面数据 查询
	 * 
	 * @return
	 */
	@JsonResult(field = "userList", include = { "userId", "loginId",
			"userName", "userState", "orgId", "orgName", "mobile", "email",
			"address", "phone", "sex", "busMobilephone" }, total = "total")
	@PermissionSearch
	public String getKunnrUserInfoList() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		userList = null;
		AllUsers allUser = new AllUsers();
		allUser.setStart(getStart());
		allUser.setEnd(getEnd());
		if (StringUtils.isNotEmpty(loginId)) {
			allUser.setLoginId(loginId);
		}
		if (StringUtils.isNotEmpty(userName)) {
			allUser.setUserName(userName);
		}
		if (StringUtils.isNotEmpty(orgName)) {
			allUser.setOrgName(orgName);
		}
		if (StringUtils.isNotEmpty(phone)) {
			allUser.setBusMobilephone(phone);
		}
		if ("A".equals(getUser().getCustType())) {
			allUser.setOrgId(getUser().getOrgId());
			allUser.setIsOffice(getUser().getIsOffice());
		} else if ("admin".equals(getUser().getLoginId())) {

		} else {
			allUser.setOrgId(getUser().getOrgId());
		}
		// allUser.setOrgId(getUser().getOrgId());
		total = allUserService.searchAllKunnrUsersCount(allUser);
		if (total != 0) {
			userList = allUserService.searchAllKunnrUsers(allUser);
		} else {
			userList = null;
		}
		return JSON;
	}

	/**
	 * 查看个人信息 经销商雇员
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toViewOfKunnrUserInfo() {
		AllUsers aUsers = this.getUser();
		userId = aUsers.getUserId();
		allUsers = allUserService.getAllUsersByUserId(userId);
		Station station = new Station();
		station.setUserId(userId);
		List<Station> stationList = new ArrayList<Station>();
		stationList = stationService.searchStationUserMore(station);
		roleIds = "";
		stationNames = "";
		if (stationList.size() != 0) {
			for (Station station2 : stationList) {
				roleIds += station2.getId() + ",";
				stationNames += station2.getStationName() + ",";
			}
			roleIds = roleIds.substring(0, roleIds.length() - 1);
			stationNames = stationNames.substring(0, stationNames.length() - 1);
		}
		return "toViewOfKunnrUserInfo";
	}

	/**
	 * 获取某一组织下未被分配的职务
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "empPostList", include = { "empPostId", "empPostName",
			"orgId", "orgName" }, total = "total")
	public String getEmpPostListJSON() {
		if (orgId4Post != null && !"".equals(orgId4Post)) {
			total = postService.getEmpPostCount(orgId4Post);
			if (total != 0) {
				empPostList = postService.getEmpPostList(orgId4Post);
			}
		} else {
			empPostList = null;
		}
		return JSON;
	}

	/**
	 * 人员同步AD域
	 * 
	 * @return
	 */
	public String userSynchroniz() {
		// 查询得到所有组织
		if (validate) {
			AllUsers u = new AllUsers();
			u.setPagination("false");
			List<AllUsers> allUserList = allUserService.searchAllUsers(u);
			if (allUserList != null && allUserList.size() > 0) {
				for (AllUsers user : allUserList) {
					// 密码明文重置123456 guest AD内置 不要创建
					if (!"guest".equals(user.getLoginId().toLowerCase())) {
						user.setExpressly("123456");
						LDAPService.addUser2Ad(user);
					}
				}
			}
			this.setSuccessMessage("user#人员同步AD域完成!");
		} else {
			this.setFailMessage("未设置AD环境变量，无法同步!");
		}
		return RESULT_MESSAGE;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getBhxjFlag() {
		return bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<AllUsers> getUserList() {
		return userList;
	}

	public void setUserList(List<AllUsers> userList) {
		this.userList = userList;
	}

	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public IStationService getStationService() {
		return stationService;
	}

	public void setStationService(IStationService stationService) {
		this.stationService = stationService;
	}

	public String getLoginId4Check() {
		return loginId4Check;
	}

	public void setLoginId4Check(String loginId4Check) {
		this.loginId4Check = loginId4Check;
	}

	public AllUsers getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(AllUsers allUsers) {
		this.allUsers = allUsers;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getEmailpassword() {
		return emailpassword;
	}

	public void setEmailpassword(String emailpassword) {
		this.emailpassword = emailpassword;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public Properties getEnv() {
		return env;
	}

	public void setEnv(Properties env) {
		this.env = env;
	}

	public String getStationNames() {
		return stationNames;
	}

	public void setStationNames(String stationNames) {
		this.stationNames = stationNames;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public IOrgService getiOrgService() {
		return iOrgService;
	}

	public void setiOrgService(IOrgService iOrgService) {
		this.iOrgService = iOrgService;
	}

	public String getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String orgStr) {
		this.orgStr = orgStr;
	}

	public String getRepassWd() {
		return repassWd;
	}

	public void setRepassWd(String repassWd) {
		this.repassWd = repassWd;
	}

	public ILDAPService getLDAPService() {
		return LDAPService;
	}

	public void setLDAPService(ILDAPService lDAPService) {
		LDAPService = lDAPService;
	}

	public String getLogins() {
		return logins;
	}

	public void setLogins(String logins) {
		this.logins = logins;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public List<EmpPost> getEmpPostList() {
		return empPostList;
	}

	public void setEmpPostList(List<EmpPost> empPostList) {
		this.empPostList = empPostList;
	}

	public String getOrgId4Post() {
		return orgId4Post;
	}

	public void setOrgId4Post(String orgId4Post) {
		this.orgId4Post = orgId4Post;
	}

	public IPostService getPostService() {
		return postService;
	}

	public void setPostService(IPostService postService) {
		this.postService = postService;
	}

	public String getLoginId4AD() {
		return loginId4AD;
	}

	public void setLoginId4AD(String loginId4AD) {
		this.loginId4AD = loginId4AD;
	}

	public String getOrgId4Update() {
		return orgId4Update;
	}

	public void setOrgId4Update(String orgId4Update) {
		this.orgId4Update = orgId4Update;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public List<CmsTbDict> getCmsTbDictList() {
		return cmsTbDictList;
	}

	public void setCmsTbDictList(List<CmsTbDict> cmsTbDictList) {
		this.cmsTbDictList = cmsTbDictList;
	}

	public String getKunnrId() {
		return kunnrId;
	}

	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public List<Kunnr> getKunnrList() {
		return kunnrList;
	}

	public void setKunnrList(List<Kunnr> kunnrList) {
		this.kunnrList = kunnrList;
	}

	public Kunnr getKunnr() {
		return kunnr;
	}

	public void setKunnr(Kunnr kunnr) {
		this.kunnr = kunnr;
	}

	public String getIsoffKunnr() {
		return isoffKunnr;
	}

	public void setIsoffKunnr(String isoffKunnr) {
		this.isoffKunnr = isoffKunnr;
	}

	public boolean isPositiveRole() {
		return positiveRole;
	}

	public void setPositiveRole(boolean positiveRole) {
		this.positiveRole = positiveRole;
	}

}
