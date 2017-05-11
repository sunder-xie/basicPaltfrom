package com.kintiger.platform.addressList.action;

import com.kintiger.platform.addressList.pojo.AddressLists;
import com.kintiger.platform.addressList.service.IAddressListService;
import com.kintiger.platform.allUser.action.AllUserAction;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.org.service.IOrgService;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

public class AddressListAction extends BaseAction
{
  private static final Log logger = LogFactory.getLog(AllUserAction.class);
  private static final long serialVersionUID = -4645806234526562765L;
  private int total;
  private String bhxjFlag;
  private String userId;

  @Decode
  private String userName;

  @Decode
  private String orgId;

  @Decode
  private String orgName;
  private String adgroupName;

  @Decode
  private String userCode;
  private String empMobilePhone;
  private String busMobilePhone;
  private String stMobile;
  private String userEmail;
  private String empPostName;
  private String stationName;
  private String download;
  private List<AddressLists> addressLists;
  private IAddressListService addressListService;
  private IOrgService iOrgService;
  private String xmlFilePath;

  @PermissionSearch
  public String searchAddressList()
  {
    this.orgId = getUser().getOrgId();
    this.orgName = this.addressListService.getOrgName(this.orgId);
    return "searchAddressList";
  }
  /**
   * 首页通讯录查询
   * zhusiliang
   * @return
   */
  @PermissionSearch
  @JsonResult(field="addressLists", include={ "userName","orgName","empMobilePhone", "busMobilePhone","stMobile"}, total="total")
  public String searchMailLists()
  {
    this.addressLists = this.addressListService.searchMailLists(userName);
    return "jsonresult";
  }

  @PermissionSearch
  @JsonResult(field="addressLists", include={"userId", "userName", "orgId", "orgName", "adgroupName", "userCode", "empMobilePhone", "busMobilePhone", "stMobile", "userEmail", "empPostName", "stationName"}, total="total")
  public String searchAddressLists()
  {
    setSuccessMessage("");
    setFailMessage("");
    this.addressLists = null;
    AddressLists addressList = new AddressLists();
    addressList.setStart(getStart());
    addressList.setEnd(getEnd());
    if (StringUtils.isNotEmpty(this.orgId)) {
      if (StringUtils.isNotEmpty(this.bhxjFlag)) {
        addressList.setOrgId(this.orgId);
        addressList.setBhxjFlag(this.bhxjFlag);
      } else {
        addressList.setOrgId(this.orgId);
      }
    }
    else addressList.setOrgId(getUser().getOrgId());

    if (StringUtils.isNotEmpty(this.userName)) {
      addressList.setUserName(this.userName);
    }
    if (StringUtils.isNotEmpty(this.userCode)) {
      addressList.setUserCode(this.userCode);
    }
    this.total = this.addressListService.searchAddressListCount(addressList);
    if (this.total != 0) {
      this.addressLists = this.addressListService.searchAddressLists(addressList);
    }
    else {
      this.addressLists = null;
    }
    return "jsonresult";
  }

  public String searchAddressListDownLoad()
  {
    ServletActionContext.getRequest().getSession()
      .setAttribute("DownLoad", "Ing");
    setSuccessMessage("");
    setFailMessage("");
    this.addressLists = null;
    AddressLists aList = new AddressLists();
    aList.setStart(0);
    aList.setEnd(1000000);
    if (StringUtils.isNotEmpty(this.orgId)) {
      if (StringUtils.isNotEmpty(this.bhxjFlag)) {
        aList.setOrgId(this.orgId);
        aList.setBhxjFlag(this.bhxjFlag);
      } else {
        aList.setOrgId(this.orgId);
      }
    }
    else aList.setOrgId(getUser().getOrgId());

    if (StringUtils.isNotEmpty(this.userName)) {
      aList.setUserName(this.userName);
    }
    if (StringUtils.isNotEmpty(this.userCode)) {
      aList.setUserCode(this.userCode);
    }

    try
    {
      this.addressLists = this.addressListService.searchAddressLists(aList);
      if (this.addressLists.size() == 0) {
        setFailMessage("Excel数据导出出错,请不要导出数据为空的列表");
      }
      List2Excel(this.addressLists);
      ServletActionContext.getRequest().getSession()
        .setAttribute("DownLoad", "Over");
    }
    catch (Exception e) {
      setFailMessage("Excel数据导出出错");
    }
    return "resultMessage";
  }

