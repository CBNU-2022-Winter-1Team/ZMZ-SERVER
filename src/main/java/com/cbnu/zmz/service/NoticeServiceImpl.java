package com.cbnu.zmz.service;

import com.cbnu.zmz.dto.NoticeDTO;
import com.cbnu.zmz.entity.Notice;
import com.cbnu.zmz.entity.User;
import com.cbnu.zmz.repository.NoticeRepository;
import com.cbnu.zmz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
//@Log4j2
//@RequiredArgsConstructor
//public class NoticeServiceImpl implements NoticeService{
//    private final NoticeRepository noticeRepository;
//    private final UserRepository userRepository;
//
//    @Override
//    public List<NoticeDTO>callNoticeList (String user_id){
//        Optional<User> result1 = userRepository.findById(user_id);
//        User user = result1.get();
//        List<Notice> result = noticeRepository.findByUserWithNotice(user);
//
//        List<NoticeDTO> noticeList = new List<NoticeDTO>();
////        List<NoticeDTO> noticeList = new ArrayList<>();
//        result.forEach(
//                r-> {
//                    NoticeDTO noticeDTO = new NoticeDTO();
//                    noticeDTO = entityToDTO(result);
//                    noticeList.add(noticeDTO);
//                }
//        );
//        return noticeList;
//    }
//}


//        List<NorificationDTO> name = new List<NorificationDTO>();
//        result.forEach(
//                r-> {
//                    NorificationDTO norificationDTO = new NorificationDTO();
//                    norificationDTO = entityToDTO(result);
//                    name.add(norificationDTO);
//                }
//        );
//        return name;