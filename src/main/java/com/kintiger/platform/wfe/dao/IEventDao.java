package com.kintiger.platform.wfe.dao;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.file.pojo.BudgetFileTmp;
import com.kintiger.platform.framework.timer.pojo.WxSendCc;
import com.kintiger.platform.framework.timer.pojo.WxSendCuruser;
import com.kintiger.platform.framework.timer.pojo.WxSendInitator;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.pojo.Cc;
import com.kintiger.platform.wfe.pojo.Leave;
import com.kintiger.platform.wfe.pojo.LinkMan;
import com.kintiger.platform.wfe.pojo.OrderCheck;
import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.pojo.ProEventTotal;
import com.kintiger.platform.wfe.pojo.TripWay;

public interface IEventDao {
	
	/**
	 * 获取事务指定处理人对应的流程Detail
	 * @param eventId
	 * @param userId
	 * @return
	 */
	public String getProEventDetail(String eventId, String userId);

	/**
	 * 获取Xml临时文件名
	 * @return
	 */
	public Long getEvent_XmlTempId();
	
	public void updateLeave(Leave leave);
	
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
	 * 根据事务号查询明细
	 * 
	 * @param eventDetail
	 * @return
	 */
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail);
	
	/**
	 * 创建附件
	 * 
	 * @param budgetFileTmp
	 * @return
	 */
	public Long createBudgetFileTmp(BudgetFileTmp budgetFileTmp);
	
	/**
	 * 获取常用联系人数
	 * 
	 * @param linkMan
	 * @return
	 */
	public int getLinkManCount(LinkMan linkMan);
	
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
	public Long createLinkMan(LinkMan linkMan);
	
	/**
	 * 更新常用联系人
	 * 
	 * @param linkMan
	 */
	public int updateLinkMan(LinkMan linkMan);
	
	public List<ProEventDetail> getStationListByEventId(Long eventId);
	
	public List<ProEventDetail> getBackListByEventId(Long eventId);
	
	public void updateBuglogFile(Long eventId, String state);
	
	public void updateEventFlag(Long eventId, String backUserIdList);
	
	public int getTripWayCount(TripWay tripWay);
	
	public List<TripWay> getTripWayList(TripWay tripWay);
	/**
	 * 根据事务模板查询事务总数
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal);
	/**
	 * 根据事务号查询事务
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal);
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
	 * 设置被拒绝事务变成已阅读
	 */
	public int setIsRead(ProcessEventTotal eventTotal);
	
	public int getCountRoleByUser(String userId, String  $missing$);
	
	
	/**
	 * 获取未处理人员的信息
	 */
	public List<AllUsers> getUntreatedUserListByEventId(String eventId);
	
	int getModelCount(ProcessEventTotal eventTotal);
	
	List<ProcessEventTotal> getModelList(ProcessEventTotal eventTotal);
	
	public List<OrderCheck> getStationIdByUserIdForOrderCheck(String userId);
	
	public int searchOrderCheckListCount(OrderCheck orderCheck);
	/**
	 * 根据事务ID得到节点附件
	 * @param  @param eventDetailId
	 * @param  @return    设定文件
	 */
	public List<ProEventDetail> getEventFileListsByEventId(String eventId);

	public int getCcCount(Cc cc);
	/**
	 * 查询可签收事务的id
	 * @return
	 * @author sl.zhu
	 */
	public List<ProcessEventTotal> getRoleEventsByUserId(String userId);

	public ProcessEventTotal getEventListById(String eventIds);
	List<AllUsers> searchAllUsersByRtxCode(AllUsers rtxuser);
	public List<Cc> getCcListBycc(Cc cc);
	/**
	 * 获取未完成会签事务号list
	 * @param  @return    设定文件
	 */
	public List<ProcessEventTotal> getCountersignEvents(Object[]object);
	/**
	 * 获取已完成会签事务号list
	 * @param  @return    设定文件
	 */
	public List<ProcessEventTotal> getCompleteCountersignEvents(Object[]object);
	
	/**
	 * 判断该事务号是否有未完成的会签
	 * @author sl.zhu
	 */
	public String completeSign(String eventId);
	public	List<WxSendCuruser> getWxSendCuruserList();
	public void updateWxCuruserStatus(WxSendCuruser wxSendCuruser);
	public	List<WxSendCc> getWxSendCcList();
	public void updateWxCcStatus(WxSendCc wxSendCc);
	public	List<WxSendInitator> getWxSendInitatorList();
	public void updateWxInitatorStatus(WxSendInitator wxSendInitator);

	public String checkProcessUserId(String userId, String eventId);
	public List<ProEventDetail> searchWorkflowEventdetailCur_sta(ProEventDetail proEventDetail);
	public int checkFix_signsampleDate(ProEventDetail proEventDetail);

	public String getKunnrId(String eventId);

	public void updateKunnrStatus(String kunnrId);

	public void createGoalSalesChange(String eventId);

	public void createGoalSalesChangeDetail(String eventId, String kunnrId);

	public void createDealerAdjustMent(String eventId);

	public void createDealerAdjustDetail(String eventId, String kunnrId);

	public void deleteKunnrTarget(String eventId, String kunnrId);

	public void createGoalFxChange(String eventId);

	public void createGoalFxChangeDetail(String eventId, String kunnrId);

	public void deleteFxTarget(String eventId, String kunnrId);
	
	
}
