package com.kintiger.platform.file.dao.impl;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.file.dao.IFileDao;
import com.kintiger.platform.file.pojo.BudgetFileTmp;

public class FileDaoImpl extends BaseDaoImpl implements IFileDao{

	public BudgetFileTmp getFileByFileId(Long fileId) {
		return (BudgetFileTmp) getSqlMapClientTemplate().queryForObject("files.queryFileTmpByKey", fileId);
	}

}
