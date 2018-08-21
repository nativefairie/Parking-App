package com.parking.app.service;

import com.parking.app.domain.User;

import javax.servlet.http.Cookie;

public interface UserService {
    void saveUserToDb(User user);
    User findByUserEmail(String userEmail);
    boolean verifyUser(User user);
    Cookie logIn(User user);
    User findUserByCookie(String cookie);
}
