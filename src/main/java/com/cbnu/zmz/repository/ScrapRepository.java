package com.cbnu.zmz.repository;

import com.cbnu.zmz.entity.Scrap;
import com.cbnu.zmz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long>{

    @Query(value = "select * from Scrap s where s.user_id = :user_id", nativeQuery = true)
    List<Scrap> findByUserId(@Param("user_id") User user);
}
