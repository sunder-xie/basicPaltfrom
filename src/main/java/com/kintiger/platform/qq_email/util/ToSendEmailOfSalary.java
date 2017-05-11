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

import com.kintiger.platform.qq_email.util.pojo.Salary;

import java.util.List;

public class ToSendEmailOfSalary {
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
		private static String from = "xppfa@chinaxpp.com";	
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

	public static void main(String[] args) throws Exception {
		Address[] address = new Address[2];
		// 发送简单的html邮件
		SendEmailForSalary(null);
	}

	/**
	 * 发送简单的html邮件
	 */
	public static void SendEmailForSalary(Salary salary)
			throws Exception {
		
			// 创建Session实例对象
			Session session = Session.getInstance(props, new MyAuthenticator());
			// 创建MimeMessage实例对象
			MimeMessage message = new MimeMessage(session);
			// 设置邮件主题
			message.setSubject("工资条发送");
			// 设置发送人
			message.setFrom(new InternetAddress(from));
			// 设置发送时间
			message.setSentDate(new Date());
			// 设置收件人
			message.setRecipients(RecipientType.TO, InternetAddress.parse(salary.getMail())); 
			// 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为gbk
			message.setContent("<table width='500' height='300' border='1' cellspacing='0'>"+
					"<tr><td>姓名</td><td>"+salary.getName()+"</td><td>发送月份</td><td>"+salary.getSendMonth()+"</td><td>实际出勤天数</td><td>"+salary.getRealworkingdays()+"</td></tr>"+
					"<tr><td colspan = '2' align=center style='background:Lavender '>工资</td>"+
					"<td colspan = '2' align=center style='background:Lavender'>补贴</td>"+
					"<td colspan = '2' align=center style='background:Lavender '>应减项目</td> </tr>"+
					"<tr><td>资本薪资</td><td width=70>"+salary.getBasicsalary()+"</td><td>电话费补助</td><td>"+salary.getTelephonefeesubsidy()+"</td><td>养老保险</td><td width=70>"+salary.getOldageinsurance()+"</td> </tr>"+    
					"<tr><td>加班工资</td><td>"+salary.getOvertimepay()+"</td><td>全勤奖金</td><td>"+salary.getAttendancebonus()+"</td><td>失业金</td><td>"+salary.getUnemploymentgold()+"</td></tr>"+
					"<tr><td>职务加给</td><td>"+salary.getPositionplus()+"</td><td rowspan = '2'>工龄奖</td><td rowspan = '2'>"+salary.getServiceAward()+"</td><td>医疗金</td><td>"+salary.getMedicalgold()+"</td></tr>"+
					"<tr><td>岗位津贴</td><td>"+salary.getPostallowance()+"</td><td>住房公积金</td><td>"+salary.getHousingProvidentFund()+"</td></tr>"+
					"<tr><td rowspan = '2'>绩效工资</td><td rowspan = '2'>"+salary.getMeritpay()+"</td><td rowspan = '2'>高温补贴</td><td rowspan = '2'>"+salary.getHotSubsidy()+"</td><td>个税个人</td><td>"+salary.getIndividualincometax()+"</td></tr>"+
					"<tr><td>房屋水电</td><td>"+salary.getRentandwater()+"</td></tr>"+
					"<tr><td rowspan = '2'>工资补发</td><td rowspan = '2'>"+salary.getWagereplacement()+"</td><td rowspan = '2'>其他</td><td rowspan = '2'>"+salary.getOthers()+"</td><td>费用扣除</td><td>"+salary.getExpensededuction()+"</td></tr>"+
					"<tr><td>工会费</td><td>"+salary.getTradeunionfee()+"</td></tr>"+
					"<tr><td style='background:Lavender '>工资合计</td><td>"+salary.getTotalwages()+"</td><td style='background:Lavender '>补贴合计</td><td>"+salary.getTotalsubsidy()+"</td><td style='background:Lavender '>应减合计</td><td>"+salary.getTotalreduction()+"</td></tr>"+
					"<tr><td style='background:Lavender '>应发工资</td><td colspan = '2'>"+salary.getShouldpay()+"</td><td style='background:Lavender '>实发工资</td><td colspan = '2'>"+salary.getRealwages()+"</td></tr>"
					+ "</table></br>&nbsp;&nbsp;&nbsp;"+salary.getGreeting(),"text/html;charset=gbk");
			// 保存并生成最终的邮件内容
			message.saveChanges();

			// 发送邮件
			Transport.send(message);
		
	}

	static class MyAuthenticator extends Authenticator {
		
		private String username = "xppfa@chinaxpp.com";

		private String password = "52xpp!123";

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
