package com.example.demo.common.entity;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user_mst")
public class UserMst {
	@Id
	@Column(name = "user_id")
	public String userId;

	@Column(name = "password")
	public String password;

	@Column(name = "user_name")
	public String userName;

	@Column(name = "admin_flg")
	public int adminFlg;

	@Column(name = "create_date_time")
	public Date createDateTime;

	@Column(name = "update_date_time")
	public Date updateDateTime;

	/**
	 * コンストラクタ
	 */
	public UserMst() {
	}
	
	/**
	 * コンストラクタ
	 * @param userId ユーザID
	 * @param password パスワード
	 * @param updateDateTime 更新日付
	 */
	public UserMst(String userId,String password, Date updateDateTime) {
		this.userId = userId;
		this.password = password;
		this.updateDateTime = updateDateTime;
	}
	
	/**
	 * コンストラクタ
	 * @param userId ユーザID
	 * @param userName ユーザ名
	 * @param adminFlg 管理者フラグ
	 * @param updateDateTime 更新日付
	 */
	public UserMst(String userId,String userName, int adminFlg, Date updateDateTime) {
		this.userId = userId;
		this.userName = userName;
		this.adminFlg = adminFlg;
		this.updateDateTime = updateDateTime;
	}

	/**
	 * コンストラクタ
	 * @param userId ユーザID
	 * @param password パスワード
	 * @param userName ユーザ名
	 * @param adminFlg 管理者権限
	 * @param createDateTime 作成日時
	 * @param updateDateTime 更新日付
	 */
	public UserMst(String userId, String password, String userName, int adminFlg, Date createDateTime,
			Date updateDateTime) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.adminFlg = adminFlg;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}
	
	/**
	 * ユーザ名設定インスタンス
	 * @param userId ユーザID
	 * @param userName ユーザ名
	 * @param updateDateTime 更新日付
	 * @return
	 */
	public UserMst setUpdateUserName(String userId,String userName, Date updateDateTime) {
		this.userId = userId;
		this.userName = userName;
		this.updateDateTime = updateDateTime;
		return this;
	}
}