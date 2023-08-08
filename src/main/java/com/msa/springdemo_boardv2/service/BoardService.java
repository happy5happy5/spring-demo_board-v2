package com.msa.springdemo_boardv2.service;


import com.msa.springdemo_boardv2.model.Board;
import com.msa.springdemo_boardv2.model.BoardDTO;

import java.util.List;

public interface BoardService {
    List<Board> getAllBoards();
    List<Board> getBoardsByPage(int pageNum, int pageSize);
    int getTotalPages(int pageSize);
    int getCurrentPageNum(int offset, int limit);
    Board getBoardById(Long id);
    Board createBoard(Board board);
    Board updateBoard(Long id, Board board);
    void deleteBoard(Long id, String writer, String password);
    boolean hasPreviousPages(int pageNum);
    boolean hasNextPages(int pageNum, int totalPages);
}

