package com.zaizi.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    String account;
    @JsonProperty("passwd") // 指定 JSON 中的字段名
    String passwd;
    @JsonProperty("old_passwd")
    String oldPasswd;
    String icon;
    String nickname;

    public User(String account, String oldPasswd) {
    }
}
