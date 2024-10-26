package com.zaizi.controller;


import com.zaizi.pojo.Subscribe;
import com.zaizi.service.SubscribeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class SubscribeController {
    @Autowired
    private SubscribeService subscribeService;

    // 订阅
    @PostMapping("/api/subscribe")
    public ResponseEntity<?> addSub(@RequestBody Subscribe subscribe) {
        try {
            subscribeService.addSub(subscribe);
            return ResponseEntity.ok(Map.of("message","subscription added"));
        } catch (Exception e) {
            log.error("订阅失败:{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error","error:"+e.getMessage()));
        }
    }
    // 取消订阅
    @PostMapping("/api/unsubscribe")
    public ResponseEntity<?> delSub(@RequestBody Subscribe subscribe) {
        try {
            subscribeService.delSub(subscribe);
            return ResponseEntity.ok(Map.of("message","subscription removed"));
        } catch (Exception e) {
            log.error("取消订阅失败:{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error","error:"+e.getMessage()));
        }
    }
    // 查询订阅
    @GetMapping("/api/subscribe/query")
    public ResponseEntity<?> queSub(@RequestParam(value = "user",required = false) String account) {
        try {
            List<String> subList = subscribeService.queSub(account);
            Map<String,Object> res = new HashMap<>();
            res.put("sub_users",subList);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            log.error("查询失败:{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error","error"+e.getMessage()));
        }
    }




}
