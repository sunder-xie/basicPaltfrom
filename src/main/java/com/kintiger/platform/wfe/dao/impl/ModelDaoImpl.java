package com.kintiger.platform.wfe.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.wfe.dao.IModelDao;
import com.kintiger.platform.wfe.pojo.ActProcdef;

@SuppressWarnings("rawtypes")
public class ModelDaoImpl extends BaseDaoImpl implements IModelDao {

	@SuppressWarnings("unchecked")
	public List<ActProcdef> invokeUserModel(ActProcdef actProcdef) {
		return (List<ActProcdef>)getSqlMapClientTemplate().queryForList(
					"wfe.getUserModel", actProcdef);

	}

}
