package com.msa.springdemo_boardv2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.msa.springdemo_boardv2.mapper"})
public class SpringDemoBoardV2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoBoardV2Application.class, args);
    }

}
