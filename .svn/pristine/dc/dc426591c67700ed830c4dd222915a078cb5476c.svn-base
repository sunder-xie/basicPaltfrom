package com.kintiger.platform.allUser.service;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.pojo.Kunnr;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;

/**
 * 人員信息接口<br>
 * 包含人員基本信息 企業架構
 * 
 * 
 */
public interface IAllUserService {

	/**
	 * 查询人员（企业架构）
	 * 
	 * @param operateUser
	 * @return
	 */
	public List<AllUsers> searchAllUsers(AllUsers alluser);
	/**
	 * 查询人员，包括了待入职人员
	 */
	public List<AllUsers> searchAllUsers_all(AllUsers alluser);
	/**
	 * 查询经销商人员
	 * @param alluser
	 * @return
	 */
	public List<AllUsers> searchKunnur_all(AllUsers alluser);
	/**
	 * 根据组织ID查找人员信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getEmpListByOrgId(String orgId);

	/**
	 * 查询人员count（企业架构）
	 * 
	 * @param operateUser
	 * @return
	 */
	public int searchAllUsersCount(AllUsers alluser);
	
	/**
	 * 
	 * 删除人员，把Emp_state 置为D
	 */
	public StringResult deleteUserByEmpId(AllUsers allUsers);

	/**
	 * 
	 * @param 根据passport查找用户信息
	 * @return
	 */
	public AllUsers getAllUserByPassport(String passport);

	/**
	 * 更新人员
	 * 
	 * @param allUsers
	 * @return
	 */
	public BooleanResult updateAllUser(AllUsers allUsers);

	/**
	 * 判断登录名（loginId）是否存在
	 * 
	 * @return
	 */
	public String getAllUserByLoginId(String loginId);

	public int getEmpCount(AllUsers allUser);

	public List<AllUsers> getEmpList(AllUsers allUser);

	public List<AllUsers> getEmpList4Code(AllUsers allUser);

	/**
	 * 创建人员 信息
	 * 
	 * @param allUsers
	 * @return
	 */
	public BooleanResult createUser(AllUsers allUsers);

	/**
	 * 根据UserId来查找人员信息。
	 * 
	 * @param ids
	 * @return
	 */
	public AllUsers getAllUsersByUserId(String ids);

	/**
	 * 根据ID查找员工姓名
	 * 
	 * @param string
	 * @return
	 */
	public String getEmpNameByUserId(String userId);

	public StringResult forbidden(AllUsers allUsers);

	/**
	 * 更改人员的组织
	 * 
	 * @param allUsers
	 * @return
	 */
	public int updateAllUserOrg(AllUsers allUsers);

	/**
	 * 查看人员详细信息
	 * 
	 * @param userId
	 * @return
	 */
	public AllUsers getAllUser(String userId);

	/**
	 * 修改人员信息
	 * 
	 * @param allUsers
	 * @return
	 */
	public BooleanResult updateUserInfo(AllUsers allUsers);

	/**
	 * 修改密码
	 */
	public StringResult updatePwd(AllUsers allUsers);

	/**
	 * 登录记录
	 * 
	 * @param passport
	 */
	public void loginLog(AllUsers loginInfo);
	
	
	/**
	 * 查询雇员
	 * 
	 * @param operateUser
	 * @return
	 */
	public List<AllUsers> searchAllKunnrUsers(AllUsers alluser);

	/**
	 * 查询人员count（雇员）
	 * 
	 * @param operateUser
	 * @return
	 */
	public int searchAllKunnrUsersCount(AllUsers alluser);
	/***
	 * 创建雇员
	 * @param allUsers
	 * @return
	 */
	public BooleanResult createKunnrUser(AllUsers allUsers);
	
	/***
	 * 修改雇员密码
	 * 
	 * @param allUsers
	 * @return
	 */public StringResult updateKunnrPwd(AllUsers allUsers);

	/****
	 * 查询单个雇员信息
	 * 
	 * @param ids
	 * @return
	 */
	public AllUsers getAllKunnrUsersByUserId(String ids);

	/****
	 * 修改雇员信息
	 * 
	 * @param allUsers
	 * @return
	 */
	public BooleanResult updateKunnrUserInfo(AllUsers allUsers);
	public StringResult forKunnrbidden(AllUsers allUsers);
	
	
	/**
	 * 
	 * 经销商查询COUNT
	 * 
	 * @return
	 */
	public int kunnrSearchCount(Kunnr kunnr);

	/**
	 * 
	 * 经销列表商查询
	 * 
	 * @return
	 */
	public List<Kunnr> kunnrSearch(Kunnr kunnr);
	
	/**
	 * 查询手机号码
	 * @param passport
	 * @return
	 */
	public List<AllUsers> getAllUserByMobile(AllUsers allUsers);
	
	/**
	 * 同步经销商手机号码到法人手机
	 * @param passport
	 * @return
	 */
	public int updateKunnrMobile(AllUsers allUsers);
}
