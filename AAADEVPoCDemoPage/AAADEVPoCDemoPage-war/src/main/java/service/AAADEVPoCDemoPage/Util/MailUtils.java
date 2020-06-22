package service.AAADEVPoCDemoPage.Util;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtils {

	String from;
	String[] to;
	String text;
	String subject;
	File file;
	
	// Get system properties
	Properties properties;
	
	
	
	public MailUtils(String from, String[] to, String text, String subject, File file) {
		
		this.from = from;
		this.to = to;
		this.text = text;
		this.subject = subject;
		this.file = file;
		
		// Assuming you are sending email from through gmails smtps
		String host = "smtp.gmail.com";
		// Setup mail server
		properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.trust", "*");
	}

	public void Send() throws MessagingException {
		System.out.println("Inicio de SEND()");

		
		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(Constants.SMTP_USER, Constants.SMTP_PASS);

			}

		});

		// Used to debug SMTP issues

		session.setDebug(true);

		

			// Create a default MimeMessage object.

			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.

			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.

			for (String recipient : to) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			}
			// Set Subject: header field

			message.setSubject(this.subject);
			
			// Create a multipar message
			 Multipart multipart = new MimeMultipart();
			 
			// Create the message part
			 BodyPart messageBodyPart = new MimeBodyPart();
			
			 // Now set the actual message
			 messageBodyPart.setContent(this.text,"text/html");
			
			 // Set text message part
			 multipart.addBodyPart(messageBodyPart);
			
			 
			 if(file != null){ //Adjuntamos el archivo solo si existe en la instancia
				// Part two is attachment
				 messageBodyPart = new MimeBodyPart();
			 
				 String filename = file.getName();
				 
				 DataSource source = new FileDataSource(file);
				 messageBodyPart.setDataHandler(new DataHandler(source));
				 messageBodyPart.setFileName(filename);
				 multipart.addBodyPart(messageBodyPart);
			 }

			// message.setText();

			System.out.println("sending...");

			// Send message

			message.setContent(multipart);
			
			Transport.send(message);

			System.out.println("Sent message successfully....");

	}
}
