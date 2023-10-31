package com.example.demo.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.common.model.Session;
import com.example.demo.sample.dao.TitleTblDao;
import com.example.demo.sample.dao.UserMstDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class EditThreadController {

	private final UserMstDao userMstDao;
	private final TitleTblDao titleTblDao;

	@GetMapping(path = "/sample/editThread")
	public String editThreadPage(@RequestParam("titleId") int titleId, HttpServletRequest request, Model model) {
		if (Session.getErrorMessage(request) != null) {
			model.addAttribute("errorMessage", Session.getErrorMessage(request));
			Session.setErrorMessage(request, null);
		}
		if (Session.getSuccessMessage(request) != null) {
			model.addAttribute("successMessage", Session.getSuccessMessage(request));
			Session.setSuccessMessage(request, null);
		}
		model.addAttribute("title", titleTblDao.selectTitle(titleId).get(0));
		return "sample/editThread";
	}

//	@PostMapping(path = "/updateAccount")
//	public String updateAccount(HttpServletRequest request, RedirectAttributes redirectAttribute,
//			@RequestParam("userId") String userId, @RequestParam("userName") String userName,
//			@RequestParam("adminFlg") int adminFlg)
//			throws NoSuchAlgorithmException {
//		// 編集しようとしているユーザがログインユーザでかつ管理者権限を一般権限に変更する場合はエラーを表示する
//		if (Session.getUser(request).getUserId().equals(userId) && Session.getUser(request).getAdminFlg() == 1
//				&& adminFlg == 0) {
//			Session.setErrorMessage(request, "管理者権限をもつログインユーザID：" + userId + "の管理者権限を変更することはできません。");
//			redirectAttribute.addAttribute("userId", userId);
//			return "redirect:sample/editAccount";
//		}
//		if (userMstDao.updateUser(
//				new UserMst(userId,
//						userName,
//						adminFlg,
//						new Date())) == 1) {
//			// 変更したパスワードがログインユーザだった場合、セッション情報を再設定
//			if (userId.equals(Session.getUser(request).getUserId())) {
//				// 変更したアカウント情報をセッションに再設定
//				Session.setUser(request, userMstDao.selectUser(userId).get(0));
//			}
//			Session.setSuccessMessage(request, "ユーザID：" + userId + "の情報を更新しました。");
//			Session.setErrorMessage(request, null);
//			redirectAttribute.addAttribute("userId", userId);
//			return "redirect:sample/editAccount";
//		} else {
//			redirectAttribute.addAttribute("userId", userId);
//			Session.setErrorMessage(request, "ユーザID：" + userId + "の情報を更新できませんでした。");
//			return "redirect:sample/editAccount";
//		}
//	}
}