  @PermissionSearch
  private void List2Excel(List<AddressLists> unMiantList)
  {
    OutputStream os = null;
    WritableWorkbook wbook = null;
    try {
      HttpServletResponse response = ServletActionContext.getResponse();

      os = response.getOutputStream();

      response.reset();

      String fileName = "addressBook.xls";
      fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
      response.setHeader("Content-disposition", "attachment; filename=" + 
        fileName);

      response.setContentType("application/msexcel");

      wbook = Workbook.createWorkbook(os);
      WritableSheet wsheet = wbook.createSheet("第一页", 0);

      wsheet.setColumnView(0, 54);
      wsheet.setColumnView(1, 17);
      wsheet.setColumnView(2, 17);
      wsheet.setColumnView(3, 17);
      wsheet.setColumnView(4, 17);
      wsheet.setColumnView(5, 27);
      wsheet.setColumnView(6, 17);
      wsheet.setColumnView(7, 17);
      wsheet.setColumnView(8, 13);

      wsheet.mergeCells(0, 0, 0, 1);
      wsheet.mergeCells(1, 0, 1, 1);
      wsheet.mergeCells(2, 0, 2, 1);
      wsheet.mergeCells(3, 0, 3, 1);
      wsheet.mergeCells(4, 0, 4, 1);
      wsheet.mergeCells(5, 0, 5, 1);
      wsheet.mergeCells(6, 0, 6, 1);
      wsheet.mergeCells(7, 0, 7, 1);
      wsheet.mergeCells(8, 0, 8, 1);

      WritableFont wfcb = new WritableFont(WritableFont.ARIAL, 10, 
        WritableFont.BOLD, false, 
        UnderlineStyle.NO_UNDERLINE, 
        Colour.BLACK);

      WritableCellFormat wcfFG = new WritableCellFormat(wfcb);
      wcfFG.setBackground(Colour.GRAY_25);
      wcfFG.setAlignment(Alignment.CENTRE);
      wcfFG.setVerticalAlignment(VerticalAlignment.CENTRE);
      wcfFG.setBorder(Border.ALL, 
        BorderLineStyle.MEDIUM);

      WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, 
        WritableFont.NO_BOLD, false, 
        UnderlineStyle.NO_UNDERLINE, 
        Colour.BLACK);
      WritableCellFormat wcfF = new WritableCellFormat(wfc);
      wcfF.setAlignment(Alignment.CENTRE);
      wcfF.setVerticalAlignment(VerticalAlignment.CENTRE);
      wcfF.setBorder(Border.ALL, 
        BorderLineStyle.HAIR);

      WritableCellFormat wcfFL = new WritableCellFormat(wfc);
      wcfFL.setAlignment(Alignment.LEFT);
      wcfFL.setVerticalAlignment(VerticalAlignment.CENTRE);
      wcfFL.setBorder(Border.ALL, 
        BorderLineStyle.HAIR);

      wsheet.addCell(new Label(0, 0, "多级组织 ", wcfFG));
      wsheet.addCell(new Label(1, 0, "用户ID", wcfFG));
      wsheet.addCell(new Label(2, 0, "姓名", wcfFG));
      wsheet.addCell(new Label(3, 0, "主岗位 ", wcfFG));
      wsheet.addCell(new Label(4, 0, "职务", wcfFG));
      wsheet.addCell(new Label(5, 0, "邮箱 ", wcfFG));
      wsheet.addCell(new Label(6, 0, "私人手机号码 ", wcfFG));
      wsheet.addCell(new Label(7, 0, "公务手机号码", wcfFG));
      wsheet.addCell(new Label(8, 0, "手机短号 ", wcfFG));

      for (int i = 1; i < unMiantList.size() + 1; i++)
      {
        wsheet.addCell(
          new Label(0, i + 1, ((AddressLists)unMiantList.get(i - 1))
          .getAdgroupName(), wcfFL));
        wsheet.addCell(
          new Label(1, i + 1, ((AddressLists)unMiantList.get(i - 1))
          .getUserCode(), wcfF));
        wsheet.addCell(
          new Label(2, i + 1, ((AddressLists)unMiantList.get(i - 1))
          .getUserName(), wcfF));
        wsheet.addCell(
          new Label(3, i + 1, ((AddressLists)unMiantList.get(i - 1))
          .getStationName(), wcfF));
        wsheet.addCell(
          new Label(4, i + 1, ((AddressLists)unMiantList.get(i - 1))
          .getEmpPostName(), wcfF));
        wsheet.addCell(
          new Label(5, i + 1, ((AddressLists)unMiantList.get(i - 1))
          .getUserEmail(), wcfF));
        wsheet.addCell(
          new Label(6, i + 1, ((AddressLists)unMiantList.get(i - 1))
          .getEmpMobilePhone(), wcfF));
        wsheet.addCell(
          new Label(7, i + 1, ((AddressLists)unMiantList.get(i - 1))
          .getBusMobilePhone(), wcfF));
        wsheet.addCell(
          new Label(8, i + 1, ((AddressLists)unMiantList.get(i - 1))
          .getStMobile(), wcfF));
      }
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
  }

