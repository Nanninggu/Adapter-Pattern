package com.example.adapter_pattern.controller;

import com.example.adapter_pattern.adapter.UserDaoAdapter;
import com.example.adapter_pattern.dto.User;
import com.example.adapter_pattern.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserMapper userMapper;

    @Autowired
    private UserDaoAdapter userDaoAdapter;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/users/info/{id}")
    public List<User> getUserWithId(@PathVariable String id) {
        return userMapper.getUser(id);
    }

    @GetMapping("/users/{id}")
    public List<User> getUser(@PathVariable String id) {
        return userDaoAdapter.getUser(id);
    }

}
