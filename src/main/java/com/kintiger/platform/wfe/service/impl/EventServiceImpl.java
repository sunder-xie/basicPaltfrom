package com.kintiger.platform.wfe.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;









import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.service.IAllUserService;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.file.pojo.BudgetFileTmp;
import com.kintiger.platform.framework.timer.pojo.WxSendCc;
import com.kintiger.platform.framework.timer.pojo.WxSendCuruser;
import com.kintiger.platform.framework.timer.pojo.WxSendInitator;
import com.kintiger.platform.framework.util.DateUtil;
import com.kintiger.platform.framework.util.FileUtil;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.webservice.pojo.ProcessEventTotal;
import com.kintiger.platform.webservice.service.IWebService;
import com.kintiger.platform.wfe.dao.IEventDao;
import com.kintiger.platform.wfe.pojo.BusinessTripApply;
import com.kintiger.platform.wfe.pojo.Cc;
import com.kintiger.platform.wfe.pojo.LinkMan;
import com.kintiger.platform.wfe.pojo.OrderCheck;
import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.pojo.ProEventTotal;
import com.kintiger.platform.wfe.pojo.TripWay;
import com.kintiger.platform.wfe.service.IEventService;


public class EventServiceImpl implements IEventService {
	
	private static final Logger logger = Logger.getLogger(EventServiceImpl.class);
	
	private IEventDao eventDao;
	private IWebService webService;
	private String appUrl;		//Ӧ�ù���·��
	private String imgUrl;
	private TransactionTemplate transactionTemplate;
	private String wfeUploadPath;
	private String wfeDownloadPath;
	private IAllUserService allUserService;
	
	public IEventDao getEventDao() {
		return eventDao;
	}

	public void setEventDao(IEventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	public Long getEvent_XmlTempId(){
		return eventDao.getEvent_XmlTempId();
	}
	
	/**
	 * ��������
	 */
	public StringResult createEvent(String key, String userId, String title, String nextUser,
			String eventContent, String userList, String memo){
		StringResult result = new StringResult();
		try{
			String processInstanceId = "";
			String md = key.substring(0, 3);
			if("any".equals(md)){			//�����Զ�����
				Object[] res = new Object[8];
				res[0] = key;
				res[1] = userId;
				res[2] = title;
				res[3] = nextUser;
				res[4] = appUrl+"/wfe/eventAction!searchEventContent.jspa";
				res[5] = memo;
				res[6] = "";
				res[7] = "";
				processInstanceId = webService.startAnyProcessWorkflow(res);			
			}else if("sem".equals(md)){ 		//�������Զ�������
				Object[] res = new Object[8];
				res[0] = key;
				res[1] = userId;
				res[2] = title;
				res[3] = userList;
				res[4] = appUrl+"/wfe/eventAction!searchEventContent.jspa";
				res[5] = memo;
				res[6] = "";
				res[7] = "";
				processInstanceId = webService.startSemiAutomaticWorkflow(res);		
			}else{
				Object[] res = new Object[5];
				res[0] = key;
				res[1] = userId;
				res[2] = appUrl+"/wfe/eventAction!searchEventContent.jspa";
				res[3] = title;
				res[4] = memo;
				processInstanceId = webService.startWorkflow(res);
			}
			try{
				Long.valueOf(processInstanceId);		//�ж������Ƿ���������	
				result.setCode(IEventService.SUCCESS);
				result.setResult(processInstanceId);
			}catch(Exception e){
				result.setCode(IEventService.ERROR);
				result.setResult("����ʧ�ܣ�");
				return result;
			}
		} catch(Exception e){
			logger.error(e);
			result.setCode(IEventService.ERROR);
			result.setResult(IEventService.ERROR_MESSAGE);
		}
		return result;
	}
	
	public String getProEventDetail(String eventId, String userId){
		return eventDao.getProEventDetail(eventId, userId);	
	}

	/**
	 * ��ѯ������ϵ��
	 * @param linkMan
	 * @return
	 */
	public List<LinkMan> getLinkManList(LinkMan linkMan) {
		try {
			return eventDao.getLinkManList(linkMan);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(linkMan), e);
		}
		return null;
	}

