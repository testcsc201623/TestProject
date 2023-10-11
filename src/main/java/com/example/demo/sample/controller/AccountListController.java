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
public class AccountListController {
	
	private final UserMstDao userMstDao;
	
	@GetMapping(path = "/sample/accountList")
	public String accountListPage(HttpServletRequest request, Model model){
        if(Session.getErrorMessage(request) != null) {
            model.addAttribute("errorMessage", Session.getErrorMessage(request));
            Session.setErrorMessage(request, null);
        }
        model.addAttribute("userList", userMstDao.selectAll());
		return "sample/accountList";
	}
	
}