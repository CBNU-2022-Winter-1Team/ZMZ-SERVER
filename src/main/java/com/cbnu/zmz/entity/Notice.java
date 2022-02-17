package com.cbnu.zmz.entity;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "notice")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long notice_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(length = 1, nullable = false)
    Integer notice_status;
    @Column(length = 100, nullable = true)
    String notice_content;
    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Notice_Kinds> notice_reason = new HashSet<>();
    public void addNoticeReason(Notice_Kinds notice_kinds) {
        notice_reason.add(notice_kinds);
    }
}
