package com.zaizi.controller;


import com.zaizi.pojo.Comment;
import com.zaizi.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    // 获取评论
    @GetMapping("/api/comment")
    public ResponseEntity<?> getComments(@RequestParam(value = "pid") Integer pid) {
        log.info("获取评论，帖子id:{}",pid);
        try {
            List<Comment> comments = commentService.getComments(pid);
            return ResponseEntity.ok(Map.of("comments",comments));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error:","error"+ e.getMessage()));
        }
    }
    // 添加评论
    @PostMapping("api/comment/add")
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        log.info("添加评论:{}",comment);
        try {
            commentService.addComment(comment);
            return ResponseEntity.ok(Map.of("message:","success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message","error"));
        }
    }
    // 删除评论
    @PostMapping("/api/comment/delete")
    public ResponseEntity<?> delComment(@RequestBody Comment comment) {
        log.info("删除评论:{}",comment);
         try {
             commentService.delComment(comment);
             return ResponseEntity.ok(Map.of("message","success"));
         } catch (Exception e) {
             return ResponseEntity.badRequest().body(Map.of("message","error"));
         }
    }
}


























