package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static java.time.LocalDateTime.*;


@SpringBootTest
public class CommentRepositoryTests {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void testClass() {

        System.out.println(commentRepository.getClass().getName());
    }

    @Test
    public void testInsertDummy(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Comment comment = Comment.builder()
                    .comment_id((long)i)
                    .comment_text("text..." + i)
                    .comment_date(now())

                    .build();
            commentRepository.save(comment);
        });
    }
}

