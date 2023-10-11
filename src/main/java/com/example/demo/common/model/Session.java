package com.example.demo.common.model;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.common.entity.UserMst;
 
public class Session {
    public static UserMst getUser(HttpServletRequest request) {
        return (UserMst) request.getSession().getAttribute("user");
    }
    public static void setUser(HttpServletRequest request, UserMst user) {
        request.getSession().setAttribute("user", user);
    }
 
    public static String getErrorMessage(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("errorMessage");
    }
 
    public static void setErrorMessage(HttpServletRequest request, String errorMessage) {
        request.getSession().setAttribute("errorMessage", errorMessage);
    }
}