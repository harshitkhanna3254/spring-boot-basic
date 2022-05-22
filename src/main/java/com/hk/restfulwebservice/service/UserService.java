package com.hk.restfulwebservice.service;

import com.hk.restfulwebservice.constants.Constants;
import com.hk.restfulwebservice.exception.types.UserAlreadyPresentException;
import com.hk.restfulwebservice.exception.types.UserNotFoundException;
import com.hk.restfulwebservice.model.User;
import com.hk.restfulwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static List<User> users = new ArrayList<>();
    private static Integer userCount = 3;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "ABC", new Date()));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public User findOne(Integer id) throws UserNotFoundException {
//        User retrievedUser = users.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .orElse(null);

        Optional<User> retrievedUser = userRepository.findById(id);

        if (!retrievedUser.isPresent())
            throw new UserNotFoundException(Constants.USER_NOT_FOUND);

        return retrievedUser.get();
    }

    public User deleteById(Integer id) throws UserNotFoundException {

        User retrievedUser = findOne(id);

        userRepository.delete(retrievedUser);

        return retrievedUser;
    }

}
