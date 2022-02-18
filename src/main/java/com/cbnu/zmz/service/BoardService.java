package com.cbnu.zmz.service;

import com.cbnu.zmz.dto.BoardDTO;
import com.cbnu.zmz.dto.StatusDTO;
import com.cbnu.zmz.dto.UserDTO;
import com.cbnu.zmz.entity.Board;

public interface BoardService {
    BoardDTO list(Long user_id);

    StatusDTO register(BoardDTO dto);

    //PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO);

    BoardDTO read(Long post_id);

    BoardDTO modify(BoardDTO dto);

    StatusDTO delete(Long post_id);

    UserDTO bookList(Long user_id);

    StatusDTO bookAdd(BoardDTO boardDTO);

    BoardDTO commentList(Long user_id);

    default Board dtoToEntity(BoardDTO boardDTO) {
        Board board = Board.builder()
                .post_id(boardDTO.getPost_id())
                .post_content(boardDTO.getPost_content())
                .post_title(boardDTO.getPost_title())
                .is_secret(boardDTO.getIs_secret())
                .post_time(boardDTO.getPost_time())
                .post_comment(boardDTO.getPost_comment())
                .post_like(boardDTO.getPost_like())
                .post_view(boardDTO.getPost_view())
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board) {
        BoardDTO boardDTO = BoardDTO.builder()
                .post_id(board.getPost_id())
                .post_content(board.getPost_content())
                .post_title(board.getPost_title())
                .is_secret(board.getIs_secret())
                .post_time(board.getPost_time())
                .post_comment(board.getPost_comment())
                .post_like(board.getPost_like())
                .post_view(board.getPost_view())
                .build();
        return boardDTO;
    }
}
