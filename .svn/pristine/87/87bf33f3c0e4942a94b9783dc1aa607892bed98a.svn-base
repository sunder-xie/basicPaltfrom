package com.kintiger.platform.framework.util;



public class PreViewFileUtil {

//	public boolean previewDoc(File file, BudgetFileTmp fileTmp, HttpServletResponse response) {
//		try {
//
//			HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(
//					file));// WordToHtmlUtils.loadDoc(new
//							// FileInputStream(inputFile));
//
//			WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//					DocumentBuilderFactory.newInstance().newDocumentBuilder()
//							.newDocument());
//			final  long fileid =fileTmp.getFileId();
//			wordToHtmlConverter.setPicturesManager(new PicturesManager() {
//				public String savePicture(byte[] content,
//						PictureType pictureType, String suggestedName,
//						float widthInches, float heightInches) {
//
//					return "/preview/" +fileid+"/"+ suggestedName;
//				}
//			});
//			wordToHtmlConverter.processDocument(wordDocument);
//			// save pictures
//			List<Picture> pics = wordDocument.getPicturesTable()
//					.getAllPictures();
//
//			if (pics != null) {
//				for (int i = 0; i < pics.size(); i++) {
//					Picture pic = pics.get(i);
//
//					try {
//						File dicFile = new File("");
//						String absoluteFile = dicFile.getAbsolutePath();
//							File filedir=new File(absoluteFile + "\\webapps\\preview\\"+fileTmp.getFileId()+"\\");
//						  if (!filedir.exists()) {
//							  filedir.mkdirs();
//							  }
//						String filepath = absoluteFile + "\\webapps\\preview\\"+fileTmp.getFileId()+"\\"
//								+ pic.suggestFullFileName();
//						FileOutputStream fileOutputStream = new FileOutputStream(
//								filepath);
//						pic.writeImageContent(fileOutputStream);
//
//						fileOutputStream.flush();
//						fileOutputStream.close();
//
//					} catch (FileNotFoundException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			
//			Document htmlDocument = wordToHtmlConverter.getDocument();
//			ServletOutputStream out = response.getOutputStream();
//			DOMSource domSource = new DOMSource(htmlDocument);
//			StreamResult streamResult = new StreamResult(out);
//			TransformerFactory tf = TransformerFactory.newInstance();
//			Transformer serializer = tf.newTransformer();
//			serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
//			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//			serializer.setOutputProperty(OutputKeys.METHOD, "html");
//			serializer.transform(domSource, streamResult);
//			out.flush();
//			out.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//
//	}
	

//	public boolean previewDocx(File f, HttpServletResponse response, BudgetFileTmp fileTmp)
//		throws Exception {
//		try {
//			if (!f.exists()) {
//				System.out.println("Sorry File does not Exists!");
//			} else {
//
//				// 1) Load DOCX into XWPFDocument
//				InputStream in = new FileInputStream(f);
//				XWPFDocument document = new XWPFDocument(in);
//
//				// 2) Prepare XHTML options (here we set the IURIResolver to
//				// load images from a "word/media" folder)
//				File dicFile = new File("");
//				String absoluteFile = dicFile.getAbsolutePath();
//				// 建立图片文件目录
//				String imgPath = absoluteFile + "\\webapps\\preview" + "\\" + fileTmp.getFileId();
//				File imageFolderFile = new File(imgPath);
//				if (!imageFolderFile.exists()) {
//					imageFolderFile.mkdirs();
//				}
//				XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
//				options.setExtractor(new FileImageExtractor(imageFolderFile));
//				// 获取所图片
//				List<XWPFPictureData> piclist = document.getAllPictures();
//				for (int i = 0; i < piclist.size(); i++) {
//					XWPFPictureData pic = piclist.get(i);
//
//					// 获取图片数据流
//					byte[] picbyte = pic.getData();
//					// 将图片写入本地文件
//					FileOutputStream fos =
//						new FileOutputStream(absoluteFile + "\\webapps\\preview" + "\\" + fileTmp.getFileId() + "\\" + pic.getFileName());
//					fos.write(picbyte);
//					fos.flush();
//					fos.close();
//				}
//				// 3) Convert XWPFDocument to XHTML
//				OutputStream out = response.getOutputStream();
//				XHTMLConverter.getInstance().convert(document, out, options);
//				out.flush();
//				out.close();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//
//	}

	/**
	 * 抽取docx文件文本内容
	 */

//	public String extractDocxContent(File document) {
//		String contents = "";
//		String wordDocxPath = document.toString();
//		StringBuffer sbf = new StringBuffer();
//		try {
//			// 载入文档
//			OPCPackage opcPackage = POIXMLDocument.openPackage(wordDocxPath);
//			XWPFDocument xwpfd = new XWPFDocument(opcPackage);
//			POIXMLTextExtractor ex = new XWPFWordExtractor(xwpfd);
//
//			// 读取文字
//			contents = ex.getText().trim();
//			//
//			// System.out.println(ByteUtils.byteToHexString(contents.getBytes()));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return contents;
//
//	}

	/**
	 * 
	 抽取文件中的图片，并保存
	 */

//	public void extractImage(File document) {
//		try {
//			String imgPath = "D:/sjqxgj/datafile/img";
//			String wordDocxPath = document.toString();
//			// 载入文档
//			FileInputStream fis = new FileInputStream(wordDocxPath);
//			OPCPackage opcPackage = POIXMLDocument.openPackage(wordDocxPath);
//			XWPFDocument xwpfd = new XWPFDocument(opcPackage);
//			// 建立图片文件目录
//			File imgFile = new File(imgPath);
//			if (!imgFile.exists()) {
//				imgFile.mkdir();
//			}
//			// 获取所图片
//			List piclist = xwpfd.getAllPictures();
//			for (int j = 0; j < piclist.size(); j++) {
//				XWPFPictureData pic = (XWPFPictureData) piclist.get(j);
//				// 获取图片数据流
//				byte[] picbyte = pic.getData();
//				// 将图片写入本地文件
//				FileOutputStream fos = new FileOutputStream(imgPath + "/" + document.getName() + j + ".jpg");
//				fos.write(picbyte);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

}
