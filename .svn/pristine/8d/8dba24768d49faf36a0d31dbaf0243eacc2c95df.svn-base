package com.kintiger.platform.allUser.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.allUser.dao.IAllUserDao;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.pojo.Kunnr;
import com.kintiger.platform.allUser.service.IAllUserService;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.conpoint.service.IConpointService;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.menu.service.impl.MenuServiceImpl;

public class AllUserServiceImpl implements IAllUserService {

	private static final Log logger = LogFactory.getLog(MenuServiceImpl.class);
	private IAllUserDao allUserDao;

	public List<AllUsers> searchAllUsers(AllUsers alluser) {
		try {
			return allUserDao.searchAllUsers(alluser);
		} catch (Exception e) {
			logger.error(alluser, e);
		}

		return null;
	}
	public List<AllUsers> searchAllUsers_all(AllUsers alluser) {
		try {
			return allUserDao.searchAllUsers_all(alluser);
		} catch (Exception e) {
			logger.error(alluser, e);
		}

		return null;
	}
	public List<AllUsers> searchKunnur_all(AllUsers alluser) {
		try {
			return allUserDao.searchKunnur_all(alluser);
		} catch (Exception e) {
			logger.error(alluser, e);
		}

		return null;
	}
	/**
	 * 根据组织ID查找人员信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getEmpListByOrgId(String orgId) {
		try {
			return allUserDao.getEmpListByOrgId(orgId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public int searchAllUsersCount(AllUsers alluser) {
		try {
			return allUserDao.searchAllUsersCount(alluser);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(alluser), e);
		}

		return 0;
	}

	public StringResult deleteUserByEmpId(AllUsers allUsers) {
		StringResult result = new StringResult();
		result.setCode(IConpointService.ERROR);
		result.setResult(IConpointService.ERROR_MESSAGE);
		try {
			int c = allUserDao.deleteUserByEmpId(allUsers);
			result.setResult(String.valueOf(c));
			result.setCode(IConpointService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUsers), e);
		}
		return result;
	}

	public AllUsers getAllUserByPassport(String passport) {
		if (StringUtils.isEmpty(passport)) {
			return null;
		}
		try {
			AllUsers user = allUserDao.getAllUserByPassport(passport);
			List<String> orgs = allUserDao.getLoginOrgs(user);
			user.setAllOrg((String[]) orgs.toArray(new String[orgs.size()]));
			return user;
		} catch (Exception e) {
			logger.error(passport, e);
		}

		return null;
	}

	public BooleanResult updateAllUser(AllUsers allUsers) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		try {
			int c = allUserDao.updateAllUser(allUsers);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			result.setCode("密码重置失败");
			logger.error(LogUtil.parserBean(allUsers), e);
		}

		return result;
	}

	public String getAllUserByLoginId(String loginId) {
		int n;
		try {
			n = allUserDao.getAllUserByLoginId(loginId);
			if (n >= 1) {
				return "exist";
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(loginId), e);
		}
		return "unexist";
	}

	public int getEmpCount(AllUsers allUser) {
		try {
			return allUserDao.getEmpCount(allUser);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUser), e);
		}

		return 0;
	}

	public List<AllUsers> getEmpList(AllUsers allUser) {
		try {
			return allUserDao.getEmpList(allUser);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUser), e);
			return null;
		}

	}

	public List<AllUsers> getEmpList4Code(AllUsers allUser) {
		try {
			return allUserDao.getEmpList4Code(allUser);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUser), e);
			return null;
		}

	}

	public BooleanResult createUser(AllUsers allUsers) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long empId = allUserDao.createUser(allUsers);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(empId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("人员创建失败");
			logger.error(LogUtil.parserBean(allUsers), e);
		}

		return booleanResult;
	}

	public AllUsers getAllUsersByUserId(String ids) {
		try {
			return allUserDao.getAllUsersByUserId(ids);
		} catch (Exception e) {
			return null;
		}
	}

	public IAllUserDao getAllUserDao() {
		return allUserDao;
	}

	public void setAllUserDao(IAllUserDao allUserDao) {
		this.allUserDao = allUserDao;
	}

	public String getEmpNameByUserId(String userId) {
		try {
			return allUserDao.getEmpNameByUserId(userId);
		} catch (Exception e) {
			return null;
		}
	}

	public StringResult forbidden(AllUsers allUsers) {
		StringResult result = new StringResult();
		result.setCode(IConpointService.ERROR);
		result.setResult(IConpointService.ERROR_MESSAGE);
		try {
			int c = allUserDao.forbidden(allUsers);
			if (c == 1) {
				result.setCode(IConpointService.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUsers), e);
		}
		return result;
	}

	public int updateAllUserOrg(AllUsers allUsers) {
		try {
			return allUserDao.updateAllUserOrg(allUsers);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUsers), e);
		}
		return 0;
	}

	public AllUsers getAllUser(String userId) {
		try {
			return allUserDao.getAllUser(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	public BooleanResult updateUserInfo(AllUsers allUsers) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult.setResult(false);
		booleanResult.setCode(IConpointService.ERROR);
		try {
			int n = allUserDao.updateUserInfo(allUsers);
			if (n == 1) {
				booleanResult.setResult(true);
				booleanResult.setCode("人员信息修改成功");
			}
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("人员信息修改失败");
			logger.error(LogUtil.parserBean(allUsers), e);
		}

		return booleanResult;
	}

	public StringResult updatePwd(AllUsers allUsers) {
		StringResult stringResult = new StringResult();
		try {
			long i = allUserDao.updatePwd(allUsers);
			stringResult.setResult(String.valueOf(i));
			stringResult.setCode(IConpointService.SUCCESS);
		} catch (Exception e) {
			stringResult.setCode(IConpointService.ERROR);
			logger.error(LogUtil.parserBean(allUsers), e);
		}
		return stringResult;
	}

	public void loginLog(AllUsers loginInfo) {
		try {
			allUserDao.loginLog(loginInfo);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public List<AllUsers> searchAllKunnrUsers(AllUsers alluser) {
		try {
			return allUserDao.searchAllKunnrUsers(alluser);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(alluser), e);

		}// TODO Auto-generated method stub
		return null;
	}

	public int searchAllKunnrUsersCount(AllUsers alluser) {
		try {
			return allUserDao.searchAllKunnrUsersCount(alluser);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(alluser), e);
		}//
		return 0;
	}

	public BooleanResult createKunnrUser(AllUsers allUsers) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long empId = allUserDao.createKunnrUser(allUsers);
			allUsers.setUserId("" + empId);
			allUserDao.createKunnrUserRole(allUsers);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(empId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("人员创建失败");
			logger.error(LogUtil.parserBean(allUsers), e);
		}
		return booleanResult;
	}

	public StringResult updateKunnrPwd(AllUsers allUsers) {
		StringResult stringResult = new StringResult();
		try {
			long i = allUserDao.updateKunnrPwd(allUsers);
			stringResult.setResult(String.valueOf(i));
			stringResult.setCode(IConpointService.SUCCESS);
		} catch (Exception e) {
			stringResult.setCode(IConpointService.ERROR);
			logger.error(LogUtil.parserBean(allUsers), e);
		}
		return stringResult;
	}

	public AllUsers getAllKunnrUsersByUserId(String ids) {
		try {
			return allUserDao.getAllKunnrUsersByUserId(ids);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(ids), e);
		}
		return null;
	}

	public BooleanResult updateKunnrUserInfo(AllUsers allUsers) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult.setResult(false);
		booleanResult.setCode(IConpointService.ERROR);
		try {
			int n = allUserDao.updateKunnrUserInfo(allUsers);
			if (n == 1) {
				booleanResult.setResult(true);
				booleanResult.setCode("修改信息成功！");
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUsers), e);
			booleanResult.setResult(true);
			booleanResult.setCode("修改信息失败！");
		}
		return booleanResult;
	}

	public StringResult forKunnrbidden(AllUsers allUsers) {
		StringResult result = new StringResult();
		result.setCode(IConpointService.ERROR);
		result.setResult(IConpointService.ERROR_MESSAGE);
		try {
			int c = allUserDao.forKunnrbidden(allUsers);
			if (c == 1) {
				result.setCode(IConpointService.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUsers), e);
		}
		return result;
	}

	public int kunnrSearchCount(Kunnr kunnr) {
		try {
			return allUserDao.kunnrSearchCount(kunnr);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(kunnr), e);
		}
		return 0;
	}

	public List<Kunnr> kunnrSearch(Kunnr kunnr) {
		try {
			return allUserDao.kunnrSearch(kunnr);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(kunnr), e);
		}
		return null;
	}

	public List<AllUsers> getAllUserByMobile(AllUsers allUsers) {
		try {
			return allUserDao.getAllUserByMobile(allUsers);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUsers), e);
			return null;
		}
		
	}
	
	public int updateKunnrMobile(AllUsers allUsers){
		try {
			return allUserDao.updateKunnrMobile(allUsers);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(allUsers), e);
			return 0;
		}
	}

}
