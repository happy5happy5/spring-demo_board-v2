package com.msa.springdemo_boardv2;

import com.msa.springdemo_boardv2.mapper.BoardMapper;
import com.msa.springdemo_boardv2.model.Board;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

//@Component
public class DummyMaker implements CommandLineRunner {


    private final BoardMapper boardmapper;


    public DummyMaker(BoardMapper boardmapper) {
        this.boardmapper = boardmapper;
    }

    @Override
        public void run(String... args) throws Exception {
//        만약 board 테이블이 없다면
//        board 테이블을 생성하고
//        dummy data를 넣어준다.

//        만약 board 테이블이 있다면
//        board 테이블을 삭제하고
//        board 테이블을 생성하고
//        dummy data를 넣어준다.
        boardmapper.dropBoardTable();
        boardmapper.createBoardTable();



            System.out.println("DummyMaker.run");
                IntStream.rangeClosed(1, 1250).forEach(i -> {
                    Board board = new Board();
                    board.setTitle("title" + i);
                    board.setContent("content" + i);
                    board.setWriter("writer" + i);
                    board.setPassword("password" + i);
//                    board.setCreatedTime("createdTime" + i);
//                    board.setUpdatedTime("updatedTime" + i);
                    boardmapper.insertBoard(board);
                });

            System.out.println("Dummy data inserted");
        }
}
