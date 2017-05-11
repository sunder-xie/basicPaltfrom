package com.kintiger.platform.post.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.post.dao.IPostDao;
import com.kintiger.platform.post.pojo.EmpPost;

public class PostDaoImpl  extends BaseDaoImpl implements IPostDao{

	public Long createEmpPost(EmpPost empPost) {
		return (Long) getSqlMapClientTemplate().insert(
				"post.createEmpPost", empPost);
	}

	public int getPostNameCount(EmpPost empPost) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"post.getPostNameCount", empPost);
	}

	public int getEmpPostCount(EmpPost empPost) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"post.getEmpPostCount", empPost);
	}

	@SuppressWarnings("unchecked")
	public List<EmpPost> getEmpPostList(EmpPost empPost) {
		return  getSqlMapClientTemplate().queryForList(
				"post.getEmpPostList", empPost);
	}

	public int deleteEmpPostById(EmpPost empPost) {
		return getSqlMapClientTemplate().update(
				"post.deleteEmpPostById", empPost);
	}

	public int getEmpPostCount(String orgId4Post) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"post.getEmpPostCount4User", orgId4Post);
	}

	@SuppressWarnings("unchecked")
	public List<EmpPost> getEmpPostList(String orgId4Post) {
		return  getSqlMapClientTemplate().queryForList(
				"post.getEmpPostList4User", orgId4Post);
	}

}
