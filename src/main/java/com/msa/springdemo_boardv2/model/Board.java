package com.msa.springdemo_boardv2.model;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class Board {

    private Integer no;         // 게시글 번호
    private Long id;            // 게시글 식별자
    private String title;       // 게시글 제목
    private String content;     // 게시글 내용
    private String writer;      // 게시글 작성자
    private String password;    // 게시글 비밀 번호

    private Timestamp createdTime;     // 게시글 작성 일자

    private Timestamp updatedTime;     // 게시글 수정 일자


    // getter, setter, toString 메서드 생략
}