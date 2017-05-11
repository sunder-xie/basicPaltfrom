package com.kintiger.platform.org.dao;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.SapTOrgUnit;

public interface IOrgDao {
	/**
	 * 根据userId查询所在组织（包括映射组织）
	 * 
	 * @param userId
	 * @return
	 */
	public List<Borg> getOrgListByUserId(String userId);

	/**
	 * 根据pOrgId查询所在组织（包括映射组织）
	 * 
	 * @param pOrgId
	 * @return List<Borg>
	 */
	public List<Borg> getOrgTreeListByPorgId(String pOrgId);

	/**
	 * 根据userId查询所在组织（包括映射组织）
	 * 
	 * @param OrgId
	 * @return List<Borg>
	 */
	public List<Borg> getOrgTreeListByOrgId(String OrgId);

	/**
	 * 根据orgId查询组织（
	 * 
	 * @param orgId
	 * @return Borg
	 */

	public Borg getOrgByOrgId(String orgId);

	/**
	 * 根据org查公司list条数
	 * 
	 * @return
	 */
	public int getCompanyListCount(Borg borg);

	/**
	 * 根据org查公司list
	 * 
	 * @return
	 */
	public List<Borg> getCompanyList(Borg borg);

	/**
	 * 根据org查公司
	 * 
	 * @return
	 */
	public Borg getCompanyName(Borg borg);

	/**
	 * 查询人员组织单位
	 * 
	 * @return
	 */
	public List<SapTOrgUnit> getSapTOrgUnitListByPId(String PID);

	/**
	 * 创建组织
	 * 
	 * @return
	 */
	public Long createOrg(Borg org);

	/**
	 * 
	 * 更新borg
	 * 
	 * @param borg
	 * @return
	 */
	public int updateBorg(Borg borg);

	/**
	 * 
	 * 调动borg
	 * 
	 * @param borg
	 * @return BooleanResult
	 */

	public int dropBorg(Borg borg);

	
	public int checkOrgCity(Long paramLong);
	
	/**
	 * 删除org
	 * 
	 * @param borg
	 * @return
	 */

	public int deleteBorg(Borg borg);

	/**
	 * 
	 * 获得子组织
	 * 
	 * @param orgId
	 * @return
	 */
	@Deprecated
	public String getFnAllChildStrOrg(String orgId);

	/**
	 * 根据userId查询所在组织
	 * 
	 * @param userId
	 * @return Borg
	 */
	public Borg getOrgByUserId(String userId);
	
	/**
	 * 根据loginId查询所在组织
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
	 * 根据orgId查询所有上级组织
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
	 * 获取组织名称呼 add by hfwu
	 * 
	 * @param org_id
	 * @return
	 */
	public String getorgname(Long org_id);

	/**
	 * 获取上级组织ID add by hfwu
	 * 
	 * @param org_id
	 * @return
	 */
	public Long getporgid(Long org_id);

	public Long getorgid();
	
	/**
	 * 根据组织名称等查询组织（包括映射组织）
	 * 
	 * @param userId
	 * @return
	 */
	public List<Borg> searchOrgTreeList(Borg borg);

	public int getInAnotherOrgCount(String baseOrgId, String orgId);

	public int getOrgCountByUserId(Borg borg);

	public List<Borg> getOrgListByUserId(Borg borg);
}
