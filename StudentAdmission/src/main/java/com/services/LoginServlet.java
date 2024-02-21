package com.services;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        PrintWriter out = res.getWriter();
        
        if (email != null && password != null) {
            if (email.equalsIgnoreCase("lutwawilliam@gmail.com") && password.equals("suisbeau")) {
                RequestDispatcher rd = req.getRequestDispatcher("/Admission.html");
                rd.forward(req, res);
            } else {
                res.setContentType("text/html");
                out.println("Your username or password is incorrect");
                RequestDispatcher rd1 = req.getRequestDispatcher("/LogIn.html");
                rd1.include(req, res);
            }
        }
    }
}
