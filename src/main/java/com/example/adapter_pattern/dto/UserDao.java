package com.example.adapter_pattern.dto;

import java.util.List;

/**
 * UserDao의 역할은 데이터베이스 액세스 메소드를 정의하고,
 * 이를 구현하는 클래스에게 이 메소드의 구현을 위임하는 것입니다.
 */

public interface UserDao {
    List<User> getUser(String id);

}

