package com.gb.billsharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class ExpenseGroup {

    @Setter
    private List<User> groupMembers;
    private String expenseGroupId;
    @Setter
    private Map<String, Contribution> userContributions;

    public ExpenseGroup() {
        this.expenseGroupId = UUID.randomUUID().toString();
    }
}