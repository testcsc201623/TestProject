package com.example.demo.sample.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.common.entity.User;

@Dao
@ConfigAutowireable
public interface UserDao {
	@Select
    List<User> selectUser(String userId);
}