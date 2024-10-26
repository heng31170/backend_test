package com.zaizi.service;

import com.zaizi.pojo.Posting;
import org.springframework.web.multipart.MultipartFile;

public interface PostingService {
    //void posting(Posting posting);
    Integer handlePost(Posting posting) throws Exception;
    String saveFile(MultipartFile file,Posting posting) throws Exception;
    void compressImage(Posting posting) throws Exception;
    String getCtrl();
    void delete(Posting posting);
}
