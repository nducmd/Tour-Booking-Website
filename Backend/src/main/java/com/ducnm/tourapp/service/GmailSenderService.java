package com.ducnm.tourapp.service;

import com.ducnm.tourapp.model.User;
import jakarta.mail.MessagingException;

public interface GmailSenderService {
    void sendEmailConfirm(Long id) throws MessagingException;
    void sendEmailResetPassword(User user) throws MessagingException;
}
