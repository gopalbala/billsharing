package com.gb.billsharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class UserShare {
    public UserShare(String userId, double share) {
        this.userId = userId;
        this.share = share;
    }
    private String userId;
    private double share;
    List<Contribution> contributions;
}
