package com.cbnu.zmz.service;

import com.cbnu.zmz.dto.NoticeDTO;
import com.cbnu.zmz.entity.Notice;
import com.cbnu.zmz.entity.User;

import java.util.List;

public interface NoticeService {
    List<NoticeDTO>callNoticeList(String user_id);

    default Notice dtoToEntity(NoticeDTO noticeDTO){
        Notice notice = Notice.builder()
                .notice_id(noticeDTO.getNotice_id())
                .user(User.builder().user_id(noticeDTO.getUser_id()).build())
                .notice_status(noticeDTO.getNotice_status())
                .notice_content(noticeDTO.getNotice_content())
                .notice_reason(noticeDTO.getNoticeSet())
                .build();
        return notice;
    }
    default NoticeDTO entityToDTO(Notice notice){
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .notice_id(notice.getNotice_id())
                .user_id(notice.getUser().getUser_id())
                .notice_status(notice.getNotice_status())
                .notice_content(notice.getNotice_content())
                .noticeSet(notice.getNotice_reason())
                .build();
        return noticeDTO;
    }
}
