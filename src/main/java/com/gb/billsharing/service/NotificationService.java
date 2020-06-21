package com.gb.billsharing.service;

import com.gb.billsharing.model.Expense;
import com.gb.billsharing.model.User;

public interface NotificationService {
    void notifyUser(User user, Expense expense);
}
