package com.kintiger.platform.boform.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.crystaldecisions.sdk.framework.CrystalEnterprise;
import com.crystaldecisions.sdk.framework.IEnterpriseSession;
import com.crystaldecisions.sdk.framework.ISessionMgr;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.boform.pojo.QueryParameter;
import com.kintiger.platform.boform.pojo.ReportParameter;
import com.kintiger.platform.boform.service.IBoformService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.DateUtil;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.role.pojo.Role;
import com.kintiger.platform.role.service.IRoleService;
import com.kintiger.platform.station.pojo.Station;
import com.kintiger.platform.station.service.IStationService;

public class BoformAction extends BaseAction {
	private static final Log logger = LogFactory.getLog(BoformAction.class);
	private static final long serialVersionUID = 9170817842048136464L;
	private IBoformService boformService;
	private IRoleService roleService;
	private IStationService stationService;
	private int total;
	private List<ReportParameter> reportParameterList;
	private String bid;
	private String pid;
	private ReportParameter reportParameter;
	private String bo3url;
	private String bo3use;
	private String bo3pwd;
	private String bo3dev;
	private String bo3enterp;
	private String bo4url;
	private String bo4use;
	private String bo4pwd;
	private String bo4dev;
	private String bo4enterp;
	private int year;
	private int month;
	private List<Integer> yearList;
	private List<Integer> monthList;
	private String custName;
	private String custId = "0";
	private List<QueryParameter> queryParameterList;
	private String custNF;
	private String custYY;
	private String custSP;
	private String reportType;
	private IOrgService orgService;
	private String boType;
	private String url;

	@Decode
	private String tableName;

	@Decode
	private String zdid;

	@Decode
	private String txt;

	@Decode
	private String zdtxt;

	@Decode
	private String d;

	@Decode
	private String search;
	private Role role;
	private String roleId;
	private String roleName;
	private String memo;
	private String value;
	private List<Role> roleList = new ArrayList();
	private String type;
	public static final String BO_TYPE_BO3 = "BO3";
	public static final String BO_TYPE_IREPORT = "ireport";

	@PermissionSearch
	public String searchReportParameter() {
		return "searchReportParameter";
	}

	@PermissionSearch
	public String searchBoRole() {
		return "searchBoRole";
	}

	@PermissionSearch
	public String createBoRolePrepare() {
		return "createBoRolePrepare";
	}

	@PermissionSearch
	public String createBORoledtPrepare() {
		return "createBORoledtPrepare";
	}

	@PermissionSearch
	public String updateBORolePrepare() {
		if ((StringUtils.isNotEmpty(this.roleId))
				&& (StringUtils.isNotEmpty(this.roleId.trim()))) {
			try {
				this.roleId = new String(this.roleId.trim().getBytes(
						"ISO8859-1"), "UTF-8");
				this.role = this.roleService.getRoleByRoleId(this.roleId);
			} catch (UnsupportedEncodingException e) {
				logger.error(this.roleId, e);
			}
		}
		this.role = (this.role == null ? new Role() : this.role);
		return "updateBOPrepare";
	}

	@PermissionSearch
	@JsonResult(field = "reportParameterList", include = { "pid", "bid",
			"tableName", "zdid", "zdtxt", "memo", "amount", "txt", "che", "d",
			"nickname", "checkWay" }, total = "total")
	public String getReportParameterJsonList() {
		ReportParameter r = new ReportParameter();
		r.setStart(getStart());
		r.setEnd(getEnd());
		try {
			if ((StringUtils.isNotEmpty(this.bid))
					&& (StringUtils.isNotEmpty(this.bid.trim())))
				r.setBid(Long.valueOf(Long.parseLong(this.bid.trim())));
		} catch (Exception e) {
			logger.error("bid:" + this.bid, e);
		}

		this.total = this.boformService.getReportParameterCount(r);
		if (this.total != 0) {
			this.reportParameterList = this.boformService
					.getReportParameterList(r);
		}

		return "jsonresult";
	}

	private boolean validate(ReportParameter reportParameter) {
		return reportParameter != null;
	}

