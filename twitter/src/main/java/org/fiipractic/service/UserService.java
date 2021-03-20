package org.fiipractic.service;

import org.fiipractic.model.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    private final List<User> users = new ArrayList<>();

    public void create(User userFromRq) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/test-spring";
        String usernameConnection = "root";
        String passwordConnection = "root";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, usernameConnection, passwordConnection);
            Statement myStatement = connection.createStatement();
            myStatement.executeUpdate("insert into user" + "(id, userName, firstName, lastName, email, pass)");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }

    }

    public List<User> getAll() {
        return users;
    }

    public User findByUserName(String userName) {
        return users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
    }

}
