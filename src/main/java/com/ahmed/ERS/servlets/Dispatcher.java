package com.ahmed.ERS.servlets;

import com.ahmed.ERS.controller.LoginController;
import com.ahmed.ERS.controller.SessionController;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("In the Servlet Dispatcher with URI: " + req.getRequestURI());
		switch(req.getRequestURI()) {
			case "/api/login":
				LoginController.login(req, res);
				break;
			case "/api/signup":
				LoginController.signUp(req, res);
				break;
			case "/api/session":
				SessionController.getSession(req, res);
		}
	}
	
}
