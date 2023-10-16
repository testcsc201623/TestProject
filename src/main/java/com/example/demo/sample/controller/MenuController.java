package com.example.demo.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.model.Session;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MenuController {

	@GetMapping(path = "/sample/menu")
	public String menuPage(HttpServletRequest request, Model model) {
		if (Session.getErrorMessage(request) != null) {
			model.addAttribute("errorMessage", Session.getErrorMessage(request));
			Session.setErrorMessage(request, null);
		}
		model.addAttribute("user", Session.getUser(request));
		return "sample/menu";
	}

	@PostMapping(path = "/goAccountList")
	public String goAccountList(HttpServletRequest request) {
		Session.setErrorMessage(request, null);
		return "redirect:sample/accountList";
	}
	
	@PostMapping(path = "/goPersonalSetting")
	public String goPersonalSetting(HttpServletRequest request) {
		Session.setErrorMessage(request, null);
		return "redirect:sample/personalSetting";
	}
}