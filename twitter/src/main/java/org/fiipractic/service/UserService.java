package org.fiipractic.service;

import org.fiipractic.service.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    private final List<User> users = new ArrayList<>();
    //sau user Dao care face legatura cu baza de date

    public List<User> getAllUsers(){
        return users;
    }

    public Long createUser(User userFromRequest){
        User user = new User();
        user.setId((long)users.size() + 1);
        user.setUserName(userFromRequest.getUserName());
        user.setFirstName(userFromRequest.getFirstName());
        user.setLastName(userFromRequest.getLastName());
        user.setEmail(userFromRequest.getEmail());
        user.setPassword(userFromRequest.getPassword());

        users.add(user);
        return user.getId();
    }

}
