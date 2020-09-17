package com.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	private static final String SERNDER_MAIL = "iiicoral012@gmail.com";
	private static final String SERNDER_PW = "qoo9924017s";

	public void sendMail(String to, String subject, String messageText) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SERNDER_MAIL, SERNDER_PW);
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SERNDER_MAIL));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(messageText);
			Transport.send(message);
			System.out.println("send mail success!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		String to = "iiicoral012@gmail.com";
		String subject = "Hello first mail!";
		MailUtil mailUtil = new MailUtil();
		mailUtil.sendMail(to, subject, "Hello world! " + StringUtil.genRamdomStr(6));
	}

}
