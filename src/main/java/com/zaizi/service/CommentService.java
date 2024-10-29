package com.zaizi.service;

import com.zaizi.pojo.Comment;

import java.util.List;

public interface CommentService {
    // 获取评论
    List<Comment> getComments(Integer pid);
//    添加评论
    void addComment(Comment comment);
    // 删除评论
    void delComment(Comment comment);
}
