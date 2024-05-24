package com.example.adapter_pattern.adapter;

import com.example.adapter_pattern.dto.User;
import com.example.adapter_pattern.dto.UserDao;
import com.example.adapter_pattern.dto.UserDaoImpl;
import com.example.adapter_pattern.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;


/**
 * UserDaoAdapter 클래스: UserDao를 구현하는 클래스이다.
 * getUser 메소드를 통해 사용자 정보를 가져온다.
 * ----------------------------------------------------------------------------
 * List<User> getUser(String id): 주어진 ID를 가진 사용자의 정보를 가져오는 메소드이다.
 * 어플리케이션이 온라인 상태라면 UserMapper를 사용하여 원격 데이터를 가져오고,
 * 오프라인 상태라면 UserDaoImpl을 사용하여 로컬 데이터를 가져온다.
 */

@Component
public class UserDaoAdapter implements UserDao {
    private final UserDaoImpl userDaoImpl;
    private final UserMapper userMapper;

    public UserDaoAdapter(UserDaoImpl userDaoImpl, UserMapper userMapper) {
        this.userDaoImpl = userDaoImpl;
        this.userMapper = userMapper;
    }

    // core logic
    public List<User> getUser(String id) {
        if (applicationIsOnline()) {
            System.out.println("Application is online. Using remote data.");
            return userMapper.getUser(id);
        } else {
            System.out.println("Application is offline. Using local data.");
            return userDaoImpl.getUser(id);
        }
    }

    private boolean applicationIsOnline() {
        Socket socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress("www.google.com", 80); // 80은 정상호출, 81은 비정상호출
        try {
            socket.connect(socketAddress, 2000);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // Ignore issues during closing
            }
        }
    }

}
