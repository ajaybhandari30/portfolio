package com.ajay.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajay.Email.Email;

@WebServlet("/emailServlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Email email = new Email();
        
        String subject			= "New email from Portfolio";
        String emailId			= "ajaybhandari1995.ab@gmail.com";
        String name 			= request.getParameter("name");
        String fromEmailId 		= request.getParameter("email");
        String emailSubject 	= request.getParameter("subject");
        String message 			= request.getParameter("message");

        System.out.println("Name :: "+name);
        System.out.println("fromEmailId :: "+fromEmailId);
        System.out.println("emailSubject :: "+emailSubject);
        System.out.println("message :: "+message);
        
        String matter = "<!DOCTYPE html>\r\n"
        		+ "<html>\r\n"
        		+ "	<head>\r\n"
        		+ "		<meta charset=\"UTF-8\">\r\n"
        		+ "		<title>Email</title>\r\n"
        		+ "	</head>\r\n"
        		+ "	<body>\r\n"
        		+ "	\r\n"
        		+ "	<div class=\"col-lg-8 pt-4 pt-lg-0 content\">\r\n"
        		+ "	\r\n"
        		+ "		<h3>Portfolio, You have received email from the Visitor </h3>\r\n"
        		+ "		<p>\r\n"
        		+ "			<strong>Subject of mail :-</strong> ##SUBJECT\r\n"
        		+ "		</p>\r\n"
        		+ "		<p>\r\n"
        		+ "			<span>\r\n"
        		+ "				<strong>--Details of Visitor--</strong>\r\n"
        		+ "			</span><br>\r\n"
        		+ "		</p>\r\n"
        		+ "		<p style=\"margin-left: 2%;\">\r\n"
        		+ "			<strong>Name :-</strong> ##USERNAME\r\n"
        		+ "		</p>\r\n"
        		+ "		<p style=\"margin-left: 2%;\">\r\n"
        		+ "			<strong>Email ID :-</strong> ##EMAIL\r\n"
        		+ "		</p>\r\n"
        		+ "		<p style=\"margin-left: 2%;\">\r\n"
        		+ "			<strong> Message from Visitor</strong>:- ##MESSAGE\r\n"
        		+ "		</p>\r\n"
        		+ "	</div>\r\n"
        		+ "	\r\n"
        		+ "	</body>\r\n"
        		+ "</html>";

        matter = matter.replaceAll("##USERNAME", name);
        matter = matter.replaceAll("##SUBJECT", emailSubject);
        matter = matter.replaceAll("##EMAIL", fromEmailId);
        matter = matter.replaceAll("##MESSAGE", message);
        
        boolean isEmailSent = email.mailSender(emailId, emailId, subject, "", "", matter.toString());
        System.out.println("isEmailSent :: "+isEmailSent);
        
        request.getRequestDispatcher("/thankyou.html").forward(request, response);
    }

}
