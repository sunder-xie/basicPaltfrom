package com.kintiger.platform.report.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.print.attribute.Size2DSyntax;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.ExcelUtil;
import com.kintiger.platform.report.pojo.ReportStructure;
import com.kintiger.platform.report.service.IReportService;
import com.kintiger.platform.report.util.JsonUtil;

public class ReportAction extends BaseAction {
	private IReportService reportService;
	private ReportStructure content;
	private List<Map<String, Object>> resultData;
	private long rptId;
	private static Log logger = LogFactory.getLog(ReportAction.class);


	/**
	 * 报表展现页面
	 * 
	 * @return
	 */
	public String toView() {
		if (rptId != 0) {
			content = reportService.searchContent(rptId);
		}
		return "view";
	}

	/**
	 * 报表数据
	 * 
	 * @return
	 */
	public String getData() {
		Map comm = new HashMap();
		if (rptId != 0) {
			content = reportService.searchContent(rptId);

			HttpServletRequest request = ServletActionContext.getRequest();
			Enumeration<String> enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String name = enu.nextElement();
				comm.put(name, request.getParameter(name));
			}
			comm.put("start", getStart());
			comm.put("end", getEnd());
			resultData = reportService.transformersReportData(content, comm);
			String result = JsonUtil.list2json(resultData);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=gbk");
			try {
				response.getWriter().write(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 导出
	 */
	@PermissionSearch
	public void ReportExp() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		HttpServletResponse response = getServletResponse();
		Map comm = new HashMap();
		if (rptId != 0) {
			content = reportService.searchContent(rptId);
			HttpServletRequest request = ServletActionContext.getRequest();
			Enumeration<String> enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String name = enu.nextElement();
				comm.put(name, request.getParameter(name));
			}
			comm.put("start", 0);
			comm.put("end", 1000000);
			resultData = reportService.transformersReportData(content, comm);
		}


		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(content.getREPORT_NO().getBytes("GBK"),
							("ISO8859-1")) + ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);//工作薄
			
			
			List<String> headerCode = new ArrayList<String>();
			for(String code : content.getREPORT_HEADER_CODE().trim().split(",")){
				headerCode.add(code.trim());
			}
			String[] headerName = content.getREPORT_HEADER_NAME().trim().split(",");

			
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableSheet wsheet = wbook.createSheet(content.getREPORT_NO(), 0);//工作表
			WritableFont font = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			
			for (int i=0;i<headerName.length;i++) {
				Label label_0 = new Label(i, 0, headerName[i].trim());
				label_0.setCellFormat(cellFormat_top);
				wsheet.addCell(label_0);
			}

		     ExcelUtil.createExcelWithBookMap(wbook, headerCode, resultData);
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
	}

	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public ReportStructure getContent() {
		return content;
	}

	public void setContent(ReportStructure content) {
		this.content = content;
	}

	public List<Map<String, Object>> getResultData() {
		return resultData;
	}

	public void setResultData(List<Map<String, Object>> resultData) {
		this.resultData = resultData;
	}

	public long getRptId() {
		return rptId;
	}

	public void setRptId(long rptId) {
		this.rptId = rptId;
	}

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		ReportAction.logger = logger;
	}

}
