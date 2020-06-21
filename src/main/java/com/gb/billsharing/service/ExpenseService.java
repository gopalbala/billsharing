package com.gb.billsharing.service;

import com.gb.billsharing.exceptions.ExpenseDoesNotExistsException;
import com.gb.billsharing.model.Expense;
import com.gb.billsharing.model.ExpenseGroup;
import com.gb.billsharing.model.ExpenseStatus;
import com.gb.billsharing.model.UserShare;
import com.gb.billsharing.repository.ExpenseRepository;
import com.gb.billsharing.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class ExpenseService {

    public Expense createExpense(String title, String description, LocalDateTime expenseDate, double expenseAmount,
                                 String userId) {
        Expense expense = Expense.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .description(description)
                .expenseDate(expenseDate)
                .expenseAmount(expenseAmount)
                .userId(userId)
                .expenseGroup(new ExpenseGroup())
                .build();
        ExpenseRepository.expenseMap.putIfAbsent(expense.getId(), expense);
        return expense;
    }

    public void addUsersToExpense(String expenseId, String emailId) {
        if (!ExpenseRepository.expenseMap.containsKey(expenseId)) {
            System.out.println("Better create expense and come here....");
        }
        ExpenseRepository.expenseMap.get(expenseId)
                .getExpenseGroup().getGroupMembers()
                .add(UserRepository.userHashMap.get(emailId));
    }

    public void assignExpenseShare(String expenseId, String emailId, double share) throws ExpenseDoesNotExistsException {
        if (!ExpenseRepository.expenseMap.containsKey(expenseId)) {
            throw new ExpenseDoesNotExistsException(String.format("Expense %s does not exists", expenseId));
        }
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        expense.getExpenseGroup()
                .getUserContributions().putIfAbsent(emailId, new UserShare(emailId, share));
    }

    public void setExpenseStatus(String expenseId, ExpenseStatus expenseStatus) {
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        expense.setExpenseStatus(expenseStatus);
    }


}
