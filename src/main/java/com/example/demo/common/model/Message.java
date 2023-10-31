package com.example.demo.common.model;

import lombok.Data;

@Data
public class Message {

	private int titleId;
	private String userId;
	private String userName;
	private String statement;

	public Message(int titleId,String userId, String userName, String statement) {
		this.titleId = titleId;
		this.userId = userId;
		this.userName = userName;
		this.statement = statement;
	}
}