package com.kintiger.platform.webservice.service.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.endpoint.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kintiger.platform.webservice.pojo.AllUsers;
import com.kintiger.platform.webservice.pojo.Page;
import com.kintiger.platform.webservice.pojo.ProcessEventDetail;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.webservice.pojo.UserUtil;
import com.kintiger.platform.webservice.resps.JsonUtil;
import com.kintiger.platform.webservice.service.IWebService;

public class WebServiceImpl  implements IWebService{
	private static final Log logger = LogFactory
	.getLog(WebServiceImpl.class);
	private Client client;
	/**
	 * 启动流程
	 * @param res
	 * @return
	 */
	public  String startWorkflow(Object[] res) {
		String processInstanceId = "";
	 try {
		    if(client == null){ //初始化
		    	init();
		    }
			Object[] results = client.invoke("startWorkflow", res);
			processInstanceId = results[0].toString();
		} catch (Exception e) {
			return "";
		} 
//	 finally{
//			destroy();
//		}
       return processInstanceId;
	}
	
	/**
	 * 查询待办事务
	 * 
	 * @param res
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<ProcessEventTotal> findTodoTasks(Object[] res) {
		Page<ProcessEventTotal> page = new Page<ProcessEventTotal>();
		try {
			if(client == null){ //初始化
			    	init();
			}
			Object[] results = client.invoke("findTodoTasks", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", ProcessEventTotal.class);
			page = (Page<ProcessEventTotal>) JsonUtil.getDTO(results[0].toString(), Page.class, classMap);
		} catch (Exception e) {
			logger.error(e);
		}  
		return page;
	}
	

	/**
	 * 查询所有的事务
	 * 
	 * @param res
	 * @return
	 * @author cy 2013-12-09
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  Page<ProcessEventTotal> findAllTasks(Object[] res) {
		Page<ProcessEventTotal> page = new Page<ProcessEventTotal>();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("findAllTasks", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", ProcessEventTotal.class);
			page = (Page<ProcessEventTotal>) JsonUtil.getDTO(results[0].toString(), Page.class, classMap);	
		} catch (Exception e) {
			logger.error(e);
		} 
		return page;
	}
	/**
	 * 事务查询
	 * 
	 * @param res
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  Page<ProcessEventTotal> findTasksByUser(Object[] res) {
		Page<ProcessEventTotal> page = new Page<ProcessEventTotal>();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("findTasksByUser", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", ProcessEventTotal.class);
			page = (Page<ProcessEventTotal>) JsonUtil.getDTO(results[0].toString(), Page.class, classMap);	
		} catch (Exception e) {
			logger.error(e);
		} 
		return page;
	}
	
	/**
	 * 事务已办事务
	 * 
	 * @param res
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  Page<ProcessEventTotal> findProcessedTasks(Object[] res) {
		Page<ProcessEventTotal> page = new Page<ProcessEventTotal>();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("findProcessedTasks", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			
			classMap.put("result", ProcessEventTotal.class);
			page = (Page<ProcessEventTotal>) JsonUtil.getDTO(results[0].toString(), Page.class, classMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}  
		return page;
	}
	
	/**
	 * 启动流程
	 * @param res
	 * @return
	 */
	public  String startSemiAutomaticWorkflow(Object[] res) {
		String processInstanceId = "";
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("startSemiautomaticWorkflow", res);
			processInstanceId = results[0].toString();
		} catch (MalformedURLException e) {
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		} 
       return processInstanceId;
	}
	
	/**
	 * 查看事务明细
	 * 
	 * @param processInstanceId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List<ProcessEventDetail> findTasksByUserProcessInstanceId(String processInstanceId) {
		List<ProcessEventDetail> list = new ArrayList<ProcessEventDetail>();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("findTasksByUserProcessInstanceId", processInstanceId);
			list = JsonUtil.getDTOList(results[0].toString(), ProcessEventDetail.class);
		} catch (Exception e) {
			logger.error(e);
		}  
		return list;
	}
	
	
	/**
	 * 事务处理
	 * @param res
	 * @return
	 */
	public  String complete(Object[] res) {
		String result = "";
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("complete", res);
			result = results[0].toString();
		} catch (MalformedURLException e) {
		
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}   
       return result;
	}
	
	/**
	 * 事务处理
	 * @param res
	 * @return
	 */
	public  String completeBackSem(Object[] res) {
		String result = "";
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("completeSem", res);
			result = results[0].toString();
		} catch (MalformedURLException e) {
		
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}   
       return result;
	}
	/**
	 * 启动任意流程
	 * @param res
	 * @return
	 */
	public  String startAnyProcessWorkflow(Object[] res) {
		String processInstanceId = "";
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("startAnyWorkflow", res);
			processInstanceId = results[0].toString();
		} catch (Exception e) {
			logger.error(e);
		}  
       return processInstanceId;
	}
	/**
	 * 事务取消
	 * 
	 * @param res
	 * @return
	 */
	public  String cancelEvent(String eventId){
		String result = "";
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("cancelEvent", eventId);
			result = results[0].toString();
		} catch (Exception e) {
			logger.error(e);
		}  
		return result;
	}
	
	/**
	 * 根据主键查询事务信息
	 * 
	 * @param eventId
	 * @return
	 */
	public  ProcessEventTotal findEventTotalById(String eventId) {
		ProcessEventTotal eventTotal = new ProcessEventTotal();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("findEventTotalById", eventId);
			eventTotal = (ProcessEventTotal) JsonUtil.getDTO(results[0].toString(), ProcessEventTotal.class);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}  
		return eventTotal;
	}
	
	/**
	 * 固定流程启动下个处理人有至少两人
	 * @param eventId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  UserUtil startWorkflowFix(Object[] res){
		UserUtil userUtil = new UserUtil();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("startWorkflowFix", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", AllUsers.class);
			userUtil = (UserUtil) JsonUtil.getDTO(results[0].toString(), UserUtil.class, classMap);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}   
		return userUtil;
	}
	/**
	 * 至少有两个处理人是调用
	 * @param eventId
	 * @return
	 */
	public  String processWorkflowFix(Object[] res){
		String  processInstanceId = "";
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("processWorkflowFix", res);
			processInstanceId = results[0].toString();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}  
		return processInstanceId;
	}
	
	/**
	 * 固定流程启动下个处理人有至少两人
	 * @param eventId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  UserUtil startProcessNexUser(Object[] res){
		UserUtil userUtil = new UserUtil();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("completeProcess", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", AllUsers.class);
			userUtil = (UserUtil) JsonUtil.getDTO(results[0].toString(), UserUtil.class, classMap);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}   
		return userUtil;
	}
	
	/**
	 * 处理事务写事务表
	 * @param res
	 * @return
	 */
	public  String processCommit(Object[] res){
		String  result = "";
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("commitProcess", res);
			result = results[0].toString();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}  
		return result;
	}
	/***
	 * 手工注入
	 */
	protected void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-webservice.xml");
	    client = (Client) ctx.getBean("client");
	}
