package com.kintiger.platform.news.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.service.IAllUserService;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.exception.BaseException;
import com.kintiger.platform.framework.util.DateUtil;
import com.kintiger.platform.framework.util.FileUtil;
import com.kintiger.platform.news.pojo.IsRead;
import com.kintiger.platform.news.pojo.NewsDetail;
import com.kintiger.platform.news.pojo.NewsFile;
import com.kintiger.platform.news.pojo.NewsRecord;
import com.kintiger.platform.news.pojo.NewsTotal;
import com.kintiger.platform.news.pojo.Organ;
import com.kintiger.platform.news.service.INewsService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.qq_email.util.PoolSend;
import com.kintiger.platform.qq_email.util.SendHtmlEmailNoFile;
import com.kintiger.platform.qq_email.util.SendMail;
import com.kintiger.platform.qq_email.util.ToSendEmailOfSalary;
import com.kintiger.platform.qq_email.util.pojo.Salary;
/**
 * Title: ���Ź��淢��
 * @Description: basisPlatform
 * @author: xg.chen
 * @date:2016��12��20�� ����9:00:01
 */
public class NewsAction extends BaseAction {
	private static final long serialVersionUID = -5009095660964669519L;
	private static final Log logger = LogFactory.getLog(NewsAction.class);
	private List<NewsTotal> lanNewsTotalList = new ArrayList<NewsTotal>();
	private List<NewsDetail> lanNewsDetailList = new ArrayList<NewsDetail>();
	private INewsService newsService;
	private int total;
	private List<NewsTotal> newsTypelist;
	private String detailId;
	private String upload_sign;
	private NewsDetail lanNewsDelailbean;
	private String total_ids;
	private String oaNewsFilePath;
	private String node;
	private String actionName;
	private String newsId;
	private String totalParentId;
	private @Decode String totalName;
	private String totalUploadSign;
	private String totalShow;
	private String totalSign;
	private String totalCode;
	private @Decode String total_Name;
	private Date create_date;
	private @Decode String delail_title;
	private String delail_ids;
	private String total_id;
	private String delailTitle;
	private File[] upload;
	private String[] uploadFileName;
	private @Decode String delail_content;
	private String css_flag;
	private String totalId;
	private String filename;
	private String totalParentTotal;
	private String[] fileId;
	private List<NewsFile> lanNewsFileList;
	private @Decode boolean totalShow_flag;
	private Long recordId;
	private List<NewsTotal> statList;
	private Long orgId;
	private String orgName;
	private Long orgId1;
	private String orgName1;
	private Long orgId2;
	private String orgName2;
	private IOrgService orgService;
	private IAllUserService allUserService;
	private String orgs;//��˾�ɼ���֯
	private String orgs2;//�����̿ɼ���֯
	
	private Long delailId;//��ϸ��id
	private String userId;//��½���û�ID
	private String isRead;//�Ƿ��Ķ���ʶ
	
	public String getOrgs2() {
		return orgs2;
	}

	public void setOrgs2(String orgs2) {
		this.orgs2 = orgs2;
	}

	public String getOrgs() {
		return orgs;
	}

	public void setOrgs(String orgs) {
		this.orgs = orgs;
	}

