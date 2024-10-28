package com.zaizi.mapper;


import com.zaizi.pojo.Posting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GetPostsMapper {
    // 获取帖子信息
    @Select("select pid, title, text, tags, account, files, time, thumbPic, thumbPicWidth, thumbPicHeight,is_liked from postings where account = #{account}")
    List<Posting> getPostsByName(@Param("account") String account);

    // 收藏
    @Update("update postings set is_liked = true where account = #{account} and pid = #{pid}")
    void likePost(Posting posting);
    //取消收藏
    @Update("update postings set is_liked = false where account = #{account} and pid = #{pid}")
    void dislikePost(Posting posting);
    // 查询点赞
    @Select("select pid from postings where is_liked = true and account = #{account}")
    List<Integer> queLikePost(String account);

    // 获取指定帖子
    @Select("select pid, title, text, tags, account, files, time, thumbPic, thumbPicWidth, thumbPicHeight,is_liked from postings where account = #{account}")
    List<Posting> getSpecifyPosts(@Param("account") String account);
    // 获取动态
    @Select("SELECT p.pid, p.title, p.text, p.tags, p.account, p.files, p.time, p.thumbPic, p.thumbPicWidth, p.thumbPicHeight, p.is_liked " +
            "FROM subscribes s JOIN postings p ON s.subscribedAccount = p.account WHERE s.account = #{account}")
    List<Posting> getFollowPosts(@Param("account") String account);
    // 搜索帖子
    List<Posting> searchPosts(@Param("account") String account,@Param("pid") Integer pid,@Param("title") String title,@Param("text") String text);


}
