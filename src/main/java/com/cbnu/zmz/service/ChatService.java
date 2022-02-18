package com.cbnu.zmz.service;

import com.cbnu.zmz.dto.ChatDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.entity.Chat;
import com.cbnu.zmz.entity.Friend;
import com.cbnu.zmz.entity.User;
import com.cbnu.zmz.repository.ChatRepository;
import com.cbnu.zmz.repository.FriendRepository;
import com.cbnu.zmz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final ChatRepository chatRepository;

    public List<ChatDTO> getChatList(String user_id){
        List<ChatDTO> list = new ArrayList<>();

        User user = User.builder()
                        .user_id(user_id).build();

        log.info(user);

        List<Chat> result = chatRepository.findChatListbyID(user);
        System.out.println(result);
        result.forEach(item -> {
            ChatDTO chatListDTO =new ChatDTO();
            chatListDTO.setUser_id(item.getUser_id().getUser_id());
            chatListDTO.setFriend_id(item.getFriend_id().getUser_id());
            chatListDTO.setChat_send(item.getChat_send());
            chatListDTO.setChat_read(item.getChat_read());
            chatListDTO.setChat_content(item.getChat_content());
            list.add(chatListDTO);
        });


        return list;
    }

    public StatusDTO insert(ChatDTO chatDTO) {
        StatusDTO statusDTO = new StatusDTO();
        User user_id = User.builder().user_id(chatDTO.getUser_id()).build();
        User friend_id = User.builder().user_id(chatDTO.getFriend_id()).build();

        Chat chat = Chat.builder()
                        .chat_content(chatDTO.getChat_content())
                        .friend_id(friend_id)
                .chat_read(0)
                        .chat_send(LocalDateTime.now())
                        .user_id(user_id).build();
        chatRepository.save(chat);

        statusDTO.setSuccess(true);
        statusDTO.setMessage("채팅 보내기 성공");
        statusDTO.setStatus(200);

        return statusDTO;
    }

    public List<ChatDTO> getChatting(String user_id, String friend_id) {
        List<ChatDTO> list = new ArrayList<>();

        User user = User.builder()
                .user_id(user_id).build();
        User friend = User.builder()
                .user_id(friend_id).build();
        log.info(friend_id);
        log.info(user);
        log.info(friend);

        List<Chat> result = chatRepository.findChatListbyID(user, friend);

        System.out.println(result);
        result.forEach(item -> {
            ChatDTO chatListDTO =new ChatDTO();
            chatListDTO.setUser_id(item.getUser_id().getUser_id());
            chatListDTO.setFriend_id(item.getFriend_id().getUser_id());
            chatListDTO.setChat_send(item.getChat_send());
            chatListDTO.setChat_read(item.getChat_read());
            chatListDTO.setChat_content(item.getChat_content());
            list.add(chatListDTO);
        });


        return list;
    }
}
