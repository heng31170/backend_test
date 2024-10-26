package com.zaizi.mapper;


import com.zaizi.pojo.Subscribe;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubscribeMapper {
    // 订阅
    @Insert("insert into subscribes (account,subscribedAccount) values (#{account},#{subscribedAccount})")
    void addSubscribe(Subscribe subscribe);
    // 取消订阅
    @Insert("delete from subscribes where account = #{account} and subscribedAccount = #{subscribedAccount}")
    void delSubscribe(Subscribe subscribe);
    // 查询订阅
    @Select("select subscribedAccount from subscribes where account = #{account}")
    List<String> queSubscribe(String account);
}
