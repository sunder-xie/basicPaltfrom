package com.kintiger.platform.allUser.dao;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.pojo.Kunnr;

public interface IAllUserDao {

	/**
	 * ��ѯ��Ա����ҵ�ܹ���
	 * 
	 * @param operateUser
	 * @return
	 */
	public List<AllUsers> searchAllUsers(AllUsers alluser);

	public List<AllUsers> searchAllUsers_all(AllUsers alluser);
	
	public List<AllUsers> searchKunnur_all(AllUsers alluser);
	/**
	 * ��ѯ��Աcount����ҵ�ܹ���
	 * 
	 * @param operateUser
	 * @return
	 */
	public int searchAllUsersCount(AllUsers alluser);
	
	/**
	 * ������֯ID������Ա��Ϣ
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
	 * �жϵ�¼����loginId���Ƿ����
	 * 
	 * @return
	 */
	public int getAllUserByLoginId(String loginId);

	public int getEmpCount(AllUsers allUsers);

	public List<AllUsers> getEmpList(AllUsers alluser);

	public List<AllUsers> getEmpList4Code(AllUsers alluser);

	/**
	 * ������Ա ��Ϣ
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
	 * �޸�����
	 */
	public long updatePwd(AllUsers allUsers);

	/**
	 * ��¼��¼
	 * 
	 * @param passport
	 */
	public void loginLog(AllUsers loginInfo);

	/**
	 * ��ȡ��¼������֯��������֯
	 * 
	 * @param allUsers
	 * @return
	 */
	public List<String> getLoginOrgs(AllUsers allUsers);

	/**
	 * ��ѯ��Ա
	 * 
	 * @param operateUser
	 * @return
	 */
	public List<AllUsers> searchAllKunnrUsers(AllUsers alluser);

	/**
	 * ��ѯ��Աcount����Ա��
	 * 
	 * @param operateUser
	 * @return
	 */
	public int searchAllKunnrUsersCount(AllUsers alluser);

	/***
	 * ���������̹�Ա
	 * 
	 * @param allUsers
	 * @return
	 */
	public long createKunnrUser(AllUsers allUsers);

	/***
	 * �޸Ĺ�Ա����
	 * 
	 * @param allUsers
	 * @return
	 */
	public long updateKunnrPwd(AllUsers allUsers);

	/****
	 * ��ѯ������Ա��Ϣ
	 * 
	 * @param ids
	 * @return
	 */
	public AllUsers getAllKunnrUsersByUserId(String ids);

	/****
	 * �޸Ĺ�Ա��Ϣ
	 * 
	 * @param allUsers
	 * @return
	 */
	public int updateKunnrUserInfo(AllUsers allUsers);

	public int forKunnrbidden(AllUsers allUsers);

	public long createKunnrUserRole(AllUsers allUsers);

	/**
	 * 
	 * �����̲�ѯCOUNT
	 * 
	 * @return
	 */
	public int kunnrSearchCount(Kunnr kunnr);

	/**
	 * 
	 * �����б��̲�ѯ
	 * 
	 * @return
	 */
	public List<Kunnr> kunnrSearch(Kunnr kunnr);

	/**
	 * ��ѯ�ֻ�����
	 * @param passport
	 * @return
	 */
	public List<AllUsers> getAllUserByMobile(AllUsers allUsers);
	
	/**
	 * ͬ���������ֻ����뵽�����ֻ�
	 * @param passport
	 * @return
	 */
	public int updateKunnrMobile(AllUsers allUsers);
}
