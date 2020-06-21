package com.gb.billsharing.model;


import lombok.*;

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
    @Setter
    private ExpenseGroup expenseGroup;
}