	private boolean validate(List<ReportParameter> reportParameterList) {
		return reportParameterList != null;
	}

	@PermissionSearch
	public String createReportParameterPrepare() {
		return "createPrepare";
	}

	public String createReportParameter() {
		if (!validate(this.reportParameterList)) {
			setFailMessage("操作失败，输入有误！");
			return "resultMessage";
		}
		StringResult result = this.boformService
				.createBatchReportParameter(this.reportParameterList);

		if ("error".equals(result.getCode())) {
			setFailMessage(result.getResult());
		}

		if ("success".equals(result.getCode())) {
			String[] bids = new String[this.reportParameterList.size()];
			int i = 0;
			for (ReportParameter reportParameter : this.reportParameterList) {
				bids[(i++)] = reportParameter.getBid().toString();
			}
			removeCache(bids);
			setSuccessMessage("操作成功！");
		}

		return "resultMessage";
	}

	@PermissionSearch
	public String updateReportParameterPrepare() {
		if ((StringUtils.isNotEmpty(this.pid))
				&& (StringUtils.isNotEmpty(this.pid.trim()))) {
			try {
				this.reportParameter = this.boformService
						.getReportParameterByPid(Long.valueOf(Long
								.parseLong(this.pid.trim())));
			} catch (Exception e) {
				logger.error(this.pid, e);
				this.reportParameter = new ReportParameter();
			}
		}

		return "updatePrepare";
	}

	public String updateReportParameter() {
		if (!validate(this.reportParameter)) {
			setFailMessage("操作失败，输入有误！");
			return "resultMessage";
		}

		StringResult result = this.boformService
				.updateReportParameter(this.reportParameter);

		if ("error".equals(result.getCode())) {
			setFailMessage(result.getResult());
		}

		if ("success".equals(result.getCode())) {
			String[] bids = new String[1];
			bids[0] = this.reportParameter.getBid().toString();
			removeCache(bids);
			setSuccessMessage("操作成功！");
		}

		return "resultMessage";
	}

	public String deleteReportParameter() {
		String[] l = new String[this.reportParameterList.size()];
		String[] bids = new String[this.reportParameterList.size()];

		int i = 0;
		ReportParameter reportParameter = new ReportParameter();
		try {
			for (ReportParameter r : this.reportParameterList)
				if (r.getPid() != null) {
					l[i] = r.getPid().toString();
					bids[i] = r.getBid().toString();
					i++;
				}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(this.reportParameterList), e);
			setFailMessage("操作失败，输入有误！");
			return "resultMessage";
		}

		if (i == 0) {
			setFailMessage("操作失败，输入有误！");
			return "resultMessage";
		}

		reportParameter.setCodes(l);
		StringResult result = this.boformService
				.deleteReportParameter(reportParameter);

		if ("error".equals(result.getCode())) {
			setFailMessage(result.getResult());
		} else {
			setSuccessMessage("已成功删除" + result.getResult() + "个BO报表参数！");

			removeCache(bids);
		}

