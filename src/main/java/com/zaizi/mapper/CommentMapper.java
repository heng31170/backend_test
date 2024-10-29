package com.zaizi.mapper;


import com.zaizi.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 获取评论
    @Select("select id, pid, account, content, time from comments where pid = #{pid}")
    List<Comment> getComments(@Param("pid") Integer pid);
    // 添加评论
    @Insert("insert into comments (pid,account,content,time) values (#{pid},#{account},#{content},UNIX_TIMESTAMP())")
    void addComment(Comment comment);
    // 删除评论
//    @Delete("delete from comments where id = #{id} and pid = #{pid} and account = #{account} and content = #{content} and time = #{time}")
    void delComment(Comment comment);
}
