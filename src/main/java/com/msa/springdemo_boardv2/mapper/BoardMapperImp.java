package com.msa.springdemo_boardv2.mapper;

import com.msa.springdemo_boardv2.model.Board;

import java.util.List;


public class BoardMapperImp implements BoardMapper{

    private final BoardMapper boardMapper;

    public BoardMapperImp(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    @Override
    public List<Board> getBoardsWithPagination(int size, int offset) {
        return boardMapper.getBoardsWithPagination(size, offset);
    }

    @Override
    public List<Board> getBoardsByPage(int start, int pageSize, String sort) {
        return boardMapper.getBoardsByPage(start, pageSize, sort);
    }

//    @Override
//    public List<Board> getBoardsByPage(int start, int pageSize) {
//        return boardMapper.getBoardsByPage(start, pageSize);
//    }

    @Override
    public int getTotalRecords() {
        return boardMapper.getTotalRecords();
    }

    @Override
    public void createBoardTable() {
        boardMapper.createBoardTable();
    }

    @Override
    public void dropBoardTable() {
        boardMapper.dropBoardTable();
    }

    @Override
    public Board getBoardById(Long id) {
        return boardMapper.getBoardById(id);
    }

    @Override
    public void insertBoard(Board board) {
//        validation check must be done before insert even though it is done in client side. yes yes
        if (board.getPassword() == null || board.getWriter() == null) {
            throw new IllegalArgumentException("Password and writer must not be null");
        }

        if (!isValidPassword(board.getPassword())){
            throw new IllegalArgumentException("Password is invalid");
        }

        boardMapper.insertBoard(board);
    }

    private boolean isValidPassword(String password) {
        //  the password must contain at least 8 characters
        if (password.length() < 8 || password.length() > 20) {
            return false;
        } else {
            return true;
        }
    }



    @Override
    public void updateBoard(Board board) {
        Board originalBoard = boardMapper.getBoardById(board.getId());

        if (!originalBoard.getWriter().equals(board.getWriter()) || !originalBoard.getPassword().equals(board.getPassword())) {
            throw new IllegalArgumentException("Writer and password must match");
        }

        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(Long id, String writer, String password) {
        Board board = boardMapper.getBoardById(id);

        if (!board.getWriter().equals(writer) || !board.getPassword().equals(password)) {
            throw new IllegalArgumentException("Writer or password is incorrect");
        }

        boardMapper.deleteBoard(id, writer, password);
    }



}
