 package com.kintiger.platform.staffAmount.pojo;
 
 import com.kintiger.platform.base.pojo.SearchInfo;
 import java.util.Date;
 
 public class StaffAmount extends SearchInfo
 {
   private static final long serialVersionUID = 8952907243103537613L;
   private Long PId;
   private String[] PIds;
   private Long orgId;
   private String stationId;
   private Long amount;//编制数
   private Long amountU;
   private Long mountC;
   private Date lastModify;
   private String state;
   private String orgName;
   private String stationName;
   private String positionTypeName;
   private String[] orgIdarrs;
   private String last_Modify;
   private String amounts;
   private String orgParentName;
   private Long companyId;
   private String companyName;
   private Long rzamount;//人资设定编制数

   
   
   public Long getPId()
   {
/*  33 */     return this.PId;
   }
 
   public void setPId(Long pId) {
/*  37 */     this.PId = pId;
   }
 
   public Long getOrgId() {
/*  41 */     return this.orgId;
   }
 
   public void setOrgId(Long orgId) {
/*  45 */     this.orgId = orgId;
   }
 
   public String getStationId() {
/*  49 */     return this.stationId;
   }
 
   public void setStationId(String stationId) {
/*  53 */     this.stationId = stationId;
   }
 
   public Long getAmount() {
/*  57 */     return this.amount;
   }
 
   public void setAmount(Long amount) {
/*  61 */     this.amount = amount;
   }
 
   public Date getLastModify() {
/*  65 */     return this.lastModify;
   }
 
   public void setLastModify(Date lastModify) {
/*  69 */     this.lastModify = lastModify;
   }
 
   public String getState() {
/*  73 */     return this.state;
   }
 
   public void setState(String state) {
/*  77 */     this.state = state;
   }
 
   public String getOrgName() {
/*  81 */     return this.orgName;
   }
 
   public void setOrgName(String orgName) {
/*  85 */     this.orgName = orgName;
   }
 
   public String getStationName() {
/*  89 */     return this.stationName;
   }
 
   public void setStationName(String stationName) {
/*  93 */     this.stationName = stationName;
   }
 
   public String getPositionTypeName() {
/*  97 */     return this.positionTypeName;
   }
 
   public void setPositionTypeName(String positionTypeName) {
/* 101 */     this.positionTypeName = positionTypeName;
   }
 
   public String[] getOrgIdarrs() {
/* 105 */     return this.orgIdarrs;
   }
 
   public void setOrgIdarrs(String[] orgIdarrs) {
/* 109 */     this.orgIdarrs = orgIdarrs;
   }
 
   public String getLast_Modify() {
/* 113 */     return this.last_Modify;
   }
 
   public void setLast_Modify(String last_Modify) {
/* 117 */     this.last_Modify = last_Modify;
   }
 
   public String getAmounts() {
/* 121 */     return this.amounts;
   }
 
   public void setAmounts(String amounts) {
/* 125 */     this.amounts = amounts;
   }
 
   public Long getCompanyId() {
/* 129 */     return this.companyId;
   }
 
   public void setCompanyId(Long companyId) {
/* 133 */     this.companyId = companyId;
   }
 
   public String getCompanyName() {
/* 137 */     return this.companyName;
   }
 
   public void setCompanyName(String companyName) {
/* 141 */     this.companyName = companyName;
   }
 
   public String getOrgParentName() {
/* 145 */     return this.orgParentName;
   }
 
   public void setOrgParentName(String orgParentName) {
/* 149 */     this.orgParentName = orgParentName;
   }
 
   public String[] getPIds() {
/* 153 */     return this.PIds;
   }
 
   public void setPIds(String[] pIds) {
/* 157 */     this.PIds = pIds;
   }
 
   public Long getAmountU() {
/* 161 */     return this.amountU;
   }
 
   public void setAmountU(Long amountU) {
/* 165 */     this.amountU = amountU;
   }
 
   public static long getSerialversionuid() {
/* 169 */     return 8952907243103537613L;
   }
 
   public Long getMountC() {
/* 173 */     return this.mountC;
   }
 
   public void setMountC(Long mountC) {
/* 177 */     this.mountC = mountC;
   }
 }

/* Location:           C:\Users\xxping\Desktop\basisPlatform\WEB-INF\classes\
 * Qualified Name:     com.kintiger.platform.staffAmount.pojo.StaffAmount
 * JD-Core Version:    0.6.0
 */