package com.example.demo.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.common.model.Session;
import com.example.demo.sample.dao.UserMstDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SuccessController {
	
	private final UserMstDao userDao;
	
	@GetMapping(path = "/sample/success")
	public String successPage(HttpServletRequest request, Model model){
        if(Session.getErrorMessage(request) != null) {
            model.addAttribute("errorMessage", Session.getErrorMessage(request));
            Session.setErrorMessage(request, null);
        }
		return "sample/success";
	}
	
}