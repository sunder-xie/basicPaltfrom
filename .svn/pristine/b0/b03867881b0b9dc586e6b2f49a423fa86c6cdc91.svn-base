package com.kintiger.platform.ireport.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.framework.util.FileUtil;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.ireport.dao.IreportDao;
import com.kintiger.platform.ireport.pojo.IreportType;
import com.kintiger.platform.ireport.service.IreportService;
import com.kintiger.platform.menu.service.impl.MenuServiceImpl;

public class ReportServiceImpl implements IreportService {
	private String ireportpath;
	private static final Log logger = LogFactory.getLog(ReportServiceImpl.class);
	IreportDao reportDao ;

	public List<IreportType> serachReportModle(IreportType type) {
		try {
			return reportDao.serachReportModle(type);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(type), e);
		}
		return null;
	}
	public  boolean saveFile(File uploadfile,String uploadfileFileName,File[] modlefiles,String[] modlefilesFileName,String savePath){

		File savedir=new File(ireportpath+"/"+savePath);
		// 如果目录不存在，则新建
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		
		FileUtil.saveAsFile(
				uploadfile, new File(ireportpath+"/"+savePath+"/"+uploadfileFileName));
		if(modlefiles!=null&&modlefiles.length>0){
			for (int i = 0; i < modlefiles.length; i++) {
				if(modlefilesFileName.length>i){
					FileUtil.saveAsFile(modlefiles[i], new File(ireportpath+"/"+savePath+"/"+modlefilesFileName[i]));
				}
			}
		}
		return true;
	}
	public  IreportType getModleIs(IreportType type ){
				  try {
					List<IreportType> serachReportModle =serachReportModle(type);	
					return serachReportModle.get(0);
					
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(type), e);	
				}
				  return null;
	}


	public Long saveReportModle(IreportType type) {
		try {
			return reportDao.saveReportModle(type);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(type), e);
		}
		return null;
	}

	public int deleteReportModle(IreportType type) {
		try {
			//修改数据库信息 is_delete 设置为1
			return reportDao.deleteReportModle(type);
			
			
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(type), e);
		}
		return 0;
	}


	public int modifyReportModle(IreportType type) {
		try {
			return reportDao.modifyReportModle(type);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(type), e);
		}
		return 0;
	}
	public String getIreportpath() {
		return ireportpath;
	}
	public void setIreportpath(String ireportpath) {
		this.ireportpath = ireportpath;
	}
	public IreportDao getReportDao() {
		return reportDao;
	}


	public void setReportDao(IreportDao reportDao) {
		this.reportDao = reportDao;
	}

}
