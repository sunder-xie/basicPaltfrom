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

	private long nd = 1000 * 24 * 60 * 60;// һ��ĺ�����
	private long nh = 1000 * 60 * 60;// һСʱ�ĺ�����
	private long nm = 1000 * 60;// һ���ӵĺ�����
	private long ns = 1000;// һ���ӵĺ�����

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
			this.setFailMessage("�ļ�������!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * Эͬ�칫���� byws
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
	 * Эͬ�칫��ͬԤ�� byws
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
		File sourceFile = new File(contractFilePath);// ת��Դ�ļ�
		System.out.println(sourceFile);
		// String absoluteFile = new File("").getAbsolutePath();
		File pdfFile = new File(officePath + "/" + strm + "/" + fileId + ".pdf");// PDFĿ���ļ�
		File swfFile = new File(officePath + "/" + strm + "/" + fileId + ".swf");// SWFĿ���ļ�
		System.out.println(pdfFile);
		Runtime r;
		System.out.println("��һ���������ļ�����׼��ת��");
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
					// ��ֹ����
					CmsTbDict dict = new CmsTbDict();
					dict.setStart(0);
					dict.setEnd(10);
					dict.setDictTypeValue("usered@filePreview");
					List<CmsTbDict> dlist = dictService.getDictList(dict);
					if (null != dlist) {
						if (dlist.size() > 0) {
							if ("N".equals(dlist.get(0).getItemValue())) {// ��Ϊ���У���ɽ�ȥת���ĵ�
								try {
									startService();
									// ����ֵ�ֵΪY����
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("Y");
									dictY.setItemDescription("����");
									dictService.updateDict(dictY);
									OfficeDocumentConverter converter = new OfficeDocumentConverter(
											officeManager);
									converter.convert(sourceFile, pdfFile);
									System.out.println("�ڶ�����ת��ΪPDF��ʽ	·��"
											+ pdfFile.getPath());
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									stopService();
									// ����ֵ�ֵΪN����
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("����");
									dictService.updateDict(dictY);
								}
							} else {
								long diff = (new Date()).getTime()
										- dlist.get(0).getLastModify()
												.getTime();
								long day = diff / nd;// ����������
								long min = diff % nd % nh / nm + day * 24 * 60;// �������ٷ���
								System.out.println("openOffice����ʱ�䣺"
										+ dlist.get(0).getLastModify() + "@@@@"
										+ new Date());
								System.out.println("openOffice����ʱ��" + min
										+ "����");
								if (min > 2) {
									stopService();
									System.out.println("openOffice����ֹͣ!");
									// ����ֵ�ֵΪN����
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("����");
									dictService.updateDict(dictY);
								}
								System.out.println("openOffice��������ռ�ã��Ժ��ٷ���!");
							}
						}
					}
				} else {
					// System.out.println("��ת��ΪPDF�������ٴ�ת��");
				}
			} else {
				System.out.println("Ҫת�����ļ�������");
			}
		}
		// ת����swf�ļ�
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
						System.out.println("��������ת��ΪSWF��ʽ	·����"
								+ swfFile.getPath());
						System.out.println("��si����ת��ΪSWF��ʽmingcheng��"
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
					System.out.println("PDF�ļ������ڣ��޷�ת��");
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
					System.out.println("ֱ��ת��ΪSWF��ʽ	·����" + swfFile.getPath());
					System.out.println("ֱ��ת��ΪSWF��ʽ���ƣ�" + swfFile.getName());
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
				System.out.println("�ļ���ʽ����ת��!");
			}
		} else {
			System.out.println("�Ѿ�תΪSWF�ļ��������ٴ�ת��");
		}
		swfFileName = officePath + "/" + strm + "/" + swfFile.getName();
		System.out.println(swfFileName);

		return "toPreviewFile";

	}

	/**
	 * ��ת����Ԥ������
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
				+ fileTmp.getSubFolders() + "/" + fileTmp.getFileNameNew());// ת��Դ�ļ�
		System.out.println(sourceFile);
		// String absoluteFile = new File("").getAbsolutePath();
		File pdfFile = new File(readOnlinePath + "/" + fileId + ".pdf");// PDFĿ���ļ�
		File swfFile = new File(readOnlinePath + "/" + fileId + ".swf");// SWFĿ���ļ�
		Runtime r;
		System.out.println("��һ���������ļ�����׼��ת��");
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
					// ��ֹ����
					CmsTbDict dict = new CmsTbDict();
					dict.setStart(0);
					dict.setEnd(10);
					dict.setDictTypeValue("usered@filePreview");
					List<CmsTbDict> dlist = dictService.getDictList(dict);
					if (null != dlist) {
						if (dlist.size() > 0) {
							if ("N".equals(dlist.get(0).getItemValue())) {// ��Ϊ���У���ɽ�ȥת���ĵ�
								try {
									startService();
									// ����ֵ�ֵΪY����
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("Y");
									dictY.setItemDescription("����");
									dictService.updateDict(dictY);
									OfficeDocumentConverter converter = new OfficeDocumentConverter(
											officeManager);
									converter.convert(sourceFile, pdfFile);
									System.out.println("�ڶ�����ת��ΪPDF��ʽ	·��"
											+ pdfFile.getPath());
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									stopService();
									// ����ֵ�ֵΪN����
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("����");
									dictService.updateDict(dictY);
								}
							} else {
								long diff = (new Date()).getTime()
										- dlist.get(0).getLastModify()
												.getTime();
								long day = diff / nd;// ����������
								long min = diff % nd % nh / nm + day * 24 * 60;// �������ٷ���
								System.out.println("openOffice����ʱ�䣺"
										+ dlist.get(0).getLastModify() + "@@@@"
										+ new Date());
								System.out.println("openOffice����ʱ��" + min
										+ "����");
								if (min > 2) {
									stopService();
									System.out.println("openOffice����ֹͣ!");
									// ����ֵ�ֵΪN����
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("����");
									dictService.updateDict(dictY);
								}
								System.out.println("openOffice��������ռ�ã��Ժ��ٷ���!");
							}
						}
					}
				} else {
					// System.out.println("��ת��ΪPDF�������ٴ�ת��");
				}
			} else {
				System.out.println("Ҫת�����ļ�������");
			}
		}
		// ת����swf�ļ�
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
						System.out.println("��������ת��ΪSWF��ʽ	·����"
								+ swfFile.getPath());
						System.out.println("��si����ת��ΪSWF��ʽmingcheng��"
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
					System.out.println("PDF�ļ������ڣ��޷�ת��");
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
					System.out.println("ֱ��ת��ΪSWF��ʽ	·����" + swfFile.getPath());
					System.out.println("ֱ��ת��ΪSWF��ʽ���ƣ�" + swfFile.getName());
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
				System.out.println("�ļ���ʽ����ת��!");
			}
		} else {
			System.out.println("�Ѿ�תΪSWF�ļ��������ٴ�ת��");
		}
		swfFileName = readOnlinePath + "/" + swfFile.getName();

		return "toPreviewFile";

	}

	public void startService() {
		DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
		try {
			System.out.println("prepare׼����������....");
			configuration.setOfficeHome(new File(openOfficeHome));// ����OpenOffice.org��װĿ¼
			System.out.println(new File(openOfficeHome));
			configuration.setPortNumbers(port); // ����ת���˿ڣ�Ĭ��Ϊ8100
			configuration.setTaskExecutionTimeout(1000 * 60 * 5L);// ��������ִ�г�ʱΪ5����
			configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);// ����������г�ʱΪ24Сʱ

			officeManager = configuration.buildOfficeManager();
			officeManager.start(); // ��������
			System.out.println("success officeת�����������ɹ�!");
		} catch (Exception ce) {
			System.out.println("officeת����������ʧ��!��ϸ��Ϣ:" + ce);
		}
	}

	public void stopService() {
		System.out.println("�ر�officeת������....");
		if (officeManager != null) {
			officeManager.stop();
		}
		System.out.println("�ر�officeת���ɹ�!");
	}

	/**
	 * ����Ԥ��
	 * 
	 * @return
	 */
	public void previewFile() {
		try {
			BudgetFileTmp fileTmp = fileService.getFileByFileId(Long
					.parseLong(fileId.trim()));
			File source = new File(wfeDownloadPath + "/"
					+ fileTmp.getSubFolders() + "/" + fileTmp.getFileNameNew());// ת��Դ�ļ�
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
				this.setFailMessage("�ļ�������!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean previewDocx() {
		return true;
	}

	/**
	 * �ļ�����
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
	 * �ϴ�ͼƬ
	 * 
	 * @return
	 */
	@PermissionSearch
	public String uploadImage() {
		// ������
		String newFileName = null;
		boolean saveAsFile = false;
		if (uploadFileName != null && uploadFileName.length() > 0) {

			// String t=DateUtil.datetime("yyyyMM");
			String savedirPath = imgUploadPath;
			String rpath = imgUploadPathUrl;
			File savedir = new File(savedirPath);
			// ���Ŀ¼�����ڣ����½�
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
				this.setSuccessMessage("ͼƬ����ʧ��");
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * װ���ļ���ʽ
	 * 
	 * @param �趨�ļ�
	 * @return void DOM����
	 */
	public void fileChange() {
		List<ProEventDetail> details = eventService
				.getEventFileListsByEventId(eventId);
		// �ж������Ƿ��и���
		if (null != details) {
			for (int i = 0; i < details.size(); i++) {
				// ��֤���������Ƿ��Ѵ���pdf����swf��ʽ���粻���ڣ�������߳�ȥת��
				String fileId = String.valueOf(details.get(i).getFileId());
				toPreViewFileUtil01(fileId);
			}
		}
	}

	/**
	 * �ļ�ת�� toPreViewFileUtil01:(������һ�仰�����������������)
	 * 
	 * @param @param fileId
	 * @param @return �趨�ļ�
	 * @return String DOM����
	 */
	public synchronized void toPreViewFileUtil01(String fileId) {
		System.out.println(openOfficeHome);
		BudgetFileTmp fileTmp = fileService.getFileByFileId(Long
				.parseLong(fileId.trim()));
		File sourceFile = new File(wfeDownloadPath + "/"
				+ fileTmp.getSubFolders() + "/" + fileTmp.getFileNameNew());// ת��Դ�ļ�
		System.out.println(sourceFile);
		// String absoluteFile = new File("").getAbsolutePath();
		File pdfFile = new File(readOnlinePath + "/" + fileId + ".pdf");// PDFĿ���ļ�
		File swfFile = new File(readOnlinePath + "/" + fileId + ".swf");// SWFĿ���ļ�
		Runtime r;
		System.out.println("��һ���������ļ�����׼��ת��");
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
					// ��ֹ����
					CmsTbDict dict = new CmsTbDict();
					dict.setStart(0);
					dict.setEnd(10);
					dict.setDictTypeValue("usered@filePreview");
					List<CmsTbDict> dlist = dictService.getDictList(dict);
					if (null != dlist) {
						if (dlist.size() > 0) {
							if ("N".equals(dlist.get(0).getItemValue())) {// ��Ϊ���У���ɽ�ȥת���ĵ�
								try {
									startService();
									// ����ֵ�ֵΪY����
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("Y");
									dictY.setItemDescription("����");
									dictService.updateDict(dictY);
									OfficeDocumentConverter converter = new OfficeDocumentConverter(
											officeManager);
									converter.convert(sourceFile, pdfFile);
									System.out.println("�ڶ�����ת��ΪPDF��ʽ	·��"
											+ pdfFile.getPath());
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									stopService();
									// ����ֵ�ֵΪN����
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("����");
									dictService.updateDict(dictY);
								}
							} else {
								long diff = (new Date()).getTime()
										- dlist.get(0).getLastModify()
												.getTime();
								long day = diff / nd;// ����������
								long min = diff % nd % nh / nm + day * 24 * 60;// �������ٷ���
								System.out.println("openOffice����ʱ�䣺"
										+ dlist.get(0).getLastModify() + "@@@@"
										+ new Date());
								System.out.println("openOffice����ʱ��" + min
										+ "����");
								if (min > 2) {
									stopService();
									System.out.println("openOffice����ֹͣ!");
									// ����ֵ�ֵΪN����
									CmsTbDict dictY = new CmsTbDict();
									dictY.setItemId(dlist.get(0).getItemId());
									dictY.setItemValue("N");
									dictY.setItemDescription("����");
									dictService.updateDict(dictY);
								}
								System.out.println("openOffice��������ռ�ã��Ժ��ٷ���!");
							}
						}
					}
				} else {
					System.out.println("��ת��ΪPDF�������ٴ�ת��");
				}
			}
		} else {
			System.out.println("Ҫת�����ļ�������");
		}
		// ת����swf�ļ�
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
						System.out.println("��������ת��ΪSWF��ʽ	·����"
								+ swfFile.getPath());
						System.out.println("��si����ת��ΪSWF��ʽmingcheng��"
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
					System.out.println("PDF�ļ������ڣ��޷�ת��");
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
					System.out.println("ֱ��ת��ΪSWF��ʽ  ·����" + swfFile.getPath());
					System.out.println("ֱ��ת��ΪSWF��ʽ���ƣ�" + swfFile.getName());
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
				System.out.println("�ļ���ʽ����ת��!");
			}
		} else {
			System.out.println("�Ѿ�תΪSWF�ļ��������ٴ�ת��");
		}
	}

	@SuppressWarnings("unused")
	private static String getToolPath(String type) {
		String osName = System.getProperty("os.name");
		type = type.replace(".", "");
		System.out.println("����������:-----" + osName + "�ļ�type��---" + type);
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
	// * Эͬ�칫����ת��byws
	// * @param fileId
	// * @param contractFileName
	// * @param contractFilePath
	// * @param strm
	// */
	// public synchronized void toPreViewFileUtil12(String fileId, String
	// contractFilePath, String contractFileName, String strm){
	// System.out.println(contractFilePath+"--"+contractFileName);
	// File sourceFile = new File(contractFilePath);// ת��Դ�ļ�
	// System.out.println(sourceFile);
	// // String absoluteFile = new File("").getAbsolutePath();
	// File pdfFile = new File(readOnlinePath + "/" +strm+"/"+ fileId +
	// ".pdf");// PDFĿ���ļ�
	// File swfFile = new File(readOnlinePath + "/" +strm+"/"+ fileId +
	// ".swf");// SWFĿ���ļ�
	// Runtime r;
	// System.out.println("��һ���������ļ�����׼��ת��");
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
	// //��ֹ����
	// CmsTbDict dict=new CmsTbDict();
	// dict.setStart(0);
	// dict.setEnd(10);
	// dict.setDictTypeValue("usered@filePreview");
	// List<CmsTbDict> dlist=dictService.getDictList(dict);
	// if(null!=dlist){
	// if(dlist.size()>0){
	// if("N".equals(dlist.get(0).getItemValue())){//��Ϊ���У���ɽ�ȥת���ĵ�
	// try {
	// startService();
	// //����ֵ�ֵΪY����
	// CmsTbDict dictY=new CmsTbDict();
	// dictY.setItemId(dlist.get(0).getItemId());
	// dictY.setItemValue("Y");
	// dictY.setItemDescription("����");
	// dictService.updateDict(dictY);
	// OfficeDocumentConverter converter = new
	// OfficeDocumentConverter(officeManager);
	// converter.convert(sourceFile, pdfFile);
	// System.out.println("�ڶ�����ת��ΪPDF��ʽ	·��" + pdfFile.getPath());
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// stopService();
	// //����ֵ�ֵΪN����
	// CmsTbDict dictY=new CmsTbDict();
	// dictY.setItemId(dlist.get(0).getItemId());
	// dictY.setItemValue("N");
	// dictY.setItemDescription("����");
	// dictService.updateDict(dictY);
	// }
	// }else{
	// long diff=(new Date()).getTime()-dlist.get(0).getLastModify().getTime();
	// long day = diff / nd;// ����������
	// long min = diff % nd % nh / nm + day * 24 * 60;// �������ٷ���
	// System.out.println("openOffice����ʱ�䣺"+dlist.get(0).getLastModify()+"@@@@"+new
	// Date());
	// System.out.println("openOffice����ʱ��"+min+"����");
	// if(min>2){
	// stopService();
	// System.out.println("openOffice����ֹͣ!");
	// //����ֵ�ֵΪN����
	// CmsTbDict dictY=new CmsTbDict();
	// dictY.setItemId(dlist.get(0).getItemId());
	// dictY.setItemValue("N");
	// dictY.setItemDescription("����");
	// dictService.updateDict(dictY);
	// }
	// System.out.println("openOffice��������ռ�ã��Ժ��ٷ���!");
	// }
	// }}
	// } else {
	// System.out.println("��ת��ΪPDF�������ٴ�ת��");
	// }
	// }
	// } else {
	// System.out.println("Ҫת�����ļ�������");
	// }
	// // ת����swf�ļ�
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
	// System.out.println("��������ת��ΪSWF��ʽ	·����" + swfFile.getPath());
	// System.out.println("��si����ת��ΪSWF��ʽmingcheng��" + swfFile.getName());
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
	// System.out.println("PDF�ļ������ڣ��޷�ת��");
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
	// System.out.println("ֱ��ת��ΪSWF��ʽ  ·����" + swfFile.getPath());
	// System.out.println("ֱ��ת��ΪSWF��ʽ���ƣ�" + swfFile.getName());
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
	// System.out.println("�ļ���ʽ����ת��!");
	// }
	// } else {
	// System.out.println("�Ѿ�תΪSWF�ļ��������ٴ�ת��");
	// }
	// }
	public String getOfficePath() {
		return officePath;
	}

	public void setOfficePath(String officePath) {
		this.officePath = officePath;
	}

}
