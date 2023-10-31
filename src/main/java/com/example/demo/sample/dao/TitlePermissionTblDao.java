package com.example.demo.sample.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface TitlePermissionTblDao {
	@Select
	List<String> selectUserList(int titleId);
	
	@Select
	List<Integer> selectAdminFlgList(int titleId, String userId);
}
