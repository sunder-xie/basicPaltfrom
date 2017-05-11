 package com.kintiger.platform.staffAmount.dao.impl;
 
 import com.kintiger.platform.allUser.pojo.AllUsers;
 import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
 import com.kintiger.platform.staffAmount.dao.IStaffDao;
 import com.kintiger.platform.staffAmount.pojo.StaffAmount;
 import com.kintiger.platform.station.pojo.Station;
 import java.util.ArrayList;
 import java.util.List;
 import org.springframework.orm.ibatis.SqlMapClientTemplate;
 
 public class StaffDaoImpl extends BaseDaoImpl
   implements IStaffDao
 {
   public int getStaffTotal(StaffAmount s)
   {
/*  14 */     return ((Integer)getSqlMapClientTemplate().queryForObject("staffamount.getStaffAmountsCount", s)).intValue();
   }
 
   public List<StaffAmount> getStaffList(StaffAmount s) {
/*  18 */     return getSqlMapClientTemplate().queryForList("staffamount.getStaffAmounts", s);
   }
 
   public int updateStaffAmounts(StaffAmount staffAmount) {
/*  22 */     List list = new ArrayList();
/*  23 */     list = getSqlMapClientTemplate().queryForList("staffamount.getStaffAmountByPid", staffAmount);

			  long dNum = ((StaffAmount)list.get(0)).getAmount().longValue() - staffAmount.getAmount().longValue();
			  List dList = new ArrayList();
			  Station ist = new Station();
			  ist.setStationId(((StaffAmount)list.get(0)).getStationId());
			  ist.setOrgId(((StaffAmount)list.get(0)).getOrgId());
			  dList = getSqlMapClientTemplate().queryForList("station.getDeleteStationCount", ist);
			  int dListNum = dList.size();
			  if(dNum>dListNum){
				  return -1;
			  }
			  
/*  24 */     int y = getSqlMapClientTemplate().update("staffamount.updateStaffAmounts", staffAmount);
/*  25 */     if (y != 0) {
/*  26 */       if (staffAmount.getAmount() == null) {
/*  27 */         List liststa = new ArrayList();
/*  28 */         liststa = getSqlMapClientTemplate().queryForList("staffamount.getStaffAmountByPid", staffAmount);
 
/*  30 */         for (int i = 0; i < liststa.size(); i++) {
/*  31 */           Station station = new Station();
/*  32 */           station.setStationId(((StaffAmount)liststa.get(i)).getStationId());
/*  33 */           station.setOrgId(((StaffAmount)liststa.get(i)).getOrgId());
/*  34 */           getSqlMapClientTemplate().delete("station.deleteStationUser", station);
         }
       } else {
/*  37 */         long num = ((StaffAmount)list.get(0)).getAmount().longValue() - staffAmount.getAmount().longValue();
/*  38 */         if (num > 0L) {
/*  39 */           Station st = new Station();
/*  40 */           st.setStationId(((StaffAmount)list.get(0)).getStationId());
/*  41 */           st.setOrgId(((StaffAmount)list.get(0)).getOrgId());
/*  42 */           List list_sta = new ArrayList();
/*  43 */           list_sta = getSqlMapClientTemplate().queryForList("station.getDeleteStationCount", st);
/*  44 */           int listStation = list_sta.size();
/*  45 */           if (num <= listStation){
/*  46 */             for (int i = 0; i < num; i++) {
/*  47 */               Station station = new Station();
/*  48 */               station.setCompId(((Station)list_sta.get(i)).getCompId());
/*  49 */               getSqlMapClientTemplate().delete("station.deleteStationUserById2", station);
             			}
					}else{
/*  52 */             for (int i = 0; i < listStation; i++) {
/*  53 */               Station station = new Station();
/*  54 */               station.setCompId(((Station)list_sta.get(i)).getCompId());
/*  55 */               getSqlMapClientTemplate().delete("station.deleteStationUserById2", station);
						}
             }
         } else {
/*  58 */           for (int i = 0; i < -num; i++) {
/*  59 */             AllUsers allUsers = new AllUsers();
/*  60 */             allUsers.setRoleIds(((StaffAmount)list.get(0)).getStationId());
/*  61 */             allUsers.setUserId("");
/*  62 */             allUsers.setOrgId(((StaffAmount)list.get(0)).getOrgId().toString());
/*  63 */             getSqlMapClientTemplate().insert("station.createStationUser", allUsers);
           }
         }
       }
     }
/*  68 */     return y;
   }
 
   public int getStaffAmountCount(StaffAmount staffAmount) {
/*  72 */     return ((Integer)getSqlMapClientTemplate().queryForObject("staffamount.getStaffAmountCount", staffAmount))
/*  73 */       .intValue();
   }
 
   public Long createStaff(StaffAmount staffAmount) {
/*  77 */     long l = ((Long)getSqlMapClientTemplate().insert("staffamount.createStaffAmount", staffAmount)).longValue();
/*  78 */     if (l != 0L) {
/*  79 */       for (int i = 0; i < staffAmount.getAmount().longValue(); i++) {
/*  80 */         AllUsers allUsers = new AllUsers();
/*  81 */         allUsers.setRoleIds(staffAmount.getStationId());
/*  82 */         allUsers.setUserId("");
/*  83 */         allUsers.setOrgId(staffAmount.getOrgId().toString().trim());
/*  84 */         getSqlMapClientTemplate().insert("station.createStationUser", allUsers);
       }
     }
/*  87 */     return Long.valueOf(l);
   }
 
   public List<Station> blurSearchStaff(Station station) {
/*  91 */     return getSqlMapClientTemplate().queryForList("staffamount.blurSearchStaff", station);
   }
 
   public int getStaffAmountCountU(StaffAmount staffAmount) {
/*  95 */     return ((Integer)getSqlMapClientTemplate().queryForObject("staffamount.getStaffAmountCountU", staffAmount))
/*  96 */       .intValue();
   }
 
   public List<Station> geStaffUser(Station paramStation) {
/* 100 */     return getSqlMapClientTemplate().queryForList("staffamount.geStaffUser", paramStation);
   }
 }

/* Location:           C:\Users\xxping\Desktop\basisPlatform\WEB-INF\classes\
 * Qualified Name:     com.kintiger.platform.staffAmount.dao.impl.StaffDaoImpl
 * JD-Core Version:    0.6.0
 */