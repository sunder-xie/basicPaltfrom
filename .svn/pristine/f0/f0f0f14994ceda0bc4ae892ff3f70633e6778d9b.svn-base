package com.kintiger.platform.login.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.framework.mail.MailService;
import com.kintiger.platform.framework.util.EncryptUtil;
import com.kintiger.platform.login.service.ILDAPService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;

public class LDAPServiceImpl implements ILDAPService {

	private Log logger = LogFactory.getLog(LDAPServiceImpl.class);
	private LdapTemplate ldapTemplate;
	private IOrgService orgService;
	private String domain;
	private String url;
	private String userDn;
	private String pwd;
	private String base;
	private static final int UF_ACCOUNTDISABLE = 0x0002;
	private static final int UF_PASSWD_NOTREQD = 0x0020;
	private static final int UF_NORMAL_ACCOUNT = 0x0200;
	private static final int UF_PASSWORD_EXPIRED = 0x800000;
	private static final int UF_PASSWD_CANT_CHANGE = 0x0040; // 用户不能更改密码。可以读取此标志，但不能直接设置它。
	private static final int UF_DONT_EXPIRE_PASSWD = 0x10000; // 表示在该帐户上永远不会过期的密码。
	private static final String SUN_JNDI_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";
	private LdapContext ctx;
	
	private String emailaddress;
	private String emailpassword;
	private String smtpServer;
	private String from;
	private String displayName;

	/**
	 * 用户域认证
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public boolean authenticate(String passport, String password) {
		try {
			Borg org = orgService.getOrgByLoginId(passport);
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectclass", "user")).and(
					new EqualsFilter("sAMAccountName", passport));
			boolean authenticated = ldapTemplate.authenticate(buildDn4Org(org),
					filter.toString(), password);
			return authenticated;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	public boolean addUser2Ad(AllUsers user) {
		try {
			ldapTemplate.bind(buildDn4User(user), null,
					buildAttributes4User(user));
			if (ctx == null) {
				init();
			}
			ModificationItem[] mods = new ModificationItem[2];
			String newQuotedPassword = "\"" + user.getExpressly() + "\"";
			byte[] newUnicodePassword = null;
			try {
				newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("unicodePwd", newUnicodePassword));
			mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("userAccountControl",
							Integer.toString(UF_NORMAL_ACCOUNT
									+ UF_PASSWORD_EXPIRED)));
			ctx.modifyAttributes(buildDn4User(user) + "," + base, mods);
			return true;
		} catch (NamingException e) {
			logger.error(e);
			return false;
		} finally {
			destory();
		}

	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 */
	public boolean updateUser2Ad(AllUsers user, String oldOrg) {
		try {
			if (ctx == null) {
				init();
			}

			// 判断是否组织调动
			if (StringUtils.isNotEmpty(oldOrg)) {
				boolean flag = changeGroup(user, oldOrg);
				if (!flag) {
					return false;
				}
			}
			ModificationItem[] mods = new ModificationItem[] {
					new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
							new BasicAttribute("mobile", user
									.getBusMobilephone().trim())),
					new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
							new BasicAttribute("mail", user.getEmail().trim())) };

			ctx.modifyAttributes(buildDn4User(user) + "," + base, mods);

