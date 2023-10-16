package com.example.demo.sample.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.common.entity.UserMst;

@Dao
@ConfigAutowireable
public interface UserMstDao {
	@Select
	List<UserMst> selectAll();

	@Select
	List<UserMst> selectUser(String userId);

	@Insert
	int createUser(UserMst userMst);

	@Update(include = { "password", "updateDateTime" })
	int updatePassword(UserMst userMst);

	@Update(include = { "userName", "adminFlg", "updateDateTime" })
	int updateUser(UserMst userMst);

	@Delete
	int deleteUser(UserMst userMst);
}