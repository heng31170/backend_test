package com.zaizi.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Subscribe {
    String account;
    @JsonProperty("sub_account")
    String subscribedAccount;
}
