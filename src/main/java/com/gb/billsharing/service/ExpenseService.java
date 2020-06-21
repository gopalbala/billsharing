package com.gb.billsharing.service;

import com.gb.billsharing.exceptions.ExpenseDoesNotExistsException;
import com.gb.billsharing.model.*;
import com.gb.billsharing.repository.ExpenseRepository;
import com.gb.billsharing.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

//@Slf4j
public class ExpenseService {
    private NotificationService notificationService = new NotificationServiceImpl();

    public Expense createExpense(String title, String description, LocalDateTime expenseDate, double expenseAmount,
                                 String userId) {
        Expense expense = Expense.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .description(description)
                .expenseDate(expenseDate)
                .expenseAmount(expenseAmount)
                .userId(userId)
                .expenseStatus(ExpenseStatus.CREATED)
                .expenseGroup(new ExpenseGroup())
                .build();
        ExpenseRepository.expenseMap.putIfAbsent(expense.getId(), expense);
        return expense;
    }

    public void addUsersToExpense(String expenseId, String emailId) throws
            ExpenseDoesNotExistsException{
        if (!ExpenseRepository.expenseMap.containsKey(expenseId)) {
            throw new
                    ExpenseDoesNotExistsException("Better create expense and come here....");
//            System.out.println("Better create expense and come here....");
        }
        ExpenseRepository.expenseMap.get(expenseId)
                .getExpenseGroup().getGroupMembers()
                .add(UserRepository.userHashMap.get(emailId));

        if (notificationService != null) {
            notificationService.notifyUser(UserRepository.userHashMap.get(emailId),
                    ExpenseRepository.expenseMap.get(expenseId));
        }
    }

    public void assignExpenseShare(String expenseId, String emailId, double share)
            throws ExpenseDoesNotExistsException {
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

    public boolean isExpenseSettled(String expenseId) {
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        ExpenseGroup expenseGroup = expense.getExpenseGroup();
        Map<String, UserShare> userContributions = expenseGroup.getUserContributions();

        double total = expense.getExpenseAmount();

        for (Map.Entry<String, UserShare> entry : userContributions.entrySet()) {
            UserShare userShare = entry.getValue();
            for (Contribution contribution : userShare.getContributions()) {
                total -= contribution.getContributionValue();
            }
        }
        if (total <= 1) {
            return true;
        }
        return false;
    }

}
