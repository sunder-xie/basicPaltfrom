package com.kintiger.platform.wfe.dao;

import java.util.List;

import com.kintiger.platform.wfe.pojo.ActProcdef;

public interface IModelDao {

	public List<ActProcdef> invokeUserModel(ActProcdef actProcdef);
}
