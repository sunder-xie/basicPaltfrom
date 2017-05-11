package com.kintiger.platform.ireport.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.FileUtil;
import com.kintiger.platform.ireport.pojo.IreportType;
import com.kintiger.platform.ireport.service.IreportService;
import com.kintiger.platform.ireport.service.impl.ReportServiceImpl;
import com.kintiger.platform.ireport.util.IreportUtil;
import com.kintiger.platform.ireport.util.JDBCConnection;
import com.kintiger.platform.ireport.util.ReportDataSource;


public class IreportAction extends BaseAction   {
	private Log logger = LogFactory.getLog(IreportAction.class);

	private static final long serialVersionUID = -1872868236628398675L;
	List<IreportType> modleList;

	private File upload;//主报表
	private String uploadFileName;//主报表名称
	private String remain;//备注：资产负债表
	private String bid;//报表ID
	private int pageIndex=1;//分页
	private int lastPage;//末页
	private String parameters;//查询参数
	private File[] files;//子报表模板
	private String ireportpath;//存放模板路径
	private static String download;//判断是否下载完参数
	private IreportType modifytype;//修改数据
	public IreportType getModifytype() {
		return modifytype;
	}
	public void setModifytype(IreportType modifytype) {
		this.modifytype = modifytype;
	}
	public String getIreportpath() {
		return ireportpath;
	}
	public void setIreportpath(String ireportpath) {
		this.ireportpath = ireportpath;
	}
	public String[] getFilesFileName() {
		return filesFileName;
	}
	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}
	private String[] filesFileName;//
	public File[] getFiles() {
		return files;
	}
	public void setFiles(File[] files) {
		this.files = files;
	}
	public String getIs_pagination() {
		return is_pagination;
	}
	public void setIs_pagination(String is_pagination) {
		this.is_pagination = is_pagination;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	private String  is_pagination;//是否分页
	private int pageNum;
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	ReportServiceImpl reportService ;
	private AllUsers allUser;

	
	
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * 跳转到报表页面
	 * @return
	 */
	public String toReport(){
		return "succ";
	}
	/**
	 * 跳转到报表上传管理页面
	 * @return
	 */
	public String toManager(){
		return "manager";
	}
	/**
	 * 跳转到模板上传页面
	 * @return
	 */
	public String toUploadReport(){
		allUser = this.getUser();
		return "toupload";
	}
	/**
	 * 跳转到模板修改页面
	 * @return
	 */
	public String toModifyReport(){
		try {
			IreportType type = new IreportType();
			type.setID(bid);
			modleList=reportService.serachReportModle(type);
			if(modleList!=null&&modleList.size()>0){
				modifytype=modleList.get(0);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return "modify";
	}
	public AllUsers getAllUser() {
		return allUser;
	}
	public void setAllUser(AllUsers allUser) {
		this.allUser = allUser;
	}
	/**
	 * 修改模板数据
	 */
	public void modify(){
		IreportType type = new IreportType();
		type.setID(bid);
		
		type.setREMAN(remain);
		type.setIs_pagination(is_pagination);
		if(is_pagination!=null&&is_pagination.equals("1")){
			type.setPageNum(0);
		}
		type.setPageNum(pageNum);
		
		reportService.modifyReportModle(type);
		
	}
	/**
	 * 查询上传的模板
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "modleList", include = { "ID","FILENAME","UPLOADER","REMAN","UPLOADTIME","pageNum","is_pagination","modleFilename" })
	public String seachModleList(){
		
		try {
			IreportType type = new IreportType();
			type.setFILENAME(uploadFileName);
			modleList=reportService.serachReportModle(type);
		} catch (Exception e) {
			logger.error(e);
		}
		return JSON;
	}
	/**
	 * 删除模板
	 */
	public void deleteModle(){
		IreportType type = new IreportType();
		type.setID(bid);
		type.setIS_DELETE("1");
		reportService.deleteReportModle(type);
	}
	/**
	 * 上传报表模板
	 */
	public void saveModle(){
		try {
			IreportType type = new IreportType();
			Date date = new Date();
			String id=date.getTime()+"";
			type.setID(id);
			type.setREMAN(remain);
			type.setFILENAME(uploadFileName);
			type.setIs_pagination(is_pagination);
			type.setIS_DELETE("0");
			
			if(is_pagination!=null&&is_pagination.equals("0")){
				type.setPageNum(pageNum);
			}
			
			String modlefilename="";
			if(filesFileName!=null){
				for (int i = 0; i < filesFileName.length; i++) {
					modlefilename=modlefilename+filesFileName[i];
					if(i!=filesFileName.length-1){
						modlefilename=modlefilename+",";
					}
				}
			}
			  type.setModleFilename(modlefilename);
			  type.setUPLOADER(this.getUser().getUserName());
			  type.setUPLOADERID(this.getUser().getUserId());
			  type.setUPLOADTIME(date);
			  reportService.saveReportModle(type);
			  reportService.saveFile(upload,uploadFileName, files,filesFileName, id);
		} catch (Exception e) {
			logger.error(e);
		}

	}
	/**
	 * 表格数据html显示
	 * --------弃用--------------
	 */           
	public void exportHtml2(){
		HttpServletResponse response = ServletActionContext
				.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		try{
			IreportType type = new IreportType();
			type.setID(bid);
			IreportType returnmodle = reportService.getModleIs(type);
			InputStream is = returnmodle.getIFILE();
			
		
			ServletOutputStream outPut = response.getOutputStream();
		
		

				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ireportpath+"/"+bid+"/"+returnmodle.getFILENAME());
				
			
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, null, JDBCConnection .getConnection());
			response.setContentType("text/html;charset=UTF-8");

			request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,jasperPrint);
			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"image?image=");
			exporter.setParameter(JRHtmlExporterParameter.PAGE_INDEX,pageIndex);
			
			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "<a>aaa</a>");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "HTML_FOOTER");
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "21");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outPut);
			exporter.exportReport();
			outPut.flush();
		}catch(Exception ex){
			logger.error(ex);
		}
	}
	/**
	 * 用于柱形图 饼图html输出
	 * @return
	 */
	public void exportHtml(){
		try{
			download="NO";
			
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();	
			request.getSession().setAttribute("download", download);
		IreportType type = new IreportType();
		type.setID(bid);
		
		IreportType returnmodle = reportService.getModleIs(type);

		if(returnmodle!=null){
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ireportpath+"/"+bid+"/"+returnmodle.getFILENAME());
			/*
			 * 获取参数
			 */
			Map parMap=this.getuserparameters(jasperReport,returnmodle);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport,parMap, JDBCConnection .getConnection());
		
			if(jasperPrint!=null){
				//设置格式
				response.setContentType("text/html;charset=UTF-8");
				//获得输出流
				PrintWriter printWriter = response.getWriter();
				//创建JRHtmlExporter对象
				JRHtmlExporter htmlExporter = new JRHtmlExporter();
			
		
				//把jasperPrint到Session里面(net.sf.jasperreports.j2ee.jasper_print)
				request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
				htmlExporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
				//设值jasperPrint
				htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
				if(returnmodle.getIs_pagination()!=null&&returnmodle.getIs_pagination().equals("1")){
					//当不使用sql分页优化，使用ireport自带分页
					htmlExporter.setParameter(JRHtmlExporterParameter.PAGE_INDEX,pageIndex-1);
					/**
					 * 获取总页数
					 */				
					lastPage=jasperPrint.getPages().size();
				}else{
					/**
					 * 获取总页数
					 */
					lastPage=IreportUtil.getLastPage(jasperReport, parameters, returnmodle);
					
				}
				htmlExporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, IreportUtil.getHtml_Header(pageIndex,lastPage,bid,parameters));
				htmlExporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, IreportUtil.getHtml_Header(pageIndex,lastPage,bid,parameters));
		
				
				//设置输出
				htmlExporter.setParameter(JRExporterParameter.OUTPUT_WRITER,printWriter);
				//设置图片生成的Servlet(生成图片就用这个ImageServlet,并且要在XML文件里面配置 image?image=这个是Servlet的url-pattern)
				//flush随机数用于重新获取图片（更新图片地址），否则条件改变后图片不会随之发生改变
				htmlExporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"image?flush="+Math.random()+"&image=");
				htmlExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
				//导出
				
				htmlExporter.exportReport();
				//关闭输出流
				printWriter.close();
				download="YES";
				request.getSession().setAttribute("download", download);
			}
			
		}
		

	} catch (JRException jre) {
		System.out.println("JRException:" + jre);
	} catch (Exception e) {
		System.out.println("Exception:" + e);
	} 
		
	
	}  

	/**
	 * pdf下载
	 */
	public void exportPdf(){
		download="NO";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();	
		request.getSession().setAttribute("download", download);
		
		try{
			
		
			IreportType type = new IreportType();
			type.setID(bid);
		
			IreportType returnmodle = reportService.getModleIs(type);

			if(returnmodle!=null){
				
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ireportpath+"/"+bid+"/"+returnmodle.getFILENAME());
				
				Map pmap=this.getuserparameters(jasperReport,null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, pmap, JDBCConnection .getConnection());
			
			
			
				IreportUtil.exportPdf(jasperPrint, request, response);
				download="YES";
				request.getSession().setAttribute("download", download);
			}
		}catch(Exception e){
			logger.error(e);
			
		}

		
	}
	/**
	 * word下载
	 */
	public void exportWord(){
		download="NO";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();	
		request.getSession().setAttribute("download", download);
		
		try{
			
			IreportType type = new IreportType();
			type.setID(bid);
			IreportType returnmodle = reportService.getModleIs(type);

			if(returnmodle!=null){
				
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ireportpath+"/"+bid+"/"+returnmodle.getFILENAME());
				
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, this.getuserparameters(jasperReport,null),JDBCConnection .getConnection());
				IreportUtil.exportWord(jasperPrint, request, response);
				download="YES";
				request.getSession().setAttribute("download", download);
			}
		}catch(Exception e){
			logger.error(e);
			
		}

	
	}
	/**
	 * excel下载
	 */
	public void exportExcel(){
		download="NO";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();	
		request.getSession().setAttribute("download", download);
		
		try{
			IreportType type = new IreportType();
			type.setID(bid);
			IreportType returnmodle = reportService.getModleIs(type);
			
			if(returnmodle!=null){
				
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ireportpath+"/"+bid+"/"+returnmodle.getFILENAME());
				
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, this.getuserparameters(jasperReport,null), JDBCConnection .getConnection());
			 	
				IreportUtil.exportExcel(jasperPrint, request, response);
				download="YES";
				request.getSession().setAttribute("download", download);
			}
		}catch(Exception e){
			logger.error(e);
			
		}

		
	}
	/**
	 * 打印
	 */
	public void printReport(){
		download="NO";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();	
		request.getSession().setAttribute("download", download);
		
		try{
			IreportType type = new IreportType();
			type.setID(bid);
			IreportType returnmodle = reportService.getModleIs(type);

			if(returnmodle!=null){
				
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ireportpath+"/"+bid+"/"+returnmodle.getFILENAME());
				
			Map pmap=this.getuserparameters(jasperReport,null);
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					
					jasperReport, pmap, JDBCConnection .getConnection());
				IreportUtil.exportPrint(jasperPrint, response, request);
				download="YES";
				request.getSession().setAttribute("download", download);
			}
		}catch(Exception e){
			logger.error(e);
			
		}

	}
	/**
	 * 打印预览
	 */
	public void printReportPreview(){
		download="NO";
	
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();	
		request.getSession().setAttribute("download", download);
		try {
			ServletOutputStream outputStream =response.getOutputStream();
			try {
				
				IreportType type = new IreportType();
				type.setID(bid);
				IreportType returnmodle = reportService.getModleIs(type);

				if(returnmodle!=null){
					
					JasperReport jasperReport = (JasperReport) JRLoader.loadObject(ireportpath+"/"+bid+"/"+returnmodle.getFILENAME());
					
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, this.getuserparameters(jasperReport,null), JDBCConnection .getConnection());
				if(jasperPrint!=null){
					FileBufferedOutputStream fbos = new FileBufferedOutputStream();
					JRPdfExporter exporter=new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.exportReport();
					fbos.close();
					if(fbos.size()>0){
						response.setContentType("application/paf");
						response.setContentLength(fbos.size());
						fbos.writeData(outputStream);
						fbos.dispose();
						outputStream.flush();
					}
				}
				}
				download="YES";
				request.getSession().setAttribute("download", download);
			} catch (Exception e) {
				logger.error(e);
			}finally{
				if(outputStream !=null){
					outputStream.close();
				}
					
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
	
	}
	/**
	 * 判断下载是否完成
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "download")
	public String checkdownloadover(){
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		download=(String)request.getSession().getAttribute("download");
		return JSON;
	}
	/**
	 * 查询条件整理成Map
	 * IreportType returnmodle  用于判断是否要分页  传入为null 则不分页  查询全部数据，如PDF WORD EXCEL 打印
	 * @return
	 */
	public Map getuserparameters(JasperReport jasperReport,IreportType returnmodle){

		Map parameterMap = new HashMap();
		//parameters 格式  id:12,name:chen,
		//userparameters 为Ireport设计报表 是传值的一个固定参数名称

			/**
			 * 设置分页参数
			 */
			 getpagination(returnmodle, jasperReport,parameterMap);
			 /**
			  * 设置查询参数
			  */
			 getUserParameter(returnmodle, jasperReport,parameterMap);
		return parameterMap;
	
	}
	public void getUserParameter(IreportType returnmodle,JasperReport jasperReport,Map parameterMap){
		 /**
		  * 设置查询参数
		  */
		if(IreportUtil.isHaveParameterValue(jasperReport,"userParameters")!=null){
			 String userparameterSql=" 1 ";
			 
			
			 
			if(parameters!=null&&!parameters.equals("null")){
				 String[] split = parameters.substring(1, parameters.length()-2).split(",");
				 if(split!=null){
			
					 for (int j = 0; j < split.length; j++) {
						 String parameter = split[j];
						 String[] split2 = parameter.split(":");
						
						 if(split2[0]==null||split2[0].equals("null")||split2[1]==null||split2[1].equals("null"))
							 break ;
						 userparameterSql = userparameterSql + " and " +  split2[0]+ " = '" + split2[1]+"' ";
					}
				 }
				 
				 
			 }
			parameterMap.put("userParameters",userparameterSql );
		}	
	}
	//pageParameter
	public void getpagination(IreportType returnmodle,JasperReport jasperReport,Map parameterMap){
		/*
		 * 设置分页参数
		 */
		if(IreportUtil.isHaveParameterValue(jasperReport,"pageParameter")!=null){
			String str="";
			if(returnmodle==null||returnmodle.getIs_pagination()==null){
				 str=" 1 ";
			}else if (returnmodle.getIs_pagination().equals("1")){
				 str=str+"1 and rn between " +((pageIndex-1)*pageNum+1) +" and " +pageIndex*pageNum+" ";
			}else if (returnmodle.getIs_pagination().equals("0")){
				int pageNum = returnmodle.getPageNum();
				if(pageNum>0){
				
					str="1 and rn between " +((pageIndex-1)*pageNum+1) +" and " +pageIndex*pageNum+" ";
				}else {
					str=str+"1 and rn between " +((pageIndex-1)*20+1) +" and " +pageIndex*20+" ";
				}
				
			}
			parameterMap.put("pageParameter",str) ;	
		}

		
	}
	public ReportServiceImpl getReportService() {
		return reportService;
	}
	public void setReportService(ReportServiceImpl reportService) {
		this.reportService = reportService;
	}
	public String getRemain() {
		return remain;
	}
	public void setRemain(String remain) {
		this.remain = remain;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public List<IreportType> getModleList() {
		return modleList;
	}
	public void setModleList(List<IreportType> modleList) {
		this.modleList = modleList;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}



}