//	/**
//	 * destroy  client 
//	 * 置空client 
//	 * */
//	protected void destroy(){
//		client.destroy();
//		client=null;
//	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	/**
	 * 发布流程
	 * @param bytes  xml对象的字节流
	 * @param processDefinitionName 流程名称
	 * @return
	 */
	public String deployProcessDefinition(String xmlString,
			String processDefinitionName) {
		String  result = "";
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] res = new Object[2];;
			res[0]=xmlString;
			res[1]=processDefinitionName;
			Object[] results = client.invoke("deployProcessDefinition",res);
			result = (String)results[0];
			return result;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}  
		return result;
	}
	
	/**
	 * 处理最后节点事务，直接完成
	 *  RTX增加删除在这修改--2015 02 03 
	 * @param res
	 * @return
	 */
	
	@SuppressWarnings("rawtypes")
	public  UserUtil completeEndTask(Object[] res){
		UserUtil userUtil = new UserUtil();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("completeEndTask", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", AllUsers.class);
			userUtil = (UserUtil) JsonUtil.getDTO(results[0].toString(), UserUtil.class, classMap);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}   
		return userUtil;
	}
	/**
	 * 取回事务
	 * 
	 * @param res
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  UserUtil backProEvent(Object[] res){
		UserUtil userUtil = new UserUtil();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("backEvent", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", AllUsers.class);
			userUtil = (UserUtil) JsonUtil.getDTO(results[0].toString(), UserUtil.class, classMap);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}   
		return userUtil;
	}
	/**
	 * 处理最后节点事务，直接完成
	 * 
	 * @param res
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  UserUtil selectSemUser(Object[] res){
		UserUtil userUtil = new UserUtil();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("selectSemUser", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", AllUsers.class);
			userUtil = (UserUtil) JsonUtil.getDTO(results[0].toString(), UserUtil.class, classMap);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}   
		return userUtil;
	}
	
	/**
	 * 处理最后节点事务，直接完成
	 * 
	 * @param res
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  UserUtil selectAnyExecuteAction(Object[] res){
		UserUtil userUtil = new UserUtil();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("selectAnyExecuteAction", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", AllUsers.class);
			userUtil = (UserUtil) JsonUtil.getDTO(results[0].toString(), UserUtil.class, classMap);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}   
		return userUtil;
	}

/**
 * @author cy
 * @param res
 * 半自动流程  审批中增加审批人	 
 */
	public UserUtil addSemUser(Object[] res) {
		UserUtil userUtil = new UserUtil();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("addSemUser", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", AllUsers.class);
			userUtil = (UserUtil) JsonUtil.getDTO(results[0].toString(), UserUtil.class, classMap);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}   
		return userUtil;
	}


/**
 * @author cy
 * @param res
 * 半自动流程  回退	 
 */
	public UserUtil setSemBackUser(Object[] res) {
		UserUtil userUtil = new UserUtil();
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("setSemBackUser", res);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("result", AllUsers.class);
			userUtil = (UserUtil) JsonUtil.getDTO(results[0].toString(), UserUtil.class, classMap);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}   
		return userUtil;
	}
	
	public String transferEvent(Object[] res) {
		String  result = "";
		try {
			if(client == null){ //初始化
		    	init();
			}
			Object[] results = client.invoke("changeCurUser", res);
			result = results[0].toString();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}  
		return result;
	}
}


