package com.kintiger.platform.framework.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.opensymphony.xwork2.ActionContext;

public class CookieUtil {

	private static String cookieDomain = "kintiger.com.cn";
	private static String cookiePath = "/";
	private static final String CURRENT_USER = "currentUser";

	public static boolean saveCookies(AllUsers user,
			HttpServletRequest request, HttpServletResponse response) {
		boolean temp = true;
		try {

			// 往Cookies
			CookieManager cookieManager = new CookieManager(request, response,
					cookieDomain, cookiePath);
			cookieManager
					.setTempCookie(CookieManager.USER_ID, user.getUserId());

			cookieManager.setTempCookie(CookieManager.USER_NAME,
					user.getUserName());

			// 在Action上下文中放入user对象.
			ActionContext ctx = ActionContext.getContext();
			ctx.put(CURRENT_USER, user);
			SecurityContext.setUser(user);
			// 保存Cookies.
			cookieManager.saveTempCookie();

		} catch (Exception e) {
			temp = false;
		}

		return temp;
	}

	/**
	 * 实现系统之间免登能功能
	 * 
	 * @param request
	 * @param response
	 * @param tokenStr
	 *            加密字符串，淘宝账号，组装规则
	 *            LOGIN_ID=test123&timestamp=234242&sign=签名以后的str
	 * @return
	 */
	public static boolean saveShareCookies(String tokenStr,
			HttpServletRequest request, HttpServletResponse response) {
		boolean temp = true;
		try {
			// 实例化cookie对象
			ShareCookieManager shareCookieManager = new ShareCookieManager(
					request, response, cookieDomain, cookiePath);
			shareCookieManager.setTempCookie(
					ShareCookieManager.ESHOP_K_LOGIN_FLAG, tokenStr);
			// 保存Cookies.
			shareCookieManager.saveTempCookie();
		} catch (Exception e) {
			temp = false;
		}
		return temp;
	}

}
