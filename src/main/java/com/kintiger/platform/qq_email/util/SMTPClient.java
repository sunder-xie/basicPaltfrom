package com.kintiger.platform.qq_email.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * 通过socket向smtp协议服务器发送邮件
 * 
 * @author fuyanqing
 * 
 */
public class SMTPClient {
	String mailServer;
	String from;
	String to;
	String content;
	String lineFeet = "\r\n";
	private int port = 25;

	Socket client;
	BufferedReader in;
	DataOutputStream os;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMailServer() {
		return mailServer;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * 初始化连接
	 * 
	 * @return
	 */
	private boolean init() {
		boolean boo = true;
		if (mailServer == null || "".equals(mailServer)) {
			return false;
		}
		try {
			client = new Socket(mailServer, port);
			in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			os = new DataOutputStream(client.getOutputStream());
			String isConnect = response();
			if (isConnect.startsWith("220")) {

			} else {
				System.out.println("建立连接失败：" + isConnect);
				boo = false;
			}
		} catch (UnknownHostException e) {
			System.out.println("建立连接失败！");
			e.printStackTrace();
			boo = false;
		} catch (IOException e) {
			System.out.println("读取流失败！");
			e.printStackTrace();
			boo = false;
		}
		return boo;
	}

	/**
	 * 发送smtp指令 并返回服务器响应信息
	 */
	private String sendCommand(String msg) {
		String result = null;
		try {
			os.writeBytes(msg);
			os.flush();
			result = response();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取服务器端响应信息
	 * 
	 * @return
	 */
	private String response() {
		String result = null;
		try {
			result = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 关闭
	 */
	private void close() {
		try {
			os.close();
			in.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 发送邮件
	 * 
	 * @return
	 */
	public boolean sendMail() {
		// 初始化
		if (client == null) {
			if (init()) {

			} else {
				return false;
			}
		}
		// 判断from,to
		if (from == null || from.isEmpty() || to == null || to.isEmpty()) {
			return false;
		}
		// 进行握手
		String result = sendCommand("HELO " + mailServer + lineFeet);
		if (isStartWith(result, "250")) {
		} else {
			System.out.println("握手失败：" + result);
			return false;
		}
		// 验证发信人信息
		String auth = sendCommand("AUTH LOGIN" + lineFeet);
		if (isStartWith(auth, "334")) {
		} else {
			return false;
		}
		String user = sendCommand(new String(Base64.encode("472523576@qq.com"
				.getBytes())) + lineFeet);
		if (isStartWith(user, "334")) {
		} else {
			return false;
		}
		String pass = sendCommand(new String(Base64.encode("dada..11"
				.getBytes())) + lineFeet);
		if (isStartWith(pass, "235")) {
		} else {
			return false;
		}

		// 发送指令
		String f = sendCommand("Mail From:<" + from + ">" + lineFeet);
		if (isStartWith(f, "250")) {
		} else {
			return false;
		}
		String toStr = sendCommand("RCPT TO:<" + to + ">" + lineFeet);
		if (isStartWith(toStr, "250")) {
		} else {
			return false;
		}

		String data = sendCommand("DATA" + lineFeet);
		if (isStartWith(data, "354")) {
		} else {
			return false;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("From:<" + from + ">" + lineFeet);
		sb.append("To:<" + to + ">" + lineFeet);
		sb.append("Subject:test" + lineFeet);
		sb.append("Date:2010/10/27 17:30" + lineFeet);
		sb.append("Content-Type:text/plain;charset=\"GB2312\"" + lineFeet);
		sb.append(lineFeet);
		sb.append(content);
		sb.append(lineFeet + "." + lineFeet);

		String conStr = sendCommand(sb.toString());
		if (isStartWith(conStr, "250")) {
		} else {
			return false;
		}

		// quit
		String quit = sendCommand("QUIT" + lineFeet);
		if (isStartWith(quit, "221")) {
		} else {
			return false;
		}
		close();
		return true;
	}

	private boolean isStartWith(String res, String with) {
		return res.startsWith(with);
	}

	public static void main(String[] args) {
		SMTPClient mail = new SMTPClient();
		mail.setMailServer("smtp.163.com");
		mail.setFrom("472523576@qq.com");
		mail.setTo("y472523576@163.com");
		mail.setContent("hello,this is a test mail!");
		boolean boo = mail.sendMail();
		if (boo)
			System.out.println("邮件发送成功！");
		else {
			System.out.println("邮件发送失败！");
		}
	}

	public static int Send_Email(String From_Email, String To_Email,
			String subject) {
		SMTPClient mail = new SMTPClient();
		mail.setMailServer("smtp.exmail.qq.com");
		mail.setFrom(From_Email);
		mail.setTo(To_Email);
		mail.setContent(subject);
		boolean boo = mail.sendMail();
		if (boo)
			System.out.println("邮件发送成功！");
		else {
			System.out.println("邮件发送失败！");
		}
		return 0;
	}
}
