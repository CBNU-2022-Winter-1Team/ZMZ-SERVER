package com.cbnu.zmz.service;

import com.cbnu.zmz.dto.BoardDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.dto.UserDTO;
import com.cbnu.zmz.entity.Board;
import com.cbnu.zmz.entity.User;

import java.util.List;

public interface BoardService {
    List<BoardDTO> list(String user_id);

    StatusDTO register(BoardDTO boardDTO);

    //PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO);

    BoardDTO read(Long post_id);

    StatusDTO modify(BoardDTO dto, String user_id);

    StatusDTO delete(Long post_id);

    List<BoardDTO> bookList(String user_id);

    StatusDTO bookAdd(String user_id, BoardDTO boardDTO);

//    BoardDTO commentList(Long user_id);

    default Board dtoToEntity(BoardDTO boardDTO) {
        User user = User.builder().user_id(boardDTO.getUser_id()).build();
        Board board = Board.builder()
                .post_id(boardDTO.getPost_id())
                .post_content(boardDTO.getPost_content())
                .post_title(boardDTO.getPost_title())
                .is_secret(boardDTO.getIs_secret())
                .post_time(boardDTO.getPost_time())
                .post_comment(boardDTO.getPost_comment())
                .post_like(boardDTO.getPost_like())
                .post_view(boardDTO.getPost_view())
                .user_id(user)
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board) {
        UserDTO userDTO = UserDTO.builder().user_id(board.getUser_id().getUser_id()).build();
        BoardDTO boardDTO = BoardDTO.builder()
                .post_id(board.getPost_id())
                .post_content(board.getPost_content())
                .post_title(board.getPost_title())
                .is_secret(board.getIs_secret())
                .post_time(board.getPost_time())
                .post_comment(board.getPost_comment())
                .post_like(board.getPost_like())
                .post_view(board.getPost_view())
                .user_id(board.getUser_id().getUser_id())
                .build();
        return boardDTO;
    }
}
