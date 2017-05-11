package com.kintiger.platform.framework.exception;

public class BaseException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8078667185858195871L;
	/**
	 * 可以根据时间生成Exception的ID
	 */
	private String _id;
	/**
	 * 发生异常的action name
	 */
	private String actionName;
	/**
	 * 发生异常的action name
	 */
	private String className;
	/**
	 * 发生异常的method name
	 */
	private String methodName;
	/**
	 * 发生异常的line number
	 */
	private String lineNumber;
	/**
	 * 对应编码
	 */
	private String statusCode;
	/**
	 * 错误提示信息
	 */
	private String errorMsg;
	/**
	 * 异常
	 */
	private Throwable throwable;
	
	public BaseException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public BaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public BaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	public BaseException(String id, String actionName, String className,
			String methodName, String lineNumber, String errorMsg,
			Throwable throwable) {
		super();
		_id = id;
		this.actionName = actionName;
		this.className = className;
		this.methodName = methodName;
		this.lineNumber = lineNumber;
		this.errorMsg = errorMsg;
		this.throwable = throwable;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String id) {
		_id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	
}
