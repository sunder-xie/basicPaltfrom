package com.kintiger.platform.login.service;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.login.pojo.ValidateResult;

/**
 * 权限验证
 * 
 * @author xujiakun
 * 
 */
public interface ICAService {

	/**
	 * 成功
	 */
	public static final String RESULT_SUCCESS = "0";

	/**
	 * 失败
	 */
	public static final String RESULT_FAILED = "1";

	/**
	 * 系统级错误
	 */
	public static final String RESULT_ERROR = "2";

	public static final String INCORRECT_NULL = "用户名或密码不能为空！";

	public static final String INCORRECT_LOGINID = "该用户在系统中不存在！";

	public static final String INCORRECT_LOGIN = "用户名或密码输入不正确！";

	public static final String INCORRECT_TOKEN = "token验证失败！";

	/**
	 * 验证登录（不通过域验证）
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult validateUser(String passport, String password);

	/**
	 * 验证登录（通过域验证）
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult validateUserByLDAP(String passport, String password);

	/**
	 * 验证token
	 * 
	 * @param token
	 * @return
	 */
	public ValidateResult validateToken(String token);

	/**
	 * 生成免登token
	 * 
	 * @param object
	 * @return
	 */
	public String generateToken(Object object);
	
	/**
	 * 
	 * @param passport
	 * @return
	 */
	public AllUsers getAllUserByPassport(String passport);

	/**
	 * 登录记录
	 * 
	 * @param passport
	 */
	public void loginLog(AllUsers loginInfo);

}