	/**
	 * ����������ϵ��
	 */
	public BooleanResult saveOrUpdateLinkMan(LinkMan linkMan) {
		BooleanResult result = new BooleanResult();
		try {
			int n = eventDao.getLinkManCount(linkMan);
			if (n == 0) {
				Long id = eventDao.createLinkMan(linkMan);
				result.setCode(id.toString());
				result.setResult(true);
			} else {
				int c = eventDao.updateLinkMan(linkMan);
				if (c == 1) {
					result.setResult(true);
				}
			}
		} catch (Exception e) {
			result.setResult(false);
			logger.error(LogUtil.parserBean(linkMan), e);
		}
		return result;
	}
	
	/**
	 * ��ȡActionId
	 */
	public String getWfeActionId(String eventId) {
		String actionId = eventDao.getWfeActionId(eventId);
		return actionId;
	}
	
	public ProEventTotal getEventTotalById(Long eventId) {
		try {
			return eventDao.getEventTotalById(eventId);
		} catch (Exception e) {
			logger.error("eventId:" + eventId, e);
		}

		return null;
	}
	
	/**
	 * ��������Ų�ѯ�����ܱ�
	 */
	public ProEventTotal getEventTotalById(Long eventId,Long ccId){
		try {
			return eventDao.getEventTotalById(eventId,ccId);
		} catch (Exception e) {
			logger.error("eventId:" + eventId, e);
		}
		return null;
	}
	
	/***
	 * ��ѯ������ϸ������
	 */
	public List<ProEventDetail> getEventDetailListAndSort(Long eventId) {
		try {
			ProEventDetail proEventDetail = new ProEventDetail();
			proEventDetail.setEventId(eventId);
//			proEventDetail.setSort("time");
//			proEventDetail.setDir("asc");

			List<ProEventDetail> temp = eventDao.getEventDetailList(proEventDetail);

			Map<Long, ProEventDetail> eventDetailMap = new LinkedHashMap<Long, ProEventDetail>();
			StringBuffer link = new StringBuffer();
			for (ProEventDetail detail : temp) {
				//�ж��Ƿ��г��͵�����
				if(detail.getRepalyCount()!=null){
					Cc cc = new Cc();
					cc.setEvent_id(Long.valueOf(eventId));
					cc.setEvent_detail_id(Long.valueOf(detail.getEventDtlId()));
					List<Cc> cclist = eventDao.getCcListByEventId(cc);
					if(cclist!=null&&cclist.size()!=0){
						detail.setCcs(cclist);
					}
				}
				// ɾ��link
				link.delete(0, link.length());

				if ( !"".equals(detail.getFilename()) && detail.getFilename() != null) {
//					link.append("<a href=\"javascript:downLoad(");
//					link.append(detail.getFileId());
//					link.append(");\">");
					link.append(detail.getFilename());
//					link.append("</a>&nbsp;");
					link.append("&nbsp;&nbsp;");
					
					link.append("<a href=\"javascript:downLoad(");
					link.append(detail.getFileId());
					link.append(");\"><img src=\"");
					link.append(imgUrl);
					link.append("/css/easyui/themes/icons/download.gif\" height=16 width=16 align=absMiddle border=0><font color=\"2A586F\">����</font>");
					link.append("</a>&nbsp;");
					
					//link.append("<a href=\"javascript:preview(");
					//link.append(detail.getFileId());
					//link.append(");\"><img src=\"");
					//link.append(imgUrl);
					//link.append("/css/easyui/themes/icons/search.png\" height=16 width=16 align=absMiddle border=0><font color=\"2A586F\">Ԥ��</font>");
					//link.append("</a>&nbsp;</br>");
//					if(detail.getFilename().endsWith(".doc")||detail.getFilename().endsWith(".docx")||detail.getFilename().endsWith("xls")){
//						link.append("<a href='javascript:preview("+detail.getFileId()+")'><font color='red'>Ԥ��</font></a>&nbsp;&nbsp;&nbsp;");
//					}
				}

				ProEventDetail ped = eventDetailMap.get(detail.getEventDtlId());
				if (ped == null) {
					detail.setLink(link.toString());
					eventDetailMap.put(detail.getEventDtlId(), detail);
				} else {
					ped.setLink(link.append(ped.getLink()).toString());
				}
			}

			return new ArrayList<ProEventDetail>(eventDetailMap.values());
		} catch (Exception e) {
			logger.error("��ѯ����" + eventId + " ����", e);
		}
		return null;
	}
	
