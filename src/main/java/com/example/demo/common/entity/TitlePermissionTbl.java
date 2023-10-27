package com.example.demo.common.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "title_permission_tbl")
public class TitlePermissionTbl {

	@Id
	@Column(name = "title_id")
	public int titleId;

	@Id
	@Column(name = "user_id")
	public String userId;

	/**
	 * 管理者権限フラグ
	 * 0:一般 1:管理者
	 */
	@Column(name = "admin_flg")
	public int adminFlg;
}
