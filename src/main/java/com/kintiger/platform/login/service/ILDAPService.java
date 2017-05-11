package com.kintiger.platform.login.service;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.org.pojo.Borg;

public interface ILDAPService {

	/**
	 * �û�����֤
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public boolean authenticate(String passport, String password);

	/**
	 * �����û�
	 * 
	 * @param user
	 */
	public boolean addUser2Ad(AllUsers user);

	/**
	 * �޸��û�
	 * 
	 * @param user
	 */
	public boolean updateUser2Ad(AllUsers user,String oldOrg);

	/**
	 * ɾ���û�
	 * 
	 * @param user
	 */
	public boolean deleteUser2Ad(AllUsers user);
	
	
	
	/**
	 * �����û�
	 * 
	 * @param user
	 */
	public boolean disableUser2Ad(AllUsers user);

	/**
	 * ������֯
	 * 
	 * @param org
	 */
	public boolean addOrg2Ad(Borg org);

	/**
	 * �޸���֯
	 * 
	 * @param org
	 */
	public boolean updateOrg2Ad(Borg oldOrg,Borg newOrg);
	
	/**
	 * ������֯
	 * 
	 * @param org
	 */
	public boolean exchangeOrg2Ad(Borg org);


	/**
	 * ɾ����֯
	 * 
	 * @param org
	 */
	public boolean deleteOrg2Ad(Borg org);
	/**
	 * 
	 * �����˺�
	 */
	public boolean enableUser2Ad(AllUsers user);
	/**
	 * 
	 * �޸������˺�
	 */
	public boolean modifyPassword2Ad(AllUsers user);
	/**
	 * �ʻ�������֯
	 * @return
	 */
	public boolean changeGroup(AllUsers user,String newOrg);

}