			return true;
		} catch (Exception e) {
			logger.error(e);
			MailService mail = new MailService(smtpServer, from,
					displayName, emailaddress, emailpassword,
					emailaddress, "修改账户AD异常确认函", e.toString());// 邮件做成
				Map<String, String> map = mail.send();
			return false;
		} finally {
			destory();
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param user
	 */
	public boolean deleteUser2Ad(AllUsers user) {
		try {
			ldapTemplate.unbind(buildDn4User(user));
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * 
	 * 禁用账号
	 */
	public boolean disableUser2Ad(AllUsers user) {
		if (ctx == null) {
			init();
		}
		ModificationItem[] mods = new ModificationItem[1];
		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
				new BasicAttribute("userAccountControl",
						Integer.toString(UF_ACCOUNTDISABLE)));
		try {
			ctx.modifyAttributes(buildDn4User(user) + "," + base, mods);
			return true;
		} catch (NamingException e) {
			logger.error(e);
			return false;
		} finally {
			destory();
		}
	}

	/**
	 * 
	 * 启用账号
	 */
	public boolean enableUser2Ad(AllUsers user) {
		if (ctx == null) {
			init();
		}
		ModificationItem[] mods = new ModificationItem[1];
		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
				new BasicAttribute("userAccountControl", Integer
						.toString(UF_NORMAL_ACCOUNT + UF_PASSWD_CANT_CHANGE
								+ UF_DONT_EXPIRE_PASSWD)));
		try {
			ctx.modifyAttributes(buildDn4User(user) + "," + base, mods);
			return true;
		} catch (NamingException e) {
			logger.error(e);
			MailService mail = new MailService(smtpServer, from,
					displayName, emailaddress, emailpassword,
					emailaddress, "启用账户AD异常确认函", e.toString());// 邮件做成
				Map<String, String> map = mail.send();
			return false;
		} finally {
			destory();
		}
	}

	/**
	 * 
	 * 修改密码账号
	 */
	public boolean modifyPassword2Ad(AllUsers user) {
		if (ctx == null) {
			init();
		}
		ModificationItem[] mods = new ModificationItem[1];

		String newQuotedPassword = "\"" + user.getExpressly() + "\"";
		byte[] newUnicodePassword = null;
		try {
			newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
				new BasicAttribute("unicodePwd", newUnicodePassword));
		try {
			ctx.modifyAttributes(buildDn4User(user) + "," + base, mods);
			return true;
		} catch (NamingException e) {
			logger.error(e);
			MailService mail = new MailService(smtpServer, from,
					displayName, emailaddress, emailpassword,
					emailaddress, "修改密码AD异常确认函", e.toString());// 邮件做成
				Map<String, String> map = mail.send();
			return false;
		} finally {
			destory();
		}
	}

	/**
	 * 新增组织
	 * 
	 * @param org
	 */
	public boolean addOrg2Ad(Borg org) {
		try {
			ldapTemplate.bind(buildDn4Org(org), null, buildAttributes4Org(org));
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * 修改组织
	 * 
	 * @param org
	 */
	public boolean updateOrg2Ad(Borg oldOrg, Borg newOrg) {
		try {
			if (ctx == null) {
				init();
			}
			ctx.rename(buildDn4Org(oldOrg) + "," + base, buildDn4NewOrg(newOrg)
					+ "," + base);
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			destory();
		}

	}

	/**
	 * 
	 * 组织调拨 暂不提供
	 */
	public boolean exchangeOrg2Ad(Borg org) {
		return true;
	}

	/**
	 * 删除组织
	 * 
	 * @param org
	 */
	public boolean deleteOrg2Ad(Borg org) {
		try {
			ldapTemplate.unbind(buildDn4Org(org));
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * 帐户更换组织
	 * 
	 * @return
	 */
	public boolean changeGroup(AllUsers user, String oldOrgId) {
		try {
			if (ctx == null) {
				init();
			}
			Borg oldOrg = new Borg();
			Borg newOrg = new Borg();

			oldOrg.setOrgId(Long.valueOf(oldOrgId));
			newOrg.setOrgId(Long.valueOf(user.getOrgId()));
			newOrg.setOrgName(user.getOrgName());

			AllUsers oldUser = new AllUsers();
			oldUser.setOrgId(oldOrgId);
			oldUser.setLoginId(user.getLoginId());

			String userName = buildDn4User(oldUser).toString() + "," + base; // 旧用户
			String newUserName = buildDn4NewUser(user, newOrg).toString() + ","
					+ base;// 新用户
			ctx.rename(userName, newUserName);
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * 根据用户获取当前用户的所有组织
	 * 
	 * @param user
	 * @return
	 */
	private Name buildDn4User(AllUsers user) {
		DistinguishedName dn = new DistinguishedName();
		if (null == user.getOrgId()) {
			user.setOrgId(orgService.getOrgByUserId(user.getUserId())
					.getOrgId().toString());
		}
		List<Borg> allParent = orgService.getAllParentOrgs(Long.parseLong(user
				.getOrgId()));
		for (Borg o : allParent) {
			dn.add("ou", o.getOrgName());
		}
		dn.add("cn", user.getLoginId());
		return dn;
	}

	/**
	 * 账户更改组织，获取新组织
	 * 
	 * @param user
	 * @param org
	 * @return
	 */
	private Name buildDn4NewUser(AllUsers user, Borg org) {
		DistinguishedName dn = new DistinguishedName();
		List<Borg> allParent = orgService.getAllParentOrgs(org.getOrgId());
		for (Borg o : allParent) {
			dn.add("ou", o.getOrgName());
		}
		dn.add("cn", user.getLoginId());
		return dn;
	}

	/***
	 * 根据当前组织获取所有父级组织
	 * 
	 * @param org
	 * @return
	 */
	private Name buildDn4Org(Borg org) {
		DistinguishedName dn = new DistinguishedName();
		List<Borg> allParent = new ArrayList<Borg>();
		if (org.getOrgId() == null) {
			allParent = orgService.getAllParentOrgs(org.getOrgParentId());
		} else {
			allParent = orgService.getAllParentOrgs(org.getOrgId());
		}
		for (Borg o : allParent) {
			dn.add("ou", o.getOrgName());
		}
		if (org.getOrgId() == null) {
			dn.add("ou", org.getOrgName());
		}
		return dn;
	}

	/**
	 * 获取修改的组织
	 * 
	 * @param org
	 * @return
	 */
	private Name buildDn4NewOrg(Borg org) {
		DistinguishedName dn = new DistinguishedName();
		List<Borg> allParent = orgService.getAllParentOrgs(org.getOrgId());
		for (int i = 0; i < allParent.size() - 1; i++) {
			dn.add("ou", allParent.get(i).getOrgName());
		}
		dn.add("ou", org.getOrgName());
		return dn;
	}

	/***
	 * 初始化用户参数
	 * 
	 * @param user
	 * @return
	 */
	private Attributes buildAttributes4User(AllUsers user) {
		Attributes attrs = new BasicAttributes();
		BasicAttribute ocattr = new BasicAttribute("objectclass");
		ocattr.add("top");
		ocattr.add("user");
		attrs.put(ocattr);
		attrs.put("cn", user.getLoginId());
		attrs.put("sn", user.getUserName());
		attrs.put("sAMAccountName", user.getLoginId());
		attrs.put("description", user.getUserName());
		attrs.put("userPrincipalName", user.getLoginId() + domain);
		attrs.put("mail", StringUtils.isEmpty(user.getEmail()) ? "123@123.com"
				: user.getEmail());
		attrs.put(
				"mobile",
				StringUtils.isEmpty(user.getBusMobilephone()) ? "123" : user
						.getBusMobilephone());
		attrs.put(
				"userAccountControl",
				Integer.toString(UF_NORMAL_ACCOUNT + UF_PASSWD_NOTREQD
						+ UF_PASSWORD_EXPIRED + UF_ACCOUNTDISABLE));
		return attrs;
	}

	private Attributes buildAttributes4Org(Borg org) {
		BasicAttribute basic = new BasicAttribute("objectClass");
		// 创建跟节点
		if (org.getOrgParentId() == 0L) {
			basic.add("top");
		}
		basic.add("organizationalUnit");
		Attributes attrs = new BasicAttributes();
		attrs.put(basic);
		attrs.put("ou", org.getOrgName().trim());
		return attrs;
	}

	/**
	 * 初始化
	 */
	protected void init() {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, SUN_JNDI_PROVIDER);
		env.put(Context.PROVIDER_URL, url);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, userDn);
		env.put(Context.SECURITY_CREDENTIALS, pwd);
		env.put(Context.SECURITY_PROTOCOL, "ssl");
		try {
			ctx = new InitialLdapContext(env, null);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void destory() {
		try {
			if (ctx != null) {
				ctx.close();
				ctx = null;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public LdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserDn() {
		return userDn;
	}

	public void setUserDn(String userDn) {
		this.userDn = userDn;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public LdapContext getCtx() {
		return ctx;
	}

	public void setCtx(LdapContext ctx) {
		this.ctx = ctx;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getEmailpassword() {
		return emailpassword;
	}

	public void setEmailpassword(String emailpassword) {
		this.emailpassword = emailpassword;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
