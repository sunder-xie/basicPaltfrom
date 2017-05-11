package com.kintiger.platform.qq_email.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.kintiger.platform.allUser.pojo.AllUsers;

/**
 * 同步成员帐号信息
 * 
 * @author Administrator
 *
 */
public class OperateUser {
	public static final String crimp = "http://openapi.exmail.qq.com:12211/openapi/user/sync";
	private static String token = (new EmailAction()).getToken();

	public static void alterUser(AllUsers alluser, String path) {
		HttpURLConnection httpConnection = null;
		URL urlcrm = null;
		try {
			urlcrm = new URL(crimp);
			httpConnection = (HttpURLConnection) urlcrm.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setUseCaches(false);
			httpConnection.setInstanceFollowRedirects(true);
			httpConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			String E_email = alluser.getRtx_LoginId() + "@chinaxpp.com";
			String params = "access_token=" + token + "&action=3&alias="
					+ E_email + "&name=" + alluser.getUserName() + "&gender="
					+ alluser.getSex() + "&position="
					+ alluser.getEmpPostName() + "&tel=&mobile="
					+ alluser.getBusMobilephone() + "&PartyPath=" + path;
			OutputStreamWriter out = new OutputStreamWriter(
					httpConnection.getOutputStream(), "UTF-8");
			out.write(params);
			out.flush();
			out.close();
			httpConnection.getContentEncoding();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void AddUser(AllUsers alluser, String path) {
		HttpURLConnection httpConnection = null;
		URL urlcrm = null;
		try {
			urlcrm = new URL(crimp);
			httpConnection = (HttpURLConnection) urlcrm.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setUseCaches(false);
			httpConnection.setInstanceFollowRedirects(true);
			System.out.println(token);
			httpConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			String E_email = alluser.getRtx_LoginId() + "@chinaxpp.com";
			String params = "access_token=" + token + "&action=2&alias="
					+ E_email + "&name=" + alluser.getUserName() + "&gender="
					+ alluser.getSex() + "&position="
					+ alluser.getEmpPostName() + "&mobile="
					+ alluser.getBusMobilephone() + "&PartyPath=" + path
					+ "&password=xpp@2015!&" + "md5=0" + "&openType=1";
			OutputStreamWriter out = new OutputStreamWriter(
					httpConnection.getOutputStream(), "UTF-8");
			out.write(params);
			out.flush();
			out.close();
			httpConnection.getContentEncoding();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void DelUser(AllUsers alluser) {
		if (!alluser.getRtx_LoginId().equals("")
				|| alluser.getRtx_LoginId() != null) {
			HttpURLConnection httpConnection = null;
			URL urlcrm = null;
			try {
				urlcrm = new URL(crimp);
				httpConnection = (HttpURLConnection) urlcrm.openConnection();
				httpConnection.setDoOutput(true);
				httpConnection.setDoInput(true);
				httpConnection.setRequestMethod("POST");
				httpConnection.setUseCaches(false);
				httpConnection.setInstanceFollowRedirects(true);
				System.out.println(token);
				httpConnection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				String E_email = alluser.getRtx_LoginId() + "@chinaxpp.com";
				String params = "access_token=" + token + "&action=1&alias="
						+ E_email;
				OutputStreamWriter out = new OutputStreamWriter(
						httpConnection.getOutputStream(), "UTF-8");
				out.write(params);
				out.flush();
				out.close();
				httpConnection.getContentEncoding();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
