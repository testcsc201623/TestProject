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
public class EditAccountController {

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
		model.addAttribute("user", userMstDao.selectUser(userId).get(0));
		return "sample/editAccount";
	}

	@PostMapping(path = "/updateAccount")
	public String updateAccount(HttpServletRequest request, RedirectAttributes redirectAttribute,
			@RequestParam("userId") String userId, @RequestParam("userName") String userName,
			@RequestParam("adminFlg") int adminFlg)
			throws NoSuchAlgorithmException {
		// 編集しようとしているユーザがログインユーザでかつ管理者権限を一般権限に変更する場合はエラーを表示する
		if (Session.getUser(request).getUserId().equals(userId) && Session.getUser(request).getAdminFlg() == 1 && adminFlg == 0) {
			Session.setErrorMessage(request, "管理者権限をもつログインユーザID：" + userId + "の管理者権限を変更することはできません。");
			redirectAttribute.addAttribute("userId", userId);
			return "redirect:sample/editAccount";
		}
		if (userMstDao.updateUser(
				new UserMst(userId,
						userName,
						adminFlg,
						new Date())) == 1) {
			Session.setSuccessMessage(request, "ユーザID：" + userId + "の情報を更新しました。");
			Session.setErrorMessage(request, null);
			redirectAttribute.addAttribute("userId", userId);
			return "redirect:sample/editAccount";
		} else {
			redirectAttribute.addAttribute("userId", userId);
			Session.setErrorMessage(request, "ユーザID：" + userId + "の情報を更新できませんでした。");
			return "redirect:sample/editAccount";
		}
	}

	@PostMapping(path = "/resetPassWord")
	public String resetPassWord(HttpServletRequest request, RedirectAttributes redirectAttribute,
			@RequestParam("resetPasswordUserId") String resetPasswordUserId)
			throws NoSuchAlgorithmException {
		if (userMstDao.updatePassword(
				new UserMst(
						resetPasswordUserId,
						HashLogic.getHash(resetPasswordUserId),
						new Date())) == 1) {
			Session.setSuccessMessage(request, "ユーザID：" + resetPasswordUserId + "のパスワードを初期化しました。");
			Session.setErrorMessage(request, null);
			redirectAttribute.addAttribute("userId", resetPasswordUserId);
			return "redirect:sample/editAccount";
		} else {
			Session.setErrorMessage(request, "ユーザID：" + resetPasswordUserId + "のパスワードを初期化できませんでした。");
			redirectAttribute.addAttribute("userId", resetPasswordUserId);
			return "redirect:sample/editAccount";
		}
	}
}
