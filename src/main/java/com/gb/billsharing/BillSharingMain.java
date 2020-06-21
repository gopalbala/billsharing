package com.gb.billsharing;

import com.gb.billsharing.exceptions.ContributionExceededException;
import com.gb.billsharing.exceptions.ExpenseDoesNotExistsException;
import com.gb.billsharing.exceptions.ExpenseSettledException;
import com.gb.billsharing.exceptions.InvalidExpenseState;
import com.gb.billsharing.model.*;
import com.gb.billsharing.repository.ExpenseRepository;
import com.gb.billsharing.service.ExpenseService;
import com.gb.billsharing.service.UserService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

public class BillSharingMain {

    static ExpenseService expenseService;
    static UserService userService;

    public static void main(String[] args) throws ContributionExceededException, InvalidExpenseState,
            ExpenseSettledException {
        expenseService = new ExpenseService();
        userService = new UserService();
        createTestUsers();

        Expense expense = createLunchExpense();
        try {
            bifurcateExpense(expense.getId());
        } catch (ExpenseDoesNotExistsException expenseDoesNotExistsException) {
            System.out.println(expenseDoesNotExistsException.getMessage());
        }
        expense.setExpenseStatus(ExpenseStatus.PENDING);

        Set<User> users = expense.getExpenseGroup().getGroupMembers();
        for (User user : users) {
            contributeToExpense(expense.getId(), user.getEmailId());
        }
        if (expenseService.isExpenseSettled(expense.getId())) {
            System.out.println("Expense Settled....");
            expenseService.setExpenseStatus(expense.getId(), ExpenseStatus.SETTLED);
        }
        System.out.println("Bye......");
    }

    private static void createTestUsers() {
        User user1 = userService.createUser("bagesh@gmail.com", "bagesh", "3486199635");
        User user2 = userService.createUser("ajay@gmail.com", "ajay", "6112482630");
        User user3 = userService.createUser("amit@gmail.com", "amit", "2509699232");
        User user4 = userService.createUser("kamal@gmail.com", "kamal", "5816355154");
        User user5 = userService.createUser("neha@gmail.com", "neha", "7737316054");
        User user6 = userService.createUser("kajal@gmail.com", "kajal", "4813053349");
        User user7 = userService.createUser("jyothi@gmail.com", "jyothi", "3974178644");
        User user8 = userService.createUser("subin@gmail.com", "subin", "4768463294");
        User user9 = userService.createUser("deepak@gmail.com", "deepak", "4829338803");
        User user10 = userService.createUser("vishnu@gmail.com", "vishnu", "3384071602");
        User user11 = userService.createUser("mayank@gmail.com", "mayank", "2376951206");
        User user12 = userService.createUser("anu@gmail.com", "anu", "8478577491");
        User user13 = userService.createUser("kavya@gmail.com", "kavya", "7505888698");
        User user14 = userService.createUser("divya@gmail.com", "divya", "9587030077");
        User user15 = userService.createUser("prabhu@gmail.com", "prabhu", "3303167757");
        User user16 = userService.createUser("sangeeth@gmail.com", "sangeeth", "7409081739");
        User user17 = userService.createUser("rajesh@gmail.com", "rajesh", "2367659285");
        User user18 = userService.createUser("alamelu@gmail.com", "alamelu", "8938025834");
        User user19 = userService.createUser("aruna@gmail.com", "aruna", "8189506064");
        User user20 = userService.createUser("palani@gmail.com", "palani", "2973733105");
    }

    public static Expense createLunchExpense() {
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, "vishnu@gmail.com");
        return expense;
    }

    private static void bifurcateExpense(String expenseId) throws ExpenseDoesNotExistsException {
        expenseService.addUsersToExpense(expenseId, "bagesh@gmail.com");
        expenseService.addUsersToExpense(expenseId, "divya@gmail.com");
        expenseService.addUsersToExpense(expenseId, "palani@gmail.com");
        expenseService.addUsersToExpense(expenseId, "neha@gmail.com");

        expenseService.assignExpenseShare(expenseId, ExpenseRepository.expenseMap.get(expenseId).getUserId(), 400);
        expenseService.assignExpenseShare(expenseId, "bagesh@gmail.com", 400);
        expenseService.assignExpenseShare(expenseId, "divya@gmail.com", 400);
        expenseService.assignExpenseShare(expenseId, "palani@gmail.com", 400);
        expenseService.assignExpenseShare(expenseId, "neha@gmail.com", 400);
    }

    private static void contributeToExpense(String expenseId, String userId)
            throws ContributionExceededException, InvalidExpenseState, ExpenseSettledException {
        Contribution contribution = new Contribution();
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        ExpenseGroup expenseGroup = expense.getExpenseGroup();
        UserShare userShare = expenseGroup.getUserContributions().get(userId);
        contribution.setContributionValue(userShare.getShare());
        contribution.setContributionDate(LocalDateTime.now());
        contribution.setTransactionId("T" + Instant.EPOCH);
        contribution.setTransactionDescription("Transferred from UPI");
        userService.contributeToExpense(expenseId, userId, contribution);

    }
}
