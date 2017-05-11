package com.kintiger.platform.file.dao;

import com.kintiger.platform.file.pojo.BudgetFileTmp;

public interface IFileDao {

	public BudgetFileTmp getFileByFileId(Long fileId);
}