  @PermissionSearch
  @JsonResult(field="download")
  public String downLoadOver()
  {
    Object obj = ServletActionContext.getRequest().getSession()
      .getAttribute("DownLoad");
    if ((obj == null) || ("Ing".equals(obj)))
      this.download = "No";
    else {
      this.download = "Yes";
    }
    return "jsonresult";
  }

  public String getUserId()
  {
    return this.userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getUserName() {
    return this.userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getOrgId() {
    return this.orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public String getOrgName() {
    return this.orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  public String getAdgroupName() {
    return this.adgroupName;
  }
  public void setAdgroupName(String adgroupName) {
    this.adgroupName = adgroupName;
  }
  public String getUserCode() {
    return this.userCode;
  }
  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }
  public String getEmpMobilePhone() {
    return this.empMobilePhone;
  }
  public void setEmpMobilePhone(String empMobilePhone) {
    this.empMobilePhone = empMobilePhone;
  }
  public String getBusMobilePhone() {
    return this.busMobilePhone;
  }
  public void setBusMobilePhone(String busMobilePhone) {
    this.busMobilePhone = busMobilePhone;
  }
  public String getStMobile() {
    return this.stMobile;
  }
  public void setStMobile(String stMobile) {
    this.stMobile = stMobile;
  }
  public String getUserEmail() {
    return this.userEmail;
  }
  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
  public String getEmpPostName() {
    return this.empPostName;
  }
  public void setEmpPostName(String empPostName) {
    this.empPostName = empPostName;
  }
  public String getStationName() {
    return this.stationName;
  }
  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  public IAddressListService getAddressListService() {
    return this.addressListService;
  }
  public void setAddressListService(IAddressListService addressListService) {
    this.addressListService = addressListService;
  }
  public static Log getLogger() {
    return logger;
  }

  public int getTotal() {
    return this.total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public String getBhxjFlag() {
    return this.bhxjFlag;
  }

  public void setBhxjFlag(String bhxjFlag) {
    this.bhxjFlag = bhxjFlag;
  }

  public IOrgService getiOrgService() {
    return this.iOrgService;
  }

  public void setiOrgService(IOrgService iOrgService) {
    this.iOrgService = iOrgService;
  }

  public void setAddressLists(List<AddressLists> addressLists) {
    this.addressLists = addressLists;
  }

  public List<AddressLists> getAddressLists() {
    return this.addressLists;
  }

  public String getDownload() {
    return this.download;
  }

  public void setDownload(String download) {
    this.download = download;
  }

  public String getXmlFilePath() {
    return this.xmlFilePath;
  }

  public void setXmlFilePath(String xmlFilePath) {
    this.xmlFilePath = xmlFilePath;
  }
}