		return "resultMessage";
	}

	@PermissionSearch
	public String showBoRptPrepare() {
		if ((StringUtils.isNotEmpty(this.bid))
				&& (StringUtils.isNotEmpty(this.bid.trim()))) {
			try {
				List o = null;
				this.reportParameterList = ((o == null) || (o.size() == 0) ? this.boformService
						.getReportParametersByBid(Long.valueOf(Long
								.parseLong(this.bid.trim()))) : o);

				if ((this.reportParameterList != null)
						&& (this.reportParameterList.size() != 0))
					this.total = this.reportParameterList.size();
			} catch (Exception e) {
				logger.error(this.bid, e);
			}

		}

		this.year = DateUtil.getYear();
		this.yearList = new ArrayList();
		for (int i = 0; i < 6; i++) {
			this.yearList.add(Integer.valueOf(this.year - 3 + i));
		}
		this.month = DateUtil.getMonth();
		if(month == 1){
			year = year-1;
			month = 12;
		}else{
			month = month-1;
		}
		this.monthList = new ArrayList();
		for (int i = 0; i < 12; i++) {
			this.monthList.add(Integer.valueOf(1 + i));
		}
		return "showBoRptPrepare";
	}

	@PermissionSearch
	public String showBoRpt() {
		if (this.boType.equals("ireport")) {
			StringBuffer n = new StringBuffer(
					"/basisPlatform/IreportAction!toReport.jspa?");
			n.append("bid=" + this.bid);
			String s = null;
			String nickname = null;
			StringBuffer parameterUrl = new StringBuffer();
			for (ReportParameter r : this.reportParameterList) {
				s = r.getMemo();
				nickname = r.getNickname();
				parameterUrl.append(nickname + ":" + s + ",");
			}
			n.append("&&parameters='" + parameterUrl + "'");
			this.url = n.toString();
			System.out.println("ireportURl" + this.url);
			return "showBoRpt";
		}
		StringBuffer n = new StringBuffer(
				"BO3".equals(this.boType) ? this.bo3url : this.bo4url);
		n.append("?sWindow=same&iDocID=").append(this.bid).append("&token=")
				.append(sso(this.boType));

		boolean f1 = true;
		boolean f2 = true;
		String s = null;
		String nickname = null;

		for (ReportParameter r : this.reportParameterList) {
			s = r.getMemo();

			int amount = r.getAmount() == 20 ? 2 : r.getAmount();
			nickname = r.getNickname();

			if (("1".equals(this.reportType)) && (f1)) {
				n.append("&lsS").append(nickname).append("=");
				f1 = false;
			}

			if (StringUtils.isNotEmpty(s)) {
				if ("1".equals(this.reportType)) {
					if (f2)
						f2 = false;
					else {
						n.append(",");
					}
					n.append(s);
				} else {
					if ((amount == 7) || (amount == 1) || (amount == 3)
							|| (amount == 5) || (amount == 6))
						n.append("&lsS");
					else if ((amount == 2) || (amount == 4) || (amount == 0)) {
						n.append("&lsM");
					}
					nickname = nickname.replace(" ", "%20");
					s = s.replace(" ", "%20");
					n.append(nickname).append("=").append(s);
				}
			}
		}

		this.url = n.toString();
		System.out.println(this.url);
		
		//创建客户对账单报表访问日志
		if(bid.equals("13219")){ //生产机
//		if(bid.equals("34521")){//测试机34521		
			String date="";
			for (ReportParameter r : this.reportParameterList) {
				if("vdate".equals(r.getNickname())){
					date=r.getMemo();
				}
			}
			if(date.length()==6){
				int k=0;
				for (int m_digit = 0; m_digit < date
						.length(); m_digit++) {
					char c = date.charAt(m_digit);
					if ((c < '0' || c > '9')) {
						k=1;
						break;
					}
				}
				if(k==0){
					int year = Integer.parseInt(date.substring(0, 4));
					int month = Integer.parseInt(date.substring(4, 6));
					int date2 = Integer.parseInt(date);
					String kunnr = this.getUser().getIsOffice();
					Calendar cal = Calendar.getInstance();
//					if((cal.get(Calendar.MONTH)+1<month && cal.get(Calendar.YEAR)==year)
//							|| cal.get(Calendar.YEAR)>year){
					int nowDate = cal.get(Calendar.YEAR)*100+cal.get(Calendar.MONTH)+1;
					if(nowDate>date2 && year>=2000 && year<3000 && month>=1 && month<=12){					
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("checkYear", year);
						map.put("checkMonth", month);
						map.put("kunnr", kunnr);
						boformService.createOrderReportLog(map);
					}
				}
			}
		}else if(bid.equals("662755")){
			int year=0;
			int month=0;
			for (ReportParameter r : this.reportParameterList) {
				if("GJAHR".equals(r.getNickname())){
					year=Integer.parseInt(r.getMemo());
				}else if("MONAT".equals(r.getNickname())){
					month=Integer.parseInt(r.getMemo());
				}
			}
			if(year!=0 && month!=0){
				int date2=year*100+month;
				String kunnr = this.getUser().getIsOffice();
				Calendar cal = Calendar.getInstance();
				int nowDate = cal.get(Calendar.YEAR)*100+cal.get(Calendar.MONTH)+1;
				if(nowDate>date2 && year>=2000 && year<3000 && month>=1 && month<=12){					
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("checkYear", year);
					map.put("checkMonth", month);
					map.put("kunnr", kunnr);
					boformService.createOrderReportLog(map);
				}
			}
		}
		return "showBoRpt";
	}

	@PermissionSearch
	public String searchQueryParameter() {
		if ((StringUtils.isNotEmpty(this.pid))
				&& (StringUtils.isNotEmpty(this.pid.trim()))) {
			try {
				this.reportParameter = this.boformService
						.getReportParameterByPid(Long.valueOf(Long
								.parseLong(this.pid.trim())));

				String userId = getUser().getLoginId();
				List province = this.boformService.getProByEid(userId);
				String provinceId = "";
				for (int i = 0; i < province.size(); i++) {
					if (i == province.size() - 1)
						provinceId = provinceId
								+ ((QueryParameter) province.get(i)).getId();
					else {
						provinceId = provinceId
								+ ((QueryParameter) province.get(i)).getId()
								+ ",";
					}
				}
				if ((this.pid.trim().equalsIgnoreCase("389"))
						&& ((userId.trim().equalsIgnoreCase("jl.fan"))
								|| (userId.trim().equalsIgnoreCase("nz.lu"))
								|| (userId.trim().equalsIgnoreCase("sf.li"))
								|| (userId.trim().equalsIgnoreCase("zw.liu"))
								|| (userId.trim().equalsIgnoreCase("sn.gao")) || (userId
									.trim().equalsIgnoreCase("qm.yu")))) {
					System.out.print(userId);

					this.reportParameter.setD("zwl01 in(" + provinceId + ")");
				}

				if (/*((this.pid.trim().equalsIgnoreCase("400"))
						|| (this.pid.trim().equalsIgnoreCase("372"))
						|| (this.pid.trim().equalsIgnoreCase("404"))
						|| (this.pid.trim().equalsIgnoreCase("495"))
						|| (this.pid.trim().equalsIgnoreCase("510")) || (this.pid
							.trim().equalsIgnoreCase("506")))
						&&*/ (userId.trim().substring(0, 1).compareTo("0") >= 0)
						&& (userId.trim().substring(0, 1).compareTo("9") <= 0)) {
					this.reportParameter.setD("kunnr = '" + userId + "'");
				}

				if ((this.reportParameter != null)
						&& (this.reportParameter.getAmount() == 20))
					setRows(10);
			} catch (Exception e) {
				logger.error(this.pid, e);
			}
		}
		return "searchQueryParameter";
	}

	@PermissionSearch
	@JsonResult(field = "queryParameterList", include = { "id", "text" }, total = "total")
	public String getQueryParameterJsonList() {
		ReportParameter r = new ReportParameter();
		r.setStart(getStart());
		r.setEnd(getEnd());
		Role role = new Role();
		role.setPid(String.valueOf(this.pid));
		role.setUserId(String.valueOf(getUser().getUserId()));
		this.roleList = this.roleService.getConstraintList(role);
		StringBuffer str1 = new StringBuffer();

		if ((this.roleList != null) && (this.roleList.size() != 0)) {
			for (int i = 0; i < this.roleList.size(); i++) {
				if (!"in".equals(((Role) this.roleList.get(i)).getSign())) {
					if ((i == 0)
							&& (this.roleList.size() > 1)
							&& (((Role) this.roleList.get(i)).getMemo()
									.equals(((Role) this.roleList.get(i + 1))
											.getMemo())))
						str1.append("("
								+ ((Role) this.roleList.get(i)).getMemo() + " "
								+ ((Role) this.roleList.get(i)).getSign()
								+ " '"
								+ ((Role) this.roleList.get(i)).getValue()
								+ "'" + " or ");
					else if ((i == 0)
							&& (this.roleList.size() > 1)
							&& (!((Role) this.roleList.get(i)).getMemo()
									.equals(((Role) this.roleList.get(i + 1))
											.getMemo())))
						str1.append("("
								+ ((Role) this.roleList.get(i)).getMemo() + " "
								+ ((Role) this.roleList.get(i)).getSign()
								+ " '"
								+ ((Role) this.roleList.get(i)).getValue()
								+ "'" + ") and (");
					else if ((i != 0)
							&& (this.roleList.size() > 1)
							&& (i < this.roleList.size() - 1)
							&& (((Role) this.roleList.get(i)).getMemo()
									.equals(((Role) this.roleList.get(i + 1))
											.getMemo())))
						str1.append(((Role) this.roleList.get(i)).getMemo()
								+ " " + ((Role) this.roleList.get(i)).getSign()
								+ " '"
								+ ((Role) this.roleList.get(i)).getValue()
								+ "'" + " or ");
					else if ((i != 0)
							&& (this.roleList.size() > 1)
							&& (i < this.roleList.size() - 1)
							&& (!((Role) this.roleList.get(i)).getMemo()
									.equals(((Role) this.roleList.get(i + 1))
											.getMemo())))
						str1.append(((Role) this.roleList.get(i)).getMemo()
								+ " " + ((Role) this.roleList.get(i)).getSign()
								+ " '"
								+ ((Role) this.roleList.get(i)).getValue()
								+ "'" + ") and (");
					else if ((i == 0) && (1 == this.roleList.size())) {
						str1.append(((Role) this.roleList.get(i)).getMemo()
								+ " " + ((Role) this.roleList.get(i)).getSign()
								+ " '"
								+ ((Role) this.roleList.get(i)).getValue()
								+ "'");
					} else if (i == this.roleList.size() - 1) {
						str1.append(((Role) this.roleList.get(i)).getMemo()
								+ " " + ((Role) this.roleList.get(i)).getSign()
								+ "'"
								+ ((Role) this.roleList.get(i)).getValue()
								+ "'" + ")");
					}
				} else if ((i == 0)
						&& (this.roleList.size() > 1)
						&& (((Role) this.roleList.get(i)).getMemo()
								.equals(((Role) this.roleList.get(i + 1))
										.getMemo())))
					str1.append("(" + ((Role) this.roleList.get(i)).getMemo()
							+ " " + ((Role) this.roleList.get(i)).getSign()
							+ " " + ((Role) this.roleList.get(i)).getValue()
							+ " or ");
				else if ((i == 0)
						&& (this.roleList.size() > 1)
						&& (!((Role) this.roleList.get(i)).getMemo().equals(
								((Role) this.roleList.get(i + 1)).getMemo())))
					str1.append("(" + ((Role) this.roleList.get(i)).getMemo()
							+ " " + ((Role) this.roleList.get(i)).getSign()
							+ " " + ((Role) this.roleList.get(i)).getValue()
							+ ") and (");
				else if ((i != 0)
						&& (this.roleList.size() > 1)
						&& (i < this.roleList.size() - 1)
						&& (((Role) this.roleList.get(i)).getMemo()
								.equals(((Role) this.roleList.get(i + 1))
										.getMemo())))
					str1.append(((Role) this.roleList.get(i)).getMemo() + " "
							+ ((Role) this.roleList.get(i)).getSign() + " "
							+ ((Role) this.roleList.get(i)).getValue() + " or ");
				else if ((i != 0)
						&& (this.roleList.size() > 1)
						&& (i < this.roleList.size() - 1)
						&& (!((Role) this.roleList.get(i)).getMemo().equals(
								((Role) this.roleList.get(i + 1)).getMemo())))
					str1.append(((Role) this.roleList.get(i)).getMemo() + " "
							+ ((Role) this.roleList.get(i)).getSign() + " "
							+ ((Role) this.roleList.get(i)).getValue()
							+ ") and (");
				else if ((i == 0) && (1 == this.roleList.size())) {
					str1.append(((Role) this.roleList.get(i)).getMemo() + " "
							+ ((Role) this.roleList.get(i)).getSign() + " "
							+ ((Role) this.roleList.get(i)).getValue());
				} else if (i == this.roleList.size() - 1) {
					str1.append(((Role) this.roleList.get(i)).getMemo() + " "
							+ ((Role) this.roleList.get(i)).getSign() + " "
							+ ((Role) this.roleList.get(i)).getValue() + ")");
				}

			}

		}

		if ((this.roleList != null) && (this.roleList.size() != 0)) {
			r.setS(str1.toString());
		}
		if ((StringUtils.isNotEmpty(this.tableName))
				&& (StringUtils.isNotEmpty(this.tableName.trim()))) {
			r.setTableName(this.tableName.trim());
		}
		if ((StringUtils.isNotEmpty(this.txt))
				&& (StringUtils.isNotEmpty(this.txt.trim()))) {
			try {
				r.setTxt(Integer.parseInt(this.txt.trim()));
			} catch (Exception e) {
				logger.error(this.txt, e);
			}
		}
		if ((StringUtils.isNotEmpty(this.zdid))
				&& (StringUtils.isNotEmpty(this.zdid.trim()))) {
			r.setZdid(this.zdid.trim());
		}
		if ((StringUtils.isNotEmpty(this.zdtxt))
				&& (StringUtils.isNotEmpty(this.zdtxt.trim()))) {
			r.setZdtxt(this.zdtxt.trim());
		}

		if ((StringUtils.isNotEmpty(this.d))
				&& (StringUtils.isNotEmpty(this.d.trim()))) {
			this.d = this.d.trim();
			
			//高级经理,客户经理,业代查看报表关联经销商
			if("crm.crm_tb_kunnr".equals(tableName)){
				String condition="";
				int stationFlag=0;
				List<Station> stations = stationService.getStationByEmpId(Long.parseLong(this.getUser().getUserId()));
				for(Station s : stations){
					List<String> kunnrs=new ArrayList<String>();
					if("h_zg".equals(s.getStationId()) || 
							"h_yd".equals(s.getStationId())){
						kunnrs=boformService.getKunnrIdByHeadOrAgent(this.getUser().getUserId());
						stationFlag=1;
					}else if("h_csjl".equals(s.getStationId())){
						kunnrs=boformService.getKunnrIdByCompetent(s.getId()+"");
						stationFlag=1;
					}else if("h_sjjl".equals(s.getStationId()) || "h_dqjl".equals(s.getStationId())
							||"h_xsfzj".equals(s.getStationId()) || "h_xszxfjjl".equals(s.getStationId())){
						kunnrs=boformService.getKunnrIdByUserId(s.getOrgId()+"");
						stationFlag=1;
					}
					if(kunnrs!=null && kunnrs.size()>0){
						for(String kunnr:kunnrs){
							if(condition.length()==0){
								condition+="\'"+kunnr+"\'";
							}else{
								condition+=",\'"+kunnr+"\'";
							}
						}
					}
				}
				if(stationFlag==1){
					if("".equals(condition)){
						d="kunnr in (\'\')";
					}else{
						d="kunnr in ("+condition+")";
					}
				}
			}
			
			r.setD(this.d);
		}
		if ((StringUtils.isNotEmpty(this.search))
				&& (StringUtils.isNotEmpty(this.search.trim()))) {
			this.search = this.search.trim();
			r.setSearch(this.search);
		}
		this.total = this.boformService.getQueryParameterCount(r);
		if (this.total != 0) {
			this.queryParameterList = this.boformService
					.getQueryParameterList(r);
		}

		return "jsonresult";
	}

	@PermissionSearch
	private String sso(String boType) {
		String token = (String) getSession().getAttribute(
				"BO3".equals(boType) ? "LogonToken" : "Bo4LogonToken");

		if (StringUtils.isEmpty(token)) {
			try {
				ISessionMgr sessionMgr = CrystalEnterprise.getSessionMgr();
				IEnterpriseSession enterpriseSession = sessionMgr.logon(
						"BO3".equals(boType) ? this.bo3use : this.bo4use,
						"BO3".equals(boType) ? this.bo3pwd : this.bo4pwd,
						"BO3".equals(boType) ? this.bo3dev : this.bo4dev,
						"BO3".equals(boType) ? this.bo3enterp : this.bo4enterp);

				getSession().setAttribute(
						"BO3".equals(boType) ? "EnterpriseSession"
								: "Bo4EnterpriseSession", enterpriseSession);

				token = enterpriseSession.getLogonTokenMgr().getDefaultToken();

				getSession().setAttribute(
						"BO3".equals(boType) ? "LogonToken" : "Bo4LogonToken",
						token);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return token;
	}

	private void removeCache(String[] bids) {
	}

	@PermissionSearch
	public String createRoleforBO() {
		if (!validate(this.role)) {
			setFailMessage("操作失败，输入有误！");
			return "resultMessage";
		}
		if (this.roleService.getRoleCount1(this.role) != 0L) {
			setFailMessage("该角色已被占用");
			return "resultMessage";
		}
		this.role.setRoleType("BO");
		StringResult result = this.roleService.createRole(this.role);
		if ("error".equals(result.getCode())) {
			setFailMessage(result.getResult());
		}
		setSuccessMessage("创建成功");
		return "resultMessage";
	}

	@PermissionSearch
	public String createRoleforBOdt() {
		if (this.role == null) {
			setFailMessage("操作失败，输入有误！");
			return "resultMessage";
		}
		this.role.setRoleId(this.roleId);
		StringResult result = this.roleService.createRoledt(this.role);
		if ("error".equals(result.getCode())) {
			setFailMessage(result.getResult());
		}
		setSuccessMessage("创建成功");
		return "resultMessage";
	}

	@PermissionSearch
	public String updateRoleforBO() {
		if (!validate(this.role)) {
			setFailMessage("操作失败，输入有误！");
			return "resultMessage";
		}
		this.role.setRoleType("BO");
		StringResult result = this.roleService.updateRole(this.role);
		if ("error".equals(result.getCode()))
			setFailMessage(result.getResult());
		else {
			setSuccessMessage("角色修改成功");
		}
		return "resultMessage";
	}

	@PermissionSearch
	@JsonResult(field = "roleList", include = { "roleId", "roleName", "descn",
			"roleType", "pid" }, total = "total")
	public String getBORoleList() {
		Role r = new Role();
		r.setStart(getStart());
		r.setEnd(getEnd());
		if ((StringUtils.isNotEmpty(this.roleId))
				&& (StringUtils.isNotEmpty(this.roleId.trim()))) {
			r.setRoleId(this.roleId.trim());
		}
		if ((StringUtils.isNotEmpty(this.roleName))
				&& (StringUtils.isNotEmpty(this.roleName.trim()))) {
			r.setRoleName(this.roleName.trim());
		}
		this.total = this.roleService.getBORoleCount(r);
		if (this.total != 0)
			this.roleList = this.roleService.getBORoleList(r);
		else {
			this.roleList = null;
		}
		return "jsonresult";
	}

	@PermissionSearch
	@JsonResult(field = "roleList", include = { "roleId", "roleName", "descn",
			"roleType", "bid", "memo", "value" }, total = "total")
	public String getBORoleDetailList() {
		Role r = new Role();
		r.setStart(getStart());
		r.setEnd(getEnd());
		if ((StringUtils.isNotEmpty(this.memo))
				&& (StringUtils.isNotEmpty(this.memo.trim()))) {
			r.setRoleId(this.memo.trim());
		}
		if ((StringUtils.isNotEmpty(this.value))
				&& (StringUtils.isNotEmpty(this.value.trim()))) {
			r.setRoleId(this.value.trim());
		}
		if ((StringUtils.isNotEmpty(this.roleId))
				&& (StringUtils.isNotEmpty(this.roleId.trim()))) {
			r.setRoleId(this.roleId.trim());
		}
		if ("Y".equals(this.type)) {
			this.total = this.roleService.getBORoleDetailCount(r);
			if (this.total != 0)
				this.roleList = this.roleService.getBODetailRoleList(r);
			else {
				this.roleList = null;
			}
		}
		return "jsonresult";
	}

	private boolean validate(Role role) {
		if (role == null) {
			return false;
		}

		return (!StringUtils.isEmpty(role.getRoleId()))
				&& (!StringUtils.isEmpty(role.getRoleId().trim()))
				&& (!StringUtils.isEmpty(role.getRoleName()))
				&& (!StringUtils.isEmpty(role.getRoleName().trim()));
	}

	public IBoformService getBoformService() {
		return this.boformService;
	}

	public void setBoformService(IBoformService boformService) {
		this.boformService = boformService;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ReportParameter> getReportParameterList() {
		return this.reportParameterList;
	}

	public void setReportParameterList(List<ReportParameter> reportParameterList) {
		this.reportParameterList = reportParameterList;
	}

	public String getBid() {
		return this.bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public ReportParameter getReportParameter() {
		return this.reportParameter;
	}

	public void setReportParameter(ReportParameter reportParameter) {
		this.reportParameter = reportParameter;
	}

	public String getBo3url() {
		return this.bo3url;
	}

	public void setBo3url(String bo3url) {
		this.bo3url = bo3url;
	}

	public String getBo3use() {
		return this.bo3use;
	}

	public void setBo3use(String bo3use) {
		this.bo3use = bo3use;
	}

	public String getBo3pwd() {
		return this.bo3pwd;
	}

	public void setBo3pwd(String bo3pwd) {
		this.bo3pwd = bo3pwd;
	}

	public String getBo3dev() {
		return this.bo3dev;
	}

	public void setBo3dev(String bo3dev) {
		this.bo3dev = bo3dev;
	}

	public String getBo3enterp() {
		return this.bo3enterp;
	}

	public void setBo3enterp(String bo3enterp) {
		this.bo3enterp = bo3enterp;
	}

	public String getBo4url() {
		return this.bo4url;
	}

	public void setBo4url(String bo4url) {
		this.bo4url = bo4url;
	}

	public String getBo4use() {
		return this.bo4use;
	}

	public void setBo4use(String bo4use) {
		this.bo4use = bo4use;
	}

	public String getBo4pwd() {
		return this.bo4pwd;
	}

	public void setBo4pwd(String bo4pwd) {
		this.bo4pwd = bo4pwd;
	}

	public String getBo4dev() {
		return this.bo4dev;
	}

	public void setBo4dev(String bo4dev) {
		this.bo4dev = bo4dev;
	}

	public String getBo4enterp() {
		return this.bo4enterp;
	}

	public void setBo4enterp(String bo4enterp) {
		this.bo4enterp = bo4enterp;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Integer> getYearList() {
		return this.yearList;
	}

	public void setYearList(List<Integer> yearList) {
		this.yearList = yearList;
	}

	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public List<QueryParameter> getQueryParameterList() {
		return this.queryParameterList;
	}

	public void setQueryParameterList(List<QueryParameter> queryParameterList) {
		this.queryParameterList = queryParameterList;
	}

	public String getCustNF() {
		return this.custNF;
	}

	public void setCustNF(String custNF) {
		this.custNF = custNF;
	}

	public String getCustYY() {
		return this.custYY;
	}

	public void setCustYY(String custYY) {
		this.custYY = custYY;
	}

	public String getCustSP() {
		return this.custSP;
	}

	public void setCustSP(String custSP) {
		this.custSP = custSP;
	}

	public IOrgService getOrgService() {
		return this.orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public String getBoType() {
		return this.boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getZdid() {
		return this.zdid;
	}

	public void setZdid(String zdid) {
		this.zdid = zdid;
	}

	public String getTxt() {
		return this.txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getZdtxt() {
		return this.zdtxt;
	}

	public void setZdtxt(String zdtxt) {
		this.zdtxt = zdtxt;
	}

	public String getD() {
		return this.d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getSearch() {
		return this.search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public static String getBoTypeBo3() {
		return "BO3";
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public IRoleService getRoleService() {
		return this.roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Role> getRoleList() {
		return this.roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public IStationService getStationService() {
		return stationService;
	}

	public void setStationService(IStationService stationService) {
		this.stationService = stationService;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public List<Integer> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Integer> monthList) {
		this.monthList = monthList;
	}
	
}
