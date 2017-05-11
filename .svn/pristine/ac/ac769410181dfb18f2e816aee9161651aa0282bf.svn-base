package com.kintiger.platform.framework.content.interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 权限验证拦截器
 * 
 * 若用户尚未登录，则跳转到登录页面
 * 
 * 
 */
public class AuthencationInterceptor implements Interceptor {
	private static final long serialVersionUID = -7498838714747075663L;
	private static final String LOGIN_TIMEOUT = "logintimeout";

	public void init() {
	}

	public void destroy() {
	}

	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		String loginId = (String) session.get("ACEGI_SECURITY_LAST_LOGINID");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request == null) {
			return LOGIN_TIMEOUT;
		}
		if (StringUtils.isNotEmpty(loginId)) {
			String ps = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length != 0) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if ("PS".equals(cookie.getName())) {
						ps = cookie.getValue();
						break;
					}
				}
			}
			// 一致 -> 验证通过; 不一致 -> 删除当前session
			if (loginId.equals(ps)) {
				return invocation.invoke();
			} else {
				// 删除session
				Enumeration enumeration = request.getSession()
						.getAttributeNames();
				while (enumeration.hasMoreElements()) {
					request.getSession().removeAttribute(
							(String) enumeration.nextElement());
				}

				request.getSession().invalidate();
			}
		}
		return LOGIN_TIMEOUT;
	}

	/**
	 * 解析当前请求actionName
	 * 
	 * @return
	 */
	private String getActionName() {
		String actionName = null;
		// 获取当前applicationContex
		ActionContext ctx = ActionContext.getContext();
		Map map = ctx.getSession();
		// 设置当前请求的URL
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		StringBuffer url = request.getRequestURL();
		int index = url.lastIndexOf(request.getContextPath())
				+ request.getContextPath().length();
		actionName = url.substring(index, url.length());
		return actionName;
	}

	private String getRequetSessionId() {
		// 获取当前applicationContex
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		return request.getRequestedSessionId();
	}

	private Map getRequestParam() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		return request.getParameterMap();
	}

}
