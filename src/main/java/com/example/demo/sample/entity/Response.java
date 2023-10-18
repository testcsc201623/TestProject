package com.example.demo.sample.entity;

import java.sql.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import lombok.Data;

@Entity
@Data
public class Response {

	@Column(name = "message_number")
	public int messageNumber;

	@Column(name = "user_id")
	public String userId;

	@Column(name = "user_name")
	public String userName;

	@Column(name = "comment")
	public String comment;

	@Column(name = "create_date_time")
	public Date createDateTime;
}
