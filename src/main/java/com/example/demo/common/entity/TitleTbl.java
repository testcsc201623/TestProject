package com.example.demo.common.entity;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "title_tbl")
public class TitleTbl {

	@Id
	@Column(name = "title_id")
	public int titleId;
	
	@Column(name = "title")
	public String title;

	@Column(name = "create_user_id")
	public String createUserId;

	@Column(name = "create_date_time")
	public Date createDateTime;

	@Column(name = "update_date_time")
	public Date updateDateTime;
}
