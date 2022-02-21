package com.cbnu.zmz.repository;

import com.cbnu.zmz.dto.BoardDTO;
import com.cbnu.zmz.entity.Board;
import com.cbnu.zmz.entity.Comment;
import com.cbnu.zmz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from Comment c where c.post_id = :post_id", nativeQuery = true)
    List<Comment>findByPostId(@Param("post_id") Board board);

}
