package com.alfarabi.chessmaster.tools;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class MailUtil{
	
	public static Session createSessionObject() {
	    Properties properties = new Properties();
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	 
	    return Session.getInstance(properties, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(Constant.SENDER_EMAIL, Constant.SENDER_EMAIL_PASSWORD);
	        }
	    });
	}
	
}