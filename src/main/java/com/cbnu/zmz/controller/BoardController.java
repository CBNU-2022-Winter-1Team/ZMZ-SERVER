package com.cbnu.zmz.controller;

import com.cbnu.zmz.dto.BoardDTO;
import com.cbnu.zmz.dto.FriendDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.dto.UserDTO;
import com.cbnu.zmz.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

//    @GetMapping("/list")
//    public ResponseEntity<BoardDTO> list(String user_id) {
//        log.info("----------list----------");
//
//        return new ResponseEntity<>(boardService.list(user_id), HttpStatus.OK);
//    }

    @GetMapping("/list")
    public List<BoardDTO> list(@AuthenticationPrincipal String user_id) {
        log.info("-----------list-----------");

        List<BoardDTO> boardDTO = boardService.list(user_id);

        return boardDTO;
    }

    @PostMapping("/register")
    public ResponseEntity<StatusDTO> register(@AuthenticationPrincipal String user_id, @RequestBody BoardDTO boardDTO) {
        log.info("----------register----------");

        return new ResponseEntity<>(boardService.register(user_id, boardDTO), HttpStatus.OK);
    }

    @PostMapping("/read")
    public ResponseEntity<BoardDTO> read(@RequestBody BoardDTO boardDTO) {
        log.info("----------read----------");

        return new ResponseEntity<>(boardService.read(boardDTO), HttpStatus.OK);
    }

    @PostMapping("/modify")
    public ResponseEntity<StatusDTO> modify(@AuthenticationPrincipal String user_id, @RequestBody BoardDTO boardDTO) {
        log.info("----------modify----------");

        return new ResponseEntity<>(boardService.modify(boardDTO, user_id), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<StatusDTO> delete(Long post_id) {
        log.info("----------delete----------");

        return new ResponseEntity<>(boardService.delete(post_id), HttpStatus.OK);
    }

    @GetMapping("/bookList")
    public ResponseEntity<List<BoardDTO>> bookList(@AuthenticationPrincipal String user_id) {
        log.info("----------bookList----------");

        return new ResponseEntity<>(boardService.bookList(user_id), HttpStatus.OK);
    }

    @PostMapping("/bookAdd")
    public ResponseEntity<StatusDTO> bookAdd(@AuthenticationPrincipal String user_id, @RequestBody BoardDTO boardDTO) {
        log.info("----------bookAdd----------");

        return new ResponseEntity<>(boardService.bookAdd(user_id, boardDTO), HttpStatus.OK);
    }

    @PostMapping("/comment/add")
    public ResponseEntity<StatusDTO>commentAdd(@AuthenticationPrincipal String user_id, @RequestBody BoardDTO boardDTO){
        log.info("====================commentAdd===============");

        return new ResponseEntity<>(boardService.commentAdd(user_id, boardDTO), HttpStatus.OK);
    }

    @GetMapping("/comment/list")
    public ResponseEntity<List<BoardDTO>>commentList(@AuthenticationPrincipal String user_id, @RequestBody BoardDTO boardDTO){
        log.info("====================commentList===============");

        return new ResponseEntity<>(boardService.commentList(boardDTO), HttpStatus.OK);
    }
//
//    @GetMapping("/commentList")
//    public ResponseEntity<BoardDTO> commentList(Long user_id) {
//        log.info("----------commentList----------");
//
//        return new ResponseEntity<>(boardService.commentList(user_id), HttpStatus.OK);
//    }
//

}