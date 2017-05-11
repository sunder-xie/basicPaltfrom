package com.kintiger.platform.boform.action;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.boform.pojo.ReportParameter;
import com.kintiger.platform.boform.service.IBoformService;
import com.kintiger.platform.framework.annotations.PermissionSearch;

public class FineReportAction extends BaseAction {

	private static final long serialVersionUID = 393817160216158896L;

	//当前登录用户
	private AllUsers loginUser;
	
	//帆软报表路径
	private String frreport;
	
	//报表控件访问权限
	private int right;
	
	//报表类型配置
	private int pid;
	
	private IBoformService boformService;
	private Integer year;
	private Integer month;
	private String kunnr;
	
	@PermissionSearch
	public String createFineReportQuery() {
		loginUser = this.getUser();
		return "createFineReportQuery";
	}
	
	/**
	 * 创建报表访问日志（客户对账单）
	 */
	public void createReportLog(){
		if(year!=null && month!=null && StringUtils.isNotEmpty(kunnr)){
			Calendar cal = Calendar.getInstance();
			int nowDate = cal.get(Calendar.YEAR)*100+cal.get(Calendar.MONTH)+1;
			int getDate = year*100+month;
			if(nowDate>getDate && year>=2000 && year<3000 && month>=1 && month<=12){					
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("checkYear", year);
				map.put("checkMonth", month);
				map.put("kunnr", kunnr);
				boformService.createOrderReportLog(map);
			}
		}
	}

	public AllUsers getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(AllUsers loginUser) {
		this.loginUser = loginUser;
	}

	public String getFrreport() {
		return frreport;
	}

	public void setFrreport(String frreport) {
		this.frreport = frreport;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public IBoformService getBoformService() {
		return boformService;
	}

	public void setBoformService(IBoformService boformService) {
		this.boformService = boformService;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
}