	public boolean processAttachments(final File[] uploadFiles,
			final String[] uploadFileNames, final Long eventDetailId, final String timestamp,final String key) {
		boolean result = true;
		if (uploadFileNames != null && uploadFileNames.length > 0
				&& StringUtils.isNotEmpty(timestamp)) {

			result = (Boolean) transactionTemplate
					.execute(new TransactionCallback() {
						public Boolean doInTransaction(TransactionStatus ts) {
							// ������
							String newFileName = null;
							boolean saveAsFile = false;

							File savedir = new File(wfeUploadPath + "/"
									+ key +"/"+ DateUtil.datetime("yyyyMM"));
							// ���Ŀ¼�����ڣ����½�
							if (!savedir.exists()) {
								savedir.mkdirs();
							}

							for (int i = 0; i < uploadFiles.length; i++) {
								if (uploadFileNames[i] != null
										&& uploadFileNames[i].length() > 0) {

									newFileName = timestamp
											+ i
											+ FileUtil
													.getFileExtention(uploadFileNames[i]);

									File imageFile = new File(wfeUploadPath
											+ "/"+ key +"/"+ DateUtil.datetime("yyyyMM")
											+ "/" + newFileName);

									saveAsFile = FileUtil.saveAsFile(
									        uploadFiles[i], imageFile);

									if (!saveAsFile) {
										logger.error("saveAsFile error: "
												+ imageFile);
										ts.setRollbackOnly();
										return false;
									}

									String filename = uploadFileNames[i];
									BudgetFileTmp ftp = new BudgetFileTmp();
									ftp.setFileName(filename);
									ftp.setEventDtlId(eventDetailId);// ��ʱ����һ��id
									ftp.setFileNameNew(newFileName);
									ftp.setSubFolders(key+"/"+DateUtil
											.datetime("yyyyMM"));

									try {
										eventDao.createBudgetFileTmp(ftp);
									} catch (Exception e) {
										logger.error(e);
										ts.setRollbackOnly();
										return false;
									}

								}
							}

							return true;
						}
					});
		}
		return result;
	
	}
	
