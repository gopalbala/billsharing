package com.gb.billsharing;

import com.gb.billsharing.exceptions.ExpenseDoesNotExistsException;
import com.gb.billsharing.model.Expense;
import com.gb.billsharing.model.User;
import com.gb.billsharing.repository.ExpenseRepository;
import com.gb.billsharing.service.ExpenseService;
import com.gb.billsharing.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExpenseServiveTest {
    static UserService userService;
    static ExpenseService expenseService;

    @BeforeAll
    public static void initClass() {
        userService = new UserService();
        expenseService = new ExpenseService();
    }

    @Test
    @DisplayName("Create expense test")
    public void createUserTest() {
        User user = userService.createUser("bagesh@gmail.com", "3486199635");
        assertNotNull(user);
        assertEquals("bagesh@gmail.com", user.getEmailId());
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, user.getEmailId());

        assertNotNull(expense);
        assertEquals(2000, expense.getExpenseAmount());
    }

    @Test
    @DisplayName("Add user to test")
    public void addUserToExpenseTest() {
        User user = userService.createUser("bagesh@gmail.com", "3486199635");
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, user.getEmailId());
        expenseService.addUsersToExpense(expense.getId(), "bagesh@gmail.com");


        assertNotNull(expense.getExpenseGroup().getGroupMembers());
        assertEquals(1, expense.getExpenseGroup().getGroupMembers().size());
    }

    @Test
    @DisplayName("Add user to test")
    public void assignExpenseShareTest() {
        User user = userService.createUser("bagesh@gmail.com", "3486199635");
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, user.getEmailId());
        try {
            expenseService.addUsersToExpense(expense.getId(), "bagesh@gmail.com");
            expenseService.assignExpenseShare(expense.getId(),
                    ExpenseRepository.expenseMap.get(expense.getId()).getUserId(), 2000);
        } catch (ExpenseDoesNotExistsException expenseDoesNotExistsException) {
            System.out.println(expenseDoesNotExistsException.getMessage());
        }
        assertNotNull(expense.getExpenseGroup().getGroupMembers());
        assertEquals(1, expense.getExpenseGroup().getGroupMembers().size());
        assertNotNull(expense.getExpenseGroup().getExpenseGroupId());
    }


}
