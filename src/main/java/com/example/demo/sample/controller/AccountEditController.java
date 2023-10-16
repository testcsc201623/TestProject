package com.example.demo.sample.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.common.entity.UserMst;
import com.example.demo.common.logic.HashLogic;
import com.example.demo.common.model.Session;
import com.example.demo.sample.dao.UserMstDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AccountEditController {

	private final UserMstDao userMstDao;

	@GetMapping(path = "/sample/editAccount")
	public String accountEditPage(@RequestParam("userId") String userId, HttpServletRequest request, Model model) {
		if (Session.getUser(request).getAdminFlg() != 1) {
			Session.setErrorMessage(request, "管理者権限がありません");
			return "redirect:menu";
		}
		if (Session.getErrorMessage(request) != null) {
			model.addAttribute("errorMessage", Session.getErrorMessage(request));
			Session.setErrorMessage(request, null);
		}
		if (Session.getSuccessMessage(request) != null) {
			model.addAttribute("successMessage", Session.getSuccessMessage(request));
			Session.setSuccessMessage(request, null);
		}
		model.addAttribute("user",userMstDao.selectUser(userId).get(0));
		return "sample/editAccount";
	}
	
	@PostMapping(path = "/resetPassWord")
	public String resetPassWord(HttpServletRequest request, Model model, @RequestParam("resetPasswordUserId") String resetPasswordUserId)
			throws NoSuchAlgorithmException {
		var now = new Date();
		var updateUsetMst = new UserMst(resetPasswordUserId, HashLogic.getHash(resetPasswordUserId), now);
		if (userMstDao.updatePassword(updateUsetMst) == 1) {
			Session.setSuccessMessage(request, "ユーザID：" + resetPasswordUserId + "のパスワードを初期化しました。");
			Session.setErrorMessage(request, null);
			return "redirect:sample/accountList";
		} else {
			Session.setErrorMessage(request, "ユーザID：" + resetPasswordUserId + "のパスワードを初期化できませんでした。");
			return "redirect:sample/accountList";
		}
	}
}

