package com.kintiger.platform.file.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.file.pojo.BudgetFileTmp;
import com.kintiger.platform.file.service.IFileService;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.DateUtil;
import com.kintiger.platform.framework.util.FileUtil;
import com.kintiger.platform.framework.util.PreViewFileUtil;
import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.service.IEventService;

public class FileAction extends BaseAction {

	private static final long serialVersionUID = -6418680336897962924L;
	private static final Logger logger = Logger.getLogger(FileAction.class);
	private OfficeManager officeManager;
	private int port[] = { 8100 };
	private String openOfficeHome;
	private String pdf2swfHome;
	private String readOnlinePath;
	private IFileService fileService;
	private String wfeDownloadPath;
	private String imgUploadPath;
	private String imgUploadPathUrl;
	private String fileId;
	private String officePath;

	private File upload;
	private String uploadFileName;
	private String swfFileName;
	private String eventId;
	private IEventService eventService;
	private IDictService dictService;

	private long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
	private long nh = 1000 * 60 * 60;// 一小时的毫秒数
	private long nm = 1000 * 60;// 一分钟的毫秒数
	private long ns = 1000;// 一秒钟的毫秒数

	private String contractFilePath;
	private String contractFileName;

	@PermissionSearch
	public String downLoadFile() {
		BudgetFileTmp fileTmp = fileService.getFileByFileId(Long
				.parseLong(fileId.trim()));
		File source = new File(wfeDownloadPath + "/" + fileTmp.getSubFolders()
				+ "/" + fileTmp.getFileNameNew());
		if (source.exists()) {
			display(source, fileTmp.getFileName(),
					ServletActionContext.getResponse());
		} else {
			this.setFailMessage("文件不存在!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 协同办公调用 byws
	 * 
	 * @return
	 */
	public String toPreViewContractFile() {
		String strm[] = contractFilePath.split("\\/");
		System.out.println(strm[4]);
		try {
			contractFilePath = new String(
					contractFilePath.getBytes("ISO8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(fileId);
		String strm4 = strm[4];
		// toPreViewFileUtil12(fileId,contractFilePath,contractFileName,name);
		return toPreViewFileUtil2(fileId, contractFilePath, contractFileName,strm4);
	}

	/**
	 * 协同办公合同预览 byws
	 * 
	 * @param fileId
	 * @param contractFileName
	 * @param contractFilePath
	 * @param strm
	 * @return
	 */
	public synchronized String toPreViewFileUtil2(String fileId,
			String contractFilePath, String contractFileName, String strm) {
		System.out.println(contractFilePath + "--" + contractFileName + "11");
		File sourceFile = new File(contractFilePath);// 转换源文件
		System.out.println(sourceFile);
		// String absoluteFile = new File("").getAbsolutePath();
		File pdfFile = new File(officePath + "/" + strm + "/" + fileId + ".pdf");// PDF目标文件
		File swfFile = new File(officePath + "/" + strm + "/" + fileId + ".swf");// SWF目标文件
		System.out.println(pdfFile);
		Runtime r;
		System.out.println("第一步：生成文件对象，准备转换");
		String suffix = StringUtils.substring(contractFileName,
				StringUtils.lastIndexOf(contractFileName, '.')).toUpperCase();
		if (sourceFile.exists()) {
			if (".DOC".equals(suffix) || ".DOCX".equals(suffix)
					|| ".PPT".equals(suffix) || ".PPTX".equals(suffix)
					|| ".XLS".equals(suffix) || ".XLSX".equals(suffix)
					|| ".CSV".equals(suffix) || ".TXT".equals(suffix)
					|| ".XLSM".equals(suffix) || ".BPM".equals(suffix)
					|| ".GIF".equals(suffix)) {
				if (!pdfFile.exists()) {
					// 防止并发
					CmsTbDict dict = new CmsTbDict();
					dict.setStart(0);
					dict.setEnd(10);
					dict.setDictTypeValue("usered@filePreview");
					List<CmsTbDict> dlist = dictService.getDictList(dict);
					if (null != dlist) {
						if (dlist.size() > 0) {
							if ("N".equals(dlist.get(0).getItemValue())) {// 如为空闲，则可进去转换文档
								try {
									startService();
									// 变更字典值为Y在用
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("Y");
									dictY.setItemDescription("在用");
									dictService.updateDict(dictY);
									OfficeDocumentConverter converter = new OfficeDocumentConverter(
											officeManager);
									converter.convert(sourceFile, pdfFile);
									System.out.println("第二步：转换为PDF格式	路径"
											+ pdfFile.getPath());
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									stopService();
									// 变更字典值为N空闲
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("空闲");
									dictService.updateDict(dictY);
								}
							} else {
								long diff = (new Date()).getTime()
										- dlist.get(0).getLastModify()
												.getTime();
								long day = diff / nd;// 计算差多少天
								long min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
								System.out.println("openOffice服务时间："
										+ dlist.get(0).getLastModify() + "@@@@"
										+ new Date());
								System.out.println("openOffice服务时间差：" + min
										+ "分钟");
								if (min > 2) {
									stopService();
									System.out.println("openOffice服务停止!");
									// 变更字典值为N空闲
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("空闲");
									dictService.updateDict(dictY);
								}
								System.out.println("openOffice服务正被占用，稍后再访问!");
							}
						}
					}
				} else {
					// System.out.println("已转换为PDF，无需再次转换");
				}
			} else {
				System.out.println("要转换的文件不存在");
			}
		}
		// 转换成swf文件
		r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (".DOC".equals(suffix) || ".DOCX".equals(suffix)
					|| ".PPT".equals(suffix) || ".PPTX".equals(suffix)
					|| ".XLS".equals(suffix) || ".XLSX".equals(suffix)
					|| ".CSV".equals(suffix) || ".TXT".equals(suffix)
					|| ".XLSM".equals(suffix) || ".BPM".equals(suffix)
					|| ".GIF".equals(suffix)) {
				if (pdfFile.exists()) {
					try {
						Process p = r.exec(pdf2swfHome + " -t "
								+ pdfFile.getPath() + " -s flashversion=9 -o "
								+ swfFile.getPath());
						System.out.println(pdf2swfHome);
						BufferedReader bufferedReader = new BufferedReader(
								new InputStreamReader(p.getInputStream()));

						while (bufferedReader.readLine() != null)
							;
						try {
							p.waitFor();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						p.waitFor();
						swfFile.createNewFile();
						System.out.println("第三步：转换为SWF格式	路径："
								+ swfFile.getPath());
						System.out.println("第si步：转换为SWF格式mingcheng："
								+ swfFile.getName());
						if (pdfFile.exists()) {
							pdfFile.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
						try {
							throw e;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} else {
					System.out.println("PDF文件不存在，无法转换");
				}
			} else if (".FONT".equals(suffix) || ".JPEG".equals(suffix)
					|| ".JPG".equals(suffix) || ".PDF".equals(suffix)
					|| ".PNG".equals(suffix) || ".WAV".equals(suffix)) {
				String cmd = getToolPath(suffix) + sourceFile.getPath()
						+ " -o " + swfFile.getPath();
				try {
					Process p = r.exec(cmd);
					System.out.println(cmd);
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(p.getInputStream()));

					while (bufferedReader.readLine() != null)
						;
					try {
						p.waitFor();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p.waitFor();
					swfFile.createNewFile();
					System.out.println("直接转换为SWF格式	路径：" + swfFile.getPath());
					System.out.println("直接转换为SWF格式名称：" + swfFile.getName());
					if (pdfFile.exists()) {
						pdfFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					try {
						throw e;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				System.out.println("文件格式不能转换!");
			}
		} else {
			System.out.println("已经转为SWF文件，无需再次转换");
		}
		swfFileName = officePath + "/" + strm + "/" + swfFile.getName();
		System.out.println(swfFileName);

		return "toPreviewFile";

	}

	/**
	 * 跳转附件预览界面
	 * 
	 * @return
	 */
	public String toPreViewFile() {
		return toPreViewFileUtil();
	}

	public synchronized String toPreViewFileUtil() {
		System.out.println(openOfficeHome);
		BudgetFileTmp fileTmp = fileService.getFileByFileId(Long
				.parseLong(fileId.trim()));
		File sourceFile = new File(wfeDownloadPath + "/"
				+ fileTmp.getSubFolders() + "/" + fileTmp.getFileNameNew());// 转换源文件
		System.out.println(sourceFile);
		// String absoluteFile = new File("").getAbsolutePath();
		File pdfFile = new File(readOnlinePath + "/" + fileId + ".pdf");// PDF目标文件
		File swfFile = new File(readOnlinePath + "/" + fileId + ".swf");// SWF目标文件
		Runtime r;
		System.out.println("第一步：生成文件对象，准备转换");
		String suffix = StringUtils.substring(fileTmp.getFileNameNew(),
				StringUtils.lastIndexOf(fileTmp.getFileNameNew(), '.'))
				.toUpperCase();
		if (sourceFile.exists()) {
			if (".DOC".equals(suffix) || ".DOCX".equals(suffix)
					|| ".PPT".equals(suffix) || ".PPTX".equals(suffix)
					|| ".XLS".equals(suffix) || ".XLSX".equals(suffix)
					|| ".CSV".equals(suffix) || ".TXT".equals(suffix)
					|| ".XLSM".equals(suffix) || ".BPM".equals(suffix)
					|| ".GIF".equals(suffix)) {
				if (!pdfFile.exists()) {
					// 防止并发
					CmsTbDict dict = new CmsTbDict();
					dict.setStart(0);
					dict.setEnd(10);
					dict.setDictTypeValue("usered@filePreview");
					List<CmsTbDict> dlist = dictService.getDictList(dict);
					if (null != dlist) {
						if (dlist.size() > 0) {
							if ("N".equals(dlist.get(0).getItemValue())) {// 如为空闲，则可进去转换文档
								try {
									startService();
									// 变更字典值为Y在用
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("Y");
									dictY.setItemDescription("在用");
									dictService.updateDict(dictY);
									OfficeDocumentConverter converter = new OfficeDocumentConverter(
											officeManager);
									converter.convert(sourceFile, pdfFile);
									System.out.println("第二步：转换为PDF格式	路径"
											+ pdfFile.getPath());
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									stopService();
									// 变更字典值为N空闲
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("空闲");
									dictService.updateDict(dictY);
								}
							} else {
								long diff = (new Date()).getTime()
										- dlist.get(0).getLastModify()
												.getTime();
								long day = diff / nd;// 计算差多少天
								long min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
								System.out.println("openOffice服务时间："
										+ dlist.get(0).getLastModify() + "@@@@"
										+ new Date());
								System.out.println("openOffice服务时间差：" + min
										+ "分钟");
								if (min > 2) {
									stopService();
									System.out.println("openOffice服务停止!");
									// 变更字典值为N空闲
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("空闲");
									dictService.updateDict(dictY);
								}
								System.out.println("openOffice服务正被占用，稍后再访问!");
							}
						}
					}
				} else {
					// System.out.println("已转换为PDF，无需再次转换");
				}
			} else {
				System.out.println("要转换的文件不存在");
			}
		}
		// 转换成swf文件
		r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (".DOC".equals(suffix) || ".DOCX".equals(suffix)
					|| ".PPT".equals(suffix) || ".PPTX".equals(suffix)
					|| ".XLS".equals(suffix) || ".XLSX".equals(suffix)
					|| ".CSV".equals(suffix) || ".TXT".equals(suffix)
					|| ".XLSM".equals(suffix) || ".BPM".equals(suffix)
					|| ".GIF".equals(suffix)) {
				if (pdfFile.exists()) {
					try {
						Process p = r.exec(pdf2swfHome + " -t "
								+ pdfFile.getPath() + " -s flashversion=9 -o "
								+ swfFile.getPath());
						System.out.println(pdf2swfHome);
						BufferedReader bufferedReader = new BufferedReader(
								new InputStreamReader(p.getInputStream()));

						while (bufferedReader.readLine() != null)
							;
						try {
							p.waitFor();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						p.waitFor();
						swfFile.createNewFile();
						System.out.println("第三步：转换为SWF格式	路径："
								+ swfFile.getPath());
						System.out.println("第si步：转换为SWF格式mingcheng："
								+ swfFile.getName());
						if (pdfFile.exists()) {
							pdfFile.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
						try {
							throw e;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} else {
					System.out.println("PDF文件不存在，无法转换");
				}
			} else if (".FONT".equals(suffix) || ".JPEG".equals(suffix)
					|| ".JPG".equals(suffix) || ".PDF".equals(suffix)
					|| ".PNG".equals(suffix) || ".WAV".equals(suffix)) {
				String cmd = getToolPath(suffix) + sourceFile.getPath()
						+ " -o " + swfFile.getPath();
				try {
					Process p = r.exec(cmd);
					System.out.println(cmd);
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(p.getInputStream()));

					while (bufferedReader.readLine() != null)
						;
					try {
						p.waitFor();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p.waitFor();
					swfFile.createNewFile();
					System.out.println("直接转换为SWF格式	路径：" + swfFile.getPath());
					System.out.println("直接转换为SWF格式名称：" + swfFile.getName());
					if (pdfFile.exists()) {
						pdfFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					try {
						throw e;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				System.out.println("文件格式不能转换!");
			}
		} else {
			System.out.println("已经转为SWF文件，无需再次转换");
		}
		swfFileName = readOnlinePath + "/" + swfFile.getName();

		return "toPreviewFile";

	}

	public void startService() {
		DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
		try {
			System.out.println("prepare准备启动服务....");
			configuration.setOfficeHome(new File(openOfficeHome));// 设置OpenOffice.org安装目录
			System.out.println(new File(openOfficeHome));
			configuration.setPortNumbers(port); // 设置转换端口，默认为8100
			configuration.setTaskExecutionTimeout(1000 * 60 * 5L);// 设置任务执行超时为5分钟
			configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);// 设置任务队列超时为24小时

			officeManager = configuration.buildOfficeManager();
			officeManager.start(); // 启动服务
			System.out.println("success office转换服务启动成功!");
		} catch (Exception ce) {
			System.out.println("office转换服务启动失败!详细信息:" + ce);
		}
	}

	public void stopService() {
		System.out.println("关闭office转换服务....");
		if (officeManager != null) {
			officeManager.stop();
		}
		System.out.println("关闭office转换成功!");
	}

	/**
	 * 附件预览
	 * 
	 * @return
	 */
	public void previewFile() {
		try {
			BudgetFileTmp fileTmp = fileService.getFileByFileId(Long
					.parseLong(fileId.trim()));
			File source = new File(wfeDownloadPath + "/"
					+ fileTmp.getSubFolders() + "/" + fileTmp.getFileNameNew());// 转换源文件
			if (source.exists()) {
				PreViewFileUtil pvfu = new PreViewFileUtil();
				if (fileTmp.getFileName().toUpperCase().endsWith(".DOC")) {
					// pvfu.previewDoc(source, fileTmp,
					// ServletActionContext.getResponse());
				} else if (fileTmp.getFileName().toUpperCase()
						.endsWith(".DOCX")) {
					// pvfu.previewDocx(source,
					// ServletActionContext.getResponse(), fileTmp);
				}
				if (fileTmp.getFileName().toUpperCase().endsWith(".JPG")) {

				}
			} else {
				this.setFailMessage("文件不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean previewDocx() {
		return true;
	}

	/**
	 * 文件下载
	 * 
	 * @param file
	 * @param fileName
	 * @param response
	 * @return6
	 */
	@SuppressWarnings("finally")
	@PermissionSearch
	private boolean display(File file, String fileName,
			HttpServletResponse response) {

		FileInputStream in = null;
		OutputStream out = null;
		try {

			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ fileName);
			in = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			response.flushBuffer();
		} catch (Exception ex) {
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final Exception e) {
				}
			}
			return true;
		}
	}

	@PermissionSearch
	public String uploadImagPrepare() {
		return "uploadImagPrepare";
	}

	/**
	 * 上传图片
	 * 
	 * @return
	 */
	@PermissionSearch
	public String uploadImage() {
		// 重命名
		String newFileName = null;
		boolean saveAsFile = false;
		if (uploadFileName != null && uploadFileName.length() > 0) {

			// String t=DateUtil.datetime("yyyyMM");
			String savedirPath = imgUploadPath;
			String rpath = imgUploadPathUrl;
			File savedir = new File(savedirPath);
			// 如果目录不存在，则新建
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
			newFileName = String.valueOf(new Date().getTime())
					+ FileUtil.getFileExtention(uploadFileName);
			File targetFile = new File(savedirPath + "/" + newFileName);

			saveAsFile = FileUtil.saveAsFile(upload, targetFile);

			if (saveAsFile) {
				this.setSuccessMessage(rpath + "/" + newFileName);
			} else {
				this.setSuccessMessage("图片保存失败");
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 装换文件格式
	 * 
	 * @param 设定文件
	 * @return void DOM对象
	 */
	public void fileChange() {
		List<ProEventDetail> details = eventService
				.getEventFileListsByEventId(eventId);
		// 判断流程是否有附件
		if (null != details) {
			for (int i = 0; i < details.size(); i++) {
				// 验证服务器上是否已存在pdf或者swf格式，如不存在，则进入线程去转换
				String fileId = String.valueOf(details.get(i).getFileId());
				toPreViewFileUtil01(fileId);
			}
		}
	}

	/**
	 * 文件转换 toPreViewFileUtil01:(这里用一句话描述这个方法的作用)
	 * 
	 * @param @param fileId
	 * @param @return 设定文件
	 * @return String DOM对象
	 */
	public synchronized void toPreViewFileUtil01(String fileId) {
		System.out.println(openOfficeHome);
		BudgetFileTmp fileTmp = fileService.getFileByFileId(Long
				.parseLong(fileId.trim()));
		File sourceFile = new File(wfeDownloadPath + "/"
				+ fileTmp.getSubFolders() + "/" + fileTmp.getFileNameNew());// 转换源文件
		System.out.println(sourceFile);
		// String absoluteFile = new File("").getAbsolutePath();
		File pdfFile = new File(readOnlinePath + "/" + fileId + ".pdf");// PDF目标文件
		File swfFile = new File(readOnlinePath + "/" + fileId + ".swf");// SWF目标文件
		Runtime r;
		System.out.println("第一步：生成文件对象，准备转换");
		String suffix = StringUtils.substring(fileTmp.getFileNameNew(),
				StringUtils.lastIndexOf(fileTmp.getFileNameNew(), '.'))
				.toUpperCase();
		if (sourceFile.exists()) {
			if (".DOC".equals(suffix) || ".DOCX".equals(suffix)
					|| ".PPT".equals(suffix) || ".PPTX".equals(suffix)
					|| ".XLS".equals(suffix) || ".XLSX".equals(suffix)
					|| ".CSV".equals(suffix) || ".TXT".equals(suffix)
					|| ".XLSM".equals(suffix) || ".BPM".equals(suffix)
					|| ".GIF".equals(suffix)) {
				if (!pdfFile.exists()) {
					// 防止并发
					CmsTbDict dict = new CmsTbDict();
					dict.setStart(0);
					dict.setEnd(10);
					dict.setDictTypeValue("usered@filePreview");
					List<CmsTbDict> dlist = dictService.getDictList(dict);
					if (null != dlist) {
						if (dlist.size() > 0) {
							if ("N".equals(dlist.get(0).getItemValue())) {// 如为空闲，则可进去转换文档
								try {
									startService();
									// 变更字典值为Y在用
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("Y");
									dictY.setItemDescription("在用");
									dictService.updateDict(dictY);
									OfficeDocumentConverter converter = new OfficeDocumentConverter(
											officeManager);
									converter.convert(sourceFile, pdfFile);
									System.out.println("第二步：转换为PDF格式	路径"
											+ pdfFile.getPath());
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									stopService();
									// 变更字典值为N空闲
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("空闲");
									dictService.updateDict(dictY);
								}
							} else {
								long diff = (new Date()).getTime()
										- dlist.get(0).getLastModify()
												.getTime();
								long day = diff / nd;// 计算差多少天
								long min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
								System.out.println("openOffice服务时间："
										+ dlist.get(0).getLastModify() + "@@@@"
										+ new Date());
								System.out.println("openOffice服务时间差：" + min
										+ "分钟");
								if (min > 2) {
									stopService();
									System.out.println("openOffice服务停止!");
									// 变更字典值为N空闲
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("空闲");
									dictService.updateDict(dictY);
								}
								System.out.println("openOffice服务正被占用，稍后再访问!");
							}
						}
					}
				} else {
					System.out.println("已转换为PDF，无需再次转换");
				}
			}
		} else {
			System.out.println("要转换的文件不存在");
		}
		// 转换成swf文件
		r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (".DOC".equals(suffix) || ".DOCX".equals(suffix)
					|| ".PPT".equals(suffix) || ".PPTX".equals(suffix)
					|| ".XLS".equals(suffix) || ".XLSX".equals(suffix)
					|| ".CSV".equals(suffix) || ".TXT".equals(suffix)
					|| ".XLSM".equals(suffix) || ".BPM".equals(suffix)
					|| ".GIF".equals(suffix)) {
				if (pdfFile.exists()) {
					try {
						Process p = r.exec(pdf2swfHome + " -t "
								+ pdfFile.getPath() + " -s flashversion=9 -o "
								+ swfFile.getPath());
						System.out.println(pdf2swfHome);
						BufferedReader bufferedReader = new BufferedReader(
								new InputStreamReader(p.getInputStream()));

						while (bufferedReader.readLine() != null)
							;
						try {
							p.waitFor();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						p.waitFor();
						swfFile.createNewFile();
						System.out.println("第三步：转换为SWF格式	路径："
								+ swfFile.getPath());
						System.out.println("第si步：转换为SWF格式mingcheng："
								+ swfFile.getName());
						if (pdfFile.exists()) {
							pdfFile.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
						try {
							throw e;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} else {
					System.out.println("PDF文件不存在，无法转换");
				}
			}
			if (".FONT".equals(suffix) || ".JPEG".equals(suffix)
					|| ".JPG".equals(suffix) || ".PDF".equals(suffix)
					|| ".PNG".equals(suffix) || ".WAV".equals(suffix)) {
				String cmd = getToolPath(suffix) + sourceFile.getPath()
						+ " -o " + swfFile.getPath();
				try {
					Process p = r.exec(cmd);
					System.out.println(cmd);
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(p.getInputStream()));

					while (bufferedReader.readLine() != null)
						;
					try {
						p.waitFor();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p.waitFor();
					swfFile.createNewFile();
					System.out.println("直接转换为SWF格式  路径：" + swfFile.getPath());
					System.out.println("直接转换为SWF格式名称：" + swfFile.getName());
					if (pdfFile.exists()) {
						pdfFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					try {
						throw e;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				System.out.println("文件格式不能转换!");
			}
		} else {
			System.out.println("已经转为SWF文件，无需再次转换");
		}
	}

	@SuppressWarnings("unused")
	private static String getToolPath(String type) {
		String osName = System.getProperty("os.name");
		type = type.replace(".", "");
		System.out.println("服务器环境:-----" + osName + "文件type：---" + type);
		if (Pattern.matches("Linux.*", osName)) {
			if ("FONT".equals(type)) {
				return "/usr/local/bin/font2swf ";
			} else if ("GIF".equals(type)) {
				return "/usr/local/bin/gif2swf ";
			} else if ("JPEG".equals(type) || "JPG".equals(type)) {
				return "/usr/local/bin/jpeg2swf -T 9 ";
			} else if ("PDF".equals(type)) {
				return "/usr/local/bin/pdf2swf -T 9 -t ";
			} else if ("PNG".equals(type)) {
				return "/usr/local/bin/png2swf -T 9 ";
			} else if ("WAV".equals(type)) {
				return "/usr/local/bin/wav2swf ";
			}
		} else if (Pattern.matches("Windows.*", osName)) {
			return "";
		} else if (Pattern.matches("Mac.*", osName)) {
			return "";
		}

		return null;
	}

	public IFileService getFileService() {
		return fileService;
	}

	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}

	public String getWfeDownloadPath() {
		return wfeDownloadPath;
	}

	public void setWfeDownloadPath(String wfeDownloadPath) {
		this.wfeDownloadPath = wfeDownloadPath;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getImgUploadPath() {
		return imgUploadPath;
	}

	public void setImgUploadPath(String imgUploadPath) {
		this.imgUploadPath = imgUploadPath;
	}

	public String getImgUploadPathUrl() {
		return imgUploadPathUrl;
	}

	public void setImgUploadPathUrl(String imgUploadPathUrl) {
		this.imgUploadPathUrl = imgUploadPathUrl;
	}

	public String getSwfFileName() {
		return swfFileName;
	}

	public void setSwfFileName(String swfFileName) {
		this.swfFileName = swfFileName;
	}

	public String getOpenOfficeHome() {
		return openOfficeHome;
	}

	public void setOpenOfficeHome(String openOfficeHome) {
		this.openOfficeHome = openOfficeHome;
	}

	public String getPdf2swfHome() {
		return pdf2swfHome;
	}

	public void setPdf2swfHome(String pdf2swfHome) {
		this.pdf2swfHome = pdf2swfHome;
	}

	public String getReadOnlinePath() {
		return readOnlinePath;
	}

	public void setReadOnlinePath(String readOnlinePath) {
		this.readOnlinePath = readOnlinePath;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public IEventService getEventService() {
		return eventService;
	}

	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public String getContractFilePath() {
		return contractFilePath;
	}

	public void setContractFilePath(String contractFilePath) {
		this.contractFilePath = contractFilePath;
	}

	public String getContractFileName() {
		return contractFileName;
	}

	public void setContractFileName(String contractFileName) {
		this.contractFileName = contractFileName;
	}

	// /**
	// * 协同办公附件转换byws
	// * @param fileId
	// * @param contractFileName
	// * @param contractFilePath
	// * @param strm
	// */
	// public synchronized void toPreViewFileUtil12(String fileId, String
	// contractFilePath, String contractFileName, String strm){
	// System.out.println(contractFilePath+"--"+contractFileName);
	// File sourceFile = new File(contractFilePath);// 转换源文件
	// System.out.println(sourceFile);
	// // String absoluteFile = new File("").getAbsolutePath();
	// File pdfFile = new File(readOnlinePath + "/" +strm+"/"+ fileId +
	// ".pdf");// PDF目标文件
	// File swfFile = new File(readOnlinePath + "/" +strm+"/"+ fileId +
	// ".swf");// SWF目标文件
	// Runtime r;
	// System.out.println("第一步：生成文件对象，准备转换");
	// String
	// suffix=StringUtils.substring(contractFileName,StringUtils.lastIndexOf(contractFileName,
	// '.')).toUpperCase();
	// if (sourceFile.exists()) {
	// if (".DOC".equals(suffix) || ".DOCX".equals(suffix) ||
	// ".PPT".equals(suffix) || ".PPTX".equals(suffix)
	// || ".XLS".equals(suffix) ||
	// ".XLSX".equals(suffix)||".CSV".equals(suffix)||".TXT".equals(suffix)
	// ||".XLSM".equals(suffix)||".BPM".equals(suffix)|| ".GIF".equals(suffix))
	// {
	// if (!pdfFile.exists()) {
	// System.out.println("pdfFile.getPath()"+"11--------------");
	// //防止并发
	// CmsTbDict dict=new CmsTbDict();
	// dict.setStart(0);
	// dict.setEnd(10);
	// dict.setDictTypeValue("usered@filePreview");
	// List<CmsTbDict> dlist=dictService.getDictList(dict);
	// if(null!=dlist){
	// if(dlist.size()>0){
	// if("N".equals(dlist.get(0).getItemValue())){//如为空闲，则可进去转换文档
	// try {
	// startService();
	// //变更字典值为Y在用
	// CmsTbDict dictY=new CmsTbDict();
	// dictY.setItemId(dlist.get(0).getItemId());
	// dictY.setItemValue("Y");
	// dictY.setItemDescription("在用");
	// dictService.updateDict(dictY);
	// OfficeDocumentConverter converter = new
	// OfficeDocumentConverter(officeManager);
	// converter.convert(sourceFile, pdfFile);
	// System.out.println("第二步：转换为PDF格式	路径" + pdfFile.getPath());
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// stopService();
	// //变更字典值为N空闲
	// CmsTbDict dictY=new CmsTbDict();
	// dictY.setItemId(dlist.get(0).getItemId());
	// dictY.setItemValue("N");
	// dictY.setItemDescription("空闲");
	// dictService.updateDict(dictY);
	// }
	// }else{
	// long diff=(new Date()).getTime()-dlist.get(0).getLastModify().getTime();
	// long day = diff / nd;// 计算差多少天
	// long min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
	// System.out.println("openOffice服务时间："+dlist.get(0).getLastModify()+"@@@@"+new
	// Date());
	// System.out.println("openOffice服务时间差："+min+"分钟");
	// if(min>2){
	// stopService();
	// System.out.println("openOffice服务停止!");
	// //变更字典值为N空闲
	// CmsTbDict dictY=new CmsTbDict();
	// dictY.setItemId(dlist.get(0).getItemId());
	// dictY.setItemValue("N");
	// dictY.setItemDescription("空闲");
	// dictService.updateDict(dictY);
	// }
	// System.out.println("openOffice服务正被占用，稍后再访问!");
	// }
	// }}
	// } else {
	// System.out.println("已转换为PDF，无需再次转换");
	// }
	// }
	// } else {
	// System.out.println("要转换的文件不存在");
	// }
	// // 转换成swf文件
	// r = Runtime.getRuntime();
	// if (!swfFile.exists()) {
	// if (".DOC".equals(suffix) || ".DOCX".equals(suffix) ||
	// ".PPT".equals(suffix) || ".PPTX".equals(suffix)
	// || ".XLS".equals(suffix) ||
	// ".XLSX".equals(suffix)||".CSV".equals(suffix)||".TXT".equals(suffix)
	// ||".XLSM".equals(suffix)||".BPM".equals(suffix)|| ".GIF".equals(suffix))
	// {
	// if (pdfFile.exists()) {
	// try {
	// Process p =
	// r.exec(pdf2swfHome + " -t " + pdfFile.getPath()
	// + " -s flashversion=9 -o " + swfFile.getPath());
	// System.out.println(pdf2swfHome);
	// BufferedReader bufferedReader = new BufferedReader(new
	// InputStreamReader(p.getInputStream()));
	//
	// while (bufferedReader.readLine() != null);
	// try {
	// p.waitFor();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// p.waitFor();
	// swfFile.createNewFile();
	// System.out.println("第三步：转换为SWF格式	路径：" + swfFile.getPath());
	// System.out.println("第si步：转换为SWF格式mingcheng：" + swfFile.getName());
	// if (pdfFile.exists()) {
	// pdfFile.delete();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// try {
	// throw e;
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// }
	// }
	// } else {
	// System.out.println("PDF文件不存在，无法转换");
	// }
	// }if (".FONT".equals(suffix) || ".JPEG".equals(suffix) ||
	// ".JPG".equals(suffix)
	// ||".PDF".equals(suffix) || ".PNG".equals(suffix) ||
	// ".WAV".equals(suffix)) {
	// String cmd = getToolPath(suffix) + sourceFile.getPath() + " -o " +
	// swfFile.getPath();
	// try {
	// Process p =
	// r.exec(cmd);
	// System.out.println(cmd);
	// BufferedReader bufferedReader = new BufferedReader(new
	// InputStreamReader(p.getInputStream()));
	//
	// while (bufferedReader.readLine() != null);
	// try {
	// p.waitFor();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// p.waitFor();
	// swfFile.createNewFile();
	// System.out.println("直接转换为SWF格式  路径：" + swfFile.getPath());
	// System.out.println("直接转换为SWF格式名称：" + swfFile.getName());
	// if (pdfFile.exists()) {
	// pdfFile.delete();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// try {
	// throw e;
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// }
	// }
	// }else{
	// System.out.println("文件格式不能转换!");
	// }
	// } else {
	// System.out.println("已经转为SWF文件，无需再次转换");
	// }
	// }
	public String getOfficePath() {
		return officePath;
	}

	public void setOfficePath(String officePath) {
		this.officePath = officePath;
	}

}
