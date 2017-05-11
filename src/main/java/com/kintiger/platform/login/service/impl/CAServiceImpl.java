package com.kintiger.platform.login.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.service.IAllUserService;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.util.EncryptUtil;
import com.kintiger.platform.login.pojo.ValidateResult;
import com.kintiger.platform.login.service.ICAService;
import com.kintiger.platform.login.service.ILDAPService;

public class CAServiceImpl implements ICAService {

	private static final Log logger = LogFactory.getLog(CAServiceImpl.class);

	private IAllUserService allUserService;
	private ILDAPService LDAPService;

	public ValidateResult validateUser(String passport, String password) {
		return validateUser(passport, password, false);
	}

	public ValidateResult validateUserByLDAP(String passport, String password) {
		return validateUser(passport, password, true);
	}

	private ValidateResult validateUser(String passport, String password,
			boolean validate) {

		// ��ʼ������ֵ ״̬ = ʧ��
		ValidateResult result = new ValidateResult();
		result.setResultCode(ICAService.RESULT_FAILED);
		result.setMessage(ICAService.INCORRECT_LOGIN);

		// �˺Ż�����Ϊ��
		if (StringUtils.isEmpty(passport) || StringUtils.isEmpty(password)) {
			result.setMessage(ICAService.INCORRECT_NULL);
			return result;
		}

		// ����passport�����û���Ϣ
		AllUsers loginUser = allUserService.getAllUserByPassport(passport);

		// �жϵ�¼�û��Ƿ���OSAPϵͳ��
		if (loginUser == null) {
			result.setMessage(ICAService.INCORRECT_LOGINID);
			return result;
		}

		// ϵͳ����Ա����������֤ �������
		if ("admin".equals(passport) || "guest".equals(passport)) {
			try {
				if ((passport.equals(loginUser.getLoginId())
						|| passport.equals(loginUser.getMobile()) || passport
							.equals(loginUser.getPhone()))
						&& (EncryptUtil.md5Encry(password).equals(loginUser
								.getPassWd()))) {

					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			// ��֤ʧ��
			return result;
		}

		if ("V".equals(loginUser.getCustType())) {
			try {
				if ((passport.equals(loginUser.getLoginId())
						|| passport.equals(loginUser.getMobile()) || passport
							.equals(loginUser.getPhone()))
						&& (EncryptUtil.md5Encry(password).equals(loginUser
								.getPassWd()))) {

					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			// ��֤ʧ��
			return result;
		}

		// ����֤
		if (validate) {
			if (LDAPService.authenticate(passport, password)) {

				// �ж� ���˺����� �� ims �Ƿ�һ�� / ��һ��ʱ �޸�ims����
				try {
					String pw = EncryptUtil.md5Encry(password);

					// ����һ�� -> �ɹ�
					if (pw.equals(loginUser.getPassWd())) {
						return setSuccessResult(result, loginUser);
					} else {
						AllUsers allUsers = new AllUsers();
						allUsers.setUserId(loginUser.getUserId());
						allUsers.setPassWd(pw);

						BooleanResult r = allUserService
								.updateAllUser(allUsers);
						// �޸ĳɹ� -> �ɹ�
						// �޸�ʧ�� -> ���µ�¼
						if (r.getResult()) {
							return setSuccessResult(result, loginUser);
						} else {
							logger.error("passport:" + passport
									+ " ���˺�������IMS��һ��");
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		} else {
			try {
				if (passport.equals(loginUser.getLoginId())
						&& EncryptUtil.md5Encry(password).equals(
								loginUser.getPassWd())) {
					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return result;
	}

	public ValidateResult validateToken(String token) {
		ValidateResult result = new ValidateResult();
		/*
		 * result.setResultCode(ICAService.RESULT_FAILED);
		 * result.setMessage(ICAService.INCORRECT_TOKEN);
		 * 
		 * try { AllUsers user = (AllUsers) memcachedCacheService.get(token); if
		 * (user != null) { // ������֤һ�κ� ʧЧ memcachedCacheService.remove(token);
		 * return setSuccessResult(result, user); } } catch (Exception e) { }
		 */
		return result;
	}

	public String generateToken(Object object) {
		/*
		 * try { String token = OidUtil.newId();
		 * memcachedCacheService.add(token, object,
		 * IMemcachedCacheService.CACHE_KEY_SSO_TOKEN_DEFAULT_EXP);
		 * 
		 * return token; } catch (Exception e) {
		 * logger.error(LogUtil.parserBean(object), e); }
		 */

		return null;
	}

	private ValidateResult setSuccessResult(ValidateResult result, AllUsers user) {
		result.setResultCode(ICAService.RESULT_SUCCESS);
		user.setPassWd(null);
		result.setAllUser(user);
		result.setMessage(null);
		return result;
	}

	public void loginLog(AllUsers loginInfo) {
		try {
			allUserService.loginLog(loginInfo);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * �ж��Ƿ��Ǿ�����
	 */
	public AllUsers getAllUserByPassport(String passport) {
		try {
				return allUserService.getAllUserByPassport(passport);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}

	public ILDAPService getLDAPService() {
		return LDAPService;
	}

	public void setLDAPService(ILDAPService lDAPService) {
		LDAPService = lDAPService;
	}

}
