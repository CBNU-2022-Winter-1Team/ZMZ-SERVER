package com.cbnu.zmz.controller;

import com.cbnu.zmz.dto.NoticeDTO;
import com.cbnu.zmz.entity.Notice;
import com.cbnu.zmz.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/noticeList")
    public ResponseEntity<List<NoticeDTO>>notice_list(String user_id){
        log.info("============notice_list==============");
        log.info(user_id);
        return new ResponseEntity<>(noticeService.callNoticeList(user_id), HttpStatus.OK);
    }
}
