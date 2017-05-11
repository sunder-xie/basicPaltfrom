package com.kintiger.platform.framework.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.kintiger.platform.monitor.pojo.ChartEntity;
import com.kintiger.platform.monitor.pojo.HessianDetail;

public class ChartDataConvertUtil {

	public static List<ChartEntity> convert2ChartEntity(
			List<HessianDetail> hessianDetailList) throws ParseException {
		Map<String, ChartEntity> map = new HashMap<String, ChartEntity>();
		List<ChartEntity> ll = new ArrayList<ChartEntity>();
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		List<String> dateStrList = new ArrayList<String>();
		for (int i = 9; i >= 0; i--) {
			Calendar date = Calendar.getInstance();
			date.setTime(today);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
			String dateStr = dft.format(date.getTime());
			dateStrList.add(dateStr);
		}
		String hessianName = null;
		for (HessianDetail detail : hessianDetailList) {
			hessianName = detail.getHessianName();
			ChartEntity entity = new ChartEntity();
			List<Long> dataList = new ArrayList<Long>();
			List<String> attList = new ArrayList<String>();
			entity.setName(hessianName.split("\\.")[6] + "."
					+ hessianName.split("\\.")[7]);
			entity.setData(dataList);
			entity.setAtt(attList);
			// 不知道什么逻辑了
			// 前10天循环 => 没记录写入0,删去当天 !有记录写入真实值,删去当天且break;
			for (Iterator iterator = dateStrList.iterator(); iterator.hasNext();) {
				String date = (String) iterator.next();
				if (date.equals(detail.getRecordDate())) {
					if (map.containsKey(hessianName)) {
						map.get(hessianName).getData().add(detail.getCount());
						map.get(hessianName).getAtt()
								.add(detail.getRecordDate());
					} else {
						dataList.add(detail.getCount());
						attList.add(detail.getRecordDate());
						map.put(hessianName, entity);
					}
					iterator.remove();
					break;
				} else {
					if (map.containsKey(hessianName)) {
						map.get(hessianName).getData().add(0L);
						map.get(hessianName).getAtt().add(date);
					} else {
						dataList.add(0L);
						attList.add(date);
						map.put(hessianName, entity);
					}
					iterator.remove();
				}
			}
		}
		//循环剩余天数在循环%>_<%
		for (String s : dateStrList) {
			ChartEntity entity = new ChartEntity();
			List<Long> dataList = new ArrayList<Long>();
			List<String> attList = new ArrayList<String>();
			if (map.containsKey(hessianName)) {
				map.get(hessianName).getData().add(0L);
				map.get(hessianName).getAtt().add(s);
			} else {
				dataList.add(0L);
				attList.add(s);
				entity.setName(hessianName);
				entity.setData(dataList);
				entity.setAtt(attList);
				map.put(hessianName, entity);
			}

		}
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ChartEntity> entry = (Map.Entry) it.next();
			ll.add(entry.getValue());
		}
		return ll;
	}
}
