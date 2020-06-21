package com.gb.billsharing.service;

import com.gb.billsharing.model.User;

import java.util.UUID;

public class UserService {
    public User createUser(String emailId, String phoneNumber) {
        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .emailId(emailId)
                .phoneNumber(phoneNumber)
                .build();
        return user;
    }
}
