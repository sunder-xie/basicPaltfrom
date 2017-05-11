package com.kintiger.platform.addressList.dao.impl;

import com.kintiger.platform.addressList.dao.IAddressListDao;
import com.kintiger.platform.addressList.pojo.AddressLists;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class AddressListDaoImpl extends BaseDaoImpl
  implements IAddressListDao
{
  public int searchAddressListCount(AddressLists addressList)
  {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
      "addresslist.searchAddressListsCount", addressList)).intValue();
  }

  public List<AddressLists> searchAddressLists(AddressLists addressList)
  {
    return getSqlMapClientTemplate().queryForList(
      "addresslist.searchAddressLists", addressList);
  }

  public String getOrgName(String orgId)
  {
    return (String)getSqlMapClientTemplate().queryForObject(
      "addresslist.getOrgName", orgId);
  }
  public List<AddressLists> searchMailLists(String userName)
  {
    return getSqlMapClientTemplate().queryForList(
      "addresslist.searchMailLists", userName);
  }
}