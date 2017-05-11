package com.kintiger.platform.framework.content.interceptor;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Map;

import ognl.ObjectPropertyAccessor;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import ognl.PropertyAccessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.framework.annotations.Decode;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.ParametersInterceptor;

/**
 * 此类在webwork配置文件中配置时须加在params之前 用于处理AJAX请求时的乱码问题
 * 
 * @author tingjia.chentj
 */
public class DecodeParametersInterceptor extends ParametersInterceptor {

	private static final long serialVersionUID = 6484320257843908148L;

	private static final Log logger = LogFactory
			.getLog(DecodeParametersInterceptor.class);

	private final static ThreadLocal<Boolean> encoded = new ThreadLocal<Boolean>();

	public static boolean isEncoded() {
		return encoded.get() == null ? false : encoded.get();
	}

	public static void setEncoded(boolean value) {
		encoded.set(value);
	}

	static {
		OgnlRuntime.setPropertyAccessor(Object.class, getObjectAccessor());
	}

	protected void after(ActionInvocation invocation, String result)
			throws Exception {
		encoded.set(null);
		encoded.remove();
	}

	protected void before(ActionInvocation invocation) throws Exception {
		setEncoded("XMLHttpRequest".equalsIgnoreCase(ServletActionContext
				.getRequest().getHeader("x-requested-with")));
	}

	private static final PropertyAccessor getObjectAccessor() {
		// 在设置前根据需要先进行URLDecode
		return new ObjectPropertyAccessor() {
			@Override
			public void setProperty(Map context, Object target, Object oname,
					Object value) throws OgnlException {
				setEncoded("XMLHttpRequest"
						.equalsIgnoreCase(ServletActionContext.getRequest()
								.getHeader("x-requested-with")));
				if (DecodeParametersInterceptor.isEncoded()) {
					try {
						boolean decode = target.getClass().isAnnotationPresent(
								Decode.class);
						decode = decode
								|| getDeclaredField(target.getClass(),
										(String) oname).isAnnotationPresent(
										Decode.class);
						if (decode) {
							String[] tmp = (value == null ? new String[0]
									: (String[]) value);
							for (int i = 0, len = tmp.length; i < len; i++) {
								if (tmp[i] != null) {
									tmp[i] = URLDecoder.decode(tmp[i], "UTF-8");
								}
							}
						}
					} catch (Exception e) {
						// getDeclaredField可能会返回空值（找不到相应的field时）
						logger.error(e); // do nothing
					}
				}
				super.setProperty(context, target, oname, value);
				encoded.set(null);
				encoded.remove();

			}

			private Field getDeclaredField(Class target, String name) {
				if (target == null)
					return null;
				try {
					return target.getDeclaredField(name);
				} catch (Exception e) {
					return getDeclaredField(target.getSuperclass(), name);
				}
			}
		};
	}

}
