package com.zaizi.service.impl;


import com.zaizi.mapper.UserMapper;
import com.zaizi.pojo.User;
import com.zaizi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    // 登录
    public User login(User user) {
        return userMapper.getUerByAccountAndPwd(user);
    }

    // 注册
    @Override
    public void sign(User user) {
        userMapper.addUser(user);
    }

    // 更新
    @Override
    public int update(User user) {
        int res = userMapper.updateUser(user);
        return res;
    }
    // 获取用户信息
    @Override
    public User getUser(String account) {
        return userMapper.getUser(account);
    }


}
