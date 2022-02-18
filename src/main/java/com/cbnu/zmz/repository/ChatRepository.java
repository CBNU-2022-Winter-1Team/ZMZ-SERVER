package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.Chat;
import com.cbnu.zmz.entity.Friend;
import com.cbnu.zmz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {


    @Query("select c from Chat c where c.user_id = :user_id")
    List<Chat> findChatListbyID(@Param("user_id") User user);

    @Query("select c from Chat c where (c.user_id = :user_id AND c.friend_id = :friend_id) OR (c.user_id = :friend_id AND c.friend_id = :user_id)")
    List<Chat> findChatListbyID(@Param("user_id") User user, @Param("friend_id") User friend);
}
