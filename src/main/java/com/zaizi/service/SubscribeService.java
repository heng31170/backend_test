package com.zaizi.service;

import com.zaizi.pojo.Subscribe;

import java.util.List;

public interface SubscribeService {
    // 订阅
    void addSub(Subscribe subscribe);
    // 取消订阅
    void delSub(Subscribe subscribe);
    // 查询订阅
    List<String> queSub(String account);
}
