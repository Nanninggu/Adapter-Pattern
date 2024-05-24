package com.example.adapter_pattern.service;

import com.example.adapter_pattern.adapter.UserDaoAdapter;
import com.example.adapter_pattern.dto.User;
import com.example.adapter_pattern.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService 클래스: UserDaoAdapter를 사용하여
 * 사용자 정보를 가져오는 서비스입니다.
 */

@Service
public class UserService {
    private final UserDaoAdapter userDaoAdapter;

    @Autowired
    public UserService(UserDaoAdapter userDaoAdapter) {
        this.userDaoAdapter = userDaoAdapter;
    }

    public List<User> getUser(String id) {
        return userDaoAdapter.getUser(id);
    }

}
