package com.kintiger.platform.post.service;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.post.pojo.EmpPost;

public interface IPostService {
	
	/**
	 * 创建职务
	 * @param empPost
	 * @return
	 */
	public BooleanResult createEmpPost(EmpPost empPost);

	/**
	 * 查找某一组织下新录入职务名是否存在
	 * @param empPost
	 * @return
	 */
	public int getPostNameCount(EmpPost empPost);

	public int getEmpPostCount(EmpPost empPost);
	/**
	 * 查找某组织下某职务
	 * @param empPost
	 * @return
	 */
	public List<EmpPost> getEmpPostList(EmpPost empPost);

	public BooleanResult deleteEmpPostById(EmpPost empPost);

	public int getEmpPostCount(String orgId4Post);

	public List<EmpPost> getEmpPostList(String orgId4Post);

}
