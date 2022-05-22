package com.hk.restfulwebservice.controller;

import com.hk.restfulwebservice.model.User;
import com.hk.restfulwebservice.repository.UserRepository;
import com.hk.restfulwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/users")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User findUser(@PathVariable Integer id) {
        return userService.findOne(id);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        User deletedUser = userService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedUser);
    }

}
