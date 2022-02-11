package com.cbnu.zmz.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="user")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(length = 50)
    String user_id;

    @Column(length = 30, nullable = false)
    String user_name;

    @Column(length = 50, nullable = false)
    String user_pw;

    @Column(nullable = true)
    Date user_birth;

    @Column(length = 50, nullable = true)
    String user_addr;

    @Column(length = 50, nullable = false)
    String user_mail;

    @Column(length = 500, nullable = true)
    String user_text;

    @Column(columnDefinition = "integer default 1 not null")
    int authority_id;

//    @Column(length = 30, nullable = false)
//    String user_picture;
}