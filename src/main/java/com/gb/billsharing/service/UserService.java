package com.gb.billsharing.service;

import com.gb.billsharing.exceptions.ContributionExceededException;
import com.gb.billsharing.exceptions.ExpenseSettledException;
import com.gb.billsharing.exceptions.InvalidExpenseState;
import com.gb.billsharing.model.*;
import com.gb.billsharing.repository.ExpenseRepository;
import com.gb.billsharing.repository.UserRepository;

public class UserService {
    public User createUser(String emailId, String phoneNumber) {
        User user = new User(emailId, phoneNumber);
        UserRepository.userHashMap.putIfAbsent(emailId, user);
        return user;
    }

    public void contributeToExpense(String expenseId, String emailId, Contribution contribution)
            throws InvalidExpenseState, ExpenseSettledException, ContributionExceededException {
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        ExpenseGroup expenseGroup = expense.getExpenseGroup();
        if (expense.getExpenseStatus() == ExpenseStatus.CREATED) {
            throw new InvalidExpenseState("Invalid expense State");
        }
        if (expense.getExpenseStatus() == ExpenseStatus.SETTLED) {
            throw new ExpenseSettledException("Expense is already settled.");
        }
        UserShare userShare = expenseGroup.getUserContributions().get(emailId);
        if (contribution.getContributionValue() > userShare.getShare()) {
            throw new ContributionExceededException(
                    String.format("User %s contribution %d exceeded the share %d",
                            emailId, contribution.getContributionValue(), userShare.getShare()));
        }
        userShare.getContributions().add(contribution);
    }
}
