package com.example.demo.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.model.Session;
import com.example.demo.sample.dao.UserMstDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AccountListController {

	private final UserMstDao userMstDao;

	@GetMapping(path = "/sample/accountList")
	public String accountListPage(HttpServletRequest request, Model model) {
		if (Session.getUser(request).getAdminFlg() != 1) {
			Session.setErrorMessage(request, "管理者権限がありません");
			return "redirect:menu";
		}
		if (Session.getErrorMessage(request) != null) {
			model.addAttribute("errorMessage", Session.getErrorMessage(request));
			Session.setErrorMessage(request, null);
		}
		model.addAttribute("userList", userMstDao.selectAll());
		return "sample/accountList";
	}

	@PostMapping(path = "/createAccount")
	public String createAccount(HttpServletRequest request, Model model, @RequestParam("userId") String userId,
			@RequestParam("password") String password, @RequestParam("adminFlg") int adminFlg) {
		//TODO アカウント作成機能実装
		return "redirect:sample/accountList";
	}

	@PostMapping(path = "/goEditAccount")
	public String goEditAccount(RedirectAttributes redirectAttribute, Model model,
			@RequestParam("editUserId") String editUserId) {
		redirectAttribute.addAttribute("userId", editUserId);
		return "redirect:sample/editAccount";
	}

	//TODO deleteAccount用コントローラ作成……クライアント側からuser_idを受け取りuser_idをキーにuser_mstから削除する
}
