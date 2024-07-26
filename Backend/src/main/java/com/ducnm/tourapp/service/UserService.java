package com.ducnm.tourapp.service;

import com.ducnm.tourapp.model.User;
import jakarta.mail.MessagingException;

import java.util.Map;

public interface UserService {
    User findUserByEmail(String email);
    void resetPassword(Map<String, String> resetInfo);
    void forgotPassword(String email) throws MessagingException;
}
