package io.micronaut.infrastructure.mybatis.mapper;

import io.micronaut.core.user.FollowRelation;
import io.micronaut.core.user.User;
import io.micronaut.http.annotation.Delete;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
	
	@Insert("insert into users "
			+ "values(#{user.id}, #{user.username}, #{user.password}, #{user.email}, "
			+ "#{user.bio}, #{user.image})")
    @Options(useGeneratedKeys = true)
    void insert(@Param("user") User user);

	@Select("select * from users where username=#{username}")
    User findByUsername(String username);
	
	@Select("select * from users where email=#{email}")
    User findByEmail(String email);

	@Select("select * from users where id=#{id}")
    User findById(String id);

	@Update("update users set name=#{name} where id=#{id}")
    void update(@Param("user") User user);

	@Select("select * from followRelation where userId=#{userId} and targetId = #{targetId}")
    FollowRelation findRelation(@Param("userId") String userId, @Param("targetId") String targetId);

    @Insert("insert into followRelation(userId, targetId) values(#{userId}, #{targetId})")
    void saveRelation(@Param("followRelation") FollowRelation followRelation);

    //@Delete("delete from followRelation where userId=#{userId} and targetId = #{targetId}")
    //void deleteRelation(@Param("followRelation") FollowRelation followRelation);
}
