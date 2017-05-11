package com.kintiger.platform.framework.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
  
/** 
 * 导出Excel公共方法（xlsx格式） 
 * @version 1.0 
 *  
 * @author bo 
 * 
 * 参数（文件名，列中文名称，列英文名称，数据列表）
 * 注:列名称中文与英文一一对应
 * 
 * 返回信息：（String）导出成功或失败原因
 * 
 * 注：调用该工具类Action不能返回"RESULT_MESSAGE"，会报错
 */  
public class ExportExcelUtil extends BaseAction{  
      
    /**
	 * 
	 */
	private static final long serialVersionUID = 609689081575689909L;
	//显示的导出表的标题  
    private String title;  
    //导出表的列名  
    private String[] rowName ;
    
    private String[] colNames;
      
    private List dataList = new ArrayList();  
      
    HttpServletResponse  response;  
      
    //构造方法，传入要导出的数据  
    public ExportExcelUtil(String title,String[] rowName,String[] colNames,List dataList){  
        this.dataList = dataList;  
        this.rowName = rowName;  
        this.title = title;
        this.colNames = colNames;
    }  
     
    public ExportExcelUtil(){  
    }  
    /* 
     * 导出数据 
     * */  
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String export() throws Exception{
        try{
        	if(rowName.length!=colNames.length){
        		return "rowName和colNames的长度必须一致，请联系管理员！";
        	}
        	SXSSFWorkbook workbook = new SXSSFWorkbook(1000);                     // 创建工作簿对象  
        	int maxNum=1000000;
        	int sheetNum=(int)Math.ceil(dataList.size()/(double)maxNum);
        	for(int x=1;x<=sheetNum;x++){
        		Sheet sheet = workbook.createSheet("第"+x+"页");                  // 创建工作表 
        		
        		//sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】  
//        		XSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象  
//        		XSSFCellStyle style = this.getStyle(workbook);                  //单元格样式对象   
        		
        		// 定义所需列数  
        		int columnNum = rowName.length;  
        		Row rowRowName = sheet.createRow(0);                // 在索引2的位置创建行(最顶端的行开始的第二行)  
        		
        		// 将列头设置到sheet的单元格中  
        		for(int n=0;n<columnNum;n++){  
        			Cell  cellRowName = rowRowName.createCell(n);               //创建列头对应个数的单元格  
        			cellRowName.setCellType(XSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型  
        			XSSFRichTextString text = new XSSFRichTextString(rowName[n]);  
        			cellRowName.setCellValue(text);                                 //设置列头单元格的值  
//        			cellRowName.setCellStyle(columnTopStyle);                       //设置列头单元格样式  
        		}  
        		
        		int startNum=(x-1)*maxNum;
        		int endNum=x*maxNum;
        		if(x==sheetNum){
        			endNum=dataList.size();
        		}
        		
        		//将查询出的数据设置到sheet对应的单元格中  
        		for(int i=startNum;i<endNum;i++){
        			Object obj = dataList.get(i);//遍历每个对象  
        			Row row = sheet.createRow(i-startNum+1);//创建所需的行数  
        			
        			for(int j=0; j<colNames.length; j++){  
        				Cell cell = row.createCell(j);
//        				cell.setCellStyle(style);
        				
        				String fieldName = colNames[j];
        				String getMethodName = "get"
        						+ fieldName.substring(0, 1).toUpperCase()
        						+ fieldName.substring(1);
        				Class tCls = obj.getClass();
        				Method getMethod = tCls.getMethod(getMethodName,
        						new Class[] {});
        				Object value = getMethod.invoke(obj, new Object[] {});
        				if(value!=null){
        					String textValue = value.toString();
        					Pattern p = Pattern.compile("^\\-?\\d+(\\.\\d+)?$");  
        					Matcher matcher = p.matcher(textValue);
        					if(matcher.matches()){
        						//是数字当作double处理
        						cell.setCellValue(Double.parseDouble(textValue));
        					}else{
        						cell.setCellValue(textValue);
        					}
        				}
        			}  
        		}  
        		//让列宽随着导出的列长自动适应  
        		for (int colNum = 0; colNum < columnNum; colNum++) {  
        			int columnWidth = sheet.getColumnWidth(colNum) / 256;  
        			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {  
        				Row currentRow;  
        				//当前行未被使用过  
        				if (sheet.getRow(rowNum) == null) {  
        					currentRow = sheet.createRow(rowNum);  
        				} else {  
        					currentRow = sheet.getRow(rowNum);  
        				}  
        				if (currentRow.getCell(colNum) != null) {  
        					Cell currentCell = currentRow.getCell(colNum);  
        					if (currentCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {  
        						int length = currentCell.getStringCellValue().getBytes().length;  
        						if (columnWidth < length) {  
        							columnWidth = length;  
        						}  
        					}  
        				}  
        			}  
        			sheet.setColumnWidth(colNum, (columnWidth+4) * 256);  
//        			if(colNum == 0){  
//        				sheet.setColumnWidth(colNum, (columnWidth-2) * 256);  
//        			}else{  
//        			}  
        		}
        	}
              
        	if(workbook !=null){
        		OutputStream out = null;
        		try  
        		{   
        			String fileName = title + ".xlsx";
        			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
        			String headStr = "attachment; filename=\"" + fileName + "\"";  
        			response = ServletActionContext.getResponse(); 
        			response.setContentType("application/msexcel");  
        			response.setHeader("Content-Disposition", headStr);
        			out = response.getOutputStream();  
        			workbook.write(out);
        			return "导出成功";
        		}catch (IOException e){  
        			e.printStackTrace();  
        		}finally {
        			if (out != null) {
        				try {
        					out.flush();
        					out.close();
        					out = null;
        					response.flushBuffer();
        					response.reset();
        				} catch (Exception e) {
        				}
        			}
        		}  
        	}
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return "";  
    }  
      
    /*  
     * 列头单元格样式 
     */      
    public XSSFCellStyle getColumnTopStyle(XSSFWorkbook workbook) {  
          
          // 设置字体  
          XSSFFont font = workbook.createFont();  
          //设置字体大小  
          font.setFontHeightInPoints((short)11);  
          //字体加粗  
          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
          //设置字体名字   
          font.setFontName("Courier New");  
          //设置样式;   
          XSSFCellStyle style = workbook.createCellStyle();  
          //设置底边框;   
          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
          //设置底边框颜色;    
          style.setBottomBorderColor(HSSFColor.BLACK.index);  
          //设置左边框;     
          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          //设置左边框颜色;   
          style.setLeftBorderColor(HSSFColor.BLACK.index);  
          //设置右边框;   
          style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
          //设置右边框颜色;   
          style.setRightBorderColor(HSSFColor.BLACK.index);  
          //设置顶边框;   
          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          //设置顶边框颜色;    
          style.setTopBorderColor(HSSFColor.BLACK.index);  
          //在样式用应用设置的字体;    
          style.setFont(font);  
          //设置自动换行;   
          style.setWrapText(false);  
          //设置水平对齐的样式为居中对齐;    
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
          //设置垂直对齐的样式为居中对齐;   
          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
            
          return style;  
            
    }  
      
    /*   
     * 列数据信息单元格样式 
     */    
    public XSSFCellStyle getStyle(XSSFWorkbook workbook) {  
          // 设置字体  
          XSSFFont font = workbook.createFont();  
          //设置字体大小  
          //font.setFontHeightInPoints((short)10);  
          //字体加粗  
          //font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  
          //设置字体名字   
          font.setFontName("Courier New");  
          //设置样式;   
          XSSFCellStyle style = workbook.createCellStyle();  
          //设置底边框;   
          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
          //设置底边框颜色;    
          style.setBottomBorderColor(HSSFColor.BLACK.index);  
          //设置左边框;     
          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          //设置左边框颜色;   
          style.setLeftBorderColor(HSSFColor.BLACK.index);  
          //设置右边框;   
          style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
          //设置右边框颜色;   
          style.setRightBorderColor(HSSFColor.BLACK.index);  
          //设置顶边框;   
          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          //设置顶边框颜色;    
          style.setTopBorderColor(HSSFColor.BLACK.index);  
          //在样式用应用设置的字体;    
          style.setFont(font);  
          //设置自动换行;   
          style.setWrapText(false);  
          //设置水平对齐的样式为居中对齐;    
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
          //设置垂直对齐的样式为居中对齐;   
          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
           
          return style;  
      
    }  
}  
