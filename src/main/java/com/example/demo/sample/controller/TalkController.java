package com.example.demo.sample.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import com.example.demo.common.entity.ThreadTbl;
import com.example.demo.common.model.Message;
import com.example.demo.common.model.Session;
import com.example.demo.sample.dao.ThreadTblDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TalkController {

	private final ThreadTblDao threadTblDao;

	@GetMapping(path = "/sample/talk")
	public String loginPage(HttpServletRequest request, Model model) {
		if (Session.getErrorMessage(request) != null) {
			model.addAttribute("errorMessage", Session.getErrorMessage(request));
			Session.setErrorMessage(request, null);
		}
		model.addAttribute("responseList", threadTblDao.selectResponseList());
		model.addAttribute("user", Session.getUser(request));
		return "sample/talk";
	}

	@MessageMapping("/message")
	@SendTo("/receive/message")
	public Message send(Message message) throws Exception {
		Thread.sleep(1000);
		var now = new Date();
		threadTblDao.createThread(new ThreadTbl("1", threadTblDao.getMaxMessageNumber() + 1,
				HtmlUtils.htmlEscape(message.getId()), message.getStatement(), now, now));
		return new Message(HtmlUtils.htmlEscape(message.getId()),HtmlUtils.htmlEscape(message.getName()), HtmlUtils.htmlEscape(message.getStatement()));
	}
}
