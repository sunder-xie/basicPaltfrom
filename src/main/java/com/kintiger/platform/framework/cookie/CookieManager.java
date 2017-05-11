package com.kintiger.platform.framework.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 提供App Center Cookie的统一管理
 * 
 * @author jacky.chenb
 */
public class CookieManager extends CookieManagerBase {

	private static final String COOKIE_EUP_TMP = "FI_COOKIE_EUP_TMP";

	public static final String USER_ID = "USER_ID";
	public static final String USER_NAME = "USER_NAME";
	public static final String USER_PASSPORT = "USER_PASSPORT";
	public static final String USER_PASSWORD = "USER_PASSWORD";
	public static final String USER_CORP_ID = "USER_CORPORATION_ID";
	public static final String USER_CORP_NAME = "USER_CORPORATION_NAME";
	public static final String USER_ROLEIDS = "USER_ROLEIDS";
	public static final String PRO_SOLUTION = "PRO_SOLUTION";
	public static final String APP_INSTANCE_ID = "APP_INSTANCE_ID";
	public static final String APP_ID = "APP_ID";
	public static final String APP_VERSION = "APP_VERSION"; // 当前用户版本信息
	public static final String BOOKSITE_ID = "BOOKSITE_ID";
	public static final String IS_ADMIN = "IS_ADMIN"; // y是admin, n不是admin
	public static final String ALLOW_PROTOCAL = "ALLOW_PROTOCAL"; // 是否接受弹出新手上路的flash
	public static final String ALLOW_INTO_INDEX = "ALLOW_INTO_INDEX"; // 是否允许进入首页
	public static final String REQ_SESSION_ID = "REQ_SESSION_ID"; // 请求实例sessionId
	public static final String LOGIN_TYPE = "LOGIN_TYPE";
	public static final String TO_TARGET = "TO_TARGET";// 指定跳转的地址参数

	public CookieManager(HttpServletRequest request,
			HttpServletResponse response, String domain, String path) {

		super(request, response, domain, path);
	}

	public CookieManager(HttpServletRequest request) {
		this(request, null, null, null);
	}

	/**
	 * 获得临时cookie中的一个项值
	 */
	public String getTempCookie(String key) {
		return getTempCookie(key, null);
	}

	/**
	 * 获得临时cookie中的一个项值。若为空，则返回defaultValue
	 */
	public String getTempCookie(String key, String defaultValue) {
		return getValue(COOKIE_EUP_TMP, key, defaultValue);
	}

	/**
	 * 设置临时cookie的一个项值。若value为空，则相当于removeTempCookie
	 */
	public void setTempCookie(String key, String value) {
		setValue(COOKIE_EUP_TMP, key, value);
	}

	/**
	 * 删除一个临时cookie项
	 */
	public void removeTempCookie(String key) {
		setValue(COOKIE_EUP_TMP, key, null);
	}

	/**
	 * 保存临时cookie中所有的项，并且写入response
	 */
	public void saveTempCookie() {
		save(COOKIE_EUP_TMP, TEMP_COOKIE_AGE);
	}

	public void removeCookie() {
		remove(COOKIE_EUP_TMP);
	}
}
