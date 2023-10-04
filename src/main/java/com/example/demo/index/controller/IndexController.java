package com.example.demo.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	String getHello(Model model) {
		model.addAttribute("message", "Hello World!!");
		return "index";
	}
}
