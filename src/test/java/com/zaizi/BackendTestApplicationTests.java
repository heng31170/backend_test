package com.zaizi;

import com.zaizi.mapper.UserMapper;
import com.zaizi.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BackendTestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    // 查询
    @Test
    public void test1() {
        User user = new User();
        user.setAccount("xyr");
        user.setPasswd("123456");
        User result = userMapper.getUerByAccountAndPwd(user);
        System.out.println(result);
    }


}
