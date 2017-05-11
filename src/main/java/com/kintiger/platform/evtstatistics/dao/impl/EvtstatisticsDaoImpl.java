package com.kintiger.platform.evtstatistics.dao.impl;

import java.util.List;

import org.apache.poi.xwpf.converter.core.utils.StringUtils;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.evtstatistics.dao.EvtstatisticsDao;
import com.kintiger.platform.evtstatistics.pojo.Evtstatistics;

@SuppressWarnings("rawtypes")
public class EvtstatisticsDaoImpl extends BaseDaoImpl implements EvtstatisticsDao{

	@Override
	public Integer searchOverTimeEvtDtlListCount(Evtstatistics evtstatistics) {
		return (Integer) getSqlMapClientTemplate().queryForObject("evtstatistics.searchOverTimeEvtDtlListCount", evtstatistics);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evtstatistics> searchOverTimeEvtDtlList(Evtstatistics evtstatistics) {
		return (List<Evtstatistics>) getSqlMapClientTemplate().queryForList("evtstatistics.searchOverTimeEvtDtlList", evtstatistics);
	}

	@Override
	public int searchEventModelListCount(Evtstatistics evtstatistics) {
		return (Integer) getSqlMapClientTemplate().queryForObject("evtstatistics.searchEventModelListCount", evtstatistics);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evtstatistics> searchEventModelList(Evtstatistics evtstatistics) {
		return (List<Evtstatistics>) getSqlMapClientTemplate().queryForList("evtstatistics.searchEventModelList", evtstatistics);
	}

	@Override
	public int createEventModel(Evtstatistics evtstatistics) {
		getSqlMapClientTemplate().insert("evtstatistics.createEventModel", evtstatistics);
		return 1;
	}

	@Override
	public int updateEventModel(Evtstatistics evtstatistics) {
		return getSqlMapClientTemplate().update("evtstatistics.updateEventModel", evtstatistics);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evtstatistics> searchEventModelForCsv(
			Evtstatistics evtstatistics) {
		return (List<Evtstatistics>) getSqlMapClientTemplate().queryForList("evtstatistics.searchEventModelForCsv", evtstatistics);
	}

	@Override
	public int searchOverTimeEvtListCount(Evtstatistics evtstatistics) {
		return (Integer) getSqlMapClientTemplate().queryForObject("evtstatistics.searchOverTimeEvtListCount", evtstatistics);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evtstatistics> searchOverTimeEvtList(Evtstatistics evtstatistics) {
		return (List<Evtstatistics>) getSqlMapClientTemplate().queryForList("evtstatistics.searchOverTimeEvtList", evtstatistics);
	}

	@Override
	public int searchEventListCount(Evtstatistics evtstatistics) {
		return (Integer) getSqlMapClientTemplate().queryForObject("evtstatistics.searchEventListCount", evtstatistics);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evtstatistics> searchEventList(Evtstatistics evtstatistics) {
		return (List<Evtstatistics>) getSqlMapClientTemplate().queryForList("evtstatistics.searchEventList", evtstatistics);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evtstatistics> searchEventModelHrRoles(
			Evtstatistics evtstatistics) {
		return (List<Evtstatistics>) getSqlMapClientTemplate().queryForList("evtstatistics.searchEventModelHrRoles", evtstatistics);
	}

	@Override
	public int searchEventModelHrListCount(Evtstatistics evtstatistics) {
		return (Integer) getSqlMapClientTemplate().queryForObject("evtstatistics.searchEventModelHrListCount", evtstatistics);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evtstatistics> searchEventModelHrList(
			Evtstatistics evtstatistics) {
		return (List<Evtstatistics>) getSqlMapClientTemplate().queryForList("evtstatistics.searchEventModelHrList", evtstatistics);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evtstatistics> searchEventModelHrDetailList(
			Evtstatistics evtstatistics) {
		return (List<Evtstatistics>) getSqlMapClientTemplate().queryForList("evtstatistics.searchEventModelHrDetailList", evtstatistics);
	}

	@Override
	public int createEventModelHr(Evtstatistics evtstatistics) {
		getSqlMapClientTemplate().insert("evtstatistics.createEventModelHr", evtstatistics);
		return 1;
	}

	@Override
	public int updateEventModelHr(Evtstatistics evtstatistics) {
		return (Integer) getSqlMapClientTemplate().update("evtstatistics.updateEventModelHr", evtstatistics);
	}

	@Override
	public int searchHrOverTimeEvtDtlListCount(Evtstatistics evtstatistics) {
		return (Integer) getSqlMapClientTemplate().queryForObject("evtstatistics.searchHrOverTimeEvtDtlListCount", evtstatistics);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evtstatistics> searchHrOverTimeEvtDtlList(
			Evtstatistics evtstatistics) {
		String sql1="select d.event_id eventId,max(t.event_title) eventTitle, " +
				    "max(t.status) eventState,max(m.model_name) modelName,max(i.emp_name) initatorName ";
		String sql3=" from workflow.workflow_tb_event_detail d " +
                    "left join workflow.workflow_tb_event_detail d1 on d.next_eventdtl_id=d1.event_dtl_id " +
                    "left join workflow.workflow_tb_event_total t on t.event_id=d.event_id " +
                    "left join basis.basis_tb_salesemp_info i on i.emp_id=t.initator " +
                    "left join office.office_tb_evtstatistics_day da on to_date(da.day,'yyyyMMdd')<=d1.last_modify and to_date(da.day,'yyyyMMdd')>=d.last_modify " +
                    "left join office.office_tb_evtstatistics_model m on m.model_id=t.model_id " +
                    "where t.status='2' and d.next_eventdtl_id is not null and d.next_eventdtl_id <>'END' and m.model_id is not null ";
		
		//动态生成审批节点
		String sql2="";
		String[] stations=evtstatistics.getStations();
		if(stations!=null && stations.length>0){
			for(int i=0;i<stations.length;i++){
				if("total".equals(stations[i])){
					sql2+=",sum(office.num_evtstatistics_days(d.last_modify,d1.last_modify)) as column"
						    + (i+1);
				}else{
					sql2+=",sum(decode(d1.cur_sta_id,'"
							+ stations[i] 
							+ "',office.num_evtstatistics_days(d.last_modify,d1.last_modify))) as column"
							+ (i+1);
				}
			}
		}
		String sql = sql1+sql2+sql3;
		evtstatistics.setSql(sql);
		return (List<Evtstatistics>) getSqlMapClientTemplate().queryForList("evtstatistics.searchHrOverTimeEvtDtlList", evtstatistics);
	}
	
	@Override
	public int searchSendEmailDateCount() {
		return (Integer) getSqlMapClientTemplate().queryForObject("evtstatistics.searchSendEmailDateCount");
	}
	
	@Override
	public int createSendEmailDate() {
		getSqlMapClientTemplate().insert("evtstatistics.createSendEmailDate");
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evtstatistics> searchOverTimeEvtDtlForEmail(
			Evtstatistics evtstatistics) {
		return (List<Evtstatistics>) getSqlMapClientTemplate().queryForList("evtstatistics.searchOverTimeEvtDtlForEmail", evtstatistics);
	}

}
