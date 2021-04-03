package org.fiipractic.service;

import org.fiipractic.exception.NotFoundException;
import org.fiipractic.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    private final List<User> users = new ArrayList<>();

    public void create(User userFromRq) {
        User user = new User();

        user.setId((long) users.size() + 1);
        user.setUserName(userFromRq.getUserName());
        user.setFirstName(userFromRq.getFirstName());
        user.setLastName(userFromRq.getLastName());
        user.setEmail(userFromRq.getEmail());
        user.setPass(userFromRq.getPass());

        users.add(user);
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

    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("user", id));
    }

}
