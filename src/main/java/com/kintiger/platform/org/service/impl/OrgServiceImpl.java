package com.kintiger.platform.org.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.org.dao.IOrgDao;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.SapTOrgUnit;
import com.kintiger.platform.org.service.IOrgService;

public class OrgServiceImpl implements IOrgService {

	private Logger logger = Logger.getLogger(IOrgService.class);

	private IOrgDao orgDao;

	private String webserviceURL;

	public List<Borg> getOrgListByUserId(String userId) {
		try {
			return orgDao.getOrgListByUserId(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	public List<Borg> getOrgTreeListByOrgId(String orgId) {
		try {
			return orgDao.getOrgTreeListByOrgId(orgId);
		} catch (Exception e) {
			logger.error(orgId, e);
		}

		return null;
	}

	public Borg getOrgByOrgId(String orgId) {
		try {
			return orgDao.getOrgByOrgId(orgId);
		} catch (Exception e) {
			logger.error(orgId, e);
		}

		return null;
	}

	/*
	 * ȡ�����ŵ�·�������ۺϹ�������/��Ϣ���� qq_email
	 */
	public String getPartyPath(Borg borg) {
		borg = orgDao.getOrgByOrgId(borg.getOrgId()+"");
		String path = "";
		if (borg.getOrgName() == "��ƮƮʳƷ�ɷ����޹�˾") {
			return "";
		}
		path = borg.getOrgName();
		while (!borg.getOrgParentName().equals("��ƮƮʳƷ�ɷ����޹�˾")) {
			path = borg.getOrgParentName() + "/" + path;
			borg = orgDao.getOrgByOrgId(borg.getOrgParentId() + "");// ͨ��id�鵽borg��Ϣ
			// System.out.println(borg.getOrgName());
		}
		return path;
	}
	public String getPartyPath(AllUsers allUsers){
		Borg borg = orgDao.getOrgByOrgId(allUsers.getOrgId());
		String path = "";
		path = borg.getOrgName();
		if (path.equals("��ƮƮʳƷ�ɷ����޹�˾")||path==null) {
			return "";
		}
		while (!(borg.getOrgParentName().equals("��ƮƮʳƷ�ɷ����޹�˾")||borg.getOrgParentName()==null)) {
			path = borg.getOrgParentName() + "/" + path;
			borg = orgDao.getOrgByOrgId(borg.getOrgParentId() + "");// ͨ��id�鵽borg��Ϣ
			// System.out.println(borg.getOrgName());
		}
		return path;
	}
	public List<Borg> getOrgTreeListByPorgId(String pOrgId) {
		try {
			return orgDao.getOrgTreeListByPorgId(pOrgId);
		} catch (Exception e) {
			logger.error(pOrgId, e);
		}

		return null;
	}

	public List<Borg> searchOrgTreeList(Borg borg) {
		try {
			return orgDao.searchOrgTreeList(borg);
		} catch (Exception e) {
			logger.error(borg, e);
		}

		return null;
	}
	
	public List<SapTOrgUnit> getSapTOrgUnitListByPId(String PId) {
		try {
			return orgDao.getSapTOrgUnitListByPId(PId);
		} catch (Exception e) {
			logger.error(PId, e);
		}

		return null;
	}

	public BooleanResult createOrg(Borg borg) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			Long orgId = orgDao.createOrg(borg);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(orgId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			logger.error(LogUtil.parserBean(borg), e);
		}

		return booleanResult;
	}

	public BooleanResult NewOU(String orgName, String orgId, String parenOrgtId) {
		BooleanResult booleanResult = new BooleanResult();

		/*
		 * JaxWsDynamicClientFactory deFactory = JaxWsDynamicClientFactory
		 * .newInstance(); Client client = null; try {
		 * 
		 * // client = //
		 * deFactory.createClient("http://10.1.3.248/webservice1/service1.asmx?WSDL"
		 * ); client = deFactory.createClient(new URL(webserviceURL)); Object[]
		 * res = new Object[3]; res[0] = orgName; res[1] = orgId; res[2] =
		 * parenOrgtId; Object[] results = client.invoke("NewOU", res); if
		 * (!StringUtils.equals(results[0].toString(), "0")) { if
		 * (StringUtils.equals(results[0].toString(), "1")) {
		 * booleanResult.setResult(false); booleanResult.setCode("��NewOUʧ��");
		 * 
		 * } else if (StringUtils.equals(results[0].toString(), "2")) {
		 * booleanResult.setResult(false); booleanResult.setCode("�Ҳ���������֯��" +
		 * parenOrgtId);
		 * 
		 * } else { booleanResult.setResult(false);
		 * booleanResult.setCode("��NewOUʧ��"); } } else {
		 * booleanResult.setResult(true); booleanResult.setCode("��NewOU�ɹ�"); } }
		 * catch (MalformedURLException e) { booleanResult.setResult(false);
		 * booleanResult.setCode("����ʧ��:�޷�����" + webserviceURL + "");
		 * logger.error("orgName:" + orgName + "orgId:" + orgId + "parenOrgtId:"
		 * + parenOrgtId, e); return booleanResult; } catch (Exception e1) {
		 * logger.error("orgName:" + orgName + "orgId:" + orgId + "parenOrgtId:"
		 * + parenOrgtId, e1); booleanResult.setResult(false);
		 * booleanResult.setCode("��NewOUʧ��"); } finally { client.destroy(); }
		 */

		return booleanResult;
	}

	public BooleanResult NewGroup(String groupName, String sAMAccountName,
			String orgId) {
		BooleanResult booleanResult = new BooleanResult();

		/*
		 * JaxWsDynamicClientFactory deFactory = JaxWsDynamicClientFactory
		 * .newInstance(); Client client = null; try { client =
		 * deFactory.createClient(new URL(webserviceURL));
		 * 
		 * } catch (MalformedURLException e1) { e1.printStackTrace(); } catch
		 * (Exception e1) { logger.error("NewGroup", e1);
		 * booleanResult.setResult(false);
		 * booleanResult.setCode("����ʧ��.����AD��webservice�Ƿ�����"); return
		 * booleanResult; } Object[] res = new Object[3]; res[0] = groupName;
		 * res[1] = sAMAccountName; res[2] = orgId; try {
		 * 
		 * Object[] results = client.invoke("NewDistributionGroup", res); if
		 * (!StringUtils.equals(results[0].toString(), "0")) { if
		 * (StringUtils.equals(results[0].toString(), "1")) {
		 * booleanResult.setResult(false); booleanResult.setCode("����ʧ��");
		 * 
		 * } else { booleanResult.setResult(false);
		 * booleanResult.setCode("����ʧ��"); } } else {
		 * booleanResult.setResult(true); booleanResult.setCode("�����ɹ�"); } }
		 * catch (Exception e) { logger.error("NewGroup", e);
		 * booleanResult.setResult(false);
		 * booleanResult.setCode("����ʧ��.����AD��webservice�Ƿ�����");
		 * 
		 * } finally { client.destroy(); }
		 */
		return booleanResult;
	}

	public BooleanResult updateBorgWithADInfo(Borg borg) {
		BooleanResult booleanResult = new BooleanResult();
		Borg org = new Borg();
		org.setOrgId(borg.getOrgId());
		org.setsAMAccountName(borg.getsAMAccountName());
		org.setAdGroupName(borg.getAdGroupName());
		try {
			orgDao.updateBorg(org);
			booleanResult.setResult(true);
		} catch (Exception e) {
			booleanResult.setResult(false);
			logger.error("updateBorgWithADInfo", e);
		}
		return booleanResult;
	}

	public BooleanResult updateBorg(Borg borg) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			orgDao.updateBorg(borg);
			booleanResult.setResult(true);
		} catch (Exception e) {
			booleanResult.setResult(false);
			logger.error("updateBorg", e);
		}
		return booleanResult;
	}

