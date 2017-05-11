package com.kintiger.platform.addressList.service.impl;

import com.kintiger.platform.addressList.dao.IAddressListDao;
import com.kintiger.platform.addressList.pojo.AddressLists;
import com.kintiger.platform.addressList.service.IAddressListService;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.menu.service.impl.MenuServiceImpl;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AddressListServiceImpl
  implements IAddressListService
{
  private static final Log logger = LogFactory.getLog(MenuServiceImpl.class);
  private IAddressListDao addressListDao;

  public int searchAddressListCount(AddressLists addressList)
  {
    try
    {
      return this.addressListDao.searchAddressListCount(addressList);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(addressList), e);
    }

    return 0;
  }


  public List<AddressLists> searchAddressLists(AddressLists addressList)
  {
    try {
      return this.addressListDao.searchAddressLists(addressList);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(addressList), e);
    }
    return null;
  }

  public String getOrgName(String orgId)
  {
    try {
      return this.addressListDao.getOrgName(orgId);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(orgId), e);
    }
    return null;
  }
  public List<AddressLists> searchMailLists(String userName)
  {
    try {
      return this.addressListDao.searchMailLists(userName);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(userName), e);
    }
    return null;
  }
  public IAddressListDao getAddressListDao() {
    return this.addressListDao;
  }

  public void setAddressListDao(IAddressListDao addressListDao) {
    this.addressListDao = addressListDao;
  }
}