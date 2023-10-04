package com.example.demo.common.model;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.common.entity.User;
 
public class Session {
    public static User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
    public static void setUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute("user", user);
    }
 
    public static String getErrorMessage(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("errorMessage");
    }
 
    public static void setErrorMessage(HttpServletRequest request, String errorMessage) {
        request.getSession().setAttribute("errorMessage", errorMessage);
    }
}