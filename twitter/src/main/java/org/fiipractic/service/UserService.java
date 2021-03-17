package org.fiipractic.service;

<<<<<<< HEAD
import org.fiipractic.service.model.User;
=======
import org.fiipractic.model.User;
>>>>>>> 7599a82f498fdc5910039beeb994da54ea1e1898
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    private final List<User> users = new ArrayList<>();
<<<<<<< HEAD
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
=======

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
>>>>>>> 7599a82f498fdc5910039beeb994da54ea1e1898
    }

}
