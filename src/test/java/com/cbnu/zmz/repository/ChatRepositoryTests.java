package com.cbnu.zmz.repository;

import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.entity.Chat;
import com.cbnu.zmz.entity.User;
import com.cbnu.zmz.entity.UserAuthority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ChatRepositoryTests {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testClass(){
        System.out.println(chatRepository.getClass().getName());
//        결과 $Proxy108  왜냐면 해당 객체에 클래스 이름이 없기 때문에 $가 붙어서 나옴
    }
    @Test
    public void testInsertDummy(){
        StatusDTO statusDTO = new StatusDTO();
        User user = userRepository.findById("test...50").get();
        User friend_id = userRepository.findById("test...1").get();


        Chat chat = Chat.builder()
                .chat_content("수고하셨습니다.")
                .friend_id(friend_id)
                .chat_read(1)
                .chat_send(LocalDateTime.now())
                .user_id(user).build();
        chatRepository.save(chat);
    }
}
