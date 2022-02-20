package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.Board;
import com.cbnu.zmz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, String> {
//    @Query("select b from Board b where b.user_id=:user_id")
//    List<Board> findBoardListByUser(@Param("user_id") User user);
//
//    @Query("select b from Board b where b.post_id=:post_id")
//    List<Board> findBoardById(@Param("post_id") Board);

//    @Query("select b from Board b where b.user_id = ?1")
//    List<Board> findBoardListByUser(String user_id);

    @Query("select b from Board b where b.user_id = ?1")
    List<Board> findBoardListByUser(@Param("user_id") User user);

    @Query("select b from Board b where b.post_id = ?1")
    Optional<Board> findBoardById(Long post_id);

}
