package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.Friend;
import com.cbnu.zmz.entity.FriendStatus;
import com.cbnu.zmz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, String> {
    @Query("select f from Friend f where f.user_id = :user_id")
    List<Friend> findByUserWithFollowing(@Param("user_id") Optional<User> user);

    @Query("select f from Friend f where f.friend_id = :user_id")
    List<Friend> findByUserWithFollower(@Param("user_id") User user);

    @Query("select f from Friend f where f.user_id = :user_id AND f.friend_id = :friend_id")
    Optional<Friend> friendIsPresent(@Param("user_id") User user_id, @Param("friend_id") User friend_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Friend f set f.friend_status = :friend_num  where f.user_id = :user_id AND f.friend_id = :friend_id", nativeQuery = true)
    int updateFriend_status(@Param("user_id") User user, @Param("friend_id") User friend, @Param("friend_num") FriendStatus friendStatus) throws Exception;



}
