package com.services;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.ejb.Handle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.jasper.runtime.ProtectedFunctionMapper;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
////import jakarta.websocket.Session;

public class EmailServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String receiverEmail = req.getParameter("receiverEmail");
		String confirmCode = req.getParameter("confirmCode");
		
		if(receiverEmail.isEmpty() || confirmCode.isEmpty()) {
			res.getWriter().write("Email and confirmation code are required");
			return;
		}
		try {
			
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			
			final String senderEmail = "lutwawilliam@gmail.com";
			final String senderPassword = "1974 suis beau";
			
			javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
				@Override
                public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(senderEmail, senderPassword);
                }
			});
			
			Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
            message.setSubject("Subject of the Email"); // Replace with your subject
            message.setText("Whoooo ! You're Admitted Successfully. Your Confirmation Code is " + confirmCode); // Replace with your message

            // Send the message
            Transport.send(message);
            
            // Handle successful email sending (e.g., display a success message)
            res.getWriter().write("Email sent successfully to the Student.");
			
			
			
		}catch(MessagingException e) {
			e.printStackTrace();
        // Handle email sending failure (e.g., log the error)
            res.getWriter().write("Failed to send email: " + e.getMessage());
		}
	}

}
