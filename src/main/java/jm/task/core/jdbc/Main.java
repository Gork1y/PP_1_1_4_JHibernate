package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();



    public static void main (String[] args) {

        // реализуйте алгоритм здесь
        userService.createUsersTable();
        userService.saveUser("kirill", "gorkiy", (byte) 31);
        userService.saveUser("liudmila", "bystrova", (byte) 34);
        userService.saveUser("alex", "babinovich", (byte) 32);
        userService.saveUser("veronika", "anatolevna", (byte) 55);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
