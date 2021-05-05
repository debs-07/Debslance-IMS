package com.example.demo;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	public String sendTextEmail() throws IOException {
		// the sender email should be the same as we used to Create a Single Sender Verification
		    Email from = new Email("");
		    String subject = "The subject";
		    Email to = new Email("");
		    Content content = new Content("text/plain", "This is a test email");
		    Mail mail = new Mail(from, subject, to, content);
		
		    SendGrid sg = new SendGrid("");
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      logger.info(response.getBody());
		      return response.getBody();	     
		    } catch (IOException ex) {
		      throw ex;
		    }	   
	}
}

