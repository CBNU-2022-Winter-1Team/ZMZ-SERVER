package com.cbnu.zmz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
    private Long post_id;

    private String post_content;

    private String post_title;

    private Boolean is_secret;

    private LocalDateTime post_time;

    private Long post_comment;

    private Long post_view;

    private Long post_like;

    private String user_id;

    private String picture_id;

    private Long comment_id;

    private String comment_text;

    private LocalDateTime comment_date;

    private LocalDateTime comment_update;

    private Long comment_id2;
}
