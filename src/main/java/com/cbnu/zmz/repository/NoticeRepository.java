package com.cbnu.zmz.repository;


import com.cbnu.zmz.entity.Notice;
import com.cbnu.zmz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice,Long>{
    @Query(value = "select * from Notice n where n.user_id = :user_id",nativeQuery = true)
    List<Notice> findByUserWithNotice(@Param("user_id") User user);
}
