package com.zaizi.mapper;


import com.zaizi.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    // 登录管理
    @Select("select account, icon, nickname from users where account= #{account} and passwd = #{passwd}")
    User getUerByAccountAndPwd(User user);

    // 注册管理
    @Insert("insert into users(account,passwd) values(#{account},#{passwd})")
    void addUser(User user);

    // 更新密码
    @Update("update users set passwd = #{passwd},icon = #{icon},nickname = #{nickname} where account = #{account} and passwd = #{oldPasswd}")
    int updateUser(User user);
    // 获取用户信息
    @Select("select account,icon,nickname from users where account = #{account}")
    User getUser(String account);

}
