package com.kintiger.platform.evtstatistics.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.xwpf.converter.core.utils.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.evtstatistics.pojo.Evtstatistics;
import com.kintiger.platform.evtstatistics.service.EvtstatisticsService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.ExportExcelUtil;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;

public class EvtstatisticsAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7362645616695027830L;

	private EvtstatisticsService evtstatisticsService;
	private IOrgService orgService;
	private List<Evtstatistics> evtstatisticsList;
	private Evtstatistics evtstatistics;
	private Integer total;
	
	private Long eventId;
	private @Decode String eventTitle;
	private @Decode String initatorName;
	private String eventState;
	private @Decode String modelName;
	private String modelId;
	private @Decode String modelType;
	private @Decode String overUserName;
	private @Decode String curUserName;
	private Integer overDate;
	private String status;
	private String startDate;
	private String endDate;
	private Long orgId;
	private String orgName;
	private String stations;
	private @Decode String stationNames;
	private String overFlag;
	
	private String uploadFileFileName;
	private File uploadFile;
	private String download;
	
	public String toSearchOverTimeEvtDtl(){
		return "toSearchOverTimeEvtDtl";
	}
	
	public String toSearchOverTimeEvt(){
		Borg borg=orgService.getOrgByUserId(this.getUser().getUserId());
		orgId=borg.getOrgId();
		orgName=borg.getOrgName();
		return "toSearchOverTimeEvt";
	}
	
	public String toSearchEventModel(){
		return "toSearchEventModel";
	}
	
	public String toSearchEventModelHr(){
		return "toSearchEventModelHr";
	}
	
	public String toSearchEventModelHrDetail(){
		Evtstatistics e = new Evtstatistics();
		e.setModelId(modelId);
		evtstatisticsList=evtstatisticsService.searchEventModelHrDetailList(e);
		return "toSearchEventModelHrDetail";
	}
	
	public String toSearchEvent(){
		return "toSearchEvent";
	}
	
	public String toSearchHrOverTimeEvtDtl(){
		return "toSearchHrOverTimeEvtDtl";
	}
	
	@JsonResult(field = "evtstatisticsList", include = { "orgId","orgName",
			"numA","numB","numC","numD","numE",}, total = "total")
	public String searchOverTimeEvtList() {
		evtstatistics = new Evtstatistics();
		evtstatistics.setStartDate(startDate);
		evtstatistics.setEndDate(endDate);
		evtstatistics.setOrgId(orgId);
		evtstatistics.setOverDate(overDate);
		evtstatistics.setStart(getStart());
		evtstatistics.setEnd(getEnd());
		total=evtstatisticsService.searchOverTimeEvtListCount(evtstatistics);
		if(total>0){
			evtstatisticsList=evtstatisticsService.searchOverTimeEvtList(evtstatistics);
		}
		return JSON;
	}
	
	@JsonResult(field = "evtstatisticsList", include = { "eventId","eventTitle",
			"eventState","initator","initatorName","modelId","modelName",
			"overDate","orgId","orgName","status","overUserId","overUserName"}, total = "total")
	public String searchOverTimeEvtDtlList() {
		evtstatistics = new Evtstatistics();
		evtstatistics.setEventId(eventId);
		evtstatistics.setEventTitle(eventTitle);
		evtstatistics.setEventState(eventState);
		evtstatistics.setInitatorName(initatorName);
		evtstatistics.setOverUserName(overUserName);
		evtstatistics.setModelName(modelName);
		evtstatistics.setModelType(modelType);
		evtstatistics.setStartDate(startDate);
		evtstatistics.setEndDate(endDate);
		evtstatistics.setOrgId(orgId);
		evtstatistics.setOverDate(overDate);
		evtstatistics.setStart(getStart());
		evtstatistics.setEnd(getEnd());
		total=evtstatisticsService.searchOverTimeEvtDtlListCount(evtstatistics);
		if(total>0){
			evtstatisticsList=evtstatisticsService.searchOverTimeEvtDtlList(evtstatistics);
		}
		return JSON;
	}
	
	@JsonResult(field = "evtstatisticsList", include = { "eventId","eventTitle",
			"eventState","initator","initatorName","modelId","modelName",
			"status","curUserId","curUserName","createDate"}, total = "total")
	public String searchEventList() {
		evtstatistics = new Evtstatistics();
		evtstatistics.setEventId(eventId);
		evtstatistics.setEventTitle(eventTitle);
		evtstatistics.setEventState(eventState);
		evtstatistics.setInitatorName(initatorName);
		evtstatistics.setCurUserName(curUserName);
		evtstatistics.setModelName(modelName);
		evtstatistics.setStartDate(startDate);
		evtstatistics.setEndDate(endDate);
		if(StringUtils.isNotEmpty(modelId)){
			String[] modelIds=modelId.split(",");
			evtstatistics.setModelIds(modelIds);
		}
		evtstatistics.setStart(getStart());
		evtstatistics.setEnd(getEnd());
		total=evtstatisticsService.searchEventListCount(evtstatistics);
		if(total>0){
			evtstatisticsList=evtstatisticsService.searchEventList(evtstatistics);
		}
		return JSON;
	}
	
	@JsonResult(field = "evtstatisticsList", include = { "modelId",
			"modelName","modelType"}, total = "total")
	public String searchEventModelList() {
		evtstatistics = new Evtstatistics();
		evtstatistics.setModelName(modelName);
		evtstatistics.setModelType(modelType);
		evtstatistics.setStart(getStart());
		evtstatistics.setEnd(getEnd());
		total=evtstatisticsService.searchEventModelListCount(evtstatistics);
		if(total>0){
			evtstatisticsList=evtstatisticsService.searchEventModelList(evtstatistics);
		}
		return JSON;
	}
	
	@JsonResult(field = "total")
	public String updateEventModel(){
		evtstatistics = new Evtstatistics();
		if(StringUtils.isNotEmpty(modelId) 
				&& StringUtils.isNotEmpty(modelType)){
			evtstatistics.setModelId(modelId);
			evtstatistics.setModelType(modelType);
			total=evtstatisticsService.updateEventModel(evtstatistics);
		}else{
			total=0;
		}
		return JSON;
	}
	
	public void exportEventModelCsv() {
		OutputStream os = null;
		String report_name = "�������͵���ģ��";
		PrintWriter print = null;
		try {
			evtstatistics = new Evtstatistics();
			evtstatisticsList=evtstatisticsService.searchEventModelForCsv(evtstatistics);
			HttpServletResponse response = ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("����ģ��");
			linedata.append(",");
			linedata.append("ģ����");
			linedata.append(",");
			linedata.append("��������");
			linedata.append("\n");
			if(evtstatisticsList!=null && evtstatisticsList.size()>0){
				for(Evtstatistics et: evtstatisticsList){
					linedata.append(et.getModelName());
					linedata.append(",");
					linedata.append(et.getModelId());
					linedata.append(",");
					linedata.append("");
					linedata.append("\n");
				}
			}
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				os = null;
			}
		}
	}
	
	public String importEventModelCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		Map<String,String> map = new HashMap<String,String>();
		try {
			evtstatistics = new Evtstatistics();
			evtstatisticsList=evtstatisticsService.searchEventModelForCsv(evtstatistics);
			for(Evtstatistics et:evtstatisticsList){
				map.put(et.getModelId(), et.getModelName());
			}
			
			String rcs = "";
			String rcs2 = "";
			List<Evtstatistics> list = new ArrayList<Evtstatistics>();
			
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				Pattern p = Pattern.compile("^(.*\\.csv)|(.*\\.CSV)$");//�ж��Ƿ�ΪCSV�ļ�
				Matcher matcher = p.matcher(uploadFileFileName);
				if (matcher.matches()){
					String[] header = SuperCSV.getHeaderFromFile(new File( //��ȡͷ���ֶ�
							uploadFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // ��ȡ��������
					for(int j=0;j<content.size();j++){
						String[] s=content.get(j);
						if(s.length<=header.length){
							if(s.length>3){
								this.setFailMessage("��" + (j + 2) + "����������ȷ.");
								return RESULT_MESSAGE;
							}
							if(StringUtils.isNotEmpty(s[0]) && StringUtils.isNotEmpty(s[1]) 
									&& StringUtils.isNotEmpty(s[2])){
								Evtstatistics e = new Evtstatistics();
								String name=map.get(s[1]);
								if(StringUtils.isNotEmpty(name)){
									if(name.equals(s[0])){
										e.setModelName(s[0]);
										e.setModelId(s[1]);
									}else{
										rcs = rcs + "��" + (j + 2) + "��ģ����Ϣ����.</br>";
									}
								}else{
									rcs = rcs + "��" + (j + 2) + "��ģ����Ϣ����.</br>";
								}
								if(s[2].equals("Эͬ�칫")){
									e.setModelType("1");
								}else if(s[2].equals("�ͻ���ϵ����")){
									e.setModelType("2");
								}else if(s[2].equals("Ӫ�����ù���")){
									e.setModelType("3");
								}else if(s[2].equals("���µ�������")){
									e.setModelType("4");
								}else if(s[2].equals("�����̹���")){
									e.setModelType("5");
								}else{
									rcs = rcs + "��" + (j + 2) + "��ģ����������ֻ������д<Эͬ�칫/�ͻ���ϵ����/Ӫ�����ù���/���µ�������/�����̹���>.</br>";
								}
								list.add(e);
							}else{
								rcs = rcs + "��" + (j + 2) + "��δ��д����.</br>";
							}
							if (!"".equals(rcs)) {
								result.append(rcs.toString() + "</br>");
								rcs = "";
							}
						}else{
							rcs = rcs + "��" + (j + 2)
									+ "����������.</br>";
							result.append(rcs.toString() + "</br>");
							rcs = "";
						}
					}
					if (list.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<list.size();j++){
							try {
								rcs2 = "";
								evtstatisticsService.createEventModel(list.get(j));
								result1.setResult(true);
							} catch (Exception e) {
								rcs2 = rcs2 + "��" + (j + 2)
										+ "�����ݱ������ݿ�ʧ��.����ϵϵͳ����Ա.</br>";
								result.append(rcs2.toString() + "</br>");
							}
						}
					}
					if (result1.getResult()) {
						this.setSuccessMessage("����ɹ�����������Ϊ:"
								+ content.size() + "��");
					} else if (!result.toString().equals("")) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br></br>"
								+ result.toString());
					} else if (!result1.getResult()) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br></br>"
								+ result.toString());
					}
				}
			}else{
				this.setFailMessage("�ļ�������");
				return RESULT_MESSAGE;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		return RESULT_MESSAGE;
	}
	
	public void exportOverTimeEvtDtl() {
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		try {
			evtstatistics = new Evtstatistics();
			evtstatistics.setEventId(eventId);
			evtstatistics.setEventTitle(eventTitle);
			evtstatistics.setEventState(eventState);
			evtstatistics.setInitatorName(initatorName);
			evtstatistics.setOverUserName(overUserName);
			evtstatistics.setModelName(modelName);
			evtstatistics.setModelType(modelType);
			evtstatistics.setStartDate(startDate);
			evtstatistics.setEndDate(endDate);
			evtstatistics.setOrgId(orgId);
			evtstatistics.setOverDate(overDate);
			evtstatistics.setPagination("false");
			evtstatisticsList=evtstatisticsService.searchOverTimeEvtDtlList(evtstatistics);
			
			String[] rowName = {"������","�������","�����","����ģ��","����״̬","��ʱ������","��ʱ����"};
			String[] colName = {"eventId","eventTitle","initatorName","modelName","eventState","overUserName","overDate"};
			
			for(Evtstatistics et:evtstatisticsList){
				if(et.getEventState().equals("1")){
					et.setEventState("������");
				}else if(et.getEventState().equals("2")){
					et.setEventState("�����");
				}else if(et.getEventState().equals("3")){
					et.setEventState("������");
				}else if(et.getEventState().equals("4")){
					et.setEventState("��ȡ��");
				}
			}
			ExportExcelUtil export = new ExportExcelUtil("���̳�ʱͳ����ϸ",rowName,colName,evtstatisticsList);
			export.export();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@JsonResult(field = "evtstatisticsList", include = { "modelId",
			"modelName","createDate","curUserName"}, total = "total")
	public String searchEventModelHrList() {
		evtstatistics = new Evtstatistics();
		evtstatistics.setModelName(modelName);
		evtstatistics.setStart(getStart());
		evtstatistics.setEnd(getEnd());
		total=evtstatisticsService.searchEventModelHrListCount(evtstatistics);
		if(total>0){
			evtstatisticsList=evtstatisticsService.searchEventModelHrList(evtstatistics);
		}
		return JSON;
	}
	
	@JsonResult(field = "evtstatisticsList", include = { "modelId",
			"modelName","roleId","roleName","overDate"})
	public String searchEventModelHrDetailList() {
		evtstatistics = new Evtstatistics();
		evtstatistics.setModelId(modelId);
		evtstatisticsList=evtstatisticsService.searchEventModelHrDetailList(evtstatistics);
		return JSON;
	}
	
	public void exportEventModelHrCsv() {
		OutputStream os = null;
		String report_name = "������������Ч�����ñ�";
		PrintWriter print = null;
		try {
			evtstatistics = new Evtstatistics();
			evtstatistics.setModelId(modelId);
			evtstatisticsList=evtstatisticsService.searchEventModelHrRoles(evtstatistics);
			evtstatistics = new Evtstatistics();
			evtstatistics.setModelId(modelId);
			evtstatistics.setModelName(evtstatisticsList.get(0).getModelName());
			evtstatistics.setRoleId("total");
			evtstatistics.setRoleName("������");
			evtstatisticsList.add(evtstatistics);
			HttpServletResponse response = ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("����ģ��");
			linedata.append(",");
			linedata.append("��ɫID");
			linedata.append(",");
			linedata.append("��ɫ");
			linedata.append(",");
			linedata.append("����");
			linedata.append("\n");
			if(evtstatisticsList!=null && evtstatisticsList.size()>0){
				for(Evtstatistics et: evtstatisticsList){
					linedata.append(et.getModelName());
					linedata.append(",");
					linedata.append(et.getRoleId());
					linedata.append(",");
					linedata.append(et.getRoleName());
					linedata.append(",");
					linedata.append("");
					linedata.append("\n");
				}
			}
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				os = null;
			}
		}
	}
	
	/**
	 * @return
	 */
	public String importEventModelHrCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		Map<String,String> map = new HashMap<String,String>();
		try {
			evtstatistics = new Evtstatistics();
			evtstatistics.setModelId(modelId);
			evtstatisticsList=evtstatisticsService.searchEventModelHrRoles(evtstatistics);
			for(Evtstatistics et:evtstatisticsList){
				map.put(et.getRoleId(), et.getRoleName());
			}
			
			modelName=evtstatisticsList.get(0).getModelName();
			String rcs = "";
			String rcs2 = "";
			List<Evtstatistics> list = new ArrayList<Evtstatistics>();
			
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				Pattern p = Pattern.compile("^(.*\\.csv)|(.*\\.CSV)$");//�ж��Ƿ�ΪCSV�ļ�
				Matcher matcher = p.matcher(uploadFileFileName);
				if (matcher.matches()){
					String[] header = SuperCSV.getHeaderFromFile(new File( //��ȡͷ���ֶ�
							uploadFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // ��ȡ��������
					for(int j=0;j<content.size();j++){
						String[] s=content.get(j);
						if(s.length<=header.length){
							if(s.length>4){
								this.setFailMessage("��" + (j + 2) + "����������ȷ.");
								return RESULT_MESSAGE;
							}
							if(StringUtils.isNotEmpty(s[0]) && StringUtils.isNotEmpty(s[1]) 
									&& StringUtils.isNotEmpty(s[2]) && StringUtils.isNotEmpty(s[3])){
								Evtstatistics e = new Evtstatistics();
								String name=map.get(s[1]);
								if(StringUtils.isNotEmpty(name)){
									if(name.equals(s[2])){
										e.setRoleId(s[1]);
										e.setRoleName(s[2]);
									}else{
										rcs = rcs + "��" + (j + 2) + "��ģ����Ϣ����.</br>";
									}
								}else if(s[1].equals("total")){
									e.setRoleId(s[1]);
									e.setRoleName(s[2]);
								}else{
									rcs = rcs + "��" + (j + 2) + "��ģ����Ϣ����.</br>";
								}
								if(StringUtils.isNotEmpty(s[3])){
									Pattern p1 = Pattern.compile("^\\d+$");//�ж��Ƿ�ΪCSV�ļ�
									Matcher matcher1 = p1.matcher(s[3]);
									if (matcher1.matches()){
										e.setOverDate(Integer.parseInt(s[3]));
									}else{
										rcs = rcs + "��" + (j + 2) + "����������Ϊ����.</br>";
									}
								}else{
									rcs = rcs + "��" + (j + 2) + "����������Ϊ��.</br>";
								}
								e.setModelId(modelId);
								e.setModelName(modelName);
								e.setCurUserId(this.getUser().getUserId());
								list.add(e);
							}else{
								rcs = rcs + "��" + (j + 2) + "��δ��д����.</br>";
							}
							if (!"".equals(rcs)) {
								result.append(rcs.toString() + "</br>");
								rcs = "";
							}
						}else{
							rcs = rcs + "��" + (j + 2)
									+ "����������.</br>";
							result.append(rcs.toString() + "</br>");
							rcs = "";
						}
					}
					if (list.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<list.size();j++){
							try {
								rcs2 = "";
								evtstatisticsService.updateEventModelHr(list.get(j));
								result1.setResult(true);
							} catch (Exception e) {
								rcs2 = rcs2 + "��" + (j + 2)
										+ "�����ݱ������ݿ�ʧ��.����ϵϵͳ����Ա.</br>";
								result.append(rcs2.toString() + "</br>");
							}
						}
					}
					if (result1.getResult()) {
						this.setSuccessMessage("����ɹ�����������Ϊ:"
								+ content.size() + "��");
					} else if (!result.toString().equals("")) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br></br>"
								+ result.toString());
					} else if (!result1.getResult()) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br></br>"
								+ result.toString());
					}
				}
			}else{
				this.setFailMessage("�ļ�������");
				return RESULT_MESSAGE;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		return RESULT_MESSAGE;
	}
	
	@JsonResult(field = "evtstatisticsList", include = { "eventId","eventTitle",
			"eventState","initator","initatorName","modelId","modelName",
			"overDate","orgId","orgName","status","overUserId","overUserName",
			"column1","column2","column3","column4","column5","column6","column7",
			"column8","column9","column10","column11","column12","column13","column14",
			"column15","column16","column17","column18","column19","column20"}, total = "total")
	public String searchHrOverTimeEvtDtlList() {
		evtstatistics = new Evtstatistics();
		evtstatistics.setEventId(eventId);
		evtstatistics.setEventTitle(eventTitle);
		evtstatistics.setInitatorName(initatorName);
		evtstatistics.setModelId(modelId);
		evtstatistics.setStartDate(startDate);
		evtstatistics.setEndDate(endDate);
		if(StringUtils.isNotEmpty(overFlag)){
			evtstatistics.setOverDate(overDate);
			evtstatistics.setOverFlag(overFlag);
		}
		if(StringUtils.isNotEmpty(stations)){
			String[] station=stations.split(",");
			evtstatistics.setStations(station);
		}
		evtstatistics.setStart(getStart());
		evtstatistics.setEnd(getEnd());
		total=evtstatisticsService.searchHrOverTimeEvtDtlListCount(evtstatistics);
		if(total>0){
			evtstatisticsList=evtstatisticsService.searchHrOverTimeEvtDtlList(evtstatistics);
		}
		return JSON;
	}
	
	public void exportHrOverTimeEvtDtl() {
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		try {
			evtstatistics = new Evtstatistics();
			evtstatistics.setEventId(eventId);
			evtstatistics.setEventTitle(eventTitle);
			evtstatistics.setInitatorName(initatorName);
			evtstatistics.setModelId(modelId);
			evtstatistics.setStartDate(startDate);
			evtstatistics.setEndDate(endDate);
			if(StringUtils.isNotEmpty(overFlag)){
				evtstatistics.setOverDate(overDate);
				evtstatistics.setOverFlag(overFlag);
			}
			if(StringUtils.isNotEmpty(stations)){
				String[] station=stations.split(",");
				evtstatistics.setStations(station);
			}
			evtstatistics.setPagination("false");
			evtstatisticsList=evtstatisticsService.searchHrOverTimeEvtDtlList(evtstatistics);
			
			String[] rowName = {"����ģ��","������","�������","�����"};
			String[] colName = {"modelName","eventId","eventTitle","initatorName"};
			
			if(StringUtils.isNotEmpty(stations)){
				String[] station=new String[stations.split(",").length];
				for(int i=0;i<stations.split(",").length;i++){
					station[i]="column"+(i+1);
				}
				
				stationNames = java.net.URLDecoder.decode(stationNames,"UTF-8");
				String[] names = (String[]) ArrayUtils.addAll(rowName, stationNames.split(","));
				String[] cols = (String[]) ArrayUtils.addAll(colName, station);
				
				ExportExcelUtil export = new ExportExcelUtil("������������Ч��ͳ��",names,cols,evtstatisticsList);
				export.export();
			}
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * У�������Ƿ��������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "download")
	public String checkDownLoadOver() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute("DownLoad");
		if (obj == null || "Ing".equals(obj)) {
			download = "No";
		} else if("Over".equals(obj)){
			download = "Yes";
		} else if(!"".equals(obj)){
			download = obj.toString();
		}
		return JSON;
	}

	public EvtstatisticsService getEvtstatisticsService() {
		return evtstatisticsService;
	}
	public void setEvtstatisticsService(EvtstatisticsService evtstatisticsService) {
		this.evtstatisticsService = evtstatisticsService;
	}
	public List<Evtstatistics> getEvtstatisticsList() {
		return evtstatisticsList;
	}
	public void setEvtstatisticsList(List<Evtstatistics> evtstatisticsList) {
		this.evtstatisticsList = evtstatisticsList;
	}

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getInitatorName() {
		return initatorName;
	}
	public void setInitatorName(String initatorName) {
		this.initatorName = initatorName;
	}
	public String getEventState() {
		return eventState;
	}
	public void setEventState(String eventState) {
		this.eventState = eventState;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getOverUserName() {
		return overUserName;
	}
	public void setOverUserName(String overUserName) {
		this.overUserName = overUserName;
	}

	public Evtstatistics getEvtstatistics() {
		return evtstatistics;
	}

	public void setEvtstatistics(Evtstatistics evtstatistics) {
		this.evtstatistics = evtstatistics;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOverDate() {
		return overDate;
	}

	public void setOverDate(Integer overDate) {
		this.overDate = overDate;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCurUserName() {
		return curUserName;
	}

	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}

	public String getStations() {
		return stations;
	}

	public void setStations(String stations) {
		this.stations = stations;
	}

	public String getOverFlag() {
		return overFlag;
	}

	public void setOverFlag(String overFlag) {
		this.overFlag = overFlag;
	}

	public String getStationNames() {
		return stationNames;
	}

	public void setStationNames(String stationNames) {
		this.stationNames = stationNames;
	}
}
