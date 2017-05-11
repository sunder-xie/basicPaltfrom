package com.kintiger.platform.addressList.service;

import com.kintiger.platform.addressList.pojo.AddressLists;

import java.util.List;

public abstract interface IAddressListService
{
  public abstract int searchAddressListCount(AddressLists paramAddressLists);

  public abstract List<AddressLists> searchAddressLists(AddressLists paramAddressLists);

  public abstract String getOrgName(String paramString);

public abstract List<AddressLists> searchMailLists(String userName);
}