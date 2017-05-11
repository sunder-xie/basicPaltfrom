package com.kintiger.platform.wfe.service;

import java.io.File;
import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.timer.pojo.WxSendCc;
import com.kintiger.platform.framework.timer.pojo.WxSendCuruser;
import com.kintiger.platform.framework.timer.pojo.WxSendInitator;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.pojo.BusinessTripApply;
import com.kintiger.platform.wfe.pojo.Cc;
import com.kintiger.platform.wfe.pojo.LinkMan;
import com.kintiger.platform.wfe.pojo.OrderCheck;
import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.pojo.ProEventTotal;
import com.kintiger.platform.wfe.pojo.TripWay;

public interface IEventService {
	public static final String SUCCESS = "success";

	public static final String ERROR = "error";
	
	public static final String NEXT = "next";

	public static final String ERROR_MESSAGE = "操作失败！";

	public static final String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	public static final String ERROR_NULL_MESSAGE = "操作失败，单据已不存在！";
	
	public Long getEvent_XmlTempId();

	public StringResult createEvent(String key, String userId, String title,String nextUser,
			String eventContent, String userList, String memo);
	
	public String getProEventDetail(String eventId, String userId);
	
	public String  getWfeActionId(String eventId);
	/**
	 * 根据事务号查询事务总表
	 */
	public ProEventTotal getEventTotalById(Long eventId);
	/**
	 * 根据事务号查询事务总表
	 */
	public ProEventTotal getEventTotalById(Long eventId,Long ccId);
	/**
	 * 根据事务模板查询事务总数
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal);
	/**
	 * 根据事务号查询事务
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal);
	/**
	 * 获取事务明细列表并且排序
	 * 
	 * @param eventId
	 * @return
	 */
	public List<ProEventDetail> getEventDetailListAndSort(Long eventId);
	
	/**
	 * 处理附件上传
	 * 
	 * @param uploadFiles
	 * @param uploadFileNames
	 * @param eventDetailId
	 * @param timestamp
	 * @return
	 */
	public boolean processAttachments(File[] uploadFiles,
			String[] uploadFileNames, Long eventDetailId, String timestamp,String key);
	
	/**
	 * 查询常用联系人
	 * 
	 * @param linkMan
	 * @return
	 */
	public List<LinkMan> getLinkManList(LinkMan linkMan);
	
	/**
	 * 创建常用联系人
	 * 
	 * @param linkMan
	 * @return
	 */
	public BooleanResult saveOrUpdateLinkMan(LinkMan linkMan);
	
	public List<ProEventDetail> getStationListByEventId(Long eventId);
	
	public List<ProEventDetail> getBackListByEventId(Long eventId);
	
	public void updateBuglogFile(Long eventId, String state);
	
	public void updateEventFlag(Long eventId, String backUserIdList);
	
	public int getTripWayCount(TripWay tripWay);
	
	public List<TripWay> getTripWayList(TripWay tripWay);
	
	public File exportBusinessTripApplyList(List<BusinessTripApply> businessTripApplyList);
	
	/***
	 * 创建抄送人
	 * @param cc
	 * @return
	 */
	public StringResult createCc(List<Cc> cc);
	
	/**
	 * 修改抄送信息
	 * @param businessTripApplyList
	 * @return
	 */
	public int  updateCc(Cc cc);
	
	/**
	 * 获取抄送信息列表数量
	 * @param cc
	 * @return
	 */
	public int  getCcListCount(Cc cc);
	/***
	 * 获取抄送信息列表
	 * @param cc
	 * @return
	 */
	public List<Cc> getCcList(Cc cc);
	/***
	 * 获取抄送信息列表
	 * @param cc
	 * @return
	 */
	public List<Cc> getCcListByEventId(Cc cc);
	/**
	 * 获取拒绝列表
	 * @param eventTotal
	 * @return
	 */
	public int getCancelEventCount(ProcessEventTotal eventTotal);
	
	/**
	 * 获取拒绝信息列表
	 * @param eventTotal
	 * @return
	 */
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal);
	
	/**
	 * 查看岗位信息
	 * 
	 * @param userId
	 * @return
	 */
	public String getStationId(String userId);
	/**
	 * 更新被拒绝的事务变成已阅读
	 */
	public int setIsRead(ProcessEventTotal eventTotal);
	/**
	 * 获取用户 是否拥有某个角色
	 * @param userId
	 * @param string
	 */
	public int getCountRoleByUser(String userId, String string);
	
	/**
	 * 获取未处理人员的信息
	 */
	public List<ProEventDetail> getUntreatedUserListByEventId(String eventId);
	
	int getModelCount(ProcessEventTotal eventTotal);
	
	List<ProcessEventTotal> getModelList(ProcessEventTotal eventTotal);
	
	public List<OrderCheck> getStationIdByUserIdForOrderCheck(String userId);
	
	/**
	 * 查询对账单反馈
	 * 
	 * @param
	 * @return
	 */
	public int searchOrderCheckListCount(OrderCheck orderCheck);
	/**
	 * 根据事务ID得到节点附件
	 * @param  @param eventDetailId
	 * @param  @return    设定文件
	 */
	public List<ProEventDetail> getEventFileListsByEventId(String eventId);
	
	/**
	 * 根据事务/明细Id及人员Id查询是否已抄送
	 * 
	 * @return
	 * @author cg.jiang
	 */
	public int getCcCount(Cc c);
	/**
	 * userId查询可签收事务及会签事务
	 * @return
	 * @author sl.zhu
	 */
	public List<ProcessEventTotal> getRoleEvents(Object[] object);
	List<AllUsers> searchAllUsersByRtxCode(AllUsers rtxuser);

	public List<Cc> getCcListBycc(Cc cc);
	/**
	 * 根据条件查询已完成会签事务list
	 * @return
	 * @author sl.zhu
	 */
	public List<ProcessEventTotal> getCompleteCountersignEvents(Object[] object);
	/**
	 * 判断是否有未完成的会签
	 * @author sl.zhu
	 */
	public String completeSign(String eventId);
	
	public	List<WxSendCuruser> getWxSendCuruserList();
	public void updateWxCuruserStatus(WxSendCuruser wxSendCuruser);
	public	List<WxSendCc> getWxSendCcList();
	public void updateWxCcStatus(WxSendCc wxSendCc);
	public	List<WxSendInitator> getWxSendInitatorList();
	public void updateWxInitatorStatus(WxSendInitator wxSendInitator);
	/**
	 * 检查登录用户是否是当前处理人
	 * @param userId  登录用户id
	 * @param eventId  事务号
	 * @return
	 */
	public String checkProcessUserId(String userId, String eventId);
	/**
	 * 签样流程检查登录用户当前所处的流程节点位置
	 * @param userId  登录用户id
	 * @param eventId  事务号
	 * @return
	 */
	public String searchWorkflowEventdetailCur_sta(ProEventDetail proEventDetail);
	public String checkFix_signsampleDate(ProEventDetail proEventDetail);
	/**
	 * sl.zhu
	 * 俞总节点同意后处理经销商数据
	 */
	public void kunnrProcess(String eventId);
}
