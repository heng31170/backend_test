package com.zaizi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaizi.pojo.Posting;
import com.zaizi.service.PostingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class PostingController {

    @Autowired
    private PostingService postingService;

    @PostMapping("/api/post")
    public ResponseEntity<?> post(
            @RequestParam(value = "file", required = false) List<MultipartFile> files,
            @RequestParam("json") String json) throws Exception {

        log.info("发布帖子操作: {}", json);
        log.info("上传文件数量:{}",files != null? files.size() : 0);
        Posting postRequest;

        // 解析 JSON 数据
        try {
            postRequest = new ObjectMapper().readValue(json, Posting.class);
        } catch (Exception e) {
            log.error("JSON 解析错误：{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "failed to get JSON"
            ));
        }

        // 检查必填字段
        if (postRequest.getTitle() == null || postRequest.getText() == null || postRequest.getAccount() == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "missing required fields"
            ));
        }
//        // 调用服务层处理
        Integer pid = postingService.handlePost(postRequest);
        log.info("pid调用成功,pid:{}", pid);
        // 确保 pid 被设置到 postRequest 中
        postRequest.setPid(pid);


        // 处理文件上传
        if (files != null && files.size() > 0) {
            StringBuilder filePaths = new StringBuilder();
            for (MultipartFile file : files) {
                try {
                    String filePath = postingService.saveFile(file,postRequest);
                    log.info("文件上传成功，文件路径:{}",filePath);
                    filePaths.append(filePath).append("|");
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(Map.of(
                            "status", "failed",
                            "message", "failed to upload file"
                    ));
                }
            }
            // 去掉最后一个竖线
            if (filePaths.length() > 0) {
                filePaths.setLength(filePaths.length() - 1);
            }
            postRequest.setFiles(filePaths.toString());
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "no files uploaded"
            ));
        }

        // 处理图像压缩
        try {
            postingService.compressImage(postRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "failed to compress file"
            ));
        }


        // 调用服务层处理

        Map<String, Object> res = new HashMap<>();
        if(postingService.getCtrl()=="insert") {
            res.put("status", "success");
            res.put("message", "success to post and upload file");
            res.put("pid", pid);
        } else {
            res.put("status", "success");
            res.put("message", "success to update post and upload file");
        }
        log.info("返回的响应:{}",res);
        return ResponseEntity.ok(res);
    }
}