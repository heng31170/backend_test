package com.zaizi.pojo;


import lombok.Data;

@Data

public class Comment {
    Integer id;
    Integer pid;
    String account;
    String content;
    Integer time;
}
