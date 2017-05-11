package com.kintiger.platform.framework.cookie;

import java.util.HashMap;
import java.util.Map;

import com.kintiger.platform.allUser.pojo.AllUsers;

public class SecurityContext {

	private static final String USER = "USER";

	private static final ThreadLocal<Object> context = new ThreadLocal<Object>() {
		protected Object initialValue() {
			return new HashMap<Object, Object>();
		}
	};

	@SuppressWarnings("unchecked")
	public static Map<String, AllUsers> getContext() {
		return (Map) context.get();
	}

	public static void clear() {
		getContext().clear();
	}

	public static AllUsers getUser() {
		return (AllUsers) getContext().get(USER);
	}

	public static void setUser(AllUsers user) {
		getContext().put(USER, user);
	}

}
