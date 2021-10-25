package com.ahmed.ERS.controller;

import com.ahmed.ERS.exceptions.InvalidCredentialsException;
import com.ahmed.ERS.model.User;
import com.ahmed.ERS.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class UserController {

    private static UserService uServ = new UserService();

    public static void updateUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, InvalidCredentialsException {
        System.out.println("In UpdateUser Method!!");
        //To read in stringified JSON data from a POST request is a little more complicated than reading form data
        StringBuilder buffer = new StringBuilder();

        //The buffered reader will read the json data line by line
        BufferedReader reader = req.getReader();

        String line;
        while((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }

        String data = buffer.toString();
        System.out.println(data);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode parsedObj = mapper.readTree(data);

        Long user_id = parsedObj.get("user_id").asLong();
        String username = parsedObj.get("username").asText();
        String password = parsedObj.get("password").asText();
        String firstName = parsedObj.get("firstname").asText();
        String lastName = parsedObj.get("lastname").asText();
        String email = parsedObj.get("email").asText();
        String userRole = parsedObj.get("userRole").asText();
        User user = new User(username,password,firstName,lastName,email);
        user.setId(user_id);
        user.setUserRole(userRole);

        try {
            System.out.println("In the user Update handler");
            User u = uServ.updateUser(user);
            System.out.println(u);
            res.setStatus(200);
            res.getWriter().write(new ObjectMapper().writeValueAsString(u));
        } catch(Exception e) {
            res.setStatus(403);
            res.getWriter().println("Couldn't Update User!!");
        }

    }

    public static void findUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, InvalidCredentialsException {
        System.out.println("In FindUser Method!!");
        //To read in stringified JSON data from a POST request is a little more complicated than reading form data
        StringBuilder buffer = new StringBuilder();

        //The buffered reader will read the json data line by line
        BufferedReader reader = req.getReader();

        String line;
        while((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }

        String data = buffer.toString();
        System.out.println(data);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode parsedObj = mapper.readTree(data);

        String username = parsedObj.get("username").asText();


        try {
            System.out.println("In the user Find handler");
            User u = uServ.getUserByName(username);
            System.out.println(u);
            res.setStatus(200);
            res.getWriter().write(new ObjectMapper().writeValueAsString(u));
        } catch(Exception e) {
            res.setStatus(403);
            res.getWriter().println("Couldn't Find User!!");
        }

    }

}
