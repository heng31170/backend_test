package com.zaizi.controller;


import com.zaizi.pojo.Posting;
import com.zaizi.service.GetPostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class GetPostsController {
    @Autowired
    private GetPostsService getPostsService;


    // 获取帖子
    @GetMapping("/api/posting/random")
    public ResponseEntity<?> gePostsByName(@RequestParam(value = "user",required = false) String account) {
        log.info("获取帖子请求，用户名:{}",account);
        try {
            List<Posting> postings = getPostsService.getPosts(account);
            return ResponseEntity.ok(Map.of("postings",postings));
        } catch (Exception e) {
            log.error("获取随机帖子失败:{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error","error:" + e.getMessage()));
        }
    }
    // 收藏
    @PostMapping("/api/like")
    public ResponseEntity<?> likePost(@RequestBody Posting posting) {
        try {
            getPostsService.like(posting);
            return ResponseEntity.ok(Map.of("message","like added"));
        } catch (Exception e) {
            log.error("帖子收藏失败:{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error","error:"+e.getMessage()));
        }

    }
    // 取消收藏
    @PostMapping("/api/dislike")
    public ResponseEntity<?> dislikePost(@RequestBody Posting posting) {
        try {
            getPostsService.dislike(posting);
            return ResponseEntity.ok(Map.of("message","like removed"));
        } catch (Exception e) {
            log.error("帖子收藏失败:{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error","error:"+e.getMessage()));
        }

    }
    // 查询点赞
    @GetMapping("/api/like/query")
    public ResponseEntity<?> queLike(@RequestParam(value = "user",required = false) String account) {
        try {
            List<Integer> likeList = getPostsService.queLike(account);
            HashMap<String,Object> res = new HashMap<>();
            res.put("likes_pid",likeList);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            log.error("查询点赞失败:{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error","error:"+e.getMessage()));
        }
    }

    // 获取指定帖子
    @GetMapping("/api/posting")
    public ResponseEntity<?> getSpecifyPosts(@RequestParam(value = "poster") String account) {
        log.info("获取指定帖子,发帖者:{}",account);
        try {
            List<Posting> postings = getPostsService.getSepcifyPosts(account);
            if(!postings.isEmpty()) {
                return ResponseEntity.ok(Map.of("postings",postings));
            }
            return ResponseEntity.badRequest().body(Map.of("message","the postings is empty"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error","error:"+e.getMessage()));
        }
    }
    // 获取动态
    @GetMapping("/api/dynamic")
    public ResponseEntity<?> getFollowPosts(@RequestParam(value = "user") String account) {
        log.info("获取关注用户的帖子,我的用户名:{}",account);
        try {
            List<Posting> postings = getPostsService.getFollowPosts(account);
            if(!postings.isEmpty()) {
                return ResponseEntity.ok(Map.of("postings",postings));
            }
            return ResponseEntity.badRequest().body(Map.of("message","the postings is empty"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error","error:"+e.getMessage()));
        }
    }
    // 搜索帖子
    @GetMapping("/api/posting/search")
    public ResponseEntity<?> searchPosts(@RequestParam(value = "user",required = false) String account,
                                         @RequestParam(value = "pid",required = false) Integer pid,
                                         @RequestParam(value = "title",required = false) String title,
                                         @RequestParam(value = "text",required = false) String text
                                         ) {
        log.info("搜索帖子操作，用户：{},pid：{},标题：{}，内容：{}",account,pid,title,text);
        try {
            List<Posting> postings = getPostsService.searchPosts(account,pid,title,text);
            return ResponseEntity.ok(Map.of("postings",postings));

        } catch (Exception e) {
            log.info("搜索失败:{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("message","error"));
        }
    }




}



















