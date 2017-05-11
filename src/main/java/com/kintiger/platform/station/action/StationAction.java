package com.kintiger.platform.station.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.station.pojo.StationOrg;
import com.kintiger.platform.station.service.IStationService;

public class StationAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6253822323971584433L;
	private IStationService stationService;
	private List<Station> stationList;
	private List<Station> userList = new ArrayList<Station>();
	@Decode
	private String stationId;
	private List<CmsTbDict> dictList;
	@Decode
	private String stationName;
	private String stationName1;
	private Long orgId;
	private String orgName;
	private List<Borg> orgList;
	private int total = 0;
	private Station station;
	private String stationIdStr;
	private String userId;
	private @Decode
	String userName;
	private String ids;
	private String custType;
	private String flag;
	@Decode
	private String searchKey;
	private String userIdReturn;
	private String empCode;// �û���¼Id
	private Long id;
	private IDictService dictService;
	private Long oaStationId;
	private String orgIds;
	private List<StationOrg> orgs;

	/**
	 * �����ѯҳ��
	 */
	@PermissionSearch
	public String stationPagePre() {
		return SUCCESS;
	}

	/**
	 * ��ѯȨ�޸�λ
	 */
	@PermissionSearch
	@JsonResult(field = "stationList", include = { "stationId", "stationName",
			"orgId", "orgName", "oatype", "oatypeName" }, total = "total")
	public String searchStation() {
		Station s = new Station();
		s = getSearchInfo(s);
		s.setStationId(stationId);
		s.setStationName(stationName);
		s.setOrgId(orgId);
		s.setStart(getStart());
		s.setEnd(getEnd());
		if (orgId != null) {
			s.setStationType(Integer.parseInt(orgId.toString()));
		}
		total = stationService.getStationJsonListCount(s);
		if (total != 0) {
			stationList = stationService.getStationJsonList(s);
		} else {
			stationList = null;
		}
		return JSON;
	}

	/**
	 * ��ѯ��λ��Ա
	 */
	@PermissionSearch
	@JsonResult(field = "stationList", include = { "userId", "userName" }, total = "total")
	public String searchStationUser() {
		station = new Station();
		station.setStationId(stationId);
		station.setUserId(userId);
		station.setStationName(stationName);
		station.setUserName(userName);//
		station.setStart(this.getStart());
		station.setEnd(this.getEnd());
		total = stationService.searchStationUserCount(station);
		if (total != 0) {
			stationList = stationService.searchStationUser(station);
			total = stationList.size();
		} else {
			stationList = null;
		}
		return JSON;
	}

	/**
	 * ��Ա����ҳ�� ��ѯ���û��еĸ�λ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "stationList", include = { "id", "userName",
			"stationName", "empCode", "orgName" }, total = "total")
	public String searchStationUserMore() {
		station = new Station();
		station.setUserId(userId);
		total = stationService.searchStationUserMoreCount(station);
		if (total != 0) {
			stationList = stationService.searchStationUserMore(station);
		} else {
			stationList = null;
		}
		return JSON;
	}

	/**
	 * ����Ȩ�޸�λ��Ա
	 * 
	 */
	public String configStationUser() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		station = stationService.getStationEntity(stationId);
		String ids[] = userIdReturn.substring(0, userIdReturn.length() - 1)
				.split(";");
		int c = stationService.getStaffStationCount(station);
		int d = stationService.getStaffStationAmount(station);
		if (ids.length <= (d - c)) {
			for (String userId : ids) {
				station.setUserId(userId);
				int r = stationService.searchStationUserCount(station);
				if (r == 0) {
					stationService.insertStationUser(station);
				}
			}
			this.setSuccessMessage("��λ��Ա���óɹ�!");
		} else {
			this.setFailMessage("��λ��Ա����ʧ��!,���������� ��������ʣ����Ϊ��" + (d - c));
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 
	 * ���봴��ҳ��
	 */
	@PermissionSearch
	public String createStationPagePre() {

		return "createpre";
	}

	/**
	 * ɾ����λ��Ա
	 * 
	 * @return
	 */
	public String deleteStationUser() {
		String ss = this.getServletRequest().getParameter("userIdStrs");
		String[] ls = ss.split(",");

		for (int i = 0; i < ls.length; i++) {
			station = new Station();
			station.setUserId(ls[i]);
			station.setStationId(stationId);
			stationService.deleteStationUser(station);
		}
		this.setSuccessMessage("��λ��Աɾ���ɹ�! ����Ϊ" + ls.length);
		return RESULT_MESSAGE;
	}

	/**
	 * ������޸�λ��Ա����ҳ��
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@PermissionSearch
	public String configStationUserPagePre()
			throws UnsupportedEncodingException {
		stationId = this.getStationId();
		stationName = new String(this.getStationName().getBytes("ISO8859-1"),
				"utf-8");

		return "configpre";
	}

	/**
	 * ����ѡ���λ��Աҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String chooseSattionUser() {
		return "choosepre";
	}

	/**
	 * ��ѯ��Ա
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "userList", include = { "userId", "userCode",
			"userName" }, total = "total")
	public String searchUser() {
		station = new Station();
		/* station.setCustType(custType); */
		station.setCustType("G");
		if (StringUtils.isNotEmpty(searchKey)) {

			if ("byid".equals(flag)) {
				station.setUserCode(searchKey);
			} else {
				station.setUserName(searchKey);
			}
		}
		station.setStart(this.getStart());
		station.setEnd(this.getEnd());
		total = stationService.searchUserCount(station);
		if (total != 0) {
			userList = stationService.searchUser(station);
		} else {
			userList = null;
		}
		return JSON;

	}

	/**
	 * ȡ�ˆT����
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "dictList", include = { "itemName", "itemValue" })
	public String getCustTypeList() {
		dictList = stationService.getCustTypeList();
		return JSON;
	}

	/**
	 * ȡ��λ��������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "dictList", include = { "itemName", "itemId" })
	public String getCustTypes() {
		CmsTbDict cm = new CmsTbDict();
		cm.setItemName("��λ��������");
		dictList = dictService.getByCmsTbDictList(cm);
		return JSON;
	}

	/**
	 * 
	 * ��������
	 */
	public String createStation() {
		if (stationService.getStationEntity(stationId) != null) {
			this.setFailMessage("Ȩ�޸�λ��Ϣ����ʧ��!��ͬ��stationID�Ѵ���   stationIDΨһ�ԡ���ʹ��ɾ��Ҳ����������ͬ����Ϣ��");
			return RESULT_MESSAGE;
		} else {
			station = new Station();
			station.setStationId(stationId);
			station.setStationName(stationName);
			station.setOrgId(orgId);
			station.setOatype(oaStationId);
			String id = stationService.createStationEntity(station);
			if (id != null) {
				this.setSuccessMessage("Ȩ�޸�λ��Ϣ�����ɹ�!");
			} else {
				this.setFailMessage("Ȩ�޸�λ��Ϣ����ʧ��!");
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * �����޸�ҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateStationPagePre() {
		station = stationService.getStationEntity(stationId);
		stationId = station.getStationId();
		stationName = station.getStationName();
		oaStationId = station.getOatype();
		orgId = station.getOrgId();
		return "updatepre";

	}

	/**
	 * ɾ������
	 * 
	 * @return
	 */
	public String deleteStation() {
		String[] ls = ids.split(",");
		for (int i = 0; i < ls.length; i++) {
			int l = stationService.getStationUserCount(ls[i]);

			if (l != 0) {
				this.setFailMessage("��λidΪ��" + ls[i]
						+ " ��Ȩ�޸�λ������,����ɾ����Ա�������ñ�����Ա��Ϣ! ");
				return RESULT_MESSAGE;
			} else {
				int ll = stationService.getStationRoleCount(ls[i]);
				if (ll != 0) {
					this.setFailMessage("��λidΪ��" + ls[i] + "��Ȩ�޸�λ���н�ɫ����ɾ����ɫ!");
					return RESULT_MESSAGE;
				} else {
					int r = stationService.deleteStationEntity(ls[i]);
					if (r > 0) {
						this.setSuccessMessage("Ȩ�޸�λɾ���ɹ�!");
					} else {
						this.setFailMessage("Ȩ�޸�λɾ��ʧ��!");
					}
				}
			}
		}
		return RESULT_MESSAGE;

	}

	/**
	 * �޸ı���
	 * 
	 * @return
	 */

	public String updateStation() {
		station = new Station();
		station.setStationId(stationId);
		station.setStationName(stationName);
		station.setOrgId(orgId);
		station.setOatype(oaStationId);
		int r = stationService.updateStationEntity(station);
		if (r > 0) {
			this.setSuccessMessage("��λ��Ϣ�޸ĳɹ�!");
		} else {
			this.setFailMessage("��λ��Ϣ�޸�ʧ��!");
		}

		return RESULT_MESSAGE;

	}

	/**
	 * ��˾�б�
	 */
	@PermissionSearch
	@JsonResult(field = "orgList", include = { "orgId", "orgName" })
	public String getCompanyJsonList() {
		orgList = stationService.getCompanyJsonList();
		return JSON;
	}

	/**
	 * �����޸�ҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toStationOrgPre() {

		return "toStationOrgPre";
	}

	/***
	 * ��ת��ѡ���λҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchStation() {

		return "toSearchStation";
	}

	/***
	 * ��ת��ѡ���λҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String ceateStationOrg() {
		StationOrg stationOrg = new StationOrg();
		stationOrg.setStationId(stationId);
		stationOrg.setOrgIds(orgIds);
		stationOrg.setCreator(this.getUser().getUserId());

		Long result = stationService.createStationOrg(stationOrg);
		if (result != 0l) {
			this.setSuccessMessage("�����ɹ���");
		} else {
			this.setFailMessage("���ݿⱣ��ʧ�ܣ�");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * ��ȡ�ø�λ��ѡ�����֯�б�
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "orgs", include = { "orgId", "orgName" })
	public String getStationOrgJsonList() {
		StationOrg stationOrg = new StationOrg();
		stationOrg.setStationId(stationId);
		orgs = stationService.getStationOrgList(stationOrg);
		return JSON;
	}

	public IStationService getStationService() {
		return stationService;
	}

	public void setStationService(IStationService stationService) {
		this.stationService = stationService;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<Borg> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Borg> orgList) {
		this.orgList = orgList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String getStationIdStr() {
		return stationIdStr;
	}

	public void setStationIdStr(String stationIdStr) {
		this.stationIdStr = stationIdStr;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getUserIdReturn() {
		return userIdReturn;
	}

	public void setUserIdReturn(String userIdReturn) {
		this.userIdReturn = userIdReturn;
	}

	public String getStationName1() {
		return stationName1;
	}

	public void setStationName1(String stationName1) {
		this.stationName1 = stationName1;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<Station> getUserList() {
		return userList;
	}

	public void setUserList(List<Station> userList) {
		this.userList = userList;
	}

	public List<CmsTbDict> getDictList() {
		return dictList;
	}

	public void setDictList(List<CmsTbDict> dictList) {
		this.dictList = dictList;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public Long getOaStationId() {
		return oaStationId;
	}

	public void setOaStationId(Long oaStationId) {
		this.oaStationId = oaStationId;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public List<StationOrg> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<StationOrg> orgs) {
		this.orgs = orgs;
	}
}
