package com.cbnu.zmz.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="chat")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long chat_id;

    @Column(length = 500, nullable = false)
    String chat_content;

    @Column(nullable = false)
    int chat_read;

    @ManyToOne
    @JoinColumn(name="friend_id", columnDefinition = "VARCHAR(50)", nullable = false)
    User friend_id;

    @ManyToOne
    @JoinColumn(name="user_id", columnDefinition = "VARCHAR(50)", nullable = false)
    User user_id;

    @Column
    LocalDateTime chat_send;
}
