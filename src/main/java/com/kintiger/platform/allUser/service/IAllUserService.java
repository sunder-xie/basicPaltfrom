package com.kintiger.platform.allUser.service;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.pojo.Kunnr;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;

/**
 * �ˆT��Ϣ�ӿ�<br>
 * �����ˆT������Ϣ ��I�ܘ�
 * 
 * 
 */
public interface IAllUserService {

	/**
	 * ��ѯ��Ա����ҵ�ܹ���
	 * 
	 * @param operateUser
	 * @return
	 */
	public List<AllUsers> searchAllUsers(AllUsers alluser);
	/**
	 * ��ѯ��Ա�������˴���ְ��Ա
	 */
	public List<AllUsers> searchAllUsers_all(AllUsers alluser);
	/**
	 * ��ѯ��������Ա
	 * @param alluser
	 * @return
	 */
	public List<AllUsers> searchKunnur_all(AllUsers alluser);
	/**
	 * ������֯ID������Ա��Ϣ
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getEmpListByOrgId(String orgId);

	/**
	 * ��ѯ��Աcount����ҵ�ܹ���
	 * 
	 * @param operateUser
	 * @return
	 */
	public int searchAllUsersCount(AllUsers alluser);
	
	/**
	 * 
	 * ɾ����Ա����Emp_state ��ΪD
	 */
	public StringResult deleteUserByEmpId(AllUsers allUsers);

	/**
	 * 
	 * @param ����passport�����û���Ϣ
	 * @return
	 */
	public AllUsers getAllUserByPassport(String passport);

	/**
	 * ������Ա
	 * 
	 * @param allUsers
	 * @return
	 */
	public BooleanResult updateAllUser(AllUsers allUsers);

	/**
	 * �жϵ�¼����loginId���Ƿ����
	 * 
	 * @return
	 */
	public String getAllUserByLoginId(String loginId);

	public int getEmpCount(AllUsers allUser);

	public List<AllUsers> getEmpList(AllUsers allUser);

	public List<AllUsers> getEmpList4Code(AllUsers allUser);

	/**
	 * ������Ա ��Ϣ
	 * 
	 * @param allUsers
	 * @return
	 */
	public BooleanResult createUser(AllUsers allUsers);

	/**
	 * ����UserId��������Ա��Ϣ��
	 * 
	 * @param ids
	 * @return
	 */
	public AllUsers getAllUsersByUserId(String ids);

	/**
	 * ����ID����Ա������
	 * 
	 * @param string
	 * @return
	 */
	public String getEmpNameByUserId(String userId);

	public StringResult forbidden(AllUsers allUsers);

	/**
	 * ������Ա����֯
	 * 
	 * @param allUsers
	 * @return
	 */
	public int updateAllUserOrg(AllUsers allUsers);

	/**
	 * �鿴��Ա��ϸ��Ϣ
	 * 
	 * @param userId
	 * @return
	 */
	public AllUsers getAllUser(String userId);

	/**
	 * �޸���Ա��Ϣ
	 * 
	 * @param allUsers
	 * @return
	 */
	public BooleanResult updateUserInfo(AllUsers allUsers);

	/**
	 * �޸�����
	 */
	public StringResult updatePwd(AllUsers allUsers);

	/**
	 * ��¼��¼
	 * 
	 * @param passport
	 */
	public void loginLog(AllUsers loginInfo);
	
	
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
	 * ������Ա
	 * @param allUsers
	 * @return
	 */
	public BooleanResult createKunnrUser(AllUsers allUsers);
	
	/***
	 * �޸Ĺ�Ա����
	 * 
	 * @param allUsers
	 * @return
	 */public StringResult updateKunnrPwd(AllUsers allUsers);

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
	public BooleanResult updateKunnrUserInfo(AllUsers allUsers);
	public StringResult forKunnrbidden(AllUsers allUsers);
	
	
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
