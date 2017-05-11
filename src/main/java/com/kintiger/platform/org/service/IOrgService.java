package com.kintiger.platform.org.service;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.SapTOrgUnit;

/**
 * �M���ӿ�
 * 
 */
public interface IOrgService {

	/**
	 * ����userId��ѯ������֯������ӳ����֯�����¼� cms.cms_api_pack.fn_user_org_list
	 * 
	 * @param userId
	 * @return
	 */
	public List<Borg> getOrgListByUserId(String userId);

	/**
	 * ����orgId��ѯ������֯������ӳ����֯��
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Borg> getOrgTreeListByOrgId(String orgId);

	/**
	 * ����pOrgId������֯��ѯ������֯������ӳ����֯��
	 * 
	 * @param pOrgId
	 * @return
	 */
	public List<Borg> getOrgTreeListByPorgId(String pOrgId);
	
	/**
	 * ����borg�õ���������·��
	 * @param borg
	 * @return
	 */
	public String getPartyPath(Borg borg);
	
	/**
	 * �����û��õ���������·��
	 * @param allUsers
	 * @return
	 */
	public String getPartyPath(AllUsers allUsers);
	/**
	 * ����orgId��ѯ��֯
	 * 
	 * @param orgId
	 * @return
	 */
	public Borg getOrgByOrgId(String orgId);

	/**
	 * ����org�鹫˾list����
	 * 
	 * @return
	 */
	public int getCompanyListCount(Borg borg);

	/**
	 * ����org�鹫˾list
	 * 
	 * @return
	 */
	public List<Borg> getCompanyList(Borg borg);

	/**
	 * ��ѯ��Ա��֯��λ
	 * 
	 * @return
	 */
	public List<SapTOrgUnit> getSapTOrgUnitListByPId(String pId);

	/**
	 * ������֯
	 * 
	 * @return
	 */
	public BooleanResult createOrg(Borg borg);

	/**
	 * 
	 * ͨ����webservice ����AD�½�OU jhzhou
	 * 
	 * @param orgName
	 *            �½�ou��,�½�Ou��orgID
	 * @return
	 */
	public BooleanResult NewOU(String orgName, String orgId, String parenOrgtId);

	/**
	 * 
	 * ͨ����webservice ����AD ���� jhzhou
	 * 
	 * @param sAMAccountName
	 *            ��������� ;orgId ����������ou��orgId
	 * @return
	 */
	public BooleanResult NewGroup(String groupName, String sAMAccountName,
			String orgId);

	/**
	 * 
	 * ����borg��ad�������Ϣ group
	 * 
	 * @param borg
	 * @return BooleanResult
	 */
	public BooleanResult updateBorgWithADInfo(Borg borg);

	/**
	 * 
	 * ����borg
	 * 
	 * @param borg
	 * @return BooleanResult
	 */

	public BooleanResult updateBorg(Borg borg);
	
	
	/**
	 * 
	 * ����borg
	 * 
	 * @param borg
	 * @return BooleanResult
	 */

	public BooleanResult dropBorg(Borg borg);
	
	public int checkOrgCity(Long paramLong);
	
	/**
	 * 
	 * ɾ��borg
	 * 
	 * @param borg
	 * @return BooleanResult
	 */

	public BooleanResult deleteBorg(Borg borg);

	/**
	 * 
	 * AD ��֯�ƶ������
	 * 
	 * @param String
	 *            orgId:���ƶ���֯ID ,String targetOrgId: Ŀ����֯ID,String
	 *            newOrgName������֯��;
	 * @return BooleanResult
	 */
	public BooleanResult reOUNameInAD(String orgId, String targetOrgId,
			String newOrgName);

	/**
	 * 
	 * �������֯
	 * 
	 * @param orgId
	 * @return
	 */
	@Deprecated
	public String getFnAllChildStrOrg(String orgId);

	/**
	 * ����userId��ѯ������֯
	 * 
	 * @param userId
	 * @return Borg
	 */
	public Borg getOrgByUserId(String userId);
	
	
	
	
	/**
	 * ����loginId��ѯ������֯
	 * 
	 * @param loginId
	 * @return Borg
	 */
	public Borg getOrgByLoginId(String loginId);
	
	
	/**
	 * cms.cms_api_pack.fn_user_org
	 * 
	 * @param userId
	 * @return
	 */
	public Borg getFnUserOrg(String userId);

	/**
	 * ����orgId��ѯ�����ϼ���֯
	 * 
	 * @param orgId
	 * @return List<Borg>
	 */
	public List<Borg> getAllParentOrgs(Long orgId);

	/**
	 * �����֯����
	 * 
	 * @param borg
	 * @return
	 */
	public int getOrgCount(Borg borg);

	/**
	 * �����֯
	 */
	public List<Borg> getOrgList(Borg borg);


	/**
	 * ��ȡ��֯��
	 * add by hfwu
	 * @param org_id
	 * @return
	 */
	public String getorgnamestr(Long org_id,Long id);
	
	/**
	 * ��ȡ��֯���ƺ�
	 * add by hfwu
	 * @param org_id
	 * @return
	 */
	public String getorgname(Long org_id);
	/**
	 * ��ȡ�ϼ���֯ID
	 * add by hfwu
	 * @param org_id
	 * @return
	 */
	public Long getporgid(Long org_id);
	public Long getorgid();
	

	/**
	 * ������֯���ƵȲ�ѯ��֯������ӳ����֯��
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Borg> searchOrgTreeList(Borg borg);
	/**
	 * ������֯���ƵȲ�ѯ��֯������ӳ����֯��
	 * 
	 * @param orgId
	 * @return
	 */
	int getInAnotherOrgCount(String baseOrgId, String orgId);
	/**sl.zhu
	 * �����û�id ����֯���Ʋ�ѯ������֯������
	 */
	public int getOrgCountByUserId(Borg borg);
	/**sl.zhu
	 * �����û�id ����֯���Ʋ�ѯ������֯
	 */
	public List<Borg> getOrgListByUserId(Borg borg);
}
