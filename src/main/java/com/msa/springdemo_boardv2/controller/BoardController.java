package com.msa.springdemo_boardv2.controller;


import com.msa.springdemo_boardv2.model.Board;
import com.msa.springdemo_boardv2.model.BoardDTO;
import com.msa.springdemo_boardv2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String listBoards(
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            Model model
    ) {
        List<Board> boards = boardService.getBoardsByPage(pageNum, pageSize);

        int totalPages = boardService.getTotalPages(pageSize);
        int currentPageNum = boardService.getCurrentPageNum((pageNum - 1) * pageSize, pageSize);
        boolean hasPreviousPages = boardService.hasPreviousPages(pageNum);
        boolean hasNextPages = boardService.hasNextPages(pageNum, totalPages);

//        게시물의 id값을 표시하는게 아니라
//        총 게시물수에 따라서 no값을 표시해야함
//        게시물의 id값을 표시하면 게시물이 삭제되었을때
//        게시물의 id값이 빠진채로 표시되기 때문에
//        게시물의 no값을 표시해야함
//        어쩔수없이 여기서 no값을 설정해줘야함
//        게시물을 보낼때 no값을 정확하게 정할수 있기 때문
//        역순으로 번호 매기기

        for (int i = 0; i < boards.size(); i++) {
            boards.get(i).setNo((currentPageNum - 1) * pageSize + i + 1);
        }

        model.addAttribute("boards", boards);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPageNum", currentPageNum);
        model.addAttribute("hasPreviousPages", hasPreviousPages);
        model.addAttribute("hasNextPages", hasNextPages);

//        boards.forEach(System.out::println);
//        boards 값의 board 객체의 createdTime 값이 어떻게 나오는지 확인
//        boards.forEach(board -> System.out.println(board.getCreatedTime()));

        return "board/list";
    }
}



