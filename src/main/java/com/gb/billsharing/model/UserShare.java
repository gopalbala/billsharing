package com.gb.billsharing.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserShare {
    public UserShare(String userId, double share) {
        this.userId = userId;
        this.share = share;
        contributions = new ArrayList<>();
    }

    private String userId;
    private double share;
    List<Contribution> contributions;
}
