package com.kintiger.platform.ireport.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.ireport.pojo.IreportType;
import com.kintiger.platform.ireport.service.impl.ReportServiceImpl;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class IreportUtil {
	/**
	 * 导出PDF
	 * @param jasperPrint
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JRException
	 */
	public static void exportPdf(JasperPrint jasperPrint,
			HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
			response.setContentType("application/pdf");
			String fileName = new String((jasperPrint.getName()+getDate()+".pdf").getBytes("GBK"), "ISO8859_1");
		
			response.setHeader("Content-disposition", "attachment; filename="
			+ fileName);
			ServletOutputStream ouputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint,
			ouputStream);
			ouputStream.flush();
			ouputStream.close();
			}
	/**
	* 导出excel
	*/
	public static void exportExcel(JasperPrint jasperPrint,
	HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
	/*
	* 设置头信息
	*/

	response.setContentType("application/vnd.ms-excel");
	String fileName = new String((jasperPrint.getName()+getDate()+".xls").getBytes("GBK"), "ISO8859_1");
	response.setHeader("Content-disposition", "attachment; filename="
	+ fileName);
	ServletOutputStream ouputStream = response.getOutputStream();
	JRXlsExporter exporter = new JRXlsExporter();
	exporter
	.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
	ouputStream);
	exporter.setParameter(
	JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
	Boolean.TRUE);
	exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
	Boolean.FALSE);
	exporter.setParameter(
	JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
	Boolean.FALSE);
	exporter.exportReport();
	ouputStream.flush();
	ouputStream.close();
	}
	
	public static JasperReport prepareReport(JasperReport jasperReport) {
		/*
		* 如果导出的是excel，则需要去掉周围的margin
		*/
		try {
		Field margin = JRBaseReport.class
		.getDeclaredField("leftMargin");
		margin.setAccessible(true);
		margin.setInt(jasperReport, 0);
		margin = JRBaseReport.class.getDeclaredField("topMargin");
		margin.setAccessible(true);
		margin.setInt(jasperReport, 0);
		margin = JRBaseReport.class.getDeclaredField("bottomMargin");
		margin.setAccessible(true);
		margin.setInt(jasperReport, 0);
		Field pageHeight = JRBaseReport.class
		.getDeclaredField("pageHeight");
		pageHeight.setAccessible(true);
		pageHeight.setInt(jasperReport, 2147483647);
		return jasperReport;
		} catch (Exception exception) {
		}
		return jasperReport;
		}
	/**
	* 导出html
	*/
	public static void exportHtml(JasperPrint jasperPrint,
	HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
	response.setContentType("text/html");
	ServletOutputStream ouputStream = response.getOutputStream();
	JRHtmlExporter exporter = new JRHtmlExporter();
	exporter.setParameter(
	JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
	Boolean.FALSE);
	exporter
	.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
	"UTF-8");
	exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
	ouputStream);
	exporter.exportReport();
	ouputStream.flush();
	ouputStream.close();
	}
	/**
	* 导出word
	*/
	public static void exportWord(JasperPrint jasperPrint,
	HttpServletRequest request, HttpServletResponse response)
	throws JRException, IOException {
	response.setContentType("application/msword;charset=utf-8");
	String fileName = new String((jasperPrint.getName()+getDate()+".doc").getBytes("GBK"), "ISO8859_1");
	response.setHeader("Content-disposition", "attachment; filename="
	+ fileName);
	JRExporter exporter = new JRRtfExporter();
	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response
	.getOutputStream());
	exporter.exportReport();
	}

	/**
	* 打印
	*/
	public static void exportPrint(JasperPrint jasperPrint,
	HttpServletResponse response, HttpServletRequest request)
	throws Exception {
		JasperPrintManager.printReport(jasperPrint, true);
	response.setContentType("application/octet-stream");
	ServletOutputStream ouputStream = response.getOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
	oos.writeObject(jasperPrint);
	oos.flush();
	oos.close();
	
	ouputStream.flush();
	ouputStream.close();
	}
	/**
	* 按照类型导出不同格式文件
	* 
	* @param datas
	* 数据
	* @param type
	* 文件类型
	* @param is
	* jasper文件的来源
	* @param request
	* @param response
	*/
	public static void export(Collection datas, String type, InputStream is,
	HttpServletRequest request, HttpServletResponse response) {
	try {
	JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
	prepareReport(jasperReport);
	JRDataSource ds = new JRBeanCollectionDataSource(datas, false);
	Map parameters = new HashMap();
	JasperPrint jasperPrint = JasperFillManager.fillReport(
	jasperReport, parameters, ds);
	if (EXCEL_TYPE.equals(type)) {
	exportExcel(jasperPrint, request, response);
	} else if (PDF_TYPE.equals(type)) {
	exportPdf(jasperPrint, request, response);
	} else if (HTML_TYPE.equals(type)) {
	exportHtml(jasperPrint, request, response);
	} else if (WORD_TYPE.equals(type)) {
	exportWord(jasperPrint, request, response);
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	public static final String PRINT_TYPE = "print";
	public static final String PDF_TYPE = "pdf";
	public static final String EXCEL_TYPE = "excel";
	public static final String HTML_TYPE = "html";
	public static final String WORD_TYPE = "word";

	public static String getDate(){
		
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyyMMddHHmmss");
		return "-"+dateformat1.format(new Date());
	}

	public static String getHtml_Header(int pageIndex,int lastPage,String bid,String parameters) throws Exception{
		String html_header="";
		String appUrl=getProperty("appUrl");
		String imgurl=getProperty("imgUrl");
		html_header=html_header+"<div style=\"hight:20px;text-align:center;border-bottom:0px;background:#e6f0ff;border-style:solid;border-width:1pt; border-color:#95B8E7;margin-left:auto; margin-right:auto;\" id=\"maindiv\">\n"+		
				 "<font  size=\"2\">当前页： "+(pageIndex)+"&nbsp;&nbsp;总页数："+(lastPage)+"</font>\n";

			if(lastPage==1){
		html_header=html_header+"<img src=\""+imgurl+"/images/platform/ireport/nav_icon_first_disabled.gif\" border=\"0\">&nbsp;&nbsp;\n"+
						        "<img src=\""+imgurl+"/images/platform/ireport/nav_icon_previous_disabled.gif\" border=\"0\">&nbsp;&nbsp;\n"+
								"<img src=\""+imgurl+"/images/platform/ireport/nav_icon_next_disabled.gif\" border=\"0\">&nbsp;&nbsp;\n"+
								"<img src=\""+imgurl+"/images/platform/ireport/nav_icon_last_disabled.gif\" border=\"0\" >&nbsp;&nbsp;&nbsp;\n";	
			}else if(pageIndex==1){
		html_header=html_header+"<img src=\""+imgurl+"/images/platform/ireport/nav_icon_first_disabled.gif\" border=\"0\">&nbsp;&nbsp;\n"+
				                "<img src=\""+imgurl+"/images/platform/ireport/nav_icon_previous_disabled.gif\" border=\"0\">&nbsp;&nbsp;\n"+
				                "<a href=\"javascript:nextpage()\"><img src=\""+imgurl+"/images/platform/ireport/nav_icon_next.gif\" border=\"0\"></a>&nbsp;&nbsp;\n"+
				                "<a href=\"javascript:lastpage()\"><img src=\""+imgurl+"/images/platform/ireport/nav_icon_last.gif\" border=\"0\" ></a>&nbsp;&nbsp;&nbsp;\n";
			}else if(pageIndex==lastPage){
		html_header=html_header+"<a href=\"javascript:firstpage()\"><img src=\""+imgurl+"/images/platform/ireport/nav_icon_first.gif\" border=\"0\"></a>&nbsp;&nbsp;\n"+
						 		"<a href=\"javascript:previouspage()\"><img src=\""+imgurl+"/images/platform/ireport/nav_icon_previous.gif\" border=\"0\"></a>&nbsp;&nbsp;\n"+
						 		"<img src=\""+imgurl+"/images/platform/ireport/nav_icon_next_disabled.gif\" border=\"0\">&nbsp;&nbsp;\n"+
						 		"<img src=\""+imgurl+"/images/platform/ireport/nav_icon_last_disabled.gif\" border=\"0\" >&nbsp;&nbsp;&nbsp;\n";	
			}else {
		html_header=html_header+ "<a href=\"javascript:firstpage()\"><img src=\""+imgurl+"/images/platform/ireport/nav_icon_first.gif\" border=\"0\"></a>&nbsp;&nbsp;\n"+
								 "<a href=\"javascript:previouspage()\"><img src=\""+imgurl+"/images/platform/ireport/nav_icon_previous.gif\" border=\"0\"></a>&nbsp;&nbsp;\n"+
								 "<a href=\"javascript:nextpage()\"><img src=\""+imgurl+"/images/platform/ireport/nav_icon_next.gif\" border=\"0\"></a>&nbsp;&nbsp;\n"+
								 "<a href=\"javascript:lastpage()\"><img src=\""+imgurl+"/images/platform/ireport/nav_icon_last.gif\" border=\"0\" ></a>&nbsp;&nbsp;&nbsp;\n";				
			}
	
		html_header=html_header+ "<a href=\"javascript:downloadPdf()\"><img src= \""+imgurl+"/images/platform/ireport/pdf.gif\" border=\"0\"></a>&nbsp;\n"+
								 "<a href=\"javascript:downloadWord()\"><img src= \""+imgurl+"/images/platform/ireport/word.gif\" border=\"0\"></a>&nbsp;\n"+
								 "<a href=\"javascript:downloadExcel()\"><img src= \""+imgurl+"/images/platform/ireport/excel.jpg\" border=\"0\"></a>&nbsp;\n"+
								 "<a href=\"javascript:printReport()\"><img src= \""+imgurl+"/images/platform/ireport/printer.gif\" border=\"0\"></a>&nbsp;&nbsp;&nbsp;&nbsp;\n"+
					        		
					             "<input type=\"hidden\" id=\"pageIndex\" name=\"pageIndex\" value=\""+pageIndex+"\"/>"+		
					             "<input type=\"hidden\" id=\"lastPage\" name=\"lastPage\" value=\""+lastPage+"\"/>"+		
								 "</div>";
		return html_header;
	}
	public static String getProperty(String proname) throws Exception{
		 Properties props = new Properties();
		 HttpServletRequest request = ServletActionContext.getRequest();
         InputStream in = new BufferedInputStream (new FileInputStream(request.getRealPath("/")+"/WEB-INF/"+"env.properties"));
         props.load(in);
         return props.getProperty (proname);
	}
	public static  String   isHaveParameterValue(JasperReport jasperReport,String paramerterName){
		JRParameter[] jrParameters = jasperReport.getParameters();
		if(jrParameters!=null){
			for (int i = 0; i < jrParameters.length; i++) {
				JRParameter jrParameter = jrParameters[i];
				if(jrParameter!=null&&jrParameter.getName()!=null&&jrParameter.getName().equals(paramerterName)){
					return jrParameter.getDefaultValueExpression().getText();
				}
			}			
		}
		return null;
	}
	public static boolean isNmuber(String str){
		try {
			int d = Integer.parseInt(str);
			if(d>0){
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
		
	}
	public static int getLastPage(JasperReport jasperReport,String parameters,IreportType returnmodle){
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		List datas = new ArrayList();
		String sql ="select count(*) ";
		try {
			//
		con = JDBCConnection .getConnection();
		st = con.createStatement();
		String query=jasperReport.getQuery().getText().toUpperCase();
		sql=sql + query.substring(query.lastIndexOf("FROM"),query.indexOf("1=$"))+" 1=1 ";
		Map parameterMap = new HashMap();
		//parameters 格式  id:12,name:chen,
		//userparameters 为Ireport设计报表 是传值的一个固定参数名称


		if(isHaveParameterValue(jasperReport,"userParameters")!=null){

					if(parameters!=null&&!parameters.equals("null")){
						 String[] split = parameters.substring(1, parameters.length()-2).split(",");
						 if(split!=null){
						
							 for (int j = 0; j < split.length; j++) {
								 String parameter = split[j];
								 String[] split2 = parameter.split(":");
								 if(split2[0]==null||split2[0].equals("null")||split2[1]==null||split2[0].equals("null"))
									 break ;
								 sql = sql + " and " +  split2[0]+ " = '" + split2[1]+"' ";
							}
						 }
					 }
				}

		rs = st. executeQuery (sql);
		int pageInt=returnmodle.getPageNum();
		int page=0;
		if(pageInt<=0){
			page=20;
		}else {
			page=pageInt;
			
		}
		int returncount=0;
		if (rs.next()) {
			int count=rs.getInt(1);
			if(count%page==0){
				returncount= count/page;
			}else{
				returncount= count/page+1;
			}

		}
		if(returncount==0){
			return 1;
		}else{
			return returncount;
		}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
            if(rs != null) rs.close();
            if(st != null) st.close();
            if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

}
