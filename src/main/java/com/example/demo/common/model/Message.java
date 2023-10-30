package com.example.demo.common.model;

import lombok.Data;

@Data
public class Message {

	private String userId;
	private String userName;
	private String statement;

	public Message(String userId, String userName, String statement) {
		this.userId = userId;
		this.userName = userName;
		this.statement = statement;
	}
}