package com.gb.billsharing.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private String userId;
    private String emailId;
    private String phoneNumber;
}