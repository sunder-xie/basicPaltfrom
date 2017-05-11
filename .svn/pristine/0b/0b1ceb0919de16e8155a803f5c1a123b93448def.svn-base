package com.kintiger.platform.framework.content.interceptor;

import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 游客权限 只有查询权限
 * 
 * @author xxping
 * 
 */
public class PermissionIntercepter implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7836747075435408562L;
	private static final String NO_PERMISSION = "nopermission";

	public void destroy() {

	}

	public void init() {

	}

	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		String loginId = (String) session.get("ACEGI_SECURITY_LAST_LOGINID");
		String methodName = invocation.getProxy().getMethod();
		Method currentMethod = invocation.getAction().getClass()
				.getMethod(methodName);
		if (loginId != null && "guest".equals(loginId)) {
			PermissionSearch opreationType = currentMethod
					.getAnnotation(PermissionSearch.class);
			if (opreationType == null) {
				return NO_PERMISSION;
			} else {
				return invocation.invoke();
			}
		}
		return invocation.invoke();
	}

	
	
	/**
	 * 判断是否是ajax访问
	 * 
	 * @param request
	 * @return
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
}
