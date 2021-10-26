package com.ahmed.ERS.servlets;

import com.ahmed.ERS.controller.LoginController;
import com.ahmed.ERS.controller.ReimbursementController;
import com.ahmed.ERS.controller.SessionController;

import com.ahmed.ERS.controller.UserController;
import com.ahmed.ERS.exceptions.InvalidCredentialsException;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {

    public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, InvalidCredentialsException {
        System.out.println("In the Servlet Dispatcher with URI: " + req.getRequestURI());
        switch (req.getRequestURI()) {
            case "/api/login":
                LoginController.login(req, res);
                break;
            case "/api/signup":
                LoginController.signUp(req, res);
                break;
            case "/api/updateuser":
                UserController.updateUser(req, res);
                break;
            case "/api/finduser":
                UserController.findUser(req, res);
                break;
            case "/api/findAllReimbursements":
                ReimbursementController.findAllReimbursements(req, res);
                break;
            case "/api/findReimbursementById":
                ReimbursementController.findReimbursementById(req, res);
                break;
            case "/api/saveReimbursement":
                ReimbursementController.saveReimbursement(req, res);
                break;
            case "/api/updateReimbursement":
                ReimbursementController.updateReimbursement(req, res);
                break;
            case "/api/getReimbursementByUserid":
                ReimbursementController.getReimbursementByUserid(req, res);
                break;
            case "/api/session":
                SessionController.getSession(req, res);
        }
    }

}
