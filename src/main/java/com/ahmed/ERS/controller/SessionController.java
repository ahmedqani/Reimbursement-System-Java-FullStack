package com.ahmed.ERS.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionController {

	public static void getSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		HttpSession session = req.getSession();
		
		ObjectMapper mapper = new ObjectMapper();
		
		ObjectNode sesInfo = mapper.createObjectNode();
		
		if(session.getAttribute("id") == null) {
			res.setStatus(404);
			res.getWriter().println("User is not logged in");
			return;
		}
		
		sesInfo.put("userId", session.getAttribute("id").toString());
		
		res.getWriter().write((new ObjectMapper().writeValueAsString(sesInfo)));
		
	}
	
}
