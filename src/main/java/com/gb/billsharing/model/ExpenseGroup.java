package com.gb.billsharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
public class ExpenseGroup {

    public void ExpenseGroup() {
        groupMembers = new HashSet<>();
    }
    private Set<User> groupMembers;
    private String expenseGroupId;
    @Setter
    private Map<String, UserShare> userContributions;

    public ExpenseGroup() {
        this.expenseGroupId = UUID.randomUUID().toString();
    }
}