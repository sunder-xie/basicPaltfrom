package com.kintiger.platform.allUser.dao;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.pojo.Kunnr;

public interface IAllUserDao {

	/**
	 * 查询人员（企业架构）
	 * 
	 * @param operateUser
	 * @return
	 */
	public List<AllUsers> searchAllUsers(AllUsers alluser);

	public List<AllUsers> searchAllUsers_all(AllUsers alluser);
	
	public List<AllUsers> searchKunnur_all(AllUsers alluser);
	/**
	 * 查询人员count（企业架构）
	 * 
	 * @param operateUser
	 * @return
	 */
	public int searchAllUsersCount(AllUsers alluser);
	
	/**
	 * 根据组织ID查找人员信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getEmpListByOrgId(String orgId);

	public int deleteUserByEmpId(AllUsers allUsers);

	/**
	 * 
	 * @param passport
	 * @return
	 */
	public AllUsers getAllUserByPassport(String passport);

	/**
	 * 
	 * @param allUsers
	 * @return
	 */
	public int updateAllUser(AllUsers allUsers);

	/**
	 * 判断登录名（loginId）是否存在
	 * 
	 * @return
	 */
	public int getAllUserByLoginId(String loginId);

	public int getEmpCount(AllUsers allUsers);

	public List<AllUsers> getEmpList(AllUsers alluser);

	public List<AllUsers> getEmpList4Code(AllUsers alluser);

	/**
	 * 创建人员 信息
	 * 
	 * @param allUsers
	 * @return
	 */
	public long createUser(AllUsers allUsers);

	public AllUsers getAllUsersByUserId(String ids);

	public String getEmpNameByUserId(String userId);

	public int forbidden(AllUsers allUsers);

	public int updateAllUserOrg(AllUsers allUsers);

	public AllUsers getAllUser(String userId);

	public int updateUserInfo(AllUsers allUsers);

	/**
	 * 修改密码
	 */
	public long updatePwd(AllUsers allUsers);

	/**
	 * 登录记录
	 * 
	 * @param passport
	 */
	public void loginLog(AllUsers loginInfo);

	/**
	 * 获取登录人主组织及兼种组织
	 * 
	 * @param allUsers
	 * @return
	 */
	public List<String> getLoginOrgs(AllUsers allUsers);

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
	 * 创建经销商雇员
	 * 
	 * @param allUsers
	 * @return
	 */
	public long createKunnrUser(AllUsers allUsers);

	/***
	 * 修改雇员密码
	 * 
	 * @param allUsers
	 * @return
	 */
	public long updateKunnrPwd(AllUsers allUsers);

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
	public int updateKunnrUserInfo(AllUsers allUsers);

	public int forKunnrbidden(AllUsers allUsers);

	public long createKunnrUserRole(AllUsers allUsers);

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
