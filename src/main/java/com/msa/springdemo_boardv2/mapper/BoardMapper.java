package com.msa.springdemo_boardv2.mapper;

import com.msa.springdemo_boardv2.model.Board;
import jdk.jfr.Timestamp;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface BoardMapper {


    @Select("SELECT * FROM board ORDER BY created_time DESC")
    List<Board> findAll();

    @Select("SELECT * FROM board LIMIT #{size} OFFSET #{offset}")
    List<Board> getBoardsWithPagination(@Param("size") int size, @Param("offset") int offset);

    @Select("SELECT * FROM board WHERE id = #{id}")
    Board getBoardById(Long id);

    @Insert("INSERT INTO board (title, content, writer, password) VALUES (#{title}, #{content}, #{writer}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertBoard(Board board);

    @Update("UPDATE board SET title = #{title}, content = #{content}, writer = #{writer}, password = #{password}, updated_time = NOW() WHERE id = #{id}")
    void updateBoard(Board board);

    @Delete("DELETE FROM board WHERE id = #{id} AND writer = #{writer} AND password = #{password}")
    void deleteBoard(Long id, String writer, String password);

    @Select("SELECT * FROM board ORDER BY created_time DESC LIMIT #{start}, #{pageSize}")
    List<Board> getBoardsByPage(int start, int pageSize);

    @Select("SELECT COUNT(*) FROM board")
    int getTotalRecords();

//    createBoardTable(); 메소드 만들기
//      pk: id

    @Select("CREATE TABLE board (id BIGINT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(100), content VARCHAR(255), writer VARCHAR(40), password VARCHAR(40), created_time DATETIME DEFAULT NOW(), updated_time DATETIME DEFAULT NOW())")
    void createBoardTable();

    @Select("DROP TABLE board")
    void dropBoardTable();

}
