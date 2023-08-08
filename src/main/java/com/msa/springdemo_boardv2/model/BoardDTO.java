package com.msa.springdemo_boardv2.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.ErrorResponse;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String password;
    private Timestamp createdTime;
    private Timestamp updatedTime;

    public BoardDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.password = board.getPassword();
        this.createdTime = board.getCreatedTime();
        this.updatedTime = board.getUpdatedTime();
    }

    // getters and setters
}


