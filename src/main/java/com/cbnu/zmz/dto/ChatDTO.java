package com.cbnu.zmz.dto;

import com.cbnu.zmz.entity.Friend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private String user_id;
    private String friend_id;
    private int chat_read;
    private LocalDateTime chat_send;

    private String chat_content;
}
