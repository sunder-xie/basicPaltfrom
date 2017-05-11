package com.kintiger.platform.org.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.menu.pojo.Tree4Ajax;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.station.service.IStationService;

public class NewOrgAction extends BaseAction{

	/**
	 * 
	 */
	private static final Log logger = LogFactory.getLog(NewOrgAction.class);
	private static final long serialVersionUID = 1L;
	private IOrgService orgService;
	private IStationService stationService;
	private IDictService dictService;
	private List<Tree4Ajax> treeList;
	private List<Borg> companyList;
	private String flag;
	private String node;
	private String orgBId;
	@Decode
	private String orgName;
	private String orgNameUrl;
	private int total;
	
	/***
	 * basis���̵�����֯����֧�ֶ�ѡ
	 * @return
	 */
	public String newOrgTree(){
		return "newOrgTree";
	}
	/***
	 * �������̵���basis����֯����֧�ֶ�ѡ
	 * @return
	 */
	public String newOrgTree2(){
		return "newOrgTree2";
	}
	/**
	 * ��������֯����֧����ѡ
	 */
	public String newOrgTreeForDealer(){
		return "newOrgTreeForDealer";
	}
	/**
	 * ��ȡ�й�˾��֯��
	 * TODO��������������ò���Ƕ���ģ�д���ɲ���newOrgTreeForDealer
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getOrgTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		companyList = new  ArrayList<Borg>();
		try {
			//flag!=Y ���չ���²���֯
			if("Y".equals(flag)){
				//�����Ա�Ƿ�ӵ�в鿴������֯��Ȩ��  i!=0 ��ʾ��Ȩ��
				int  i = stationService.getPermissionByUserId(this.getUser().getUserId());
				if (StringUtils.isNotEmpty(orgNameUrl)) {//orgName ��Ϊ�գ���ʾ��ͨ��������ѯ��
					orgNameUrl = new String(
							orgNameUrl.getBytes("ISO8859-1"), "utf-8");
					System.out.println();
					Borg borg = new Borg();
					borg.setOrgName(orgNameUrl);
					borg.setUserId(i!=0?"88647":getUser().getUserId());
					borg.setStart(0);
					borg.setEnd(100);
					companyList = orgService.getOrgListByUserId(borg);
				}else{
					if(i!=0){
						companyList = orgService.getOrgTreeListByPorgId(node);
					}else{
						//��ȡ��Ա���θ�λ����֯
						List<String> orgList = stationService.getOrgListByUserId(this.getUser().getUserId());
						for(String str:orgList){
							Borg borg = orgService.getOrgByOrgId(str);
							companyList.add(borg);
						}
					}
				}
			}else{
				    companyList = orgService.getOrgTreeListByPorgId(node);
			}
		} catch (Exception e) {
			logger.error(node, e);
		}
		if (companyList == null || companyList.size() == 0){
			return JSON;
		}
		for (Borg borg : companyList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(borg.getOrgId()));
			tree.setText(borg.getOrgName());
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSON;
	}
//	/**
//	 * Combogrid ���ʹ�ã�
//	 */
//	@JsonResult(field = "companyList", include = { "orgId", "orgName", "costCenter" }, total = "total")
//	public String getCombogridOrgList() {
//		Borg borg = new Borg();
//		if (StringUtils.isNotEmpty(orgName)) {
//			borg.setOrgName(orgName);
//		}
//		borg.setUserId(getUser().getUserId());
//		borg.setStart(getStart());
//		borg.setEnd(getEnd());
//		total = orgService.getOrgCountByUserId(borg);
//		if (total != 0) {
//			companyList = orgService.getOrgListByUserId(borg);
//		}
//		return JSON;
//	}
	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public IStationService getStationService() {
		return stationService;
	}

	public void setStationService(IStationService stationService) {
		this.stationService = stationService;
	}


	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public List<Borg> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Borg> companyList) {
		this.companyList = companyList;
	}

	public static Log getLogger() {
		return logger;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getOrgBId() {
		return orgBId;
	}
	public void setOrgBId(String orgBId) {
		this.orgBId = orgBId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgNameUrl() {
		return orgNameUrl;
	}
	public void setOrgNameUrl(String orgNameUrl) {
		this.orgNameUrl = orgNameUrl;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
