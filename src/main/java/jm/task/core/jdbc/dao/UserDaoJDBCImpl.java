package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() throws ClassNotFoundException {

    }
    private Connection connection;
    private Driver driver;
    private User user;


    public void createUsersTable() {
        String createUserTable = "CREATE TABLE user";
        try {
            Statement statement = Util.getConnection().createStatement();
            ResultSet resultset= statement.executeQuery(createUserTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
