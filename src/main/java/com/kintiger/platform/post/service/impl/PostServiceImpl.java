package com.kintiger.platform.post.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.post.dao.IPostDao;
import com.kintiger.platform.post.pojo.EmpPost;
import com.kintiger.platform.post.service.IPostService;

public class PostServiceImpl  implements IPostService{

	private IPostDao postDao;
	private static final Log logger = LogFactory.getLog(PostServiceImpl.class);
	
	public BooleanResult createEmpPost(EmpPost empPost) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		try {
			Long c = postDao.createEmpPost(empPost);
			if (c !=null) {
				result.setResult(true);
			}
		} catch (Exception e) {
			result.setCode("职务创建失败");
			logger.error(LogUtil.parserBean(empPost), e);
		}

		return result;
	}

	public int getPostNameCount(EmpPost empPost) {
		try{
			return postDao.getPostNameCount(empPost);
		}catch (Exception e){
			logger.error(LogUtil.parserBean(empPost), e);
		}
		return 0;
	}
	
	public int getEmpPostCount(EmpPost empPost) {
		try{
			return postDao.getEmpPostCount(empPost);
		}catch (Exception e){
			logger.error(LogUtil.parserBean(empPost), e);
		}
		return 0;
	}

	public List<EmpPost> getEmpPostList(EmpPost empPost) {
		try{
			return postDao.getEmpPostList(empPost);
		}catch (Exception e){
			logger.error(LogUtil.parserBean(empPost), e);
		}
		return null;
	}
	
	public BooleanResult deleteEmpPostById(EmpPost empPost) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		try {
			int c = postDao.deleteEmpPostById(empPost);
			if (c !=0) {
				result.setResult(true);
				result.setCode(String.valueOf(c));
			}
		} catch (Exception e) {
			result.setCode("职务删除失败");
			logger.error(LogUtil.parserBean(empPost), e);
		}

		return result;
	}


	public int getEmpPostCount(String orgId4Post) {
		try{
			return postDao.getEmpPostCount(orgId4Post);
		}catch (Exception e){
			logger.error(LogUtil.parserBean(orgId4Post), e);
		}
		return 0;
	}

	public List<EmpPost> getEmpPostList(String orgId4Post) {
		try{
			return postDao.getEmpPostList(orgId4Post);
		}catch (Exception e){
			logger.error(LogUtil.parserBean(orgId4Post), e);
		}
		return null;
	}
	
	public IPostDao getPostDao() {
		return postDao;
	}

	public void setPostDao(IPostDao postDao) {
		this.postDao = postDao;
	}


	
}
