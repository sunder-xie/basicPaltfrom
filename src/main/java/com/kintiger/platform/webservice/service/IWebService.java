package com.kintiger.platform.webservice.service;

import java.util.List;

import com.kintiger.platform.webservice.pojo.Page;
import com.kintiger.platform.webservice.pojo.ProcessEventDetail;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.webservice.pojo.UserUtil;

public interface IWebService {
	 
	/**
	 * ��������
	 * @param res
	 * @return
	 */
	public  String startWorkflow(Object[] res);
	
	/**
	 * ��ѯ��������
	 * 
	 * @param res
	 * @return
	 */
	public  Page<ProcessEventTotal> findTodoTasks(Object[] res);
	
	/**
	 * ��ѯ���е�����
	 * @param res
	 * @return
	 */
	public Page<ProcessEventTotal> findAllTasks(Object[] res);
	/**
	 * �����ѯ
	 * 
	 * @param res
	 * @return
	 */
	public  Page<ProcessEventTotal> findTasksByUser(Object[] res);
	/**
	 * �����Ѱ�����
	 * 
	 * @param res
	 * @return
	 */
	public  Page<ProcessEventTotal> findProcessedTasks(Object[] res);
	/**
	 * ������������
	 * @param res
	 * @return
	 */
	public String startSemiAutomaticWorkflow(Object[] res);
	/**
	 * �鿴������ϸ
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public  List<ProcessEventDetail> findTasksByUserProcessInstanceId(String processInstanceId);
	/**
	 * ������
	 * @param res
	 * @return
	 */
	public  String complete(Object[] res);
	/**
	 * ������
	 * @param res
	 * @return
	 * @author cg.jiang
	 */
	public  String completeBackSem(Object[] res);
	/**
	 * ������������
	 * @param res
	 * @return
	 */
	public String startAnyProcessWorkflow(Object[] res);
	/**
	 * ����ȡ��
	 * 
	 * @param res
	 * @return
	 */
	public  String cancelEvent(String eventId);
	/**
	 * �̶����������¸�����������������
	 * @param eventId
	 * @return
	 */
	public  UserUtil startWorkflowFix(Object[] res);
	/**
	 * �̶����������¸�����������������
	 * @param eventId
	 * @return
	 */
	public  UserUtil startProcessNexUser(Object[] res);
	/**
	 * ��������д�����
	 * @param res
	 * @return
	 */
	public  String processCommit(Object[] res);
	/**
	 * �����������������ǵ���
	 * @param eventId
	 * @return
	 */
	public  String processWorkflowFix(Object[] res);
	
	/**
	 * �������� cy
	 * @param bytes  xml������ֽ���
	 * @param processDefinitionName ��������
	 * @return
	 */
	
	public String deployProcessDefinition(String  xmlString,String processDefinitionName);
	
	/**
	 * �������ڵ�����ֱ�����
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil completeEndTask(Object[] res);
	/**
	 * ȡ������
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil backProEvent(Object[] res);
	/**
	 * �������ڵ�����ֱ�����
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil selectSemUser(Object[] res);
	/**
	 * �������ڵ�����ֱ�����
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil selectAnyExecuteAction(Object[] res);
	
	public UserUtil addSemUser(Object[] res);
	
	public UserUtil setSemBackUser(Object[] res);
	
	/**
	 * ��ְ��Ա����ת��
	 * 
	 * @param res
	 * @return
	 */
	public  String transferEvent(Object[] res);
}
