package com.gb.billsharing;


import com.gb.billsharing.model.User;
import com.gb.billsharing.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    static UserService userService;

    @BeforeAll
    public static void initClass() {
        userService = new UserService();
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
}
