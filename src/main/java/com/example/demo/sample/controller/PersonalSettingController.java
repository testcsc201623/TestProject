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
public class PersonalSettingController {

	private final UserMstDao userMstDao;

	@GetMapping(path = "/sample/personalSetting")
	public String personalSettingPage(HttpServletRequest request, Model model) {
		if (Session.getErrorMessage(request) != null) {
			model.addAttribute("errorMessage", Session.getErrorMessage(request));
			Session.setErrorMessage(request, null);
		}
		if (Session.getSuccessMessage(request) != null) {
			model.addAttribute("successMessage", Session.getSuccessMessage(request));
			Session.setSuccessMessage(request, null);
		}
		model.addAttribute("user", userMstDao.selectUser(Session.getUser(request).getUserId()).get(0));
		return "sample/personalSetting";
	}

	@PostMapping(path = "/updateName")
	public String updateName(HttpServletRequest request, RedirectAttributes redirectAttribute,
			@RequestParam("userName") String userName)
			throws NoSuchAlgorithmException {
		var userId = Session.getUser(request).getUserId();
		if (userMstDao.updateUserName(new UserMst().setUpdateUserName(userId, userName, new Date())) == 1) {
			// 変更したアカウント情報をセッションに再設定
			Session.setUser(request, userMstDao.selectUser(userId).get(0));
			Session.setSuccessMessage(request, "ユーザID：" + userId + "の情報を更新しました。");
			Session.setErrorMessage(request, null);
			return "redirect:sample/personalSetting";
		} else {
			Session.setErrorMessage(request, "ユーザID：" + userId + "の情報を更新できませんでした。");
			return "redirect:sample/personalSetting";
		}
	}

	@PostMapping(path = "/updatePassword")
	public String updatePassword(HttpServletRequest request, RedirectAttributes redirectAttribute,
			@RequestParam("hiddenOldPassword") String hiddenOldPassword,
			@RequestParam("hiddenNewPassword") String hiddenNewPassword,
			@RequestParam("hiddenNewPassword2") String hiddenNewPassword2)
			throws NoSuchAlgorithmException {
		var userId = Session.getUser(request).getUserId();
		if (!HashLogic.getHash(hiddenOldPassword)
				.equals(userMstDao.selectUser(Session.getUser(request).getUserId()).get(0).getPassword())) {
			Session.setErrorMessage(request, "現在のパスワードに誤りがあります。");
			return "redirect:sample/personalSetting";
		}
		if (hiddenNewPassword.isBlank() || hiddenNewPassword.length() < 4) {
			Session.setErrorMessage(request, "新しいパスワードは4文字以上入力してください。");
			return "redirect:sample/personalSetting";
		}
		if (!hiddenNewPassword.equals(hiddenNewPassword2)) {
			Session.setErrorMessage(request, "再入力したパスワードが異なります。");
			return "redirect:sample/personalSetting";
		}
		if (userMstDao.updatePassword(
				new UserMst(
						userId,
						HashLogic.getHash(hiddenNewPassword),
						new Date())) == 1) {
			// 変更したアカウント情報をセッションに再設定
			Session.setUser(request, userMstDao.selectUser(userId).get(0));
			Session.setSuccessMessage(request, "パスワードを変更しました。");
			Session.setErrorMessage(request, null);
			return "redirect:sample/personalSetting";
		} else {
			Session.setErrorMessage(request, "パスワードを変更できませんでした。");
			return "redirect:sample/personalSetting";
		}
	}
}
