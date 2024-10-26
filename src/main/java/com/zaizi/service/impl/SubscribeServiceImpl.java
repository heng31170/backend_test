package com.zaizi.service.impl;

import com.zaizi.mapper.SubscribeMapper;
import com.zaizi.pojo.Subscribe;
import com.zaizi.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscribeServiceImpl implements SubscribeService {
    @Autowired
    private SubscribeMapper subscribeMapper;
    // 订阅
    @Override
    public void addSub(Subscribe subscribe) {
        subscribeMapper.addSubscribe(subscribe);
    }

    @Override
    public void delSub(Subscribe subscribe) {
        subscribeMapper.delSubscribe(subscribe);
    }

    @Override
    public List<String> queSub(String account) {
        List<String> subList = subscribeMapper.queSubscribe(account);
        return subList;
    }
}
