package com.gb.billsharing.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    private String id;
    private String userId;
    private String title;
    private String description;
    private LocalDateTime expenseDate;
    private double expenseAmount;
    @Getter
    private ExpenseGroup expenseGroup;
}