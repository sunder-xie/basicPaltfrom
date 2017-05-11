package com.kintiger.platform.login.service;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.org.pojo.Borg;

public interface ILDAPService {

	/**
	 * 用户域认证
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public boolean authenticate(String passport, String password);

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	public boolean addUser2Ad(AllUsers user);

	/**
	 * 修改用户
	 * 
	 * @param user
	 */
	public boolean updateUser2Ad(AllUsers user,String oldOrg);

	/**
	 * 删除用户
	 * 
	 * @param user
	 */
	public boolean deleteUser2Ad(AllUsers user);
	
	
	
	/**
	 * 禁用用户
	 * 
	 * @param user
	 */
	public boolean disableUser2Ad(AllUsers user);

	/**
	 * 新增组织
	 * 
	 * @param org
	 */
	public boolean addOrg2Ad(Borg org);

	/**
	 * 修改组织
	 * 
	 * @param org
	 */
	public boolean updateOrg2Ad(Borg oldOrg,Borg newOrg);
	
	/**
	 * 调拨组织
	 * 
	 * @param org
	 */
	public boolean exchangeOrg2Ad(Borg org);


	/**
	 * 删除组织
	 * 
	 * @param org
	 */
	public boolean deleteOrg2Ad(Borg org);
	/**
	 * 
	 * 启用账号
	 */
	public boolean enableUser2Ad(AllUsers user);
	/**
	 * 
	 * 修改密码账号
	 */
	public boolean modifyPassword2Ad(AllUsers user);
	/**
	 * 帐户更换组织
	 * @return
	 */
	public boolean changeGroup(AllUsers user,String newOrg);

}
