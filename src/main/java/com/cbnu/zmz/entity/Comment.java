package com.cbnu.zmz.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Comment")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long comment_id;

    @Column(length = 100, nullable = false)
    String comment_text;

    @Column(nullable = false)
    LocalDateTime comment_date;

    @Column(length = 50)
    String anonymouseName;

    @Column(columnDefinition = "boolean default false")
    boolean isAnonymouse;

    @ManyToOne//글은 오직 하나의 사용자가 있다. 하나의 사용자는 여러개의 글을 쓸 수 있다
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User user;

    @ManyToOne//대글은 오직 하나의 (기본)게시글이 있다. 하나의 (기본)게시글에는 여러개의 댓글이 있을 수 있다
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    Board board;

}
