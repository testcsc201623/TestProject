package com.example.demo.common.model;

import lombok.Data;

@Data
public class Message {

	private String id;
	private String name;
	private String statement;

	public Message(String id, String name, String statement) {
		this.id = id;
		this.name = name;
		this.statement = statement;
	}
}