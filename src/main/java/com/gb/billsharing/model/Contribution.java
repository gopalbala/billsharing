package com.gb.billsharing.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Contribution {
    private String contributionId;
    private double contributionValue;
    private String transactionId;
    private LocalDateTime contributionDate;
    private String transactionDescription;
}