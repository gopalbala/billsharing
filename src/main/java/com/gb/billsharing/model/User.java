package com.gb.billsharing.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private String name;

    private String userId;
    private String emailId;

    public User(@NonNull String emailId, String name, String phoneNUmber) {
        userId = UUID.randomUUID().toString();
        this.emailId = emailId;
        this.name = name;
        this.phoneNumber = phoneNUmber;
    }

    private String phoneNumber;
}