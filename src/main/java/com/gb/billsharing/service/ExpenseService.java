package com.gb.billsharing.service;

import com.gb.billsharing.model.Expense;

import java.time.LocalDateTime;

public class ExpenseService {
    public Expense createExpense(String title, String description, LocalDateTime expenseDate, double expenseAmount,
                                 String userId) {
        Expense expense = Expense.builder()
                .title(title)
                .description(description)
                .expenseDate(expenseDate)
                .expenseAmount(expenseAmount)
                .userId(userId)
                .build();
        return expense;
    }
}
