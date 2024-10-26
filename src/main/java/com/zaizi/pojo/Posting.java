package com.zaizi.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posting {
    Integer pid;
    String title;
    String text;
    String account;
    String tags;
    String files;
    @JsonProperty("ThumbPic")
    String thumbPic;
    @JsonProperty("ThumbPicWidth")
    Integer thumbPicWidth;
    @JsonProperty("ThumbPicHeight")
    Integer thumbPicHeight;
    Integer time;
    @JsonProperty("is_liked")
    Boolean isLiked;
}
