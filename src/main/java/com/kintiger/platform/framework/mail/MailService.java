package com.kintiger.platform.framework.mail;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.apache.log4j.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.menu.action.MenuTreeAjaxAction;

public class MailService {

	private static final Log logger = LogFactory
			.getLog(MenuTreeAjaxAction.class);

	// ���巢���ˡ��ռ��ˡ�SMTP���������û��������롢���⡢���ݵ�
	private String displayName;
	private String to;
	private String from;
	private String smtpServer;
	private String username;
	private String password;
	private String subject;
	private String content;
	private boolean ifAuth; // �������Ƿ�Ҫ�����֤
	private String filename = "";
	private List<Mail> files = new ArrayList<Mail>(); // ���ڱ��淢�͸������ļ����ļ���

	/**
	 * ����SMTP��������ַ
	 */
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	/**
	 * ���÷����˵ĵ�ַ
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * ������ʾ������
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * ���÷������Ƿ���Ҫ�����֤
	 */
	public void setIfAuth(boolean ifAuth) {
		this.ifAuth = ifAuth;
	}

	/**
	 * ����E-mail�û���
	 */
	public void setUserName(String username) {
		this.username = username;
	}

	/**
	 * ����E-mail����
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ���ý�����
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * ��������
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * ������������
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * �÷��������ռ�������
	 */
	public void addAttachfile(Mail mailFile) {
		files.add(mailFile);
	}

	/**
	 * ��ʼ��SMTP��������ַ��������E-mail��ַ���û��������롢�����ߡ����⡢����
	 */
	public MailService(String smtpServer, String from, String displayName,
			String username, String password, String to, String subject,
			String content) {
		this.smtpServer = smtpServer;
		this.from = from;
		this.displayName = displayName;
		this.ifAuth = true;
		this.username = username;
		this.password = password;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * ��ʼ��SMTP��������ַ��������E-mail��ַ�������ߡ����⡢����
	 */
	public MailService(String smtpServer, String from, String displayName,
			String to, String subject, String content) {
		this.smtpServer = smtpServer;
		this.from = from;
		this.displayName = displayName;
		this.ifAuth = false;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * �����ʼ�
	 */
	@SuppressWarnings("unused")
	public HashMap<String, String> send() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("state", "success");
		String message = "�ʼ����ͳɹ���";
		Session session = null;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);

		if (ifAuth) { // ��������Ҫ�����֤
			props.put("mail.smtp.auth", "true");
			SmtpAuth smtpAuth = new SmtpAuth(username, password);
			session = Session.getDefaultInstance(props, smtpAuth);
		} else {
			props.put("mail.smtp.auth", "false");
			session = Session.getDefaultInstance(props, null);
		}
		session.setDebug(false);//��̨����ӡ
		Transport trans = null;
	    Message msg = null;
		try {
			msg = new MimeMessage(session);
			Address from_address = null;
			try {
				displayName = MimeUtility
						.encodeText(displayName, "gb2312", "B");
				from_address = new InternetAddress(from, displayName);
				msg.setFrom(from_address);
			} catch (java.io.UnsupportedEncodingException e) {
				logger.error(e.getMessage());
			}
			// ����ռ���
			String[] toArray = to.split(";");
			InternetAddress[] toAdd = new InternetAddress[toArray.length];
			for (int i = 0; i < toArray.length; i++) {
				toAdd[i] = new InternetAddress(toArray[i]);
			}
			InternetAddress[] address = toAdd;
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setHeader("Disposition-Notification-To", from);
			try {
				subject = MimeUtility.encodeText(subject, "gb2312", "B");
			} catch (Exception e) {
				logger.error(e.getMessage());
				// TODO: handle exception
			}

			msg.setSubject(subject);
			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(content.toString(), "text/html;charset=GBK");
			mp.addBodyPart(mbp);
			if (!files.isEmpty()) {// �и���
				for (Mail file : files) {
					mbp = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(file.getFilepath()); // �õ�����Դ
					mbp.setDataHandler(new DataHandler(fds)); // �õ�������������BodyPart
					try {
						filename = MimeUtility.encodeText(file.getFilename(),
								"gb2312", "B");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					mbp.setFileName(filename); // �õ��ļ���ͬ������BodyPart
					mp.addBodyPart(mbp);
				}
			}
			msg.setContent(mp); // Multipart���뵽�ż�
			msg.setSentDate(new Date()); // �����ż�ͷ�ķ�������
			// �����ż�
			msg.saveChanges();
			trans = session.getTransport("smtp");
			trans.connect(smtpServer, username, password);
			trans.sendMessage(msg, msg.getAllRecipients());
			trans.close();

		} catch (AuthenticationFailedException e) {
			map.put("state", "failed");
			message = "�ʼ�����ʧ�ܣ�����ԭ��\n" + "�����֤����!";
			// e.printStackTrace();
			logger.error(e.getMessage());
		} catch (MessagingException e) {
			message = "�ʼ�����ʧ�ܣ�����ԭ��\n" + e.getMessage();
			map.put("state", "failed");
			e.printStackTrace();
			Exception ex = null;
			if ((ex = e.getNextException()) != null) {
				logger.error(e.getMessage());
			}
		}
		map.put("message", message);
		return map;
	}
}
