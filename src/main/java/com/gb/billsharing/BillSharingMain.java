package com.gb.billsharing;

import com.gb.billsharing.exceptions.ExpenseDoesNotExistsException;
import com.gb.billsharing.model.Expense;
import com.gb.billsharing.model.ExpenseStatus;
import com.gb.billsharing.model.User;
import com.gb.billsharing.repository.ExpenseRepository;
import com.gb.billsharing.service.ExpenseService;
import com.gb.billsharing.service.UserService;

import java.time.LocalDateTime;
import java.time.Month;

public class BillSharingMain {

    static ExpenseService expenseService;
    static UserService userService;
    public static void main(String[] args) {
        createTestUsers();
        expenseService = new ExpenseService();
        userService = new UserService();
        Expense expense = createLunchExpense();
        try {
            bifurcateExpense(expense.getId());
        } catch (ExpenseDoesNotExistsException expenseDoesNotExistsException) {
            System.out.println(expenseDoesNotExistsException.getMessage());
        }
        expense.setExpenseStatus(ExpenseStatus.PENDING);

    }

    private static void createTestUsers() {
        User user1 = userService.createUser("bagesh@gmail.com", "3486199635");
        User user2 = userService.createUser("ajay@gmail.com", "6112482630");
        User user3 = userService.createUser("amit@gmail.com", "2509699232");
        User user4 = userService.createUser("kamal@gmail.com", "5816355154");
        User user5 = userService.createUser("neha@gmail.com", "7737316054");
        User user6 = userService.createUser("kajal@gmail.com", "4813053349");
        User user7 = userService.createUser("jyothi@gmail.com", "3974178644");
        User user8 = userService.createUser("subin@gmail.com", "4768463294");
        User user9 = userService.createUser("deepak@gmail.com", "4829338803");
        User user10 = userService.createUser("vishnu@gmail.com", "3384071602");
        User user11 = userService.createUser("mayank@gmail.com", "2376951206");
        User user12 = userService.createUser("anu@gmail.com", "8478577491");
        User user13 = userService.createUser("kavya@gmail.com", "7505888698");
        User user14 = userService.createUser("divya@gmail.com", "9587030077");
        User user15 = userService.createUser("prabhu@gmail.com", "3303167757");
        User user16 = userService.createUser("sangeeth@gmail.com", "7409081739");
        User user17 = userService.createUser("rajesh@gmail.com", "2367659285");
        User user18 = userService.createUser("alamelu@gmail.com", "8938025834");
        User user19 = userService.createUser("aruna@gmail.com", "8189506064");
        User user20 = userService.createUser("palani@gmail.com", "2973733105");
    }

    public static Expense createLunchExpense() {
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
        , LocalDateTime.of(2020, Month.JUNE,19,12,0),2000.00,"vishnu@gmail.com");
        return expense;
    }

    private static void bifurcateExpense(String expenseId) throws ExpenseDoesNotExistsException {
        expenseService.addUsersToExpense(expenseId,"bagesh@gmail.com");
        expenseService.addUsersToExpense(expenseId,"divya@gmail.com");
        expenseService.addUsersToExpense(expenseId,"palani@gmail.com");
        expenseService.addUsersToExpense(expenseId,"neha@gmail.com");

        expenseService.assignExpenseShare(expenseId, ExpenseRepository.expenseMap.get(expenseId).getUserId(),400);
        expenseService.assignExpenseShare(expenseId, "bagesh@gmail.com",400);
        expenseService.assignExpenseShare(expenseId, "divya@gmail.com",400);
        expenseService.assignExpenseShare(expenseId, "palani@gmail.com",400);
        expenseService.assignExpenseShare(expenseId, "neha@gmail.com",400);
    }
}
