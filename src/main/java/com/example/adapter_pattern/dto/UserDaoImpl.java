package com.example.adapter_pattern.dto;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserDaoImpl 클래스: UserDao 인터페이스를 구현하는 클래스이다.
 * getUser 메소드를 통해 사용자 정보를 가져온다.
 */

@Component
public class UserDaoImpl implements UserDao {
    public List<User> getUser(String id) {
        List<User> value = List.of(new User("ERROR", "ERROR-NUM-001", "CALL = may9noy@gmail.com"));

        return value;
    }

}
