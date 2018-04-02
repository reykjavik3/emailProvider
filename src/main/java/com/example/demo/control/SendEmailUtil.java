package com.example.demo.control;
import java.util.Properties;  

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendEmailUtil {
	private final static String MailServer = "smtp.exmail.qq.com";
	private final static int MailPort = 465;
	private final static String MailUser = "licongrong@lushangyouwo.cn";
	private final static String MailPassword = "Zjl1314zjl";
	
	public static void sendMail(final String subject, final String[] toList, final String[] ccList,
			final String content) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
					senderImpl.setHost(MailServer);
					senderImpl.setPort(MailPort);
					MimeMessage mailMessage = senderImpl.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
					System.out.println("subject : " +  subject);
					messageHelper.setSubject(subject);
					messageHelper.setText(content, true);
					if (toList != null) {
						messageHelper.setTo(toList);
					}

					if (ccList != null) {
						messageHelper.setCc(ccList);
					}

					messageHelper.setFrom(MailUser);
					// 将回车符转换为 <br/>
//					messageHelper.setText(content.replaceAll("\n", "<br/>"), true);

					senderImpl.setUsername(MailUser);
					senderImpl.setPassword(MailPassword);
					Properties prop = new Properties();
					prop.put("mail.smtp.auth", "true");
					prop.put("mail.smtp.timeout", "25000");
					prop.put("mail.smtp.socketFactory.port", MailPort);
					prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
					senderImpl.setJavaMailProperties(prop);
					senderImpl.send(mailMessage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
