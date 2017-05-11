package com.kintiger.platform.sap.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.kintiger.platform.framework.util.HttpUtil;
import com.kintiger.platform.sap.service.ISSOService;

public class SSOServiceImpl implements ISSOService {

	private static final String PARAM_CLIENT = "sap-client";

	private static final String PARAM_USER = "sap-user";

	private static final String PARAM_PASSWORD = "sap-password";

	private static final String STRING_END = "/bc/gui/sap/its/webgui";

	private Logger logger = Logger.getLogger(SSOServiceImpl.class);

	/**
	 * http://192.168.160.28:8000.
	 */
	private String portalHost;

	/**
	 * /sap/bc/gui/sap/its/webgui.
	 */
	private String portalService;

	/**
	 * 800.
	 */
	private String sapClient;

	public String getSSOUrl(String user, String password) throws Exception {
		String ssoUrl = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put(PARAM_CLIENT, sapClient);
			params.put(PARAM_USER, user);
			params.put(PARAM_PASSWORD, password);
			String responseStr = HttpUtil.post(portalHost + portalService, params);

			int end = responseStr.indexOf(STRING_END);
			if (end != -1) {
				ssoUrl = portalHost + responseStr.substring(end - 106, end + STRING_END.length());
			} else {
				throw new Exception(ISSOService.RESULT_ERROR);
			}
		} catch (Exception e) {
			throw new Exception(ISSOService.RESULT_ERROR, e);
		}

		return ssoUrl;
	}

	public String getMySAPSSO2Ticket(String user, String password) throws Exception {
		String ticket = null;
		StringBuilder str = new StringBuilder();
		try {
			str.append(portalHost).append(portalService).append("?").append(PARAM_CLIENT).append("=").append(sapClient)
				.append("&").append(PARAM_USER).append("=").append(user).append("&").append(PARAM_PASSWORD).append("=")
				.append(password);

			URL url = new URL(str.toString());
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("content-type", "application/binary;charset=UTF-8");
			int responseCode = ((HttpURLConnection) connection).getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				String headerName = connection.getHeaderFieldKey(1);

				for (int i = 1; StringUtils.isNotEmpty(headerName); i++) {
					if (headerName.equals("set-cookie")) {
						String cookie = connection.getHeaderField(i);
						if (cookie.indexOf("MYSAPSSO2") != -1) {
							ticket = cookie.substring(cookie.indexOf('=') + 1, cookie.indexOf(';'));
							break;
						}
					}
					headerName = connection.getHeaderFieldKey(i);
				}
			} else {
				throw new Exception(ISSOService.RESULT_ERROR);
			}
		} catch (Exception e) {
			logger.error("str:" + str.toString(), e);
			throw new Exception(ISSOService.RESULT_ERROR, e);
		}

		return ticket;
	}

	public String getPortalHost() {
		return portalHost;
	}

	public void setPortalHost(String portalHost) {
		this.portalHost = portalHost;
	}

	public String getPortalService() {
		return portalService;
	}

	public void setPortalService(String portalService) {
		this.portalService = portalService;
	}

	public String getSapClient() {
		return sapClient;
	}

	public void setSapClient(String sapClient) {
		this.sapClient = sapClient;
	}

}
