package com.ahmed.ERS.servlets;

import com.ahmed.ERS.exceptions.InvalidCredentialsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MasterServlet", urlPatterns = "/api/*")
public class MasterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MasterServlet Was called > Get Method");
		try {
			Dispatcher.process(req, res);
		} catch (InvalidCredentialsException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MasterServlet Was called > POST Method");
		try {
			Dispatcher.process(req, res);
		} catch (InvalidCredentialsException e) {
			e.printStackTrace();
		}
	}

}
