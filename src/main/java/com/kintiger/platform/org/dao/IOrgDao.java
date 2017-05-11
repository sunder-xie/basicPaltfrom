package com.kintiger.platform.org.dao;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.SapTOrgUnit;

public interface IOrgDao {
	/**
	 * ����userId��ѯ������֯������ӳ����֯��
	 * 
	 * @param userId
	 * @return
	 */
	public List<Borg> getOrgListByUserId(String userId);

	/**
	 * ����pOrgId��ѯ������֯������ӳ����֯��
	 * 
	 * @param pOrgId
	 * @return List<Borg>
	 */
	public List<Borg> getOrgTreeListByPorgId(String pOrgId);

	/**
	 * ����userId��ѯ������֯������ӳ����֯��
	 * 
	 * @param OrgId
	 * @return List<Borg>
	 */
	public List<Borg> getOrgTreeListByOrgId(String OrgId);

	/**
	 * ����orgId��ѯ��֯��
	 * 
	 * @param orgId
	 * @return Borg
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
	 * ����org�鹫˾
	 * 
	 * @return
	 */
	public Borg getCompanyName(Borg borg);

	/**
	 * ��ѯ��Ա��֯��λ
	 * 
	 * @return
	 */
	public List<SapTOrgUnit> getSapTOrgUnitListByPId(String PID);

	/**
	 * ������֯
	 * 
	 * @return
	 */
	public Long createOrg(Borg org);

	/**
	 * 
	 * ����borg
	 * 
	 * @param borg
	 * @return
	 */
	public int updateBorg(Borg borg);

	/**
	 * 
	 * ����borg
	 * 
	 * @param borg
	 * @return BooleanResult
	 */

	public int dropBorg(Borg borg);

	
	public int checkOrgCity(Long paramLong);
	
	/**
	 * ɾ��org
	 * 
	 * @param borg
	 * @return
	 */

	public int deleteBorg(Borg borg);

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
	public String getFnUserOrg(String userId);

	/**
	 * ����orgId��ѯ�����ϼ���֯
	 * 
	 * @param orgId
	 * @return List<Borg>
	 */
	public List<Borg> getAllParentOrgs(Long orgId);

	/**
	 * 
	 * @param borg
	 * @return
	 */
	public int getOrgCount(Borg borg);

	/**
	 * 
	 * @param borg
	 * @return
	 */
	public List<Borg> getOrgList(Borg borg);

	/**
	 * ��ȡ��֯���ƺ� add by hfwu
	 * 
	 * @param org_id
	 * @return
	 */
	public String getorgname(Long org_id);

	/**
	 * ��ȡ�ϼ���֯ID add by hfwu
	 * 
	 * @param org_id
	 * @return
	 */
	public Long getporgid(Long org_id);

	public Long getorgid();
	
	/**
	 * ������֯���ƵȲ�ѯ��֯������ӳ����֯��
	 * 
	 * @param userId
	 * @return
	 */
	public List<Borg> searchOrgTreeList(Borg borg);

	public int getInAnotherOrgCount(String baseOrgId, String orgId);

	public int getOrgCountByUserId(Borg borg);

	public List<Borg> getOrgListByUserId(Borg borg);
}
