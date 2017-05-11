package com.kintiger.platform.wfe.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.file.pojo.BudgetFileTmp;
import com.kintiger.platform.framework.timer.pojo.WxSendCc;
import com.kintiger.platform.framework.timer.pojo.WxSendCuruser;
import com.kintiger.platform.framework.timer.pojo.WxSendInitator;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.wfe.dao.IEventDao;
import com.kintiger.platform.wfe.pojo.Cc;
import com.kintiger.platform.wfe.pojo.Leave;
import com.kintiger.platform.wfe.pojo.LinkMan;
import com.kintiger.platform.wfe.pojo.OrderCheck;
import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.pojo.ProEventTotal;
import com.kintiger.platform.wfe.pojo.TripWay;

@SuppressWarnings("rawtypes")
public class EventDaoImpl extends BaseDaoImpl implements IEventDao {

	/**
	 * 获取事务指定当前处理人对应的流程Detail(第一条,有可能多条)
	 * @param eventId
	 * @param userId
	 * @return
	 */
	public String getProEventDetail(String eventId, String userId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("event_id", Long.parseLong(eventId.trim()));
		params.put("cur_user_id", userId);
		return (String)getSqlMapClientTemplate().queryForObject("wfe.getDeatilByCurUserAndEventID", params);
	}
	
	public Long getEvent_XmlTempId() {
		return (Long)getSqlMapClientTemplate().queryForObject("wfe.getEvent_XmlTempID");
	}

	public void updateLeave(Leave leave) {
		getSqlMapClientTemplate().update("wfe.updateEntity", leave);
	}

