package com.cbnu.zmz.controller;

import com.cbnu.zmz.dto.FriendDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.dto.UserDTO;
import com.cbnu.zmz.entity.User;
import com.cbnu.zmz.security.TokenProvider;
import com.cbnu.zmz.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;  //@RequiredArgsConstructor

    @Autowired
    private TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/join")
    public ResponseEntity<StatusDTO> register(@RequestBody UserDTO userDTO){

        log.info("----------------join-------------------------------");


        return new ResponseEntity<>(userService.register(userDTO, passwordEncoder), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        log.info("=========login============");

        User user = userService.getByCredentials(
                userDTO.getUser_id(),
                userDTO.getUser_pw(),
                passwordEncoder
        );
        StatusDTO statusDTO =StatusDTO.builder()
                .message("Login failed.")
                .build();

        log.info(user);
        if(user != null){
            final String token = tokenProvider.create(user);

            final UserDTO responseUserDTO = UserDTO.builder()
                    .user_id(user.getUser_id())
                    .token(token)
                    .user_name(user.getUser_name())
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        } else {

            return ResponseEntity
                    .badRequest()
                    .body(statusDTO);
        }

    }

    @GetMapping("/follower")
    public ResponseEntity<List<UserDTO>> follower(@AuthenticationPrincipal String user_id){
        log.info("============follower==============");
        log.info(user_id);

        return new ResponseEntity<>(userService.follower(user_id), HttpStatus.OK);
    }

    @GetMapping("/following")
    public ResponseEntity<List<UserDTO>> following(@AuthenticationPrincipal String user_id){
        log.info("============following==============");
        log.info(user_id);

        return new ResponseEntity<>(userService.following(user_id), HttpStatus.OK);
    }

    @PostMapping("/followAccept")
    public ResponseEntity<StatusDTO> followAccept(@RequestBody FriendDTO friendDTO) throws Exception {
        log.info("============followAccept==============");
        log.info(friendDTO);
        return new ResponseEntity<>(userService.followAccept(friendDTO), HttpStatus.OK);

    }

    @PostMapping("/followProposal")
    public ResponseEntity<StatusDTO> followProposal(@RequestBody UserDTO userDTO) {
        log.info("============followProposal==============");
        log.info(userDTO);
        return new ResponseEntity<>(userService.followProposal(userDTO), HttpStatus.OK);

    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> info(String user_id){
        log.info("============userInfo==============");
        log.info(user_id);
        return new ResponseEntity<>(userService.info(user_id), HttpStatus.OK);
    }


    @PostMapping("/modify/profile")
    public ResponseEntity<StatusDTO> modifyProfile(@RequestBody UserDTO userDTO){
        log.info("============modify profile==============");
        log.info(userDTO);
        return new ResponseEntity<>(userService.modifyProfile(userDTO), HttpStatus.OK);
    }

    @PostMapping("/modify/pw")
    public ResponseEntity<StatusDTO> modifyPw(@RequestBody UserDTO userDTO){
        log.info("============modify password==============");
        log.info(userDTO);

        return new ResponseEntity<>(userService.modifyPw(userDTO), HttpStatus.OK);
    }


}