	public List<ProEventDetail> getStationListByEventId(Long eventId) {
		try {
			return eventDao.getStationListByEventId(eventId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public List<ProEventDetail> getBackListByEventId(Long eventId) {
		try {
			return eventDao.getBackListByEventId(eventId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public int getTripWayCount(TripWay tripWay) {
		try {
			return eventDao.getTripWayCount(tripWay);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	public List<TripWay> getTripWayList(TripWay tripWay) {
		try {
			return eventDao.getTripWayList(tripWay);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public void updateBuglogFile(Long eventId, String state) {
		try {
			eventDao.updateBuglogFile(eventId, state);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	/**�������̻����¼�״̬����
	 * @author cg.jiang
	 */
	public void updateEventFlag(Long eventId, String backUserIdList) {
		try {
			eventDao.updateEventFlag(eventId, backUserIdList);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	
	/**
	 * ��������ģ���ѯ��������
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal){
		try {
			return eventDao.getTripApplyListCount(proEventTotal);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	/**
	 * ��������Ų�ѯ����
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal){
		try {
			return eventDao.getTripApplyList(proEventTotal);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public File exportBusinessTripApplyList(List<BusinessTripApply> businessTripApplyList) {

		try{
			// ������ģ���ļ�����·��
			File saveDir = new File(wfeDownloadPath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			File rootFile = new File(wfeDownloadPath + "/" + "emptyBasis.xls");
			if (!rootFile.exists()) {
				rootFile.createNewFile();
			}

			// д���ݵ�Excel�����
			WritableWorkbook book = Workbook.createWorkbook(rootFile);
			WritableSheet sheet = book.createSheet("Sheet_1", 0);
			//	����������ʽ
			WritableFont fontHead = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
			WritableCellFormat formatHead = new WritableCellFormat(fontHead);
			formatHead.setAlignment(Alignment.CENTRE);
			formatHead.setVerticalAlignment(VerticalAlignment.CENTRE);
			formatHead.setBackground(jxl.format.Colour.YELLOW); // ���õ�Ԫ��ı�����ɫ
			
			WritableCellFormat formatTable = new WritableCellFormat();
			formatTable.setAlignment(Alignment.CENTRE);
			formatTable.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			Label label;
			// ���õ�һ���и�
			sheet.setRowView(0, 400);
			label = new Label(0, 0, "����ID", formatHead);
			sheet.addCell(label);
			// �����п�
			sheet.setColumnView(0, 20);
			
			label = new Label(1, 0, "�������", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(1, 20);
			
			label = new Label(2, 0, "�����", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(2, 20);
			
			label = new Label(3, 0, "��������", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(3, 20);
			
			label = new Label(4, 0, "������Ŀ", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(4, 20);
			
			label = new Label(5, 0, "����ص�", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(5, 20);
			
			label = new Label(6, 0, "���з�ʽ", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(6, 40);
			
			label = new Label(7, 0, "��ʼʱ��", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(7, 20);
			
			label = new Label(8, 0, "����ʱ��", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(8, 20);
			
			label = new Label(9, 0, "����", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(9, 20);
	
			
			String temp2="yyyy-MM-dd";
			SimpleDateFormat formatter2 = new SimpleDateFormat (temp2);
			for(int i=0; i<businessTripApplyList.size(); i++){
				label = new Label(0, i+1, businessTripApplyList.get(i).getEventId(), formatTable);
				sheet.addCell(label);
				label = new Label(1, i+1, businessTripApplyList.get(i).getEventTitle(), formatTable);
				sheet.addCell(label);
				label = new Label(2, i+1, businessTripApplyList.get(i).getUserName(), formatTable);
				sheet.addCell(label);
				label = new Label(3, i+1, businessTripApplyList.get(i).getOrgName(), formatTable);
				sheet.addCell(label);
				label = new Label(4, i+1, businessTripApplyList.get(i).getCostCenterName(), formatTable);
				sheet.addCell(label);
				label = new Label(5, i+1, businessTripApplyList.get(i).getPlace(), formatTable);
				sheet.addCell(label);
				label = new Label(6, i+1, businessTripApplyList.get(i).getTripWayName(), formatTable);
				sheet.addCell(label);
				label = new Label(7, i+1, businessTripApplyList.get(i).getBeginDate()==null ? "" :formatter2.format(businessTripApplyList.get(i).getBeginDate()), formatTable);
				sheet.addCell(label);
				label = new Label(8, i+1, businessTripApplyList.get(i).getEndDate()==null ? "" :formatter2.format(businessTripApplyList.get(i).getEndDate()), formatTable);
				sheet.addCell(label);
				label = new Label(9, i+1, businessTripApplyList.get(i).getReason(), formatTable);
				sheet.addCell(label);
			}
			book.write();
			book.close();
			return rootFile;
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	
	}
	/***
	 * ����������
	 * @param cc
	 * @return
	 */
	public StringResult createCc(List<Cc> cc){
		try {
			return eventDao.createCc(cc);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * �޸ĳ�����Ϣ
	 * @param businessTripApplyList
	 * @return
	 */
	public int  updateCc(Cc cc){
		try {
			return eventDao.updateCc(cc);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	
	/**
	 * ��ȡ������Ϣ�б�����
	 * @param cc
	 * @return
	 */
	public int  getCcListCount(Cc cc){
		try {
			return eventDao.getCcListCount(cc);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	/***
	 * ��ȡ������Ϣ�б�
	 * @param cc
	 * @return
	 */
	public List<Cc> getCcList(Cc cc){
		try {
			return eventDao.getCcList(cc);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	/***
	 * ��ȡ������Ϣ�б�
	 * @param cc
	 * @return
	 */
	public List<Cc> getCcListByEventId(Cc cc){
		try {
			return eventDao.getCcListByEventId(cc);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	/**
	 * ��ȡ�ܾ��б�
	 * @param eventTotal
	 * @return
	 */
	public int getCancelEventCount(ProcessEventTotal eventTotal){
		return eventDao.getCancelEventCount(eventTotal);
	}
	
	/**
	 * ��ȡ�ܾ���Ϣ�б�
	 * @param eventTotal
	 * @return
	 */
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal){
		return eventDao.getCancelEventList(eventTotal);
	}
	
	public String getStationId(String userId) {
		try {
			return eventDao.getStationId(userId);
		} catch (Exception e) {
			logger.error(e);
		}
		return "";
	}
	public int setIsRead(ProcessEventTotal eventTotal) {
		try {
		return eventDao.setIsRead(eventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	public int getCountRoleByUser(String userId, String roleid) {
		try {
			return eventDao.getCountRoleByUser(userId,roleid);
			} catch (Exception e) {
				logger.error(e);
			}
			return 0;
		
	}
	

	public List<ProEventDetail> getUntreatedUserListByEventId(String eventId) {
		List<ProEventDetail>  proEventDetailList=new ArrayList<ProEventDetail>();
		try {
			List<AllUsers> allUsersList=eventDao.getUntreatedUserListByEventId(eventId);
			if(allUsersList!=null&&allUsersList.size()!=0){
				AllUsers  allUsers2=allUsersList.get(1);
				if(allUsers2!=null){
					if(StringUtils.isNotEmpty(allUsers2.getUserId())){
						ProEventDetail proEventDetail=new ProEventDetail();
						allUsers2=allUserService.getAllUser(allUsers2.getUserId());
						proEventDetail.setUserName(allUsers2.getUserName());
						proEventDetailList.add(proEventDetail);
					}
				}
				AllUsers  allUsers=allUsersList.get(0);
				if(allUsers!=null){
					if(StringUtils.isNotEmpty(allUsers.getUserId())){
						String []codes=allUsers.getUserId().split(",");
						for (int i = 0; i < codes.length; i++) {
							ProEventDetail proEventDetail=new ProEventDetail();
							allUsers=allUserService.getAllUser(codes[i]);
							proEventDetail.setUserName(allUsers.getUserName());
							proEventDetailList.add(proEventDetail);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(EventServiceImpl.class,e);
		}
		
		return proEventDetailList;
		
	}
	
	public int getModelCount(ProcessEventTotal eventTotal) {
		try {
			return eventDao.getModelCount(eventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	public List<ProcessEventTotal> getModelList(ProcessEventTotal eventTotal) {
		try {
			return eventDao.getModelList(eventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public List<OrderCheck> getStationIdByUserIdForOrderCheck(String userId){
		try {
			return eventDao.getStationIdByUserIdForOrderCheck(userId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public int searchOrderCheckListCount(OrderCheck orderCheck){
		try {
			return eventDao.searchOrderCheckListCount(orderCheck);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	public List<ProEventDetail> getEventFileListsByEventId(String eventId) {
		try {
			return eventDao.getEventFileListsByEventId(eventId);
		} catch (Exception e) {
			return null;
		}
		
	}

	public IWebService getWebService() {
		return webService;
	}

	public void setWebService(IWebService webService) {
		this.webService = webService;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public String getWfeUploadPath() {
		return wfeUploadPath;
	}

	public void setWfeUploadPath(String wfeUploadPath) {
		this.wfeUploadPath = wfeUploadPath;
	}

	public String getWfeDownloadPath() {
		return wfeDownloadPath;
	}

	public void setWfeDownloadPath(String wfeDownloadPath) {
		this.wfeDownloadPath = wfeDownloadPath;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}
	
	public int getCcCount(Cc cc) {
		try {
			return eventDao.getCcCount(cc);
		} catch (Exception e) {
			logger.error(e);
		}
		return -1;
	}

	
	public List<ProcessEventTotal> getRoleEvents(Object[] object) {
			List<ProcessEventTotal> proEventTotalList=new ArrayList<ProcessEventTotal>();
			List<ProcessEventTotal> events= eventDao.getRoleEventsByUserId(String.valueOf(object[0]));//ת������
			proEventTotalList.addAll(events);
			List<ProcessEventTotal> et=eventDao.getCountersignEvents(object);
			proEventTotalList.addAll(et);
			return proEventTotalList;
	}

	
	public List<ProcessEventTotal> getCompleteCountersignEvents(
			Object[] object) {
		List<ProcessEventTotal> proEventTotalList= eventDao.getCompleteCountersignEvents(object);//��ǩ����
		return proEventTotalList;
	}
	
	public List<AllUsers> searchAllUsersByRtxCode(AllUsers rtxuser) {
		try {
			return eventDao.searchAllUsersByRtxCode(rtxuser);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public List<Cc> getCcListBycc(Cc cc) {
		try {
			return eventDao.getCcListBycc(cc);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	
	public String completeSign(String eventId) {
		return eventDao.completeSign(eventId);
	}


	
	public List<WxSendCuruser> getWxSendCuruserList() {
		try {
			return eventDao.getWxSendCuruserList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	
	public void updateWxCuruserStatus(WxSendCuruser wxSendCuruser) {
		try {
		eventDao.updateWxCuruserStatus(wxSendCuruser);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}

	
	public List<WxSendCc> getWxSendCcList() {
		try {
			return eventDao.getWxSendCcList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	
	public void updateWxCcStatus(WxSendCc wxSendCc) {
		try {
		eventDao.updateWxCcStatus(wxSendCc);
	} catch (Exception e) {
		logger.error(e);
	}
		
	}

	
	public List<WxSendInitator> getWxSendInitatorList() {
		try {
			return eventDao.getWxSendInitatorList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	
	public void updateWxInitatorStatus(WxSendInitator wxSendInitator) {
		try {
			eventDao.updateWxInitatorStatus(wxSendInitator);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	
	public String checkProcessUserId(String userId, String eventId) {
		try {
			return	eventDao.checkProcessUserId(userId,eventId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public String searchWorkflowEventdetailCur_sta(ProEventDetail proEventDetail) {
		try{
			List<ProEventDetail > list=eventDao.searchWorkflowEventdetailCur_sta(proEventDetail);
			if (list==null) {
				return "0";
			}else {
				if (list.size()==0) {
					return "0";
				}
				String cursta=list.get(0).getCurStaId();
				if (cursta.indexOf("star")>=0) {
					return "1";
				}else if (cursta.indexOf("qctb_yyjhb")>=0) {
					return "2";
				}else if (cursta.indexOf("qctb_cgb")>=0) {
					return "3";
				}else if (cursta.indexOf("qctb_pbb")>=0) {
					return "4";
				}else if (cursta.indexOf("qctb_sgb")>=0) {
					return "5";
				}
				
				
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			return "0";
	}

	@Override
	public String checkFix_signsampleDate(
			ProEventDetail proEventDetail) {
		String showlevel=proEventDetail.getOperation();
		int total=0;
		if ("1".equals(showlevel) || "2".equals(showlevel) || "3".equals(showlevel) || "4".equals(showlevel)) {
			total=eventDao.checkFix_signsampleDate(proEventDetail);
			if(total>0){
				return "��������ҵ���������ύ��";
			}
		}
		
		return "ok";
	}

	@Override
	public void kunnrProcess(String eventId) {
		String kunnrId =eventDao.getKunnrId(eventId);
		if(kunnrId!=null&&!"".equals(kunnrId.trim())){
			//�޸ľ�����״̬Ϊ������  3
			eventDao.updateKunnrStatus(kunnrId);
			//������Ŀ���������������ϸ���в����¼
			 eventDao.createGoalSalesChange(eventId);
			 eventDao.createGoalSalesChangeDetail(eventId,kunnrId);
			//�ھ�����Ŀ���������������ϸ���в����¼
			 eventDao.createDealerAdjustMent(eventId);
			 eventDao.createDealerAdjustDetail(eventId,kunnrId);
			//��Ŀ�����еľ����̵�Ŀ����״̬��Ϊɾ��     
			 eventDao.deleteKunnrTarget(eventId,kunnrId);
			 //�ڷ���Ŀ���������������ϸ���в����¼
			 eventDao.createGoalFxChange(eventId);
			 eventDao.createGoalFxChangeDetail(eventId,kunnrId);
			//�ѷ���Ŀ�����еľ����̵�Ŀ����״̬��Ϊɾ��     
			 eventDao.deleteFxTarget(eventId,kunnrId);
		}
		
	}
}
