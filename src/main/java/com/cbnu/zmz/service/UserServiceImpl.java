package com.cbnu.zmz.service;

import com.cbnu.zmz.dto.FriendDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.dto.UserDTO;
import com.cbnu.zmz.entity.Friend;
import com.cbnu.zmz.entity.FriendStatus;
import com.cbnu.zmz.entity.User;
import com.cbnu.zmz.repository.FriendRepository;
import com.cbnu.zmz.repository.FriendStatusRepository;
import com.cbnu.zmz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final FriendRepository friendRepository;

    private final FriendStatusRepository friendStatusRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public StatusDTO register(UserDTO userDTO){
        StatusDTO statusDTO = new StatusDTO();
        log.info(userDTO);

        userDTO.setUser_pw(passwordEncoder.encode(userDTO.getUser_pw()));

        User user = dtoToEntity(userDTO);
        log.info("=========join=========");
        log.info(user);

        Optional<User> result = userRepository.findById(user.getUser_id());

        if(result.isPresent()){
            statusDTO.setMessage("이미 해당하는 아이디가 있습니다.");
            statusDTO.setStatus(400);
        }
        else{
            statusDTO.setSuccess(true);
            statusDTO.setMessage("회원가입 성공");
            statusDTO.setStatus(200);
            userRepository.save(user);
        }

        return statusDTO;
    }


//    @Override
//    public Page<User> login(String user_id, String user_pw){
//        Pageable pageable
//        Page<User> result = userRepository.findAll(pageable);
//    }

    @Override
    public List<UserDTO> follower(String user_id){


        List<UserDTO> list = new ArrayList<>();

        Optional<User> result1 = userRepository.findById(user_id);

        User user = result1.get();

        List<Friend> result = friendRepository.findByUserWithFollower(user);

        result.forEach(
                r-> {
                    if(r.getFriend_num().getFriend_num()==1){
                        UserDTO userDTO = new UserDTO();
                        userDTO.setUser_id(r.getUser_id().getUser_id());
                        userDTO.setFriend_id(r.getFriend_id().getUser_id());
                        list.add(userDTO);
                    }
                }
                );
        return list;
    }

    @Override
    public List<UserDTO> following(String user_id){


        List<UserDTO> list = new ArrayList<>();

        Optional<User> result1 = userRepository.findById(user_id);

        User user = result1.get();

        List<Friend> result = friendRepository.findByUserWithFollowing(user);

        result.forEach(
                r-> {
                    if(r.getFriend_num().getFriend_num()==1){
                        UserDTO userDTO = new UserDTO();
                        userDTO.setUser_id(r.getUser_id().getUser_id());
                        userDTO.setFriend_id(r.getFriend_id().getUser_id());
                        list.add(userDTO);
                    }
                }
        );
        return list;
    }

    @Override
    public StatusDTO followAccept(FriendDTO friendDTO) throws Exception {
        Optional<User> result = userRepository.findById(friendDTO.getUser_id());
        User user = result.get();

        Optional<User> result1 = userRepository.findById(friendDTO.getFriend_id());
        User friend = result1.get();

        Optional<FriendStatus> result2 = friendStatusRepository.findById(friendDTO.getFriend_num());
        FriendStatus friendStatus = result2.get();

        int test = -1;

        test = friendRepository.updateFriend_status(user, friend, friendStatus);

        log.info("AcceptTest==========="+test);

        StatusDTO statusDTO = new StatusDTO();

        statusDTO.setSuccess(true);
        statusDTO.setMessage("친구 수락");
        statusDTO.setStatus(200);

        return statusDTO;
    }
//
//    @Override
//    public StatusDTO followProposal(String user_id, String friend_id){
//        StatusDTO statusDTO = new StatusDTO();
//
//
//        return statusDTO;
//    }
//
//    @Override
//    public UserDTO userInfo(String user_id){
//
//    }
//
//    @Override
//    public UserDTO userConfig(String user_id){
//
//    }
//
//    @Override
//    public StatusDTO modifyProfile(UserDTO userDTO){
//        StatusDTO statusDTO = new StatusDTO();
//
//
//        return statusDTO;
//    }
//
//    @Override
//    public StatusDTO modifyPw(String user_id, String user_pw, String user_new_pw){
//        StatusDTO statusDTO = new StatusDTO();
//
//
//        return statusDTO;
//    }
}
