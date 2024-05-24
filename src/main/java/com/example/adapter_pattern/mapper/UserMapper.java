package com.example.adapter_pattern.mapper;

import com.example.adapter_pattern.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserMapper 인터페이스: MyBatis 매퍼로, 데이터베이스와의 상호작용을 담당한다.
 * getUser 메소드를 통해 사용자 정보를 가져온다.
 */

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    List<User> getUser(String id);

}
