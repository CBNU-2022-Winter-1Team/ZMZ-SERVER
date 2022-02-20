//package com.cbnu.zmz.repository;
//
//import com.cbnu.zmz.entity.Board;
//import com.cbnu.zmz.entity.Picture;
//import com.cbnu.zmz.entity.User;
//import com.cbnu.zmz.entity.UserAuthority;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//public class PictureRepositoryTests {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    PictureRepository pictureRepository;
//
//    @Autowired
//    BoardRepository boardRepository;
//
//    @Test
//    public void testClass(){
//        System.out.println(pictureRepository.getClass().getName());
//    }
//
//    @Test
//    public void testInsertDummy(){
//
//        Optional<User> result1 = userRepository.findById("test...1");
//
//        User user = result1.get();
//
//        Optional<Board> result2 = boardRepository.findById(2L);
//
//        Board board = result2.get();
//
//
//        Picture picture = Picture.builder()
//                    .picture_id("test...3" )
//                    .picture_path("ccc")
//                    .picture_name("picture1")
//                    .board(board)
//                    .build();
//
//            pictureRepository.save(picture);
//    }
//}
