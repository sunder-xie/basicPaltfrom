package com.kintiger.platform.framework.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaodan.daixd 共享cookie，用于和网店用户免登
 * 
 */
public class ShareCookieManager extends CookieManagerBase {

	private static final String COOKIE_EUP_TMP = "FI_SHARE_TMP"; // cookie名字

	public static final String ESHOP_K_LOGIN_FLAG = "ESHOP_K_LOGIN_FLAG";

	public ShareCookieManager(HttpServletRequest request,
			HttpServletResponse response, String domain, String path) {
		super(request, response, domain, path);
	}

	public ShareCookieManager(HttpServletRequest request) {
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
