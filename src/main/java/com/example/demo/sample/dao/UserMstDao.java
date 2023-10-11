package com.example.demo.sample.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.common.entity.UserMst;

@Dao
@ConfigAutowireable
public interface UserMstDao {
	@Select
    List<UserMst> selectAll();
	
	@Select
    List<UserMst> selectUser(String userId);
}