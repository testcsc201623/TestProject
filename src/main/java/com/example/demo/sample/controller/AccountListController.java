package com.example.demo.sample.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.entity.UserMst;
import com.example.demo.common.logic.HashLogic;
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
		if (Session.getSuccessMessage(request) != null) {
			model.addAttribute("successMessage", Session.getSuccessMessage(request));
			Session.setSuccessMessage(request, null);
		}
		model.addAttribute("userList", userMstDao.selectAll());
		return "sample/accountList";
	}

	@PostMapping(path = "/createAccount")
	public String createAccount(HttpServletRequest request, @RequestParam("userId") String userId,
			@RequestParam("userName") String userName, @RequestParam("adminFlg") int adminFlg)
			throws NoSuchAlgorithmException {
		if (userMstDao.selectUser(userId).size() == 1) {
			Session.setErrorMessage(request, "同一のユーザIDが存在します。ユーザIDは一意の値にしてください。");
			return "redirect:sample/accountList";
		}
		var now = new Date();
		var createUsetMst = new UserMst(userId, HashLogic.getHash(userId), userName, adminFlg, now, now);
		if (userMstDao.createUser(createUsetMst) == 1) {
			Session.setSuccessMessage(request, "ユーザID：" + userId + "を作成しました。");
			Session.setErrorMessage(request, null);
			return "redirect:sample/accountList";
		} else {
			Session.setErrorMessage(request, "ユーザID：" + userId + "を作成できませんでした。");
			return "redirect:sample/accountList";
		}

	}

	@PostMapping(path = "/goEditAccount")
	public String goEditAccount(HttpServletRequest request, RedirectAttributes redirectAttribute,
			@RequestParam("editUserId") String editUserId) {
		redirectAttribute.addAttribute("userId", editUserId);
		Session.setErrorMessage(request, null);
		return "redirect:sample/editAccount";
	}

	@PostMapping(path = "/deleteAccount")
	public String deleteAccount(HttpServletRequest request,
			@RequestParam("deleteUserId") String deleteUserId) {
		if (Session.getUser(request).getUserId().equals(deleteUserId)) {
			Session.setErrorMessage(request, "現在ログイン中のユーザと同一ユーザです。削除できません。");
			return "redirect:sample/accountList";
		}
		var deleteUserMst = new UserMst();
		deleteUserMst.setUserId(deleteUserId);
		if (userMstDao.deleteUser(deleteUserMst) == 1) {
			Session.setSuccessMessage(request, "ユーザID：" + deleteUserId + "を削除しました。");
			Session.setErrorMessage(request, null);
			return "redirect:sample/accountList";
		} else {
			Session.setErrorMessage(request, "ユーザID：" + deleteUserId + "を削除できませんでした。");
			return "redirect:sample/accountList";
		}
	}
}
