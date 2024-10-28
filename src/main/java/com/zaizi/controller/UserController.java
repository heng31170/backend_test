package com.zaizi.controller;


import com.zaizi.pojo.User;
import com.zaizi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    // login
    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        log.info("登录操作:{}",user);
        User u = userService.login(user);
        if(u != null) {
            Map<String,Object> res = new HashMap<>();
            res.put("status","success");
            res.put("message","success to login");
            Map<String,Object> user1 = new HashMap<>();
            user1.put("account",u.getAccount());
            user1.put("nickname",u.getNickname());
            user1.put("icon",u.getIcon());
            res.put("user",user1);
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.status(401).body(Map.of(
                    "status","failed",
                    "message","failed to login, user does not exist or the password is wrong"
            ));
        }
    }
    // sign
    @PostMapping("/api/sign")
    public ResponseEntity<?> sign(@RequestBody User user) {
        log.info("注册操作",user);
        User u_check = userService.login(user);
        if(u_check == null) {
            userService.sign(user);
            Map<String, Object> res = new HashMap<>();
            res.put("status","success");
            res.put("message","success to sign up");
            Map<String, Object> user2 = new HashMap<>();
            user2.put("account",user.getAccount());
            user2.put("nickname",user.getNickname());
            user2.put("icon",null);
            res.put("user",user2);
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.status(400).body(Map.of(
                    "status","failed",
                    "message","failed to sign up, the user is exited"
            ));
        }
    }
    // update
    @PostMapping("/api/update/user")
    public ResponseEntity<?> update(@RequestBody User user) {
        log.info("更新用户操作:");
        try {
            if(user.getPasswd().isEmpty()) {
                log.info("input new passwd please");
                return ResponseEntity.badRequest().body(Map.of("status","failed","message","input new passwd please"));
            }
            int res = userService.update(user);  // 更新
            if(res == 0) {
                return ResponseEntity.badRequest().body(Map.of("status","failed","message","failed to update user"));
            }
            return ResponseEntity.ok(Map.of("status","success","message","success to update user"));
        } catch (Exception e) {
            log.error("更新失败:{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("status","failed","message","failed to update user"));
        }
    }
    // 获取用户信息
    @GetMapping("/api/userinfo")
    public ResponseEntity<?> getUser(@RequestParam(value = "user") String account) {
        log.info("获取用户信息，用户名:{}",account);
        try {
            User u = userService.getUser(account);
            if(u == null) {
                return ResponseEntity.badRequest().body(Map.of("message","not found"));
            }
            HashMap<String,Object> res = new HashMap<>();
            res.put("message","success to get userInfo");
            res.put("user",u.getAccount());
            res.put("icon",u.getIcon());
            res.put("nickname",u.getNickname());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message","not found"));
        }
    }


}






















