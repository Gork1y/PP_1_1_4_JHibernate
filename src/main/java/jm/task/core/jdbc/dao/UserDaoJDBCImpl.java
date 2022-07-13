package jm.task.core.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
@Transactional
public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String query = """
            CREATE TABLE if not exists users (
            	id bigint auto_increment NOT NULL,
            	firstname varchar(100) NOT NULL,
            	lastname varchar(100) NULL,
            	age int NOT NULL,
            	primary key (id)
            );
                """;
        try (Statement statement = Util.getConnection().createStatement()) {
            Util.getConnection().setAutoCommit(false);  //НО ВОТ НАХ оно это здесь??)))))
            statement.execute(query);
            Util.getConnection().commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Util.getConnection().rollback();
                System.out.println("JDBC Transaction rolled back successfully");
            } catch (SQLException e1) {
                System.out.println("SQLException in rollback" + e.getMessage());
            }
        }
    }

    public void dropUsersTable() {
        String dropTableQuery = "DROP TABLE IF EXISTS users";
        try (Statement statement = Util.getConnection().createStatement()) {
            Util.getConnection().setAutoCommit(false); //НО ВОТ НАХ оно это здесь??)))))
            statement.execute(dropTableQuery);
            Util.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Util.getConnection().rollback();
                System.out.println("JDBC Transaction rolled back successfully");
            } catch (SQLException e1) {
                System.out.println("SQLException in rollback" + e.getMessage());
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUserQuery = "INSERT INTO users (firstname,lastname,age) VALUES (?,?,?) ";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(saveUserQuery)) {
            Util.getConnection().setAutoCommit(false); //НО ВОТ НАХ оно это здесь??)))))
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User with name " + name + " added to db");
            Util.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Util.getConnection().rollback();
                System.out.println("JDBC Transaction rolled back successfully");
            } catch (SQLException e1) {
                System.out.println("SQLException in rollback" + e.getMessage());
            }
        }
    }

    public void removeUserById(long id) {
        String removeUserByIdQuery = "DELETE FROM users where id = ?";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(removeUserByIdQuery)) {
            Util.getConnection().setAutoCommit(false); //НО ВОТ НАХ оно это здесь??)))))
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            Util.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Util.getConnection().rollback();
                System.out.println("JDBC Transaction rolled back successfully");
            } catch (SQLException e1) {
                System.out.println("SQLException in rollback" + e.getMessage());
            }
        }
    }

    public List<User> getAllUsers() {
        String getAllUsersQuery = "SELECT * from users";
        List<User> users = new ArrayList<>();

        try (Statement statement = Util.getConnection().createStatement()) {
            Util.getConnection().setAutoCommit(false); //НО ВОТ НАХ оно это здесь??)))))
            ResultSet resultSet = statement.executeQuery(getAllUsersQuery);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            Util.getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Util.getConnection().rollback();
                System.out.println("JDBC Transaction rolled back successfully");
            } catch (SQLException e1) {
                System.out.println("SQLException in rollback" + e.getMessage());
            }
        }
        return users;
    }


    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            Util.getConnection().setAutoCommit(false); //НО ВОТ НАХ оно это здесь??)))))
            statement.execute("TRUNCATE table users;");
            Util.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Util.getConnection().rollback();
                System.out.println("JDBC Transaction rolled back successfully");
            } catch (SQLException e1) {
                System.out.println("SQLException in rollback" + e.getMessage());
            }
        }
    }
}
