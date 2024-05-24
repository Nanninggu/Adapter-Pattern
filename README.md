## Adapter-Pattern에 대해서 정리하기 😂

### **0\. 이 프로젝트에 대한 주요 설명은 아래와 같다.**

이 프로젝트는 어댑터 패턴을 사용하여 UserDaoImpl과 UserMapper의 인터페이스를 통일시키는 것을 목표로 하고 있다. 이를 통해 나머지 애플리케이션은 UserDaoImpl이나 UserMapper에 대해 알 필요 없이 사용자 정보를 가져올 수 있다. 이는 어댑터 패턴의 주요 목표인 인터페이스의 호환성을 달성하는 것이다.

### **1\. 이프로젝트에서 어떻게 구현 되었나?**

이 프로젝트에서의 어댑터 패턴은 UserDaoImpl 클래스와 UserMapper 인터페이스 사이에 적용되었다.  
UserDaoImpl 클래스는 UserDao 인터페이스를 구현하며, getUser 메소드를 통해 사용자 정보를 가져온다.

```
public class UserDaoImpl implements UserDao {
    public List<User> getUser(String id) {
        // Implementation here
        return null;
    }
}
```

UserMapper 인터페이스는 MyBatis 매퍼로, 데이터베이스와의 상호작용을 담당한다.

getUser 메소드를 통해 사용자 정보를 가져온다.

```
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    List<User> getUser(String id);
}
```

이 두 클래스/인터페이스는 같은 기능을 수행하지만, 인터페이스가 다르다. 이를 해결하기 위해 어댑터 패턴을 사용할 수 있다. 어댑터 클래스는 UserDaoImpl 클래스와 UserMapper 인터페이스 사이에 위치하며, 두 인터페이스를 통일시킨다. 이 어댑터 클래스는 아래와 같이 구현될 수 있다.

```
public class UserDaoAdapter implements UserDao {
    private final UserDaoImpl userDaoImpl;
    private final UserMapper userMapper;

    public UserDaoAdapter(UserDaoImpl userDaoImpl, UserMapper userMapper) {
        this.userDaoImpl = userDaoImpl;
        this.userMapper = userMapper;
    }

    public List<User> getUser(String id) {
        // Here you can decide whether to use userDaoImpl or userMapper
        // based on your specific requirements.
    }
}
```

이 예제에서는 getUser 메소드가 applicationIsOnline 메소드를 호출하여 애플리케이션이 온라인인지 확인한다.

애플리케이션이 온라인 상태라면 userMapper를 사용하여 사용자 정보를 가져오고, 오프라인 상태라면 userDaoImpl을 사용한다. applicationIsOnline 메소드는 애플리케이션이 온라인인지 오프라인인지를 확인하는 로직으로 구현되어야 한다.

```
public class UserDaoAdapter implements UserDao {
    private final UserDaoImpl userDaoImpl;
    private final UserMapper userMapper;

    public UserDaoAdapter(UserDaoImpl userDaoImpl, UserMapper userMapper) {
        this.userDaoImpl = userDaoImpl;
        this.userMapper = userMapper;
    }

    public List<User> getUser(String id) {
        if (applicationIsOnline()) {
            return userMapper.getUser(id);
        } else {
            return userDaoImpl.getUser(id);
        }
    }

    private boolean applicationIsOnline() {
        // Implement your logic to check if the application is online or offline
        
        return true; // This is a placeholder
    }
}
```

### **2\. 핵심 로직 설명**

UserDaoAdapter 클래스는 UserDao 인터페이스를 구현하며, UserDaoImpl과 UserMapper를 사용하여 사용자 정보를 가져오는 역할을 한다. 이 클래스는 어플리케이션이 온라인 상태인지 오프라인 상태인지를 판단하고, 그에 따라 원격 데이터를 사용하거나 로컬 데이터를 사용할지 결정한다.  
\- 클래스의 주요 구성 요소는 다음과 같다.
**UserDaoImpl userDaoImpl:** UserDaoImpl 인스턴스를 저장하는 필드이다. 이 필드는 생성자를 통해 주입된다.  
**UserMapper userMapper:** UserMapper 인스턴스를 저장하는 필드이다. 이 필드는 생성자를 통해 주입된다.  
**UserDaoAdapter(UserDaoImpl userDaoImpl,UserMapperuserMapper):** UserDaoAdapter의 생성자이다. UserDaoImpl과 UserMapper 인스턴스를 받아서 내부 필드에 저장한다.  
**List<User> getUser(String id ):** 주어진 ID를 가진 사용자의 정보를 가져오는 메소드이다. 어플리케이션이 온라인 상태라면 UserMapper를 사용하여 원격 데이터를 가져오고, 오프라인 상태라면 UserDaoImpl을 사용하여 로컬 데이터를 가져온다.
**boolean applicationIsOnline():** 어플리케이션이 온라인 상태인지 판단하는 메소드이다. 소켓을 사용하여 특정 주소([http://www.google.com)에](http://www.google.com)에) 연결을 시도하고, 연결이 성공하면 어플리케이션이 온라인 상태라고 판단한다. 연결이 실패하면 어플리케이션이 오프라인 상태라고 판단한다.

끝