	// ���ص�excel����
	private File fileContent; //

	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}

	/**
	 * MethodsTitle: ��ҳ����
	 * @author: xg.chen
	 * @date:2016��12��15�� ����1:23:59
	 * @version 1.1 ���ϵͳ�����Լ�BUG�޸�����������
	 * @return
	 */
	@PermissionSearch
	public String newsIndex() {
		lanNewsTotalList = new ArrayList<NewsTotal>();
		lanNewsDelailbean = new NewsDetail();
		// ȡ������֯�����ϼ���֯
		List<Borg> porgList = orgService.getAllParentOrgs(Long.parseLong(this
				.getUser().getOrgId()));
		String[] porgs = new String[porgList.size()];
		int i = 0;
		for (Borg org : porgList) {
			porgs[i] = org.getOrgId().toString();
			i++;
		}
		lanNewsDelailbean.setCodes(porgs);
		//
		List<NewsDetail> detailList = null;
		detailList = newsService.getNewsList(lanNewsDelailbean);
		/*if(detailList.size()==0){
			detailList = newsService.getNewsList1(lanNewsDelailbean);
		}*/
		
		Map<Long, NewsTotal> map = new HashMap<Long, NewsTotal>();
		for (NewsDetail d : detailList) {
			//ϵͳ���º�BUG�޸��Ƿ񵯳���Ӧ�Ĵ��ڵ�����
			//long totalId=13090;//��Ŀ���ñ��(�˱��Ϊ��Ŀ�е�Ψһ��ţ�����ʱ��Ҫ�޸�)
			long totalId=1127;
			if (totalId==d.getTotal_id()) {
				//�ȼ��һ�������ϸ�������û�ж���
				IsRead isRead=new IsRead();
				isRead.setDelailId(d.getDelail_id());
				isRead.setUserId(this.getUser().getUserId());
				IsRead isRead1=newsService.getIsRead(isRead);
				if(isRead1==null){
					//���û�����ڱ��в���һ��
					isRead.setDelailId(d.getDelail_id());
					isRead.setUserId(this.getUser().getUserId());
					isRead.setTotalId(d.getTotal_id());
					isRead.setIsRead("Y");
					newsService.insertIsRead(isRead);
					//������������ʾ
					NewsFile lanNewsFile = new NewsFile();
					lanNewsFile.setDetail_id(d.getDelail_id());
					lanNewsFileList = newsService.getNewsFileList(lanNewsFile);
					if (lanNewsFileList.size() > 0) {
						NewsFile NewsFile = lanNewsFileList.get(0);
						d.setNews_file_url(oaNewsFilePath + "/"
								+ NewsFile.getNews_file_url());
					}
					if (map.containsKey(d.getTotal_id())) {
						map.get(d.getTotal_id()).getNewsdet_list().add(d);
					} else {
						NewsTotal total = new NewsTotal();
						total.setTotal_id(d.getTotal_id());
						total.setTotal_name(d.getTotal_name());
						total.setTotal_sign(d.getTotal_sign());
						total.setTotal_show(d.getTotal_show());
						String content=getNewsContent(Long.valueOf(d.getDelail_id()));
						if (StringUtils.isNotEmpty(content)) {
							d.setDelail_content(content);
						} else {
							d.setDelail_content("  ");
						}
						total.setNewsdet_list(new ArrayList<NewsDetail>());
						total.getNewsdet_list().add(d);
						map.put(d.getTotal_id(), total);
						lanNewsTotalList.add(total);
						break;
					}
				} else {//���򷵻����õ����Ķ��Ķ�������
					NewsTotal total = new NewsTotal();
					total.setTotal_id(d.getTotal_id());
					total.setTotal_name("�Ѳ���");
					total.setTotal_sign(d.getTotal_sign());
					total.setTotal_show("S");
					total.setNewsdet_list(new ArrayList<NewsDetail>());
					total.getNewsdet_list().add(d);
					map.put(d.getTotal_id(), total);
					lanNewsTotalList.add(total);
				}
			} else {
				NewsFile lanNewsFile = new NewsFile();
				lanNewsFile.setDetail_id(d.getDelail_id());
				lanNewsFileList = newsService.getNewsFileList(lanNewsFile);
				if (lanNewsFileList.size() > 0) {
					NewsFile NewsFile = lanNewsFileList.get(0);
					d.setNews_file_url(oaNewsFilePath + "/"
							+ NewsFile.getNews_file_url());
				}
				if (map.containsKey(d.getTotal_id())) {
					map.get(d.getTotal_id()).getNewsdet_list().add(d);
				} else {
					NewsTotal total = new NewsTotal();
					total.setTotal_id(d.getTotal_id());
					total.setTotal_name(d.getTotal_name());
					total.setTotal_sign(d.getTotal_sign());
					total.setTotal_show(d.getTotal_show());
					total.setNewsdet_list(new ArrayList<NewsDetail>());
					total.getNewsdet_list().add(d);
					map.put(d.getTotal_id(), total);
					lanNewsTotalList.add(total);
				}
			}
		}
		map = null;
		return "newsIndex";
	}
	/**
	 * MethodsTitle: ��ȡ����
	 * @author: xg.chen
	 * @date:2016��12��16�� ����3:19:26
	 * @version 1.0
	 * @param delidId
	 * @return
	 */
	public String getNewsContent(Long delidId){
		lanNewsDelailbean = new NewsDetail();
		lanNewsDelailbean.setDelail_id(Long.valueOf(delidId));
		lanNewsDelailbean = newsService.getNewsDetail(lanNewsDelailbean);
		String conent = null;
		if (StringUtils.isNotEmpty(lanNewsDelailbean.getDelail_content())) {
			try {
				return conent = FileUtil.readFile(oaNewsFilePath + "/"
						+ lanNewsDelailbean.getDelail_content());
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return conent;
	}
	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	@PermissionSearch
	public String getOneNews() {
		lanNewsDelailbean = new NewsDetail();
		NewsFile lanNewsFile = new NewsFile();
		String conent = null;

		if (StringUtils.isNotEmpty(detailId)) {
			lanNewsDelailbean.setDelail_id(Long.valueOf(detailId));
			lanNewsFile.setDetail_id(Long.valueOf(detailId));

			// ��ȡ������ϸ
			lanNewsDelailbean = newsService.getNewsDetail(lanNewsDelailbean);
			lanNewsDelailbean.setClicks_ratio(lanNewsDelailbean
					.getClicks_ratio() + 1);

			// �޸ĵ������
			newsService.updateNewsDetail(lanNewsDelailbean);

			// �����־
			NewsRecord record = new NewsRecord();
			record.setDetailId(Long.parseLong(detailId));
			record.setUserName(this.getUser().getUserName());
			record.setStartTime(new Date().getTime());
			recordId = newsService.addRecord(record);

			// ��ȡ����
			if (StringUtils.isNotEmpty(lanNewsDelailbean.getDelail_content())) {
				try {
					conent = FileUtil.readFile(oaNewsFilePath + "/"
							+ lanNewsDelailbean.getDelail_content());
				} catch (Exception e) {
					logger.error(e);
				}
				if (StringUtils.isNotEmpty(conent)) {
					lanNewsDelailbean.setDelail_content(conent);
				} else {
					lanNewsDelailbean.setDelail_content("  ");
				}
			}

			// ��ȡ�����б�
			lanNewsFileList = newsService.getNewsFileList(lanNewsFile);
			if (lanNewsFileList != null && lanNewsFileList.size() > 0) {
				for (NewsFile NewsFile : lanNewsFileList) {
					NewsFile.setNews_file_url(oaNewsFilePath + "/"
							+ NewsFile.getNews_file_url());
				}
				upload_sign = "Y";
			} else {
				upload_sign = "N";
			}
		}
		return "oneNews";
	}

	/**
	 * ��¼���ʱ��
	 * 
	 * @return
	 */
	@JsonResult(field = "recordId")
	public String recordScanTime() {
		NewsRecord record = new NewsRecord();
		record.setId(recordId);
		record.setEndTime(new Date().getTime());
		newsService.recordScanTime(record);
		return JSON;
	}

	@PermissionSearch
	public String searchScanRecord() {

		return "scan";

	}

	/**
	 * ��ת��������ϸ�б�
	 * 
	 * @return
	 */
	@PermissionSearch
	public String getSearchNews() {
		return "getSearchNews";
	}

	/**
	 * MethodsTitle: ��ת��ϸ�б�
	 * @author: xg.chen
	 * @date:2016��12��28�� ����2:29:49
	 * @version 1.0
	 * @return
	 */
	@JsonResult(field = "lanNewsDetailList", include = { "delail_id",
			"delail_title", "detail_date", "delail_operator", "clicks_ratio",
			"css_flag" }, total = "total")
	@PermissionSearch
	public String searchNewsD() {
		NewsTotal NewsTotal = new NewsTotal();
		NewsTotal = this.getSearchInfo(NewsTotal);
		NewsTotal.setStart(getStart());
		NewsTotal.setEnd(getEnd());
		if (StringUtils.isNotEmpty(total_id)) {
			NewsTotal.setTotal_id(Long.valueOf(total_id.replace(" ", "")));
		}
		if (StringUtils.isNotEmpty(total_Name)) {
			NewsTotal.setTotal_name(total_Name);
		}
		
		//���ݵ�¼�˵��ϼ���֯����ȡ������ϸ
		List<Borg> porgList = orgService.getAllParentOrgs(Long.parseLong(this
				.getUser().getOrgId()));
		String[] porgs = new String[porgList.size()];
		int i = 0;
		for (Borg org : porgList) {
			porgs[i] = org.getOrgId().toString();
			i++;
		}
		NewsTotal.setCodes(porgs);
		
		total = newsService.getNewsDetailMoreListCount(NewsTotal);
		if (total > 0) {
			lanNewsDetailList = newsService.getNewsDetailMoreList(NewsTotal);
		}
		return JSON;
	}

	/**
	 * ��ת��������Ŀ��ѯ��ά��
	 * 
	 * @return
	 */

	@JsonResult(field = "lanNewsTotalList", include = { "total_id",
			"total_parent_id", "total_name", "total_title", "total_date",
			"total_code", "total_show", "total_sign", "total_upload_sign" }, total = "total")
	@PermissionSearch
	public String searchNewsTotalList() {
		NewsTotal newsTotal = new NewsTotal();
		newsTotal = this.getSearchInfo(newsTotal);

		if (StringUtils.isNotEmpty(total_Name)) {
			newsTotal.setTotal_name(total_Name);
		}
		newsTotal.setStart(getStart());
		newsTotal.setEnd(getEnd());
		total = newsService.getNewsTotalJsonCount(newsTotal);
		if (total > 0) {
			lanNewsTotalList = newsService.getNewsTotalJsonList(newsTotal);
		}

		return JSON;
	}
	/**
	 * 
	 * @return
	 */
	@JsonResult(field = "lanNewsTotalList", include = { "total_id",
			"total_parent_id", "total_name", "total_title", "total_date",
			"total_code", "total_show", "total_sign", "total_upload_sign" })
	@PermissionSearch
	public String searchNewsTotalListbox() {
		NewsTotal newsTotal = new NewsTotal();
		newsTotal = this.getSearchInfo(newsTotal);

		if (StringUtils.isNotEmpty(total_Name)) {
			newsTotal.setTotal_name(total_Name);
		}
		total = newsService.getNewsTotalJsonCount(newsTotal);
		newsTotal.setStart(getStart());
		newsTotal.setEnd(total);
		List<NewsTotal> newsTotals = new ArrayList<NewsTotal>();
		lanNewsTotalList = new ArrayList<NewsTotal>();
		if (total > 0) {
			newsTotals = newsService.getNewsTotalJsonList(newsTotal);
			for (NewsTotal s : newsTotals) {
				s.setTotal_title(s.getTotal_id() + "," + s.getTotal_show());
				lanNewsTotalList.add(s);
			}
		}

		return JSON;
	}

	/**
	 * ��ת�����Ű�鴴��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String newsTot_add() {
		int i = newsService.getTotalShowCount();
		if (i == 0) {
			totalShow_flag = true;
		} else {
			totalShow_flag = false;
		}
		return "newsTotAdd";
	}

	/**
	 * �����ܵ�
	 * 
	 * @return
	 */
	public String createNewsTotal() {
		String result = blurNewsCode();
		if (!result.equals("")) {
			this.setFailMessage(result);
			return RESULT_MESSAGE;
		}
		NewsTotal lanNewsTotalbean = new NewsTotal();
		lanNewsTotalbean.setTotal_parent_id(Long.valueOf(0));
		lanNewsTotalbean.setTotal_name(totalName);
		if (totalShow != null) {
			lanNewsTotalbean.setTotal_show(totalShow);
		} else {
			lanNewsTotalbean.setTotal_show("N");
		}
		lanNewsTotalbean.setTotal_flag("Y");
		lanNewsTotalbean.setTotal_upload_sign(totalUploadSign);
		lanNewsTotalbean.setTotal_sign(totalSign);
		lanNewsTotalbean.setTotal_code(totalCode);
		Long totalId = newsService.createNewsTotal(lanNewsTotalbean);
		if (totalId == 0l) {
			this.setFailMessage("����ʧ��!");
		} else {
			this.setSuccessMessage("�����ɹ�!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ֻ����ʾ�������Ƿ�ռ��
	 */
	@PermissionSearch
	public String blurNewsCode() {
		NewsTotal lanNewsTotalbean = new NewsTotal();
		String result = "";
		if (StringUtils.isNotEmpty(totalCode)
				&& StringUtils.isNotEmpty(totalCode.trim())) {
			try {
				lanNewsTotalbean.setTotal_code(totalCode);
				int i = newsService.blurNewsCode(lanNewsTotalbean);
				if (i != 0) {
					result = "���������Ѿ���ռ�ã�";
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return result;
	}

	/**
	 * ɾ�������ܵ�
	 * 
	 * @return
	 */
	public String deleteNewsTotal() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		if (StringUtils.isNotEmpty(total_ids)) {
			String[] ls = total_ids.split(",");
			for (int i = 0; i < ls.length; i++) {
				NewsDetail det = new NewsDetail();
				det.setTotal_id(Long.valueOf(ls[i]));
				det.setStart(0);
				det.setEnd(10);
				List<NewsDetail> lists = newsService.getNewsDetailJsonList(det);
				if (lists.size() > 0) {
					this.setFailMessage("ɾ��ʧ��,��ĿΪId:" + ls[i]
							+ "��������ϸ��Ϣ  ����ɾ����ϸ��Ϣ��ɾ����Ŀ��Ϣ����");
					return RESULT_MESSAGE;
				}
			}
			NewsTotal NewsTotal = new NewsTotal();
			NewsTotal.setTotal_ids(ls);
			NewsTotal.setTotal_flag("N");
			if (newsService.updateNewsTotal(NewsTotal) > 0) {
			} else {
				this.setFailMessage("ɾ��ʧ��");
			}
			this.setSuccessMessage("ɾ���ɹ��� ɾ������:" + ls.length);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * MethodsTitle: ������޸�������Ŀ��ϸ
	 * @author: xg.chen
	 * @date:2016��12��27�� ����4:53:25
	 * @version 1.0
	 * @return
	 */
	@PermissionSearch
	public String createNewsdet() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		lanNewsDelailbean = new NewsDetail();
		AllUsers user = this.getUser();
		File savedir = null;
		if (StringUtils.isNotEmpty(delail_content)) {
			savedir = new File(oaNewsFilePath);
			// ���Ŀ¼�����ڣ����½�
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
			String name = DateUtil.getNowDateminStr() + ".txt";
			FileUtil.saveFile(oaNewsFilePath + "/" + name, delail_content,
					false);
			lanNewsDelailbean.setDelail_content(name);
		}
		lanNewsDelailbean.setDelail_title(delailTitle);
		lanNewsDelailbean.setClicks_ratio(0l);
		lanNewsDelailbean.setCss_flag(css_flag);
		lanNewsDelailbean.setDelail_operator(user.getUserName());
		lanNewsDelailbean.setTotal_id(Long.valueOf(totalParentId));
		lanNewsDelailbean.setOrg_name("1");
		lanNewsDelailbean.setOptionOrgs(orgs);
		lanNewsDelailbean.setOptionOrgName(orgName);
		lanNewsDelailbean.setOptionOrgs2(orgs2);
		lanNewsDelailbean.setOptionOrgName2(orgName2);
		
		Long detailId = newsService.createNewsDetail(lanNewsDelailbean);
		if (orgs != null && orgName != null && !orgName.equals("")) {
			if (0l == detailId) {
				this.setFailMessage("����������ϸʧ�ܣ�");
				return RESULT_MESSAGE;
			}
			if (!createFileNews(detailId).equals("")) {
				this.setFailMessage("�������Ÿ���ʧ�ܣ�");
				return RESULT_MESSAGE;
			}
			//�����ʼ���Ҫѭ������
			try {
				if(!StringUtils.isEmpty(orgs)){
					String[] ls=orgs.split(";");
					for (int i = 0; i < ls.length; i++) {
						//���ʼ����ͳɹ����������м�¼���͵�֪ͨ��ϸ�ͷ��͵���֯
						Organ organ = new Organ();
						organ.setDelailId(detailId);
						organ.setOrgId(ls[i]);
						Long id = newsService.createOrgan(organ);
						if (0l == id) {
							this.setFailMessage("����������ϸʧ�ܣ�");
							return RESULT_MESSAGE;
						}
						sendEmail(Long.valueOf(ls[i]) + "", detailId, delailTitle, "gszz",delail_content,upload);
						logger.debug(Long.valueOf(ls[i]) +"---��˾��֯�ʼ����ͳɹ���");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setSuccessMessage("�����ɹ���");
		}
		if (orgs2 != null && orgName2 != null && !orgName2.equals("")) {
			if (!(orgs != null && orgName != null && !orgName.equals(""))) {
				if (0l == detailId) {
					this.setFailMessage("����������ϸʧ�ܣ�");
					return RESULT_MESSAGE;
				}
				if (!createFileNews(detailId).equals("")) {
					this.setFailMessage("�������Ÿ���ʧ�ܣ�");
					return RESULT_MESSAGE;
				}
			}
			//�����ʼ���Ҫѭ������
			try {
				if(!StringUtils.isEmpty(orgs2)){
					String[] ls=orgs2.split(";");
					for (int i = 0; i < ls.length; i++) {
						//���ʼ����ͳɹ����������м�¼���͵�֪ͨ��ϸ�ͷ��͵���֯
						Organ organ = new Organ();
						organ.setDelailId(detailId);
						organ.setOrgId(ls[i]);
						Long id = newsService.createOrgan(organ);
						if (0l == id) {
							this.setFailMessage("����������ϸʧ�ܣ�");
							return RESULT_MESSAGE;
						}
						sendEmail(Long.valueOf(ls[i]) + "", detailId, delailTitle, "jxszz",delail_content,upload);
						logger.debug(Long.valueOf(ls[i]) +"---��������֯�ʼ����ͳɹ���");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setSuccessMessage("�����ɹ���");
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * MethodsTitle: ����ѡ�Ŀɼ���֯����email
	 * @author: xg.chen
	 * @date:2016��12��27�� ����4:52:44
	 * @version 1.0
	 * @param orgId ����ID
	 * @param detailIds ����ID
	 * @param title ����
	 * @param zzjg ��֯��ʶ "gszz"��˾��֯  "jxszz"��������֯
	 * @param content �ļ�����
	 * @param uploadFiles �ļ�����
	 * @return
	 */
	public String sendEmail(String orgId, Long detailIds,String title,String zzjg,String content,File[] uploadFiles) {
		try {
			Vector<Address> vector = new Vector<Address>();
			int flag = 0;
			List<AllUsers> userList = null;
			AllUsers allUser = new AllUsers();
			allUser.setStart(getStart());
			allUser.setEnd(getEnd());
			String orgids = orgService.getFnAllChildStrOrg(orgId);
			if (StringUtils.isNotEmpty(orgids)) {
				allUser.setOrgIds(orgids.split(","));
			}
			if (zzjg.equals("gszz")) {
				total = allUserService.searchAllUsersCount(allUser);
				if (total != 0) {
					userList = allUserService.searchAllUsers_all(allUser);
				} else {
					userList = null;
				}
				for (AllUsers allUsers : userList) {
					if (allUsers.getRtx_LoginId() != null
							&& !allUsers.getRtx_LoginId().equals("")) {
						//allUsers.getRtx_LoginId() + "@chinaxpp.com"
						vector.add(new InternetAddress(allUsers.getRtx_LoginId() + "@chinaxpp.com", "","utf-8"));
						System.out.println(flag);
						flag++;
					}
				}
			}
			if (zzjg.equals("jxszz")) {
				try {
					userList = allUserService.searchKunnur_all(allUser);
				} catch (Exception e) {
					return "";
				}
				for (AllUsers allUsers : userList) {
					if (allUsers.getLoginId() != null
							&& !allUsers.getLoginId().equals("")) {
						//allUsers.getRtx_LoginId() + "@chinaxpp.com"
						//"869095990@qq.com"
						vector.add(new InternetAddress(allUsers.getRtx_LoginId() + "@chinaxpp.com", "", "utf-8"));
						System.out.println(flag);
						flag++;
					}
				}
			}
			Address[] a = new Address[flag];
			for (Address address : vector) {
				a[flag - 1] = address;
				flag--;
			}
			String[] imageFileName = null;
			NewsFile lanNewsFile = new NewsFile();
			lanNewsFile.setDetail_id(Long.valueOf(detailIds));
			lanNewsFileList = newsService.getNewsFileList(lanNewsFile);
			if (lanNewsFileList != null && lanNewsFileList.size() > 0) {
				imageFileName = new String[lanNewsFileList.size()];
				for (NewsFile newsFile : lanNewsFileList) {
					imageFileName[flag] = newsFile.getNews_file_name();
					flag++;
				}
			}
			if (a.length > 0 && imageFileName!=null) {
				//���͵����ʼ�==>������
				/*PoolSend send = new PoolSend();
				send.send(new SendHtmlEmail(a, imageFileName, uploadFiles, title, content));
				send.close();*/
				SendMail.sendHtmlEmail(a, imageFileName, upload, title, content);
			}else {
				//���͵����ʼ�==>�޸���
				PoolSend send = new PoolSend();
				send.send(new SendHtmlEmailNoFile(a, title, content));
				send.close();
				//SendMail.sendHtmlEmailNoFile(a, title, content);
			}
			
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "����";
	}
	/**
	 * �����ļ�
	 * @param detailId
	 * @return
	 */
	public String createFileNews(Long detailId) {
		NewsFile newsFilebean = new NewsFile();
		String imageFileName = "";
		if (upload != null && upload.length > 0) {
			for (int i = 0; i < upload.length; i++) {
				if (uploadFileName[i] != null && uploadFileName[i].length() > 0) {
					imageFileName = UUID.randomUUID()
							+ FileUtil.getFileExtention(uploadFileName[i]);
					File savedir = new File(oaNewsFilePath);
					// ���Ŀ¼�����ڣ����½�
					if (!savedir.exists()) {
						savedir.mkdirs();
					}
					File imageFile = new File(oaNewsFilePath + "/"
							+ imageFileName);
					FileUtil.saveAsFile(upload[i], imageFile);

					newsFilebean.setDetail_id(detailId);
					newsFilebean.setNews_file_name(uploadFileName[i]);
					newsFilebean.setNews_file_url(imageFileName);

					Long news_file_id = newsService
							.createNewsFile(newsFilebean);
					if (0l == news_file_id) {
						return "����";
					}
				}
			}
		}
		return "";
	}

	/**
	 * MethodsTitle: ��ת��������Ŀ��ѯ��ά��  ������Ŀά���б�
	 * @author: xg.chen
	 * @date:2016��12��28�� ����2:19:16
	 * @version 1.0
	 * @return
	 */

	@JsonResult(field = "lanNewsDetailList", include = { "delail_id",
			"total_id", "delail_title", "delail_operator", "detail_date",
			"clicks_ratio", "total_name", "optionOrgName" }, total = "total")
	@PermissionSearch
	public String searchNewsDetailList() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		NewsDetail newsDetail = new NewsDetail();
		/* newsDetail = this.getSearchInfo(newsDetail); */

		// ������Ŀ����
		if (StringUtils.isNotEmpty(total_Name)) {
			newsDetail.setTotal_name(total_Name);
		}

		// ����
		if (StringUtils.isNotEmpty(delail_title)) {
			newsDetail.setDelail_title(delail_title);
		}
		newsDetail.setStart(getStart());
		newsDetail.setEnd(getEnd());
		
		//���ݵ�¼�˵��ϼ���֯����ȡ������ϸ
		List<Borg> porgList = orgService.getAllParentOrgs(Long.parseLong(this
				.getUser().getOrgId()));
		String[] porgs = new String[porgList.size()];
		int i = 0;
		for (Borg org : porgList) {
			porgs[i] = org.getOrgId().toString();
			i++;
		}
		//��admin��½��ʱ����Բ鿴��ȫ����������ϸ
		if(porgs[0].equals("50919")){
			String[] porg = new String[0];
			newsDetail.setCodes(porg);
		} else {
			newsDetail.setCodes(porgs);
		}
		
		total = newsService.getNewsDetailJsonCount(newsDetail);
		if (total > 0) {
			lanNewsDetailList = newsService.getNewsDetailJsonList(newsDetail);
		}
		return JSON;
	}

	/**
	 * ��Ŀ��ϸɾ��
	 * 
	 * @return
	 */
	public String deleteNewsDetail() {
		NewsDetail newsDetail = new NewsDetail();
		if (StringUtils.isNotEmpty(delail_ids)) {
			String[] ls = delail_ids.split(",");
			String[] codes = new String[ls.length];
			for (int i = 0; i < ls.length; i++) {
				if (!ls[i].trim().equals("")) {
					codes[i] = ls[i];
				}
			}
			newsDetail.setCodes(codes);
			if (newsService.deleteNewsDetail(newsDetail) > 0) {
				this.setSuccessMessage("�����ɹ�");
				// ���cache
				/* removeCache(); */
			} else {
				this.setFailMessage("����ʧ��");
			}
		} else {
			this.setFailMessage("����ʧ��");
		}

		return RESULT_MESSAGE;
	}

	/*
	 * private void removeCache() { try {
	 * memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_NEWS); }
	 * catch (Exception e) {
	 * 
	 * } }
	 */
	/**
	 * ��ת����Ŀ��ϸ����
	 * 
	 * @return
	 */
	@PermissionSearch
	public String newsDetail_add() {
		return "newsDetail_add";
	}

	/**
	 * ��ת����Ŀ��ϸ������ά��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchNewsDetail() {
		return "searchNewsDetail";
	}

	/**
	 * ��ת����Ŀ��ѯ��ά��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchNewsTotal() {
		return "searchNewsTotal";
	}

	/**
	 * ��ת���޸�������Ŀ��ϸҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateNewsDetailPre() {
		lanNewsDelailbean = new NewsDetail();
		NewsFile lanNewsFile = new NewsFile();
		lanNewsFileList = new ArrayList<NewsFile>();
		String conent = "";

		if (StringUtils.isNotEmpty(detailId)) {
			lanNewsDelailbean.setDelail_id(Long.valueOf(detailId));
			lanNewsFile.setDetail_id(Long.valueOf(detailId));

			// ��ȡ������ϸ
			lanNewsDelailbean = newsService.getNewsDetail(lanNewsDelailbean);
			filename = lanNewsDelailbean.getDelail_content();
			totalShow = lanNewsDelailbean.getTotal_show();
			delailTitle = lanNewsDelailbean.getDelail_title();
			orgId = lanNewsDelailbean.getOptionOrg();
			orgName = lanNewsDelailbean.getOptionOrgName();
			NewsTotal newsTotal = new NewsTotal();
			newsTotal.setTotal_id(lanNewsDelailbean.getTotal_id());
			newsTotal = newsService.getNewsTotal(newsTotal);
			totalParentTotal = newsTotal.getTotal_id() + ","
					+ newsTotal.getTotal_show();

			// ��ȡ����
			if (StringUtils.isNotEmpty(lanNewsDelailbean.getDelail_content())) {
				try {
					conent = FileUtil.readFile(oaNewsFilePath + "/"
							+ lanNewsDelailbean.getDelail_content());
				} catch (Exception e) {
					logger.error(e);
				}

				if (StringUtils.isNotEmpty(conent)) {
					lanNewsDelailbean.setDelail_content(conent);

				} else {
					lanNewsDelailbean.setDelail_content("  ");
				}
			}

			// ��ȡ�����б�
			lanNewsFileList = newsService.getNewsFileList(lanNewsFile);
			if (lanNewsFileList != null && lanNewsFileList.size() > 0) {
				for (NewsFile NewsFile : lanNewsFileList) {
					NewsFile.setNews_file_url(oaNewsFilePath + "/"
							+ NewsFile.getNews_file_url());
				}
			}
		}

		return "updateNewsDetailPre";
	}

	/**
	 * MethodsTitle: �޸�������Ŀ��ϸ
	 * @author: xg.chen
	 * @date:2017��2��17�� ����2:48:58
	 * @version 1.1 ɾ����������bug�޸�
	 * @return
	 */
	public String updateNewsDetail() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		String result = blurNewsCode();
		if (!result.equals("")) {
			this.setFailMessage(result);
			return RESULT_MESSAGE;
		}
		lanNewsDelailbean = new NewsDetail();
		NewsFile newsFilebean = new NewsFile();
		lanNewsFileList = new ArrayList<NewsFile>();
		String imageFileName = "";

		if (StringUtils.isNotEmpty(detailId)) {
			lanNewsDelailbean.setDelail_id(Long.valueOf(detailId));
			// ����
			lanNewsDelailbean.setDelail_title(delailTitle);
			// lanNewsDelailbean.setTotal_id(Long.valueOf(totalParentId));
			lanNewsDelailbean.setTotal_id(Long.valueOf(totalParentId));
			// �Ƿ���
			lanNewsDelailbean.setCss_flag(css_flag);
			lanNewsDelailbean.setOptionOrgs(orgs);
			lanNewsDelailbean.setOptionOrgName(orgName);
			// ��ȡ����
			if (StringUtils.isNotEmpty(delail_content)
					&& StringUtils.isNotEmpty(delail_content)) {
				File savedir = new File(oaNewsFilePath);
				// ���Ŀ¼�����ڣ����½�
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				if (!FileUtil.saveFile(oaNewsFilePath + "/" + filename,
						delail_content, false)) {
					this.setFailMessage("�޸���ϸʧ��");
					return RESULT_MESSAGE;
				}
			}

			if (newsService.updateNewsDetail(lanNewsDelailbean) <= 0) {// �޸�������ϸ
				this.setFailMessage("�޸���ϸʧ��");
				return RESULT_MESSAGE;
			}
			newsFilebean.setNews_del_flag("N");
			newsFilebean.setDetail_id(Long.valueOf(detailId));
			newsFilebean.setCodes(fileId);

			if (newsService.updateNewsFile(newsFilebean) >= 0) { // ��ӻ�ɾ������
				if (upload != null && upload.length > 0) { // ��������
					for (int i = 0; i < upload.length; i++) {
						if (uploadFileName[i].length() > 0
								&& uploadFileName[i] != null) {//�ļ���Ϊ�յĻ���Ϊ����
							imageFileName = UUID.randomUUID()
									+ FileUtil
											.getFileExtention(uploadFileName[i]);
							File savedir = new File(oaNewsFilePath);
							// ���Ŀ¼�����ڣ����½�
							if (!savedir.exists()) {
								savedir.mkdirs();
							}
							File imageFile = new File(oaNewsFilePath + "/"
									+ imageFileName);
							FileUtil.saveAsFile(upload[i], imageFile);

							newsFilebean.setNews_file_name(uploadFileName[i]);
							newsFilebean.setNews_file_url(imageFileName);
							Long news_file_id = newsService.createNewsFile(newsFilebean);
							if (0l == news_file_id) {
								this.setFailMessage("�������Ÿ���ʧ�ܣ�");
								return RESULT_MESSAGE;
							}
						} /*else {//�������ɾ������
							Long news_file_id = newsService.deleteNewsFile(newsFilebean);
							if (0l == news_file_id) {
								this.setFailMessage("���Ÿ���ɾ��ʧ�ܣ�");
								return RESULT_MESSAGE;
							}
						}*/
					}
				}
			}
		}
		this.setSuccessMessage("�޸ĳɹ���");

		// ���cache

		return RESULT_MESSAGE;
	}
	/**
	 * MethodsTitle: ����ɾ������
	 * @author: xg.chen
	 * @date:2017��2��20�� ����9:27:26
	 * @version 1.0
	 * @return
	 */
	public String deleteNewsFile(){
		this.setSuccessMessage("");
		this.setFailMessage("");
		lanNewsDelailbean = new NewsDetail();
		NewsFile newsFilebean = new NewsFile();
		if(StringUtils.isNotEmpty(detailId)){
			newsFilebean.setDetail_id(Long.valueOf(detailId));
			Long news_file_id = newsService.deleteNewsFile(newsFilebean);
			if (0l == news_file_id) {
				this.setFailMessage("���Ÿ���ɾ��ʧ�ܣ�");
			} else {
				this.setSuccessMessage("true");
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * �޸���Ŀ��Ϣ
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateNewsTotalPre() {
		if (StringUtils.isNotEmpty(totalId)) {
			NewsTotal NewsTotal = new NewsTotal();
			NewsTotal.setTotal_id(Long.valueOf(totalId));
			NewsTotal = newsService.getNewsTotal(NewsTotal);
			int i = newsService.getTotalShowCount();
			if (i == 0) {
				totalShow_flag = true;
			} else {
				if (NewsTotal.getTotal_show().equals("Y")) {
					totalShow_flag = true;
				} else {
					totalShow_flag = false;
				}
			}

			if (NewsTotal != null) {
				// ��ȡ��Ŀ����
				totalName = NewsTotal.getTotal_name();
				totalUploadSign = NewsTotal.getTotal_upload_sign();
				totalShow = NewsTotal.getTotal_show();
				totalSign = NewsTotal.getTotal_sign();
				totalCode = NewsTotal.getTotal_code();

			}
		}
		return "updateNewsTotalPre";
	}

	/**
	 * �޸������ܵ�
	 * 
	 * @return
	 */
	public String updateNewsTotal() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		NewsTotal NewsTotal = new NewsTotal();
		String[] ls = totalId.toString().split(",");
		NewsTotal.setTotal_ids(ls);
		NewsTotal.setTotal_code(totalCode);
		NewsTotal.setTotal_name(totalName);
		if (totalShow != null) {
			NewsTotal.setTotal_show(totalShow);
		} else {
			NewsTotal.setTotal_show("N");
		}
		NewsTotal.setTotal_sign(totalSign);
		NewsTotal.setTotal_upload_sign(totalUploadSign);

		if (newsService.updateNewsTotal(NewsTotal) > 0) {
			this.setSuccessMessage("�޸ĳɹ���");
		} else {
			this.setFailMessage("�޸�ʧ��");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * ��ת��Ⱥ������ҳ��
	 * 
	 * @return
	 */
	public String toPayOff() {
		return "toPayOff";
	}

	/**
	 * ��ȡ������Ϣ
	 */
	public String SendPayOff() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		StringResult stringResult = getPayOffList(fileContent);
		if ("success".equals(stringResult.getCode())) {
			this.setSuccessMessage("����ɹ���");
		} else {
			this.setFailMessage("����ʧ�ܣ�" + stringResult.getResult());
		}
		return RESULT_MESSAGE;
	}
	
	/**
	 * ��ù�����Ϣ
	 * @param fileContent
	 * @return
	 */
	public StringResult getPayOffList(File fileContent) {
		StringResult result = new StringResult();
		List<Salary> salaryList = new ArrayList<Salary>();
		org.apache.poi.ss.usermodel.Workbook workbook = null;
		org.apache.poi.ss.usermodel.Sheet sheet = null;
		try {
			System.out.println(fileContent);
			workbook = new HSSFWorkbook(new FileInputStream(fileContent));
		} catch (FileNotFoundException e) {
			result.setResult("�����ļ�������");
			throw new BaseException(System.currentTimeMillis() + "", null,
					"ActivityPlanService", "saveMarketDetailItem", "439",
					"�����ļ�������", null);
		} catch (UnsupportedEncodingException e) {
			result.setResult("�����ļ�����");
			throw new BaseException(System.currentTimeMillis() + "", null,
					"ActivityPlanService", "saveMarketDetailItem", "441",
					"�����ļ�����", null);
		} catch (IOException e) {
			result.setResult("�����ļ��쳣");
			e.printStackTrace();
		}
		try {
			String[] FileHeaders = new String[29];
			FileHeaders[0] = "����";
			FileHeaders[1] = "�����·�";
			FileHeaders[2] = "ʵ�ʳ�������";
			FileHeaders[3] = "����н��";
			FileHeaders[4] = "�Ӱ๤��";
			FileHeaders[5] = "ְ��Ӹ�";
			FileHeaders[6] = "��λ����";
			FileHeaders[7] = "��Ч����";
			FileHeaders[8] = "���ʲ���";
			FileHeaders[9] = "���ʺϼ�";
			FileHeaders[10] = "�绰�Ѳ���";
			FileHeaders[11] = "ȫ�ڽ���";
			FileHeaders[12] = "���佱";
			FileHeaders[13] = "���²���";
			FileHeaders[14] = "��������";
			FileHeaders[15] = "�����ϼ�";
			FileHeaders[16] = "Ӧ��н��";
			FileHeaders[17] = "���ϱ���";
			FileHeaders[18] = "ʧҵ��";
			FileHeaders[19] = "ҽ�ƽ�";
			FileHeaders[20] = "ס��������";
			FileHeaders[21] = "��˰����";
			FileHeaders[22] = "����ˮ��";
			FileHeaders[23] = "���ÿ۳�";
			FileHeaders[24] = "�����";
			FileHeaders[25] = "Ӧ���ϼ�";
			FileHeaders[26] = "ʵ��н��";
			FileHeaders[27] = "����";
			FileHeaders[28] = "�ʺ���";
			sheet = workbook.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			if (rows == 0) {
				result.setCode("error");
				result.setResult("�����excel�ļ�Ϊ�գ�");
				return result;
			} else if (sheet.getRow(0).getLastCellNum() != FileHeaders.length) {
				result.setCode("error");
				result.setResult("�����excel�ļ������������ļ�������һ��!");
				return result;
			} else {
				StringBuilder headResult = new StringBuilder();
				for (int i = 0; i < FileHeaders.length; i++) {
					if (!FileHeaders[i].equals(getValue(sheet.getRow(0)
							.getCell(i)))) {
						if (headResult.length() > 0) {
							headResult.append("</br>");
						}
						headResult.append("��").append(i + 1).append("��")
								.append(getValue(sheet.getRow(0).getCell(i)))
								.append("��ģ����").append(FileHeaders[i])
								.append("��һ��");
						System.out.println(headResult + "");
					}
				}
				if (headResult.length() > 0) {
					result.setCode("error");
					result.setResult(headResult.toString());
					return result;
				}
				StringBuilder errorMsgContent = new StringBuilder();
				for (int i = 1; i <= rows; i++) {
					Salary salary = new Salary();

					String name = ""; // ����
					String sendMonth = ""; // �����·�
					String realworkingdays = "";// ʵ�ʳ�������
					String basicsalary = "";// ����н��
					String overtimepay = "";// �Ӱ๤��
					String positionplus = "";// ְ��Ӹ�
					String postallowance = "";// ��λ����
					String meritpay = "";// ��Ч����
					String wagereplacement = "";// ���ʲ���
					String totalwages = "";// ���ʺϼ�
					String telephonefeesubsidy = ""; // �绰�Ѳ���
					String attendancebonus = "";// ȫ�ڽ���
					String serviceAward = "";// ���佱
					String hotSubsidy = "";// ���²���
					String others = "";// ��������
					String totalsubsidy = "";// �����ϼ�
					String shouldpay = "";// Ӧ��н��
					String oldageinsurance = "";// ���ϱ���
					String unemploymentgold = "";// ʧҵ��
					String medicalgold = "";// ҽ�ƽ�
					String housingProvidentFund = "";// ס��������
					String individualincometax = "";// ��˰����
					String rentandwater = "";// ����ˮ��
					String expensededuction = "";// ���ÿ۳�
					String tradeunionfee = "";// �����
					String totalreduction = "";// Ӧ���ϼ�
					String realwages = "";// ʵ��н��
					String mail = "";// ����
					String greeting ="";
					name = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 29));
					sendMonth = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 28));
					realworkingdays = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 27));
					basicsalary = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 26));
					overtimepay = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 25));
					positionplus = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 24));
					postallowance = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 23));
					meritpay = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 22));
					wagereplacement = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 21));
					totalwages = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 20));
					telephonefeesubsidy = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 19));
					attendancebonus = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 18));
					serviceAward = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 17));
					hotSubsidy = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 16));
					others = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 15));
					totalsubsidy = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 14));
					shouldpay = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 13));
					oldageinsurance = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 12));
					unemploymentgold = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 11));
					medicalgold = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 10));
					housingProvidentFund = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 9));
					individualincometax = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 8));
					rentandwater = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 7));
					expensededuction = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 6));
					tradeunionfee = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 5));
					totalreduction = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 4));
					realwages = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 3));
					mail = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 2));
					greeting = getValue(sheet.getRow(i).getCell(
							FileHeaders.length - 1));
					if (StringUtils.isEmpty(name)) {
						if (errorMsgContent.length() > 0) {
							errorMsgContent.append("</br>");
						}
						errorMsgContent.append("��" + (i + 1) + "��,����������д����ȷ��!");
					}
					if (StringUtils.isEmpty(sendMonth)) {
						if (errorMsgContent.length() > 0) {
							errorMsgContent.append("</br>");
						}
						errorMsgContent.append("��" + (i + 1)
								+ "��,�����·ݱ�����д����ȷ��!");
					}
					if (StringUtils.isEmpty(realworkingdays)) {
						if (errorMsgContent.length() > 0) {
							errorMsgContent.append("</br>");
						}
						errorMsgContent.append("��" + (i + 1)
								+ "��,ʵ�ʳ�������������д����ȷ��!");
					}
					if (StringUtils.isEmpty(basicsalary)) {
						basicsalary = 0 + "";
					}
					if (StringUtils.isEmpty(overtimepay)) {
						overtimepay = 0 + "";
					}
					if (StringUtils.isEmpty(positionplus)) {
						positionplus = 0 + "";
					}
					if (StringUtils.isEmpty(postallowance)) {
						postallowance = 0 + "";
					}
					if (StringUtils.isEmpty(meritpay)) {
						meritpay = 0 + "";
					}
					if (StringUtils.isEmpty(wagereplacement)) {
						wagereplacement = 0 + "";
					}
					if (StringUtils.isEmpty(totalwages)) {
						totalwages = 0 + "";
					}
					if (StringUtils.isEmpty(telephonefeesubsidy)) {
						telephonefeesubsidy = 0 + "";
					}
					if (StringUtils.isEmpty(attendancebonus)) {
						attendancebonus = 0 + "";
					}
					if (StringUtils.isEmpty(serviceAward)) {
						serviceAward = 0 + "";
					}
					if (StringUtils.isEmpty(hotSubsidy)) {
						hotSubsidy = 0 + "";
					}
					if (StringUtils.isEmpty(others)) {
						others = 0 + "";
					}
					if (StringUtils.isEmpty(totalsubsidy)) {
						totalsubsidy = 0 + "";
					}
					if (StringUtils.isEmpty(shouldpay)) {
						shouldpay = 0 + "";
					}
					if (StringUtils.isEmpty(oldageinsurance)) {
						oldageinsurance = 0 + "";
					}
					if (StringUtils.isEmpty(unemploymentgold)) {
						unemploymentgold = 0 + "";
					}
					if (StringUtils.isEmpty(medicalgold)) {
						medicalgold = 0 + "";
					}
					if (StringUtils.isEmpty(housingProvidentFund)) {
						housingProvidentFund = 0 + "";
					}
					if (StringUtils.isEmpty(individualincometax)) {
						individualincometax = 0 + "";
					}
					if (StringUtils.isEmpty(rentandwater)) {
						rentandwater = 0 + "";
					}
					if (StringUtils.isEmpty(expensededuction)) {
						expensededuction = 0 + "";
					}
					if (StringUtils.isEmpty(tradeunionfee)) {
						tradeunionfee = 0 + "";
					}
					if (StringUtils.isEmpty(totalreduction)) {
						totalreduction = 0 + "";
					}
					if (StringUtils.isEmpty(realwages)) {
						realwages = 0 + "";
					}
					if (StringUtils.isEmpty(mail)) {
						if (errorMsgContent.length() > 0) {
							errorMsgContent.append("</br>");
						}
						errorMsgContent.append("��" + (i + 1) + "��,���������д����ȷ��!");
					}
					if (StringUtils.isEmpty(greeting)) {
						greeting = "ף��������죡";
					}

					if (errorMsgContent.length() == 0) {
						salary.setName(name);
						salary.setSendMonth(sendMonth);
						salary.setRealworkingdays(realworkingdays);
						salary.setBasicsalary(basicsalary);
						salary.setOvertimepay(overtimepay);
						salary.setPositionplus(positionplus);
						salary.setPostallowance(postallowance);
						salary.setMeritpay(meritpay);
						salary.setWagereplacement(wagereplacement);
						salary.setTotalwages(totalwages);
						salary.setTelephonefeesubsidy(telephonefeesubsidy);
						salary.setAttendancebonus(attendancebonus);
						salary.setServiceAward(serviceAward);
						salary.setHotSubsidy(hotSubsidy);
						salary.setOthers(others);
						salary.setTotalsubsidy(totalsubsidy);
						salary.setShouldpay(shouldpay);
						salary.setOldageinsurance(oldageinsurance);
						salary.setUnemploymentgold(unemploymentgold);
						salary.setMedicalgold(medicalgold);
						salary.setHousingProvidentFund(housingProvidentFund);
						salary.setIndividualincometax(individualincometax);
						salary.setRentandwater(rentandwater);
						salary.setExpensededuction(expensededuction);
						salary.setTradeunionfee(tradeunionfee);
						salary.setTotalreduction(totalreduction);
						salary.setRealwages(realwages);
						salary.setMail(mail);
						salary.setGreeting(greeting);
						salaryList.add(salary);
					}
				}
				if (errorMsgContent.length() > 0) {
					result.setCode("error");
					result.setResult(errorMsgContent.toString());
					return result;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result.setCode("error");
			result.setResult("����ʧ��!");
			return result;
		}
		// result = getSAPStock(salaryList);
		try {
			for (Salary salary : salaryList) {
				try{
				ToSendEmailOfSalary.SendEmailForSalary(salary);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result.setCode("error");
			result.setResult("ϵͳ����������ϵ����Ա��");
			return result;
		}
		result.setCode("success");
		result.setResult("���ͳɹ�!");
		System.out.println(salaryList);
		return result;
	}

	private String getValue(Cell cell) {
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(cell.getStringCellValue().replace("\n", "")
					.trim());
		} else {
			return "";
		}

	}

	/**
	 * @return
	 */
	public String PayOffDownload() {
		setSuccessMessage("");
		setFailMessage("");
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// ȡ�������
			os = response.getOutputStream();
			// ��������
			response.reset();
			// �趨����ļ�ͷ
			String fileName = "����Ⱥ��(ģ��).csv";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// �����������
			response.setContentType("application/msexcel");
			// ����excel�ļ�
			wbook = Workbook.createWorkbook(os);

			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

			// WritableSheet.setColumnView(int i,int width)
			// ������ָ����i+1�еĿ�ȣ����磺
			// ����һ�еĿ����Ϊ30
			// sheet.setColumnView(0,30)
			// wsheet.setRowView(0,10)
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// ���õ�Ԫ�񱳾���ɫ
			// cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// ���������ʽ
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// ���þ���
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// ���ñ��߿�
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			WritableSheet wsheet = wbook.createSheet("����Ⱥ��ģ��", 0);

			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 15);
			wsheet.setColumnView(1, 15);
			wsheet.setColumnView(2, 15);
			wsheet.setColumnView(3, 15);
			wsheet.setColumnView(4, 15);
			wsheet.setColumnView(5, 15);
			wsheet.setColumnView(6, 15);
			wsheet.setColumnView(7, 15);
			wsheet.setColumnView(8, 15);
			wsheet.setColumnView(9, 15);
			wsheet.setColumnView(10, 15);
			wsheet.setColumnView(11, 15);
			wsheet.setColumnView(12, 15);
			wsheet.setColumnView(13, 15);
			wsheet.setColumnView(14, 15);
			wsheet.setColumnView(15, 15);
			wsheet.setColumnView(16, 15);
			wsheet.setColumnView(17, 15);
			wsheet.setColumnView(18, 15);
			wsheet.setColumnView(19, 15);
			wsheet.setColumnView(20, 15);
			wsheet.setColumnView(21, 15);
			wsheet.setColumnView(22, 15);
			wsheet.setColumnView(23, 15);
			wsheet.setColumnView(24, 15);
			wsheet.setColumnView(25, 15);
			wsheet.setColumnView(26, 15);
			wsheet.setColumnView(27, 15);
			wsheet.setColumnView(28, 15);
			Label label_0 = new Label(0, 0, "����");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "�����·�");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);

			Label label_2 = new Label(2, 0, "ʵ�ʳ�������");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);

			Label label_3 = new Label(3, 0, "����н��");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);

			Label label_4 = new Label(4, 0, "�Ӱ๤��");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);

			Label label_5 = new Label(5, 0, "ְ��Ӹ�");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);

			Label label_6 = new Label(6, 0, "��λ����");
			label_6.setCellFormat(cellFormat_top);
			wsheet.addCell(label_6);

			Label label_7 = new Label(7, 0, "��Ч����");
			label_7.setCellFormat(cellFormat_top);
			wsheet.addCell(label_7);

			Label label_8 = new Label(8, 0, "���ʲ���");
			label_8.setCellFormat(cellFormat_top);
			wsheet.addCell(label_8);

			Label label_9 = new Label(9, 0, "���ʺϼ�");
			label_9.setCellFormat(cellFormat_top);
			wsheet.addCell(label_9);

			Label label_10 = new Label(10, 0, "�绰�Ѳ���");
			label_10.setCellFormat(cellFormat_top);
			wsheet.addCell(label_10);

			Label label_11 = new Label(11, 0, "ȫ�ڽ���");
			label_11.setCellFormat(cellFormat_top);
			wsheet.addCell(label_11);

			Label label_12 = new Label(12, 0, "���佱");
			label_12.setCellFormat(cellFormat_top);
			wsheet.addCell(label_12);

			Label label_13 = new Label(13, 0, "���²���");
			label_13.setCellFormat(cellFormat_top);
			wsheet.addCell(label_13);

			Label label_14 = new Label(14, 0, "��������");
			label_14.setCellFormat(cellFormat_top);
			wsheet.addCell(label_14);

			Label label_15 = new Label(15, 0, "�����ϼ�");
			label_15.setCellFormat(cellFormat_top);
			wsheet.addCell(label_15);

			Label label_16 = new Label(16, 0, "Ӧ��н��");
			label_16.setCellFormat(cellFormat_top);
			wsheet.addCell(label_16);

			Label label_17 = new Label(17, 0, "���ϱ���");
			label_17.setCellFormat(cellFormat_top);
			wsheet.addCell(label_17);

			Label label_18 = new Label(18, 0, "ʧҵ��");
			label_18.setCellFormat(cellFormat_top);
			wsheet.addCell(label_18);

			Label label_19 = new Label(19, 0, "ҽ�ƽ�");
			label_19.setCellFormat(cellFormat_top);
			wsheet.addCell(label_19);

			Label label_20 = new Label(20, 0, "ס��������");
			label_20.setCellFormat(cellFormat_top);
			wsheet.addCell(label_20);

			Label label_21 = new Label(21, 0, "��˰����");
			label_21.setCellFormat(cellFormat_top);
			wsheet.addCell(label_21);

			Label label_22 = new Label(22, 0, "����ˮ��");
			label_22.setCellFormat(cellFormat_top);
			wsheet.addCell(label_22);

			Label label_23 = new Label(23, 0, "���ÿ۳�");
			label_23.setCellFormat(cellFormat_top);
			wsheet.addCell(label_23);

			Label label_24 = new Label(24, 0, "�����");
			label_24.setCellFormat(cellFormat_top);
			wsheet.addCell(label_24);

			Label label_25 = new Label(25, 0, "Ӧ���ϼ�");
			label_25.setCellFormat(cellFormat_top);
			wsheet.addCell(label_25);

			Label label_26 = new Label(26, 0, "ʵ��н��");
			label_26.setCellFormat(cellFormat_top);
			wsheet.addCell(label_26);

			Label label_27 = new Label(27, 0, "����");
			label_27.setCellFormat(cellFormat_top);
			wsheet.addCell(label_27);
			
			Label label_28 = new Label(28, 0, "�ʺ���");
			label_28.setCellFormat(cellFormat_top);
			wsheet.addCell(label_28);
			wbook.write();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error(e);
				}
				os = null;
			}
		}

		return "resultMessage";
	}

	public List<NewsTotal> getLanNewsTotalList() {
		return lanNewsTotalList;
	}

	public void setLanNewsTotalList(List<NewsTotal> lanNewsTotalList) {
		this.lanNewsTotalList = lanNewsTotalList;
	}

	public List<NewsDetail> getLanNewsDetailList() {
		return lanNewsDetailList;
	}

	public void setLanNewsDetailList(List<NewsDetail> lanNewsDetailList) {
		this.lanNewsDetailList = lanNewsDetailList;
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<NewsTotal> getNewsTypelist() {
		return newsTypelist;
	}

	public void setNewsTypelist(List<NewsTotal> newsTypelist) {
		this.newsTypelist = newsTypelist;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getUpload_sign() {
		return upload_sign;
	}

	public void setUpload_sign(String upload_sign) {
		this.upload_sign = upload_sign;
	}

	public NewsDetail getLanNewsDelailbean() {
		return lanNewsDelailbean;
	}

	public void setLanNewsDelailbean(NewsDetail lanNewsDelailbean) {
		this.lanNewsDelailbean = lanNewsDelailbean;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getTotalParentId() {
		return totalParentId;
	}

	public void setTotalParentId(String totalParentId) {
		this.totalParentId = totalParentId;
	}

	public String getTotalName() {
		return totalName;
	}

	public void setTotalName(String totalName) {
		this.totalName = totalName;
	}

	public String getTotalUploadSign() {
		return totalUploadSign;
	}

	public void setTotalUploadSign(String totalUploadSign) {
		this.totalUploadSign = totalUploadSign;
	}

	public String getTotalShow() {
		return totalShow;
	}

	public void setTotalShow(String totalShow) {
		this.totalShow = totalShow;
	}

	public String getTotalSign() {
		return totalSign;
	}

	public void setTotalSign(String totalSign) {
		this.totalSign = totalSign;
	}

	public String getTotalCode() {
		return totalCode;
	}

	public void setTotalCode(String totalCode) {
		this.totalCode = totalCode;
	}

	public String getTotal_Name() {
		return total_Name;
	}

	public void setTotal_Name(String total_Name) {
		this.total_Name = total_Name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getDelail_title() {
		return delail_title;
	}

	public void setDelail_title(String delail_title) {
		this.delail_title = delail_title;
	}

	public String getDelail_ids() {
		return delail_ids;
	}

	public void setDelail_ids(String delail_ids) {
		this.delail_ids = delail_ids;
	}

	public String getTotal_id() {
		return total_id;
	}

	public void setTotal_id(String total_id) {
		this.total_id = total_id;
	}

	public String getDelailTitle() {
		return delailTitle;
	}

	public void setDelailTitle(String delailTitle) {
		this.delailTitle = delailTitle;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDelail_content() {
		return delail_content;
	}

	public void setDelail_content(String delail_content) {
		this.delail_content = delail_content;
	}

	public String getCss_flag() {
		return css_flag;
	}

	public void setCss_flag(String css_flag) {
		this.css_flag = css_flag;
	}

	public String getTotalId() {
		return totalId;
	}

	public void setTotalId(String totalId) {
		this.totalId = totalId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String[] getFileId() {
		return fileId;
	}

	public void setFileId(String[] fileId) {
		this.fileId = fileId;
	}

	public String getOaNewsFilePath() {
		return oaNewsFilePath;
	}

	public void setOaNewsFilePath(String oaNewsFilePath) {
		this.oaNewsFilePath = oaNewsFilePath;
	}

	public List<NewsFile> getLanNewsFileList() {
		return lanNewsFileList;
	}

	public void setLanNewsFileList(List<NewsFile> lanNewsFileList) {
		this.lanNewsFileList = lanNewsFileList;
	}

	public String getTotal_ids() {
		return total_ids;
	}

	public void setTotal_ids(String total_ids) {
		this.total_ids = total_ids;
	}

	public boolean isTotalShow_flag() {
		return totalShow_flag;
	}

	public void setTotalShow_flag(boolean totalShow_flag) {
		this.totalShow_flag = totalShow_flag;
	}

	public String getTotalParentTotal() {
		return totalParentTotal;
	}

	public void setTotalParentTotal(String totalParentTotal) {
		this.totalParentTotal = totalParentTotal;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public List<NewsTotal> getStatList() {
		return statList;
	}

	public void setStatList(List<NewsTotal> statList) {
		this.statList = statList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getOrgId1() {
		return orgId1;
	}

	public void setOrgId1(Long orgId1) {
		this.orgId1 = orgId1;
	}

	public String getOrgName1() {
		return orgName1;
	}

	public void setOrgName1(String orgName1) {
		this.orgName1 = orgName1;
	}

	public Long getOrgId2() {
		return orgId2;
	}

	public void setOrgId2(Long orgId2) {
		this.orgId2 = orgId2;
	}

	public String getOrgName2() {
		return orgName2;
	}

	public void setOrgName2(String orgName2) {
		this.orgName2 = orgName2;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public File getFileContent() {
		return fileContent;
	}

	public void setFileContent(File fileContent) {
		this.fileContent = fileContent;
	}

	public Long getDelailId() {
		return delailId;
	}

	public void setDelailId(Long delailId) {
		this.delailId = delailId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	
}
