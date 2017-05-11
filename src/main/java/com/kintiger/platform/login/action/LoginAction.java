package com.kintiger.platform.login.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;


import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.content.listener.OnlineCounter;
import com.kintiger.platform.framework.util.EncryptUtil;
import com.kintiger.platform.framework.util.HttpUtil;
import com.kintiger.platform.framework.util.PropertiesUtil;
import com.kintiger.platform.login.pojo.ValidateResult;
import com.kintiger.platform.login.service.ICAService;
import com.kintiger.platform.sap.service.ISAPService;

public class LoginAction extends BaseAction {

	private Log logger = LogFactory.getLog(LoginAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -1872868236628398675L;

	/**
	 * 登录账号
	 */
	private String passport;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 用户名字
	 */
	private String name;
	/**
	 * 是否AD验证
	 */
	private boolean validate;

	/**
	 * 域名
	 */
	private String domain;

	private ICAService caService;

	private String jdbcUrl;

	private String jdbcSid;

	private String jdbcUsername;

	private String jdbcPassword;

	private String ldapHost;

	private String ldapDomain;

	private String ldapUser;

	private String ldapPassword;

	private long onLine;

	/**
	 * sap portal sso.
	 */
	private ISAPService sapService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@PermissionSearch
	public String index() {
		// 过期访问权限控制
		HttpSession session = this.getSession();
		session.setAttribute("ACEGI_SECURITY_ACCESS", "access");

		String driver = PropertiesUtil.readValue(this.getServletRequest()
				.getRealPath("WEB-INF/env.properties"), "jdbc.url");
		if (!"".equals(driver)) {
			this.setFailMessage("");
			HttpServletRequest request = getServletRequest();
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length != 0) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if ("PS".equals(cookie.getName())) {
						passport = cookie.getValue();
						break;
					}
				}
			}
			return "index";
		} else {
			return "initCon";
		}
	}

	@PermissionSearch
	@SuppressWarnings("deprecation")
	public String initConnect() throws ClassNotFoundException, SQLException {
		setFailMessage("");
		String env = this.getServletRequest().getRealPath(
				"WEB-INF/env.properties");
		String jdbc = "jdbc:oracle:thin:" + jdbcUsername + "/" + jdbcPassword
				+ "@" + jdbcUrl + ":1521:" + jdbcSid;
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(jdbc, jdbcUsername, jdbcPassword);
		if (con != null) {
			PropertiesUtil.writeProperties(env, "jdbc.url", jdbc);
			PropertiesUtil.writeProperties(env, "jdbc.username", jdbcUsername);
			PropertiesUtil.writeProperties(env, "jdbc.password", jdbcPassword);
			if (!"".equals(ldapHost)) {
				String bs[] = ldapDomain.split("\\.");
				String base = "DC=" + bs[0] + ",DC=" + bs[1];
				String dn = "CN=" + ldapUser + ",CN=Users," + base;
				PropertiesUtil.writeProperties(env, "ldap.ldapHost", "ladp://"
						+ ldapHost + ":389");
				PropertiesUtil.writeProperties(env, "ldap.ldapHost2", "ladp://"
						+ ldapHost + ":636");
				PropertiesUtil.writeProperties(env, "ldap.domain", "@"
						+ ldapDomain);
				PropertiesUtil.writeProperties(env, "ldap.base", base);
				PropertiesUtil.writeProperties(env, "ldap.userDn", dn);
				PropertiesUtil.writeProperties(env, "ldap.password",
						ldapPassword);
				PropertiesUtil.writeProperties(env, "ldap.validate", "true");
			} else {
				PropertiesUtil.writeProperties(env, "ldap.validate", "false");
			}
			con.close();
			// 重载bean
			XmlWebApplicationContext context = (XmlWebApplicationContext) WebApplicationContextUtils
					.getWebApplicationContext(this.getServletRequest()
							.getSession().getServletContext());
			context.refresh();
		}
		return "index";
	}

	/**
	 * 登录页
	 * 
	 * @return
	 */
	@PermissionSearch
	@SuppressWarnings("unchecked")
	public String login() {
		// 判断过期访问权限是否已经销毁
		String access = (String) getServletRequest().getSession().getAttribute(
				"ACEGI_SECURITY_ACCESS");

		if (("access").equals(access)) {

			ValidateResult result = null;

			// 添加仅经销商验证
			AllUsers typeuser1 = caService.getAllUserByPassport(passport);
			if(null ==typeuser1){
				this.setFailMessage("账户不存在！");
				return LOGFAIL;
			}
			if ("A".equals(typeuser1.getCustType())) {
				result = caService.validateUser(passport, password);
			} else {
				if (validate) {// true:需要域验证 false:不需要域验证
					result = caService.validateUserByLDAP(passport, password);
				} else {
					result = caService.validateUser(passport, password);
				}
			}

			// 验证失败
			if (ICAService.RESULT_FAILED.equals(result.getResultCode())
					|| ICAService.RESULT_ERROR.equals(result.getResultCode())) {
				this.setFailMessage(result.getMessage());
				return LOGFAIL;
			}

			AllUsers loginUser = result.getAllUser();
			name = loginUser.getUserName();

			HttpSession session = this.getSession();
			session.setAttribute("ACEGI_SECURITY_LAST_LOGINID",
					loginUser.getLoginId());
			session.setAttribute("ACEGI_SECURITY_LAST_LOGINUSER", loginUser);
			HttpServletResponse response = getServletResponse();
			if (response != null) {
				Cookie ps = new Cookie("PS", loginUser.getLoginId());
				ps.setPath("/");
				ps.setDomain(domain);
				// ps.setMaxAge(3600);
				response.addCookie(ps);
				try {
					password = EncryptUtil.md5Encry(password);
				} catch (Exception e) {
					password = null;
				}
				Cookie pw = new Cookie("PW", password);
				pw.setPath("/");
				pw.setDomain(domain);
				// pw.setMaxAge(3600);
				response.addCookie(pw);

				// 论坛SSO登录 从cooike取
				try {
					String nameTemp = URLEncoder.encode(name, "gbk");
					// admin登录只存loginid
					String cookieV = "admin".equals(loginUser.getLoginId()) ? loginUser
							.getLoginId() : nameTemp + "("
							+ loginUser.getLoginId() + ")";
					Cookie jforumName = new Cookie("jforumName", cookieV);
					jforumName.setPath("/");
					jforumName.setDomain(domain);
					// jforumName.setMaxAge(3600);
					response.addCookie(jforumName);
				} catch (UnsupportedEncodingException e) {
					logger.error(e);
				}
			}
			onLine = OnlineCounter.getOnline();
			// 初始化密码
			if ("A".equals(typeuser1.getCustType())) {
				if ("".equals(typeuser1.getExpiredpsw())||null==typeuser1.getExpiredpsw()) {//判断是否等一次登录
					return "kunnrchangePassword";
				}
			} else if ("e10adc3949ba59abbe56e057f20f883e".equals(password)) {
				return "changePassword";
			}
			// 初始密码修改

			// 登录LOG
			String ip = getServletRequest().getRemoteAddr();
			AllUsers loginInfo = new AllUsers();
			loginInfo.setLoginId(passport);
			loginInfo.setIp(ip);
			caService.loginLog(loginInfo);
			if ("A".equals(loginUser.getCustType())) {
				System.out.println("kunnrsuccess");
				return "kunnrsuccess";
			}
			return SUCCESS;
		} else {
			return "logintimeout";
		}
	}

	@PermissionSearch
	public String logout() {
		HttpSession session = this.getSession();
		HttpServletResponse response = getServletResponse();
		try {
			// 退出sap portal
			String ssoUrl = (String) session.getAttribute("SAP_PORTAL_SSO_URL");
			if (StringUtils.isNotEmpty(ssoUrl)) {
				try {
					HttpUtil.post(ssoUrl + "?~OKCODE=/nex", null);
				} catch (Exception e) {
					logger.error(e);
				}
				sapService.removePermission("wsap001");
				sapService.removePermission("wsap002");
				session.removeAttribute("SAP_PORTAL_SSO_URL");
			}

			session.removeAttribute("ACEGI_SECURITY_LAST_LOGINID");
			session.removeAttribute("ACEGI_SECURITY_LAST_LOGINUSER");
			session.removeAttribute("ACEGI_SECURITY_ACCESS");
			session.invalidate();
			// 删除论坛SSO
			Cookie cookie = new Cookie("jforumSSOCookieNameUser", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			if ("logout"
					.equals(this.getServletRequest().getParameter("action"))) {
				this.setFailMessage("您已成功退出exp系统!");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return LOGOUT;
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

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public ICAService getCaService() {
		return caService;
	}

	public void setCaService(ICAService caService) {
		this.caService = caService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
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

	public String getJdbcSid() {
		return jdbcSid;
	}

	public void setJdbcSid(String jdbcSid) {
		this.jdbcSid = jdbcSid;
	}

	public long getOnLine() {
		return onLine;
	}

	public void setOnLine(long onLine) {
		this.onLine = onLine;
	}

	public ISAPService getSapService() {
		return sapService;
	}

	public void setSapService(ISAPService sapService) {
		this.sapService = sapService;
	}

}
