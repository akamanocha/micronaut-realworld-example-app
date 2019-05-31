package io.micronaut.infrastructure.repository;

import java.util.Optional;

import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.micronaut.core.user.FollowRelation;
import io.micronaut.core.user.User;
import io.micronaut.core.user.UserRepository;
import io.micronaut.infrastructure.mybatis.mapper.UserMapper;
import io.micronaut.validation.Validated;


@Singleton
@Validated
public class UserRepositoryImpl implements UserRepository {
	
	private final UserMapper userMapper;
	
	public UserRepositoryImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public User save(@NotBlank String email, @NotBlank String username, @NotBlank String password, 
			@NotBlank String bio, @NotBlank String image) {
		User user = new User(email, username, password, bio, image);
        userMapper.insert(user);
        return user;
	}

	@Override
	public Optional<User> findById(String id) {
		return Optional.ofNullable(userMapper.findById(id));
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return Optional.ofNullable(userMapper.findByUsername(username));
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(userMapper.findByEmail(email));
	}

	@Override
	public void saveRelation(FollowRelation followRelation) {
		userMapper.saveRelation(followRelation);
	}

	@Override
	public Optional<FollowRelation> findRelation(String userId, String targetId) {
		return Optional.ofNullable(userMapper.findRelation(userId, targetId));
	}

	/*
	@Override
	public void removeRelation(FollowRelation followRelation) {
		userMapper.deleteRelation(followRelation);
	}
	*/

	@Override
	public User save(User user) {
		userMapper.insert(user);
        return user;
	}

}