	public BooleanResult dropBorg(Borg borg) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			orgDao.dropBorg(borg);
			booleanResult.setResult(true);
		} catch (Exception e) {
			booleanResult.setResult(false);
			logger.error("dropBorg", e);
		}
		return booleanResult;
	}

	public int checkOrgCity(Long userId) {
		try {
			return this.orgDao.checkOrgCity(userId);
		} catch (Exception e) {
			this.logger.error(LogUtil.parserBean(userId), e);
		}
		return 0;
	}

	public BooleanResult deleteBorg(Borg borg) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			orgDao.deleteBorg(borg);
			booleanResult.setResult(true);
		} catch (Exception e) {
			booleanResult.setResult(false);
			logger.error("dropBorg", e);
		}
		return booleanResult;
	}

	public BooleanResult reOUNameInAD(String orgId, String targetOrgId,
			String newOrgName) {
		BooleanResult booleanResult = new BooleanResult();
		/*
		 * JaxWsDynamicClientFactory deFactory = JaxWsDynamicClientFactory
		 * .newInstance(); Client client = null; try { client =
		 * deFactory.createClient(new URL(webserviceURL)); } catch
		 * (MalformedURLException e1) { logger.error("reOUNameInAD", e1); }
		 * catch (Exception e1) { logger.error("reOUNameInAD", e1);
		 * booleanResult.setResult(false);
		 * booleanResult.setCode("AD�޸�ʧ��.����AD��webservice�Ƿ�����:" +
		 * webserviceURL); return booleanResult; } Object[] res = new Object[3];
		 * res[0] = orgId; res[1] = targetOrgId; res[2] = newOrgName; try {
		 * Object[] results = client.invoke("MoveOU", res); if
		 * (!StringUtils.equals(results[0].toString(), "0")) { if
		 * (StringUtils.equals(results[0].toString(), "1")) {
		 * booleanResult.setResult(false); booleanResult.setCode("AD�޸�ʧ��");
		 * 
		 * } else if (StringUtils.equals(results[0].toString(), "2")) {
		 * booleanResult.setResult(false);
		 * booleanResult.setCode("AD�޸�ʧ��:�Ҳ������ƶ�����֯."); } else if
		 * (StringUtils.equals(results[0].toString(), "3")) {
		 * booleanResult.setResult(false);
		 * booleanResult.setCode("AD�޸�ʧ��:�Ҳ���Ŀ����֯."); } } else {
		 * booleanResult.setResult(true); booleanResult.setCode("AD�޸ĳɹ�"); } }
		 * catch (Exception e) { logger.error("reOUNameInAD", e);
		 * booleanResult.setResult(false);
		 * booleanResult.setCode("AD�޸�ʧ��.����AD��webservice�Ƿ�����");
		 * 
		 * } finally { client.destroy();
		 * 
		 * }
		 */
		return booleanResult;
	}

	public String getFnAllChildStrOrg(String orgId) {
		try {
			return orgDao.getFnAllChildStrOrg(orgId);
		} catch (Exception e) {
			logger.error(orgId, e);
		}

		return null;
	}

	public int getCompanyListCount(Borg borg) {
		try {
			return orgDao.getCompanyListCount(borg);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(borg), e);
		}

		return 0;
	}

	public List<Borg> getCompanyList(Borg borg) {
		try {
			return orgDao.getCompanyList(borg);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(borg), e);
		}

		return null;
	}

	public Borg getOrgByUserId(String userId) {
		try {
			return orgDao.getOrgByUserId(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	public Borg getOrgByLoginId(String loginId) {
		try {
			return orgDao.getOrgByLoginId(loginId);
		} catch (Exception e) {
			logger.error(loginId, e);
		}
		return null;
	}

	public Borg getFnUserOrg(String userId) {
		try {
			String orgId = orgDao.getFnUserOrg(userId).split(",")[0];
			return orgDao.getOrgByOrgId(orgId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	public List<Borg> getAllParentOrgs(Long orgId) {
		try {
			return orgDao.getAllParentOrgs(orgId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(orgId), e);
		}

		return null;
	}

	public int getOrgCount(Borg borg) {
		try {
			return orgDao.getOrgCount(borg);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(borg), e);
		}

		return 0;
	}

	public List<Borg> getOrgList(Borg borg) {
		try {
			return orgDao.getOrgList(borg);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(borg), e);
		}

		return null;
	}

	public IOrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(IOrgDao orgDao) {
		this.orgDao = orgDao;
	}

	public String getWebserviceURL() {
		return webserviceURL;
	}

	public void setWebserviceURL(String webserviceURL) {
		this.webserviceURL = webserviceURL;
	}

	/**
	 * ��ȡ��֯�� add by hfwu
	 * 
	 * @param org_id
	 * @return
	 */
	public String getorgnamestr(Long org_id, Long id) {
		String oustr = "";
		// LONGֵ�Աȵ�ʱ������BUG ��ΪString ֮���ж�
		if (!org_id.toString().equals(id.toString())) {
			oustr = getorgnamestr(getporgid(org_id), id) + "\\"
					+ getorgname(org_id);
		} else if (!(org_id.toString() == null)
				&& !org_id.toString().equals("")) {
			oustr = getorgname(org_id);
		} else {
			return null;
		}
		return oustr;
	}

	/**
	 * ��ȡ��֯���ƺ� add by hfwu
	 * 
	 * @param org_id
	 * @return
	 */
	public String getorgname(Long org_id) {
		String orgname = "";
		try {
			orgname = orgDao.getorgname(org_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgname;
	}

	/**
	 * ��ȡ�ϼ���֯ID add by hfwu
	 * 
	 * @param org_id
	 * @return
	 */
	public Long getporgid(Long org_id) {
		Long orgId = 1L;
		try {
			orgId = orgDao.getporgid(org_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgId;
	}

	public Long getorgid() {
		return orgDao.getorgid();
	}

	public int getInAnotherOrgCount(String baseOrgId, String orgId) {
		try {
			return orgDao.getInAnotherOrgCount(baseOrgId, orgId);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public int getOrgCountByUserId(Borg borg) {
		try {
			return orgDao.getOrgCountByUserId(borg);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public List<Borg> getOrgListByUserId(Borg borg) {
		try {
			return orgDao.getOrgListByUserId(borg);
		} catch (Exception e) {
			logger.error(borg, e);
		}
		return null;
	}

}
