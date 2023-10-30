package com.example.demo.sample.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.example.demo.common.entity.LastTitleViewedTbl;
import com.example.demo.common.entity.ThreadTbl;
import com.example.demo.common.entity.TitleTbl;
import com.example.demo.common.model.Message;
import com.example.demo.common.model.Session;
import com.example.demo.sample.dao.LastTitleViewedTblDao;
import com.example.demo.sample.dao.ThreadTblDao;
import com.example.demo.sample.dao.TitleTblDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TalkController {

	private final TitleTblDao titleTblDao;
	private final ThreadTblDao threadTblDao;
	private final LastTitleViewedTblDao lastTitleViewedTblDao;

	@GetMapping(path = "/sample/talk")
	public String talkPage(HttpServletRequest request, Model model) {
		if (Session.getErrorMessage(request) != null) {
			model.addAttribute("errorMessage", Session.getErrorMessage(request));
			Session.setErrorMessage(request, null);
		}
		// 閲覧権限があるタイトル一覧をモデルにセットする
		model.addAttribute("titleList", titleTblDao.selectBrowsableThreadTblList(Session.getUser(request).getUserId()));
		// 最後に閲覧したタイトルを取得し、閲覧データがあればレスの一覧をモデルにセットする
		var titleIdArray = lastTitleViewedTblDao.selectUser(Session.getUser(request).getUserId());
		if (titleIdArray.size() > 0) {
			model.addAttribute("responseList", threadTblDao.selectResponseList(titleIdArray.get(0).getTitleId()));
		}
		model.addAttribute("user", Session.getUser(request));
		return "sample/talk";
	}

	@PostMapping(path = "/selectTitle")
	public String updateAccount(HttpServletRequest request, RedirectAttributes redirectAttribute,
			@RequestParam("titleId") int titleId)
			throws NoSuchAlgorithmException {
		// 指定したタイトルIDの閲覧権限があるか判定する
		var titleList = titleTblDao.selectBrowsableThreadTblList(Session.getUser(request).getUserId());
		for (TitleTbl title : titleList) {
			// 閲覧権限があれば最終閲覧テーブルの更新処理を実行する
			if (title.getTitleId() == titleId) {
				var now = new Date();
				// 最終閲覧テーブルを更新、ない場合は作成してトークページ表示にリダイレクトすることで画面表示する
				if (lastTitleViewedTblDao.selectUser(Session.getUser(request).getUserId()).size() > 0) {
					if (lastTitleViewedTblDao
							.updateUser(
									new LastTitleViewedTbl(Session.getUser(request).getUserId(), titleId, now)) < 1) {
						Session.setErrorMessage(request, "テーブル情報を取得できませんでした。");
					}
				} else {
					if (lastTitleViewedTblDao
							.createUser(new LastTitleViewedTbl(Session.getUser(request).getUserId(), titleId, now,
									now)) < 1) {
						Session.setErrorMessage(request, "テーブル情報を取得できませんでした。");
					}
				}
				return "redirect:sample/talk";
			}
		}
		Session.setErrorMessage(request, "指定したタイトルは閲覧権限がありません。");
		return "redirect:sample/talk";
	}

	@MessageMapping("/message")
	@SendTo("/receive/message")
	public Message send(Message message) throws Exception {
		Thread.sleep(1000);
		var now = new Date();
		threadTblDao.createThread(new ThreadTbl(1, threadTblDao.getMaxMessageNumber() + 1,
				HtmlUtils.htmlEscape(message.getUserId()), message.getStatement(), now, now));
		return new Message(HtmlUtils.htmlEscape(message.getUserId()), HtmlUtils.htmlEscape(message.getUserName()),
				HtmlUtils.htmlEscape(message.getStatement()));
	}
}
