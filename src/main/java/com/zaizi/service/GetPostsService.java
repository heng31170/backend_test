package com.zaizi.service;


import com.zaizi.pojo.Posting;

import java.util.List;

public interface GetPostsService {
    // 获取帖子信息
    List<Posting> getPosts(String account);

    // 收藏
    void like(Posting posting);
    // 取消收藏
    void dislike(Posting posting);
    // 查询点赞
    List<Integer> queLike(String account);
    // 获取指定帖子
    List<Posting> getSepcifyPosts(String account);
    // 获取动态
    List<Posting> getFollowPosts(String account);
}
