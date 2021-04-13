package org.fiipractic.service;

import org.fiipractic.exception.NotFoundException;
import org.fiipractic.model.User;
import org.fiipractic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    //private final List<User> users = new ArrayList<>();
    @Autowired
    private UserRepository userRepository;

    public Long create(User userFromRq) {
        User user = new User();
        //user.setId((long) users.size() + 1);
        user.setUserName(userFromRq.getUserName());
        user.setFirstName(userFromRq.getFirstName());
        user.setLastName(userFromRq.getLastName());
        user.setEmail(userFromRq.getEmail());
        user.setPass(userFromRq.getPass());

        userRepository.save(user);
        return user.getId();
    }

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll()
            .forEach(list::add);
        return list;
    }

    public User findByUserName(String userName) {
        return getAll().stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public User findById(Long id) {
        return getAll().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("user", id));
    }

    public Long addFolloweeForUser(Long userId, Long followeeId){
        User user = findById(userId);

        user.addFollowee(followeeId);
        userRepository.save(user);

        return followeeId;
    }

    public ArrayList<Long> getFolloweesForUser(Long userId){
        User user = findById(userId);

        return user.getFollowing();
    }

}
