package com.example.demo.sample.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.common.entity.LastTitleViewedTbl;

@Dao
@ConfigAutowireable
public interface LastTitleViewedTblDao {
	@Select
	List<LastTitleViewedTbl> selectUser(String userId);

	@Update(include = { "userId", "titleId", "updateDateTime" })
	int updateUser(LastTitleViewedTbl lastTitleViewedTbl);

	@Insert
	int createUser(LastTitleViewedTbl lastTitleViewedTbl);
}
