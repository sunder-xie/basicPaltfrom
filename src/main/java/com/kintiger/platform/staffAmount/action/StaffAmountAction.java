package com.kintiger.platform.staffAmount.action;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.menu.action.MenuTreeAjaxAction;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.staffAmount.pojo.StaffAmount;
import com.kintiger.platform.staffAmount.service.IStaffService;
import com.kintiger.platform.station.pojo.Station;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StaffAmountAction extends BaseAction {
	/* 24 */private static final Log logger = LogFactory
			.getLog(MenuTreeAjaxAction.class);
	private static final long serialVersionUID = 1L;
	private IStaffService staffAmountService;
	private IOrgService iOrgService;
	/* 28 */private int total = 0;
	private StaffAmount staffAmount;
	/* 30 */private StringResult stringResult = new StringResult();
	private String PId;
	private String amount;
	private String orgName;
	private String orgId;
	private String org_id;
	private String positionTypeName;
	/* 37 */private String bhxjFlag = "";
	private String uploadFileFileName;
	private File uploadFile;
	private String PIds;
	private String UpdateNum;
	private String stationId;
	private String sort;
	private String order;
	private String type;
	private List<StaffAmount> staffList;
	private List<Station> statList;
	private String positionId;

	@PermissionSearch
	public String toSearchStaffPage() {
		/* 57 */return "searchStaff";
	}

	@PermissionSearch
	@JsonResult(field = "staffList", include = { "PId", "orgId", "stationId",
			"amount", "amountU", "mountC", "state", "orgName", "orgParentName",
			"positionTypeName" }, total = "total")
	public String getStaffAmountList() throws UnsupportedEncodingException {
		/* 71 */this.staffList = new ArrayList();
		/* 72 */StaffAmount staffAmount = new StaffAmount();
		/* 73 */staffAmount.setStart(getStart());
		/* 74 */staffAmount.setEnd(getEnd());
		/* 75 */staffAmount.setSort(this.sort);
		/* 76 */if (StringUtils.isNotEmpty(this.orgId)) {
			/* 77 */if ((StringUtils.isNotEmpty(this.bhxjFlag))
					&& (StringUtils.equals(this.bhxjFlag, "C"))) {
				/* 78 */String orgids = this.iOrgService
						.getFnAllChildStrOrg(this.orgId);
				/* 79 */if (StringUtils.isNotEmpty(orgids))
					/* 80 */staffAmount.setOrgIdarrs(orgids.split(","));
			} else {
				/* 83 */staffAmount.setOrgId(Long.valueOf(Long
						.parseLong(this.orgId)));
			}
		}

		/* 87 */if (StringUtils.isNotEmpty(this.positionTypeName)) {
			/* 88 */staffAmount.setPositionTypeName(URLDecoder.decode(
					this.positionTypeName, "UTF-8").trim());
		}
		/* 90 */this.total = this.staffAmountService.getStaffTotal(staffAmount);
		/* 91 */if (this.total != 0) {
			/* 92 */this.staffList = this.staffAmountService
					.getStaffList(staffAmount);
		}
		/* 94 */return "jsonresult";
	}

	@PermissionSearch
	public String staffCreateSave() {
		/* 104 */return "createStaff";
	}

	public String createStaff() {
		/* 108 */setSuccessMessage("");
		/* 109 */setFailMessage("");
		/* 110 */StaffAmount staffAmount = new StaffAmount();
		/* 111 */staffAmount
				.setOrgId(Long.valueOf(Long.parseLong(this.org_id)));
		/* 112 */staffAmount.setStationId(this.positionId);
		/* 113 */staffAmount
				.setAmount(Long.valueOf(Long.parseLong(this.amount)));
		/* 114 */if (this.staffAmountService.getStaffAmountCount(staffAmount) != 0) {
			/* 115 */setFailMessage("该组织下该岗位已维护编制!");
		} else {
			/* 117 */StringResult stringResult = this.staffAmountService
					.createStaff(staffAmount);
			/* 118 */if ("error".equals(stringResult.getCode()))
				/* 119 */setFailMessage(stringResult.getResult());
			else {
				/* 121 */setSuccessMessage("已成功新增编制,编制ID为："
						+ stringResult.getResult() + "！");
			}
		}
		/* 124 */return "resultMessage";
	}

	public String deleteStaff() {
		/* 133 */setSuccessMessage("");
		/* 134 */setFailMessage("");
		/* 135 */String[] l = this.PIds.split(",");
		/* 136 */StaffAmount staffAmount = new StaffAmount();
		/* 137 */staffAmount.setPIds(l);
		/* 138 */staffAmount.setState("D");
		/* 139 */int amountU = this.staffAmountService
				.getStaffAmountCountU(staffAmount);
		/* 140 */if (amountU > 0) {
			/* 141 */setFailMessage("在删除的编制中有人员占用编制,请先维护好人员编制,再重新删除");
		} else {
			/* 143 */StringResult result = this.staffAmountService
					.updateStaffAmounts(staffAmount);
			/* 144 */if ("error".equals(result.getCode()))
				/* 145 */setFailMessage(result.getResult());
			else {
				/* 147 */setSuccessMessage("已成功删除" + result.getResult()
						+ "个编制!");
			}
		}
		/* 150 */return "resultMessage";
	}

	public String updateStaff() {
		/* 159 */setSuccessMessage("");
		/* 160 */setFailMessage("");
		/* 161 */String[] str = this.UpdateNum.split(",");
		/* 162 */String[] l = str[1].split(",");
		/* 163 */StaffAmount staffAmount = new StaffAmount();
		/* 164 */staffAmount.setPIds(l);
		/* 165 */staffAmount.setAmount(Long.valueOf(Long.parseLong(str[0])));
		/* 166 */StringResult result = this.staffAmountService
				.updateStaffAmounts(staffAmount);
		/* 167 */if ("error".equals(result.getCode()))
			/* 168 */setFailMessage(result.getResult());
		else {
			/* 170 */setSuccessMessage("修改成功！");
		}
		/* 172 */return "resultMessage";
	}

	@PermissionSearch
	@JsonResult(field = "statList", include = { "stationId", "stationName" })
	public String blurSearchStaff() {
		/* 183 */Station station = new Station();
		/* 184 */if ((StringUtils.isNotEmpty(this.positionTypeName))
				&& (StringUtils.isNotEmpty(this.positionTypeName.trim()))) {
			try {
				/* 186 */this.positionTypeName =
				/* 187 */new String(getServletRequest().getParameter(
						"positionTypeName").getBytes("ISO8859-1"), "UTF-8");
				/* 188 */station.setStationName(this.positionTypeName.trim());
				/* 189 */this.statList = this.staffAmountService
						.blurSearchStaff(station);
			} catch (UnsupportedEncodingException e) {
				/* 191 */logger.error(e);
			}
		}
		/* 194 */return "jsonresult";
	}

	public String geStaffUserPre() {
		/* 203 */return "geStaffUserPre";
	}

	@JsonResult(field = "statList", include = { "userId", "userCode",
			"empUserCode", "userName" })
	public String geStaffUser() {
		/* 213 */Station station = new Station();
		/* 214 */station.setOrgId(Long.valueOf(Long.parseLong(this.orgId)));
		/* 215 */station.setStationId(this.stationId);
		/* 216 */station.setCustType(this.type);
		/* 217 */this.statList = this.staffAmountService.geStaffUser(station);
		/* 218 */return "jsonresult";
	}

	public IStaffService getStaffAmountService() {
		/* 222 */return this.staffAmountService;
	}

	public void setStaffAmountService(IStaffService staffAmountService) {
		/* 226 */this.staffAmountService = staffAmountService;
	}

	public int getTotal() {
		/* 230 */return this.total;
	}

	public void setTotal(int total) {
		/* 234 */this.total = total;
	}

	public StaffAmount getStaffAmount() {
		/* 238 */return this.staffAmount;
	}

	public void setStaffAmount(StaffAmount staffAmount) {
		/* 242 */this.staffAmount = staffAmount;
	}

	public String getPId() {
		/* 246 */return this.PId;
	}

	public void setPId(String pId) {
		/* 250 */this.PId = pId;
	}

	public String getAmount() {
		/* 254 */return this.amount;
	}

	public void setAmount(String amount) {
		/* 258 */this.amount = amount;
	}

	public String getOrgName() {
		/* 262 */return this.orgName;
	}

	public void setOrgName(String orgName) {
		/* 266 */this.orgName = orgName;
	}

	public String getOrgId() {
		/* 270 */return this.orgId;
	}

	public void setOrgId(String orgId) {
		/* 274 */this.orgId = orgId;
	}

	public String getPositionTypeName() {
		/* 278 */return this.positionTypeName;
	}

	public void setPositionTypeName(String positionTypeName) {
		/* 282 */this.positionTypeName = positionTypeName;
	}

	public String getBhxjFlag() {
		/* 286 */return this.bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		/* 290 */this.bhxjFlag = bhxjFlag;
	}

	public String getUploadFileFileName() {
		/* 294 */return this.uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		/* 298 */this.uploadFileFileName = uploadFileFileName;
	}

	public File getUploadFile() {
		/* 302 */return this.uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		/* 306 */this.uploadFile = uploadFile;
	}

	public List<StaffAmount> getStaffList() {
		/* 310 */return this.staffList;
	}

	public void setStaffList(List<StaffAmount> staffList) {
		/* 314 */this.staffList = staffList;
	}

	public static Log getLogger() {
		/* 318 */return logger;
	}

	public static long getSerialversionuid() {
		/* 322 */return 1L;
	}

	public StringResult getStringResult() {
		/* 326 */return this.stringResult;
	}

	public void setStringResult(StringResult stringResult) {
		/* 330 */this.stringResult = stringResult;
	}

	public String getPIds() {
		/* 334 */return this.PIds;
	}

	public void setPIds(String pIds) {
		/* 338 */this.PIds = pIds;
	}

	public String getUpdateNum() {
		/* 342 */return this.UpdateNum;
	}

	public void setUpdateNum(String updateNum) {
		/* 346 */this.UpdateNum = updateNum;
	}

	public String getStationId() {
		/* 350 */return this.stationId;
	}

	public void setStationId(String stationId) {
		/* 354 */this.stationId = stationId;
	}

	public String getPositionId() {
		/* 358 */return this.positionId;
	}

	public void setPositionId(String positionId) {
		/* 362 */this.positionId = positionId;
	}

	public List<Station> getStatList() {
		/* 366 */return this.statList;
	}

	public void setStatList(List<Station> statList) {
		/* 370 */this.statList = statList;
	}

	public String getOrg_id() {
		/* 374 */return this.org_id;
	}

	public void setOrg_id(String org_id) {
		/* 378 */this.org_id = org_id;
	}

	public IOrgService getiOrgService() {
		/* 382 */return this.iOrgService;
	}

	public void setiOrgService(IOrgService iOrgService) {
		/* 386 */this.iOrgService = iOrgService;
	}

	public String getSort() {
		/* 390 */return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
