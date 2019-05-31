package io.micronaut.core.user;


import javax.inject.Singleton;

import io.micronaut.core.user.EncryptService;

@Singleton
public class NaiveEncryptService implements EncryptService {
    @Override
    public String encrypt(String password) {
        return password;
    }

    @Override
    public boolean check(String checkPassword, String realPassword) {
        return checkPassword.equals(realPassword);
    }
}
