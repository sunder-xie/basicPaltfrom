package com.kintiger.platform.webservice.service;

import java.util.List;

import com.kintiger.platform.webservice.pojo.Page;
import com.kintiger.platform.webservice.pojo.ProcessEventDetail;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.webservice.pojo.UserUtil;

public interface IWebService {
	 
	/**
	 * 启动流程
	 * @param res
	 * @return
	 */
	public  String startWorkflow(Object[] res);
	
	/**
	 * 查询待办事务
	 * 
	 * @param res
	 * @return
	 */
	public  Page<ProcessEventTotal> findTodoTasks(Object[] res);
	
	/**
	 * 查询所有的事务
	 * @param res
	 * @return
	 */
	public Page<ProcessEventTotal> findAllTasks(Object[] res);
	/**
	 * 事务查询
	 * 
	 * @param res
	 * @return
	 */
	public  Page<ProcessEventTotal> findTasksByUser(Object[] res);
	/**
	 * 事务已办事务
	 * 
	 * @param res
	 * @return
	 */
	public  Page<ProcessEventTotal> findProcessedTasks(Object[] res);
	/**
	 * 启动自由流程
	 * @param res
	 * @return
	 */
	public String startSemiAutomaticWorkflow(Object[] res);
	/**
	 * 查看事务明细
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public  List<ProcessEventDetail> findTasksByUserProcessInstanceId(String processInstanceId);
	/**
	 * 事务处理
	 * @param res
	 * @return
	 */
	public  String complete(Object[] res);
	/**
	 * 事务处理
	 * @param res
	 * @return
	 * @author cg.jiang
	 */
	public  String completeBackSem(Object[] res);
	/**
	 * 启动任意流程
	 * @param res
	 * @return
	 */
	public String startAnyProcessWorkflow(Object[] res);
	/**
	 * 事务取消
	 * 
	 * @param res
	 * @return
	 */
	public  String cancelEvent(String eventId);
	/**
	 * 固定流程启动下个处理人有至少两人
	 * @param eventId
	 * @return
	 */
	public  UserUtil startWorkflowFix(Object[] res);
	/**
	 * 固定流程启动下个处理人有至少两人
	 * @param eventId
	 * @return
	 */
	public  UserUtil startProcessNexUser(Object[] res);
	/**
	 * 处理事务写事务表
	 * @param res
	 * @return
	 */
	public  String processCommit(Object[] res);
	/**
	 * 至少有两个处理人是调用
	 * @param eventId
	 * @return
	 */
	public  String processWorkflowFix(Object[] res);
	
	/**
	 * 发布流程 cy
	 * @param bytes  xml对象的字节流
	 * @param processDefinitionName 流程名称
	 * @return
	 */
	
	public String deployProcessDefinition(String  xmlString,String processDefinitionName);
	
	/**
	 * 处理最后节点事务，直接完成
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil completeEndTask(Object[] res);
	/**
	 * 取回事务
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil backProEvent(Object[] res);
	/**
	 * 处理最后节点事务，直接完成
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil selectSemUser(Object[] res);
	/**
	 * 处理最后节点事务，直接完成
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil selectAnyExecuteAction(Object[] res);
	
	public UserUtil addSemUser(Object[] res);
	
	public UserUtil setSemBackUser(Object[] res);
	
	/**
	 * 离职人员事务转移
	 * 
	 * @param res
	 * @return
	 */
	public  String transferEvent(Object[] res);
}
