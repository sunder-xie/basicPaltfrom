package com.kintiger.platform.sap.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.util.ClientUtil;
import com.kintiger.platform.framework.util.HttpUtil;
import com.kintiger.platform.sap.service.ISAPService;
import com.kintiger.platform.sap.service.ISSOService;

public class SapAction extends BaseAction {

	private static final long serialVersionUID = -9092859151846436697L;

	private ISAPService sapService;

	private ISSOService ssoService;

	private String role;

	private String tcode;

	private String url;

	private String passport;

	private String password;

	public String tcode() {
		HttpSession session = this.getSession();
		String ssoUrl = (String) session.getAttribute("SAP_PORTAL_SSO_URL");

		if (StringUtils.isEmpty(ssoUrl)) {
			List<String> roles = new ArrayList<String>();
			AllUsers user = this.getUser();
			if ("admin".equals(user.getLoginId())) {
				passport = "wsap";
				password = "654321";
				role = "ZBASIC,ZWSAP_MM01,ZWSAP_SD01,ZWSAP_BC01";
			} else if ("admin1".equals(user.getLoginId())) {
				passport = "wsap001";
				password = "123456";
				role = "ZBASIC,ZWSAP_MM01";
			} else if ("admin2".equals(user.getLoginId())) {
				passport = "wsap001";
				password = "123456";
				role = "ZBASIC,ZWSAP_SD01";
			} else if ("admin3".equals(user.getLoginId())) {
				passport = "wsap002";
				password = "123456";
				role = "ZBASIC,ZWSAP_BC01";
			}
			for (String r : role.split(",")) {
				roles.add(r);
			}

			try {
				sapService.updatePermission(passport, roles, user.getLoginId(),
					ClientUtil.getIpAddr(this.getServletRequest()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				ssoUrl = ssoService.getSSOUrl(passport, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("SAP_PORTAL_SSO_URL", ssoUrl);
			url = ssoUrl + "?~TRANSACTION=" + tcode;
		} else {
			url = ssoUrl + "?~OKCODE=/n" + tcode;
		}

		return SUCCESS;
	}

	public String logout() {
		sapService.removePermission(passport);
		HttpSession session = this.getSession();
		String ssoUrl = (String) session.getAttribute("SAP_PORTAL_SSO_URL");
		url = ssoUrl + "?~OKCODE=/nex";
		try {
			HttpUtil.post(url, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.removeAttribute("SAP_PORTAL_SSO_URL");

		return "done";
	}

	public ISAPService getSapService() {
		return sapService;
	}

	public void setSapService(ISAPService sapService) {
		this.sapService = sapService;
	}

	public ISSOService getSsoService() {
		return ssoService;
	}

	public void setSsoService(ISSOService ssoService) {
		this.ssoService = ssoService;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
