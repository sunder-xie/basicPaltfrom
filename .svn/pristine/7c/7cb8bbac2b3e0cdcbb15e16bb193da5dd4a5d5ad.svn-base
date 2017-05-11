package com.kintiger.platform.monitor.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.ChartDataConvertUtil;
import com.kintiger.platform.framework.util.PropertiesUtil;
import com.kintiger.platform.monitor.pojo.ChartEntity;
import com.kintiger.platform.monitor.pojo.HessianDetail;
import com.kintiger.platform.monitor.service.IMonitorService;

public class MonitorAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7077268705174158821L;
	private IMonitorService monitorService;
	private List<HessianDetail> hessianDetailList;
	private List<ChartEntity> chartEntityList = new ArrayList<ChartEntity>();
	private String jdbcHost = "";
	private String jdbcSid = "";
	private String jdbcUser = "";
	private String jdbcPassword = "";
	private String ldapValidate = "";
	private String ldapHost = "";
	private String ldapDomain = "";
	private String ldapUser = "";
	private String ldapPassword = "";
	private String smtpServer = "";
	private String emailAddress = "";
	private String emailPassword = "";
	private String displayName = "";

	private String flag;

	@PermissionSearch
	public String searchHessianPer() {
		return SUCCESS;
	}

	@PermissionSearch
	@JsonResult(field = "chartEntityList", include = { "name", "data", "att" })
	public String searchHessian() {
		try {
			hessianDetailList = monitorService.searchHessian();
			Map<String, List<HessianDetail>> map = new HashMap<String, List<HessianDetail>>();
			for (HessianDetail detail : hessianDetailList) {
				List<HessianDetail> detailList = new ArrayList<HessianDetail>();
				if (map.containsKey(detail.getHessianName())) {
					map.get(detail.getHessianName()).add(detail);
				} else {
					detailList.add(detail);
					map.put(detail.getHessianName(), detailList);
				}

			}
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, List<HessianDetail>> entry = (Map.Entry) it
						.next();
				List<ChartEntity> ll = ChartDataConvertUtil
						.convert2ChartEntity(entry.getValue());
				for (ChartEntity e : ll) {
					chartEntityList.add(e);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return JSON;
	}

	@PermissionSearch
	public String setEnvPre() {
		String path = this.getServletRequest().getRealPath(
				"WEB-INF/env.properties");
		String allJdbcUrl = PropertiesUtil.readValue(path, "jdbc.url").split(
				"\\@")[1];
		jdbcHost = allJdbcUrl.split("\\:")[0];
		jdbcSid = allJdbcUrl.split("\\:")[2];
		jdbcUser = PropertiesUtil.readValue(path, "jdbc.username");
		jdbcPassword = PropertiesUtil.readValue(path, "jdbc.password");
		ldapValidate = PropertiesUtil.readValue(path, "ldap.validate");
		if ("true".equals(ldapValidate)) {
			ldapHost = PropertiesUtil.readValue(path, "ldap.ldapHost").split(
					"\\//")[1].split("\\:")[0];
			ldapDomain = PropertiesUtil.readValue(path, "ldap.domain").split(
					"\\@")[1];
			ldapUser = "Administrator";
			ldapPassword = PropertiesUtil.readValue(path, "ldap.password");
		}
		smtpServer = PropertiesUtil.readValue(path, "allUser.smtpServer");
		emailAddress = PropertiesUtil.readValue(path, "allUser.emailaddress");
		emailPassword = PropertiesUtil.readValue(path, "allUser.emailpassword");
		displayName = PropertiesUtil.readValue(path, "allUser.displayName");
		return "envpre";
	}

	@JsonResult(field = "flag")
	public String setEnv() {
		String path = this.getServletRequest().getRealPath(
				"WEB-INF/env.properties");
		if (!"".equals(ldapHost)) {
			String bs[] = ldapDomain.split("\\.");
			String base = "DC=" + bs[0] + ",DC=" + bs[1];
			String dn = "CN=" + ldapUser + ",CN=Users," + base;
			PropertiesUtil.writeProperties(path, "ldap.ldapHost", "ladp://"
					+ ldapHost + ":389");
			PropertiesUtil.writeProperties(path, "ldap.ldapHost2", "ladp://"
					+ ldapHost + ":636");
			PropertiesUtil.writeProperties(path, "ldap.domain", "@"
					+ ldapDomain);
			PropertiesUtil.writeProperties(path, "ldap.base", base);
			PropertiesUtil.writeProperties(path, "ldap.userDn", dn);
			PropertiesUtil.writeProperties(path, "ldap.password", ldapPassword);
			PropertiesUtil.writeProperties(path, "ldap.validate", "true");
		}
		if (!"".equals(smtpServer)) {
			PropertiesUtil.writeProperties(path, "allUser.smtpServer",
					smtpServer);
			PropertiesUtil.writeProperties(path, "allUser.from", emailAddress);
			PropertiesUtil.writeProperties(path, "allUser.emailaddress",
					emailAddress);
			PropertiesUtil.writeProperties(path, "allUser.emailpassword",
					emailPassword);
			PropertiesUtil.writeProperties(path, "allUser.displayName",
					displayName);
		}
		// ÷ÿ‘ÿbean
		XmlWebApplicationContext context = (XmlWebApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(this.getServletRequest().getSession()
						.getServletContext());
		context.refresh();
		flag = "Y";
		return JSON;
	}

	public IMonitorService getMonitorService() {
		return monitorService;
	}

	public void setMonitorService(IMonitorService monitorService) {
		this.monitorService = monitorService;
	}

	public List<HessianDetail> getHessianDetailList() {
		return hessianDetailList;
	}

	public void setHessianDetailList(List<HessianDetail> hessianDetailList) {
		this.hessianDetailList = hessianDetailList;
	}

	public List<ChartEntity> getChartEntityList() {
		return chartEntityList;
	}

	public void setChartEntityList(List<ChartEntity> chartEntityList) {
		this.chartEntityList = chartEntityList;
	}

	public String getJdbcHost() {
		return jdbcHost;
	}

	public void setJdbcHost(String jdbcHost) {
		this.jdbcHost = jdbcHost;
	}

	public String getJdbcSid() {
		return jdbcSid;
	}

	public void setJdbcSid(String jdbcSid) {
		this.jdbcSid = jdbcSid;
	}

	public String getJdbcUser() {
		return jdbcUser;
	}

	public void setJdbcUser(String jdbcUser) {
		this.jdbcUser = jdbcUser;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	public String getLdapHost() {
		return ldapHost;
	}

	public void setLdapHost(String ldapHost) {
		this.ldapHost = ldapHost;
	}

	public String getLdapUser() {
		return ldapUser;
	}

	public void setLdapUser(String ldapUser) {
		this.ldapUser = ldapUser;
	}

	public String getLdapPassword() {
		return ldapPassword;
	}

	public void setLdapPassword(String ldapPassword) {
		this.ldapPassword = ldapPassword;
	}

	public String getLdapDomain() {
		return ldapDomain;
	}

	public void setLdapDomain(String ldapDomain) {
		this.ldapDomain = ldapDomain;
	}

	public String getLdapValidate() {
		return ldapValidate;
	}

	public void setLdapValidate(String ldapValidate) {
		this.ldapValidate = ldapValidate;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
