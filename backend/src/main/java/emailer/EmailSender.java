package emailer;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;


public class EmailSender {
	public void sendEmail(String to, String from, String subject, String body)
	{    

		// host right now is set to local houst
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(properties);

		try{
			
			MimeMessage message = new MimeMessage(session);

			// set who it is from 
			message.setFrom(new InternetAddress(from));

			// set who it is to 
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// sets the subject
			message.setSubject(subject);

			//body of the message
			message.setText(body);

			//sends the actual message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
