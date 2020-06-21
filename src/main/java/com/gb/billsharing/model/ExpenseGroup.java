package com.gb.billsharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExpenseGroup {
    private String expenseGroupId;
    private String expenseId;
    private Expense expense;
    private List<User> groupMembers;
    private Map<String, Contribution> userContributions;
}