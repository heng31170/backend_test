package com.zaizi.service.impl;

import com.zaizi.mapper.PostingMapper;
import com.zaizi.pojo.Posting;
import com.zaizi.service.PostingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
public class PostingServiceImpl implements PostingService {
    @Autowired
    private PostingMapper postingMapper;


    String ctrl = null;
    // 是否有pid插入
    @Override
    public Integer handlePost(Posting posting) throws Exception {
        // 若pid存在则更新，否则插入
        if(posting.getPid() != null) {
            ctrl = "update";
            postingMapper.updatePosting(posting);
        } else {
            ctrl = "insert";
            postingMapper.addPosting(posting);
        }
        return posting.getPid();

    }
    public String getCtrl() {
        return ctrl;
    }

    @Override
    public void delete(Posting posting) {
        postingMapper.deletePosting(posting);
    }

    //    保存文件
    @Override
    public String saveFile(MultipartFile file,Posting posting) throws Exception {
        String uploadDir = "D:/Javaidea/springboot_learn/backend_test/src/main/resources/static/original/" + posting.getPid() + "/";
        String thumbDir = "D:/Javaidea/springboot_learn/backend_test/src/main/resources/static/thumbnail/" + posting.getPid() + "/";
        File directory_ori = new File(uploadDir);
        File directory_thu = new File(thumbDir);
        if(!directory_ori.exists()) {
            directory_ori.mkdirs();  // 创建目录
        }
        if(!directory_thu.exists()) {
            directory_thu.mkdirs();
        }
        String filePath = uploadDir + file.getOriginalFilename();
        try{
            file.transferTo(new File(filePath));  //保存文件
        } catch (Exception e) {
            log.error("文件上传失败:{}",e.getMessage());
            throw new Exception("File upload failed" + e.getMessage());
        }
        return filePath;   // 返回文件路径
    }


//    压缩图片
    @Override
    public void compressImage(Posting posting) throws Exception {
        String thumbPicPath = "D:/Javaidea/springboot_learn/backend_test/src/main/resources/static/thumbnail/" + posting.getPid() + "/" + posting.getThumbPic();
        String originalPicPath = "D:/Javaidea/springboot_learn/backend_test/src/main/resources/static/original/" + posting.getPid() + "/" + posting.getThumbPic();

        log.info("原始图片路径: {}", originalPicPath);
        log.info("略缩图路径: {}", thumbPicPath);

        // 检查原始文件是否存在
        File originalFile = new File(originalPicPath);
        if (!originalFile.exists()) {
            log.error("原始图片文件不存在: {}", originalPicPath);
            delete(posting);
            log.info("成功删除帖子:{}",posting);
            throw new Exception("Original image file does not exist");
        }

        String command = String.format("ffmpeg -y -i \"%s\" -vf scale=%d:%d \"%s\"",
                originalPicPath, posting.getThumbPicWidth(), posting.getThumbPicHeight(), thumbPicPath);

        Process process;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            log.error("执行命令失败: {}", e.getMessage());
            throw new Exception("Execution failed: " + e.getMessage());
        }

        StringBuilder output = new StringBuilder();
        StringBuilder errorOutput = new StringBuilder();

        // 读取标准输出
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        // 读取错误输出
        try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line;
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            log.error("ffmpeg 执行错误，标准输出: {}", output.toString());
            log.error("ffmpeg 错误输出: {}", errorOutput.toString());
            throw new Exception("Image compression failed");
        }
    }
}




























