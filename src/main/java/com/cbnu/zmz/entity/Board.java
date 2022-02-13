package com.cbnu.zmz.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table(name="Board")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @Column(nullable = false)
    Long post_id;

    @Column(length = 2000, nullable = false)
    String post_content;

    @Column(length = 20, nullable = true)
    String post_title;

    @Column(nullable = false)
    Boolean is_secret;

    @Column(nullable = false)
    LocalDateTime post_time;

    @Column(nullable = true)
    Long post_comment;

    @Column(nullable = true)
    Long post_like;

    @Column(nullable = true)
    Long post_view;

    @ManyToOne//게시글은 오직 하나의 사용자가 있다
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User user;

}
