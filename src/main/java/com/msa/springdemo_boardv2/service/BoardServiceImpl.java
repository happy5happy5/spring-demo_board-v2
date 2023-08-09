package com.msa.springdemo_boardv2.service;

import com.msa.springdemo_boardv2.mapper.BoardMapper;
import com.msa.springdemo_boardv2.model.Board;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<Board> getAllBoards() {
        List<Board> boards = boardMapper.findAll();
        return boards;
    }

    @Override
    public List<Board> getBoardsByPage(int pageNum, int pageSize, String sort) {
        int start = (pageNum - 1) * pageSize;
        List<Board> boards = boardMapper.getBoardsByPage(start, pageSize, sort);
        return boards;
    }

    @Override
    public int getTotalPages(int pageSize) {
        int totalRecords = boardMapper.getTotalRecords();
        return (totalRecords + pageSize - 1) / pageSize;
    }

    @Override
    public int getCurrentPageNum(int offset, int limit) {
        return (offset / limit) + 1;
    }

    @Override
    public Board getBoardById(Long id) {
        Board board = boardMapper.getBoardById(id);
        return board;
    }

    @Override
    public Board createBoard(Board board) {
        boardMapper.insertBoard(board);
        return board;
    }

    @Override
    public Board updateBoard(Long id, Board board) {
        board.setId(id);
        boardMapper.updateBoard(board);
        return board;
    }

    @Override
    public void deleteBoard(Long id, String writer, String password) {
        boardMapper.deleteBoard(id, writer,password);
    }

    @Override
    public boolean hasPreviousPages(int pageNum) {
        return pageNum > 10;
    }

    @Override
    public boolean hasNextPages(int pageNum, int totalPages) {
        return totalPages - pageNum >= 10;
    }
}