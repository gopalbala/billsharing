package com.gb.billsharing.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    private String id;
    private String title;
    private String description;
    private LocalDateTime expenseDate;
    private double expenseAmount;
}