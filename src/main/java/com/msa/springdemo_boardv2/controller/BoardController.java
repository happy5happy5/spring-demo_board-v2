package com.msa.springdemo_boardv2.controller;


import com.msa.springdemo_boardv2.model.Board;
import com.msa.springdemo_boardv2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


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
            @RequestParam(name = "sort", defaultValue = "created_Time") String sort,
            Model model
    ) {
//        현재 boardcontroller list에 있다는걸 컨트롤러 프린트로 표시
        System.out.println("[BoardController] board/list ............");
        List<Board> boards = boardService.getBoardsByPage(pageNum, pageSize, sort);

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
//
//        for문을 사용해서 no값을 설정해줘야함
//        i++ 씩 증가시키면서
        AtomicInteger i = new AtomicInteger();
        boards.forEach(board -> board.setNo((totalPages-currentPageNum +1) * pageSize - (i.getAndIncrement())));
//        이게 맞음 원래 db와 관계없이 그냥 냅다 no값 설정해줘야함

        model.addAttribute("boards", boards);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPageNum", currentPageNum);
        model.addAttribute("hasPreviousPages", hasPreviousPages);
        model.addAttribute("hasNextPages", hasNextPages);

//        boards.forEach(System.out::println);
//        boards 값의 board 객체의 createdTime 값이 어떻게 나오는지 확인
//        board에 어떤 key값으로 들어가는지 확인

//        boards.forEach(board -> System.out.println(board));
//
//        boards.forEach(board -> System.out.println(board.getCreated_Time()));

        return "board/list";
    }

    @GetMapping("/detail")
    public String detailBoard(
            @RequestParam("id") Long id,
            @RequestParam("no") int no,
            Model model
    ) {
        System.out.println("[BoardController] board/detail/{id} ............");
        Board board = boardService.getBoardById(id);
        model.addAttribute("no", no);
        model.addAttribute("board", board);
        return "board/detail";
    }

//        const data = {
//        boardId,
//                password,
//                newPassword,
//                title,
//                content
//    };
//    이런형태 객체를 json변환한 데이터를 받는다
    @PostMapping("/edit")
    public String editBoard(
            @RequestBody Map<String,String> requestMap, Model model
    ) {
        System.out.println("[BoardController] board/edit ............");
        Long id = Long.parseLong(requestMap.get("boardId"));
        String password = requestMap.get("password");
        Board board = boardService.getBoardById(id);

        if (board.getPassword().equals(password)) {
//            board를 수정한다
            board.setTitle(requestMap.get("title"));
            board.setContent(requestMap.get("content"));
            board.setPassword(requestMap.get("newPassword"));
            boardService.updateBoard(id, board);
            return "board/edit";
        } else {
            return "redirect:/board/passwordError";
        }


    }

    @PostMapping("/delete")
    public String deleteBoard(
            @RequestBody Map<String,String> requestMap, Model model
    ) {
        System.out.println("[BoardController] board/delete ............");
        Long id = Long.parseLong(requestMap.get("boardId"));
        String password = requestMap.get("password");
        Board board = boardService.getBoardById(id);

        if (board.getPassword().equals(password)) {
            boardService.deleteBoard(id, board.getWriter(), password);
            return "board/delete";
        } else {
            return "redirect:/board/passwordError";
        }
    }

    @GetMapping("/createform")
    public String createForm() {
        System.out.println("[BoardController] board/createform ............");
        return "board/createform";
    }

    @PostMapping("/create")
    public String createBoard(
            @RequestBody Map<String,String> requestMap, Model model
    ) {
        System.out.println("[BoardController] board/create ............");
        String title = requestMap.get("title");
        String writer = requestMap.get("writer");
        String content = requestMap.get("content");
        String password = requestMap.get("password");

        Board board = new Board();
        board.setTitle(title);
        board.setWriter(writer);
        board.setContent(content);
        board.setPassword(password);

        boardService.createBoard(board);
//        새로 만들어진 board의 id값을 가져온다
        Long id = board.getId();
//            list 페이지로 이동한다
        return "redirect:/board/list";
    }
}



