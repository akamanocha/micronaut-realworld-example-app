package io.micronaut.application;

import io.micronaut.application.data.UserData;
import io.micronaut.infrastructure.mybatis.readservice.UserReadService;

import java.util.Optional;

import javax.inject.Singleton;

@Singleton
public class UserQueryService  {
    private UserReadService userReadService;

    public UserQueryService(UserReadService userReadService) {
        this.userReadService = userReadService;
    }

    public Optional<UserData> findById(String id) {
        return Optional.ofNullable(userReadService.findById(id));
    }
    
    public Optional<UserData> findByUsername(String username) {
        return Optional.ofNullable(userReadService.findByUsername(username));
    }
}
