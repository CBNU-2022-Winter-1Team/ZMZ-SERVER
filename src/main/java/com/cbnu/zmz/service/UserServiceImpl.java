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

    @Override
    public StatusDTO register(UserDTO userDTO, PasswordEncoder passwordEncoder){
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

        Optional<User> user = Optional.of(result1.get());

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
//    {
//        "user_id" : "test...1",
//            "friend_id" : "test...2",
//            "friend_num" : 2
//    }
        return statusDTO;
    }


    @Override
    public StatusDTO followProposal(UserDTO  userDTO){
        StatusDTO statusDTO = new StatusDTO();
        User user_id = User.builder()
                .user_id(userDTO.getUser_id()).build();

        User friend_id = User.builder()
                .user_id(userDTO.getFriend_id()).build();

        FriendStatus friendStatus = FriendStatus.builder()
                        .friend_num(3).build();

        Friend friend = Friend.builder()
                        .user_id(user_id)
                        .friend_id(friend_id)
                        .friend_num(friendStatus).build();

        Optional<Friend> result = friendRepository.friendIsPresent(user_id, friend_id);
        statusDTO.setSuccess(true);
        statusDTO.setStatus(200);

        if(result.isPresent()){
            statusDTO.setMessage("isPresent");
        }
        else{
            statusDTO.setMessage("success");
            friendRepository.save(friend);
        }

        return statusDTO;
    }

    @Override
    public UserDTO info(String user_id){
        Optional<User> result = userRepository.findById(user_id);

        User user = result.get();

        UserDTO userDTO = entityToDTO(user);

        log.info(userDTO);

        return userDTO;
    }

    @Override
    public StatusDTO modifyProfile(UserDTO userDTO){
        StatusDTO statusDTO = new StatusDTO();

        Optional<User> result = userRepository.findById(userDTO.getUser_id());



        User user = User.builder()
                .user_id(userDTO.getUser_id())
                .user_pw(result.get().getUser_pw())
                .user_name(userDTO.getUser_name())
                .user_text(userDTO.getUser_text())
                .build();

        userRepository.save(user);


        statusDTO.setSuccess(true);
        statusDTO.setStatus(200);
        statusDTO.setMessage("success");

        return statusDTO;
    }

    @Override
    public StatusDTO modifyPw(UserDTO userDTO){
        StatusDTO statusDTO = new StatusDTO();

        Optional<User> result = userRepository.findById(userDTO.getUser_id());


        statusDTO.setSuccess(true);
        statusDTO.setStatus(200);

//        boolean passwordResult = passwordEncoder.matches(userDTO.getUser_pw(), result.get().getUser_pw());
//
//
//
//        if(passwordResult){
//            User user = User.builder()
//                    .user_id(result.get().getUser_id())
//                    .user_pw(passwordEncoder.encode(userDTO.getUser_new_pw()))
//                    .user_name(result.get().getUser_name())
//                    .user_text(result.get().getUser_text())
//                    .build();
//            userRepository.save(user);
//            statusDTO.setMessage("pwOk");
//        }
//        else{
//            statusDTO.setMessage("pwFaile");
//        }

        return statusDTO;
    }
    @Override
    public User getByCredentials(final String email, final String password, final PasswordEncoder encoder){
        final Optional<User> originalUser = userRepository.findById(email);

        if(originalUser.get() != null && encoder.matches(password, originalUser.get().getUser_pw())){
            return originalUser.get();
        }
        return null;
    }
}
