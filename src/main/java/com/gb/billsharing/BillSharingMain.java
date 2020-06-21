package com.gb.billsharing;

import com.gb.billsharing.model.User;
import com.gb.billsharing.service.UserService;

public class BillSharingMain {
    public static void main(String[] args) {
        UserService userService = new UserService();
        createTestUsers(userService);
    }

    private static void createTestUsers(UserService userService) {
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
}
