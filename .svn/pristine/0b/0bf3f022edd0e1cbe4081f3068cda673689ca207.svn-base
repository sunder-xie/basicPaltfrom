package com.kintiger.platform.base.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.SearchInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * Title:BaseAction 
 * @Description: basisPlatform
 * @author: xg.chen
 * @date:2016年12月20日 上午9:02:51
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 7674615559114195895L;

	public static final String CREATE = "create";
	public static final String UPDATE = "update";
	public static final String CREATE_PREPARE = "createPrepare";
	public static final String UPDATE_PREPARE = "updatePrepare";
	public static final String DELETE = "delete";
	public static final String LIST = "list";
	public static final String RESULT_MESSAGE = "resultMessage";
	public static final String JSON = "jsonresult";
	public static final String LOGOUT = "logout";
	public static final String LOGFAIL = "logfail";

	/**
	 * 当前页
	 */
	private int page;

	/**
	 * 每页显示记录数
	 */
	private int rows;

	/**
	 * 开始行
	 */
	private int start;

	/**
	 * 结束行
	 */
	private int end;

	/**
	 * 错误信息
	 */
	private String failMessage;

	/**
	 * 成功信息
	 */
	private String successMessage;

	/**
	 * 环境变量
	 */
	protected Properties env;

	protected HttpSession getSession() {
		return getServletRequest().getSession();
	}

	/**
	 * 取得HttpServletRequest对象.
	 * 
	 * @return HttpServletRequest对象.
	 */
	protected HttpServletRequest getServletRequest() {
		ActionContext ctx = ActionContext.getContext();
		return (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
	}

	/**
	 * 取得HttpServletResponse对象.
	 * 
	 * @return HttpServletResponse对象.
	 */
	protected HttpServletResponse getServletResponse() {
		ActionContext ctx = ActionContext.getContext();
		return (HttpServletResponse) ctx
				.get(ServletActionContext.HTTP_RESPONSE);
	}

	/**
	 * getUser
	 * 
	 * @return
	 */
	public AllUsers getUser() {
		AllUsers loginUser = (AllUsers) getSession().getAttribute(
				"ACEGI_SECURITY_LAST_LOGINUSER");
		return loginUser;
	}

	public <T extends SearchInfo> T getSearchInfo(T info) {
		info.setStart(((info.getPage() - 1) * info.getRows()));
		info.setEnd(info.getStart() + info.getRows());
		return info;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public Properties getEnv() {
		return env;
	}

	public void setEnv(Properties env) {
		this.env = env;
	}

	public int getStart() {
		return (page - 1) * rows;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return (page - 1) * rows + rows;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
