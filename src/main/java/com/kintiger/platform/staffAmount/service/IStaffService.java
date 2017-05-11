package com.kintiger.platform.staffAmount.service;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.staffAmount.pojo.StaffAmount;
import com.kintiger.platform.station.pojo.Station;
import java.util.List;

public abstract interface IStaffService
{
  public static final String SUCCESS = "success";
  public static final String ERROR = "error";
  public static final String ERROR_MESSAGE = "操作失败！";
  public static final String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";
  public static final String ERROR_NULL_MESSAGE = "操作失败，单据已不存在！";
  public static final String MENU_REDIRECT_URL = "/menu/menuAction!redirectMenu.jspa?node=";

  public abstract int getStaffTotal(StaffAmount paramStaffAmount);

  public abstract List<StaffAmount> getStaffList(StaffAmount paramStaffAmount);

  public abstract StringResult updateStaffAmounts(StaffAmount paramStaffAmount);

  public abstract int getStaffAmountCount(StaffAmount paramStaffAmount);

  public abstract StringResult createStaff(StaffAmount paramStaffAmount);

  public abstract List<Station> blurSearchStaff(Station paramStation);

  public abstract int getStaffAmountCountU(StaffAmount paramStaffAmount);

  public abstract List<Station> geStaffUser(Station paramStation);
}

/* Location:           C:\Users\xxping\Desktop\basisPlatform\WEB-INF\classes\
 * Qualified Name:     com.kintiger.platform.staffAmount.service.IStaffService
 * JD-Core Version:    0.6.0
 */