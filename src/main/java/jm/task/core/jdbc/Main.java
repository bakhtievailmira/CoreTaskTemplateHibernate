package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        List<User> usersList;
        userService.createUsersTable();
        userService.saveUser("User1_Name","User1_LastName", (byte) 11);
        userService.saveUser("User2_Name","User2_LastName", (byte) 22);
        userService.saveUser("User3_Name","User3_LastName", (byte) 33);
        userService.saveUser("User4_Name","User4_LastName", (byte) 44);
        usersList = userService.getAllUsers();
        for (int i = 0; i < usersList.size(); i++) {
            System.out.println(usersList.get(i));
        }
    //    userService.cleanUsersTable();
    //    userService.dropUsersTable();

    }

}
