package com.gb.billsharing.service;

import com.gb.billsharing.model.Expense;
import com.gb.billsharing.model.User;

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifyUser(User user, Expense expense) {
        System.out.println("Notified");
    }
}
