package org.fiipractic.service;

<<<<<<< HEAD
import org.fiipractic.exception.NotFoundException;
import org.fiipractic.model.User;
import org.springframework.stereotype.Component;

=======
import org.fiipractic.model.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    private final List<User> users = new ArrayList<>();

    public void create(User userFromRq) {
<<<<<<< HEAD
        User user = new User();

        user.setId((long) users.size() + 1);
        user.setUserName(userFromRq.getUserName());
        user.setFirstName(userFromRq.getFirstName());
        user.setLastName(userFromRq.getLastName());
        user.setEmail(userFromRq.getEmail());
        user.setPass(userFromRq.getPass());

        users.add(user);
=======
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

>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
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

<<<<<<< HEAD
    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("user", id));
    }

=======
>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
}
