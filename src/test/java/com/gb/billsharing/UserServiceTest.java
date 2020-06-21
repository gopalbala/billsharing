package com.gb.billsharing;


import com.gb.billsharing.exceptions.ContributionExceededException;
import com.gb.billsharing.exceptions.ExpenseDoesNotExistsException;
import com.gb.billsharing.exceptions.ExpenseSettledException;
import com.gb.billsharing.exceptions.InvalidExpenseState;
import com.gb.billsharing.model.*;
import com.gb.billsharing.repository.ExpenseRepository;
import com.gb.billsharing.service.ExpenseService;
import com.gb.billsharing.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    static UserService userService;
    static ExpenseService expenseService;

    @BeforeAll
    public static void initClass() {
        userService = new UserService();
        expenseService = new ExpenseService();
    }

    @Test
    @DisplayName("Create user test")
    public void createUserTest() {
        User user = userService.createUser("bagesh@gmail.com", "3486199635");
        assertNotNull(user);
        assertEquals("bagesh@gmail.com", user.getEmailId());
    }

    @Test
    @DisplayName("Create user null email id test")
    public void createUserNullEmailTest() {
        assertThrows(NullPointerException.class, () -> {
            User user = userService.createUser(null, "3486199635");
        });
    }

    private static void bifurcateExpense(String expenseId) throws ExpenseDoesNotExistsException {
        expenseService.addUsersToExpense(expenseId, "bagesh@gmail.com");
        expenseService.assignExpenseShare(expenseId, ExpenseRepository.expenseMap.get(expenseId).getUserId(), 2000);
    }

    @Test
    @DisplayName("Contribute share to expense")
    public void contributeToExpense() throws ContributionExceededException, InvalidExpenseState, ExpenseSettledException {
        User user = userService.createUser("bagesh@gmail.com", "3486199635");
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, user.getEmailId());
        try {
            bifurcateExpense(expense.getId());
        } catch (ExpenseDoesNotExistsException expenseDoesNotExistsException) {
            System.out.println(expenseDoesNotExistsException.getMessage());
        }
        expense.setExpenseStatus(ExpenseStatus.PENDING);
        Set<User> users = expense.getExpenseGroup().getGroupMembers();
        for (User usr : users) {
            Contribution contribution = new Contribution();
            ExpenseGroup expenseGroup = expense.getExpenseGroup();
            UserShare userShare = expenseGroup.getUserContributions().get(usr.getEmailId());
            contribution.setContributionValue(600);
            contribution.setContributionDate(LocalDateTime.now());
            contribution.setTransactionId("T" + Instant.EPOCH);
            contribution.setTransactionDescription("Transferred from UPI");
            userService.contributeToExpense(expense.getId(), usr.getEmailId(), contribution);
        }

        assertEquals(600,
                expense.getExpenseGroup().getUserContributions()
                        .get(user.getEmailId()).getContributions().get(0).getContributionValue());
    }
}
