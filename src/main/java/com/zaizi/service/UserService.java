package com.zaizi.service;

import com.zaizi.pojo.User;

public interface UserService {
    // 登录
    User login(User user);

    // 注册
    void sign(User user);
    // 更新
    int update(User user);
    // 获取用户信息
    User getUser(String account);
}
