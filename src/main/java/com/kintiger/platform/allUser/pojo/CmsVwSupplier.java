package com.kintiger.platform.allUser.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 員工內部供應商信息
 * 
 */
public class CmsVwSupplier extends SearchInfo {

	private static final long serialVersionUID = -6103728926926948912L;

	private String supplierNumber;

	private String supplierNameZh;

	private String supplierType;

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplierNameZh() {
		return supplierNameZh;
	}

	public void setSupplierNameZh(String supplierNameZh) {
		this.supplierNameZh = supplierNameZh;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

}
