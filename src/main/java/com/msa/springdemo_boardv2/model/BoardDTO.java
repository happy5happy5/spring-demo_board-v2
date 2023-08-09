package com.msa.springdemo_boardv2.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String password;
    private String created_Time;
    private String updated_Time;

    public BoardDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.password = board.getPassword();
        this.created_Time = board.getCreated_Time();
        this.updated_Time = board.getUpdated_Time();
    }

    // getters and setters
}


