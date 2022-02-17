package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.Notice;
import com.cbnu.zmz.entity.Notice_Kinds;
import com.cbnu.zmz.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class NoticeRepositoryTests {
    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testClass() {
        System.out.println(noticeRepository.getClass().getName());
    }

    @Test
    public void testInsertDummy() {
        Optional<User> result = userRepository.findById("test...1");
        User user = result.get();

        IntStream.rangeClosed(1, 100).forEach((i -> {

            Notice notice = Notice.builder()
                    .user(user)
                    .notice_id((long) i)
                    .notice_content("this is notice " + i)
                    .notice_status(Boolean.TRUE)
                    .build();
            notice.addNoticeReason(Notice_Kinds.NONE);
            noticeRepository.save(notice);
        }));
    }
}
