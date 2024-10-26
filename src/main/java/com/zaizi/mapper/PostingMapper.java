package com.zaizi.mapper;

import com.zaizi.pojo.Posting;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PostingMapper {

    void addPosting(Posting posting);
    void updatePosting(Posting posting);
    @Delete("delete from postings where pid = #{pid}")
    void deletePosting(Posting posting);
}