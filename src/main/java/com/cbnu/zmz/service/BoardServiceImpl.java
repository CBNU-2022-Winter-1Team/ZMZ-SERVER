package com.cbnu.zmz.service;

import com.cbnu.zmz.dto.BoardDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.dto.UserDTO;
import com.cbnu.zmz.entity.Board;
import com.cbnu.zmz.entity.Scrap;
import com.cbnu.zmz.entity.User;
import com.cbnu.zmz.repository.BoardRepository;
import com.cbnu.zmz.repository.ScrapRepository;
import com.cbnu.zmz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final ScrapRepository scrapRepository;

//    @Override
//    public BoardDTO list(String user_id) {
//        List<Board> result = boardRepository.findBoardListByUser(user_id);
//
//        Board board = result.get();
//
//        BoardDTO boardDTO = entityToDTO(board);
//
//        log.info(boardDTO);
//
//        return boardDTO;
//    }
    @Override
    public List<BoardDTO> list(String user_id) {
        List<BoardDTO> list = new ArrayList<>();

        User user = User.builder()
                .user_id(user_id).build();

        log.info(user);

        List<Board> result = boardRepository.findBoardListByUser(user);

        result.forEach( b -> {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setUser_id(b.getUser_id().getUser_id());
            boardDTO.setPost_title(b.getPost_title());
            boardDTO.setPost_content(b.getPost_content());
            boardDTO.setPost_like(b.getPost_like());

            list.add(boardDTO);
        });

        return list;
    }


    public StatusDTO register(BoardDTO boardDTO) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setSuccess(true);
        statusDTO.setMessage("게시판 등록 성공");
        statusDTO.setStatus(200);

        boardDTO.setPost_time(LocalDateTime.now());
        Board board = dtoToEntity(boardDTO);

        boardRepository.save(board);

        return statusDTO;
    }

    //PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO);

    public BoardDTO read(Long post_id) {
        Optional<Board> result = boardRepository.findBoardById(post_id);

        Board board = result.get();

        BoardDTO boardDTO = entityToDTO(board);

        log.info(boardDTO);

        return boardDTO;
    }

    public StatusDTO modify(BoardDTO dto, String user_id){
        Optional<Board> result = boardRepository.findBoardById(dto.getPost_id());
        StatusDTO statusDTO = new StatusDTO();

        BoardDTO boardDTO = entityToDTO(result.get());

        boardDTO.setPost_title(dto.getPost_title());
        boardDTO.setPost_content(dto.getPost_content());

        Board board = dtoToEntity(boardDTO);
        statusDTO.setStatus(200);

        if(result.get().getUser_id().getUser_id().equals(user_id) ){
            statusDTO.setSuccess(true);
            statusDTO.setMessage("게시판 등록 성공");
            boardRepository.save(board);
        }
        else{
            statusDTO.setSuccess(false);
            statusDTO.setMessage("해당 게시물에 대한 권한이 없습니다.");
        }




    return statusDTO;
    }

    public StatusDTO delete(Long post_id){

        Optional<Board> result = boardRepository.findBoardById(post_id);

        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setStatus(200);
        if(result.isPresent()){
            Board board = result.get();
            boardRepository.delete(board);
            statusDTO.setSuccess(true);
            statusDTO.setMessage("게시물 삭제 성공");
        }
        else{
            statusDTO.setSuccess(false);
            statusDTO.setMessage("게시물 삭제 실패");
        }
        return statusDTO;

    };

    public List<BoardDTO> bookList(String user_id){
        Optional<User> result = userRepository.findById(user_id);

        List <BoardDTO> boardDTOList = new ArrayList<>();
        if(result.isPresent()){
            User user = result.get();
            List<Scrap> scrapList = scrapRepository.findByUserId(user);

            scrapList.forEach(scrap -> {
                BoardDTO boardDTO;
                boardDTO = entityToDTO(boardRepository.findBoardById(scrap.getBoard().getPost_id()).get());
                boardDTOList.add(boardDTO);

            });
        }

        return boardDTOList;
    };

    public StatusDTO bookAdd(String user_id, BoardDTO boardDTO){
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setStatus(200);

        Optional<User> result1 = userRepository.findById(user_id);

        Optional<Board> result2 = boardRepository.findBoardById(boardDTO.getPost_id());

        if(result2.isPresent() && result1.isPresent()){
            User user = result1.get();
            Board board = result2.get();

            Scrap scrap = Scrap.builder().user(user).board(board).build();

            scrapRepository.save(scrap);
            statusDTO.setSuccess(true);
            statusDTO.setMessage("게시물 스크랩 성공");
        }
        else{
            statusDTO.setSuccess(false);
            statusDTO.setMessage("게시물 스크랩 실패");
        }

        return statusDTO;
    };

//    BoardDTO commentList(Long user_id);
}
