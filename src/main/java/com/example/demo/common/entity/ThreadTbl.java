package com.example.demo.common.entity;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "thread_tbl")
public class ThreadTbl {

	@Id
	@Column(name = "title_id")
	public String titleId;

	@Id
	@Column(name = "message_number")
	public int messageNumber;

	@Id
	@Column(name = "user_id")
	public String userId;

	@Column(name = "comment")
	public String comment;

	@Column(name = "create_date_time")
	public Date createDateTime;

	@Column(name = "update_date_time")
	public Date updateDateTime;
}
