package io.micronaut.infrastructure.mybatis.readservice;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import io.micronaut.application.data.UserData;

public interface UserReadService {
	
	@Select("select * from users where username = #{username}")
	UserData findByUsername(@Param("username") String username);

	@Select("select * from users where id = #{id}")
    UserData findById(@Param("id") String id);

}
