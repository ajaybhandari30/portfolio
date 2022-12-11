package com.ajay.Email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;   
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;   
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email 
{
	private static String port 		= "";
	private static String mailhost 	= "";
	private String username;   
    private String password;
	
	public Email() 
	{
		port 		= "465";
		mailhost 	= "smtp.gmail.com";
		username	= "ajaybhandari1995.ab@gmail.com";
		password	= "emznvkghiqaykprl";
	}
	
	public boolean mailSender(String fromAdd, String toAdd, String subject, String reply, String cc, String matter) 
    {   
		boolean isEmailSent = false;
		
		try 
		{
			System.out.println("Inside mailSender >> Form address :: "+fromAdd+"\n To address ::"+toAdd+"\n subject ::"+subject+"\n reply ::"+reply+"\n cc ::"+cc);
			System.out.println("Inside mailSender >> matter :: "+matter);

			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", mailhost); 
			properties.put("mail.smtp.port", port);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.socketFactory.port", port);
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   

			Session session = Session.getInstance(properties,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });
			
			
			 MimeMessage mimeMessage = new MimeMessage(session);
			 
			 InternetAddress[] toAddress, ccAddress; 
			 mimeMessage.setFrom(new InternetAddress(fromAdd));
			 
			 toAddress = InternetAddress.parse(toAdd);
			 
			 if(reply != null && !"".equals(reply)) 
				 mimeMessage.setReplyTo(new InternetAddress[]{new InternetAddress(reply)});
			 
			 mimeMessage.setRecipients(Message.RecipientType.TO, toAddress);
			 
			 if(cc != null && !"".equals(cc)) 
			 { 
				 ccAddress = InternetAddress.parse(cc);
				 mimeMessage.setRecipients(Message.RecipientType.CC, ccAddress); 
			}
			 
			 mimeMessage.setSubject(subject); 
			 mimeMessage.setContent(matter, "text/html");
			 mimeMessage.saveChanges(); 
			 
			 System.out.println("Before sending mail....");
			 
			 Transport transport = session.getTransport("smtps");
			 transport.connect(mailhost, Integer.valueOf(port), username, password);
			 transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			 transport.close();
			 
			isEmailSent = true;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception :: "+e);
			e.printStackTrace();
		} 
		
		return isEmailSent;
    }
	
	protected PasswordAuthentication getPasswordAuthentication() 
    {   
        return new PasswordAuthentication(username, password);   
    } 
}
