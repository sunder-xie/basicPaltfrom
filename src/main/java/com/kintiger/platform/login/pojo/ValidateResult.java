package com.kintiger.platform.login.pojo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.kintiger.platform.allUser.pojo.AllUsers;

/**
 * 验证登陆后返回信息
 * 
 * @author xujiakun
 * 
 */
@XmlRootElement
public class ValidateResult implements Serializable {

	private static final long serialVersionUID = 807590090225810140L;

	// 返回结果
	private String resultCode;

	// 信息
	private String message;

	// 登陆用户信息
	private AllUsers allUser;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AllUsers getAllUser() {
		return allUser;
	}

	public void setAllUser(AllUsers allUser) {
		this.allUser = allUser;
	}

}
