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
	 * ��ȡ����ָ�������˶�Ӧ������Detail
	 * @param eventId
	 * @param userId
	 * @return
	 */
	public String getProEventDetail(String eventId, String userId);

	/**
	 * ��ȡXml��ʱ�ļ���
	 * @return
	 */
	public Long getEvent_XmlTempId();
	
	public void updateLeave(Leave leave);
	
	public String  getWfeActionId(String eventId);
	/**
	 * ��������Ų�ѯ�����ܱ�
	 */
	public ProEventTotal getEventTotalById(Long eventId);
	/**
	 * ��������Ų�ѯ�����ܱ�
	 */
	public ProEventTotal getEventTotalById(Long eventId,Long ccId);
	/**
	 * ��������Ų�ѯ��ϸ
	 * 
	 * @param eventDetail
	 * @return
	 */
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail);
	
	/**
	 * ��������
	 * 
	 * @param budgetFileTmp
	 * @return
	 */
	public Long createBudgetFileTmp(BudgetFileTmp budgetFileTmp);
	
	/**
	 * ��ȡ������ϵ����
	 * 
	 * @param linkMan
	 * @return
	 */
	public int getLinkManCount(LinkMan linkMan);
	
	/**
	 * ��ѯ������ϵ��
	 * 
	 * @param linkMan
	 * @return
	 */
	public List<LinkMan> getLinkManList(LinkMan linkMan);
	
	/**
	 * ����������ϵ��
	 * 
	 * @param linkMan
	 * @return
	 */
	public Long createLinkMan(LinkMan linkMan);
	
	/**
	 * ���³�����ϵ��
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
	 * ��������ģ���ѯ��������
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal);
	/**
	 * ��������Ų�ѯ����
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal);
	/***
	 * ����������
	 * @param cc
	 * @return
	 */
	public StringResult createCc(List<Cc> cc);
	
	/**
	 * �޸ĳ�����Ϣ
	 * @param businessTripApplyList
	 * @return
	 */
	public int  updateCc(Cc cc);
	
	/**
	 * ��ȡ������Ϣ�б�����
	 * @param cc
	 * @return
	 */
	public int  getCcListCount(Cc cc);
	/***
	 * ��ȡ������Ϣ�б�
	 * @param cc
	 * @return
	 */
	public List<Cc> getCcList(Cc cc);
	/***
	 * ��ȡ������Ϣ�б�
	 * @param cc
	 * @return
	 */
	public List<Cc> getCcListByEventId(Cc cc);
	/**
	 * ��ȡ�ܾ��б�
	 * @param eventTotal
	 * @return
	 */
	public int getCancelEventCount(ProcessEventTotal eventTotal);
	
	/**
	 * ��ȡ�ܾ���Ϣ�б�
	 * @param eventTotal
	 * @return
	 */
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal);
	
	/**
	 * �鿴��λ��Ϣ
	 * 
	 * @param userId
	 * @return
	 */
	public String getStationId(String userId);
	/**
	 * ���ñ��ܾ����������Ķ�
	 */
	public int setIsRead(ProcessEventTotal eventTotal);
	
	public int getCountRoleByUser(String userId, String  $missing$);
	
	
	/**
	 * ��ȡδ������Ա����Ϣ
	 */
	public List<AllUsers> getUntreatedUserListByEventId(String eventId);
	
	int getModelCount(ProcessEventTotal eventTotal);
	
	List<ProcessEventTotal> getModelList(ProcessEventTotal eventTotal);
	
	public List<OrderCheck> getStationIdByUserIdForOrderCheck(String userId);
	
	public int searchOrderCheckListCount(OrderCheck orderCheck);
	/**
	 * ��������ID�õ��ڵ㸽��
	 * @param  @param eventDetailId
	 * @param  @return    �趨�ļ�
	 */
	public List<ProEventDetail> getEventFileListsByEventId(String eventId);

	public int getCcCount(Cc cc);
	/**
	 * ��ѯ��ǩ�������id
	 * @return
	 * @author sl.zhu
	 */
	public List<ProcessEventTotal> getRoleEventsByUserId(String userId);

	public ProcessEventTotal getEventListById(String eventIds);
	List<AllUsers> searchAllUsersByRtxCode(AllUsers rtxuser);
	public List<Cc> getCcListBycc(Cc cc);
	/**
	 * ��ȡδ��ɻ�ǩ�����list
	 * @param  @return    �趨�ļ�
	 */
	public List<ProcessEventTotal> getCountersignEvents(Object[]object);
	/**
	 * ��ȡ����ɻ�ǩ�����list
	 * @param  @return    �趨�ļ�
	 */
	public List<ProcessEventTotal> getCompleteCountersignEvents(Object[]object);
	
	/**
	 * �жϸ�������Ƿ���δ��ɵĻ�ǩ
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
