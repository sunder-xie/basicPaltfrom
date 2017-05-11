package com.kintiger.platform.allUser.dao.impl;

import java.util.List;

import com.kintiger.platform.allUser.dao.IAllUserDao;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.pojo.Kunnr;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;

public class AllUserDaoImpl extends BaseDaoImpl implements IAllUserDao {

	@SuppressWarnings("unchecked")
	public List<AllUsers> searchAllUsers(AllUsers alluser) {
		return getSqlMapClientTemplate().queryForList("alluser.searchAllUsers",
				alluser);
	}
	@SuppressWarnings("unchecked")
	public List<AllUsers> searchAllUsers_all(AllUsers alluser) {
		return getSqlMapClientTemplate().queryForList("alluser.searchAllUsers_all",
				alluser);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> searchKunnur_all(AllUsers alluser) {
		return getSqlMapClientTemplate().queryForList("alluser.searchKunnur_all",
				alluser);
	}

	public int searchAllUsersCount(AllUsers alluser) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"alluser.searchAllUsersCount", alluser);
	}
	@SuppressWarnings("unchecked")
	public List<AllUsers> getEmpListByOrgId(String orgId) {
		Long org_id = Long.valueOf(orgId);
		return (List<AllUsers>) getSqlMapClientTemplate().queryForList(
				"alluser.getEmpListByOrgId", org_id);
	}

	public int deleteUserByEmpId(AllUsers allUsers) {
		return (Integer) getSqlMapClientTemplate().delete(
				"alluser.deleteUserByEmpId", allUsers);
	}

	public AllUsers getAllUserByPassport(String passport) {
		return (AllUsers) getSqlMapClientTemplate().queryForObject(
				"alluser.getAllUserByPassport", passport);
	}

	public int updateAllUser(AllUsers allUsers) {
		return getSqlMapClientTemplate().update("alluser.updateAllUser",
				allUsers);
	}

	public int getAllUserByLoginId(String loginId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"alluser.getAllUserByLoginId", loginId);
	}

	public int getEmpCount(AllUsers allUsers) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"alluser.getEmpCount", allUsers);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> getEmpList(AllUsers alluser) {
		return getSqlMapClientTemplate().queryForList("alluser.getEmpList",
				alluser);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> getEmpList4Code(AllUsers alluser) {
		return getSqlMapClientTemplate().queryForList(
				"alluser.getEmpList4Code", alluser);
	}

	public long createUser(AllUsers allUsers) {
		return (Long) getSqlMapClientTemplate().insert("alluser.createUser",
				allUsers);
	}

	public AllUsers getAllUsersByUserId(String ids) {
		return (AllUsers) getSqlMapClientTemplate().queryForObject(
				"alluser.getAllUsersByUserId", ids);
	}

	public String getEmpNameByUserId(String userId) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"alluser.getEmpNameByUserId", userId);
	}

	public int forbidden(AllUsers allUsers) {
		return getSqlMapClientTemplate().update("alluser.forbidden", allUsers);
	}

	public int updateAllUserOrg(AllUsers allUsers) {
		return getSqlMapClientTemplate().update("alluser.updateAllUserOrg",
				allUsers);
	}

	public AllUsers getAllUser(String userId) {
		return (AllUsers) getSqlMapClientTemplate().queryForObject(
				"alluser.getAllUser", userId);
	}

	public int updateUserInfo(AllUsers allUsers) {
		return getSqlMapClientTemplate().update("alluser.updateUserInfo",
				allUsers);
	}

	public long updatePwd(AllUsers allUsers) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("alluser.updatePwd", allUsers);
	}

	public void loginLog(AllUsers loginInfo) {
		getSqlMapClientTemplate().insert("alluser.loginLog", loginInfo);
	}

	@SuppressWarnings("unchecked")
	public List<String> getLoginOrgs(AllUsers allUsers) {
		return (List<String>) getSqlMapClientTemplate().queryForList(
				"alluser.getLoginOrgs", allUsers);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> searchAllKunnrUsers(AllUsers alluser) {
		return getSqlMapClientTemplate().queryForList(
				"alluser.searchAllKunnrUsers", alluser);
	}

	public int searchAllKunnrUsersCount(AllUsers alluser) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"alluser.searchAllKunnrUsersCount", alluser);
	}

	public long createKunnrUser(AllUsers allUsers) {
		return (Long) getSqlMapClientTemplate().insert(
				"alluser.createKunnrUser", allUsers);
	}

	public long updateKunnrPwd(AllUsers allUsers) {
		return getSqlMapClientTemplate().update("alluser.updateKunnrPwd",
				allUsers);
	}

	public AllUsers getAllKunnrUsersByUserId(String ids) {
		return (AllUsers) getSqlMapClientTemplate().queryForObject(
				"alluser.getAllKunnrUsersByUserId", ids);
	}

	public int updateKunnrUserInfo(AllUsers allUsers) {
		return getSqlMapClientTemplate().update("alluser.updateKunnrUserInfo",
				allUsers);

	}

	public int forKunnrbidden(AllUsers allUsers) {
		return getSqlMapClientTemplate().update("alluser.forKunnrbidden",
				allUsers);
	}

	public long createKunnrUserRole(AllUsers allUsers) {
		return (Long) getSqlMapClientTemplate().insert(
				"alluser.selectRole4User", allUsers);
	}

	public int kunnrSearchCount(Kunnr kunnr) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"alluser.kunnrSearchCount", kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<Kunnr> kunnrSearch(Kunnr kunnr) {
		return getSqlMapClientTemplate().queryForList("alluser.kunnrSearch",
				kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> getAllUserByMobile(AllUsers allUsers) {
		return getSqlMapClientTemplate().queryForList("alluser.getAllUserByMobile",
				allUsers);
	}
	
	public int updateKunnrMobile(AllUsers allUsers){
		return getSqlMapClientTemplate().update("alluser.updateKunnrMobile",
				allUsers);
	}

}
