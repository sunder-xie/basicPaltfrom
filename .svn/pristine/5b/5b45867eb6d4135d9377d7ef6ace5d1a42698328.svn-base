package com.kintiger.platform.qq_email.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.kintiger.platform.base.action.BaseAction;

/**
 * Title: 无附件发送邮件 Description: basisPlatform
 * 
 * @author: xg.chen
 * @date:2017年3月10日 下午2:35:46
 */
public class SendHtmlEmailNoFile extends BaseAction implements Runnable {
	// 邮件发送协议
	private final static String PROTOCOL = "smtp";
	// SMTP邮件服务器
	private final static String HOST = "smtp.exmail.qq.com";
	// SMTP邮件服务器默认端口
	private final static String PORT = "25";
	// 是否要求身份认证
	private final static String IS_AUTH = "true";
	// 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
	private final static String IS_ENABLED_DEBUG_MOD = "true";
	// 发件人
	private static String from = "exp@chinaxpp.com";
	// 初始化连接邮件服务器的会话信息
	private static Properties props = null;

	static {
		props = new Properties();
		props.setProperty("mail.transport.protocol", PROTOCOL);
		props.setProperty("mail.smtp.host", HOST);
		props.setProperty("mail.smtp.port", PORT);
		props.setProperty("mail.smtp.auth", IS_AUTH);
		props.setProperty("mail.debug", IS_ENABLED_DEBUG_MOD);
	}
	// 参数值
	private Address[] address;
	private String title;
	private String content;

	/**
	 * 无附件发送邮件
	 * @param address 邮件地址
	 * @param title 主题
	 * @param content 内容
	 */
	public SendHtmlEmailNoFile(Address[] address, String title, String content) {
		super();
		this.address = address;
		this.title = title;
		this.content = content;
	}

	@Override
	public void run() {
		try {
			// 创建Session实例对象
			Session session = Session.getDefaultInstance(props,
					new MyAuthenticator());
			// 创建邮件内容
			MimeMessage message = new MimeMessage(session);
			// 邮件主题,并指定编码格式
			message.setSubject(title, "utf-8");
			// 发件人
			message.setFrom(new InternetAddress(from));
			// 收件人
			message.setRecipients(RecipientType.BCC, address);
			// 创建一个MIME子类型为“related”的MimeMultipart对象
			MimeMultipart mp = new MimeMultipart("mixed");
			System.out.println("mp----->" + mp);
			// 创建一个表示正文的MimeBodyPart对象，并将它加入到前面创建的MimeMultipart对象中
			MimeBodyPart htmlPart = new MimeBodyPart();
			mp.addBodyPart(htmlPart);
			System.out.println("htmlPart----->" + htmlPart);
			// 将MimeMultipart对象设置为整个邮件的内容
			message.setContent(mp);

			// 创建一个MIME子类型为"alternative"的MimeMultipart对象，并作为前面创建的htmlPart对象的邮件内容
			MimeMultipart htmlMultipart = new MimeMultipart("alternative");
			// 创建一个表示html正文的MimeBodyPart对象
			MimeBodyPart htmlBodypart = new MimeBodyPart();
			// 其中cid=androidlogo.gif是引用邮件内部的图片，即imagePart.setContentID("androidlogo.gif");方法所保存的图片
			htmlBodypart.setContent("<span'>" + content + "</span>",
					"text/html;charset=utf-8");
			htmlMultipart.addBodyPart(htmlBodypart);
			htmlPart.setContent(htmlMultipart);

			// 保存并生成最终的邮件内容
			message.saveChanges();

			// 发送邮件
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Title: 邮件发送默认值设置 Description: basisPlatform
	 * 
	 * @author: xg.chen
	 * @date:2017年3月10日 下午2:09:19
	 */
	public static class MyAuthenticator extends Authenticator {
		private String username = "exp@chinaxpp.com";
		private String password = "52xpp!123";

		// private String password = "1";

		public MyAuthenticator() {
			super();
		}

		public MyAuthenticator(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}
//  TEST
	public static void main(String[] args) throws UnsupportedEncodingException {
		/*for (int i = 0; i < 20; i++) {
			PoolSend send = new PoolSend();
			send.send(new SendHtmlEmailNoFile(
					new Address[] { new InternetAddress("869095990@qq.com", "",
							"utf-8") }, "aa", "aa"));
			send.close();
		}*/
	}
}
