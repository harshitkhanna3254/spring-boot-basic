package com.hk.restfulwebservice.service;

import com.hk.restfulwebservice.constants.Constants;
import com.hk.restfulwebservice.exception.types.UserNotFoundException;
import com.hk.restfulwebservice.model.User;
import com.hk.restfulwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static List<User> users = new ArrayList<>();
    private static Integer userCount = 3;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findOne(Integer id) throws UserNotFoundException {

        Optional<User> retrievedUser = userRepository.findById(id);

        if (!retrievedUser.isPresent())
            throw new UserNotFoundException(Constants.USER_NOT_FOUND);

        return retrievedUser.get();
    }

    public User deleteById(Integer id) throws UserNotFoundException {

        User retrievedUser = findOne(id);

        userRepository.deleteById(id);

        return retrievedUser;
    }

}
