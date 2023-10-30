package com.example.demo.common.entity;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "last_title_viewed_tbl")
public class LastTitleViewedTbl {

	@Id
	@Column(name = "user_id")
	public String userId;

	@Column(name = "title_id")
	public int titleId;

	@Column(name = "create_date_time")
	public Date createDateTime;

	@Column(name = "update_date_time")
	public Date updateDateTime;

	/**
	 * コンストラクタ
	 */
	public LastTitleViewedTbl() {

	}

	/**
	 * コンストラクタ
	 * @param userId ユーザID
	 * @param titleId タイトルID
	 * @param updateDateTime 更新日時
	 */
	public LastTitleViewedTbl(String userId, int titleId, Date updateDateTime) {
		this.userId = userId;
		this.titleId = titleId;
		this.updateDateTime = updateDateTime;
	}

	/**
	 * コンストラクタ
	 * @param userId ユーザID
	 * @param titleId タイトルID
	 * @param createDateTime 作成日時
	 * @param updateDateTime 更新日時
	 */
	public LastTitleViewedTbl(String userId, int titleId, Date createDateTime, Date updateDateTime) {
		this.userId = userId;
		this.titleId = titleId;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}
}
