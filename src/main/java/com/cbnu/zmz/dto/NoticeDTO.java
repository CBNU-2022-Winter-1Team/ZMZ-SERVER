package com.cbnu.zmz.dto;

import com.cbnu.zmz.entity.Notice_Kinds;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
    private Long notice_id;
    private String user_id;
    private int notice_status;
    private String notice_content;
    private Set<Notice_Kinds> noticeSet = new HashSet<>();
    private String sender_id;
    private int friend_status;
}
