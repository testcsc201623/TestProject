package com.example.demo.sample.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.common.Constant;
import com.example.demo.common.model.Session;
import com.example.demo.sample.dao.UserMstDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {
	
	private final UserMstDao userMstDao;
	
	@GetMapping(path = "/sample/login")
	public String loginPage(HttpServletRequest request, Model model){
        if(Session.getErrorMessage(request) != null) {
            model.addAttribute("errorMessage", Session.getErrorMessage(request));
            Session.setErrorMessage(request, null);
        }
		return "sample/login";
	}
	
  @PostMapping(path = "/login")
  public String login(HttpServletRequest request, Model model, @RequestParam("userId") String userId, @RequestParam("password") String password) throws NoSuchAlgorithmException{
      var resultUser = userMstDao.selectUser(userId);
      request.changeSessionId();
      if(resultUser.size() != 1 || !isValidPassword(resultUser.get(0).getPassword(),password)){
          Session.setErrorMessage(request, "ログインに失敗しました。");
          return "redirect:sample/login";
      }
      Session.setErrorMessage(request, null);
      Session.setUser(request, resultUser.get(0));
      return "redirect:sample/menu";
  }
	
	private boolean isValidPassword(String registeredPassword, String inputPassword) throws NoSuchAlgorithmException{
        var inputPasswordByteArray = MessageDigest.getInstance("SHA-256").digest((inputPassword + Constant.SALT).getBytes());
        var inputPasswordHash = String.format("%040x", new BigInteger(1, inputPasswordByteArray));
        return registeredPassword.equals(inputPasswordHash);
    }
}