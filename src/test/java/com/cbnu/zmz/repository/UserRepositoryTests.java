package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.User;
import com.cbnu.zmz.entity.UserAuthority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testClass(){
        System.out.println(userRepository.getClass().getName());
    }

    @Test
    public void testInsertDummy(){
        IntStream.rangeClosed(111, 120).forEach(i ->{
            User user = User.builder()
                    .user_id("test..." + i)
                    .user_name("name"+i)
                    .user_pw("1234")
                    .build();
            user.addMemberRole(UserAuthority.USER);
            userRepository.save(user);
        });
    }

    @Test
    public void insertDummies() {
        //1 - 80까지는 USER만 지정
        //81- 90까지는 USER,MANAGER
        //91- 100까지는 USER,MANAGER,ADMIN

        IntStream.rangeClosed(1,100).forEach(i -> {
            User user = User.builder()
                    .user_id("test..." + i)
                    .user_name("name"+i)
                    .user_pw(passwordEncoder.encode("1111"))
                    .build();

//            {
//                "user_id" : "test...151",
//                    "user_pw" : "1111",
//                    "user_name" : "jw",
//                    "user_mail" : "dj@gmail.com"
//            }
//default role
            user.addMemberRole(UserAuthority.USER);

            if(i > 80){
                user.addMemberRole(UserAuthority.MANAGER);
            }

            if(i > 90){
                user.addMemberRole(UserAuthority.ADMIN);
            }
            userRepository.save(user);

        });
    }
}