	public String getWfeActionId(String eventId) {
		return (String) getSqlMapClientTemplate().queryForObject("wfe.getWfeActionId",eventId);
	}
	/**
	 * 获取流程明细
	 */
	public ProEventTotal getEventTotalById(Long eventId) {
		return (ProEventTotal) getSqlMapClientTemplate().queryForObject(
				"wfe.getEventTotalById", eventId);
	}
	/**
	 * 根据事务号查询事务总表
	 */
	public ProEventTotal getEventTotalById(Long eventId,Long ccId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("event_id", eventId);
		params.put("cc_id", ccId);
		
		return (ProEventTotal) getSqlMapClientTemplate().queryForObject(
				"wfe.getEventTotalByIdAndCcId", params);
	}
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail) {
		return getSqlMapClientTemplate().queryForList(
				"wfe.getEventDetailList", eventDetail);
	}
	
	public Long createBudgetFileTmp(BudgetFileTmp budgetFileTmp) {
		return (Long) getSqlMapClientTemplate().insert(
				"wfe.createBudgetFileTmp", budgetFileTmp);
	}

	/** 联系人个数  */
	public int getLinkManCount(LinkMan linkMan) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"wfe.getLinkManCount", linkMan);
	}
	
	/** 选择常用联系人 */
	@SuppressWarnings("unchecked")
	public List<LinkMan> getLinkManList(LinkMan linkMan) {
		return getSqlMapClientTemplate().queryForList("wfe.getLinkManList",
				linkMan);
	}
	
	/** 新增联系人  */
	public Long createLinkMan(LinkMan linkMan) {
		return (Long) getSqlMapClientTemplate().insert("wfe.createLinkMan",
				linkMan);
	}
	
	/** 修改联系人 */
	public int updateLinkMan(LinkMan linkMan) {
		return getSqlMapClientTemplate().update("wfe.updateLinkMan", linkMan);
	}

	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getStationListByEventId(Long eventId) {
		return getSqlMapClientTemplate().queryForList("wfe.getStationListByEventId", eventId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getBackListByEventId(Long eventId) {
		return getSqlMapClientTemplate().queryForList("wfe.getBackListByEventId", eventId);
	}
	
	public void updateBuglogFile(Long eventId, String state){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eventId", eventId);
		map.put("state", state);
		getSqlMapClientTemplate().update("wfe.updateBuglogFile", map);
	}
	
	public void updateEventFlag(Long eventId, String backUserIdList){
		String[] list=backUserIdList.split(",");
		for(String userId : list){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eventId", eventId);
		map.put("userId", userId);
		getSqlMapClientTemplate().update("wfe.updateEventFlag", map);
		}
	}
	public int getTripWayCount(TripWay tripWay) {
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getTripWayCount", tripWay);
	};
	
	@SuppressWarnings("unchecked")
	public List<TripWay> getTripWayList(TripWay tripWay) {
		return getSqlMapClientTemplate().queryForList("wfe.getTripWayList", tripWay);
	};
	/**
	 * 根据事务模板查询事务总数
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal){
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getTripApplyListCount", proEventTotal);
	}
	/**
	 * 根据事务号查询事务列表
	 */
	@SuppressWarnings("unchecked")
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal){
		return getSqlMapClientTemplate().queryForList("wfe.getTripApplyList", proEventTotal);
	}
	
	/***
	 * 创建抄送人
	 * @param cc
	 * @return
	 */
	public StringResult createCc(final List<Cc> ccList){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)throws SQLException {
                executor.startBatch();
                for(Cc cc : ccList) {
                	executor.insert("wfe.createCc", cc);
                }
                executor.executeBatch();
                StringResult stringResult = new StringResult();
                stringResult.setCode("success");
                return stringResult;
            }
        });
		return null;
	}
	
	/**
	 * 修改抄送信息
	 * @param businessTripApplyList
	 * @return
	 */
	public int  updateCc(Cc cc){
		return getSqlMapClientTemplate().update("wfe.updateCc", cc);
	}
	
	/**
	 * 获取抄送信息列表数量
	 * @param cc
	 * @return
	 */
	public int  getCcListCount(Cc cc){
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getCcListCount", cc);
	}
	/***
	 * 获取抄送信息列表
	 * @param cc
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Cc> getCcList(Cc cc){
		return getSqlMapClientTemplate().queryForList("wfe.getCcList", cc);
	}
	/***
	 * 获取抄送信息列表
	 * @param cc
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Cc> getCcListByEventId(Cc cc){
		return getSqlMapClientTemplate().queryForList("wfe.getCcListByEventId", cc);

	}
	/**
	 * 获取拒绝列表
	 * @param eventTotal
	 * @return
	 */
	public int getCancelEventCount(ProcessEventTotal eventTotal){
		return (Integer)getSqlMapClientTemplate().queryForObject("wfe.getCancelEventCount", eventTotal);
	}
	
	/**
	 * 获取拒绝信息列表
	 * @param eventTotal
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal){
		return getSqlMapClientTemplate().queryForList("wfe.getCancelEventList", eventTotal);
	}
	
	public String getStationId(String userId) {
		return (String)getSqlMapClientTemplate().queryForObject("wfe.getStationId", userId);
	}


	public int setIsRead(ProcessEventTotal eventTotal) {
		return getSqlMapClientTemplate().update("wfe.updateWorkflowtotal", eventTotal);
	}

	public int getCountRoleByUser(String userId, String  roleid) {
		 Map map = new HashMap<String , String >();
		 map.put("userId", userId);
		 map.put("roleId", roleid);
	    return (Integer)getSqlMapClientTemplate().queryForObject("wfe.getCountRoleByUser", map);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> getUntreatedUserListByEventId(String eventId) {
		return  getSqlMapClientTemplate().queryForList("wfe.getUntreatedUserByEventId", eventId);
	}
	
	public int getModelCount(ProcessEventTotal eventTotal) {
		return (Integer)getSqlMapClientTemplate().queryForObject("wfe.getModelCount", eventTotal);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcessEventTotal> getModelList(ProcessEventTotal eventTotal) {
		return getSqlMapClientTemplate().queryForList("wfe.getModelList", eventTotal);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderCheck> getStationIdByUserIdForOrderCheck(String userId){
		return (List<OrderCheck>)getSqlMapClientTemplate().queryForList("wfe.getStationIdByUserIdForOrderCheck", userId);
	}
	
	public int searchOrderCheckListCount(OrderCheck orderCheck){
		return (Integer)getSqlMapClientTemplate().queryForObject("wfe.searchOrderCheckListCount", orderCheck);
	}

	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getEventFileListsByEventId(String eventId) {
		return (List<ProEventDetail>)getSqlMapClientTemplate().queryForList("wfe.getEventFileListsByEventId",eventId);
		
	}
	
	public int getCcCount(Cc c) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"wfe.getCcCount", c);
	}

	@SuppressWarnings("unchecked")
	public List<ProcessEventTotal> getRoleEventsByUserId(String userId) {
		return (List<ProcessEventTotal>)getSqlMapClientTemplate().queryForList("wfe.getRoleEventsByUserId",userId);
	}

	@SuppressWarnings("unchecked")
	public ProcessEventTotal getEventListById(String eventId) {
		return (ProcessEventTotal) getSqlMapClientTemplate().queryForObject("wfe.getEventListById", eventId);
	}
	public List<AllUsers> searchAllUsersByRtxCode(AllUsers rtxuser) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("wfe.searchAllUsersByRtxCode", rtxuser);
	}
	
	public List<Cc> getCcListBycc(Cc cc) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("wfe.getCcListBycc", cc);
	}

	public List<ProcessEventTotal> getCountersignEvents(Object[] res) {
		 Map map = new HashMap<String , String >();
		 map.put("userId", res[0]);
		 map.put("eventId", res[1]);
		 map.put("initator", res[2]);
		 map.put("eventTitle", res[3]);
		 map.put("modelName", res[4]);
		 map.put("status", res[5]);
		 map.put("orgName", res[6]);
		return getSqlMapClientTemplate().queryForList("wfe.getCountersignEventsByUserId", map);
	}
	public List<ProcessEventTotal> getCompleteCountersignEvents(Object[] res) {
		 Map map = new HashMap<String , String >();
		 map.put("userId", res[0]);
		 map.put("eventId", res[1]);
		 map.put("initator", res[2]);
		 map.put("eventTitle", res[3]);
		 map.put("modelName", res[4]);
		 map.put("status", res[5]);
		 map.put("orgName", res[6]);
		return getSqlMapClientTemplate().queryForList("wfe.getCompleteCountersignEvents", map);
	}

	
	public String completeSign(String eventId) {
		return (String)getSqlMapClientTemplate().queryForObject("wfe.completeSign", eventId);
	}


	
	public List<WxSendCuruser> getWxSendCuruserList() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("wfe.getWxSendCuruserList");
	}

	
	public void updateWxCuruserStatus(WxSendCuruser wxSendCuruser) {
		getSqlMapClientTemplate().update("wfe.updateWxCuruserStatus", wxSendCuruser);
		
	}
	
	public List<WxSendCc> getWxSendCcList() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("wfe.getWxSendCcList");
	}

	
	public void updateWxCcStatus(WxSendCc wxSendCc){
		getSqlMapClientTemplate().update("wfe.updateWxCcStatus", wxSendCc);
		
	}

	
	public List<WxSendInitator> getWxSendInitatorList() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("wfe.getWxSendInitatorList");
	}

	
	public void updateWxInitatorStatus(WxSendInitator wxSendInitator) {
		getSqlMapClientTemplate().update("wfe.updateWxInitatorStatus", wxSendInitator);
	}

	
	public String checkProcessUserId(String userId, String eventId) {
		 Map map = new HashMap<String , String >();
		 map.put("userId", userId);
		 map.put("eventId", eventId);
		return (String) getSqlMapClientTemplate().queryForObject("wfe.checkProcessUserId", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProEventDetail> searchWorkflowEventdetailCur_sta(
			ProEventDetail proEventDetail) {
		// TODO Auto-generated method stub
		return (List<ProEventDetail>)getSqlMapClientTemplate().queryForList("wfe.searchWorkflowEventdetailCur_sta", proEventDetail);
	}
	@SuppressWarnings("unchecked")
	@Override
	public int checkFix_signsampleDate(
			ProEventDetail proEventDetail) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.checkFix_signsampleDate", proEventDetail);
	}

	@Override
	public String getKunnrId(String eventId) {
		return (String)getSqlMapClientTemplate().queryForObject("wfe.getKunnrId", eventId);
	}

	public void updateKunnrStatus(String kunnrId) {
		getSqlMapClientTemplate().update("wfe.updateKunnrStatus",kunnrId);
	}

	public void createGoalSalesChange(String eventId) {
		  getSqlMapClientTemplate().insert("wfe.createGoalSalesChange", eventId);
	}

	public void createGoalSalesChangeDetail(String eventId,String kunnrId) {
		Map map = new HashMap<String , String >();
		map.put("eventId", eventId);
		map.put("kunnrId", kunnrId);
		getSqlMapClientTemplate().insert("wfe.createGoalSalesChangeDetail", map);
	}

	public void createDealerAdjustMent(String eventId) {
		 getSqlMapClientTemplate().insert("wfe.createDealerAdjustMent", eventId);
		
	}

	public void createDealerAdjustDetail(String eventId, String kunnrId) {
		Map map = new HashMap<String , String >();
		map.put("eventId", eventId);
		map.put("kunnrId", kunnrId);
		getSqlMapClientTemplate().insert("wfe.createDealerAdjustDetail", map);
		
	}

	public void deleteKunnrTarget(String eventId,String kunnrId) {
		Map map = new HashMap<String , String >();
		map.put("eventId", eventId);
		map.put("kunnrId", kunnrId);
		 getSqlMapClientTemplate().update("wfe.deleteKunnrTarget", map);
	}

	public void createGoalFxChange(String eventId) {
		  getSqlMapClientTemplate().insert("wfe.createGoalFxChange", eventId);
	}

	public void createGoalFxChangeDetail(String eventId,String kunnrId) {
		Map map = new HashMap<String , String >();
		map.put("eventId", eventId);
		map.put("kunnrId", kunnrId);
		getSqlMapClientTemplate().insert("wfe.createGoalFxChangeDetail", map);
	}

	public void deleteFxTarget(String eventId,String kunnrId) {
		Map map = new HashMap<String , String >();
		map.put("eventId", eventId);
		map.put("kunnrId", kunnrId);
		 getSqlMapClientTemplate().update("wfe.deleteFxTarget", map);
	}
}
