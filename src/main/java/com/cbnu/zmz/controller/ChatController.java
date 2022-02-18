package com.cbnu.zmz.controller;

import com.cbnu.zmz.dto.ChatDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chat")
public class ChatController {
    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatListService){
        this.chatService = chatListService;
    }

    @GetMapping("list/{user_id}")
    public  Object chatlist(@PathVariable String user_id){
        log.info("----------------chat/list-----------------------");

        System.out.println("user_id : " + user_id);
        List<ChatDTO> chatDTO = chatService.getChatList(user_id);
        return chatDTO;
    }

    @PostMapping("insert")
    public ResponseEntity<StatusDTO> chatInsert(@RequestBody ChatDTO chatDTO){
        log.info("----------------chat/insert-----------------------");

        return new ResponseEntity<StatusDTO>(chatService.insert(chatDTO), HttpStatus.OK);
    }

    @GetMapping("list/{user_id}/{friend_id}")
    public  Object getChating(@PathVariable String user_id, @PathVariable String friend_id){
        log.info("----------------chat/with-----------------------");
        List<ChatDTO> chatDTO = chatService.getChatting(user_id, friend_id);
        return chatDTO;
    }
}
