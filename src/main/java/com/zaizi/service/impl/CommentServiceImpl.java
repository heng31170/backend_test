package com.zaizi.service.impl;

import com.zaizi.mapper.CommentMapper;
import com.zaizi.pojo.Comment;
import com.zaizi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    // 获取评论
    @Override
    public List<Comment> getComments(Integer pid) {
        List<Comment> comments = commentMapper.getComments(pid);
        return comments;
    }
    // 添加评论
    @Override
    public void addComment(Comment comment) {
        commentMapper.addComment(comment);
    }

    // 删除评论
    @Override
    public void delComment(Comment comment) {
        commentMapper.delComment(comment);
    }


}
