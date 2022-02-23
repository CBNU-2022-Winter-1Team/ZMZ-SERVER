package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.Anonymous;
import com.cbnu.zmz.entity.Friend;
import com.cbnu.zmz.entity.FriendStatus;
import com.cbnu.zmz.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class FriendRepositoryTests {
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendStatusRepository friendStatusRepository;

    @Test
    public void testClass(){
        System.out.println(friendRepository.getClass().getName());
//        결과 $Proxy108  왜냐면 해당 객체에 클래스 이름이 없기 때문에 $가 붙어서 나옴
    }

    @Test
    public void testInsertDummy(){

        Optional<User> result1 = userRepository.findById("test...1");
        Optional<User> result2 = userRepository.findById("test...50");
        Optional<FriendStatus> result3 = friendStatusRepository.findById(1);

        User user_id = result1.get();
        User friend_id = result2.get();
        FriendStatus friendStatus = result3.get();

        Friend friend = Friend.builder()
                        .user_id(user_id)
                        .friend_id(friend_id)
                      .friend_num(friendStatus)
                      .build();
        friendRepository.save(friend);
    }
}
