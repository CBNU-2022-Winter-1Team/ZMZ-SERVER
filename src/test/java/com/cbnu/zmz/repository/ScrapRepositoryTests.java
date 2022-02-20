package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.Board;
import com.cbnu.zmz.entity.Report;
import com.cbnu.zmz.entity.Scrap;
import com.cbnu.zmz.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ScrapRepositoryTests {
    @Autowired
    ScrapRepository scrapRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;
    @Test
    public void testClass(){
        System.out.println(scrapRepository.getClass().getName());
    }

    @Test
    public void testInsertDummy(){
        Optional<User> result1 = userRepository.findById("test...1");
        User user = result1.get();
        Optional<Board> result2 = boardRepository.findById("1");
        Board board = result2.get();
        IntStream.rangeClosed(1,100).forEach((i -> {
            Scrap scrap = Scrap.builder()
                    .user(user)
                    .board(board)
                    .scrap_id((long)i)
                    .build();
            scrapRepository.save(scrap);
        }));
    }
}
