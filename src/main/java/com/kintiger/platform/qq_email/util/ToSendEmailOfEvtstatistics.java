package com.kintiger.platform.qq_email.util;

import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.kintiger.platform.evtstatistics.pojo.Evtstatistics;
import com.kintiger.platform.qq_email.util.pojo.Salary;

import java.util.List;

public class ToSendEmailOfEvtstatistics {
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
		private static String from = "xppet@chinaxpp.com";	
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
	
	/**
	 * 发送简单的html邮件
	 */
	public static void SendEmailForEvtstatistics(String email,String content)throws Exception {
		
			// 创建Session实例对象
			Session session = Session.getInstance(props, new MyAuthenticator());
			// 创建MimeMessage实例对象
			MimeMessage message = new MimeMessage(session);
			// 设置邮件主题
			message.setSubject("流程超时提醒");
			// 设置发送人
			message.setFrom(new InternetAddress(from));
			// 设置发送时间
			message.setSentDate(new Date());
			// 设置收件人
			message.setRecipients(RecipientType.TO, InternetAddress.parse(email+"@chinaxpp.com")); 
			// 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为gbk
			message.setContent(content,"text/html;charset=gbk");
			// 保存并生成最终的邮件内容
			message.saveChanges();

			// 发送邮件
			Transport.send(message);
		
	}

	public static class MyAuthenticator extends Authenticator {
		
		private String username = "xppet@chinaxpp.com";

		private String password = "Xppet123";

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
}
