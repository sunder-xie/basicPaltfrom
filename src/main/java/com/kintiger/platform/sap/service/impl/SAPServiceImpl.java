package com.kintiger.platform.sap.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.sap.pojo.SAPConnectionBean;
import com.kintiger.platform.sap.service.ISAPService;
import com.sap.mw.jco.JCO;

public class SAPServiceImpl implements ISAPService {

	private Logger logger = Logger.getLogger(SAPServiceImpl.class);

	private SAPConnectionBean sapConnection;

	public String updatePermission(String passport, List<String> roles, String loginId, String ip) throws Exception {
		JCO.Client client = null;
		try {
			client = this.sapConnection.getSAPClientFromPool();

			this.sapConnection.setFuncName("ZRFC_WES_LOGIN");
			JCO.Function func = this.sapConnection.getFunction(client);

			JCO.Table table = func.getTableParameterList().getTable("ACTIVITYGROUPS");
			JCO.ParameterList output = func.getExportParameterList();
			JCO.ParameterList input = func.getImportParameterList();

			input.setValue(passport, "USERNAME");
			input.setValue(loginId, "WESNAME");
			input.setValue(ip, "USERIP");
			int tableNum = 0;
			if ((roles != null) && (roles.size() > 0)) {
				for (String role : roles) {
					table.appendRow();
					table.setRow(tableNum);
					table.setValue(role, "AGR_NAME");
					tableNum++;
				}
			}

			client.execute(func);
		} catch (Exception e) {
			throw new Exception("RFC²Ù×÷Ê§°Ü£¡", e);
		} finally {
			if (client != null) {
				JCO.releaseClient(client);
			}
			client = null;
		}

		return null;
	}

	public boolean removePermission(String passport) {
		try {
			updatePermission(passport, null, null, null);
			return true;
		} catch (Exception e) {
			this.logger.error("passport:" + passport, e);
		}

		return false;
	}

	public SAPConnectionBean getSapConnection() {
		return this.sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

}
