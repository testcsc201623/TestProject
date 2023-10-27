package com.example.demo.sample.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.common.entity.TitleTbl;

@Dao
@ConfigAutowireable
public interface TitleTblDao {
	@Select
	List<TitleTbl> selectBrowsableThreadTblList(String userId);
}
