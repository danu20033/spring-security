package com.danushka.test.springsecurityclient.service;

import com.danushka.test.springsecurityclient.entity.User;
import com.danushka.test.springsecurityclient.entity.VerificationToken;
import com.danushka.test.springsecurityclient.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationToken(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateVerificationToken(String token);
}
