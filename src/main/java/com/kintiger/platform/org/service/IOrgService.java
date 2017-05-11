package com.kintiger.platform.org.service;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.SapTOrgUnit;

/**
 * 組織接口
 * 
 */
public interface IOrgService {

	/**
	 * 根据userId查询所在组织（包括映射组织）含下级 cms.cms_api_pack.fn_user_org_list
	 * 
	 * @param userId
	 * @return
	 */
	public List<Borg> getOrgListByUserId(String userId);

	/**
	 * 根据orgId查询所在组织（包括映射组织）
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Borg> getOrgTreeListByOrgId(String orgId);

	/**
	 * 根据pOrgId父级组织查询所在组织（包括映射组织）
	 * 
	 * @param pOrgId
	 * @return
	 */
	public List<Borg> getOrgTreeListByPorgId(String pOrgId);
	
	/**
	 * 根据borg得到部门所在路径
	 * @param borg
	 * @return
	 */
	public String getPartyPath(Borg borg);
	
	/**
	 * 根据用户得到部门所在路径
	 * @param allUsers
	 * @return
	 */
	public String getPartyPath(AllUsers allUsers);
	/**
	 * 根据orgId查询组织
	 * 
	 * @param orgId
	 * @return
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
	 * 查询人员组织单位
	 * 
	 * @return
	 */
	public List<SapTOrgUnit> getSapTOrgUnitListByPId(String pId);

	/**
	 * 创建组织
	 * 
	 * @return
	 */
	public BooleanResult createOrg(Borg borg);

	/**
	 * 
	 * 通过调webservice 操作AD新建OU jhzhou
	 * 
	 * @param orgName
	 *            新建ou名,新建Ou的orgID
	 * @return
	 */
	public BooleanResult NewOU(String orgName, String orgId, String parenOrgtId);

	/**
	 * 
	 * 通过调webservice 操作AD 建组 jhzhou
	 * 
	 * @param sAMAccountName
	 *            邮箱组别名 ;orgId 邮箱组所在ou的orgId
	 * @return
	 */
	public BooleanResult NewGroup(String groupName, String sAMAccountName,
			String orgId);

	/**
	 * 
	 * 更新borg的ad域相关信息 group
	 * 
	 * @param borg
	 * @return BooleanResult
	 */
	public BooleanResult updateBorgWithADInfo(Borg borg);

	/**
	 * 
	 * 更新borg
	 * 
	 * @param borg
	 * @return BooleanResult
	 */

	public BooleanResult updateBorg(Borg borg);
	
	
	/**
	 * 
	 * 调动borg
	 * 
	 * @param borg
	 * @return BooleanResult
	 */

	public BooleanResult dropBorg(Borg borg);
	
	public int checkOrgCity(Long paramLong);
	
	/**
	 * 
	 * 删除borg
	 * 
	 * @param borg
	 * @return BooleanResult
	 */

	public BooleanResult deleteBorg(Borg borg);

	/**
	 * 
	 * AD 组织移动或更名
	 * 
	 * @param String
	 *            orgId:被移动组织ID ,String targetOrgId: 目标组织ID,String
	 *            newOrgName：新组织名;
	 * @return BooleanResult
	 */
	public BooleanResult reOUNameInAD(String orgId, String targetOrgId,
			String newOrgName);

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
	public Borg getFnUserOrg(String userId);

	/**
	 * 根据orgId查询所有上级组织
	 * 
	 * @param orgId
	 * @return List<Borg>
	 */
	public List<Borg> getAllParentOrgs(Long orgId);

	/**
	 * 获得组织个数
	 * 
	 * @param borg
	 * @return
	 */
	public int getOrgCount(Borg borg);

	/**
	 * 获得组织
	 */
	public List<Borg> getOrgList(Borg borg);


	/**
	 * 获取组织串
	 * add by hfwu
	 * @param org_id
	 * @return
	 */
	public String getorgnamestr(Long org_id,Long id);
	
	/**
	 * 获取组织名称呼
	 * add by hfwu
	 * @param org_id
	 * @return
	 */
	public String getorgname(Long org_id);
	/**
	 * 获取上级组织ID
	 * add by hfwu
	 * @param org_id
	 * @return
	 */
	public Long getporgid(Long org_id);
	public Long getorgid();
	

	/**
	 * 根据组织名称等查询组织（包括映射组织）
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Borg> searchOrgTreeList(Borg borg);
	/**
	 * 根据组织名称等查询组织（包括映射组织）
	 * 
	 * @param orgId
	 * @return
	 */
	int getInAnotherOrgCount(String baseOrgId, String orgId);
	/**sl.zhu
	 * 根据用户id 及组织名称查询所有组织的数量
	 */
	public int getOrgCountByUserId(Borg borg);
	/**sl.zhu
	 * 根据用户id 及组织名称查询所有组织
	 */
	public List<Borg> getOrgListByUserId(Borg borg);
}
