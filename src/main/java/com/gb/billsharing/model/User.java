package com.gb.billsharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    public User(String emailId, String phoneNUmber) {
        userId = UUID.randomUUID().toString();
        this.emailId = emailId;
        this.phoneNumber = phoneNUmber;
    }

    private String userId;
    private String emailId;
    private String phoneNumber;
}