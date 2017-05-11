package com.kintiger.platform.qq_email.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.jfree.util.Log;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.PropertiesUtil;

public class EmailAction extends BaseAction{
	public static final String URL = "https://exmail.qq.com/cgi-bin/token";
	
	public String getToken() {
		String params = null;
		try {
			// 创建连接
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			params = "grant_type=client_credentials&client_id=chinaxpp.com&client_secret="+getKey();
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8");
			out.write(params);
			out.flush();
			out.close();
			connection.getContentEncoding();
			java.io.InputStream in = connection.getInputStream();
			BufferedReader breader = new BufferedReader(new InputStreamReader(
					in, "UTF-8"));
			String lines = "";
			String str = "";
			String json = "";
			while ((lines = breader.readLine()) != null) {
				str = lines;
				json = json + str;
			}
			String s = json.substring(18, 104);
			Log.info("==============" + s + ">>>>>>>access_token");
			return s;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	@PermissionSearch
	public String getKey() {
		try{
			String access_token = PropertiesUtil.readValue(this.getServletRequest()
					.getRealPath("WEB-INF/env.properties"), "access_token");
        return access_token;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
}
