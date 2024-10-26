package com.zaizi.service.impl;

import com.zaizi.mapper.GetPostsMapper;
import com.zaizi.pojo.Posting;
import com.zaizi.service.GetPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Service
public class GetPostsServiceImpl implements GetPostsService {
    @Autowired
    private GetPostsMapper getPostsMapper;

    // 获取帖子信息
    @Override
    public List<Posting> getPosts(String account) {
        return getPostsMapper.getPostsByName(account);
    }

    // 收藏帖子
    @Override
    public void like(Posting posting) {
        getPostsMapper.likePost(posting);
    }
    // 取消收藏帖子
    @Override
    public void dislike(Posting posting) {
        getPostsMapper.dislikePost(posting);
    }

    @Override
    public List<Integer> queLike(String account) {
        List<Integer> likeList = getPostsMapper.queLikePost(account);
        return likeList;
    }

    // 指定帖子
    @Override
    public List<Posting> getSepcifyPosts(String account) {
        return getPostsMapper.getSpecifyPosts(account);
    }
    // 获取动态
    @Override
    public List<Posting> getFollowPosts(String account) {
        return getPostsMapper.getFollowPosts(account);
    }

}
