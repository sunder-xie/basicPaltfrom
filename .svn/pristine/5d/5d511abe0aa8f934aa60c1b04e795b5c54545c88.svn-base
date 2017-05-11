package com.kintiger.platform.qq_email.util.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.evtstatistics.pojo.Evtstatistics;
import com.kintiger.platform.evtstatistics.service.EvtstatisticsService;
import com.kintiger.platform.qq_email.util.ToSendEmailOfEvtstatistics;

public class TaskToSendEmailForEvtstatistics extends TimerTask{

	private ServletContextEvent sce;
	private WebApplicationContext springContext;
	private EvtstatisticsService evtstatisticsService;
	private IDictService dictService;
	
	public TaskToSendEmailForEvtstatistics(ServletContextEvent sce){
		this.sce=sce;
	}
	@Override
	public void run() {
		try {
			springContext = WebApplicationContextUtils.
					getWebApplicationContext(sce.getServletContext());
			evtstatisticsService = (EvtstatisticsService) springContext.getBean("evtstatisticsService");
			dictService = (IDictService) springContext.getBean("dictService");
			int count=evtstatisticsService.searchSendEmailDateCount();
			if (count==0) {
				int overDate=0;
				CmsTbDict cmsTbDict = new CmsTbDict();
				cmsTbDict.setItemName("流程效率统计超时天数");
				cmsTbDict.setPagination("false");
				List<CmsTbDict> cmsTbDictList = dictService.getByCmsTbDictList(cmsTbDict);
				for(CmsTbDict ct:cmsTbDictList){
					if("U".equals(ct.getItemState())){
						overDate=Integer.parseInt(ct.getItemValue());
					}
				}
				
				Map<String,List<Evtstatistics>> map = new HashMap<String,List<Evtstatistics>>();
				Evtstatistics evtstatistics = new Evtstatistics();
				evtstatistics.setOverDate(overDate);
				List<Evtstatistics> evtstatisticsList=evtstatisticsService.searchOverTimeEvtDtlForEmail(evtstatistics);
				
				for(Evtstatistics et:evtstatisticsList){
					List<Evtstatistics> list=map.get(et.getEmail());
					if(list==null || list.size()==0){
						List<Evtstatistics> tmp = new ArrayList<Evtstatistics>();
						tmp.add(et);
						map.put(et.getEmail(), tmp);
					}else{
						list.add(et);
						map.put(et.getEmail(), list);
					}
				}
				
				for(String key:map.keySet()){
					String content="您有未处理的待办事宜已超时：</br></br>";
					List<Evtstatistics> tmp = map.get(key);
					for(Evtstatistics et:tmp){
						content+="事务号："+ et.getEventId() 
					             +"</br>事务标题："+ et.getEventTitle() 
					             +"</br>超时天数："+ et.getOverDate()
					             +"</br></br>";
					}
					ToSendEmailOfEvtstatistics.SendEmailForEvtstatistics(key,content);
				}
				evtstatisticsService.createSendEmailDate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
